package com.example.text;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity
{
    private FirebaseAuth mAuth;
    TextInputEditText email,password,confirm_password;
    AppCompatButton signup;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
        email=findViewById(R.id.email_text);
        password=findViewById(R.id.password_text);
        confirm_password=findViewById(R.id.confirm_password_text);
        signup=findViewById(R.id.signup);

        signup.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                if(validator(email.getText().toString().trim(),password.getText().toString().trim(),confirm_password.getText().toString().trim()))
                {
                    signUpWithEmail(email.getText().toString().trim(),password.getText().toString().trim());
                }
            }
        });
    }

    private void signUpWithEmail(String email, String password)
    {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful())
                        {
                            Intent intent = new Intent(RegisterActivity.this,MainActivity.class);
                            startActivity(intent);
                            Snackbar.make(signup, "Successful SignUp", Snackbar.LENGTH_LONG).show();
                        } else {
                            Snackbar.make(signup, "SignUp Failed", Snackbar.LENGTH_LONG).show();
                        }
                    }
                });
    }

    private boolean validator(String email_text, String password_text, String confirm_password_text)
    {
        if(!isValidEmail(email_text))
        {
            email.setError("Invalid Email");
            return false;
        }
        if(!pasword_validator(password_text,confirm_password_text))
        {
            return false;
        }
        return  true;
    }

    private boolean pasword_validator(String password_text, String confirm_password_text)
    {
        if(password_text.length()<6)
        {
            password.setError("Password should be atleast 6 character");
            return false;
        }
        if(!password_text.equals(confirm_password_text))
        {
            confirm_password.setError("Password doest match");
            return false;
        }
        return  true;
    }

    public static boolean isValidEmail(CharSequence target)
    {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }
}