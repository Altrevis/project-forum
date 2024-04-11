package com.oop;

import java.util.HashMap;

public class IDandPassword {
    
    HashMap<String,String> logininfo = new HashMap<String,String>();
    IDandPassword() {
        logininfo.put("Altrevis", "d!abolo81");
    }
    protected HashMap getLoginInfo() {
        return logininfo;
    }
}
