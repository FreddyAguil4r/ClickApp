package com.example.click

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {

    private val timer: CountDownTimer

    //Set up live data
    private var _currentTime = MutableLiveData<Long>()
    val currentTime: LiveData<Long> get() = _currentTime

    private var _score = MutableLiveData<Int>()
    val score: LiveData<Int> get() = _score

    private var _currentButton = MutableLiveData<Int>()
    val currentButton: LiveData<Int> get() = _currentButton

    private var _scoreColor = MutableLiveData<String>()
    val scoreColor: LiveData<String> get() = _scoreColor

    private var _gameFinished = MutableLiveData<Boolean>()
    val gameFinished: LiveData<Boolean> get() = _gameFinished

    init {
        _score.value = 0
        _currentButton.value = (1..4).random()
        _scoreColor.value = "purple"
        _gameFinished.value = false
        timer = object : CountDownTimer(31000, 1000) {
            override fun onTick(p0: Long) {
                _currentTime.value = p0 / 1000
                _scoreColor.value = "purple"
            }

            override fun onFinish() {
                _gameFinished.value = true
            }

        }
        timer.start()
    }

    //Gain a point because you clicked a button
    fun gainPoint(){
        _score.value = _score.value?.plus(1)
        _currentButton.value = (1..4).random()
        _scoreColor.value = "purple"
    }

    //Lose a point because you missed a button
    fun losePoint(){
        _score.value = _score.value?.plus(-1)
        _currentButton.value = (1..4).random()
        _scoreColor.value = "red"
    }
}