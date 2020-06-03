package br.com.helpcar.util;

import android.widget.EditText;

import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

public class MaskFormat {
    public static void cpf(EditText editText){
        SimpleMaskFormatter smf = new SimpleMaskFormatter("NNN.NNN.NNN-NN");
        MaskTextWatcher mtw = new MaskTextWatcher(editText, smf);
        editText.addTextChangedListener(mtw);
    }
}
