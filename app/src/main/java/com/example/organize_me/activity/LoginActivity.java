package com.example.organize_me.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.organize_me.R;
import com.example.organize_me.config.ConfiguracaoFirebase;
import com.example.organize_me.model.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;

public class LoginActivity extends AppCompatActivity {

    private EditText campoEmail, campoSenha;
    private Button botaoEntrar;
    private FirebaseAuth autenticacao;
    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        //Conf inicial
        inicializarComponentes();
        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();

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
                usuario = new Usuario();
                usuario.setEmail(email);
                usuario.setSenha(senha);
                loginUsuario();
            }
        }
    }

    private void loginUsuario(){
        autenticacao.signInWithEmailAndPassword(
            usuario.getEmail(), usuario.getSenha()
        ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if( task.isSuccessful() ){

                    abrirTelaPrincipal();

                }else{

                    String excecao = "";

                    try {

                        throw task.getException();

                    }catch ( FirebaseAuthInvalidUserException e){
                        excecao = "Não encontramos esse usuário no nosso sistema.";
                    }catch ( FirebaseAuthInvalidCredentialsException e){
                        excecao = "E-mail e senha não correspondem a um usuário cadastrado!";
                    }catch ( Exception e ){
                        excecao = "Erro ao logar: " + e.getMessage();
                        e.printStackTrace();
                    }

                    Toast.makeText(LoginActivity.this,
                            excecao,
                            Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    private void abrirTelaPrincipal(){
        startActivity(new Intent( LoginActivity.this, PrincipalActivity.class));
        finish();
    }

}
