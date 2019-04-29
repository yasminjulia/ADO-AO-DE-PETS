package com.example.login_registro;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static java.lang.String.*;

public class Publicacao extends AppCompatActivity {

    EditText animal, age, local;
    DBHelper db;
    Button postar, pesquisar;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_publicacao );
        db=new DBHelper( this );

        animal = (EditText)findViewById(R.id.animal_nome);
        age= (EditText) findViewById( R.id.idade);
        local= (EditText) findViewById( R.id.endereco );
        postar=(Button)findViewById(R.id.bt_postar);
        pesquisar = (Button)findViewById( R.id.bt_pesquisa );

        postar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String animais = animal.getText().toString();
                String age1 = age.getText().toString();
                String end =local.getText().toString();

                if (animais.equals( "" )){
                    Toast.makeText( Publicacao.this,"Nome não inserido, tente novamente", Toast.LENGTH_SHORT).show();
                } else if (age1.equals( "" )|| end.equals( "" )) {
                    Toast.makeText( Publicacao.this, "Deve preencher o campo corretamente, tente novamente", Toast.LENGTH_SHORT ).show();
                } else {
                    long res  = db.CriarPost( animais, age1, end );
                    if (res>0){
                        Toast.makeText( Publicacao.this, "Publicado com sucesso", Toast.LENGTH_SHORT ).show();
                    } else {
                        Toast.makeText( Publicacao.this, "Publicação inválida", Toast.LENGTH_SHORT ).show();
                    }

                }
            }

        });
        pesquisar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Publicacao.this, Mural.class);
                startActivity(i);
            }
        });

    }
}
