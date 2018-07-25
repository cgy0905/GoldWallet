package com.enternityfintech.goldcard.business.capture.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.enternityfintech.goldcard.R
import com.enternityfintech.goldcard.app.base.mvp.ILoadViewImpl
import com.enternityfintech.goldcard.base.BaseStatusBarActivity
import com.enternityfintech.goldcard.business.capture.presenter.CapturePresenter
import kotlinx.android.synthetic.main.activity_capture.*

/**
 *Created by cool on 2018/7/2
 */
class CaptureActivity : BaseStatusBarActivity(), ILoadViewImpl {

    val presenter = CapturePresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_capture)

        captureView.onSucceed = {bitmap, result ->
            presenter.decode(result)
        }

        captureView.onError = {reason, finishSelf ->
            showToast(reason)
            if (!finishSelf) {
                finish()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        captureView.onResume()
    }

    override fun onPause() {
        super.onPause()
        captureView.onPause()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        captureView.onActivityResult(requestCode, resultCode, data)
    }

    override fun withContext(): Context {
        return this
    }
}