package com.mclec11.mc_vehicle_renting;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION=1;
    private static final String  DATABASE_NAME="foodie";

    private static final String TABLE_NAME="cust";
    private static final String ID="id";
    private static final String name="name";
    private static final String password="password";
    private static final String phoneNo="phoneNo";

    private static final String TABLE_NAME1="orr";
    private static final String ORDERID="order_id";
    private static final String ORDERname="order_name";
    private static final String ORDERplaced="order_placed";
    private static final String ORDERamount="order_amount";
    //private static final String  DATABASE_NAME="renting_db";


//    private static final String TABLE_NAME="customer";
//    private static final String ID="id";
//    private static final String name="name";
//    private static final String password="password";
//    private static final String phoneNo="phoneNo";
//
//    private static final String TABLE_NAME1="orrr";
//    private static final String ORDERID="order_id";
//    private static final String ORDERname="order_name";
//    private static final String ORDERplaced="order_placed";
//    private static final String ORDERamount="order_amount";

    public DatabaseHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String table_query="CREATE TABLE if not EXISTS "+TABLE_NAME+
                "("+
                ID+" INTEGER PRIMARY KEY,"+
                name+" TEXT ,"+
                password+" TEXT ,"+
                phoneNo+ " TEXT "+
                ")";

        String table_query1="CREATE TABLE if not EXISTS "+TABLE_NAME1+
                "("+
                ORDERID+" INTEGER PRIMARY KEY,"+
                ORDERname+" TEXT ,"+
                ORDERplaced+" TEXT ,"+
                ORDERamount+ " TEXT "+
                ")";

        db.execSQL(table_query);
        db.execSQL(table_query1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME1);
    }

    public void AddUser(UserModel userModel){
        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues contentValues=new ContentValues();
        contentValues.put(name,userModel.getName());
        contentValues.put(password,userModel.getPassword());
        contentValues.put(phoneNo,userModel.getPhoneNo());
        db.insert(TABLE_NAME,null,contentValues);
        db.close();
    }

    public void AddOrder(OrderModel orderModel){
        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues contentValues=new ContentValues();
        contentValues.put(ORDERname,orderModel.getOrder_name());
        contentValues.put(ORDERplaced,orderModel.getOrder_placed());
        contentValues.put(ORDERamount,orderModel.getOrder_amount());
        db.insert(TABLE_NAME1,null,contentValues);
        db.close();
    }

    public List<UserModel> getAllUsers(){
        List<UserModel> userModelList=new ArrayList<>();
        String query="SELECT * from "+TABLE_NAME;
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(query,null);
        if(cursor.moveToFirst()){
            do{
                UserModel userModel=new UserModel(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3));
                userModelList.add(userModel);
            }
            while (cursor.moveToNext());

        }
        db.close();
        return userModelList;
    }

    public List<OrderModel> getAllOrders(){
        List<OrderModel> orderModelList=new ArrayList<>();
        String query="SELECT * from "+TABLE_NAME1;
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(query,null);
        if(cursor.moveToFirst()){
            do{
                OrderModel orderModel=new OrderModel(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3));
                orderModelList.add(orderModel);
            }
            while (cursor.moveToNext());

        }
        db.close();
        return orderModelList;
    }

    public UserModel getUser(int id){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.query(TABLE_NAME,new String[]{ID,name,password,phoneNo},ID+" = ?",new String[]{String.valueOf(id)},null,null,null);
        if(cursor!=null){
            cursor.moveToFirst();
        }
        UserModel userModel=new UserModel(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3));
        db.close();
        return userModel;
    }

    public OrderModel getOrder(int id){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.query(TABLE_NAME1,new String[]{ORDERID,ORDERname,ORDERplaced,ORDERamount},ORDERID+" = ?",new String[]{String.valueOf(id)},null,null,null);
        if(cursor!=null){
            cursor.moveToFirst();
        }
        OrderModel orderModel=new OrderModel(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3));
        db.close();
        return orderModel;
    }

    public boolean validateUser (String username, String userpassword){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor =db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE "
                + name + "='" + username +"'AND "+ password +"='"+ userpassword +"'" ,  null);
        if(cursor.getCount() > 0)
            return true;
        return false;
    }

    public int updateUser(UserModel userModel){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(name,userModel.getName());
        contentValues.put(password,userModel.getPassword());
        return db.update(TABLE_NAME,contentValues,ID+"=?",new String[]{String.valueOf(userModel.getId())});
    }

    public int updateOrder(OrderModel orderModel){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(ORDERname,orderModel.getOrder_name());
        contentValues.put(ORDERplaced,orderModel.getOrder_placed());
        contentValues.put(ORDERamount,orderModel.getOrder_amount());
        return db.update(TABLE_NAME1,contentValues,ORDERID+"=?",new String[]{String.valueOf(orderModel.getOrder_id())});
    }

    public void deleteUser(String id){
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(TABLE_NAME,ID+"=?",new String[]{id});
        db.close();
    }

    public void deleteOrder(String id){
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(TABLE_NAME1,ORDERID+"=?",new String[]{id});
        db.close();
    }

    public int getTotalCount(){
        String query="SELECT * from "+TABLE_NAME;
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery(query,null);
        return cursor.getCount();
    }

    public int getTotalCountOrders(){
        String query="SELECT * from "+TABLE_NAME1;
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery(query,null);
        return cursor.getCount();
    }
}

