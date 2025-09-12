package com.example.oopandclass;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class NoteMapper {

    static Gson gson = new Gson();

    // OOP -> Entity
    public static NoteEntity toEntity(Note note) {
        if (note instanceof Textnote) {
            return new NoteEntity(note.title, "text", null, ((Textnote) note).getTextContent(), note.createdDate);
        }
        else if (note instanceof Checklistnote) {
            String jsonItems = gson.toJson(((Checklistnote) note).getItems());
            return new NoteEntity(note.title, "checklist", jsonItems, null, note.createdDate);
        }
        return null;
    }

    // Entity -> OOP
    public static Note fromEntity(NoteEntity entity) {
        if (entity.type.equals("text")) {
            return new Textnote(entity.title, entity.createdDate, entity.content);
        }
        /*else if (entity.type.equals("checklist")) {
            List<String> items = gson.fromJson(entity.checklistItemsJson, new TypeToken<List<String>>(){}.getType());
            return new ChecklistNote(entity.title, entity.createdDate, items);
        }*/
        return null;
    }
}
