package com.mclec11.mc_vehicle_renting;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import java.util.List;

public class OrdersData extends Activity {

    DatabaseHelper databaseHelper;
    TextView datalist;
    TextView datalist_count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderside);

        databaseHelper=new DatabaseHelper(OrdersData.this);

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
        datalist_count.setText("ALL DATA COUNT : "+databaseHelper.getTotalCountOrders());

        List<OrderModel> orderModelList=databaseHelper.getAllOrders();
        datalist.setText("");
        for(OrderModel orderModel:orderModelList){
            datalist.append("ID : "+orderModel.getOrder_id()+"   |   Customer Name : "+orderModel.getOrder_name()+"   |   Order Placed : "+orderModel.getOrder_placed()+"   |   Bill : "+orderModel.getOrder_amount()+" \n\n");
        }
    }

    private void ShowInputDialog() {
        AlertDialog.Builder al=new AlertDialog.Builder(OrdersData.this);
        View view=getLayoutInflater().inflate(R.layout.insert_order_data,null);
        final EditText name=view.findViewById(R.id.name);
        final EditText order=view.findViewById(R.id.phone);
        final EditText amount=view.findViewById(R.id.password);
        Button insertBtn=view.findViewById(R.id.insert_btn);
        al.setView(view);

        final AlertDialog alertDialog=al.show();

        insertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OrderModel orderModel=new OrderModel();
                orderModel.setOrder_name(name.getText().toString());
                orderModel.setOrder_placed(order.getText().toString());
                orderModel.setOrder_amount(amount.getText().toString());
                databaseHelper.AddOrder(orderModel);
                alertDialog.dismiss();
                refreshData();
            }
        });
    }

    private void showDeleteDialog() {
        AlertDialog.Builder al=new AlertDialog.Builder(OrdersData.this);
        View view=getLayoutInflater().inflate(R.layout.delete_order_dialog,null);
        al.setView(view);
        final EditText id_input=view.findViewById(R.id.id_input);
        Button delete_btn=view.findViewById(R.id.delete_btn);
        final AlertDialog alertDialog=al.show();

        delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseHelper.deleteOrder(id_input.getText().toString());
                alertDialog.dismiss();
                refreshData();
            }
        });
    }

    private void showUpdateIdDialog() {
        AlertDialog.Builder al=new AlertDialog.Builder(OrdersData.this);
        View view=getLayoutInflater().inflate(R.layout.update_id_order_dialog,null);
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
        OrderModel orderModel=databaseHelper.getOrder(Integer.parseInt(id));
        AlertDialog.Builder al=new AlertDialog.Builder(OrdersData.this);
        View view=getLayoutInflater().inflate(R.layout.update_order_dialog,null);
        final EditText name=view.findViewById(R.id.name);
        final EditText order=view.findViewById(R.id.phone);
        final EditText amount=view.findViewById(R.id.password);
        Button update_btn=view.findViewById(R.id.update_btn);
        al.setView(view);

        name.setText(orderModel.getOrder_name());
        order.setText(orderModel.getOrder_placed());
        amount.setText(orderModel.getOrder_amount());

        final AlertDialog alertDialog=al.show();
        update_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OrderModel orderModel=new OrderModel();
                orderModel.setOrder_name(name.getText().toString());
                orderModel.setOrder_id(id);
                orderModel.setOrder_placed(order.getText().toString());
                orderModel.setOrder_amount(amount.getText().toString());
                databaseHelper.updateOrder(orderModel);
                alertDialog.dismiss();
                refreshData();
            }
        });
    }
}
