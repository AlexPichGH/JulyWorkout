package application.p.alex.julyworkout;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import application.p.alex.julyworkout.model.Workout;
import application.p.alex.julyworkout.model.WorkoutList;
import application.p.alex.julyworkout.utils.Constants;

public class WorkoutDetailActivity extends AppCompatActivity {
    private static final String LAST_RECORD_REPEATS = "lastrecord";
    private static final String LAST_RECORD_DATE = "lastrecorddate";
    private static final int NULL_REPEATS = 0;
    //    private SharedPreferences myLastRecord;
    private TextView title;
    private TextView description;
    private TextView repsCount;
    private TextView executingTime;
    private TextView difficult;
    private TextView record;
    private TextView currentDateAndTime;
    private ImageView imageView;
    private ImageView popupMenu;
    private SeekBar repeatsSeekBar;

    private int workoutIndex;
    private int recordRepeats;
    private String currentDateTimeString;
    private Intent saveRecordIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_detail);

        Intent intent = getIntent();
        workoutIndex = intent.getIntExtra(Constants.WORKOUT_INDEX, 0);
        iniUI(workoutIndex);
    }

    private void iniUI(int workoutIndex) {
        Workout workout = WorkoutList.getInstance(this).getWorkout(workoutIndex);
        title = findViewById(R.id.workout_detail_title);
        title.setText(workout.getTitle());
        description = findViewById(R.id.workout_detail_description);
        description.setText(workout.getDescription());
        repsCount = findViewById(R.id.workout_detail_repeats_count);
        repsCount.setText(String.valueOf(workout.getRepeatsCount()));
        executingTime = findViewById(R.id.workout_detail_time);
        executingTime.setText(workout.getExecutingTime());
        difficult = findViewById(R.id.workout_detail_difficult);
        difficult.setText(workout.getDifficult());
        record = findViewById(R.id.workout_detail_record);
        currentDateAndTime = findViewById(R.id.workout_detail_current_date_time);
        imageView = findViewById(R.id.workout_detail_image);
        imageView.setImageResource(workout.getImageResRef());
        repeatsSeekBar = findViewById(R.id.workout_detail_seek_bar);
        repeatsSeekBar.setProgress(workout.getRepeatsCount());
        repeatsSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                repsCount.setText(String.valueOf(i));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        popupMenu = findViewById(R.id.workout_detail_popup_menu);
        popupMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopUpMenu(view);
            }
        });

//        switch (workoutIndex) {
//            case Constants.PULL_UPS_ID:
//                myLastRecord = getSharedPreferences(Constants.RECORD_PULL_UP_SAVE, Context.MODE_PRIVATE);
//                break;
//            case Constants.PUSH_UPS_ID:
//                myLastRecord = getSharedPreferences(Constants.RECORD_PUSH_UP_SAVE, Context.MODE_PRIVATE);
//                break;
//            case Constants.SIT_UPS_ID:
//                myLastRecord = getSharedPreferences(Constants.RECORD_SIT_UP_SAVE, Context.MODE_PRIVATE);
//                break;
//            default:
//                break;
//        }
//        if (myLastRecord.contains(LAST_RECORD_REPEATS) && myLastRecord.contains(LAST_RECORD_DATE)) {
//            record.setText(String.valueOf(myLastRecord.getInt(LAST_RECORD_REPEATS, recordRepeats)));
//            currentDateAndTime.setText(myLastRecord.getString(LAST_RECORD_DATE, currentDateTimeString));
//        }
    }

    private void showPopUpMenu(View view) {
        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.inflate(R.menu.workout_detail_popup_menu);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.workout_detail_popup_save: {
                        setRecords();
                        return true;
                    }
                    case R.id.workout_detail_popup_delete: {
                        deleteRecord();
                        return true;
                    }
                    case R.id.workout_detail_popup_share: {
                        shareRecord();
                        return true;
                    }
                    default:
                        return false;
                }
            }
        });
        popupMenu.show();
    }

    private void setRecords() {
        if (repeatsSeekBar.getProgress() > recordRepeats) {
            currentDateTimeString = new SimpleDateFormat("dd.MM.yyyy", Locale.ROOT).format(new Date());
            recordRepeats = repeatsSeekBar.getProgress();
            record.setText(String.valueOf(recordRepeats));
            currentDateAndTime.setText(currentDateTimeString);
//            saveRecord(recordRepeats, currentDateTimeString);
            Toast.makeText(WorkoutDetailActivity.this, R.string.record_save, Toast.LENGTH_SHORT).show();
        } else if (repeatsSeekBar.getProgress() == NULL_REPEATS) {
            Toast.makeText(WorkoutDetailActivity.this, R.string.null_repeats, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(WorkoutDetailActivity.this, R.string.record_not_save, Toast.LENGTH_SHORT).show();
        }
    }

//    private void saveRecord(int record, String currentDateTimeString) {
//        SharedPreferences.Editor editor = myLastRecord.edit();
//        editor.putInt(LAST_RECORD_REPEATS, record);
//        editor.putString(LAST_RECORD_DATE, currentDateTimeString);
//        editor.apply();
//    }

    private void deleteRecord() {
//        SharedPreferences.Editor editor = myLastRecord.edit();
//        editor.clear();
//        editor.apply();
        record.setText("");
        currentDateAndTime.setText("");
        Toast.makeText(WorkoutDetailActivity.this, R.string.record_delete, Toast.LENGTH_SHORT).show();
    }

    private void shareRecord() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, (Constants.RECORD_MSG + record.getText()));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    @Override
    public void onBackPressed() {
        saveRecordIntent = new Intent();
        saveRecordIntent.putExtra(String.valueOf(R.string.record), record.getText().toString());
        setResult(RESULT_OK, saveRecordIntent);
        finish();
    }
}
