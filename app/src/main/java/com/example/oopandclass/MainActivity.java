package com.example.oopandclass;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}

public static void main(String[] args) {
            Note a = new Note();
            a.title = "work";
            a.context = "Lab3";
            a.createdDate = 15;
            a.getSummary();


            Note b = new Note();
            b.title = "Shopping List";
            b.context = "Milk, Bread, Eggs, and Butter.";
            b.createdDate = 16;
            b.getSummary();

}
public static void main(String[] args) {
    User a = new User();
    a.fullname = "work";
    a.ArrayList = "Lab3";
    a.age = 28;
    a.addNote();
    a.showAllNotes();
    a.showNoteSummary();

}

