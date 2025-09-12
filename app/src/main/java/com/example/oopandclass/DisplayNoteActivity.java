package com.example.oopandclass;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DisplayNoteActivity extends AppCompatActivity {

    Button Button;

    Button Button1;
    EditText titleOfTextNote, contentOfTextNote;
    TextView showNote;
    ProgressBar progressBar;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_display_note);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        titleOfTextNote = findViewById(R.id.editTextText3);
        progressBar = findViewById(R.id.progressBar2);
        showNote = findViewById(R.id.textView6);
        Button = findViewById(R.id.button5);
        Button1 = findViewById(R.id.button6);

        progressBar.setVisibility(View.GONE);
        showNote.setVisibility(View.GONE);

        Button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(back);
            }
        });


        Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(view.VISIBLE);
                showNote.setVisibility(View.GONE);
                new Thread(() -> {
                    try{
                        Thread.sleep(2000);
                    }catch (Exception e){
                        throw new RuntimeException(e);
                    }
                    runOnUiThread(()->{
                        progressBar.setVisibility(View.GONE);
                        showNote.setVisibility(View.VISIBLE);

                    });
                }).start();

            }
        });



    }
}