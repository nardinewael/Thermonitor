package guc.thermonitor;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class ListActivity extends AppCompatActivity {
    private static String[] list = {"SQL","JAVA","JAVA SCRIPT","C#","PYTHON","C++",};
    private ListView listview ;
    private TextView name;
    private Button logout;
    private FirebaseAuth mAuth;
    private FirebaseUser fbUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        listview = findViewById(R.id.listView);
        logout = findViewById(R.id.logout);
        name = findViewById(R.id.name);
        String user = null;
        if(savedInstanceState == null){
            Bundle extras = getIntent().getExtras();
            if(extras==null){
                user = null;
            }
            else{
                user = extras.getString("User");
            }
        }
        else{
            try{
                user = (String) savedInstanceState.getSerializable("User");
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }

        try{
            mAuth = FirebaseAuth.getInstance();
            fbUser = mAuth.getCurrentUser();
            UserProfileChangeRequest profile = new UserProfileChangeRequest.Builder().setDisplayName(user).build();
            fbUser.updateProfile(profile).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
//                        Toast.makeText(getApplicationContext(), "Profile update", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
        catch(Exception e){
            e.printStackTrace();
        }

        name.setText("Hello "+fbUser.getEmail());


        ListAdapter adapter = new CustomAdaptor(getApplicationContext(),list);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(),DeviceDetailActivity.class);
                String item = String.valueOf(parent.getItemAtPosition(position));
                intent.putExtra("message", item);
                startActivity(intent);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    @Override
    public void onStart(){
        super.onStart();
        if(fbUser == null){
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
