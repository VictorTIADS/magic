package com.bootcamp.magic.Mathers

import android.content.res.Resources
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher

class RecyclerViewMatcher(private val recyclerViewId: Int) {
    fun atPosition(position: Int): Matcher<View> {
        return atPositionOnView(position, -1)
    }
    fun atPositionOnView(position: Int, targetViewId: Int): Matcher<View> {
        return object : TypeSafeMatcher<View>() {
            private var resources: Resources? = null
            private var childView: View? = null

            override fun matchesSafely(view: View): Boolean {
                if (childView == null) {
                    val recyclerView = view.rootView.findViewById<RecyclerView>(recyclerViewId)
                    childView = recyclerView.findViewHolderForAdapterPosition(position)?.itemView
                }
                return if (targetViewId == -1) {
                    view === childView
                } else {
                    val targetView = childView?.findViewById(targetViewId) as View?
                    view === targetView
                }
            }

            override fun describeTo(description: Description?) {
                var idDescription = Integer.toString(recyclerViewId)
                resources?.let {
                    idDescription = try {
                        it.getResourceName(recyclerViewId)
                    } catch (var4: Resources.NotFoundException) {
                        String.format("%s (resource name not found)", Integer.valueOf(recyclerViewId))
                    }
                }
                description?.appendText("with id: $idDescription")
            }
        }
    }
    companion object {
        fun withRecyclerView(recyclerViewId: Int) =
            RecyclerViewMatcher(recyclerViewId)
    }
}