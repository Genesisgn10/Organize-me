package com.example.organize_me.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.example.organize_me.R;
import com.example.organize_me.helper.DateUtil;
import com.google.android.gms.common.util.DataUtils;
import com.google.android.material.textfield.TextInputEditText;

public class DespesasActivity extends AppCompatActivity {

    private TextInputEditText campoData, campoCategoria, campoDescricao;
    private EditText campoValor;

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

}
