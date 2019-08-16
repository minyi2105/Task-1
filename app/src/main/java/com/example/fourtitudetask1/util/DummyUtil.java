package com.example.fourtitudetask1.util;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Map;

public class DummyUtil {
    //valid edit text or text view (check if it's empty then display error message)
    public static boolean isInputEmpty(View view, Map<View, String> map) {
        boolean isEmpty = false;

        for (Map.Entry<View, String> entry : map.entrySet()) {
            View inputView = entry.getKey();

            String errorMessage = entry.getValue();

            String input = getOnlyText(inputView);

            if (TextUtils.isEmpty(input)) {
                if (inputView instanceof EditText) {
                    EditText editText = (EditText) inputView;
                    editText.setError(errorMessage);
                } else if (inputView instanceof TextView){
                    TextView textView = (TextView) inputView;
                    textView.setError(errorMessage);
                }

                isEmpty = true;
            } else{
                // manually removing the setError is required because the some of the views are not using as the way it normally be used
                if (inputView instanceof EditText) {
                    EditText editText = (EditText) inputView;
                    editText.setError(null);
                }
                else if (inputView instanceof TextView){
                    TextView textView = (TextView) inputView;
                    textView.setError(errorMessage);
                }
            }
        }
        return isEmpty;
    }

    //get only text from Edit Text or text view by removing any space
    public static String getOnlyText(View view) {
        String text = "";

        if (view instanceof EditText) {
            EditText editText = (EditText) view;
            text = editText.getText().toString().trim();
        } else if (view instanceof TextView){
            TextView textView = (TextView) view;
            text = textView.getText().toString().trim();
        }

        return text;
    }
}
