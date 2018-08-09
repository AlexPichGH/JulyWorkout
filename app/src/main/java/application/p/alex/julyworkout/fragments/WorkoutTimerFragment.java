package application.p.alex.julyworkout.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

import application.p.alex.julyworkout.R;

public class WorkoutTimerFragment extends Fragment implements View.OnClickListener {

    private static final int MILLS_IN_SECOND = 3600;
    private static final int SECONDS_IN_MINUTES = 60;
    private static final int DELAY = 1000;
    private static final String KEY_SECONDS = "seconds";
    private static final String KEY_RUNNING = "running";
    private static final String KEY_WAS_RUNNING = "wasRunning";

    private TextView textViewWatch;
    private Button resetButton;
    private Button stopButton;
    private Button startButton;

    private int seconds;
    private boolean running;
    private boolean wasRunning;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_workout_timer, container, false);
        initTimer(root);
        if (!wasRunning) {
            runTimer();
        }
        return root;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            seconds = savedInstanceState.getInt(KEY_SECONDS);
            running = savedInstanceState.getBoolean(KEY_RUNNING);
            wasRunning = savedInstanceState.getBoolean(KEY_WAS_RUNNING);
        }
    }

    private void initTimer(View root) {
        textViewWatch = root.findViewById(R.id.textView_watch);
        resetButton = root.findViewById(R.id.reset_button);
        stopButton = root.findViewById(R.id.stop_button);
        startButton = root.findViewById(R.id.start_button);

        resetButton.setOnClickListener(this);
        stopButton.setOnClickListener(this);
        startButton.setOnClickListener(this);
    }

    @Override
    public void onStart() {
        if (wasRunning) {
            running = true;
        }
        super.onStart();
    }

    @Override
    public void onStop() {
        wasRunning = running;
        running = false;
        super.onStop();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt(KEY_SECONDS, seconds);
        savedInstanceState.putBoolean(KEY_RUNNING, running);
        savedInstanceState.putBoolean(KEY_WAS_RUNNING, wasRunning);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.reset_button:
                running = false;
                seconds = 0;
                break;
            case R.id.stop_button:
                running = false;
                break;
            case R.id.start_button:
                running = true;
                break;
            default:
                break;
        }
    }

    private void runTimer() {

        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds / MILLS_IN_SECOND;
                int minutes = (seconds % MILLS_IN_SECOND) / SECONDS_IN_MINUTES;
                int secs = seconds % 60;
                String time = String.format(Locale.US, "%d:%02d:%02d", hours, minutes, secs);
                textViewWatch.setText(time);
                if (running) {
                    seconds++;
                }
                handler.postDelayed(this, DELAY);
            }
        });
    }
}
