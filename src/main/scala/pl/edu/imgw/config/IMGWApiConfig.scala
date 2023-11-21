package pl.edu.imgw.config

import cats.implicits._
import ciris.{ConfigValue, env}
import org.http4s.Uri

import scala.concurrent.duration.FiniteDuration
import scala.reflect.runtime.universe._

object IMGWApiConfig {
  final case class IMGWApiConfig(
      endpoint: Uri,
      frequency: FiniteDuration
  )

  def make[F[_], R: TypeTag]: ConfigValue[F, IMGWApiConfig] = {
    (
      env("IMGW_WEATHER_ENDPOINT")
        .as[String]
        .default("https://danepubliczne.imgw.pl/api/data/synop"),
      env("IMGW_HYDRO_ENDPOINT")
        .as[String]
        .default("https://danepubliczne.imgw.pl/api/data/hydro"),
      env("FREQUENCY").as[FiniteDuration].default(FiniteDuration(10, "seconds"))
    ).parMapN((weatherEndpoint, hydroEndpoint, frequency) => {
      println(typeOf[R].typeSymbol.asType.name)
      typeOf[R].typeSymbol.asType.name.toString match {
        case "WeatherData" => IMGWApiConfig(Uri.unsafeFromString(weatherEndpoint), frequency)
        case "HydroData"   => IMGWApiConfig(Uri.unsafeFromString(hydroEndpoint), frequency)
      }
    })
  }
}
