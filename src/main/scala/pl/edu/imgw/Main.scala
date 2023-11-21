package pl.edu.imgw

import cats.effect.{ExitCode, IO, IOApp}
import org.http4s.circe.CirceEntityCodec.circeEntityDecoder
import org.typelevel.log4cats.LoggerFactory
import org.typelevel.log4cats.slf4j.Slf4jFactory
import pl.edu.imgw.Utils.IODebugOps
import pl.edu.imgw.data.{HydroData, WeatherData}
import pl.edu.imgw.api.IMGWApi

object Main extends IOApp {

  implicit val logging: LoggerFactory[IO] = Slf4jFactory[IO]

  override def run(args: List[String]): IO[ExitCode] = {

    IMGWApi
      .getStream[IO, WeatherData]
      .evalMap(x =>
        IO {
          Thread.sleep(200)
          x
        }.debug
      )
      .merge(
        IMGWApi
          .getStream[IO, HydroData]
          .evalMap(x =>
            IO {
              Thread.sleep(300)
              x
            }.debug
          )
      )
      .compile
      .drain
      .as(ExitCode.Success)

  }
}
