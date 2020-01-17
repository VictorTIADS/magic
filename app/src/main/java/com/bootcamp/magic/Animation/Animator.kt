package com.bootcamp.magic.Animation

import android.view.View
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo

fun View.fadeOut() {
    this.visibility = View.GONE
    YoYo.with(Techniques.FadeOut).duration(500).playOn(this)
}

fun View.fadeIn() {
    this.visibility = View.VISIBLE
    YoYo.with(Techniques.FadeIn).duration(500).playOn(this)
}

fun View.slideOutDown() {
    if (this.visibility != View.GONE) {
        YoYo.with(Techniques.SlideOutDown).onEnd {
            this.visibility = View.GONE
        }.duration(500).playOn(this)

    }
}
fun View.slideInUp() {
    if (this.visibility != View.VISIBLE) {
        this.visibility = View.VISIBLE
        YoYo.with(Techniques.SlideInUp).duration(500).playOn(this)
    }
}