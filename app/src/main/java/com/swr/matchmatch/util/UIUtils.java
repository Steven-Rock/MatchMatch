package com.swr.matchmatch.util;

import android.support.design.widget.Snackbar;
import android.view.View;

import com.swr.matchmatch.R;

/**
 * Created by Steve on 10/7/2017.
 */

public class UIUtils {

    public static void showSnackBar(String msg, View view){

        if(msg == null ) return;

        Snackbar snackbar = Snackbar
                .make(view, msg, Snackbar.LENGTH_LONG);

        snackbar.getView().setBackgroundColor(view.getResources().getColor(R.color.colorAccent));
        snackbar.show();

    }
}
