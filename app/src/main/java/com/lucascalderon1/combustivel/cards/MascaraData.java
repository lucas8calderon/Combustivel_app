package com.lucascalderon1.combustivel.cards;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MascaraData implements TextWatcher {

    private final Calendar calendar = Calendar.getInstance();
    private boolean isUpdating = false;
    private EditText editText;

    public MascaraData(EditText editText) {
        this.editText = editText;
    }

    public static String unmask(String data) {
        return data.replaceAll("[^0-9]", "");
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (isUpdating) {
            isUpdating = false;
            return;
        }

        String str = s.toString().replaceAll("[^0-9]", "");
        if (str.length() > 8) {
            str = str.substring(0, 8);
        }

        try {
            isUpdating = true;
            String day = str.substring(0, 2);
            String month = str.substring(2, 4);
            String year = str.substring(4, 8);

            calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(day));
            calendar.set(Calendar.MONTH, Integer.parseInt(month) - 1);
            calendar.set(Calendar.YEAR, Integer.parseInt(year));

            String formatted = String.format("%02d/%02d/%04d", calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.YEAR));

            editText.setText(formatted);
            editText.setSelection(formatted.length());
        } catch (Exception e) {
            // do nothing, just keep the previous value
        }
    }

    @Override
    public void afterTextChanged(Editable s) {
    }

    public static Date parseDate(String s) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.parse(s);
    }
}