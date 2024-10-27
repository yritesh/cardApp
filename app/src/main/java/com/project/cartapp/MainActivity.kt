package com.project.cartapp

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TableLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge

var activePlayer = 1
var player1 = ArrayList<Int>()
var player2 = ArrayList<Int>()

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val playerDisplay = findViewById<TextView>(R.id.player_display)
        Handler(Looper.getMainLooper()).postDelayed({
            playerDisplay.text = getString(R.string.player_1_move)
        }, 1000)
        enableEdgeToEdge()
    }

    fun buttonClick(view: View) {
        val currView: Button = view as Button
        val buttonSelected: Button = view
        var cell = 0
        when (buttonSelected.id) {
            R.id.bt1 -> cell = 1
            R.id.bt2 -> cell = 2
            R.id.bt3 -> cell = 3
            R.id.bt4 -> cell = 4
            R.id.bt5 -> cell = 5
            R.id.bt6 -> cell = 6
            R.id.bt7 -> cell = 7
            R.id.bt8 -> cell = 8
            R.id.bt9 -> cell = 9
        }
        stopTouch(listOf(view))
        mark(currView, activePlayer)
        move(cell, activePlayer, this)
    }

    fun resetGame(viewReset: View) {
        val matchArea: ViewGroup = findViewById(R.id.match_area)
        val playerDisplay = findViewById<TextView>(R.id.player_display)
        Handler(Looper.getMainLooper()).postDelayed({
            playerDisplay.text = getString(R.string.player_1_move) // Set the new text
        }, 1000)
        reset(matchArea, this)
        viewReset.visibility = View.INVISIBLE
        player1.clear()
        player2.clear()
    }
}

fun displayFireworks(activity: Activity) {
    val fireworksView: ImageView = activity.findViewById(R.id.fireworks_view)
    fireworksView.visibility = View.VISIBLE

    Handler(Looper.getMainLooper()).postDelayed({
        fireworksView.visibility = View.GONE
    }, 2000)
}

fun move(cell: Int, activePlayerCurr: Int, context: Context) {
    val player: ArrayList<Int> = if (activePlayerCurr > 1) {
        player2
    } else {
        player1
    }
    player.add(cell)
    checkWinner(player, context)
}

fun checkWinner(player: ArrayList<Int>, context: Context) {
    if (
        player.containsAll(listOf(1, 2, 3))
        || player.containsAll(listOf(1, 5, 9))
        || player.containsAll(listOf(1, 4, 7))
        || player.containsAll(listOf(2, 5, 8))
        || player.containsAll(listOf(3, 6, 9))
        || player.containsAll(listOf(3, 5, 7))
        || player.containsAll(listOf(4, 5, 6))
    ) {
        val activity = context as? Activity ?: return
        val matchArea = activity.findViewById<TableLayout>(R.id.match_area)
        val textDisplay = activity.findViewById<TextView>(R.id.player_display)
        stopTouchAll(matchArea, context)
        textDisplay.text = activity.getString(R.string.over, activePlayer.toString())
        displayFireworks(activity)
        Toast.makeText(context, "Winner is Player $activePlayer", Toast.LENGTH_SHORT).show()
    } else {
        val activity = context as? Activity ?: return
        val playerDisplay = activity.findViewById<TextView>(R.id.player_display)
        if (activePlayer > 1) {
            activePlayer = 1
            playerDisplay.text = activity.getString(R.string.player_1_move)
        } else {
            activePlayer = 2
            playerDisplay.text = activity.getString(R.string.player_2_move)
        }
    }
}

fun mark(button: Button, activePlayerCurr: Int) {
    val mark = if (activePlayerCurr > 1) {
        "o"
    } else {
        "x"
    }
    button.text = mark
}

fun stopTouch(buttons: List<Button>) {
    for (buttonCurr in buttons) {
        buttonCurr.isEnabled = false
    }
}

fun stopTouchAll(layout: ViewGroup, context: Context) {
    for (i in 0 until layout.childCount) {
        when (val view = layout.getChildAt(i)) {
            is Button -> view.isEnabled = false
            is ViewGroup -> stopTouchAll(view, context)
        }
    }
    val activity = context as? Activity ?: return
    val resetButton: Button = activity.findViewById(R.id.play_again_button)
    resetButton.visibility = View.VISIBLE
}

fun reset(layout: ViewGroup, context: Context) {
    for (i in 0 until layout.childCount) {
        when (val view = layout.getChildAt(i)) {
            is Button -> {
                view.isEnabled = true
                view.text = context.getString(R.string.blank)
            }

            is ViewGroup -> reset(view, context)
        }
    }
}
