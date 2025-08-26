package com.example.clock

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import java.time.LocalTime
import kotlin.math.cos
import kotlin.math.sin

class AnalogClockView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val paint = Paint().apply {
        isAntiAlias = true
        style = Paint.Style.STROKE
    }

    private val rect = RectF()
    private var centerX = 0f
    private var centerY = 0f
    private var radius = 0f
    private var currentTime = LocalTime.now()

    fun setTime(time: LocalTime) {
        currentTime = time
        invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        centerX = width / 2f
        centerY = height / 2f
        radius = (minOf(width, height) / 2f) * 0.8f
        rect.set(centerX - radius, centerY - radius, centerX + radius, centerY + radius)

        // Draw clock face
        paint.color = Color.BLACK
        paint.strokeWidth = 4f
        canvas.drawCircle(centerX, centerY, radius, paint)

        // Draw hour marks
        paint.strokeWidth = 3f
        for (i in 1..12) {
            val angle = Math.PI / 6 * (i - 3)
            val startX = centerX + (radius * 0.9f * cos(angle)).toFloat()
            val startY = centerY + (radius * 0.9f * sin(angle)).toFloat()
            val stopX = centerX + (radius * 0.95f * cos(angle)).toFloat()
            val stopY = centerY + (radius * 0.95f * sin(angle)).toFloat()
            canvas.drawLine(startX, startY, stopX, stopY, paint)
        }

        // Draw hour hand
        paint.color = Color.BLUE
        paint.strokeWidth = 15f
        val hourAngle = Math.PI / 6 * (currentTime.hour % 12 + currentTime.minute / 60.0) - Math.PI / 2
        val hourLength = radius * 0.5f
        canvas.drawLine(
            centerX,
            centerY,
            centerX + (hourLength * cos(hourAngle)).toFloat(),
            centerY + (hourLength * sin(hourAngle)).toFloat(),
            paint
        )

        // Draw minute hand
        paint.color = Color.rgb(142, 69, 133)
        paint.strokeWidth = 15f
        val minuteAngle = Math.PI / 30 * currentTime.minute - Math.PI / 2
        val minuteLength = radius * 0.7f
        canvas.drawLine(
            centerX,
            centerY,
            centerX + (minuteLength * cos(minuteAngle)).toFloat(),
            centerY + (minuteLength * sin(minuteAngle)).toFloat(),
            paint
        )



        // Draw center circle
        paint.color = Color.rgb(185, 118, 227)
        paint.style = Paint.Style.STROKE
        canvas.drawCircle(centerX, centerY, 15f, paint)

        // Restore stroke style
        paint.style = Paint.Style.STROKE
    }
}
