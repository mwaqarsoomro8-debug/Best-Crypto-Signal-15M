package com.bestcryptosignal.fifteenminute

import android.app.Activity
import android.os.Bundle
import android.graphics.Color
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.TextView

class MainActivity : Activity() {
    private val bg = Color.rgb(13, 18, 31)
    private val panel = Color.rgb(25, 34, 53)
    private val white = Color.rgb(242, 246, 255)
    private val muted = Color.rgb(170, 184, 205)
    private val green = Color.rgb(45, 205, 125)
    private val red = Color.rgb(255, 94, 105)
    private val yellow = Color.rgb(255, 202, 84)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showDashboard("WAIT")
    }

    private fun showDashboard(signal: String) {
        val root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(dp(22), dp(28), dp(22), dp(22))
            setBackgroundColor(bg)
        }

        root.addView(label("BEST CRYPTO SIGNAL", 14f, green, true))
        root.addView(label("15minute", 30f, white, true))
        root.addView(label("BUY / SELL / WAIT dashboard", 15f, muted, false))
        root.addView(space(22))

        val signalColor = when (signal) {
            "BUY" -> green
            "SELL" -> red
            else -> yellow
        }
        val card = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER
            setPadding(dp(18), dp(24), dp(18), dp(24))
            setBackgroundColor(panel)
        }
        card.addView(label("CURRENT DEMO SIGNAL", 13f, muted, true).apply {
            gravity = Gravity.CENTER
        })
        card.addView(label(signal, 36f, signalColor, true).apply {
            gravity = Gravity.CENTER
        })
        card.addView(label("Demo only — no live market feed", 13f, muted, false).apply {
            gravity = Gravity.CENTER
        })
        root.addView(card, LinearLayout.LayoutParams(-1, -2))
        root.addView(space(20))

        root.addView(label("TRADE PLAN", 18f, white, true))
        root.addView(label("Entry: set after checking the live chart", 15f, muted, false))
        root.addView(label("Take Profit (TP): define before entry", 15f, muted, false))
        root.addView(label("Stop Loss (SL): define before entry", 15f, muted, false))
        root.addView(label("Position size: calculate from your risk limit", 15f, muted, false))
        root.addView(space(18))

        root.addView(label("Choose a sample signal", 15f, white, true))
        val row = LinearLayout(this).apply {
            orientation = LinearLayout.HORIZONTAL
        }
        row.addView(button("BUY", green) { showDashboard("BUY") }, weightParams())
        row.addView(button("SELL", red) { showDashboard("SELL") }, weightParams())
        row.addView(button("WAIT", yellow) { showDashboard("WAIT") }, weightParams())
        root.addView(row)
        root.addView(space(18))
        root.addView(label(
            "Important: signals are illustrative buttons, not trading advice or live exchange signals. Confirm price, fees, liquidity and risk independently. Never risk money you cannot afford to lose.",
            13f, muted, false
        ))
        setContentView(root)
    }

    private fun label(text: String, size: Float, color: Int, bold: Boolean): TextView =
        TextView(this).apply {
            this.text = text
            textSize = size
            setTextColor(color)
            if (bold) setTypeface(null, android.graphics.Typeface.BOLD)
            setPadding(0, dp(5), 0, dp(5))
        }

    private fun button(text: String, color: Int, action: () -> Unit): TextView =
        TextView(this).apply {
            this.text = text
            textSize = 15f
            gravity = Gravity.CENTER
            setTextColor(Color.rgb(13, 18, 31))
            setTypeface(null, android.graphics.Typeface.BOLD)
            setPadding(dp(8), dp(13), dp(8), dp(13))
            setBackgroundColor(color)
            setOnClickListener { action() }
        }

    private fun space(height: Int) = android.view.View(this).apply {
        layoutParams = LinearLayout.LayoutParams(1, dp(height))
    }

    private fun weightParams() = LinearLayout.LayoutParams(0, -2, 1f).apply {
        marginEnd = dp(6)
    }

    private fun dp(value: Int): Int =
        (value * resources.displayMetrics.density).toInt()
}
