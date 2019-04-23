package com.utc.es


import akka.persistence.typed.PersistenceId
import akka.persistence.typed.javadsl.CommandHandler
import akka.persistence.typed.javadsl.EventHandler
import akka.persistence.typed.javadsl.EventSourcedBehavior

object TestTypedPersistence {

    sealed class Command {
        data class Add(val item: String) : Command()
        class Clear : Command()
        class PrintState : Command()
    }

    sealed class Event {
        data class Added(val item: String) : Event()
        class Cleared : Event()
    }

    data class State(val items: List<String> = emptyList()) {
        fun addItem(item: String): State = State((listOf(item) + items).take(5))
    }

    class TestTypedPersistence(persistenceId: PersistenceId) : EventSourcedBehavior<Command, Event, State>(persistenceId) {

        override fun emptyState(): State = State()

        override fun commandHandler(): CommandHandler<Command, Event, State> =
                CommandHandler<Command, Event, State> { state: State, command: Command ->
                    when (command) {
                        is Command.Add -> Effect().persist(Event.Added(command.item))
                        is Command.Clear -> Effect().persist(Event.Cleared())
                        is Command.PrintState -> Effect().none().thenRun { newState: State ->  println(newState.items) }
                    }
                }


        override fun eventHandler(): EventHandler<State, Event> =
                EventHandler<State, Event> { state, event ->
                    when (event) {
                        is Event.Added -> state.addItem(event.item)
                        is Event.Cleared -> emptyState()
                    }
                }

    }

}