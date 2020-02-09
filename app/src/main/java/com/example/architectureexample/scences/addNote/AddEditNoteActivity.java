package com.example.architectureexample.scences.addNote;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.lifecycle.ViewModelProvider;
import com.example.architectureexample.R;
import com.example.architectureexample.model.note.Note;

public class AddEditNoteActivity extends AppCompatActivity {
    public static final String EXTRA_EDIT_NOTE = "uci.mswe.265p.recipe_savior.EXTRA_EDIT_NOTE";

    private EditText editTextTitle;
    private EditText editTextDescription;
    private NumberPicker numberPickerPriority;

    private AddNoteViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        editTextTitle = findViewById(R.id.edit_text_title);
        editTextDescription = findViewById(R.id.edit_text_description);
        numberPickerPriority = findViewById(R.id.number_picker_priority);

        numberPickerPriority.setMinValue(1);
        numberPickerPriority.setMaxValue(10);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);

        // TODO: - if you want to share data at different fragments
        // try this: https://www.youtube.com/watch?v=ACK67xU1Y3s
        viewModel = new ViewModelProvider(this).get(AddNoteViewModel.class);

        Intent intent = getIntent();

        if (intent.hasExtra(EXTRA_EDIT_NOTE)) { // safe mode
            setTitle("Edit Note");
            Note note = (Note) intent.getSerializableExtra(EXTRA_EDIT_NOTE);
            editTextTitle.setText(note.getTitle());
            editTextDescription.setText(note.getDescription());
            numberPickerPriority.setValue(note.getPriority());
        } else { // add mode
            setTitle("Add Note");
        }
    }

    @Override
    protected void onDestroy() {
        Toast.makeText(this, "Closed...", Toast.LENGTH_SHORT).show();
        super.onDestroy();
    }

    private void saveNote() {
        String title = editTextTitle.getText().toString();
        String description = editTextDescription.getText().toString();
        int priority = numberPickerPriority.getValue();

        if (title.trim().isEmpty() || description.trim().isEmpty()) {
            Toast.makeText(this, "Please insert a title and description", Toast.LENGTH_SHORT).show();
            return;
        }

        Note note = new Note(title, description, priority);

        if (getIntent().hasExtra(EXTRA_EDIT_NOTE)) { // edit
            int id = ((Note) getIntent().getSerializableExtra(EXTRA_EDIT_NOTE)).getId();
            note.setId(id);
            viewModel.update(note);
            Toast.makeText(this, "Note updated", Toast.LENGTH_SHORT).show();
        } else { // add
            viewModel.insert(note);
            Toast.makeText(this, "Note saved", Toast.LENGTH_SHORT).show();
        }

        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_note_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_note:
                saveNote();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
