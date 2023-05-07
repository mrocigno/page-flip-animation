package br.com.mrocigno.kotlinscript

import android.graphics.Camera
import android.graphics.Canvas
import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.graphics.values
import androidx.core.view.WindowCompat
import com.google.android.material.slider.Slider
import java.io.PrintWriter

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        val top = findViewById<TurnPageTest>(R.id.topper)
        findViewById<Slider>(R.id.slider).apply {
            this.valueFrom = 0f
            this.valueTo = 1f
            this.value = 1f
            this.addOnChangeListener { slider, value, fromUser ->
                top.fraction = 1f - value
            }
        }

        findViewById<View>(R.id.hello).apply {

            teste()
            setOnClickListener {
//                val centerX = width/2f
//                val centerY = height/2f
//                startAnimation(Rotate3dAnimation(
//                    0f, 180f, centerX, centerY, 1f, false
//                ).apply {
//                    duration = 1000
//                    this.fillAfter = true
//                })
            }
        }
    }

    fun teste() {
        val aa = findViewById<View>(R.id.hello)

        val interpolatedTime = .3f



        val fromDegrees = 0f
        val degrees = fromDegrees + (180f - fromDegrees) * interpolatedTime
        val centerX = aa.width/2f
        val centerY =  aa.height/2f
        val camera = Camera()
        val matrix = aa.matrix
        camera.save()
        camera.rotateY(degrees)
        camera.getMatrix(matrix)
        camera.restore()
        matrix.preTranslate(-centerX, -centerY)
        matrix.postTranslate(centerX, centerY)
        aa.animationMatrix = matrix
    }
}