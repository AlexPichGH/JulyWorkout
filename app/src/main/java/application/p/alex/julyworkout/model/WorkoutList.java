package application.p.alex.julyworkout.model;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import application.p.alex.julyworkout.R;

public class WorkoutList {
    private static WorkoutList ourInstance;
    private static List<Workout> workouts;
    private Context context;

    private WorkoutList(Context context) {
        initMockList(context);

    }

    public static WorkoutList getInstance(Context context) {
        if (ourInstance != null) {
            return ourInstance;
        }
        return ourInstance = new WorkoutList(context);
    }

    public Workout getWorkout(int index) {
        return workouts.get(index);
    }

    public List<Workout> getAllWorkouts() {
        return workouts;
    }

    public void setWorkout(int index, Workout workout) {
        workouts.set(index, workout);
    }

    private void initMockList(Context context) {
        workouts = new ArrayList<>();

        Workout workoutPullUps = new Workout(context.getString(R.string.pull_ups),
                context.getString(R.string.pull_up_description), R.mipmap.pull_up,
                context.getString(R.string.difficult_hard), 0, 0);
        Workout workoutPushUps = new Workout(context.getString(R.string.push_ups),
                context.getString(R.string.push_up_description),R.mipmap.push_up,
                context.getString(R.string.difficult_medium), 0, 0);
        Workout workoutSitUps = new Workout(context.getString(R.string.sit_ups),
                context.getString(R.string.sit_up_description), R.mipmap.squats,
                context.getString(R.string.difficult_medium), 0, 0);

        workouts.add(workoutPullUps);
        workouts.add(workoutPushUps);
        workouts.add(workoutSitUps);
    }
}
