package com.example.login_registro;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.widget.EditText;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    private static int versao = 7;
    private static String nome = "Login_Registro_BaseDados.db";

    public DBHelper(Context context) {
        super( context, nome, null, versao );
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        ContentValues cv = new ContentValues();
        String str = "CREATE TABLE Utilizador(username TEXT PRIMARY KEY, password TEXT);";
        String str1 = "CREATE TABLE DadosAnimal(id INTEGER PRIMARY KEY AUTOINCREMENT, nome TEXT , animal TEXT, local TEXT, contato INT, imagem BLOB);";
        db.execSQL( str );
        db.execSQL( str1 );
        cv.put( "username", "adm" );
        cv.put( "password", "adm" );
        db.insert( "Utilizador", null, cv );

        cv = new ContentValues();
        cv.put( "nome", "vania" );
        cv.put( "animal", "doguinho" );
        cv.put( "local", "Ribeira" );
        cv.put("contato", "809890797");

        db.insert( "DadosAnimal", null, cv );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        ContentValues cv = new ContentValues();
        db.execSQL( "DROP TABLE IF EXISTS Utilizador;" );
        db.execSQL( "DROP TABLE IF EXISTS DadosAnimal;" );
        onCreate( db );

    }

    public long CriarUtlizador(String username, String password) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put( "username", username );
        cv.put( "password", password );
        long result = db.insert( "Utilizador", null, cv );
        return result;
    }

    public String ValidarLogin(String username, String password) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery( "SELECT * FROM  Utilizador WHERE username=? AND password=?", new String[]{username, password} );
        if (c.getCount() > 0) {
            return "OK";
        }
        return "ERRO";
    }

    //o retorno da funçao tem que ser o objeto animal
    public ArrayList<String> PreencherDados() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor b = db.rawQuery( "SELECT * FROM  DadosAnimal ", null);
        ArrayList<String> names = new ArrayList<>();
        if (b.getCount() > 0) {
            b.moveToFirst();
            while ( !b.isAfterLast()) {
                String name= b.getString(b.getColumnIndex("nome"));
                names.add(name);
                b.moveToNext();
            }

            return names;
        }
        return null;
    }

    public Animal ObterDetalhesAnimal() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor b = db.rawQuery( "SELECT * FROM  DadosAnimal ", null);

        Animal animal = null;
        if (b.getCount() > 0) {
            b.moveToFirst();
            while ( !b.isAfterLast()) {
                animal = new Animal();
                animal.setName(b.getString(b.getColumnIndex("nome")));
                animal.setAge(b.getString(b.getColumnIndex("age")));
                animal.setEndereco(b.getString(b.getColumnIndex("endereco")));
                animal.setContato( b.getString(b.getColumnIndex( "contato")));
                b.moveToNext();
            }

            return animal;
        }
        return animal;
    }

    public long CriarPost(Animal animal) {

        SQLiteDatabase db = getWritableDatabase();
        ContentValues a = new ContentValues();
        a.put("nome", nome);
        a.put("animal" , animal);
        a.put( "local", end);
        a.put( "Contato",tel );
        a.put("image", DbBitmapUtility.getBytes(animal.getImage()));
        long result = db.insert( "DadosAnimal", null, a );
        return result;
    }


}
