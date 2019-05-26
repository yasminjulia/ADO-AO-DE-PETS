package com.example.login_registro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegistrarActivity extends AppCompatActivity {

    EditText et_username,et_pass1, et_pass2;
    Button  bt_registrar,bt_share;
    DBHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        db=new DBHelper( this );

        et_username= findViewById(R.id.et_reg_username);
        et_pass1= findViewById(R.id.et_reg_password1);
        et_pass2= findViewById(R.id.et_reg_password2);

        bt_registrar= findViewById(R.id.bt_registranovo);
        bt_registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = et_username.getText().toString();
                String p1 = et_pass1.getText().toString();
                String p2 = et_pass2.getText().toString();

    bt_share.setOnClickListener( new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Animal ani = new Animal();
        ani.setName(Animal.getName());

        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT,ani.getClass().toString());
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
    }
} );
                if (username.equals( "" )) {
                    Toast.makeText( RegistrarActivity.this, "Username não inserido, tente novamente", Toast.LENGTH_SHORT ).show();
                } else if (p1.equals( "" ) || p2.equals( "" )) {
                    Toast.makeText( RegistrarActivity.this, "Deve preencher o campo senha, tente novamente", Toast.LENGTH_SHORT ).show();
                } else if (!p1.equals( p2 )) {
                    Toast.makeText( RegistrarActivity.this, "As senhas devem ser iguais, tente novamente", Toast.LENGTH_SHORT ).show();
                } else {
                    long res = db.CriarUtlizador( username, p1 );
                    if (res > 0) {
                        Toast.makeText( RegistrarActivity.this, "Login criado", Toast.LENGTH_SHORT ).show();
                        bt_registrar.setOnClickListener( new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent i = new Intent( RegistrarActivity.this, Publicacao.class );
                                startActivity( i );
                            }
                        } );
                    } else {
                        Toast.makeText( RegistrarActivity.this, "Registro inválido", Toast.LENGTH_SHORT ).show();
                    }

                }
            }

        });
    }
}
