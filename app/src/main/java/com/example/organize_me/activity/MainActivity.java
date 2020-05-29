package com.example.organize_me.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.organize_me.PrincipalActivity;
import com.example.organize_me.R;
import com.example.organize_me.config.ConfiguracaoFirebase;
import com.google.firebase.auth.FirebaseAuth;
import com.heinrichreimersoftware.materialintro.app.IntroActivity;
import com.heinrichreimersoftware.materialintro.slide.FragmentSlide;

public class MainActivity extends IntroActivity {

    private FirebaseAuth autenticacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ///setContentView(R.layout.activity_main);

        //conf iniciais
        verificarUsuarioLogado();
        setButtonBackVisible(false);
        setButtonNextVisible(false);

        //Slides
        addSlide(new FragmentSlide.Builder()
                .background(android.R.color.holo_blue_light)
                .fragment(R.layout.intro_1)
                .canGoBackward(false)
                .build()
        );

        addSlide(new FragmentSlide.Builder()
                .background(android.R.color.holo_blue_light)
                .fragment(R.layout.intro_2)
                .build()
        );

        addSlide(new FragmentSlide.Builder()
                .background(android.R.color.holo_blue_light)
                .fragment(R.layout.intro_3)
                .build()
        );

        addSlide(new FragmentSlide.Builder()
                .background(android.R.color.holo_blue_light)
                .fragment(R.layout.intro_4)
                .build()
        );

        addSlide(new FragmentSlide.Builder()
                .background(android.R.color.holo_blue_light)
                .fragment(R.layout.intro_cadastro)
                .canGoForward(false)
                .build()
        );
    }

    @Override
    protected void onStart() {
        super.onStart();
        verificarUsuarioLogado();
    }

    public void btEntrar(View view){
        startActivity(new Intent(MainActivity.this, LoginActivity.class));
    }

    public void btCadastrar(View view){
        startActivity(new Intent(MainActivity.this, CadastroActivity.class));
    }

    private void verificarUsuarioLogado(){
        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        if ( autenticacao.getCurrentUser() != null ){
            abrirTelaPrincipal();
        }

    }

    private void abrirTelaPrincipal(){
        startActivity(new Intent( MainActivity.this, PrincipalActivity.class));
        finish();
    }

}
