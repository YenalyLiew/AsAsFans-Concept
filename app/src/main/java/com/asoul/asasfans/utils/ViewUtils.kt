package com.asoul.asasfans.utils

import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.MarginLayoutParams
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import com.asoul.asasfans.AsApplication
import com.google.android.material.card.MaterialCardView

/**
 * 动态设置View的Margin。
 *
 * @param start 开始px
 * @param top 上px
 * @param end 结束px
 * @param bottom 下px
 */
fun View.setMargin(start: Int, top: Int, end: Int, bottom: Int) {
    if (this.layoutParams is MarginLayoutParams) {
        val p = this.layoutParams as MarginLayoutParams
        p.setMargins(0, top, 0, bottom)
        p.marginStart = start
        p.marginEnd = end
        this.requestLayout()
    }
}

/**
 * dp to px
 *
 * @param dpValue dp value
 * @return px值
 */
fun dip2px(dpValue: Float): Int {
    val scale = AsApplication.context.resources.displayMetrics.density
    return (dpValue * scale + 0.5F).toInt()
}

val Number.dp: Int
    get() {
        return dip2px(this.toFloat())
    }

/**
 * 动态添加`ToolsFragment`里的工具列表。
 *
 * @param linearViewGroup 根LinearLayout，用于存放Item.
 * @param position 添加View的位置，默认为-1，即从上到下依次排列。
 * @param icon 图标，需要Drawable.
 * @param text 文字，需要引用`string.xml`的文字。
 * @param moreAction 引用了所用的所有View，如需要更多Action可以使用该函数。
 *
 * @author Yenaly Liew
 */
fun Fragment.addToolsItem(
    linearViewGroup: LinearLayout,
    position: Int = -1,
    @DrawableRes icon: Int,
    @StringRes text: Int,
    moreAction: ((
        cardView: MaterialCardView,
        linearLayout: LinearLayout,
        imageView: ImageView,
        textView: TextView
    ) -> Unit)? = null
) {
    val materialCardView = MaterialCardView(requireContext())
    materialCardView.isHapticFeedbackEnabled = true
    materialCardView.layoutParams = LinearLayout.LayoutParams(
        ViewGroup.LayoutParams.MATCH_PARENT,
        ViewGroup.LayoutParams.WRAP_CONTENT
    ).apply {
        setMargins(0, 0, 0, 8.dp)
    }

    val linearLayout = LinearLayout(requireContext())
    linearLayout.layoutParams = ViewGroup.LayoutParams(
        ViewGroup.LayoutParams.MATCH_PARENT,
        ViewGroup.LayoutParams.WRAP_CONTENT
    )
    linearLayout.orientation = LinearLayout.HORIZONTAL
    linearLayout.gravity = Gravity.CENTER_VERTICAL

    val imageView = ImageView(requireContext())
    imageView.setImageDrawable(ResourcesCompat.getDrawable(resources, icon, null))
    imageView.layoutParams = LinearLayout.LayoutParams(40.dp, 40.dp).apply {
        setMargins(0, 16.dp, 0, 16.dp)
        marginStart = 16.dp
        marginEnd = 16.dp
    }

    val textView = TextView(requireContext())
    textView.setText(text)
    textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16F)
    textView.layoutParams = LinearLayout.LayoutParams(
        ViewGroup.LayoutParams.WRAP_CONTENT,
        ViewGroup.LayoutParams.WRAP_CONTENT
    )

    linearLayout.addView(imageView, 0)
    linearLayout.addView(textView, 1)
    materialCardView.addView(linearLayout)
    linearViewGroup.addView(materialCardView, position)

    moreAction?.invoke(materialCardView, linearLayout, imageView, textView)
}