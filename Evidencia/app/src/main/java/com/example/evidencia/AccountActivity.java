package com.example.evidencia;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AccountActivity extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference usersRef = database.getReference("users");

    User user;
    EditText nameEt, emailEt, passwordEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        user = getIntent().getParcelableExtra("USER");

        nameEt = (EditText) findViewById(R.id.nameEt);
        emailEt = (EditText) findViewById(R.id.emailEt);
        passwordEt = (EditText) findViewById(R.id.passwordEt);

        nameEt.setText(user.name);
        emailEt.setText(user.email);
        passwordEt.setText(user.password);

        ImageView returnImg = (ImageView) findViewById(R.id.returnImg3);
        Button accountBtn = (Button) findViewById(R.id.accountBtn);

        returnImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goBack();
            }
        });

        accountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(nameEt.getText().length() <= 3 || emailEt.getText().length() <= 3 || passwordEt.getText().length() <= 3){
                    Toast.makeText(AccountActivity.this, "Todos los datos deben ser mayores a 3", Toast.LENGTH_SHORT).show();
                }else{
                    String oldEncodedEmail = encodeEmail(user.email);
                    DatabaseReference oldUserRef = usersRef.child(oldEncodedEmail);
                    oldUserRef.removeValue();

                    user.setName(String.valueOf(nameEt.getText()));
                    user.setEmail(String.valueOf(emailEt.getText()));
                    user.setPassword(String.valueOf(passwordEt.getText()));

                    String newEncodedEmail = encodeEmail(user.email);
                    DatabaseReference userRef = usersRef.child(newEncodedEmail);

                    userRef.child("name").setValue(user.name);
                    userRef.child("email").setValue(user.email);
                    userRef.child("password").setValue(user.password);

                    Toast.makeText(AccountActivity.this, "Cambios actualizados", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void goBack(){
        Intent intent = new Intent(AccountActivity.this, MenuActivity.class);
        intent.putExtra("USER", user);

        startActivity(intent);
    }

    static String encodeEmail(String email) {
        return email.replace(".", ",");
    }
}