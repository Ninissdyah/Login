package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    Button btnLogin;
    EditText emailInput, passInput;
    //sebagai variabel yang menampung kondisi awal ketika belum login
    boolean login = false;

    //membuat variabel untuk SharedPreferences
    private SharedPreferences mSharedPref;
    //memberi nama sharedP
    private final String mSharedPrefFile = "com.example.sharedpreferenceapp";
    //memberi nama key nya, 1sharedP bisa punya banyak key
    private final String LOGIN_KEY = "login-key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //me-get sharedP biar ga null dimana dgn mode private agar tidak bisa dipakai di kelas lain
        mSharedPref = getSharedPreferences(mSharedPrefFile, MODE_PRIVATE);

        btnLogin = findViewById(R.id.btn_login);
        emailInput = findViewById(R.id.email_input);
        passInput = findViewById(R.id.pass_input);
        //assign variabel login dengan me-get boolean key dan value default false
        login = mSharedPref.getBoolean(LOGIN_KEY, false);
        //kondisi dimana ketika sudah login akan otomatis menampilkan intent
        if (login) {
            Intent intent = new Intent(MainActivity.this, MainActivity2.class);
            startActivity(intent);
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //ketika btn di klik kondisi login berubah mjd true dan menampilkan intent kemudian menyimpan history login
                login = true;
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                saveLogin();
                startActivity(intent);
            }
        });
    }

    private void saveLogin(){
        //buat nge-edit value dari login yg false mjd true dan sebaliknya ini fungsi buat nge-editnya
        SharedPreferences.Editor editor = mSharedPref.edit();
        //edit boolean
        editor.putBoolean(LOGIN_KEY, login);
        //me-replace false><true dan sebaliknya
        editor.apply();
    }
}