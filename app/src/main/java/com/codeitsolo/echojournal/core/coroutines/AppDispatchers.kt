package com.codeitsolo.echojournal.core.coroutines

import javax.inject.Qualifier

/**
 * Represents dispatchers for coroutines.
 */
enum class AppDispatchers {

    /**
     * Dispatcher for IO-bound tasks.
     */
    IO,

    /**
     * Dispatcher for CPU-bound tasks.
     */
    DEFAULT,

    /**
     * Dispatcher for main-thread tasks.
     */
    MAIN
}

/**
 * Annotation for dispatchers.
 */
@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class Dispatcher(val dispatcher: AppDispatchers)