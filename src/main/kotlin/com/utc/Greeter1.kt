package com.utc

import akka.actor.typed.ActorRef
import akka.actor.typed.ActorSystem
import akka.actor.typed.Behavior
import akka.actor.typed.Terminated
import akka.actor.typed.Props
import akka.actor.typed.DispatcherSelector
import akka.actor.typed.javadsl.ActorContext
import akka.actor.typed.javadsl.Behaviors



object Greeter1 {

    sealed class Command {
        data class Greet(val who: String) : Command()
    }

    val greeter: Behavior<Command.Greet> =
            Behaviors.receive<Command.Greet> {
                ctx: ActorContext<Command.Greet>, msg: Command.Greet ->
                    System.out.println("Hello ${msg.who}")
                    Behaviors.same()
            }

}
