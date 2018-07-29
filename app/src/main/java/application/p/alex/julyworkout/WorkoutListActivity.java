package application.p.alex.julyworkout;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
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
    private TextView textViewPullUpsRecordW;
    private TextView textViewPushUpsRecordW;
    private TextView textViewSitUpsRecordW;
    private TextView textViewPullUpsRecord;
    private TextView textViewPushUpsRecord;
    private TextView textViewSitUpsRecord;
    private CheckBox checkBoxShowRecord;

    private String pullUpResult;
    private String pushUpResult;
    private String sitUpResult;
    private boolean showRecordChecked;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data != null && resultCode == RESULT_OK) {
            String result = data.getStringExtra(String.valueOf(R.string.record));
            showRecordChecked = checkBoxShowRecord.isChecked();
            isShowRecordChecked(requestCode, result, showRecordChecked);
        } else {
            return;
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate()");
//        setContentView(R.layout.list_item);
        setContentView(R.layout.activity_workout_list);
        workoutList = WorkoutList.getInstance(this).getAllWorkouts();

        buttonPullUps = findViewById(R.id.button_pull_ups);
        buttonPushUps = findViewById(R.id.button_push_ups);
        buttonSitUps = findViewById(R.id.button_sit_ups);

        textViewPullUpsRecordW = findViewById(R.id.workout_list_record_pull_ups_w);
        textViewPushUpsRecordW = findViewById(R.id.workout_list_record_push_ups_w);
        textViewSitUpsRecordW = findViewById(R.id.workout_list_record_sit_ups_w);
        textViewPullUpsRecord = findViewById(R.id.workout_list_record_pull_ups);
        textViewPushUpsRecord = findViewById(R.id.workout_list_record_push_ups);
        textViewSitUpsRecord = findViewById(R.id.workout_list_record_sit_ups);

        checkBoxShowRecord = findViewById(R.id.workout_list_check_box_show_record);
        checkBoxShowRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkBoxShowRecord.isChecked()) {
                    checkBoxShowRecord.setChecked(true);
                } else {
                    checkBoxShowRecord.setChecked(false);
                    textViewPullUpsRecordW.setText(R.string.empty_string);
                    textViewPullUpsRecord.setText(R.string.empty_string);
                    textViewPushUpsRecordW.setText(R.string.empty_string);
                    textViewPushUpsRecord.setText(R.string.empty_string);
                    textViewSitUpsRecordW.setText(R.string.empty_string);
                    textViewSitUpsRecord.setText(R.string.empty_string);
                }
            }
        });

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

        if (savedInstanceState != null) {
            pullUpResult = savedInstanceState.getString(Constants.RECORD_PULL_UP_SAVE);
            textViewPullUpsRecord.setText(pullUpResult);
            pushUpResult = savedInstanceState.getString(Constants.RECORD_PUSH_UP_SAVE);
            textViewPushUpsRecord.setText(pushUpResult);
            sitUpResult = savedInstanceState.getString(Constants.RECORD_SIT_UP_SAVE);
            textViewSitUpsRecord.setText(sitUpResult);
            showRecordChecked = savedInstanceState.getBoolean(Constants.RECORD_SHOW);
        }
    }

    private void isShowRecordChecked(int requestCode, String result, boolean showRecordChecked) {
        if (showRecordChecked) {
            switch (requestCode) {
                case Constants.PULL_UPS_ID:
                    pullUpResult = result;
                    textViewPullUpsRecordW.setText(R.string.record);
                    textViewPullUpsRecord.setText(pullUpResult);
                    break;
                case Constants.PUSH_UPS_ID:
                    pushUpResult = result;
                    textViewPushUpsRecordW.setText(R.string.record);
                    textViewPushUpsRecord.setText(pushUpResult);
                    break;
                case Constants.SIT_UPS_ID:
                    sitUpResult = result;
                    textViewSitUpsRecordW.setText(R.string.record);
                    textViewSitUpsRecord.setText(sitUpResult);
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString(Constants.RECORD_PULL_UP_SAVE, pullUpResult);
        savedInstanceState.putString(Constants.RECORD_PUSH_UP_SAVE, pushUpResult);
        savedInstanceState.putString(Constants.RECORD_SIT_UP_SAVE, sitUpResult);
        savedInstanceState.putBoolean(Constants.RECORD_SHOW, showRecordChecked);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart()");
    }

    private void startWorkoutDetailActivity(int i) {
        Intent startDetailActivity = new Intent(WorkoutListActivity.this, WorkoutDetailActivity.class);
        startDetailActivity.putExtra(Constants.WORKOUT_INDEX, i);
        startActivityForResult(startDetailActivity, i);
    }
}
