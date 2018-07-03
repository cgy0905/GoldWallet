package com.enternityfintech.goldcard.utils;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.enternityfintech.goldcard.R;
import com.enternityfintech.goldcard.widget.CustomDialog;

import me.drakeet.materialdialog.MaterialDialog;

/**
 * Created by cgy
 * 2018/7/3  9:24
 */
public class DialogHelper {

    private CustomDialog dialogWaiting;
    private MaterialDialog materialDialog;



    public Dialog showWaitingDialog(Context context, String tip) {
        hideWaitingDialog();
        View view = View.inflate(context, R.layout.dialog_waiting, null);
        if (TextUtils.isEmpty(tip))
            ((TextView)view.findViewById(R.id.tvTip)).setText(tip);
        dialogWaiting = new CustomDialog(context, view, R.style.MyDialog);
        dialogWaiting.show();
        dialogWaiting.setCancelable(false);
        return dialogWaiting;
    }

    /**
     * 隐藏等待提示框
     */
    public void hideWaitingDialog() {
        if (dialogWaiting != null){
            dialogWaiting.dismiss();
            dialogWaiting = null;
        }
    }

    /**
     * 显示MaterialDialog
     */
    public MaterialDialog showMaterialDialog(Context context, String title, String message, String positiveText, String negativeText, View.OnClickListener positiveButtonClickListener, View.OnClickListener negativeButtonClickListener) {
        hideMaterialDialog();
        materialDialog = new MaterialDialog(context);
        if (!TextUtils.isEmpty(title)) {
            materialDialog.setTitle(title);
        }
        if (!TextUtils.isEmpty(message)) {
            materialDialog.setMessage(message);
        }
        if (!TextUtils.isEmpty(positiveText)) {
            materialDialog.setPositiveButton(positiveText, positiveButtonClickListener);
        }
        if (!TextUtils.isEmpty(negativeText)) {
            materialDialog.setNegativeButton(negativeText, negativeButtonClickListener);
        }
        materialDialog.show();
        return materialDialog;
    }

    /**
     * 隐藏MaterialDialog
     */
    public void hideMaterialDialog(){
        if (materialDialog != null) {
            materialDialog.dismiss();
            materialDialog = null;
        }
    }
}
