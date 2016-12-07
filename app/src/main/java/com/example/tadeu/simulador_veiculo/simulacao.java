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
    double porcetagem, valor_veiculo, valor_entrada, juros, entrada_param1, entrada_param2, juros_param1, juros_param2, valor_parcela, valor_restante, montante;
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


        valor_parcela_text.setEnabled(false);
        valor_veiculo_text.setEnabled(false);
        ver.setEnabled(false);

        simular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                carro = vei_spinner.getSelectedItem().toString();

                if (carro.equals("Palio")){
                    setNome("novo-palio-fire/monte-seu-carro.html?modelo=170&versao=17144Z1");
                    setValorVeiculo(40366);


                } else if (carro.equals("Uno")){
                    setNome("uno/monte-seu-carro.html?modelo=195&versao=195A4N2");
                    setValorVeiculo(45580);


                }else if (carro.equals("Punto")){
                    setNome("novo-punto/monte-seu-carro.html");
                    setValorVeiculo(53310);

                }else if (carro.equals("Toro")){
                    setNome("toro/monte-seu-carro.html");
                    setValorVeiculo(82930);
                }

                valor_veiculo = getValorVeiculo();

                valor_veiculo_text.setText(String.valueOf(valor_veiculo));

                valor_entrada = convert_double(valor_entrada_text.getText().toString());

                porcetagem = ((valor_entrada * 100)/valor_veiculo);
                valor_restante = valor_veiculo-valor_entrada;

                if (porcetagem<=entrada_param1){
                    juros = juros_param1;
                }else if (porcetagem>=entrada_param2){
                    juros = juros_param2;
                }

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
                habilita_botao_ver();


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
                acessar_carro_web(getNome());
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

        montante = valor_restante * Math.pow((1 + (juros/100)), p);
        String m = String.valueOf((montante/p));
        valor_parcela_text.setText(m);

    }
    private Intent retornait(Class classe ){
        Intent it = new Intent(context, classe);
        return it;
    }
    private void acessar_carro_web(String endereco){
        acessar = new Intent(Intent.ACTION_VIEW).setData(Uri.parse("http://www.fiat.com.br/carros/"+endereco));
        startActivity(acessar);
    }
    private void habilita_botao_ver(){
        if (!carro.equals("Selecione o carro")){
            ver.setEnabled(true);
        }
    }
    public void setNome(String n){
        this.nome = n;
    }
    public String getNome(){
        return this.nome;
    }
    public void setValorVeiculo(double v){
        this.valor_veiculo = v;
    }
    public double getValorVeiculo(){
        return this.valor_veiculo;
    }
}
