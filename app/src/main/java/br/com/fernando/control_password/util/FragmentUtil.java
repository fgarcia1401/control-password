package br.com.fernando.control_password.util;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

public class FragmentUtil {

    public static void addFragment(FragmentManager fragmentManager, int container, Fragment newFragment) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(container, newFragment);
        fragmentTransaction.commitAllowingStateLoss();
    }

    public static void closeKeyboard(Activity activity) {

        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);

        if(activity != null) {
            View f = activity.getCurrentFocus();
            if (null != f && null != f.getWindowToken() && EditText.class.isAssignableFrom(f.getClass())) {
                inputMethodManager.hideSoftInputFromWindow(f.getWindowToken(), 0);
            } else {
                activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
            }
        }
        
    }

    public static void showKeyboard(final Activity activity) {
        final InputMethodManager inputMethodManager = (InputMethodManager) activity
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
    }

    public static void clearBackStack(FragmentManager fragmentManager) {

        int backStackEntry = fragmentManager.getBackStackEntryCount();
        if (backStackEntry > 0) {
            for (int i = 0; i < backStackEntry; i++) {
                fragmentManager.popBackStackImmediate();
            }
        }
    }
}