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
    private static final int PULL_UPS_ID = 0;
    private static final int PUSH_UPS_ID = 1;
    private static final int SIT_UPS_ID = 2;
    private List<Workout> workoutList;
    private Button buttonPullUps;
    private Button buttonPushUps;
    private Button buttonSitUps;
    private TextView textViewPullUpsRecord;
    private TextView textViewPushUpsRecord;
    private TextView textViewSitUpsRecord;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data != null && resultCode == RESULT_OK) {
            String result = data.getStringExtra(String.valueOf(R.string.record));
            switch (requestCode) {
                case PULL_UPS_ID:
                    textViewPullUpsRecord.setText(result);
                    break;
                case PUSH_UPS_ID:
                    textViewPushUpsRecord.setText(result);
                    break;
                case SIT_UPS_ID:
                    textViewSitUpsRecord.setText(result);
                    break;
                default:
                    break;
            }
        } else {
            return;
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
                startWorkoutDetailActivity(PULL_UPS_ID);
            }
        });

        buttonPushUps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startWorkoutDetailActivity(PUSH_UPS_ID);
            }
        });

        buttonSitUps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startWorkoutDetailActivity(SIT_UPS_ID);
            }
        });
    }

    private void startWorkoutDetailActivity(int i) {
        Intent startDetailActivity = new Intent(WorkoutListActivity.this, WorkoutDetailActivity.class);
        startDetailActivity.putExtra(Constants.WORKOUT_INDEX, i);
        startActivityForResult(startDetailActivity, i);
    }
}
