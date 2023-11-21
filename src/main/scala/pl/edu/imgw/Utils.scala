package pl.edu.imgw

import cats.effect.IO

object Utils {

  implicit class IODebugOps[A](io: IO[A]) {
    def debug: IO[A] = io.map { a =>
      println(s"[${Thread.currentThread().getName}] $a")
      a
    }
  }
}
