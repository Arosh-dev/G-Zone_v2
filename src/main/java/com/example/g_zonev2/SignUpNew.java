package com.example.g_zonev2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpNew extends AppCompatActivity {

    TextInputLayout name,email,password;
    AppCompatButton signup;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sign_up_new);

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        signup = findViewById(R.id.btn_signup2);

        //button event

        signup.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("Users");
                if(!vaidatePassword() | !validateEmail() | !validateName()){
                    return;
                }
                //get values
                String fname = name.getEditText().getText().toString();
                String uemail = email.getEditText().getText().toString();
                String upass = password.getEditText().getText().toString();
                UserClass newUser = new UserClass(fname,uemail,upass,0,0,0);
                String id = newUser.convertEmail();
                reference.child(id).setValue(newUser);
            }
        });

    }

    private boolean validateName(){
        String value= name.getEditText().getText().toString();
        if(value.isEmpty()){
            name.setError("This field required");
            return false;
        }else{
            name.setError(null);
            name.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateEmail(){
        String value= email.getEditText().getText().toString();
        String emailpattern= "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if(value.isEmpty()){
            email.setError("This field required");
            return false;
        }else if(!value.matches(emailpattern)){
            email.setError("Invalid email address");
            return false;
        }else{
            email.setError(null);
            return true;
        }
    }

    private boolean vaidatePassword(){
        String value= password.getEditText().getText().toString();
        if(value.isEmpty()){
            password.setError("This field required");
            return false;
        }else{
            password.setError(null);
            return true;
        }
    }
}