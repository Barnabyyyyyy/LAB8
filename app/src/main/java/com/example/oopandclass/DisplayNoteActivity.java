package com.example.oopandclass;

import static androidx.core.content.ContextCompat.startActivity;

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

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class DisplayNoteActivity extends AppCompatActivity {

    Button Button;

    Button Button1;
    EditText titleOfTextNote, contentOfTextNote;
    TextView showNote, showNoteFromAPI;
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
        showNoteFromAPI = findViewById(R.id.textView3);
        Button = findViewById(R.id.button5);
        Button1 = findViewById(R.id.button6);



        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            List<NoteEntity> entities = AppDatabase.getInstance(this).noteDao().getAll();
            List<Note> notes = new ArrayList<>();
            for (NoteEntity entity : entities) {
                notes.add(NoteMapper.fromEntity(entity));
            }
            runOnUiThread(() -> {
                StringBuilder sb = new StringBuilder();
                for (Note note : notes) {
                    sb.append(note.display()).append("\n");
                }
                showNote.setText(sb.toString());
            });
        });

        //load from API
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);
        Call<List<Textnote>> call = apiService.getTextNote();

        call.enqueue(new Callback<List<Textnote>>() {
            @Override
            public void onResponse(Call<List<Textnote>> call, Response<List<Textnote>> response) {
                if (!response.isSuccessful()) {
                    showNoteFromAPI.setText("Error Code: " + response.code());
                    return;
                }

                List<Textnote> notes = response.body();
                StringBuilder builder = new StringBuilder();
                for (Textnote n : notes) {
                    builder.append("Title: ").append(n.getTitle()).append("\n")
                            .append("Body: ").append(n.getTextContent()).append("\n\n");
                }
                showNoteFromAPI.setText(builder.toString());
            }

            @Override
            public void onFailure(Call<List<Textnote>> call, Throwable t) {
                showNoteFromAPI.setText("Failed: " + t.getMessage());
            }
        });

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