package com.example.fourtitudetask1.util;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

//Source code from https://stackoverflow.com/a/35868038, as inputType="textMultiLine" is not working
public class EditTextLinesLimiter implements TextWatcher {
    private EditText editText;
    private int maxLines;
    private String lastValue = "";

    public EditTextLinesLimiter(EditText editText, int maxLines) {
        this.editText = editText;
        this.maxLines = maxLines;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        lastValue = charSequence.toString();
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        if (editText.getLineCount() > maxLines) {
            int selectionStart = editText.getSelectionStart() - 1;
            editText.setText(lastValue);
            if (selectionStart >= editText.length()) {
                selectionStart = editText.length();
            }
            editText.setSelection(selectionStart);
        }
    }
}
