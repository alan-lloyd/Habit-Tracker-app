package androidbasicsnd.lloyd.alan.com.udacity.habittracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidbasicsnd.lloyd.alan.com.udacity.habittracker.HabitTrackerContract.Habits;

/**
 * Database helper for HabitTracker app. Manages database creation and version management
 */
public class HabitTrackerDbHelper extends SQLiteOpenHelper {

    //to use with logs
    public static final String LOG_TAG = HabitTrackerDbHelper.class.getSimpleName();

    /**
     * Name of the database file
     */
    private static final String DATABASE_NAME = "habit_tracker.db";
    /**
     * Database version. If you change the database schema, you must increment the database version
     */
    private static final int DATABASE_VERSION = 1;

    /**
     * Constructs a new instance of HabitTrackerDbHelper context of the app
     */
    public HabitTrackerDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Helper method to insert hardcoded habit data into the database
     */
    public void insertHabit(String habit_name, String any_exercise, int habit_repeats) {


        // Create a ContentValues object where column names are the keys,
        // and habit attributes are the values
        ContentValues values = new ContentValues();
        values.put(Habits.COLUMN_HABIT_NAME, habit_name);
        values.put(Habits.COLUMN_HABIT_INVOLVES_EXERCISE, any_exercise);
        values.put(Habits.COLUMN_HABIT_REPEATED_HOW_MANY_TIMES, habit_repeats);

        // Gets the database in write mode
        SQLiteDatabase db = getWritableDatabase();

        long newRowId = db.insert(Habits.TABLE_NAME, null, values);
        // Show a toast message depending on whether or not the insertion was successful
        if (newRowId == -1) {
            // If the row ID is -1, then there was an error with insertion.
            Log.d(LOG_TAG, "Row insert failed, ID = " + newRowId);
        } else {
            // Otherwise, the insertion was successful and we can display a toast with the row ID.
            Log.d(LOG_TAG, "Row insert SUCCESS, ID = " + newRowId);
        }
    }

    /**
     * This is called when the database is created
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create a String that contains the SQL statement to create the pets table
        String SQL_CREATE_HABITS_TABLE = "CREATE TABLE " + Habits.TABLE_NAME + " ("
                + Habits._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Habits.COLUMN_HABIT_NAME + " TEXT NOT NULL, "
                + Habits.COLUMN_HABIT_INVOLVES_EXERCISE + " TEXT NOT NULL, "
                + Habits.COLUMN_HABIT_REPEATED_HOW_MANY_TIMES + " INTEGER NOT NULL DEFAULT 1);";

        // Execute the SQL statement
        db.execSQL(SQL_CREATE_HABITS_TABLE);
    }

    /**
     * called when database needs to be upgraded
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // The database is still at version 1, so there's nothing to do be done here
    }

    /*  gets requested data from db prior to display in logs */
    public Cursor readHabits() {
        SQLiteDatabase databaseInfo = getReadableDatabase();

        String[] projection = {
                Habits._ID,
                Habits.COLUMN_HABIT_NAME,
                Habits.COLUMN_HABIT_INVOLVES_EXERCISE,
                Habits.COLUMN_HABIT_REPEATED_HOW_MANY_TIMES};

        Cursor cursor = databaseInfo.query(Habits.TABLE_NAME, projection, null, null, null, null, null);
        return cursor;
    }
}
