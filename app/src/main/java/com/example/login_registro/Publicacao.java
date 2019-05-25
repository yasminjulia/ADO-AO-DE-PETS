package com.example.login_registro;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import android.widget.ImageView;
import android.content.Context;
import static java.lang.String.*;

public class Publicacao extends AppCompatActivity {

    EditText animal, age, tel, local;
    DBHelper db;
    Button postar, pesquisar;
    ImageButton cam, map;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    ImageView imThumb;
    Context context;
    Animal animalObject;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_publicacao );
        context = this;
        db=new DBHelper( this );

        animal = (EditText)findViewById(R.id.animal_nome);
        age= (EditText)findViewById( R.id.idade);
        local= (EditText)findViewById( R.id.endereco );
        postar=(Button)findViewById(R.id.bt_postar);
        pesquisar = (Button)findViewById( R.id.bt_pesquisa );
        tel = (EditText)findViewById( R.id.contato );
        cam = (ImageButton) findViewById( R.id.Buttocam );
        map = (ImageButton)findViewById( R.id.Buttomap );
        imThumb = (ImageView) findViewById(R.id.imThumb);


        postar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animalObject = new Animal();
                animalObject.setName(animal.getText().toString());
                animalObject.setAge(age.getText().toString());
                animalObject.setEndereco(local.getText().toString());
                animalObject.setContato(tel.getText().toString());

                //

                if (animalObject.getName().equals( "" )){
                    Toast.makeText( Publicacao.this,"Nome não inserido, tente novamente", Toast.LENGTH_SHORT).show();
                } else if (animalObject.getAge().equals( "" )|| animalObject.getEndereco().equals( "" )) {
                    Toast.makeText( Publicacao.this, "Deve preencher o campo corretamente, tente novamente", Toast.LENGTH_SHORT ).show();
                } else {
                    //alterar metodo criar post para receber o objeto animal
                    long res  = (long) db.CriarPost( animalObject.getName(), animalObject.getAge(), animalObject.getEndereco(), animalObject.getContato(), animalObject.getImage());
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

        cam.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
            }
        } );
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent( MediaStore.ACTION_IMAGE_CAPTURE );
        if (takePictureIntent.resolveActivity( getPackageManager())!= null){
            startActivityForResult( takePictureIntent, REQUEST_IMAGE_CAPTURE );
        }
    }
    protected void onActivityResult(int requestCode, int resultCode,Intent data){
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode== RESULT_OK){
        Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");

            imThumb.setImageBitmap( imageBitmap );
            animalObject.setImage(imageBitmap);

        }
    }
}
