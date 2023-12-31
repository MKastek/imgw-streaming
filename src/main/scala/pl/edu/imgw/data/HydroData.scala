package pl.edu.imgw.data

import io.circe.Decoder
import io.circe.generic.semiauto.deriveDecoder

import java.time.Instant

final case class HydroData(
    id_stacji: Int,
    stacja: String,
    rzeka: String,
    województwo: String,
    stan_wody: Option[Int],
    stan_wody_data_pomiaru: Option[Instant],
    temperatura_wody: Option[Float],
    temperatura_wody_data_pomiaru: Option[Instant],
    zjawisko_lodowe: Option[Float],
    zjawisko_lodowe_data_pomiaru: Option[Instant],
    zjawisko_zarastania: Option[Float],
    zjawisko_zarastania_data_pomiaru: Option[Instant]
)

object HydroData {
  implicit val decoder: Decoder[HydroData] = deriveDecoder[HydroData]
  implicit val decodeInstant: Decoder[Instant] = Decoder.decodeString.map { str =>
    Instant.parse(str.replace(' ','T').concat("Z"))
  }
}
