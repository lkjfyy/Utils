

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Administrator on 2017/12/13.
 */

public abstract class EditTextUtils {

    /**
     * 设置含多个edittext的页面按钮是否可点击
     *
     * @param editTexts 页面中的edittext
     * @param textView  是否可点击的textview（页面中的点击按钮）
     */
    public static void setOnTextChangedListener(EditText[] editTexts, final TextView textView) {
        final Boolean[] booleens = new Boolean[editTexts.length];
        if (editTexts != null && editTexts.length > 0) {
            for (int i = 0; i < editTexts.length; i++) {
                booleens[i] = false;//默认不可点击
                final int j = i;
                editTexts[i].addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!TextUtils.isEmpty(s)) {
                            booleens[j] = true;
                        } else {
                            booleens[j] = false;
                        }
                        setTextViewClick(booleens, textView);
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
            }

        }
    }

    private static void setTextViewClick(Boolean[] booleens, TextView textView) {
        int m = 0;
        for (int i = 0; i < booleens.length; i++) {
            if (booleens[i]) {
                m++;
            }
        }
        if (m == booleens.length) {
            textView.setEnabled(true);
        } else {
            textView.setEnabled(false);
        }
    }

    /**
     * 设置页面中的imageview是否可见
     *
     * @param editText
     * @param imageView
     */
    public static void setOnEditTextFocusChangedListener(final EditText editText, final ImageView imageView) {
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus && !TextUtils.isEmpty(editText.getText().toString().trim())) {
                    imageView.setVisibility(View.VISIBLE);
                    //光标在最后显示  
                    editText.setSelection(editText.length());
                } else {
                    imageView.setVisibility(View.GONE);
                }
            }
        });
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (TextUtils.isEmpty(s)){
                    imageView.setVisibility(View.GONE);
                }else {
                    imageView.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}
