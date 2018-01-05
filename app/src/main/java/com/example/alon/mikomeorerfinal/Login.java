package com.example.alon.mikomeorerfinal;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity implements View.OnClickListener {
    private Button reg;
    private EditText etemail,etpassword;
    private TextView tvsignup;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        reg=(Button)findViewById(R.id.btn_login);
        etemail=(EditText)findViewById(R.id.et_emailL);
        etpassword=(EditText)findViewById(R.id.et_passwordL);
        tvsignup=(TextView)findViewById(R.id.textView_sign);
        reg.setOnClickListener(this);
        tvsignup.setOnClickListener(this);
        progressDialog=new ProgressDialog(this);
        firebaseAuth=FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser()!=null)
        {
            finish();
            startActivity(new Intent(getApplicationContext(),Menu.class));
        }
    }

    private void userLogin(){
        String email=etemail.getText().toString().trim();
        String password=etpassword.getText().toString().trim();
        if (TextUtils.isEmpty(email))
        {
            Toast.makeText(this, "Please Enter Email", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(password))
        {
            Toast.makeText(this, "Please Enter Password", Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.setMessage("Registering User...");
        progressDialog.show();
        firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressDialog.dismiss();
                if (task.isSuccessful())
                {
                    finish();
                    startActivity(new Intent(getApplicationContext(),Menu.class));
                }
            }
        });
    }


    @Override
    public void onClick(View view) {
        if (view==reg)
        {
            userLogin();
        }
        if (view==tvsignup)
        {
            finish();
            startActivity(new Intent(this, Register.class));
        }
    }
}
