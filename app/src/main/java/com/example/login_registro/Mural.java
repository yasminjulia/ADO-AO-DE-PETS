package com.example.login_registro;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.Toolbar;
import android.view.Menu;
import java.util.ArrayList;

public class Mural extends AppCompatActivity {

    DBHelper db;
    Cursor c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_mural );
        ListView list = (ListView) findViewById( R.id.lista );
        db= new DBHelper( this );

        final ArrayList<String> coisas = Preencher();

        ArrayAdapter<String> array = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, coisas  );
        list.setAdapter( array );
    }

    private ArrayList<String> Preencher() {
        return (db.PreencherDados());
    }
}
