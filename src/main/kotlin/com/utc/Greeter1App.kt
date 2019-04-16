package com.utc

import akka.actor.typed.ActorSystem






    fun main(args: Array<String>) {

        val system =
                ActorSystem.create<Greeter1.Command.Greet>(Greeter1.greeter, "hello")

        system.tell(Greeter1.Command.Greet("World!"))


        try {
            println("Press ENTER to exit the system")
            readLine()
        } finally {
            system.terminate()
        }
    }

