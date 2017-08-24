package androidbasicsnd.lloyd.alan.com.udacity.habittracker;

import android.provider.BaseColumns;

/**
 * API Contract for HabitTracker app
 */
public class HabitTrackerContract {

    /**
     * Inner class that defines constant values for the habits database table.
     * Each entry in the table represents a single habit
     */
    public static abstract class Habits implements BaseColumns {

        /**
         * Name of database table for habits
         */
        public final static String TABLE_NAME = "habits";

        /**
         * Unique ID number for the habit (only for use in the database table)
         * Type: INTEGER
         */
        public final static String _ID = BaseColumns._ID;

        /**
         * Name of the habit Type: TEXT
         */
        public final static String COLUMN_HABIT_NAME = "habit_name";

        /**
         * whether habit involves exercise (yes, no or some)  Type: TEXT
         */
        public final static String COLUMN_HABIT_INVOLVES_EXERCISE = "any_exercise";


        /**
         * number of times habit repeated in 2017  Type: INTEGER
         */
        public final static String COLUMN_HABIT_REPEATED_HOW_MANY_TIMES = "habit_repeats";

    }
}
