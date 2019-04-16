package com.utc

import akka.actor.typed.ActorSystem


fun main(args: Array<String>) {

    val system =
            ActorSystem.create<Greeter2.Command>(Greeter2.greeter, "hello-2")

    system.tell(Greeter2.Command.Greet("World!"))
    system.tell(Greeter2.Command.ChangeGreeting("Goodbye"))
    system.tell(Greeter2.Command.Greet("cruel world."))

    try {
        println("Press ENTER to exit the system")
        readLine()
    } finally {
        system.terminate()
    }
}
