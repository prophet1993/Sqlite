package com.example.prophet.sqlite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button login, register, kilepes;
    AdatbazisAdapter adatbazisadapter;
    Intent i;

    EditText username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adatbazisadapter = new AdatbazisAdapter(this);
        adatbazisadapter = adatbazisadapter.open();

        login = (Button)findViewById(R.id.login);
        username = (EditText) findViewById(R.id.userinput);
        password = (EditText) findViewById(R.id.passinput);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = username.getText().toString();
                String passWord = password.getText().toString();
                String storedPassword = adatbazisadapter.getSinlgeEntry(userName);
                if(passWord.equals(storedPassword))
                {
                    Toast.makeText(MainActivity.this, "Sikeres bejelentkezve!", Toast.LENGTH_LONG).show();
                    i = new Intent(MainActivity.this, BejelentkezesActivity.class);
                    i.putExtra("username", userName);
                    startActivity(i);
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Rossz felhasználónév vagy jelszó! Sikertelen!", Toast.LENGTH_LONG).show();
                }
            }
        });


        register = (Button) findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i = new Intent(MainActivity.this, RegisztracioActivity.class);
                startActivity(i);
            }
        });

        kilepes = (Button) findViewById(R.id.kilepes);
        kilepes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moveTaskToBack(true);
                finish();
                System.exit(0);
            }
        });
    }
}
