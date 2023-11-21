package pl.edu.imgw.data

import io.circe.Decoder
import io.circe.generic.semiauto.deriveDecoder

final case class WeatherData(
    id_stacji: String,
    stacja: String,
    data_pomiaru: String,
    godzina_pomiaru: String,
    temperatura: String,
    predkosc_wiatru: Option[String],
    kierunek_wiatru: Option[String],
    wilgotnosc_wzgledna: Option[String],
    suma_opadu: String,
    cisnienie: Option[String]
)

object WeatherData {
  implicit val decoder: Decoder[WeatherData] = deriveDecoder[WeatherData]
}
