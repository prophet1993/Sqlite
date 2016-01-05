package com.example.prophet.sqlite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisztracioActivity extends AppCompatActivity {

    EditText username, password1, password2, teljesnev;
    Button register, vissza;
    Intent i;

    AdatbazisAdapter adatbazisadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regisztracio);

        vissza = (Button) findViewById(R.id.vissza);
        vissza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = new Intent(RegisztracioActivity.this, MainActivity.class);
                finish();
                startActivity(i);
            }
        });

        adatbazisadapter = new AdatbazisAdapter(this);
        adatbazisadapter = adatbazisadapter.open();

        username = (EditText) findViewById(R.id.usernameinput);
        password1 = (EditText) findViewById(R.id.passwordinput1);
        password2 = (EditText) findViewById(R.id.passwordinput2);
        teljesnev = (EditText) findViewById(R.id.teljesnevinput);

        register = (Button) findViewById(R.id.regisztracio);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = username.getText().toString();
                String passWord1 = password1.getText().toString();
                String passWord2 = password2.getText().toString();
                String teljesNev = teljesnev.getText().toString();
                if (userName.equals("") || passWord1.equals("") || passWord2.equals("") || teljesNev.equals("")) {
                    Toast.makeText(RegisztracioActivity.this, "Tölts ki minden mezőt!", Toast.LENGTH_LONG).show();
                    return;
                }
                if (!passWord1.equals(passWord2)) {
                    Toast.makeText(RegisztracioActivity.this, "Két jelszó nem egyezik meg!", Toast.LENGTH_LONG).show();
                    return;
                } else {
                    adatbazisadapter.insertEntry(userName, passWord1, teljesNev);
                    Toast.makeText(RegisztracioActivity.this, "Sikeres regisztrálva!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
