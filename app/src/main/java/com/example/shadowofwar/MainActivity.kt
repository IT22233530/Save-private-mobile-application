package com.example.shadowofwar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView


class MainActivity : AppCompatActivity(),GameTask{
    lateinit var rootLayout:LinearLayout
    lateinit var  startBtn :Button
    lateinit var mGameView: GameView
    lateinit var score: TextView
    lateinit var img: ImageView
    private lateinit var highScoreTextView: TextView // Add this

    private var highScore = 0 // Track high score

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startBtn = findViewById(R.id.startBtn)
        rootLayout = findViewById(R.id.rootLayout)
        score = findViewById(R.id.score)
        img = findViewById(R.id.imageView4)
        mGameView = GameView(this,this)
        highScoreTextView = findViewById(R.id.highScore) // Initialize high score TextView


        startBtn.setOnClickListener{
            mGameView.setBackgroundResource(R.drawable.back2)
            mGameView.resetGame()
            rootLayout.addView(mGameView)
            startBtn.visibility = View.GONE
            score.visibility =View.GONE
            img.visibility = View.GONE
           highScoreTextView.visibility = View.GONE

        }
    }
    override fun closeGame(mScore: Int){
        score.text = "Score : $mScore"
        if (mScore > highScore) {
            highScore = mScore
            highScoreTextView.text = "High Score: $highScore"
        }
//        val intent = Intent(this, EndScreenActivity::class.java)
//        intent.putExtra("highScore", highScore)
//        intent.putExtra("currentScore", mScore)
//        startActivity(intent)

        rootLayout.removeView(mGameView)
        startBtn.visibility = View.VISIBLE
        score.visibility = View.VISIBLE
        highScoreTextView.visibility = View.VISIBLE
//        endScreen()
    }
//   private fun endScreen() {
//       val intent = Intent(this, EndScreenActivity::class.java)
//        startActivity(intent)
//    }

}