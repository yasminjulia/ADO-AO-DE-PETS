package com.example.login_registro;

import android.graphics.Bitmap;

public class Animal {

    private String name, age, endereco, contato;

    public static Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    private Bitmap image;


    public static String getName() {
        return name;
    }

    public static String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public static String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }



}
