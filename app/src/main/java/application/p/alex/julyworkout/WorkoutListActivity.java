package application.p.alex.julyworkout;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.List;

import application.p.alex.julyworkout.model.Workout;
import application.p.alex.julyworkout.model.WorkoutList;
import application.p.alex.julyworkout.utils.Constants;

public class WorkoutListActivity extends AppCompatActivity {
    private List<Workout> workoutList;
    private Button buttonPullUps;
    private Button buttonPushUps;
    private Button buttonSitUps;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_list);

        workoutList = WorkoutList.getInstance(this).getAllWorkouts();

        buttonPullUps = findViewById(R.id.button_pull_ups);
        buttonPushUps = findViewById(R.id.button_push_ups);
        buttonSitUps = findViewById(R.id.button_sit_ups);

        buttonPullUps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startDetailActivity = new Intent(WorkoutListActivity.this, WorkoutDetailActivity.class);
                startDetailActivity.putExtra(Constants.WORKOUT_INDEX, 0);
                startActivity(startDetailActivity);
            }
        });

        buttonPushUps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startDetailActivity = new Intent(WorkoutListActivity.this, WorkoutDetailActivity.class);
                startDetailActivity.putExtra(Constants.WORKOUT_INDEX, 1);
                startActivity(startDetailActivity);
            }
        });

        buttonSitUps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startDetailActivity = new Intent(WorkoutListActivity.this, WorkoutDetailActivity.class);
                startDetailActivity.putExtra(Constants.WORKOUT_INDEX, 2);
                startActivity(startDetailActivity);
            }
        });
    }
}
