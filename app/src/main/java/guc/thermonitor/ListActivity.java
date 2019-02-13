package guc.thermonitor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

public class ListActivity extends AppCompatActivity {
    private static String[] list = {"SQL","JAVA","JAVA SCRIPT","C#","PYTHON","C++"};
    private ListView listview ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        listview = findViewById(R.id.listView);
        ListAdapter adapter = new CustomAdaptor(getApplicationContext(),list);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(),DeviceDetailActivity.class);
                String onclick = String.valueOf(parent.getItemAtPosition(position));
                intent.putExtra("message", onclick);
                startActivity(intent);
            }
        });
    }

}
