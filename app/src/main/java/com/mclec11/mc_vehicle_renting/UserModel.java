package com.mclec11.mc_vehicle_renting;

import java.util.ArrayList;

public class UserModel {

    String id="";
    String name="";
    String password="";
    String phoneNo="";

    public UserModel(String id, String name, String password, String phoneNo) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.phoneNo = phoneNo;
    }

    static ArrayList<String> ul;

	public static void createlist(){
		ul = new ArrayList<String>();
	}

    public UserModel(){

    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getPhoneNo() {
        return phoneNo;
    }
}
