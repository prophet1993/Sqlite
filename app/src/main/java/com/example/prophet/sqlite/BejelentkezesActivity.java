package com.example.prophet.sqlite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class BejelentkezesActivity extends AppCompatActivity {

    TextView a;
    Button logout;
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bejelentkezes);

        Bundle extras = getIntent().getExtras();
        String userName = extras.getString("username");
        a = (TextView) findViewById(R.id.user);
        a.setText("Üdvözöljük, " + userName + "!");

        logout = (Button) findViewById(R.id.logout); //kijelentkezés
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = new Intent(BejelentkezesActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}
