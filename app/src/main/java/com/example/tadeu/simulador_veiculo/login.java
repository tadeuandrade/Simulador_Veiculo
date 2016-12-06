package com.example.tadeu.simulador_veiculo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class login extends Activity {

    EditText usuario, senha;
    Button entrar;
    Intent logar;
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usuario = (EditText) findViewById(R.id.usuario);
        senha = (EditText) findViewById(R.id.senha);
        entrar = (Button) findViewById(R.id.entrar);

        entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (usuario.getText().toString().equals("admin00") && senha.getText().toString().equals("vendas")){

                    logar = retornait(menu.class);
                    startActivity(logar);
                    finish();

                }else{

                Toast.makeText(getApplicationContext(), "Usuário ou senha incorretos", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
    private Intent retornait(Class classe){
        Intent it = new Intent(context, classe);
        return it;
    }
}
