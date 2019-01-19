package com.leodeleon.onflick.feed

import com.leodeleon.data.SchedulerFactory
import io.reactivex.schedulers.Schedulers

class TestSchedulers: SchedulerFactory {
    override fun main() = Schedulers.trampoline()

    override fun io() = Schedulers.trampoline()
}