package com.leodeleon.data

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SchedulerProvider: SchedulerFactory {

    override fun main() = AndroidSchedulers.mainThread()

    override fun io() = Schedulers.io()
}