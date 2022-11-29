package com.example.evidencia;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Map;

public class ListActivity extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference reasonsRef = database.getReference("reasons");


    User user;
    ArrayList<String> listReasons;
    RecyclerView recycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        user = getIntent().getParcelableExtra("USER");

        ImageView accountImg = (ImageView) findViewById(R.id.accountImg2);
        ImageView returnImg = (ImageView) findViewById(R.id.returnImg);
        Button registerBtn = (Button) findViewById(R.id.registerBtn);
        //LinearLayout listItem = (LinearLayout) findViewById(R.id.listItem);

        recycler = (RecyclerView) findViewById(R.id.recyclerId);
        recycler.setLayoutManager(new LinearLayoutManager(this));

        listReasons = new ArrayList<String>();

        String encodedEmail = encodeEmail(user.email);

        DatabaseReference reasonRef = reasonsRef.child(encodedEmail);
        reasonRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot reasonsSnapshot) {
                if(!reasonsSnapshot.exists()){
                    Toast.makeText(ListActivity.this, "No hay ningúna razón social", Toast.LENGTH_SHORT).show();
                }else{
                    listReasons = collectReasons((Map<String,Object>) reasonsSnapshot.getValue());
                    AdapterReasons adapterReasons = new AdapterReasons(listReasons);
                    recycler.setAdapter(adapterReasons);
                }
            }

            private ArrayList<String> collectReasons(Map<String, Object> value) {
                ArrayList<String> aliases = new ArrayList<>();

                //iterate through each user, ignoring their UID
                for (Map.Entry<String, Object> entry : value.entrySet()){

                    //Get user map
                    Map singleUser = (Map) entry.getValue();
                    //Get phone field and append to list
                    aliases.add((String) singleUser.get("alias"));
                }

                return aliases;
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "onCancelled", databaseError.toException());
            }
        });




        try
        {
            InputStream ims = getAssets().open("user.png");
            Drawable d = Drawable.createFromStream(ims, null);
            accountImg.setImageDrawable(d);
            ims.close();
        }
        catch(IOException ex)
        {
            return;
        }

        returnImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), MenuActivity.class);
                intent.putExtra("USER", user);
                startActivity(intent);
            }
        });

        /*listItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showImage();
            }
        });*/

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

    public void showImage() {
        Dialog builder = new Dialog(ListActivity.this);
        builder.requestWindowFeature(Window.FEATURE_NO_TITLE);
        builder.getWindow().setBackgroundDrawable(
                new ColorDrawable(android.graphics.Color.TRANSPARENT));
        builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                //nothing;
            }
        });

        ImageView imageView = new ImageView(this);
        try
        {
            InputStream ims = getAssets().open("qr.png");
            Drawable d = Drawable.createFromStream(ims, null);
            imageView.setImageDrawable(d);
            ims.close();
        }
        catch(IOException ex)
        {
            return;
        }
        builder.addContentView(imageView, new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        builder.show();
    }

    static String encodeEmail(String email) {
        return email.replace(".", ",");
    }
}