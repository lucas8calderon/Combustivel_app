package com.lucascalderon1.combustivel.cards;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class MascaraValorReal implements TextWatcher {

    private EditText editText;

    public MascaraValorReal(EditText editText) {
        this.editText = editText;
    }

    private boolean isUpdating = false;
    private DecimalFormat df = new DecimalFormat("###,##0.00");
    private DecimalFormat dfnd = new DecimalFormat("###,###");

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (isUpdating) {
            isUpdating = false;
            return;
        }

        String str = s.toString().replaceAll("[^0-9]", "");
        try {
            isUpdating = true;
            double value = Double.parseDouble(str) / 100;
            String formatted = "R$ " + df.format(value);
            editText.setText(formatted);
            editText.setSelection(formatted.length());
        } catch (NumberFormatException e) {
            // do nothing, just keep the previous value
        }
    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void afterTextChanged(Editable s) {
    }

    public static String unmask(String s) {
        return s.replaceAll("[^0-9\\.]", "");
    }


    public static double parseDouble(String s) throws NumberFormatException {
        return Double.parseDouble(unmask(s)) / 100;
    }

}
