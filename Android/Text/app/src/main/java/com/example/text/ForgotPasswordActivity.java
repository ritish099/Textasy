package com.example.text;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.auth.FirebaseAuth;

import javax.xml.validation.Validator;

public class ForgotPasswordActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    MaterialTextView description;
    TextInputEditText email;
    AppCompatButton send_link;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        mAuth = FirebaseAuth.getInstance();
        description=findViewById(R.id.description);
        email=findViewById(R.id.email_text);
        send_link=findViewById(R.id.send_link);

        send_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validator(email.getText().toString().trim()))
                {
                    sendResetLink(email.getText().toString().trim());
                }
            }
        });
    }

    private void sendResetLink(String email_text)
    {
        mAuth.sendPasswordResetEmail(email_text)
                .addOnCompleteListener(new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if(task.isSuccessful()) {
                            Intent intent = new Intent(ForgotPasswordActivity.this,LoginActivity.class);
                            startActivity(intent);
                            Snackbar.make(send_link, "Reset Link Send", Snackbar.LENGTH_LONG).show();
                        } else {
                            Snackbar.make(send_link, "Sending Reset Link Failed", Snackbar.LENGTH_LONG).show();
                        }
                    }
                });
    }

    private boolean validator(String email_text)
    {
        if(!isValidEmail(email_text))
        {
            email.setError("Invalid Email");
            return false;
        }
        return true;
    }
    public static boolean isValidEmail(CharSequence target)
    {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }
}