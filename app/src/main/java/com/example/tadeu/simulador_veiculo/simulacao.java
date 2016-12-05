package com.example.tadeu.simulador_veiculo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.opengl.EGLExt;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class simulacao extends Activity {

    Spinner vei_spinner, parc_spinner;
    Intent recebe;
    String entrada1, entrada2, taxajuros1, taxajuros2, nparc, carro, nome;
    ArrayAdapter vei_adapter, parc_adapter;
    Button simular, ver, fechar;
    EditText valor_veiculo_text, valor_entrada_text, valor_parcela_text;
    double porcetagem, valor_veiculo, valor_entrada, juros, entrada_param1, entrada_param2, juros_param1, juros_param2, valor_parcela, valor_restante;
    Context context = this;
    Intent volta, acessar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simulacao);

        valor_veiculo_text = (EditText) findViewById(R.id.valor_veiculo);
        valor_entrada_text = (EditText) findViewById(R.id.valor_entrada);
        valor_parcela_text = (EditText) findViewById(R.id.valor_parcela);

        vei_spinner = (Spinner) findViewById(R.id.veiculo_spinner);
        parc_spinner = (Spinner) findViewById(R.id.parcela_spinner);

        simular = (Button) findViewById(R.id.simular);
        ver = (Button) findViewById(R.id.ver);
        fechar = (Button) findViewById(R.id.fechar);

        vei_adapter = retornaadapter(R.array.veiculos_array);
        vei_spinner.setAdapter(vei_adapter);

        parc_adapter = retornaadapter(R.array.parcela_array);
        parc_spinner.setAdapter(parc_adapter);

        recebe = getIntent();

        entrada1 = recebe.getStringExtra("entrada1");
        entrada2 = recebe.getStringExtra("entrada2");
        taxajuros1 = recebe.getStringExtra("taxajuros1");
        taxajuros2 = recebe.getStringExtra("taxajuros2");


        entrada_param1 = convert_double(entrada1);
        entrada_param2 = convert_double(entrada2);
        juros_param1 = convert_double(taxajuros1);
        juros_param2 = convert_double(taxajuros2);


        if (porcetagem<=entrada_param1){
            juros = juros_param1;
        }else if (porcetagem>=entrada_param2){
            juros = juros_param2;
        }

        valor_parcela_text.setEnabled(false);

        simular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                valor_veiculo = convert_double(valor_veiculo_text.getText().toString());
                valor_entrada = convert_double(valor_entrada_text.getText().toString());

                porcetagem = ((valor_entrada * 100)/valor_veiculo);
                valor_restante = valor_veiculo-valor_entrada;


                nparc = parc_spinner.getSelectedItem().toString();

                if (nparc.equals("12")){
                    calcula_parcela(12);

                }else if (nparc.equals("24")){
                    calcula_parcela(24);

                }else if (nparc.equals("36")){
                    calcula_parcela(36);

                }else if (nparc.equals("60")){
                    calcula_parcela(60);
                }
            }
        });


        fechar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                volta = retornait(menu.class);
                startActivity(volta);

            }
        });

        ver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                carro = vei_spinner.getSelectedItem().toString();
                if (carro.equals("Palio")){
                    nome = "palio-2017/index.html";
                    acessar_carro_web(nome);

                } else if (carro.equals("Uno")){
                    nome = "uno.html";
                    acessar_carro_web(nome);

                }else if (carro.equals("Punto")){
                    nome = "novo-punto-2017/index.html";
                    acessar_carro_web(nome);

                }else if (carro.equals("Toro")){
                    nome ="toro.html";
                    acessar_carro_web(nome);
                }
            }
        });




    }

    private ArrayAdapter retornaadapter(int array){
        ArrayAdapter adapter;
        adapter = ArrayAdapter.createFromResource(this, array, android.R.layout.simple_spinner_item);
        return adapter;
    }
    private double convert_double(String t){
        return Double.parseDouble(t);
    }
    private void calcula_parcela(double p){
        valor_parcela = (valor_restante/p);
        valor_parcela = valor_parcela +((valor_parcela*juros)/100);
        String valor = String.valueOf(valor_parcela);
        valor_parcela_text.setText(valor);

    }
    private Intent retornait(Class classe ){
        Intent it = new Intent(context, classe);
        return it;
    }
    private void acessar_carro_web(String endereco){
        acessar = new Intent(Intent.ACTION_VIEW).setData(Uri.parse("http://www.fiat.com.br/carros/"+endereco));
        startActivity(acessar);
    }
}
