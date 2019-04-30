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
    SQLiteDatabase db;
    Cursor c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_mural );
        ListView list = (ListView) findViewById( R.id.lista );

        final ArrayList<String> coisas = PreencherDados();
        ArrayAdapter<String> array = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, coisas  );
        list.setAdapter( array );
    }
0
    private ArrayList<String> PreencherDados() {
        try {
        db = openOrCreateDatabase( "listagem.db", Context.MODE_PRIVATE, null );
        c = db.rawQuery( "SELECT * from listagem", null );

        }catch (exception e){
            Toast.makeText( Mural.this, "Publicação inválida", Toast.LENGTH_SHORT ).show();
        }


        ArrayList<String> dados = new ArrayList<String>();

        dados.add( "Paulo" );
        dados.add( "Juliana" );
        dados.add( "Pedro Santos" );
        dados.add( "Luis" );
        dados.add( "Fernando" );
        dados.add( "Pedro Carlos" );
        dados.add( "Luis Claudio" );
        dados.add( "Maria Luisa" );
        dados.add( "Paula Santos" );
        dados.add( "Valenttina" );
        dados.add( "Fernanda Matos" );
        return (dados);
    }
}
