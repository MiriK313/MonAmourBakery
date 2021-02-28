package com.example.monamourbakery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.DatabaseUtils;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Activity_SignUp extends AppCompatActivity {

    private MaterialTextView signUp_LBL_EDT;
    private EditText signUp_EDT_userFirstName;
    private EditText signUp_EDT_userLastName;
    private EditText signUp_EDT_userEmail;
    private EditText signUp_EDT_userPassword;
    private MaterialButton signUp_BTN_sign;
    private String firstNameInput = "";
    private String lastNameInput = "";
    private String emailInput = "";
    private String passwordInput = "";
    private FirebaseAuth mAuth;
    private DatabaseReference db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__sign_up);
        mAuth = FirebaseAuth.getInstance();

        findViews();
        initViews();

    }

    private void initViews() {
        signUp_BTN_sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp();
            }
        });
    }

    private void signUp() {
        firstNameInput = signUp_EDT_userFirstName.getText().toString().trim();
        lastNameInput = signUp_EDT_userLastName.getText().toString().trim();
        emailInput = signUp_EDT_userEmail.getText().toString().trim();
        passwordInput = signUp_EDT_userPassword.getText().toString().trim();
        if(checkErrorInputs(emailInput,passwordInput,firstNameInput,lastNameInput) == false){
            return;
        }
        else{
            mAuth.createUserWithEmailAndPassword(emailInput,passwordInput)
                    .addOnCompleteListener(this,(task) ->{
                        if(task.isSuccessful()){
                            FirebaseUser firebaseUser = mAuth.getCurrentUser();
                            String userId = firebaseUser.getUid();
                            db = FirebaseDatabase.getInstance().getReference("myUsers");
                            User user = new User(userId, emailInput, passwordInput, firstNameInput, lastNameInput);
                            db.child(userId).setValue(user);
                            Log.d("pttt","Inside SignUp ---- User Id = "+userId);
                            Intent intent = new Intent(Activity_SignUp.this, Activity_Login.class);
                            startActivity(intent);
                            finish();
                        }
                    });
        }

    }

    private boolean checkErrorInputs(String email, String password,String firstName,String lastName) {
        if (email.isEmpty() || password.isEmpty() || firstName.isEmpty() || lastName.isEmpty()){
            Toast.makeText(Activity_SignUp.this, "Please Fill Email/Password/Full Name", Toast.LENGTH_SHORT).show();
            return false;
        }
        if( isValidEmail(email) == false ){
            Toast.makeText(Activity_SignUp.this, "Please Enter A Valid EMAIL!", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(isValidPassword(password) == false){
            Toast.makeText(Activity_SignUp.this, "Password Must be 6 digits!", Toast.LENGTH_SHORT).show();
            return false;

        }
        if(isValidName(firstName) == false || isValidName(lastName)== false){
            Toast.makeText(Activity_SignUp.this, "Name must be at least 2 letters / ONLY LETTERS!", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    private boolean isValidName(String Name) {
        if(Name.length() <2)
            return false;
        else{
            return Name.matches("[a-zA-Z]+");
        }
    }

    private boolean isValidPassword(String password){
        return TextUtils.isDigitsOnly(password) && password.length() == 6;
    }

    private boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

    private void findViews() {
        signUp_EDT_userFirstName =findViewById(R.id.signUp_EDT_userFirstName);
        signUp_EDT_userLastName = findViewById(R.id.signUp_EDT_userLastName);
        signUp_EDT_userEmail = findViewById(R.id.signUp_EDT_userEmail);
        signUp_EDT_userPassword = findViewById(R.id.signUp_EDT_userPassword);
        signUp_BTN_sign = findViewById(R.id.signUp_BTN_sign);
        signUp_LBL_EDT = findViewById(R.id.signUp_LBL_EDT);
    }

}