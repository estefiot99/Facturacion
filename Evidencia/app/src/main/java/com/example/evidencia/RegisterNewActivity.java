package com.example.evidencia;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegisterNewActivity extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference reasonsRef = database.getReference("reasons");

    EditText aliasEt, nombreEt, direccionEt, emailEt, telefonoEt, rfcEt;

    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_new);

        user = getIntent().getParcelableExtra("USER");

        aliasEt = (EditText) findViewById(R.id.newAliasEt);
        nombreEt = (EditText) findViewById(R.id.newNameEt);
        direccionEt = (EditText) findViewById(R.id.newAddressEt);
        emailEt = (EditText) findViewById(R.id.newEmailEt);
        telefonoEt = (EditText) findViewById(R.id.newPhoneEt);
        rfcEt = (EditText) findViewById(R.id.newRfcEt);

        ToggleButton fisToggle = (ToggleButton) findViewById(R.id.fisToggle);
        ToggleButton morToggle = (ToggleButton) findViewById(R.id.morToggle);

        Button newRegisterBtn = (Button) findViewById(R.id.newRegisterBtn);
        ImageView returnImg2 = (ImageView) findViewById(R.id.returnImg2);
        ImageView accountImg = (ImageView) findViewById(R.id.accountImg3);

        fisToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    morToggle.setChecked(false);
                    morToggle.setTextColor(Color.parseColor("#000000"));
                } else {
                    morToggle.setChecked(true);
                    morToggle.setTextColor(Color.parseColor("#ffffff"));
                }
            }
        });

        morToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    fisToggle.setChecked(false);
                    fisToggle.setTextColor(Color.parseColor("#000000"));
                } else {
                    fisToggle.setChecked(true);
                    fisToggle.setTextColor(Color.parseColor("#ffffff"));
                }
            }
        });

        newRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(aliasEt.getText().length() <= 3 || nombreEt.getText().length() <= 3 || direccionEt.getText().length() <= 3 || emailEt.getText().length() <= 3 || telefonoEt.getText().length() <= 3 || rfcEt.getText().length() <= 3){
                    Toast.makeText(RegisterNewActivity.this, "Asegurate de llenar todos los datos correctamente", Toast.LENGTH_SHORT).show();
                }else{
                    String encodedEmail = encodeEmail(user.email);
                    String key = reasonsRef.child(encodedEmail).push().getKey();

                    if (fisToggle.isChecked()) {
                        reasonsRef.child(encodedEmail).child(key).child("persona").setValue("fisica");
                    } else {
                        reasonsRef.child(encodedEmail).child(key).child("persona").setValue("moral");
                    }

                    reasonsRef.child(encodedEmail).child(key).child("alias").setValue(String.valueOf(aliasEt.getText()));
                    reasonsRef.child(encodedEmail).child(key).child("nombre").setValue(String.valueOf(nombreEt.getText()));
                    reasonsRef.child(encodedEmail).child(key).child("direccion").setValue(String.valueOf(direccionEt.getText()));
                    reasonsRef.child(encodedEmail).child(key).child("email").setValue(String.valueOf(emailEt.getText()));
                    reasonsRef.child(encodedEmail).child(key).child("telefono").setValue(String.valueOf(telefonoEt.getText()));
                    reasonsRef.child(encodedEmail).child(key).child("rfc").setValue(String.valueOf(rfcEt.getText()));

                    goToList();
                }
            }
        });

        returnImg2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToList();
            }
        });


    }

    static String encodeEmail(String email) {
        return email.replace(".", ",");
    }

    public void goToList(){
        Intent intent = new Intent(RegisterNewActivity.this, ListActivity.class);
        intent.putExtra("USER", user);

        startActivity(intent);
    }
}