package application.p.alex.julyworkout;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import application.p.alex.julyworkout.model.Workout;
import application.p.alex.julyworkout.model.WorkoutList;
import application.p.alex.julyworkout.utils.Constants;

public class WorkoutListActivity extends AppCompatActivity {
    private static final String TAG = "WorkoutListActivity";
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
                case Constants.PULL_UPS_ID:
                    textViewPullUpsRecord.setText(result);
                    break;
                case Constants.PUSH_UPS_ID:
                    textViewPushUpsRecord.setText(result);
                    break;
                case Constants.SIT_UPS_ID:
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
        Log.d(TAG, String.valueOf(R.string.on_create));
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
                startWorkoutDetailActivity(Constants.PULL_UPS_ID);
            }
        });

        buttonPushUps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startWorkoutDetailActivity(Constants.PUSH_UPS_ID);
            }
        });

        buttonSitUps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startWorkoutDetailActivity(Constants.SIT_UPS_ID);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, String.valueOf(R.string.on_start));
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, String.valueOf(R.string.on_resume));
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, String.valueOf(R.string.on_pause));
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, String.valueOf(R.string.on_stop));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, String.valueOf(R.string.on_destroy));
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, String.valueOf(R.string.on_restart));
    }

    private void startWorkoutDetailActivity(int i) {
        Intent startDetailActivity = new Intent(WorkoutListActivity.this, WorkoutDetailActivity.class);
        startDetailActivity.putExtra(Constants.WORKOUT_INDEX, i);
        startActivityForResult(startDetailActivity, i);
    }
}
