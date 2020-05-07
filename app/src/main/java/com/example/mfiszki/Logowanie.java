package com.example.mfiszki;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Logowanie extends AppCompatActivity {
    EditText login, haslo;
    Button zaloguj;
    TextView rejestracja;
    ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logowanie);
        getSupportActionBar().hide();
        firebaseAuth = FirebaseAuth.getInstance();
        login = (EditText) findViewById(R.id.login);
        if(firebaseAuth.getCurrentUser()!=null)
        {
            finish();
            Intent intent = new Intent(Logowanie.this,kategorie.class);
            startActivity(intent);
        }
        progressDialog = new ProgressDialog(this);
        haslo = (EditText) findViewById(R.id.haslo);
        zaloguj = (Button) findViewById(R.id.przycisklogowania);
        rejestracja = (TextView) findViewById(R.id.bezkonta);
        rejestracja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(Logowanie.this,Rejestracja.class);
                startActivity(intent);
            }
        });
        zaloguj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLogin();
            }
        });
    }

    private void userLogin() {
       String email = login.getText().toString().trim();
       String haslowpis = haslo.getText().toString().trim();
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(),"pusty login",Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(haslowpis)) {
            Toast.makeText(getApplicationContext(),"pusty haslo",Toast.LENGTH_LONG).show();
            return;
        }
        progressDialog.setMessage("Logowanie w toku...");
        progressDialog.show();
        firebaseAuth.signInWithEmailAndPassword(email,haslowpis).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressDialog.dismiss();
                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"Logowanie powiodło się",Toast.LENGTH_LONG).show();
                    finish();
                    Intent intent = new Intent(Logowanie.this,kategorie.class);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Logowanie nie powiodło się, podałeś złe dane lub nie masz konta",Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}
