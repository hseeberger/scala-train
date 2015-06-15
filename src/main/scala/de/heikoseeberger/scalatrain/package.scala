/*
 * Copyright 2015 Heiko Seeberger
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package de.heikoseeberger

import scala.annotation.tailrec
import scala.collection.immutable.Seq

package object scalatrain {

  def isIncreasing[A <: Ordered[A]](as: Seq[A]): Boolean =
    as.sliding(2)
      .forall {
        case Seq(a1, a2) => a1 < a2
        case _           => true
      }

  @tailrec
  def isIncreasingTailrec[A <: Ordered[A]](as: Seq[A]): Boolean =
    as match {
      case Seq(a1, a2, _*) => a1 < a2 && isIncreasingTailrec(as.tail)
      case _               => true
    }
}
