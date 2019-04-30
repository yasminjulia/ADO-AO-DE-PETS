package com.example.login_registro;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.EditText;

public class DBHelper extends SQLiteOpenHelper {
    private static int versao = 1;
    private static String nome = "Login_Registro_BaseDados.db";

    public DBHelper(Context context) {
        super( context, nome, null, versao );
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String str = "CREATE TABLE Utilizador(username TEXT PRIMARY KEY, password TEXT);";
        String str1 = "CREATE TABLE DadosAnimal(nome TEXT PRIMARY KEY, animal TEXT, local TEXT);";
        db.execSQL( str );
        db.execSQL( str1 );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL( "DROP TABLE IF EXISTS Utilizador;" );
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
    public String PreencherDados(String nome, String animal, String local) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor b = db.rawQuery( "SELECT * FROM  DadosAnimal WHERE nome=? AND animal=? AND local=?", new String[]{nome,  animal, local} );
        if (b.getCount() > 0) {
            return "OK";
        }
        return "ERRO";
    }

    public long CriarPost(String nome, String animal, String end) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues a = new ContentValues();
        a.put("nome", nome);
        a.put("animal" , animal);
        a.put( "local", end);
        long result = db.insert( "DadosAnimal", null, a );
        return result;
    }


}
