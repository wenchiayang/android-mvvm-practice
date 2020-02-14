package com.example.architectureexample.scences.addNote;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import com.example.architectureexample.model.note.Note;
import com.example.architectureexample.scences.note.NoteRepository;

public class AddNoteViewModel extends AndroidViewModel {
    private NoteRepository repository;

    public AddNoteViewModel(@NonNull Application application) {
        super(application);
        repository = new NoteRepository(application);
    }

    public void insert(Note note) {
        repository.insert(note);
    }

    public void update(Note note) {
        repository.update(note);
    }
}