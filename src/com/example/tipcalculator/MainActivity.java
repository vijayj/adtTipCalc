package com.example.tipcalculator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

	private EditText etAmount;
	private TextView txtTip;
	private ArrayList<Button> buttons;
	private static String TIP_LABEL = "Tip is:";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		etAmount = (EditText) findViewById(R.id.etAmount);
		txtTip = (TextView) findViewById(R.id.txtTipAmount);
		
		buttons = new ArrayList<Button>();
		buttons.add((Button)findViewById(R.id.btnTen));
		buttons.add((Button)findViewById(R.id.btnFifteen));
		buttons.add((Button)findViewById(R.id.btnTwenty));
		
		OnClickListener buttonHandler = new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
			 Button button = (Button) v;
			 BigDecimal tipAmount = new BigDecimal((String) button.getText());			 
			 BigDecimal amount  = new BigDecimal(etAmount.getText().toString());
			 
			 BigDecimal tip = amount.multiply(tipAmount).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP);
			 
			 txtTip.setText(String.format("%s $%s", TIP_LABEL,tip));
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
