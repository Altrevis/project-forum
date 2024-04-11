package com.oop;

class App{
    public static void main(String[] args) {
        IDandPassword idandPassword = new IDandPassword();
        
        @SuppressWarnings({ "unchecked", "unused" })
        LoginPage loginPage = new LoginPage(idandPassword.getLoginInfo());
    }
}
