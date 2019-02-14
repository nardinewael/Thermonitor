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

public class registrationActivity extends AppCompatActivity {
    private Button register;
    private EditText username ;
    private EditText email;
    private EditText password;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        register = findViewById(R.id.reg);
        username = findViewById(R.id.username);
        email = findViewById(R.id.regemail);
        password = findViewById(R.id.regpassword);
        mAuth = FirebaseAuth.getInstance();
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }

        });
    }
    private void registerUser(){
        final String user = username.getText().toString();
        String mail = email.getText().toString();
        String pw = password.getText().toString();
        if (user.isEmpty()){
            username.setError("enter a valid username");
            username.requestFocus();
        }
      else  if(mail.isEmpty()){
            email.setError("enter a valid email");
            email.requestFocus();
        }
        else if(pw.isEmpty()){
            password.setError("enter a valid password");
            password.requestFocus();
        }
        else {
            mAuth.createUserWithEmailAndPassword(mail,pw).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Intent intent = new Intent(getApplicationContext(), ListActivity.class);
                        Toast.makeText(getApplicationContext(),"congratulation you hace created the account",Toast.LENGTH_SHORT).show();
                        intent.putExtra("User", user);
                        startActivity(intent);
                        finish();
                    }
                    else {
                        Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_LONG).show();
                    }

                }
            });
        }
    }


}
