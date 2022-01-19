package com.mclec11.mc_vehicle_renting;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Mainpage extends Activity {

    Button menu,order;
    String user_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainpage);

        Intent myintent = getIntent();
        Bundle extras = myintent.getExtras();
        user_name = extras.getString("cust_name");
        Toast.makeText(Mainpage.this, "Welcome " + user_name ,Toast.LENGTH_LONG ).show();

        menu=(Button)findViewById(R.id.bmenu);
        order=(Button)findViewById(R.id.bOrder);

        UserModel.createlist();

        menu.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i =new Intent(Mainpage.this,Menuscreen.class);
                startActivity(i);
            }
        });

        order.setOnClickListener( new View.OnClickListener() {

            public void onClick(View v) {
                Intent i =new Intent(Mainpage.this,Orderlist.class);
                Bundle bundle = new Bundle();
                bundle.putString("cust_name", user_name);
                i.putExtras(bundle);
                startActivity(i);
            }
        });

    }
}
