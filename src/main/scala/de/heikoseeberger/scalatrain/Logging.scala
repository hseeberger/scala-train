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

package de.heikoseeberger.scalatrain

import org.apache.logging.log4j.{ Logger => Log4jLogger, LogManager }

trait Logging {
  protected val logger: Logger =
    new Logger(LogManager.getLogger(getClass.getName))
}

final class Logger private[scalatrain] (underlying: Log4jLogger) {

  def fatal(message: => Any): Unit =
    if (underlying.isFatalEnabled) underlying.fatal(message)

  def fatal(message: => Any, t: Throwable): Unit =
    if (underlying.isFatalEnabled) underlying.fatal(message, t)

  def error(message: => Any): Unit =
    if (underlying.isErrorEnabled) underlying.error(message)

  def error(message: => Any, t: Throwable): Unit =
    if (underlying.isErrorEnabled) underlying.error(message, t)

  def warn(message: => Any): Unit =
    if (underlying.isWarnEnabled) underlying.warn(message)

  def info(message: => Any): Unit =
    if (underlying.isInfoEnabled) underlying.info(message)

  def debug(message: => Any): Unit =
    if (underlying.isDebugEnabled) underlying.debug(message)

  def trace(message: => Any): Unit =
    if (underlying.isTraceEnabled) underlying.trace(message)
}
