package com.example.zoy.firebasereal;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {
    EditText tEmail,tPassword;
    Button btnLogin,btnRegister;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        tEmail=(EditText) findViewById(R.id.tEmail);
        tPassword=(EditText) findViewById(R.id.tPassword);
        btnLogin=(Button) findViewById(R.id.btnLogin);
        btnRegister=(Button) findViewById(R.id.btnRegister);
        databaseReference=FirebaseDatabase.getInstance().getReference("Students");
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pwd= null;
                try {
                    pwd = Security.encrypt(tPassword.getText().toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                logIn(tEmail.getText().toString(),pwd);
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intphto =new Intent(getApplicationContext(),Register.class);
                startActivity(intphto);
            }
        });

    }
    private void logIn(final String id,final String password) {
    databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
          if(dataSnapshot.child(id).exists()){
              if (!id.isEmpty()){
                  User user=dataSnapshot.child(id).getValue(User.class);
                  if (user.getPassword().equals(password)){
                      Toast.makeText(Login.this,"Login Success",Toast.LENGTH_LONG).show();
                      Intent intphto =new Intent(getApplicationContext(),Register.class);
                      startActivity(intphto);
                  }else {
                      Toast.makeText(Login.this,"Password Incorrect",Toast.LENGTH_LONG).show();
                  }
              }else {
                  Toast.makeText(Login.this,"User is not register",Toast.LENGTH_LONG).show();
              }

          }else {
              Toast.makeText(Login.this,"User is not register",Toast.LENGTH_LONG).show();
          }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    });
    }
}
