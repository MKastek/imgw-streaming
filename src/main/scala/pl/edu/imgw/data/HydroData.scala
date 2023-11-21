package pl.edu.imgw.data

import io.circe.Decoder
import io.circe.generic.semiauto.deriveDecoder

final case class HydroData(
    id_stacji: String,
    stacja: String,
    rzeka: String,
    województwo: String,
    stan_wody: Option[String],
    stan_wody_data_pomiaru: Option[String],
    temperatura_wody: Option[String],
    temperatura_wody_data_pomiaru: Option[String],
    zjawisko_lodowe: Option[String],
    zjawisko_lodowe_data_pomiaru: Option[String],
    zjawisko_zarastania: Option[String],
    zjawisko_zarastania_data_pomiaru: Option[String]
)

object HydroData {
  implicit val decoder: Decoder[HydroData] = deriveDecoder[HydroData]
}
