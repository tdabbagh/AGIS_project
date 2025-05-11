package com.example.agisproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Register extends AppCompatActivity {



    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseUser user = mAuth.getCurrentUser();
    String name_value, email_value, pass_value, role_value;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

    Button reg = (Button) findViewById(R.id.register);
    EditText name = (EditText) findViewById(R.id.register_name);
    EditText email = (EditText) findViewById(R.id.register_email);
    EditText password = (EditText) findViewById(R.id.register_password);
    Spinner role = (Spinner) findViewById(R.id.spinner_role);

    reg.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            if(name.getText().toString().isEmpty() ||
                    email.getText().toString().isEmpty() ||
                    password.getText().toString().isEmpty() ||
                    role.getSelectedItem().toString().isEmpty())
            {
                Toast.makeText(Register.this,"Empty fields are required!", Toast.LENGTH_LONG).show();
            }
            else
            {
                name_value = name.getText().toString();
                email_value = email.getText().toString();
                pass_value = password.getText().toString();
                role_value = role.getSelectedItem().toString();
                addUser(name_value, email_value, pass_value, role_value);

            }
            startActivity(new Intent(Register.this, Login2.class));
        }
    });
    }
    public void addUser(String user_name, String user_email, String user_pass, String user_role){
        mAuth.createUserWithEmailAndPassword(user_email,
                user_pass).addOnCompleteListener(Register.this,
                new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
                            user = mAuth.getCurrentUser();
                            Toast.makeText(Register.this,"Successful Registration" + user.getEmail()+" is now added to the AGI's chatting application",Toast.LENGTH_LONG).show();
                        }
                        else{
                            Toast.makeText(Register.this,"User already exist",Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}