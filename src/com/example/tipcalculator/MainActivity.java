package com.example.tipcalculator;

import java.math.BigDecimal;
import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	protected static final String INVALID_AMOUNT_MESSAGE = "Please enter a valid amount";
	private EditText etAmount;
	private TextView txtTip;
	private ArrayList<Button> buttons;
	private static String TIP_LABEL = "Tip is:";
	private static String ENTER_TIP = "Please enter tip percentage";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		etAmount = (EditText) findViewById(R.id.etAmount);
		etAmount.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				txtTip.setText(ENTER_TIP);				
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				
			}
		});
		
		txtTip = (TextView) findViewById(R.id.txtTipAmount);
		txtTip.setText(ENTER_TIP);
		txtTip.setTextSize(0, 18);
		
		buttons = new ArrayList<Button>();
		buttons.add((Button)findViewById(R.id.btnTen));
		buttons.add((Button)findViewById(R.id.btnFifteen));
		buttons.add((Button)findViewById(R.id.btnTwenty));
		
		OnClickListener buttonHandler = new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
			 Button button = (Button) v;			 
			 BigDecimal tipAmount = new BigDecimal((String) button.getText());			 
			 try {
				 BigDecimal amount  = new BigDecimal(etAmount.getText().toString());
				 BigDecimal tip = amount.multiply(tipAmount).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP);
				 txtTip.setText(String.format("%s $%s", TIP_LABEL,tip));
			 } catch(NumberFormatException e){
				 Toast.makeText(getApplication(), INVALID_AMOUNT_MESSAGE, Toast.LENGTH_SHORT).show();
			 }			 
			}
		};
		
		for (Button button : buttons) {
			button.setOnClickListener(buttonHandler);
		}				
	}	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
