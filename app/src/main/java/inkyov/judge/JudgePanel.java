package inkyov.judge;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class JudgePanel extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    String IP;
    String port;
    int returnNumber;
    String returnColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button red1 = (Button) findViewById(R.id.red1);
        Button red2 = (Button) findViewById(R.id.red2);
        Button red3 = (Button) findViewById(R.id.red3);
        Button red4 = (Button) findViewById(R.id.red4);
        Button red5 = (Button) findViewById(R.id.red5);
        Button blue1 = (Button) findViewById(R.id.blue1);
        Button blue2 = (Button) findViewById(R.id.blue2);
        Button blue3 = (Button) findViewById(R.id.blue3);
        Button blue4 = (Button) findViewById(R.id.blue4);
        Button blue5 = (Button) findViewById(R.id.blue5);
        Button backButton = (Button) findViewById(R.id.backButton);



        red1.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        sendPoint("red", 1);
                    }
                }) {
                }.start();
            }
        });

        red2.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        sendPoint("red", 2);
                    }
                }) {
                }.start();
            }
        });

        red3.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        sendPoint("red", 3);
                    }
                }) {
                }.start();
            }
        });

        red4.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        sendPoint("red", 4);
                    }
                }) {
                }.start();
            }
        });

        red5.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        sendPoint("red", 5);
                    }
                }) {
                }.start();
            }
        });

        blue1.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        sendPoint("blue", 1);
                    }
                }) {
                }.start();
            }
        });

        blue2.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        sendPoint("blue", 2);
                    }
                }) {
                }.start();
            }
        });

        blue3.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        sendPoint("blue", 3);
                    }
                }) {
                }.start();
            }
        });

        blue4.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        sendPoint("blue", 4);
                    }
                }) {
                }.start();
            }
        });

        blue5.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        sendPoint("blue", 5);
                    }
                }) {
                }.start();
            }
        });

        backButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println(returnNumber);
                        System.out.println(returnColor);
                        try {
                            Socket client = new Socket(IP, Integer.parseInt(port));
                            ObjectOutputStream objectOutputStream = new ObjectOutputStream(client.getOutputStream());
                            objectOutputStream.writeObject(new Point(returnColor, -returnNumber));
                            objectOutputStream.flush();
                            client.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }){
                }.start();

            }
        });
    }

    protected void onResume(){
        super.onResume();

        sharedPreferences = getSharedPreferences("judge", MODE_PRIVATE);
        if(!sharedPreferences.contains("IP") || !sharedPreferences.contains("PORT")){
            Intent intent = new Intent(this, Settings.class);
            try{
                startActivity(intent);
            }catch (android.content.ActivityNotFoundException ex){
                Toast.makeText(getApplicationContext(), "Your activity could not be run", Toast.LENGTH_SHORT).show();
            }
        }else{
            IP = sharedPreferences.getString("IP", "127.0.0.1");
            port = sharedPreferences.getString("PORT", "8080");
        }
    }


    private void sendPoint(String player, int point){
        try {
            Socket client = new Socket(IP, Integer.parseInt(port));
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(client.getOutputStream());
            objectOutputStream.writeObject(new Point(player, point));
            objectOutputStream.flush();
            returnNumber = point;
            returnColor = player;
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, Settings.class);
            try{
                startActivity(intent);
            }catch (android.content.ActivityNotFoundException ex){
                Toast.makeText(getApplicationContext(), "Your activity could not be run", Toast.LENGTH_SHORT).show();
            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
