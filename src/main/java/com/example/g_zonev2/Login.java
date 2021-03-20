package com.example.g_zonev2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.g_zonev2.Dashboard.Dashboard;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {
    private Boolean exit = false;
    AppCompatButton btn,btn2,btn3;
    ImageView logo;
    TextView txt1,txt2;
    TextInputLayout Username,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        btn = findViewById(R.id.btnsignup);
        btn3 = findViewById(R.id.btnsignin);
        //hooks
        logo = findViewById(R.id.logo_image);
        txt1 = findViewById(R.id.logo_name);
        txt2 = findViewById(R.id.slogo_name);
        Username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        btn2 = findViewById(R.id.btnsignup);





        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                Intent i = new Intent(Login.this,SignUpNew.class);
                //startActivity(i);

               Pair[] pairs = new Pair[6];
                pairs[0] = new Pair<View,String>(logo,"logo_image");
                pairs[1] = new Pair<View,String>(txt1,"text1");
                pairs[2] = new Pair<View,String>(txt2,"txt2");
                pairs[3] = new Pair<View,String>(Username,"field1");
                pairs[4] = new Pair<View,String>(password,"field2");
                pairs[5] = new Pair<View,String>(btn2,"btnsignup");

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Login.this,pairs);
                    startActivity(i,options.toBundle());
                }
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                isUser();
            }
        });



    }

    private Boolean validateUsername(){
        String val= Username.getEditText().getText().toString();
        if(val.isEmpty()){
            Username.setError("Field cannot be empty");
            return false;
        }else{
            Username.setError(null);
            Username.setErrorEnabled(false);
            return true;
        }

    }

    private boolean validatePassword(){
        String val= password.getEditText().getText().toString();
        if(val.isEmpty()){
            password.setError("Field cannot be empty");
            return false;
        }else{
            password.setError(null);
            password.setErrorEnabled(false);
            return true;
        }
    }

    public void login(){
        if(validateUsername() | validatePassword()){
            return;
        }else{

            isUser();
        }
    }

    private void isUser() {

        String userEnteredUsername= Username.getEditText().getText().toString().trim();
        String userEnteredPassword= password.getEditText().getText().toString().trim();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
        Query checkUser = reference.orderByChild("email").equalTo(userEnteredUsername);

        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.exists()){
                    Username.setError(null);
                    Username.setErrorEnabled(false);
                    String passwordFromDB = snapshot.child(userEnteredUsername.replace(".",",")).child("password").getValue(String.class);

                    if(passwordFromDB.equals(userEnteredPassword)){
                        Username.setError(null);
                        Username.setErrorEnabled(false);

                        String nameFromDB = snapshot.child(userEnteredUsername.replace(".",",")).child("name").getValue(String.class);
                        String emailFromDB = snapshot.child(userEnteredUsername.replace(".",",")).child("email").getValue(String.class);

                        Intent intent = new Intent(getApplicationContext(),Dashboard.class);
                        intent.putExtra("name",nameFromDB);
                        intent.putExtra("email",emailFromDB);
                        startActivity(intent);
                    }else{
                        password.setError("Wrong password");
                        password.requestFocus();
                    }
                }else{
                    Username.setError("Username is wrong");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }


    @Override
    public void onBackPressed() {
        if (exit) {
            finish(); // finish activity
        } else {
            Toast.makeText(this, "Press Back again to Exit.",
                    Toast.LENGTH_SHORT).show();
            exit = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    exit = false;
                }
            }, 3 * 1000);

        }

    }
}
