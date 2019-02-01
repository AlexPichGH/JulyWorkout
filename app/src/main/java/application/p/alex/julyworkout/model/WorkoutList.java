package application.p.alex.julyworkout.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import application.p.alex.julyworkout.database.WorkoutCursorWrapper;
import application.p.alex.julyworkout.database.WorkoutDbHelper;
import application.p.alex.julyworkout.database.WorkoutDbSchema.WorkoutTable;
import application.p.alex.julyworkout.database.WorkoutDbSchema.WorkoutTable.Columns;

public class WorkoutList {
    private static WorkoutList workouts;
    private Context context;
    private WorkoutDbHelper dbHelper;

    private WorkoutList(Context c) {
        context = c.getApplicationContext();
        dbHelper = new WorkoutDbHelper(context);
        dbHelper.updateDataBase();
        dbHelper.getReadableDatabase();
    }

    public static WorkoutList getInstance(Context context) {
        if (workouts == null) {
            return workouts = new WorkoutList(context);
        }
        return workouts;
    }

    public List<Workout> getWorkoutList() {
        List<Workout> workoutList = new ArrayList<>();
        WorkoutCursorWrapper cursorWrapper = getCursorWrapper();
        cursorWrapper.moveToFirst();
        while (!cursorWrapper.isAfterLast()) {
            workoutList.add(cursorWrapper.getWorkouts());
            cursorWrapper.moveToNext();
        }
        return workoutList;
    }

    private WorkoutCursorWrapper getCursorWrapper() {
        Cursor cursor = dbHelper.getReadableDatabase().query(
                WorkoutTable.NAME,
                null,
                null,
                null,
                null,
                null,
                null
        );
        return new WorkoutCursorWrapper(cursor);
    }

    public List<Workout> getFavoriteWorkouts() {
        List<Workout> favoriteWorkouts = new ArrayList<>();
        for (int i = 0; i < getWorkoutList().size(); i++) {
            if (getWorkoutList().get(i).isFavorite()) {
                favoriteWorkouts.add(getWorkoutList().get(i));
            }
        }
        return favoriteWorkouts;
    }

    public void addWorkoutToFavorite(Workout workout) {
        String id = workout.getWorkoutId();
        ContentValues values = new ContentValues();
        values.put(Columns.WORKOUT_FAVORITE, 1);
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        database.update(WorkoutTable.NAME, values, Columns.WORKOUT_ID + " = ?", new String[]{id});
    }

    public void deleteWorkoutFromFavorite(Workout workout) {
        String id = workout.getWorkoutId();
        ContentValues values = new ContentValues();
        values.put(Columns.WORKOUT_FAVORITE, 0);
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        database.update(WorkoutTable.NAME, values, Columns.WORKOUT_ID + " = ?", new String[]{id});
    }
}
