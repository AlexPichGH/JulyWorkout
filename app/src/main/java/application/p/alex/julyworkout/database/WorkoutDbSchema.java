package application.p.alex.julyworkout.database;

class WorkoutDbSchema {
    public static final class WorkoutTable {
        public static final String NAME = "workouts";

        static final class Columns {
            static final String WORKOUT_TITLE = "title";
            static final String WORKOUT_DESCRIPTION = "description";
            static final String WORKOUT_DIFFICULT = "difficult";
            static final String WORKOUT_TIME = "time";
            static final String WORKOUT_PREVIEW = "preview";
            static final String WORKOUT_IMAGE = "image";
            static final String WORKOUT_RECORD = "record";
            static final String WORKOUT_RECORD_DATE = "record_date";
            static final String WORKOUT_FAVORITE = "favorite";
        }
    }
}
