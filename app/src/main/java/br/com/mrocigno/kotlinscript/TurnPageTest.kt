package br.com.mrocigno.kotlinscript

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.PointF
import android.util.AttributeSet
import android.util.Log
import android.widget.FrameLayout
import java.lang.Math.min
import java.lang.Math.pow
import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.pow
import kotlin.math.sin
import kotlin.math.sqrt


class TurnPageTest @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val test = BitmapFactory.decodeStream(context.assets.open("test.png"))
    var fraction = 0f
        set(value) {
            field = value
            invalidate()
        }
    private val linesPaint = Paint().apply { color = Color.RED; style = Paint.Style.STROKE; strokeWidth = 2f }

    init {
        setWillNotDraw(false)
//        setOnClickListener {
//            ValueAnimator.ofFloat(0f, 1f).apply {
//                duration = 1000L
//                addUpdateListener {
//                    fraction = it.animatedFraction
//                    invalidate()
//                }
//            }.start()
//        }
    }

    var mWidth = 0f
    var mHeight = 0f
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        mWidth = MeasureSpec.getSize(widthMeasureSpec).toFloat()
        mHeight = MeasureSpec.getSize(heightMeasureSpec).toFloat()
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        if (mWidth <= 0 || canvas == null) return


        val rows = 1
        val columns = 150
        val vertices = computeVerticesFromEllipse(columns, rows, .2f)

        canvas.drawBitmapMesh(test, columns, rows, vertices, 0, null, 0, null)
    }

    private fun computeVerticesFromEllipse(columns: Int, rows: Int, curveFactor: Float): FloatArray {
        val center = mWidth / 2.5
        val curve = (curveFactor * fraction) * center

        val vertices = FloatArray(((columns + 1) * 2) * (rows + 1))

        val increment = mWidth / columns
        var index = 0

        repeat(columns + 1) {
            val x = increment * it
            val y = -sqrt((1 - ((increment * it) - center).pow(2.0) / center.pow(2.0)) * curve.pow(2.0)).nanToZero()

            vertices[index++] = x.toFloat()
            vertices[index++] = y.toFloat()
        }


        repeat(columns + 1) {
            val x = increment * it
            val y = sqrt((1 - ((increment * it) - center).pow(2.0) / center.pow(2.0)) * curve.pow(2.0)).nanToZero() + height

            vertices[index++] = x.toFloat()
            vertices[index++] = y.toFloat()
        }

        return vertices
    }

//    private fun computeVerticesFromEllipse(columns: Int, rows: Int, curveFactor: Float): FloatArray {
//        val width = mWidth * (1f - (curveFactor * fraction))
//
//        val curveColumns = (columns * .7f).toInt()
//        val rest = columns - curveColumns
//
//        val curveSize = (width / columns) * curveColumns
//
//        val center = curveSize / 2.0
//        val curve = (curveFactor * fraction) * center
//
//        val vertices = FloatArray(((columns + 1) * 2) * (rows + 1))
//
//        val increment = width / columns
//        var index = 0
//
//        repeat(curveColumns + 1) {
//            val x = increment * it
//            val y = -sqrt((1 - ((increment * it) - center).pow(2.0) / center.pow(2.0)) * curve.pow(2.0))
//
//            vertices[index++] = x.toFloat()
//            vertices[index++] = y.toFloat()
//        }
//
//        repeat(rest) {
//            val start = curveColumns + it + 1
//
//            val x = increment * start
//            val y = 0
//
//            vertices[index++] = x.toFloat()
//            vertices[index++] = y.toFloat()
//        }
//
//
//        repeat(curveColumns + 1) {
//            val x = increment * it
//            val y = sqrt((1 - ((increment * it) - center).pow(2.0) / center.pow(2.0)) * curve.pow(2.0)) + height
//
//            vertices[index++] = x.toFloat()
//            vertices[index++] = y.toFloat()
//        }
//
//        repeat(rest) {
//            val start = curveColumns + it + 1
//
//            val x = increment * start
//            val y = mHeight
//
//            vertices[index++] = x.toFloat()
//            vertices[index++] = y.toFloat()
//        }
//
//        return vertices
//    }
    private fun Double.nanToZero() =
        this.takeIf { !it.isNaN() } ?: 0.0
}
