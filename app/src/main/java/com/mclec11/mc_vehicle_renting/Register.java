package com.mclec11.mc_vehicle_renting;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends Activity {

    EditText name,password,phone;
    Button register;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        databaseHelper=new DatabaseHelper(Register.this);

        register=(Button)findViewById(R.id.breg);
        name = (EditText)findViewById(R.id.etname);
        password =(EditText)findViewById(R.id.etemail);
        phone =(EditText)findViewById(R.id.etphone);

        register.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String cust_name = name.getText().toString();
                String cust_password = password.getText().toString();
                String cust_phone = phone.getText().toString();

                if(cust_name.equals("")||cust_password.equals("")||cust_phone.equals("")){
                    Toast.makeText(Register.this, "All fields are Mandatory", Toast.LENGTH_LONG).show();
                }
                else{
                    UserModel userModel=new UserModel();
                    userModel.setName(name.getText().toString());
                    userModel.setPassword(password.getText().toString());
                    userModel.setPhoneNo(phone.getText().toString());
                    databaseHelper.AddUser(userModel);

                    Intent i =new Intent(Register.this,Mainpage.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("cust_name", cust_name);
                    i.putExtras(bundle);
                    startActivity(i);
                }
            }
        });
    }
}

