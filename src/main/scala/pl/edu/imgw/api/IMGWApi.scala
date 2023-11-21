package pl.edu.imgw.api

import cats.effect.{Async, Clock}
import fs2.Stream
import org.http4s.EntityDecoder
import org.http4s.client.Client
import pl.edu.imgw.client.HttpClient
import pl.edu.imgw.config.IMGWApiConfig

import scala.reflect.runtime.universe._

object IMGWApi {

  def getStream[F[_]: Async: Clock, R: TypeTag](implicit
      decoder: EntityDecoder[F, List[R]]
  ): Stream[F, R] = {

    def makeAPIStream(httpClient: Client[F]) =
      Stream
        .eval(IMGWApiConfig.make[F, R].load)
        .flatMap { config =>
          Stream(config)
            .evalMap { _ =>
              httpClient.expect[List[R]](config.endpoint)
            }
        }

    Stream
      .resource(HttpClient.make[F]())
      .flatMap(makeAPIStream)
      .flatMap(Stream.emits(_))
  }
}
