package com.example.shadowofwar

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.MotionEvent
import android.view.View

class GameView(var c: Context, var gameTask: GameTask) : View(c) {
    private var myPaint: Paint? = null
    private var speed = 1
    private var time = 0
    private var score = 0
    private var myTankPosition = 0
    private val bombs = ArrayList<HashMap<String, Any>>()
    private  var highScore = 0

    var viewWith = 0
    var viewHeight = 0

    init {
        myPaint = Paint()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        viewWith = this.measuredWidth
        viewHeight = this.measuredHeight

        if (time % 700 < 10 + speed) {
            val map = HashMap<String, Any>()
            map["lane"] = (0..2).random()
            map["startTime"] = time
            bombs.add(map)
        }

        time = time + 10 + 10 + speed
        val tankWith = viewWith / 5
        val tankHeight = tankWith + 10
        myPaint!!.style = Paint.Style.FILL
        val d = resources.getDrawable(R.drawable.sold, null)

        d.setBounds(
            myTankPosition * viewWith / 3 + viewWith / 15 + 25,
            viewHeight - 2 - tankHeight,
            myTankPosition * viewWith / 3 + viewWith / 15 + tankWith - 25,
            viewHeight - 2
        )
        d.draw(canvas!!)
        myPaint!!.color = Color.GREEN


        for (i in bombs.indices) {
            try {
                val bunnyX = bombs[i]["lane"] as Int * viewWith / 3 + viewWith / 15
                var bunnyY = time - bombs[i]["startTime"] as Int
                val d2 = resources.getDrawable(R.drawable.bomb, null)

                d2.setBounds(
                    bunnyX + 25, bunnyY - tankHeight, bunnyX + tankWith - 25, bunnyY
                )
                d2.draw(canvas)

                if (bombs[i]["lane"] as Int == myTankPosition) {
                    if (bunnyY > viewHeight - 2 - tankHeight && bunnyY < viewHeight - 2) {
                        gameTask.closeGame(score)
                    }
                }
                if (bunnyY > viewHeight + tankHeight) {
                    bombs.removeAt(i)
                    score++
                    speed = 1 + Math.abs(score / 8)

                    if (score > highScore) {
                        highScore = score

                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        myPaint!!.color = Color.WHITE
        myPaint!!.textSize = 40f
        canvas.drawText("Score : $score", 80f, 80f, myPaint!!)
        canvas.drawText("Speed : $speed", 380f, 80f, myPaint!!)
        canvas.drawText("High Score : $highScore", 680f, 80f, myPaint!!)

        invalidate()
    }



    fun resetGame() {
        score = 0
        speed = 1
        time = 0
        myTankPosition = 0
        bombs.clear()
        invalidate() // Redraw the view to reflect the reset state
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event!!.action) {
            MotionEvent.ACTION_DOWN -> {
                val x1 = event.x
                if (x1 < viewWith / 2) {
                    if (myTankPosition > 0) {
                        myTankPosition--
                    }
                }
                if (x1 > viewWith / 2) {
                    if (myTankPosition < 2) {
                        myTankPosition++
                    }
                }
                invalidate()
            }

            MotionEvent.ACTION_UP -> {
            }
        }

        return true
    }
}
