package com.example.happy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast


class MainActivity : AppCompatActivity() {

    internal var score = 0
    internal var gameStarted = false
    internal lateinit var tapMeButton: Button
    internal lateinit var gameScore: TextView
    internal lateinit var timeLeft: TextView
    internal lateinit var countDownTimer: CountDownTimer
    internal val initCountDown: Long = 6000
    internal val countDownInterval: Long = 1000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tapMeButton = findViewById(R.id.btnTapMe)
        gameScore = findViewById(R.id.txtScore)
        timeLeft = findViewById(R.id.txtTime)

        tapMeButton.setOnClickListener { view ->
            incrementScore()
        }
        resetGame()

    }

    private fun incrementScore() {
        if (!gameStarted) {
            startGame()
        }
        score += 1
        val newScore = getString(R.string.yourScore, score)
        gameScore.text = newScore
    }

    private fun resetGame() {
        score = 0
        gameScore.text = getString(R.id.txtScore, score)

        val initialTimeLeft = initCountDown / 1000
        timeLeft.text = getString(R.string.timeLeft, initialTimeLeft)

        countDownTimer = object : CountDownTimer(initCountDown, countDownInterval) {
            override fun onTick(millisUntilFinished: Long) {
                val timeLeftInt = millisUntilFinished / 1000
                timeLeft.text = getString(R.string.timeLeft, timeLeftInt)
            }

            override fun onFinish() {
                endGame()
            }
        }
        gameStarted = false
    }

    private fun startGame() {
        countDownTimer.start()
        gameStarted = true
    }

    private fun endGame() {

        Toast.makeText(applicationContext, getString(R.string.gameOverMessage, score),Toast.LENGTH_LONG).show()
        resetGame()
    }

}