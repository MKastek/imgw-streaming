package pl.edu.imgw

import cats.effect.{ExitCode, IO, IOApp}
import cats.implicits.catsSyntaxApplicativeId
import org.http4s.circe.CirceEntityCodec.circeEntityDecoder
import org.typelevel.log4cats.LoggerFactory
import org.typelevel.log4cats.slf4j.Slf4jFactory
import pl.edu.imgw.Utils.IODebugOps
import pl.edu.imgw.data.{HydroData, WeatherData}
import pl.edu.imgw.api.IMGWApi
import doobie._
import doobie.implicits._

import java.time.Instant

object Main extends IOApp {

  implicit val logging: LoggerFactory[IO] = Slf4jFactory[IO]

  val xa: Transactor[IO] = Transactor.fromDriverManager[IO](
    "org.postgresql.Driver",
    "jdbc:postgresql:imgw",
    "docker", // username
    "docker" // password
  )

  def saveweatherData(weatherData: WeatherData): IO[Int] = {
    val weatherDataInsert: doobie.ConnectionIO[Int] =
      sql"insert into weather_data values (${weatherData.id_stacji},${weatherData.stacja},${weatherData.data_pomiaru.toString} :: date,${weatherData.godzina_pomiaru},${weatherData.temperatura},${weatherData.predkosc_wiatru},${weatherData.kierunek_wiatru},${weatherData.wilgotnosc_wzgledna},${weatherData.suma_opadu},${weatherData.cisnienie})".update.run
    weatherDataInsert.transact(xa)
  }

  override def run(args: List[String]): IO[ExitCode] = {
    IMGWApi
      .getStream[IO, WeatherData]
      .evalMap(x =>

        saveweatherData(x) >> x.pure[IO].debug
      )
      .merge(
        IMGWApi
          .getStream[IO, HydroData]
          .evalMap(x =>
            x.pure[IO].debug
          )
      )
      .compile
      .drain
      .as(ExitCode.Success)

  }
}
