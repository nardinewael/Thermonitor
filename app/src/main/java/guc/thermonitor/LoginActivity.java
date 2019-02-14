package guc.thermonitor;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
   private EditText email;
   private EditText passw;
   private Button login;
   private Button regbutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email = findViewById(R.id.etmail);
        passw = findViewById(R.id.etpw);
        login = findViewById(R.id.loginBtn);
        regbutton = findViewById(R.id.buttonreg);
        mAuth = FirebaseAuth.getInstance();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginMethod();

            }
        });
        regbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),registrationActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    private void loginMethod(){
        String mail = email.getText().toString();
        String password = passw.getText().toString();
        if(mail.isEmpty()){
            email.setError("enter a correct email");
            email.requestFocus();
        }
        else if(password.isEmpty()){
            passw.setError("enter a correct password");
            passw.requestFocus();
        }
        mAuth.signInWithEmailAndPassword(mail,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Intent intent = new Intent(getApplicationContext(),ListActivity.class);
                    startActivity(intent);
                    finish();
                }
                else
                    Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }
    @Override
    protected void onStart(){
        super.onStart();
        if(mAuth.getCurrentUser()!=null){
            Intent intent = new Intent(getApplicationContext(),ListActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
