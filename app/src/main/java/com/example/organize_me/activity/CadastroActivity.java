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
import com.example.organize_me.helper.Base64Custom;
import com.example.organize_me.model.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

public class CadastroActivity extends AppCompatActivity {

    private EditText campoNome, campoEmail, campoSenha, campoSenha2;
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
        campoSenha2 = findViewById(R.id.editTextCadastroSenha2);

        botaoCadastrar = findViewById(R.id.buttonCadastrar);
    }

    private void validarCampos(){

        String nome = campoNome.getText().toString();
        String email = campoEmail.getText().toString();
        String senha = campoSenha.getText().toString();
        String senha2 = campoSenha.getText().toString();


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

                    if( !senha.equals(senha2) ){
                        campoSenha2.setError("senhas não são iguais");
                        campoSenha2.setFocusable(true);
                        campoSenha2.requestFocus();
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
    }

    private void cadastrarUsuario(){

        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        autenticacao.createUserWithEmailAndPassword(
          usuario.getEmail(), usuario.getNome()
        ).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if( task.isSuccessful() ){

                    String idUsuario = Base64Custom.codificarBase64( usuario.getEmail() );
                    usuario.setId( idUsuario );
                    usuario.salvar();

                    Toast.makeText(CadastroActivity.this,
                            "Sucesso ao cadastrar usuário",
                            Toast.LENGTH_SHORT).show();
                    finish();
                }else{

                    String excecao = "";

                    try {

                        throw task.getException();

                    }catch( FirebaseAuthWeakPasswordException e){
                        excecao = "Digite uma senha mais forte!";
                    }catch ( FirebaseAuthInvalidCredentialsException e){
                        excecao = "Por favor, digite um e-mail válido!";
                    }catch ( FirebaseAuthUserCollisionException e){
                        excecao = "Esse e-mail já está cadastrada!";
                    }catch ( Exception e ){
                        excecao = "Erro ao cadastrar usuário: " + e.getMessage();
                        e.printStackTrace();
                    }

                    Toast.makeText(CadastroActivity.this,
                            excecao,
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
