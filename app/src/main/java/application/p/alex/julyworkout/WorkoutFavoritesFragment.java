package application.p.alex.julyworkout;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import application.p.alex.julyworkout.list.FavoriteWorkoutAdapter;
import application.p.alex.julyworkout.model.WorkoutList;

public class WorkoutFavoritesActivity extends AppCompatActivity {
    private RecyclerView workoutRecycler;
    private FavoriteWorkoutAdapter favoriteWorkoutAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_workout_favorites);

        workoutRecycler = findViewById(R.id.workout_favorites_recycler);
        favoriteWorkoutAdapter = new FavoriteWorkoutAdapter(this, WorkoutList.getInstance(this).getFavoriteWorkouts());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        workoutRecycler.setLayoutManager(linearLayoutManager);
        workoutRecycler.setAdapter(favoriteWorkoutAdapter);
    }
}
