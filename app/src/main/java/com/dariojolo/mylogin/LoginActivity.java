package com.dariojolo.mylogin;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.dariojolo.mylogin.utils.utils;

public class LoginActivity extends AppCompatActivity {

    private SharedPreferences prefs;

    private EditText editTextMail;
    private EditText editTextPass;
    private Switch switchRemember;
    private Button btnLogin;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        bindUI();

        prefs = getSharedPreferences("Preferences", Context.MODE_PRIVATE);

        setCredentialsIfExists();



        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editTextMail.getText().toString();
                String pass = editTextPass.getText().toString();
                if (login(email,pass)){
                    goToMain();
                    saveOnPreferences(email,pass);
                };
            }
        });
    }

    private void setCredentialsIfExists() {
        String eMail = utils.getUserMailPrefs(prefs);
        String pass = utils.getUserPassPrefs(prefs);
        if (!TextUtils.isEmpty(eMail) && !TextUtils.isEmpty(pass)){
            editTextMail.setText(eMail);
            editTextPass.setText(pass);
        }
    }

    private boolean login(String eMail, String password){
        if (!isValidEMail(eMail)){
            Toast.makeText(this, "Email is not valid, please try again", Toast.LENGTH_SHORT).show();
            return false;
        } else if(!isValidPassword(password)){
            Toast.makeText(this, "Password is not valid, 5 characters or more. please try again", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }
    private void bindUI(){
        editTextMail = (EditText)findViewById(R.id.editTextEmail);
        editTextPass = (EditText)findViewById(R.id.editTextPass);
        switchRemember = (Switch) findViewById(R.id.switchRemember);
        btnLogin = (Button)findViewById(R.id.btnAceptar);
    }

    public void saveOnPreferences(String email, String pass){
        if (switchRemember.isChecked()){
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("email", email);
            editor.putString("pass", pass);
            //editor.commit();
            editor.apply();

        }
    }
    private boolean isValidEMail(String email){
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
    private boolean isValidPassword(String password){
        return password.length() > 4;
    }
    public void goToMain(){
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
