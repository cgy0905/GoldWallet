package com.enternityfintech.goldcard.base

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import com.enternityfintech.goldcard.R
import com.enternityfintech.goldcard.utils.ScreenUtils


/**
 * Created by cool on 2018/2/28.
 */

abstract class BaseStatusBarActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window = window
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = Color.TRANSPARENT
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        setFitsSystemWindows(!isImmersion())
    }

    private fun setFitsSystemWindows(fitSystemWindows: Boolean) {
        val contentFrameLayout = findViewById<ViewGroup>(android.R.id.content);
        val parentView = contentFrameLayout.getChildAt(0)
        if (parentView != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            parentView.fitsSystemWindows = fitSystemWindows
        }
        if (fitSystemWindows) {
            setStatusViewColor(getStatusBarColor())
        }
    }

    /**
     * Prevent overwriting elsewhere.
     */
    final override fun isImmersive(): Boolean {
        return super.isImmersive()
    }

    open fun isImmersion(): Boolean {
        return true
    }

    open fun getStatusBarColor(): Int {
        return ContextCompat.getColor(this, R.color.main_color)
    }

    private fun setStatusViewColor(color: Int) {
        val contentView = findViewById<ViewGroup>(android.R.id.content)
        val statusBarView = View(this)
        val lp = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ScreenUtils.getStatusBarHeight(this))
        statusBarView.setBackgroundColor(color)
        contentView.addView(statusBarView, lp)
    }

}
