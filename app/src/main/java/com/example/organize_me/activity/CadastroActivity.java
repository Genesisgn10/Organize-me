package com.example.organize_me.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.organize_me.R;

public class CadastroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide(); //aqui a mágica
        setContentView(R.layout.activity_cadastro);

    }
}
