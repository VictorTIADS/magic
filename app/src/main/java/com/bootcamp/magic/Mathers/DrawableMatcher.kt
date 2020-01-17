package com.bootcamp.magic.Mathers

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import androidx.core.content.ContextCompat
import org.hamcrest.Description
import org.hamcrest.TypeSafeMatcher

fun withDrawable(resourceId: Int) = DrawableMatcher(resourceId)
fun noDrawable(resourceId: Int) = DrawableMatcher(-1)
class DrawableMatcher(private val resourceId: Int) : TypeSafeMatcher<View>() {
    private var resourceName: String? = null
    override fun describeTo(description: Description?) {
        description?.let {
            description.appendText("with drawable from resource id: ")
            description.appendValue(resourceId)
            resourceName?.let { name ->
                description.appendText("[")
                description.appendText(name)
                description.appendText("]")
            }
        }
    }
    override fun matchesSafely(item: View?): Boolean {
        if (item !is ImageView) {
            return false
        }
        if (resourceId < 0) {
            return item.drawable == null
        }
        val resources = item.getContext().resources
        val expectedDrawable = ContextCompat.getDrawable(item.context, resourceId) ?: return false
        resourceName = resources.getResourceName(resourceId)
        val bitmap = getBitmap(item.drawable)
        val otherBitmap = getBitmap(expectedDrawable)
        return bitmap.sameAs(otherBitmap)
    }
    private fun getBitmap(drawable: Drawable): Bitmap {
        val bitmap = Bitmap.createBitmap(
            drawable.intrinsicWidth,
            drawable.intrinsicHeight,
            Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, canvas.width, canvas.height)
        drawable.draw(canvas)
        return bitmap
    }
}