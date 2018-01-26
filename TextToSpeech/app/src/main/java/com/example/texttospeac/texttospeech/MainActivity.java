package com.example.texttospeac.texttospeech;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;


public class MainActivity extends Activity {
    TextToSpeech t1;
    EditText ed1;
    Button b1;
    Button b2;
    SearchView searchView;
    ListView listView;
    ArrayList<String> list;
    ArrayAdapter<String > adapter;
    String l1="";
    String l2="";
    String l3="";
    String l4="";
    String l5="";
    String l6="";

    int num = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ed1=(EditText)findViewById(R.id.editText);
        b1=(Button)findViewById(R.id.button);
        b2=(Button)findViewById(R.id.button2);


        listView = (ListView) findViewById(R.id.lv1);


        ArrayValueAddFunction();




        class PostTask extends AsyncTask<String, Integer, String> {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();

            }

            @Override
            protected String doInBackground(String... params) {
                String url=params[0];



                for (int i = 0; i <= 100; i += 5) {
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    publishProgress(i);
                }
                return "All Done!";
            }

            @Override
            protected void onProgressUpdate(Integer... values) {
                super.onProgressUpdate(values);

            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);

            }
        }



        t1=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    t1.setLanguage(Locale.UK);
                }
            }
        });


        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = ed1.getText().toString();
                Uri uri = Uri.parse("http://www.google.com/#q="+url);
                new PostTask().execute("http://www.google.com/#q="+url);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);





            }
        });






        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayValueAddFunction();
                String toSpeak = ed1.getText().toString();
                Toast.makeText(getApplicationContext(), toSpeak,Toast.LENGTH_SHORT).show();
                t1.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
            }
        });
    }

    private void ArrayValueAddFunction() {



        if(num == 2 ){ l1 = ed1.getText().toString();}
        else if(num == 3 ){l2 = ed1.getText().toString();}
        else if(num == 4 ){l3 = ed1.getText().toString();}
        else if(num == 5) {l4 = ed1.getText().toString();}
        else if(num == 6){l5 = ed1.getText().toString();}
        else if(num == 7){l6 = ed1.getText().toString();num=1;}


        else{}

        num++;


        list = new ArrayList<>();

        list.add(" "+l1);
        list.add(" "+l2);
        list.add(" "+l3);
        list.add(" "+l4);
        list.add(" "+l5);
        list.add(" "+l6);


        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,list);
        listView.setAdapter(adapter);






    }


}