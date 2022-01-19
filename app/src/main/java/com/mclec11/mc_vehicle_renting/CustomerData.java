package com.mclec11.mc_vehicle_renting;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import java.util.List;

public class CustomerData extends Activity {
    DatabaseHelper databaseHelper;
    TextView datalist;
    TextView datalist_count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customerside);

        databaseHelper=new DatabaseHelper(CustomerData.this);

        Button insert=findViewById(R.id.insert_data);
        Button delete=findViewById(R.id.delete_data);
        Button update=findViewById(R.id.update_data);
        Button read=findViewById(R.id.refresh_data);
        datalist=findViewById(R.id.all_data_list);
        datalist_count=findViewById(R.id.data_list_count);

        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refreshData();
            }
        });

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowInputDialog();
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showUpdateIdDialog();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDeleteDialog();
            }
        });
    }

    private void refreshData() {
        datalist_count.setText("ALL DATA COUNT : "+databaseHelper.getTotalCount());

        List<UserModel> userModelList=databaseHelper.getAllUsers();
        datalist.setText("");
        for(UserModel userModel:userModelList){
            datalist.append("ID : "+userModel.getId()+"   |   Name : "+userModel.getName()+"   |   Phone No : "+userModel.getPhoneNo()+"   |   Password : "+userModel.getPassword()+" \n\n");
        }
    }

    private void ShowInputDialog() {
        AlertDialog.Builder al=new AlertDialog.Builder(CustomerData.this);
        View view=getLayoutInflater().inflate(R.layout.insert_dialog,null);
        final EditText name=view.findViewById(R.id.name);
        final EditText phone=view.findViewById(R.id.phone);
        final EditText password=view.findViewById(R.id.password);
        Button insertBtn=view.findViewById(R.id.insert_btn);
        al.setView(view);

        final AlertDialog alertDialog=al.show();

        insertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserModel userModel=new UserModel();
                userModel.setName(name.getText().toString());
                userModel.setPhoneNo(phone.getText().toString());
                userModel.setPassword(password.getText().toString());
                databaseHelper.AddUser(userModel);
                alertDialog.dismiss();
                refreshData();
            }
        });
    }


    private void showDeleteDialog() {
        AlertDialog.Builder al=new AlertDialog.Builder(CustomerData.this);
        View view=getLayoutInflater().inflate(R.layout.delete_dialog,null);
        al.setView(view);
        final EditText id_input=view.findViewById(R.id.id_input);
        Button delete_btn=view.findViewById(R.id.delete_btn);
        final AlertDialog alertDialog=al.show();

        delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseHelper.deleteUser(id_input.getText().toString());
                alertDialog.dismiss();
                refreshData();
            }
        });


    }

    private void showUpdateIdDialog() {
        AlertDialog.Builder al=new AlertDialog.Builder(CustomerData.this);
        View view=getLayoutInflater().inflate(R.layout.update_id_dialog,null);
        al.setView(view);
        final EditText id_input=view.findViewById(R.id.id_input);
        Button fetch_btn=view.findViewById(R.id.update_id_btn);
        final AlertDialog alertDialog=al.show();
        fetch_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDataDialog(id_input.getText().toString());
                alertDialog.dismiss();
                refreshData();
            }
        });

    }

    private void showDataDialog(final String id) {
        UserModel userModel=databaseHelper.getUser(Integer.parseInt(id));
        AlertDialog.Builder al=new AlertDialog.Builder(CustomerData.this);
        View view=getLayoutInflater().inflate(R.layout.update_dialog,null);
        final EditText name=view.findViewById(R.id.name);
        final EditText phone=view.findViewById(R.id.phone);
        final EditText password=view.findViewById(R.id.password);
        Button update_btn=view.findViewById(R.id.update_btn);
        al.setView(view);

        name.setText(userModel.getName());
        phone.setText(userModel.getPhoneNo());
        password.setText(userModel.getPassword());

        final AlertDialog alertDialog=al.show();
        update_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserModel userModel=new UserModel();
                userModel.setName(name.getText().toString());
                userModel.setId(id);
                userModel.setPhoneNo(phone.getText().toString());
                userModel.setPassword(password.getText().toString());
                databaseHelper.updateUser(userModel);
                alertDialog.dismiss();
                refreshData();
            }
        });
    }
}
