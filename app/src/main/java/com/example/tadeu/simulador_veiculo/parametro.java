package com.example.tadeu.simulador_veiculo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class parametro extends Activity {

    EditText entrada1,entrada2,taxajuros1,taxajuros2;
    Button definir;
    Intent salva_param;
    Context context=this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parametro);

        entrada1 = (EditText) findViewById(R.id.entrada1);
        entrada2 = (EditText) findViewById(R.id.entrada2);
        taxajuros1 = (EditText) findViewById(R.id.taxajuros1);
        taxajuros2 = (EditText) findViewById(R.id.taxajuros2);
        definir = (Button) findViewById(R.id.definir_param);


        definir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                salva_param = retornait(menu.class);
                salva_param.putExtra("entrada1", entrada1.getText().toString());
                salva_param.putExtra("entrada2", entrada2.getText().toString());
                salva_param.putExtra("taxajuros1", taxajuros1.getText().toString());
                salva_param.putExtra("taxajuros2", taxajuros2.getText().toString());
                startActivity(salva_param);
                finish();

            }
        });
    }
    private Intent retornait(Class classe ){
        Intent it = new Intent(context, classe);
        return it;
    }

}
