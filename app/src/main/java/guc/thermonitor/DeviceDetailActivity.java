package guc.thermonitor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DeviceDetailActivity extends AppCompatActivity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_detail);
        Bundle extras = getIntent().getExtras();
        String s = extras.getString("message");
        textView = findViewById(R.id.detail);
        if(s!=null){
            textView.setText(s);
        }

    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        finish();
    }

}
