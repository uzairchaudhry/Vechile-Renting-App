package com.mclec11.mc_vehicle_renting;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Admin extends Activity {

    Button customer,order;
    String user_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        Intent myintent = getIntent();
        Bundle extras = myintent.getExtras();
        user_name = extras.getString("cust_name");
        Toast.makeText(Admin.this, "Welcome " + user_name ,Toast.LENGTH_LONG ).show();

        customer=(Button)findViewById(R.id.button1);
        order=(Button)findViewById(R.id.button2);

        customer.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i =new Intent(Admin.this,CustomerData.class);
                startActivity(i);
            }
        });

        order.setOnClickListener( new View.OnClickListener() {

            public void onClick(View v) {
                Intent i =new Intent(Admin.this,OrdersData.class);
                startActivity(i);
            }
        });

    }
}
