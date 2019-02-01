package application.p.alex.julyworkout.database;

public class WorkoutDbSchema {
    public static final class WorkoutTable {
        public static final String NAME = "workouts";

        public static final class Columns {
            public static final String WORKOUT_ID = "workout_id";
            public static final String WORKOUT_TITLE = "title";
            public static final String WORKOUT_DESCRIPTION = "description";
            public static final String WORKOUT_DIFFICULT = "difficult";
            public static final String WORKOUT_TIME = "time";
            public static final String WORKOUT_PREVIEW = "preview";
            public static final String WORKOUT_IMAGE = "image";
            public static final String WORKOUT_RECORD = "record";
            public static final String WORKOUT_RECORD_DATE = "record_date";
            public static final String WORKOUT_FAVORITE = "favorite";
        }
    }
}
