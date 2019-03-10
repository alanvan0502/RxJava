package com.jonbott.learningrxjava.SimpleExamples

import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable

class ObservableExample {

    companion object {
        fun disposableExample() {
            val observable = Observable.fromArray(1, 2, 3, 4, 5, 6, 7, 8, 9)

            val bag = CompositeDisposable()

            val disposable = observable.subscribe({ number: Int -> println(number) },
                    { error -> println("some error happened: $error") },
                    { println("completed") },
                    { println("subscription is connected")})

            bag.add(disposable)

            bag.dispose()
        }

        // 3 types of observables to work with: Relay (Hot Observable), Subjects (Hot Observable), Observables
        // Traits: Single (onNext, onError), Completable(onComplete, onError), Maybe(onNext/onComplete, onError)
    }

}