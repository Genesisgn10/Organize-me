package com.example.organize_me.activity;

import android.content.Intent;
import android.os.Bundle;

import com.example.organize_me.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

public class PrincipalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

         */
    }

    public void adicionarDespesa(View view){
        startActivity(new Intent(PrincipalActivity.this, DespesasActivity.class));
    }

    public void adicionarReceita(View view){
        startActivity(new Intent(PrincipalActivity.this, ReceitasActivity.class));
    }

}
