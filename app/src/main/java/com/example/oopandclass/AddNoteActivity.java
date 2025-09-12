package com.example.oopandclass;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AddNoteActivity extends AppCompatActivity {

    Button submitTextNote;

    Button Button2;
    TextView showNote;
    EditText titleOfTextNote, contentOfTextNote;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_note);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        submitTextNote = findViewById(R.id.button4);
        titleOfTextNote = findViewById(R.id.editTextText);
        contentOfTextNote = findViewById(R.id.editTextText2);
        showNote = findViewById(R.id.textView2);
        Button2 = findViewById(R.id.button8);

        Button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(back);
            }
        });

        submitTextNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strOfTitle = titleOfTextNote.getText().toString();
                String strOfContent = contentOfTextNote.getText().toString();
                Date strOfDate = new Date();

                Textnote textNote1 = new Textnote(strOfTitle, strOfDate, strOfContent);

                showNote.setText(textNote1.display());

                NoteEntity entity = NoteMapper.toEntity(textNote1);

                Context context = view.getContext();
                Executors.newSingleThreadExecutor().execute(() -> {
                    AppDatabase.getInstance(context).noteDao().insert(entity);
                });
            }
        });
    }
}