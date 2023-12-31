package pl.edu.imgw.data

import io.circe.Decoder
import io.circe.generic.semiauto.deriveDecoder

import java.time.LocalDate
import java.time.format.DateTimeFormatter

final case class WeatherData(
                              id_stacji: Int,
                              stacja: String,
                              data_pomiaru: LocalDate,
                              godzina_pomiaru: Int,
                              temperatura: Float,
                              predkosc_wiatru: Option[Float],
                              kierunek_wiatru: Option[Float],
                              wilgotnosc_wzgledna: Option[Float],
                              suma_opadu: Float,
                              cisnienie: Option[Float]
)

object WeatherData {
  implicit val decoder: Decoder[WeatherData] = deriveDecoder[WeatherData]
  implicit val decodeLocalDate: Decoder[LocalDate] = Decoder.decodeString.map { str =>
    LocalDate.parse(str, DateTimeFormatter.ofPattern("yyyy-MM-dd"))
  }
}
