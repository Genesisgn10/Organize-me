package com.example.organize_me.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.organize_me.R;
import com.example.organize_me.helper.DateUtil;

import com.example.organize_me.model.Movimentacao;
import com.google.android.material.textfield.TextInputEditText;

public class DespesasActivity extends AppCompatActivity {

    private TextInputEditText campoData, campoCategoria, campoDescricao;
    private EditText campoValor;
    private Movimentacao movimentacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_despesas);

        //conf iniciais
        inicializarComponentes();

        //preenche o campo data com a data atual
        campoData.setText( DateUtil.dataAtual() );
    }



    private void inicializarComponentes(){
        campoData = findViewById(R.id.editData);
        campoCategoria = findViewById(R.id.editCategoria);
        campoDescricao = findViewById(R.id.editDescricao);
        campoValor = findViewById(R.id.editValor);
    }

    public void salvarDespesas(View view){

        movimentacao = new Movimentacao();

        String data = campoData.getText().toString();

        movimentacao.setValor( Double.parseDouble(campoValor.getText().toString()) );
        movimentacao.setCategoria( campoCategoria.getText().toString() );
        movimentacao.setDescricao( campoDescricao.getText().toString() );
        movimentacao.setData( data );
        movimentacao.setTipo( "d" );
        movimentacao.salvar( data );
    }

}
