package application.p.alex.julyworkout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import application.p.alex.julyworkout.interfaces.OnWorkoutListItemSelectedListener;
import application.p.alex.julyworkout.model.Workout;
import application.p.alex.julyworkout.model.WorkoutList;
import application.p.alex.julyworkout.utils.Constants;

public class WorkoutDetailFragment extends Fragment {
    private static final String LAST_RECORD_REPEATS = "lastrecord";
    private static final String LAST_RECORD_DATE = "lastrecorddate";
    private static final int NULL_REPEATS = 0;
    OnWorkoutListItemSelectedListener itemSelectedListener;
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

    public static WorkoutDetailFragment initFragment(int workoutIndex) {
        Bundle arguments = new Bundle();
        arguments.putInt(Constants.WORKOUT_INDEX, workoutIndex);
        WorkoutDetailFragment fragment = new WorkoutDetailFragment();
        fragment.setArguments(arguments);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        itemSelectedListener = (OnWorkoutListItemSelectedListener) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_workout_detail, container, false);
        iniUI(root);
        return root;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        assert getArguments() != null;
        workoutIndex = getArguments().getInt(Constants.WORKOUT_INDEX);
    }

    private void iniUI(View root) {
        Workout workout = WorkoutList.getInstance(getContext()).getWorkout(workoutIndex);
        title = root.findViewById(R.id.workout_detail_title);
        title.setText(workout.getTitle());
        description = root.findViewById(R.id.workout_detail_description);
        description.setText(workout.getDescription());
        repsCount = root.findViewById(R.id.workout_detail_repeats_count);
        repsCount.setText(String.valueOf(workout.getRepeatsCount()));
        executingTime = root.findViewById(R.id.workout_detail_time);
        executingTime.setText(workout.getExecutingTime());
        difficult = root.findViewById(R.id.workout_detail_difficult);
        difficult.setText(workout.getDifficult());
        record = root.findViewById(R.id.workout_detail_record);
        currentDateAndTime = root.findViewById(R.id.workout_detail_current_date_time);
        imageView = root.findViewById(R.id.workout_detail_image);
        imageView.setImageResource(workout.getImageResRef());
        repeatsSeekBar = root.findViewById(R.id.workout_detail_seek_bar);
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

        popupMenu = root.findViewById(R.id.workout_detail_popup_menu);
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
        PopupMenu popupMenu = new PopupMenu(getContext(), view);
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
            Toast.makeText(getContext(), R.string.record_save, Toast.LENGTH_SHORT).show();
        } else if (repeatsSeekBar.getProgress() == NULL_REPEATS) {
            Toast.makeText(getContext(), R.string.null_repeats, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), R.string.record_not_save, Toast.LENGTH_SHORT).show();
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
        Toast.makeText(getContext(), R.string.record_delete, Toast.LENGTH_SHORT).show();
    }

    private void shareRecord() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, (Constants.RECORD_MSG + record.getText()));
        if (intent.resolveActivity(getContext().getPackageManager()) != null) {
            startActivity(intent);
        }
    }

//    @Override
//    public void onBackPressed() {
//        saveRecordIntent = new Intent();
//        saveRecordIntent.putExtra(String.valueOf(R.string.record), record.getText().toString());
//        setResult(RESULT_OK, saveRecordIntent);
//        finish();
//    }
}
