package com.mclec11.mc_vehicle_renting;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Amount extends Activity {
	
	TextView result;
	Button confirm;
	double sum;
	public static String totalamount;
	String order,name,balance;

	DatabaseHelper databaseHelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_amount);

		databaseHelper=new DatabaseHelper(Amount.this);

		result=(TextView)findViewById(R.id.tvtotal);
		confirm=(Button)findViewById(R.id.bOk);

		Intent data = getIntent();
		Bundle extras = data.getExtras();

		final int temp = Integer.valueOf(extras.getString("total"));
		result.setText("Total Amount: "+temp);

		balance = extras.getString("total");
        order = extras.getString("all_order");
		name = extras.getString("cust_name");

		confirm.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				OrderModel orderModel = new OrderModel();
				orderModel.setOrder_name(name);
				orderModel.setOrder_placed(order);
				orderModel.setOrder_amount(balance);
				databaseHelper.AddOrder(orderModel);
				totalamount = temp + "";
				Intent m = new Intent(Amount.this, Lastpage.class);
				startActivity(m);
			}
		});
	}
}
