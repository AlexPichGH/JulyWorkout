package application.p.alex.julyworkout.model;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import application.p.alex.julyworkout.R;

public class WorkoutList {
    private static WorkoutList ourInstance;
    private List<Workout> workouts = new ArrayList<>();
    private List<Workout> favoriteWorkouts = new ArrayList<>();
    private Context context;

    private WorkoutList(Context context) {
        initMockList(context);
        favoriteWorkoutTrue();
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

    public void setFavoriteWorkout(int index) {
        favoriteWorkouts.add(workouts.get(index));
    }

    public void removeFavoriteWorkout(int index) {
        favoriteWorkouts.remove(workouts.get(index));
    }

    public List<Workout> getFavoriteWorkouts() {
        return favoriteWorkouts;
    }

    private void initMockList(Context context) {

        Workout workoutPullUps = new Workout(context.getString(R.string.pull_ups),
                context.getString(R.string.pull_up_description), R.mipmap.pull_up, R.mipmap.pull_up_preview,
                context.getString(R.string.difficult_hard), "00:05:00");
        Workout workoutPushUps = new Workout(context.getString(R.string.push_ups),
                context.getString(R.string.push_up_description), R.mipmap.push_up, R.mipmap.push_up_preview,
                context.getString(R.string.difficult_medium), "00:05:00");
        Workout workoutSitUps = new Workout(context.getString(R.string.sit_ups),
                context.getString(R.string.sit_up_description), R.mipmap.squats, R.mipmap.squats_prewiew,
                context.getString(R.string.difficult_medium), "00:05:00");
        Workout workoutRunning = new Workout(context.getString(R.string.running),
                context.getString(R.string.running_description), R.mipmap.run_preview, R.mipmap.run_preview,
                context.getString(R.string.difficult_hard), "00:15:00");
        Workout workoutBar = new Workout(context.getString(R.string.bar),
                context.getString(R.string.bar_description), R.mipmap.bar, R.mipmap.bar_prewiew,
                context.getString(R.string.difficult_hard), "00:01:30");
        Workout workoutSideBar = new Workout(context.getString(R.string.side_bar),
                context.getString(R.string.side_bar_description), R.mipmap.side_bar, R.mipmap.side_bar_preview,
                context.getString(R.string.difficult_medium), "00:00:30");
        Workout workoutLunge = new Workout(context.getString(R.string.lunge),
                context.getString(R.string.lunge_description), R.mipmap.lunge, R.mipmap.lunge_preview,
                context.getString(R.string.difficult_easy), "00:07:00");
        Workout workoutCrunch = new Workout(context.getString(R.string.crunch),
                context.getString(R.string.crunch_description), R.mipmap.crunch, R.mipmap.crunch_preview,
                context.getString(R.string.difficult_medium), "00:07:00");

        workouts.add(workoutPullUps);
        workouts.add(workoutPushUps);
        workouts.add(workoutSitUps);
        workouts.add(workoutRunning);
        workouts.add(workoutBar);
        workouts.add(workoutSideBar);
        workouts.add(workoutLunge);
        workouts.add(workoutCrunch);
    }

    private void favoriteWorkoutTrue() {
        if (!favoriteWorkouts.isEmpty()) {
            for (int i = 0; i < favoriteWorkouts.size(); i++) {
                favoriteWorkouts.get(i).setFavorite(true);
            }
        }
    }
}
