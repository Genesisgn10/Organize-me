package com.example.organize_me.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.organize_me.R;

public class LoginActivity extends AppCompatActivity {

    private EditText campoEmail, campoSenha;
    private Button botaoEntrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);

        //Conf inicial
        inicializarComponentes();

        botaoEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validarCampo();
            }
        });

    }

    private void inicializarComponentes() {

        campoEmail = findViewById(R.id.editTextLoginEmail);
        campoSenha = findViewById(R.id.editTextLoginSenha);

        botaoEntrar = findViewById(R.id.buttonLogar);

    }

    private void validarCampo(){

        String email = campoEmail.getText().toString();
        String senha = campoSenha.getText().toString();

        if(email.isEmpty()){
            campoEmail.setError("Campo obrigatório");
            campoEmail.setFocusable(true);
            campoEmail.requestFocus();
        }else{
            if(senha.isEmpty()){
                campoSenha.setError("Campo obrigatório");
                campoSenha.setFocusable(true);
                campoSenha.requestFocus();
            }else{

            }
        }
    }

}
