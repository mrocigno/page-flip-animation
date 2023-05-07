package br.com.mrocigno.kotlinscript

import android.graphics.Camera
import android.view.animation.Animation
import android.view.animation.Transformation

/**
 * An animation that rotates the view on the Y axis between two specified angles.
 * This animation also adds a translation on the Z axis (depth) to improve the effect.
 */
class Rotate3dAnimation(
    private val mFromDegrees: Float,
    private val mToDegrees: Float,
    private val mCenterX: Float,
    private val mCenterY: Float,
    private val mDepthZ: Float,
    private val mReverse: Boolean
) : Animation() {
    private var mCamera: Camera? = null
    override fun initialize(width: Int, height: Int, parentWidth: Int, parentHeight: Int) {
        super.initialize(width, height, parentWidth, parentHeight)
        mCamera = Camera()
    }

    override fun applyTransformation(interpolatedTime: Float, t: Transformation) {
        val fromDegrees = 0f
        val degrees = fromDegrees + (180f - fromDegrees) * /*interpolatedTime*/ .3f
        val centerX = mCenterX
        val centerY = mCenterY
        val camera = Camera()
        val matrix = t.matrix
        camera.save()
//        if (mReverse) {
        camera.translate(0.0f, 0.0f, .5f * .3f)
//        } else {
//            camera.translate(0.0f, 0.0f, mDepthZ * (1.0f - interpolatedTime))
//        }
        camera.rotateY(degrees)
        camera.getMatrix(matrix)
        camera.restore()
        matrix.preTranslate(-centerX, -centerY)
        matrix.postTranslate(centerX, centerY)
//        val fromDegrees = mFromDegrees
//        val degrees = fromDegrees + (mToDegrees - fromDegrees) * interpolatedTime
//        val centerX = mCenterX
//        val centerY = mCenterY
//        val camera = mCamera
//        val matrix = t.matrix
//        camera!!.save()
//        if (mReverse) {
//            camera.translate(0.0f, 0.0f, mDepthZ * interpolatedTime)
//        } else {
//            camera.translate(0.0f, 0.0f, mDepthZ * (1.0f - interpolatedTime))
//        }
//        camera.rotateY(degrees)
//        camera.getMatrix(matrix)
//        camera.restore()
//        matrix.preTranslate(-centerX, -centerY)
//        matrix.postTranslate(centerX, centerY)
    }
}