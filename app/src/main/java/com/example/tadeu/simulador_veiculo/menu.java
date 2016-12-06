package com.example.tadeu.simulador_veiculo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class menu extends Activity {


    Button parametro, simular;
    Intent lancarparam, enviarsimula, recebido;
    Context context=this;
    String entrada1, entrada2, taxajuros1, taxajuros2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        parametro = (Button) findViewById(R.id.param);
        simular = (Button) findViewById(R.id.simular);
        lancarparam = retornait(parametro.class);
        enviarsimula = retornait(simulacao.class);

        recebido = getIntent();
        entrada1 = recebido.getStringExtra("entrada1");
        entrada2 = recebido.getStringExtra("entrada2");
        taxajuros1 = recebido.getStringExtra("taxajuros1");
        taxajuros2 = recebido.getStringExtra("taxajuros2");

        if (entrada1==null || entrada2==null || taxajuros1==null || taxajuros2==null){
            simular.setEnabled(false);
        }


        parametro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(lancarparam);

            }
        });

        simular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                enviarsimula.putExtra("entrada1", entrada1);
                enviarsimula.putExtra("entrada2", entrada2);
                enviarsimula.putExtra("taxajuros1", taxajuros1);
                enviarsimula.putExtra("taxajuros2", taxajuros2);
                startActivity(enviarsimula);
                finish();
            }
        });
    }

    private Intent retornait(Class classe ){
        Intent it = new Intent(context, classe);
        return it;
    }
}
