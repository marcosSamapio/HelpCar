package br.com.helpcar.util;

import android.widget.EditText;

public class CheckField {
    public static Boolean isEmpty(EditText editText) {
        if (editText.getText().length() == 0) {
            editText.setError("Campo obrigatório");
            return true;
        } else return false;
    }

    public static Boolean isEmpty(EditText editText, int value) {
        if (editText.getText().length() == 0) {
            editText.setError("Campo obrigatório");
            return true;
        } else if(editText.getText().length() < value) {
            editText.setError("Dados incompletos");
            return true;
        } else return false;
    }
}
