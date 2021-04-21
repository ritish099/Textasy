package com.example.text;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    AppCompatButton loginButton;
    MaterialTextView forgot_password,register;
    TextInputEditText email,password;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        loginButton=findViewById(R.id.login_button);
        forgot_password=findViewById(R.id.forgot_password);
        register=findViewById(R.id.register);
        email=findViewById(R.id.email_text);
        password=findViewById(R.id.password_text);
        
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validator(email.getText().toString().trim(),password.getText().toString().trim()))
                {
                    signInWithEmail(email.getText().toString().trim(),password.getText().toString().trim());
                }
            }
        });

        forgot_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
                startActivity(intent);
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    private void signInWithEmail(String email, String password)
    {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            Snackbar.make(loginButton, "Login Successful", Snackbar.LENGTH_SHORT).show();
                        } else {
                            Snackbar.make(loginButton, "Login Failed", Snackbar.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    private boolean validator(String email_text, String password_text) 
    {
        if(!isValidEmail(email_text))
        {
            email.setError("Invalid Email");
            return false;
        }
        if(!ValidPassword(password_text))
        {
            return  false;
        }
        return true;
    }

    private boolean ValidPassword(String password_text) 
    {
        if(password_text.length()<6)
        {
            password.setError("Password should be atleast 6 character");
            return false;
        }
        return true;
    }

    public static boolean isValidEmail(CharSequence target)
    {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }
}