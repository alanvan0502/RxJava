package com.jonbott.learningrxjava.Activities.ReactiveUi.Complex

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.jonbott.learningrxjava.R
import com.jonbott.learningrxjava.databinding.ActivityComplexUiBinding
import io.reactivex.disposables.CompositeDisposable


//https://github.com/ahmedrizwan/RxRecyclerAdapter

private enum class CellType {
    ITEM,
    ITEM2
}

class ReactiveUIActivity : AppCompatActivity() {

    private val dataSet = (0..100).toList().map { it.toString() }
    private var bag = CompositeDisposable()
    lateinit var boundActivity: ActivityComplexUiBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_complex_ui)

        commonInit()
        showSimpleBindingExample()
    }

    private fun showSimpleBindingExample() {
        
    }

    private fun commonInit() {

    }
}