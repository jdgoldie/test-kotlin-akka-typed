package com.utc

import akka.actor.typed.javadsl.Behaviors
import akka.actor.typed.Behavior

object Greeter2 {


    sealed class Command {
        data class Greet(val who: String) : Command()
        data class ChangeGreeting(val newGreeting: String) : Command()
    }

    val greeter = greeter("Hello")

    fun greeter(greetingValue: String) : Behavior<Greeter2.Command> =
        Behaviors.receiveMessage<Greeter2.Command> {  msg ->
            when(msg) {
                is Greeter2.Command.Greet -> {
                    println("${greetingValue}, ${msg.who}")
                    Behaviors.same()
                }
                is Greeter2.Command.ChangeGreeting -> {
                    greeter(msg.newGreeting)
                }
            }

        }


}