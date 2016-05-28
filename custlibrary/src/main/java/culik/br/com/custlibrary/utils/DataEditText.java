package culik.br.com.custlibrary.utils;

/**
 * Created by LUIZ on 27/05/2016.
 */
import android.content.Context;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.text.method.NumberKeyListener;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;

import java.text.SimpleDateFormat;

public class DataEditText extends EditText {
    private boolean isUpdating;

    /*
    * Maps the cursor position from date number to masked number…
    *
    * D D 2 M M 5 Y Y Y Y
    */
    private int positioning[] = { 0, 1, /*/*/ 3, 4, /*/*/ 6, 7, 8, 9, 10};

    public DataEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initialize();

    }

    public DataEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize();

    }

    public DataEditText(Context context) {
        super(context);
        initialize();

    }

    public String getCleanText() {
        String text = DataEditText.this.getText().toString();

        text.replaceAll("[^0-9]*", "");
        return text;

    }

    private void initialize() {

        final int maxNumberLength = 8;
        this.setKeyListener(keylistenerNumber);
        this.setText(" / / ");
        this.setSelection(1);

        this.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if(!hasFocus){
                    if(DataEditText.this.getText().toString().length() > 0){
                        try{
                            SimpleDateFormat sf=new SimpleDateFormat("dd/MM/yyyy");
                            sf.parse(DataEditText.this.getText().toString().trim());
                        }catch(Exception e){
                            e.printStackTrace();
                            DataEditText.this.setText(" / / ");
                    //        MessageBox.show(DataEditText.this.getContext(),"Atenção esta data não está valida,\n" +
                    //        "o formato é: DD/MM/AAAA ,tente novamente.");
                        }
                    }
                }else{
                    DataEditText.this.setSelection(1);
                }
            }

        });

        this.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                String current = s.toString();

/*
* Ok, here is the trick… calling setText below will recurse
* to this function, so we set a flag that we are actually
* updating the text, so we don’t need to reprocess it…
*/
                if (isUpdating) {
                    isUpdating = false;
                    return;

                }

/* Strip any non numeric digit from the String… */
                String number = current.replaceAll("[^0-9]*", "");

                if (number.length() > 8)
                    number = number.substring(0, 8);
                int length = number.length();

/* Pad the number to 8 characters… , coloca um espaço na proxima posição */
                String paddedNumber = padNumber(number, maxNumberLength);

/* Split date number into parts…
* 01234567
* DDMMYYYY
* or
* 012345
* DDMMYY
* */
                String part1 = paddedNumber.substring(0, 2);//dia
                String part2 = paddedNumber.substring(2, 4);//mes
                String part3 = paddedNumber.substring(4, paddedNumber.length());//ano, pode se com 2 ou 4 digitos

/* build the masked date number… */
                String date = part1 + "/" + part2 + "/" + part3 ;

/*
* Set the update flag, so the recurring call to
* afterTextChanged won’t do nothing…
*/
                isUpdating = true;
                DataEditText.this.setText(date);

                System.out.println("Posição n°: "+length);

                DataEditText.this.setSelection(positioning[length]);

            }

            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {

            }
        });
    }

    protected String padNumber(String number, int maxLength) {
        String padded = new String(number);

        for (int i = 0; i < maxLength - number.length(); i++)
        padded += " ";

        return padded;

    }

    private final KeylistenerNumber keylistenerNumber = new KeylistenerNumber();

    private class KeylistenerNumber extends NumberKeyListener {

        public int getInputType() {
            return InputType.TYPE_CLASS_NUMBER
                    | InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS;

        }

        @Override
        protected char[] getAcceptedChars() {
            return new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8',
                    '9' };

        }
    }
}
