package com.mclec11.mc_vehicle_renting;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends Activity {

    EditText name,password;
    Button signUp,login,admin;
    DatabaseHelper databaseHelper;
    static final String KEY_NAME = "name";
    static final String KEY_PASSWORD = "password";
    boolean validate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        databaseHelper=new DatabaseHelper(Login.this);

        name = (EditText)findViewById(R.id.etn);
        password =(EditText)findViewById(R.id.etmob);

        signUp = (Button)findViewById(R.id.blogin);
        login = (Button)findViewById(R.id.bregh);
        admin = (Button)findViewById(R.id.badmin);

        login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String cust_name = name.getText().toString();
                String cust_password =password.getText().toString();

                if(cust_name.equals("")||cust_password.equals("")){
                    Toast.makeText(Login.this, "Please enter the data", Toast.LENGTH_SHORT).show();
                }

                else if(databaseHelper.validateUser(cust_name, cust_password)){
                    Intent i =new Intent(Login.this,Mainpage.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("cust_name", cust_name);
                    i.putExtras(bundle);
                    startActivity(i);
                    name.setText("");
                    password.setText("");
                }
                else {
                    Toast.makeText(Login.this, "Your are not registered! ", Toast.LENGTH_SHORT).show();
                    name.setText("");
                    password.setText("");
                }
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent r =new Intent(Login.this,Register.class);
                startActivity(r);
            }
        });
        admin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String cust_name = name.getText().toString();
                String cust_password =password.getText().toString();

                if(cust_name.equals("")||cust_password.equals("")){
                    Toast.makeText(Login.this, "Please enter the data", Toast.LENGTH_SHORT).show();
                }

                else {
                    Intent r =new Intent(Login.this,Admin.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("cust_name", cust_name);
                    r.putExtras(bundle);
                    startActivity(r);
                    name.setText("");
                    password.setText("");
                }
            }
        });
    }
}
