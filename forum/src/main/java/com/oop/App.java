package com.oop;

class App{
    public static void main(String[] args) {
        IDandPassword idandPassword = new IDandPassword();
        new LoginPage(idandPassword.getLoginInfo(), null);
    }
}
