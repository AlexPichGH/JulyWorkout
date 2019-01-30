package application.p.alex.julyworkout.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import application.p.alex.julyworkout.database.WorkoutDbSchema.WorkoutTable.Columns;
import application.p.alex.julyworkout.model.Workout;

public class WorkoutCursorWrapper extends CursorWrapper {

    public WorkoutCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Workout getWorkouts() {

        String title = getString(getColumnIndex(Columns.WORKOUT_TITLE));
        String description = getString(getColumnIndex(Columns.WORKOUT_DESCRIPTION));
        String difficult = getString(getColumnIndex(Columns.WORKOUT_DIFFICULT));
        String time = getString(getColumnIndex(Columns.WORKOUT_TIME));
        String preview = getString(getColumnIndex(Columns.WORKOUT_PREVIEW));
        String image = getString(getColumnIndex(Columns.WORKOUT_IMAGE));
        int record = getInt(getColumnIndex(Columns.WORKOUT_RECORD));
        String recordDate = getString(getColumnIndex(Columns.WORKOUT_RECORD_DATE));
        int indexFavorite = getInt(getColumnIndex(Columns.WORKOUT_FAVORITE));

        Workout workout = new Workout();
        workout.setTitle(title);
        workout.setDescription(description);
        workout.setDifficult(difficult);
        workout.setExecutingTime(time);
        workout.setPreview(preview);
        workout.setImage(image);
        workout.setLastRecordRepeats(record);
        workout.setLastRecordDate(recordDate);
        workout.setFavorite(isFavorite(indexFavorite));

        return workout;
    }

    private boolean isFavorite(int index) {
        return index != 0;
    }
}
