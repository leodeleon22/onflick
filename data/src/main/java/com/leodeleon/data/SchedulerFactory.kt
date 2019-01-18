package com.leodeleon.data

import io.reactivex.Scheduler

interface SchedulerFactory {
    fun main(): Scheduler
    fun io(): Scheduler
}