package com.example.alon.mikomeorerfinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Menu extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth firebaseAuth;
    private TextView textViewUserEmail;
    private Button buttonLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        textViewUserEmail=(TextView)findViewById(R.id.tvusermail);
        buttonLogout=(Button)findViewById(R.id.button_logout);
        buttonLogout.setOnClickListener(this);
        firebaseAuth=FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser()==null)
        {
            finish();
            startActivity(new Intent(this, Login.class));
        }
        FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();
        textViewUserEmail.setText("Welcome " + firebaseUser.getEmail());
    }

    @Override
    public void onClick(View view) {
        if (view==buttonLogout)
        {
            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(this, Login.class));
        }

    }
}
