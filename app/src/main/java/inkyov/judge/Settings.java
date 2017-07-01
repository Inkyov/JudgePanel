package inkyov.judge;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Inkyov on 11/4/2016.
 */
public class Settings extends AppCompatActivity {
    SharedPreferences sharedPreferences;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        sharedPreferences = getSharedPreferences("judge", MODE_PRIVATE);
        if(sharedPreferences.contains("IP")){
            EditText ipText = (EditText) findViewById(R.id.ipText);
            ipText.setText(sharedPreferences.getString("IP", ""));
        }
        if(sharedPreferences.contains("PORT")){
            EditText portText = (EditText) findViewById(R.id.portText);
            portText.setText(sharedPreferences.getString("PORT", ""));
        }

    }

    public void Save(View view){
        SharedPreferences.Editor edit = sharedPreferences.edit();
        EditText ip = (EditText) findViewById(R.id.ipText);
        EditText port = (EditText) findViewById(R.id.portText);
        if(ip.getText().toString().trim().length()>0 && port.getText().toString().trim().length()>0){
            edit.putString("IP", ip.getText().toString());
            edit.putString("PORT", port.getText().toString());
            edit.commit();
            finish();
        }else{
        Toast.makeText(getApplicationContext(), "No empty fields are allowed", Toast.LENGTH_SHORT).show();
        }
    }
}
