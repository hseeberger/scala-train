package de.heikoseeberger

import scala.annotation.tailrec
import scala.collection.immutable.Seq

package object scalatrain {

  def isIncreasing[A <: Ordered[A]](as: Seq[A]): Boolean = as
    .sliding(2) // scalacheck:off
    .forall {
      case Seq(a1, a2) => a1 < a2
      case _           => true
    }

  @tailrec
  def isIncreasingTailrec[A <: Ordered[A]](as: Seq[A]): Boolean = as match {
    case Seq(a1, a2, _*) => a1 < a2 && isIncreasingTailrec(as.tail)
    case _               => true
  }
}
