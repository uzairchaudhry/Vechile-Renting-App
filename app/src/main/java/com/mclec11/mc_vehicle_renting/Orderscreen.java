package com.mclec11.mc_vehicle_renting;

import android.app.Activity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Orderscreen extends Activity implements OnClickListener {

	EditText Quantity;
	Button Place_Order;
	String tempstring;
	Integer i;
	String s;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_orderscreen);

		Quantity =(EditText)findViewById(R.id.etquan);
		Place_Order =(Button)findViewById(R.id.baddorder);

		s = getIntent().getStringExtra("order");

		Quantity.setRawInputType(InputType.TYPE_CLASS_NUMBER);

		Place_Order.setClickable(true);

		Place_Order.setOnClickListener(this);

	}
	@Override
	protected void onResume() {
		super.onResume();
		Place_Order.setClickable(true);
	}
	@Override
	protected void onRestart() {
		super.onRestart();
		Place_Order.setClickable(true);
	}

	@Override
	public void onClick(View v) {
		Place_Order.setClickable(false);
		tempstring = Quantity.getText().toString();

		i = Integer.parseInt(tempstring);
		Toast.makeText(Orderscreen.this, s, Toast.LENGTH_LONG);

		s=s+"  "+ "-"+"  "+ i;

		boolean add = UserModel.ul.add(s);
		finish();
	}
	public boolean checkType()
	{
		return false;
	}

}