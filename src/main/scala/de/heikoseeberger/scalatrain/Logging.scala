package de.heikoseeberger.scalatrain

import org.apache.logging.log4j.{ Logger => Log4jLogger, LogManager }

trait Logging {
  protected val logger: Logger = new Logger(LogManager.getLogger(getClass.getName))
}

class Logger private[scalatrain] (underlying: Log4jLogger) {

  def fatal(message: => Any): Unit = if (underlying.isFatalEnabled) underlying.fatal(message)

  def fatal(message: => Any, t: Throwable): Unit = if (underlying.isFatalEnabled) underlying.fatal(message, t)

  def error(message: => Any): Unit = if (underlying.isErrorEnabled) underlying.error(message)

  def error(message: => Any, t: Throwable): Unit = if (underlying.isErrorEnabled) underlying.error(message, t)

  def warn(message: => Any): Unit = if (underlying.isWarnEnabled) underlying.warn(message)

  def info(message: => Any): Unit = if (underlying.isInfoEnabled) underlying.info(message)

  def debug(message: => Any): Unit = if (underlying.isDebugEnabled) underlying.debug(message)

  def trace(message: => Any): Unit = if (underlying.isTraceEnabled) underlying.trace(message)
}
