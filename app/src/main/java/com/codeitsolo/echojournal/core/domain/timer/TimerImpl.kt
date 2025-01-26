package com.codeitsolo.echojournal.core.domain.timer

import com.codeitsolo.echojournal.core.coroutines.AppDispatchers
import com.codeitsolo.echojournal.core.coroutines.Dispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Implementation of the [Timer] interface.
 */
class TimerImpl @Inject constructor(
    @Dispatcher(AppDispatchers.DEFAULT) private val defaultDispatcher: CoroutineDispatcher
) : Timer {

    private var coroutineScope = CoroutineScope(defaultDispatcher)
    private var isActive = false

    private val _timer = MutableStateFlow(0L)
    override val time: Flow<Long> = _timer.asSharedFlow()

    private var lastTimestamp = 0L
    private var timeMillis = 0L

    override fun start() {
        if (isActive) return
        coroutineScope.launch {
            lastTimestamp = System.currentTimeMillis()
            this@TimerImpl.isActive = true
            while (true) {
                ensureActive()
                delay(10L)
                if (this@TimerImpl.isActive) {
                    timeMillis += System.currentTimeMillis() - lastTimestamp
                    _timer.emit(timeMillis / 1000)
                }
                lastTimestamp = System.currentTimeMillis()
            }
        }
    }

    override fun resume() {
        isActive = true
    }

    override fun stop() {
        isActive = false
    }

    override fun cancel() {
        coroutineScope.cancel()
        coroutineScope = CoroutineScope(defaultDispatcher)
        timeMillis = 0L
        lastTimestamp = 0L
        isActive = false
    }
}