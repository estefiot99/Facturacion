package com.example.evidencia;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

public class MainActivity extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference usersRef = database.getReference("users");

    EditText emailEt, passwordEt;
    String email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView logo = (ImageView) findViewById(R.id.logo);
        emailEt = (EditText) findViewById(R.id.emailEt);
        passwordEt = (EditText) findViewById(R.id.passwordEt) ;
        Button loginBtn = (Button) findViewById(R.id.loginBtn);

        try
        {
            InputStream ims = getAssets().open("logo.png");
            Drawable d = Drawable.createFromStream(ims, null);
            logo.setImageDrawable(d);
            ims.close();
        }
        catch(IOException ex)
        {
            return;
        }

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                email = String.valueOf(emailEt.getText());
                password = String.valueOf(passwordEt.getText());

                checkUser(email, password);

            }

            private void checkUser(String email, String password) {

                if(email.length() > 1){

                    String encodedEmail = encodeEmail(email);

                    DatabaseReference userRef = usersRef.child(encodedEmail);

                    userRef.addListenerForSingleValueEvent(new ValueEventListener() {

                        @Override
                        public void onDataChange(DataSnapshot userSnapshot) {
                            if(!userSnapshot.exists()){

                                Toast.makeText(MainActivity.this, "No hay ningún usuario con ese email", Toast.LENGTH_SHORT).show();
                            }else{
                                if(password.length() > 1){

                                    if(password.equals(String.valueOf(userSnapshot.child("password").getValue()))){
                                        User user = new User(String.valueOf(userSnapshot.child("email").getValue()), String.valueOf(userSnapshot.child("name").getValue()), String.valueOf(userSnapshot.child("password").getValue()));
                                        login(user);
                                    }else{
                                        Toast.makeText(MainActivity.this, "Contraseña incorrecta", Toast.LENGTH_SHORT).show();
                                    }
                                }else{
                                    Toast.makeText(MainActivity.this, "No olvides ingresar tu contraseña", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            Log.w(TAG, "onCancelled", databaseError.toException());
                        }
                    });
                }else{
                    Toast.makeText(MainActivity.this, "No olvides ingresar tu email", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    static String encodeEmail(String email) {
        return email.replace(".", ",");
    }

    static String decodeEmail(String email) {
        return email.replace(",", ".");
    }

    public void login(User user){
        Intent intent = new Intent(MainActivity.this, MenuActivity.class);
        intent.putExtra("USER", user);

        startActivity(intent);
    }


}