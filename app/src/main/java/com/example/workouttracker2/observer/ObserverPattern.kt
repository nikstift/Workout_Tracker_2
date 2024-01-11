package com.example.workouttracker2.observer

class Observable(
    val subscribers: MutableList<Observer> = ArrayList()
) {
    fun addObserver(observer: Observer) {
        subscribers.add(observer)
    }
    fun removeObserver(observer: Observer) {
        subscribers.remove(observer)
    }
    fun notifyObservers(event: Event) {
        subscribers.forEach {
            it.update(event)
        }
    }
}

// Anderer Name Subscriber
interface Observer {
    fun update(event: Event)
}

data class Event(val content: String)



