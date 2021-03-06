  package agarwal.shashwat.mvvm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

  public class MainActivity extends AppCompatActivity {

      private static final String TAG = "MainActivity";
    private NoteViewModel noteViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final NoteAdapter noteAdapter=new NoteAdapter();
        recyclerView.setAdapter(noteAdapter);
         try{
        noteViewModel= new ViewModelProvider(this).get(NoteViewModel.class);}
         catch (Exception e){
             Log.d(TAG, "onCreate: "+e.toString());
         }
        noteViewModel.getAllNotes().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {
                //update recycler view
                noteAdapter.setNotes(notes);
            }
        });
    }
}