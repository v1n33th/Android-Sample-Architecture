package com.floraonline.floranational.utlities.onetimeevent

import java.util.concurrent.atomic.AtomicBoolean

/**
 * Use this data class to handle livedata that needs to be handled only once
 * Ref: https://github.com/android/architecture-samples/blob/dev-todo-mvvm-live/todoapp/app/src/main/java/com/example/android/architecture/blueprints/todoapp/SingleLiveEvent.java
 */
class OneTimeEvent<T>(
    private val value: T
) {

    private val isConsumed = AtomicBoolean(false)

    internal fun getValue(): T? =
        if (isConsumed.compareAndSet(false, true)) value
        else null

    internal fun peekData(): T = value
}

fun <T> T.toOneTimeEvent() =
    OneTimeEvent(this)



