package com.jonbott.learningrxjava

import com.jonbott.learningrxjava.SimpleExamples.ObservableExample
import com.jonbott.learningrxjava.SimpleExamples.SimpleRx
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun testDisposableExample() {
        ObservableExample.disposableExample()
    }

    @Test
    fun testSimpleValues() {
        //SimpleRx.simpleValues()
        SimpleRx.subjects()
    }

    @Test
    fun testBasicObservable() {
        SimpleRx.basicObservable()
    }
}
