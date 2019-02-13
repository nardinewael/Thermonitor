package guc.thermonitor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomAdaptor extends ArrayAdapter<String> {
    public CustomAdaptor (Context context , String [] items){
        super(context,R.layout.customrow,items);
    }
    @Override
    public View getView(int position , View convertView, ViewGroup parent){
        LayoutInflater inflater =LayoutInflater.from(getContext());
        View CustomView = inflater.inflate(R.layout.customrow,parent,false);
        String text = getItem(position);
        TextView textviewtext = CustomView.findViewById(R.id.listtext);
        ImageView  imageviewimage = CustomView.findViewById(R.id.listimage);
        textviewtext.setText(text);
        switch (text){
            case "SQL" :imageviewimage.setImageResource(R.drawable.sql);
            break;
            case "JAVA":imageviewimage.setImageResource(R.drawable.java);
            break;
            case "JAVA SCRIPT":imageviewimage.setImageResource(R.drawable.javasript);
            break;
            case "C#" :imageviewimage.setImageResource(R.drawable.csharp);
            break;
            case"PYTHON":imageviewimage.setImageResource(R.drawable.python);
            break;
            case "C++" :imageviewimage.setImageResource(R.drawable.cplusplus);
            break;
        }
        return CustomView;

    }
}
