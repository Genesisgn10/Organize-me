package com.example.organize_me.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

public class CadastroActivity extends AppCompatActivity {

    private EditText campoNome, campoEmail, campoSenha;
    private Button botaoCadastrar;
    private FirebaseAuth autenticacao;
    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_cadastro);

        //Conf inicial
        inicializarComponentes();

        botaoCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validarCampos();

            }
        });

    }

    private void inicializarComponentes() {

        campoNome = findViewById(R.id.editTextCadastroNome);
        campoEmail = findViewById(R.id.editTextCadastroEmail);
        campoSenha = findViewById(R.id.editTextCadastroSenha);

        botaoCadastrar = findViewById(R.id.buttonCadastrar);
    }

    private void validarCampos(){

        String nome = campoNome.getText().toString();
        String email = campoEmail.getText().toString();
        String senha = campoSenha.getText().toString();

        if(nome.isEmpty()){
            campoNome.setError("Campo obrigatório");
            campoNome.setFocusable(true);
            campoNome.requestFocus();
        }else{
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
                    usuario.setNome(nome);
                    cadastrarUsuario();
                }
            }
        }

    }

    private void cadastrarUsuario(){

        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        autenticacao.createUserWithEmailAndPassword(
          usuario.getEmail(), usuario.getNome()
        ).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(CadastroActivity.this,
                            "Sucesso ao cadastrar usuário",
                            Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(CadastroActivity.this,
                            "Erro ao cadastrar usuário",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

}
