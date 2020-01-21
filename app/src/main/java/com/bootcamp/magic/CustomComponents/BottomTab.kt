package com.bootcamp.magic.CustomComponents

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.content.withStyledAttributes
import com.bootcamp.magic.R
import kotlinx.android.synthetic.main.tab_bottom.view.*

class BottomTab(
    context: Context,
    attrs: AttributeSet
): ConstraintLayout(context, attrs) {

    init {
        View.inflate(context, R.layout.tab_bottom, this)
        context.withStyledAttributes(
            attrs,
            R.styleable.BottomTab,
            0,
            0
        ) {
            home_button.text = getString(R.styleable.BottomTab_firstButtonName)
            favorite_button.text = getString(R.styleable.BottomTab_secondButtonName)

            top_line.setBackgroundColor(ContextCompat.getColor(context, getResourceId(
                R.styleable.BottomTab_colorTopLine, android.R.color.white)))
            middle_line.setBackgroundColor(ContextCompat.getColor(context, getResourceId(
                R.styleable.BottomTab_colorMiddleLine, android.R.color.white)))
        }
    }

    fun setOnHomeClick(func: () -> Unit){
        home_button.setOnClickListener {
            func()
        }
    }

    fun setOnFavoriteClick(func: () -> Unit){
        favorite_button.setOnClickListener {
            func()
        }
    }
}