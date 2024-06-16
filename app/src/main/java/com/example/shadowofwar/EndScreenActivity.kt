package com.example.shadowofwar

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class EndScreenActivity : AppCompatActivity(){


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_end_screen)



        val highScore = intent.getIntExtra("highScore", 0)
        val currentScore = intent.getIntExtra("currentScore", 0)

        val highScoreTextView = findViewById<TextView>(R.id.highScoreTextView)
        val currentScoreTextView = findViewById<TextView>(R.id.score)
        val restartButton = findViewById<Button>(R.id.restartBtn)

        highScoreTextView.text = "High Score: $highScore"
        currentScoreTextView.text = "Your Score: $currentScore"

        restartButton.setOnClickListener {
            // Restart the game by finishing this activity
            // (This assumes that MainActivity is the launcher activity)
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()

        }



    }


}