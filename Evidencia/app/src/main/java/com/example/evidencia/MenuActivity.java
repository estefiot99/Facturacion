package com.example.evidencia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;

public class MenuActivity extends AppCompatActivity {

    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        user = getIntent().getParcelableExtra("USER");

        TextView welcomeUser = (TextView) findViewById(R.id.welcomerUser);
        ImageView accountImg = (ImageView) findViewById(R.id.accountImg);
        Button generateBtn = (Button) findViewById(R.id.generateBtn);
        Button registerBtn = (Button) findViewById(R.id.registerBtn);

        welcomeUser.setText("Bienvenido " + user.name);

        try
        {
            InputStream ims = getAssets().open("user.png");
            Drawable d = Drawable.createFromStream(ims, null);
            accountImg.setImageDrawable(d);
            ims.close();
        }
        catch(
                IOException ex)
        {
            return;
        }

        generateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ListActivity.class);
                intent.putExtra("USER", user);
                startActivity(intent);
            }
        });

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), RegisterNewActivity.class);
                intent.putExtra("USER", user);
                startActivity(intent);
            }
        });

        accountImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), AccountActivity.class);
                intent.putExtra("USER", user);
                startActivity(intent);
            }
        });

    }
}