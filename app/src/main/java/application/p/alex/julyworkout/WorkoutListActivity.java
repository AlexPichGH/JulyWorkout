package application.p.alex.julyworkout;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import application.p.alex.julyworkout.model.Workout;
import application.p.alex.julyworkout.model.WorkoutList;
import application.p.alex.julyworkout.utils.Constants;

public class WorkoutListActivity extends AppCompatActivity {
    private List<Workout> workoutList;
    private Button buttonPullUps;
    private Button buttonPushUps;
    private Button buttonSitUps;
    private TextView textViewPullUpsRecord;
    private TextView textViewPushUpsRecord;
    private TextView textViewSitUpsRecord;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null) {
            return;
        } else if (requestCode == 0) {
            String result = data.getStringExtra(String.valueOf(R.string.record));
            textViewPullUpsRecord.setText(Constants.RECORD_MSG);
            textViewPullUpsRecord.append(result);
        } else if (requestCode == 1) {
            String result = data.getStringExtra(String.valueOf(R.string.record));
            textViewPushUpsRecord.setText(Constants.RECORD_MSG);
            textViewPushUpsRecord.append(result);
        } else if (requestCode == 2) {
            String result = data.getStringExtra(String.valueOf(R.string.record));
            textViewSitUpsRecord.setText(Constants.RECORD_MSG);
            textViewSitUpsRecord.append(result);
        }

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_list);

        workoutList = WorkoutList.getInstance(this).getAllWorkouts();

        buttonPullUps = findViewById(R.id.button_pull_ups);
        buttonPushUps = findViewById(R.id.button_push_ups);
        buttonSitUps = findViewById(R.id.button_sit_ups);
        textViewPullUpsRecord = findViewById(R.id.workout_list_record_pull_ups);
        textViewPushUpsRecord = findViewById(R.id.workout_list_record_push_ups);
        textViewSitUpsRecord = findViewById(R.id.workout_list_record_sit_ups);

        buttonPullUps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startDetailActivity = new Intent(WorkoutListActivity.this, WorkoutDetailActivity.class);
                startDetailActivity.putExtra(Constants.WORKOUT_INDEX, 0);
                startActivityForResult(startDetailActivity, 0);
            }
        });

        buttonPushUps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startDetailActivity = new Intent(WorkoutListActivity.this, WorkoutDetailActivity.class);
                startDetailActivity.putExtra(Constants.WORKOUT_INDEX, 1);
                startActivityForResult(startDetailActivity, 1);
            }
        });

        buttonSitUps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startDetailActivity = new Intent(WorkoutListActivity.this, WorkoutDetailActivity.class);
                startDetailActivity.putExtra(Constants.WORKOUT_INDEX, 2);
                startActivityForResult(startDetailActivity, 2);
            }
        });
    }
}
