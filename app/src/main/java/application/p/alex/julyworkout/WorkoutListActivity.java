package application.p.alex.julyworkout;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

import application.p.alex.julyworkout.list.WorkoutAdapter;
import application.p.alex.julyworkout.model.Workout;
import application.p.alex.julyworkout.model.WorkoutList;

public class WorkoutListActivity extends AppCompatActivity {
    private List<Workout> workoutList;
    private RecyclerView workoutRecycler;
    private WorkoutAdapter workoutAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_list);

        workoutRecycler = findViewById(R.id.workout_list_recycler);
        workoutAdapter = new WorkoutAdapter(this, WorkoutList.getInstance(this).getAllWorkouts());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        workoutRecycler.setLayoutManager(linearLayoutManager);
        workoutRecycler.setAdapter(workoutAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.workout_list_popup_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(this, WorkoutFavoritesActivity.class);
        startActivity(intent);
        return super.onOptionsItemSelected(item);
    }
}
