package com.example.myapplication100

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class ScoreViewModel(application: Application) : AndroidViewModel(application) {
    private val sharedPreferences: SharedPreferences =
        application.getSharedPreferences("ScoresPrefs", Context.MODE_PRIVATE)

    private val _scoreTeamOne = MutableLiveData<Int>().apply { value = loadScore("team_one") }
    val scoreTeamOne: LiveData<Int> = _scoreTeamOne

    private val _scoreTeamTwo = MutableLiveData<Int>().apply { value = loadScore("team_two") }
    val scoreTeamTwo: LiveData<Int> = _scoreTeamTwo

    private val _finalResult = MutableLiveData<String>()
    val finalResult: LiveData<String> = _finalResult

    fun increaseTeamOneScore() {
        _scoreTeamOne.value = (_scoreTeamOne.value ?: 0) + 1
        saveScore("team_one", _scoreTeamOne.value ?: 0)
    }

    fun increaseTeamTwoScore() {
        _scoreTeamTwo.value = (_scoreTeamTwo.value ?: 0) + 1
        saveScore("team_two", _scoreTeamTwo.value ?: 0)
    }

    fun checkFinalResult() {
        val result = when {
            scoreTeamOne.value == scoreTeamTwo.value -> "The two teams are equal"
            scoreTeamOne.value!! > scoreTeamTwo.value!! -> "Team One is the winner"
            else -> "Team Two is the winner"
        }
        _finalResult.value = result
    }


    private fun saveScore(key: String, score: Int) {
        with(sharedPreferences.edit()) {
            putInt(key, score)
            apply()
        }
    }

    private fun loadScore(key: String): Int {
        return sharedPreferences.getInt(key,0)
    }
}
