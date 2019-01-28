package application.p.alex.julyworkout.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
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

import application.p.alex.julyworkout.R;
import application.p.alex.julyworkout.interfaces.OnWorkoutListItemSelectedListener;
import application.p.alex.julyworkout.model.Workout;
import application.p.alex.julyworkout.model.WorkoutList;
import application.p.alex.julyworkout.utils.Constants;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class WorkoutDetailFragment extends Fragment {

    private static final String LAST_RECORD_REPEATS = "last_record";
    private static final String LAST_RECORD_DATE = "last_record_date";
    @BindView(R.id.workout_detail_title)
    TextView title;
    @BindView(R.id.workout_detail_description)
    TextView description;
    @BindView(R.id.workout_detail_repeats_count)
    TextView repsCount;
    //    @BindView(R.id.workout_detail_time)
//    TextView executingTime;
    @BindView(R.id.workout_detail_difficult)
    TextView difficult;
    @BindView(R.id.workout_detail_record)
    TextView record;
    @BindView(R.id.workout_detail_current_date_time)
    TextView currentDateAndTime;
    @BindView(R.id.workout_detail_image)
    ImageView imageView;
    @BindView(R.id.workout_detail_popup_menu)
    ImageView popupMenu;
    @BindView(R.id.workout_detail_seek_bar)
    SeekBar repeatsSeekBar;
    private static final int NULL_REPEATS = 0;
    OnWorkoutListItemSelectedListener itemSelectedListener;
    private Unbinder unbinder;
    private int workoutIndex;
    private int recordRepeats;
    private String currentDateTimeString;

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

        unbinder = ButterKnife.bind(this, root);

        iniUI();
        initTimerFragment();
        return root;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            workoutIndex = getArguments().getInt(Constants.WORKOUT_INDEX);
        }
    }

    private void iniUI() {
        Workout workout = WorkoutList.getInstance(getContext()).getWorkout(workoutIndex);
        title.setText(workout.getTitle());
        description.setText(workout.getDescription());
        repsCount.setText(String.valueOf(workout.getRepeatsCount()));
        difficult.setText(workout.getDifficult());
        imageView.setImageResource(workout.getImageResRef());
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

        popupMenu.setOnClickListener(this::showPopUpMenu);
    }

    private void showPopUpMenu(View view) {
        PopupMenu popupMenu = new PopupMenu(getContext(), view);
        popupMenu.inflate(R.menu.workout_detail_popup_menu);
        popupMenu.setOnMenuItemClickListener(menuItem -> {
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
        });
        popupMenu.show();
    }

    private void setRecords() {
        if (repeatsSeekBar.getProgress() > recordRepeats) {
            currentDateTimeString = new SimpleDateFormat("dd.MM.yyyy", Locale.ROOT).format(new Date());
            recordRepeats = repeatsSeekBar.getProgress();
            record.setText(String.valueOf(recordRepeats));
            currentDateAndTime.setText(currentDateTimeString);
            Toast.makeText(getContext(), R.string.record_save, Toast.LENGTH_SHORT).show();
        } else if (repeatsSeekBar.getProgress() == NULL_REPEATS) {
            Toast.makeText(getContext(), R.string.null_repeats, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), R.string.record_not_save, Toast.LENGTH_SHORT).show();
        }
    }


    private void deleteRecord() {
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

    private void initTimerFragment() {
        FragmentManager fragmentManager = getChildFragmentManager();
        WorkoutTimerFragment timerFragment = new WorkoutTimerFragment();
        fragmentManager.beginTransaction().replace(R.id.workout_timer, timerFragment).commit();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
