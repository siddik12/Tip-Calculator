package bd.com.bitm.batch33.tipcalculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {

    private TextView textViewPercentage, textViewTipAmount, textViewTotalAmount;

    private static final NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
    private static final NumberFormat percentFormat = NumberFormat.getPercentInstance();

    private double billAmount = 0.0;
    private double percent = 0.15;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewPercentage = findViewById(R.id.textViewPercentage);
        textViewTipAmount = findViewById(R.id.textViewTipAmount);
        textViewTotalAmount = findViewById(R.id.textViewTotalAmount);

        initUI();
        updateTip();

        EditText editTextAmount = findViewById(R.id.editTextAmount);
        editTextAmount.addTextChangedListener(editTextAmountWatcher);

        SeekBar seekBar = findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(this);


    }

    private void updateUI(double tipPercent,double totalAmount){
        textViewTipAmount.setText(currencyFormat.format(tipPercent));
        textViewTotalAmount.setText(currencyFormat.format(totalAmount));
    }

    private void initUI() {
        double tipPercentBill = billAmount * 0.15;
        double totalPercentBill = billAmount + tipPercentBill;
        updateUI(tipPercentBill,totalPercentBill);
    }

    private void updateTip() {
        textViewPercentage.setText(percentFormat.format(percent));

        double tipAmount = billAmount * percent;
        double totalAmount = billAmount + tipAmount;

        updateUI(tipAmount,totalAmount);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        percent = progress / 100.0;
        updateTip();
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }


    private TextWatcher editTextAmountWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            try {
                billAmount = Double.parseDouble(s.toString());
            }
            catch (NumberFormatException e) {
                billAmount = 0.0;
            }

            initUI();
            updateTip();
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

}
