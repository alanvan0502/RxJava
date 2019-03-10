package com.jonbott.learningrxjava.SimpleExamples

import io.reactivex.disposables.CompositeDisposable
import com.jakewharton.rxrelay2.BehaviorRelay
import com.jonbott.learningrxjava.Common.disposedBy
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.toObservable
import io.reactivex.subjects.BehaviorSubject
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import java.lang.IllegalArgumentException
import java.util.concurrent.TimeUnit

object SimpleRx {

    var bag = CompositeDisposable()

    private val observableOnSubscribe = ObservableOnSubscribe<Int> { emitter ->
        if (!emitter.isDisposed) {
            emitter.onNext(1)
            emitter.onNext(2)
            emitter.onNext(3)
            emitter.onNext(4)
            emitter.onNext(5)
            emitter.onNext(6)
            emitter.onComplete()
        }
    }

    fun simpleValues() {
        println("~~~~~~simpleValues~~~~~~")

        val someInfo = BehaviorRelay.createDefault("1")
        println("🙈 someInfo.value ${ someInfo.value }")

        val plainString = someInfo.value
        println("🙈 plainString: $plainString")

        someInfo.accept("2")
        println("🙈 someInfo.value ${ someInfo.value }")

        someInfo.subscribe { newValue -> println( "🦁 Value has changed: $newValue " )}.let {
            bag.add(it)
        }
        someInfo.accept("3")

        bag.dispose()
        //NOTE: Relays receive the most recent value and the subsequent values. Relays will never receive onError and onComplete events
    }

    fun subjects() {
        val behaviorSubject = BehaviorSubject.createDefault(24)
        behaviorSubject.onNext(33)
        behaviorSubject.onNext(31)
        val disposable = behaviorSubject.subscribe(
                { newValue: Int -> println("🐮 behaviorSubject subscription: $newValue") }, //onNext
                { error -> println("error ${error.localizedMessage}")}, //onError
                { println("completed")}, //onComplete
                { println("subscribed")} //onSubscribed
        )
        behaviorSubject.onNext(34)
        behaviorSubject.onNext(48)
        behaviorSubject.onNext(48)

        val subject = BehaviorSubject.create(observableOnSubscribe)

        val sDisposable = subject.subscribe {
            value: Int -> println(value)
        }

        //1 onError
        val someException = IllegalArgumentException("some fake error")
//        behaviorSubject.onError(someException)
//        behaviorSubject.onNext(109) // will never show

        behaviorSubject.onComplete()
        behaviorSubject.onNext(110)

        disposable.dispose()
    }

    fun basicObservable() {
        // The observable
        val observable = Observable.create<String> {
            observer ->
            // The lambda is called for every subscriber
            println("🐋 Observable being triggered")
            launch {
                delay(1000)
                observer.onNext("some value 23")
                observer.onComplete()
            }
        }

        observable.subscribe{ someString -> println("\uD83D\uDC0B new value: $someString")}.disposedBy(bag)

        observable.subscribe { someString -> println("\uD83D\uDC0B another subscribe: $someString")}.disposedBy(bag)

        val anotherObservable = Observable.create<String>(anotherObservableOnSubscribe)
        anotherObservable.subscribe { someString -> println("yet another subscriber: $someString")}.disposedBy(bag)
    }

    var anotherObservableOnSubscribe = ObservableOnSubscribe<String> {
        println("Another observable being triggered")
        launch {
            delay(1000)
            it.onNext("some value 24")
            it.onComplete()
        }
    }

    fun creatingObservables() {
        val observable1 = Observable.just("Alan Van", 23)
        val observable2 = Observable.interval(300, TimeUnit.MILLISECONDS).timeInterval(AndroidSchedulers.mainThread())
        val observale3 = Observable.fromArray(1, 2, 3, 4, 5)

        val userIds = arrayOf(1,2,3,4,5)
        val observable4 = Observable.fromArray(*userIds)
        val observable5 = userIds.toObservable()
    }
}