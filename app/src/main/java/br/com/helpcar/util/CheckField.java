package br.com.helpcar.util;

import android.widget.EditText;

public class CheckField {
    public static Boolean isEmpty(EditText editText) {
        if (editText.getText().length() == 0) {
            editText.setError("Campo obrigatório");
            return true;
        } else return false;
    }
}
