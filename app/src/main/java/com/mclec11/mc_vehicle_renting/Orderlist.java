package com.mclec11.mc_vehicle_renting;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class Orderlist extends Activity {

    ArrayAdapter<String> adapter;
    UserModel orderModelClass;
    ListView list;
    static String s1;
    static String s2;

    Button gotomenu, placedorder;
    static String s;
    String cust_name;
    int total;

    String name;
    String placeOrder;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderlist);

        Intent data = getIntent();
        Bundle extras = data.getExtras();
        name = extras.getString("cust_name");

        orderModelClass = new UserModel();
        list = (ListView) findViewById(R.id.list);
        gotomenu = (Button) findViewById(R.id.bgotomenu);
        placedorder = (Button) findViewById(R.id.bplace);

        cust_name = orderModelClass.getName();

        adapter = new ArrayAdapter<String>(Orderlist.this, android.R.layout.simple_list_item_1, UserModel.ul);
        list.setAdapter(adapter);
        total = 0;
        for (int i = 0; i < list.getCount(); i++) {
            String temp2 = (String) list.getItemAtPosition(i);
            String intValue = temp2.replaceAll("[^0-9]", "");
            int x = Integer.parseInt(intValue);
            //System.out.println("::::" + x);
            x = (x%10)*(x/10);
            total += x;
        }

        if (UserModel.ul.isEmpty()) {
            Toast.makeText(this, "No vehicle is placed. Please go to the Menu section", Toast.LENGTH_LONG).show();

        } else {
            s2 = UserModel.ul.get(0).toString();
        }

        list.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View view, final int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Orderlist.this);
                builder.setTitle("Wanna remove order? ");
                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        s = (String) list.getItemAtPosition(position);
                        UserModel.ul.remove(s);
                        adapter.notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog alt = builder.create();
                alt.show();
            }
        });

        gotomenu.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent menuIntent = new Intent(Orderlist.this, Menuscreen.class);
                startActivity(menuIntent);
            }
        });
        for (int i = 1; i < UserModel.ul.size(); i++) {
            s1 = UserModel.ul.get(i).toString();
            s2 += "," + s1;
        }

        for (int i = 0; i < UserModel.ul.size(); i++) {
            String order = (String) list.getItemAtPosition(i);
            placeOrder += "," + order;
        }

        placedorder.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                String balance = String.valueOf(total);
                Intent i = new Intent(Orderlist.this, Amount.class);
                Bundle bundle = new Bundle();
                bundle.putString("all_order", placeOrder);
                bundle.putString("total", balance);
                bundle.putString("cust_name", name);
                i.putExtras(bundle);
                startActivity(i);
                finish();
            }
        });
    }
}