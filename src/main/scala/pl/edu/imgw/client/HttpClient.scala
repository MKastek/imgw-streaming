package pl.edu.imgw.client

import cats.effect.{Async, IO, Resource}
import org.http4s.blaze.client.BlazeClientBuilder
import org.http4s.client.Client

import scala.concurrent.ExecutionContext.Implicits.global

object HttpClient {
  def make[F[_]: Async](): Resource[F, Client[F]] = {
    for{
      client <- BlazeClientBuilder[F](global).resource
    } yield client
  }
}
