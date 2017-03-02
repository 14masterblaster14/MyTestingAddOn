package com.example.a405floatinglabeledittext;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextInputLayout inputLayoutName, inputLayoutEmail, inputLayoutPswd;
    private EditText editTextName, editTextEmail, editTextPswd;
    private Button buttonSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intializeWidgets();
    }

    private void intializeWidgets() {

        inputLayoutName = (TextInputLayout) findViewById(R.id.InputLayoutFullName);
        inputLayoutEmail = (TextInputLayout) findViewById(R.id.InputLayoutEmail);
        inputLayoutPswd = (TextInputLayout) findViewById(R.id.InputLayoutPswd);

        editTextName = (EditText) findViewById(R.id.FullName);
        editTextEmail = (EditText) findViewById(R.id.Email);
        editTextPswd = (EditText) findViewById(R.id.Pswd);

        buttonSignUp = (Button) findViewById(R.id.SignUp);
        buttonSignUp.setOnClickListener(this::signUp);
    }

    private void signUp(View view) {

        boolean isValid = true;

        if (editTextName.getText().toString().isEmpty()) {
            inputLayoutName.setError(getString(R.string.Username_validation_msg));
            isValid = false;
        } else {
            inputLayoutName.setErrorEnabled(false);
        }

        if (editTextEmail.getText().toString().isEmpty()) {
            inputLayoutName.setError(getString(R.string.Email_validation_msg));
            isValid = false;
        } else {
            inputLayoutEmail.setErrorEnabled(false);
        }

        if (editTextPswd.getText().toString().trim().length() < 8) {
            inputLayoutPswd.setError(getString(R.string.Pswd_validation_msg));
            isValid = false;
        } else {
            inputLayoutPswd.setErrorEnabled(false);
        }

        if (isValid) {
            Toast.makeText(MainActivity.this, R.string.signup_success, Toast.LENGTH_SHORT).show();
        }
    }
}
