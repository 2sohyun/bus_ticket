package com;

public class MemberInfo {
    String id;
    String name;
    String password;
    int cellphone;
    String gender;
    int age;
    
    public MemberInfo(String id, String name, String password, int cellphone, String gender) {
    	this.id = id;
    	this.name = name;
    	this.password = password;
    	this.cellphone = cellphone;
    	this.gender = gender;
    	
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassWord() {
        return password;
    }

    public void setPassWord(String password) {
        this.password = password;
    }
    
    public int getCellPhone() {
        return cellphone;
    }

    public void setCellPhone(int cellphone) {
        this.cellphone = cellphone;
    }
    
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
