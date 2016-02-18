package io.github.junheng.akka.utils

import akka.actor.{Actor, ActorLogging, ActorRef}
import akka.util.Timeout

import scala.concurrent.Await
import scala.concurrent.duration._
import scala.language.postfixOps

/**
  * Please use service locator instead. see akka-locator
  */
@Deprecated
trait ActorDependency {
  this: Actor =>

  implicit private val timeout = Timeout(2 seconds)

  def depends(path: String) = {
    var refOpt: Option[ActorRef] = None
    var retried = 0
    while (refOpt.isEmpty && retried < 10) {
      try {
        refOpt = Some(Await.result(context.actorSelection(path).resolveOne().mapTo[ActorRef], 2 seconds))
      } catch {
        case ex: Exception => Thread.sleep(2000)
      }
      retried += 1
    }
    refOpt
  }
}