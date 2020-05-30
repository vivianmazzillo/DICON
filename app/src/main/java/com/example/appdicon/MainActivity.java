package com.example.appdicon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private EditText emailEditText;
    private EditText passEditText;
    private EditText rePassEditText;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emailEditText = findViewById(R.id.emailEditText);
        passEditText = findViewById(R.id.passEditText);
        rePassEditText = findViewById(R.id.rePassEditText);

        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    private void updateUI(FirebaseUser currentUser) {
        Log.i("User;","+currentUser");
    }

    public void createUserWithEmailAndPassword(String email,String password){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("ÉXITO", "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                            // enviar a la pantalla correspondiente
                            startActivity(new Intent(MainActivity.this,MenuActivity.class));
                            finish();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("ERROR", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Error: No se pudo hacer el registro",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                        // ...
                    }
                });
    }

    public void buttonPress(View view){

        String email = emailEditText.getText().toString();
        String pass = passEditText.getText().toString();
        String rePass = rePassEditText.getText().toString();

        if (!email.isEmpty()&&!pass.isEmpty()&&!rePass.isEmpty()){

            if (pass.equals(rePass)){
                if(pass.length()>5){

                    createUserWithEmailAndPassword(email,pass);

                }else
                    Toast.makeText(this, "La contraseña debe tener mínimo 6 caracteres", Toast.LENGTH_SHORT).show();


            }else
                Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();


        }else
            Toast.makeText(this, "Por favor completar todos los campos", Toast.LENGTH_SHORT).show();

    }

    public void yaTengoCuentaButton(View view){

        startActivity(new Intent(this,LoginActivity.class));
        finish();
    }

}
