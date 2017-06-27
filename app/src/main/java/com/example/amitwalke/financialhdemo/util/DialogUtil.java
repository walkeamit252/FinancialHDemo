
package com.example.amitwalke.financialhdemo.util;


import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import com.example.amitwalke.financialhdemo.R;


/**
 * Created by Amit Walke on 27-06-2017.
 */
public class DialogUtil {

    private static final String TAG = "DialogUtil";


    public static void showNoNetworkAlert(Context ctx) {
        try {
            new android.app.AlertDialog.Builder(ctx).setTitle(R.string.app_name).setMessage("No Internet")
                    .setPositiveButton("OK", null).create().show();
        } catch (WindowManager.BadTokenException e) {
            e.printStackTrace();
        }
    }


}
