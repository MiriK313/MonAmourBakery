package com.example.monamourbakery;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

public class Activity_Login extends AppCompatActivity {

    private enum STATE{
       EMAIl,PASSWORD
   }
    private MaterialTextView login_LBL_EDT;
    private EditText login_useremail_EDT;
    private EditText login_userpassword_EDT;
    private MaterialButton login_BTN_connect;
    private MaterialButton login_BTN_signup;
    private String emailInput = "";
    private String passwordInput = "";
    private FirebaseAuth mAuth;
    private DatabaseReference db;
    private Gson gson = new Gson();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__login);
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance().getReference();
        findViews();
        initViews();

    }




    private void connectClicked() {
        emailInput = login_useremail_EDT.getText().toString().trim();
        passwordInput = login_userpassword_EDT.getText().toString().trim();
        if(checkErrorInputs(emailInput,passwordInput) == false){
            Log.d("pttt","Error details -->Password or Email");
            return;
        }
        else{
            login();
        }



    }

    private boolean checkErrorInputs(String email, String password) {
        if (email.isEmpty() || password.isEmpty()){
            Toast.makeText(Activity_Login.this, "Please Fill Email/Password", Toast.LENGTH_SHORT).show();
            return false;
        }
        if( isValidEmail(email) == false ){
            Toast.makeText(Activity_Login.this, "Please Enter A Valid EMAIL!", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(isValidPassword(password) == false){
            Toast.makeText(Activity_Login.this, "Password Must be 6 digits!", Toast.LENGTH_SHORT).show();
            return false;

        }

        return true;
    }

    private boolean isValidPassword(String password){
        return TextUtils.isDigitsOnly(password) && password.length() == 6;
    }

    private boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

    private void login() {
        Log.d("pttt","Inside Login ---- LoGIN");

        mAuth.signInWithEmailAndPassword(emailInput, passwordInput).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    dataBaseConformation();

                } else {
                    Toast.makeText(Activity_Login.this, "The password or email is incorrect", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void dataBaseConformation() {
        DatabaseReference db = FirebaseDatabase.getInstance().getReference();
        FirebaseUser firebaseUser = mAuth.getCurrentUser();
        Log.d("pttt","Inside Login ---- User Id = "+firebaseUser.getUid());
        db.child("myUsers").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for( DataSnapshot dataSnapshot : snapshot.getChildren()){
                    User user = dataSnapshot.getValue(User.class);
                    if(user.getUserId().equals(firebaseUser.getUid().toString().trim())){
                        boolean isManager = false;
                        if(user.getEmail().equals("mon_A_Bakery@gmail.com"))
                            isManager = true;
                        Intent intent = new Intent(Activity_Login.this, Activity_Main.class);
                        intent.putExtra("userId", ""+firebaseUser.getUid());
                        intent.putExtra("isManager", isManager);
                        intent.putExtra("CurrentUser",gson.toJson(user));

                        startActivity(intent);
                        finish();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }


    private void initViews() {
        login_BTN_connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                connectClicked();
            }
        });
        login_BTN_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_Login.this, Activity_SignUp.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void findViews() {
        login_LBL_EDT=findViewById(R.id.login_LBL_EDT);
        login_useremail_EDT=findViewById(R.id.login_useremail_EDT);
        login_userpassword_EDT=findViewById(R.id.login_userpassword_EDT);
        login_BTN_connect=findViewById(R.id.login_BTN_connect);
        login_BTN_signup = findViewById(R.id.login_BTN_signup);

    }


}

