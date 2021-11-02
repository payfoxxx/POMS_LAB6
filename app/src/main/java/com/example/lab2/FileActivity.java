package com.example.lab2;

import android.os.Bundle;
import android.os.Environment;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;

public class FileActivity extends AppCompatActivity {
    TextView fileText;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file);

        Button fileBtn = findViewById(R.id.show_file_btn);
        fileBtn.setOnClickListener(v->loadFile());
        fileText = findViewById(R.id.file_text);
    }

    private void loadFile(){
        /*String res;
        StringBuilder stringBuilder = new StringBuilder();
        File downloadDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File myFile = new File(downloadDir,"file.txt");
        try {
            BufferedReader reader = new BufferedReader(new FileReader(myFile));
            String line = null;
            String ls = System.getProperty("line.separator");
            while((line = reader.readLine()) != null){
                stringBuilder.append(line);
                stringBuilder.append(ls);
            }
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            reader.close();
        } catch (Exception e){
            e.printStackTrace();
        }
        res = stringBuilder.toString();
        fileText.setText(res);*/
        FileInputStream fin = null;
        try {
            fin = openFileInput("file.txt");
            byte[] bytes = new byte[fin.available()];
            fin.read(bytes);
            String text = new String(bytes);
            fileText.setText(text);
        } catch (IOException ex){
            Toast.makeText(this,ex.getMessage(),Toast.LENGTH_SHORT).show();
        }
        finally{
            try {
                if(fin!=null)
                    fin.close();
            } catch (IOException ex){
                Toast.makeText(this,ex.getMessage(),Toast.LENGTH_SHORT).show();
            }
        }
    }
}
