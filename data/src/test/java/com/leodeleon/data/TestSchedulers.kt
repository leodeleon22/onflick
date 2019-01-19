package com.leodeleon.data

import io.reactivex.schedulers.Schedulers

class TestSchedulers: SchedulerFactory {
    override fun main() = Schedulers.trampoline()

    override fun io() = Schedulers.trampoline()
}