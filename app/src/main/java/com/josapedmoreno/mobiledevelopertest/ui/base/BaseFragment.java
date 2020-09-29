package com.josapedmoreno.mobiledevelopertest.ui.base;

import android.app.Activity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.zhuinden.simplestackextensions.fragments.KeyedFragment;

public abstract class BaseFragment extends KeyedFragment {

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        View view = activity.getCurrentFocus();
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public void snackbar(String message, String extras) {
        Snackbar snackbar = Snackbar.make(requireActivity().getWindow().getDecorView().getRootView(), "", Snackbar.LENGTH_LONG);
        View snackbarView = snackbar.getView();
        TextView tv = snackbarView.findViewById(com.google.android.material.R.id.snackbar_text);

        switch (message) {
            case "reset-success":
                //snackbar.setText(getString(R.string.reset_success))
                //tv.setTextColor(Color.WHITE)
                //snackbarView.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.snackbarSuccess))
                break;
            default:
                break;
        }
        snackbar.show();
    }
}
