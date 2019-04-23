package com.utc.es

import akka.actor.typed.ActorSystem;
import akka.persistence.typed.PersistenceId
import com.typesafe.config.ConfigFactory


fun main(args: Array<String>) {


    val system = ActorSystem.create(TestTypedPersistence.TestTypedPersistence(PersistenceId("test")), "test",
            ConfigFactory.parseString("""
            akka.persistence.journal.plugin = "akka.persistence.journal.inmem"
            akka.persistence.snapshot-store.plugin = "akka.persistence.snapshot-store.local"
        """.trimIndent()))

    system.tell(TestTypedPersistence.Command.Add("Item #1"))
    system.tell(TestTypedPersistence.Command.Add("Item #2"))
    system.tell(TestTypedPersistence.Command.PrintState())
    system.tell(TestTypedPersistence.Command.Clear())
    system.tell(TestTypedPersistence.Command.PrintState())

    try {
        println("Press ENTER to exit the system")
        readLine()
    } finally {
        system.terminate()
    }
}