package androidbasicsnd.lloyd.alan.com.udacity.habittracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.database.sqlite.SQLiteDatabase;

import androidbasicsnd.lloyd.alan.com.udacity.habittracker.HabitTrackerContract.Habits;

import android.util.Log;
import android.database.Cursor;

import static androidbasicsnd.lloyd.alan.com.udacity.habittracker.HabitTrackerDbHelper.LOG_TAG;

/**
 * Displays list of habits that were entered and stored in the app
 */
public class MainActivity extends AppCompatActivity {

    HabitTrackerDbHelper mDbHelper; //to access db

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // To access our database, we instantiate our subclass of SQLiteOpenHelper
        // and pass the context, which is the current activity
        mDbHelper = new HabitTrackerDbHelper(this);

        deleteDatabase(mDbHelper.getDatabaseName()); //deletes old table data prior to rewrite below
        //rows of data to write to db
        mDbHelper.insertHabit("Swimming", "yes", 82);
        mDbHelper.insertHabit("watching less TV", "no", 2);
        //requested data
        Cursor cursor = mDbHelper.readHabits();

        try {
            int idColumnIndex = cursor.getColumnIndex(Habits._ID);
            int habitNameColumnIndex = cursor.getColumnIndex(Habits.COLUMN_HABIT_NAME);
            int anyExerciseColumnIndex = cursor.getColumnIndex(Habits.COLUMN_HABIT_INVOLVES_EXERCISE);
            int habitRepeatColumnIndex = cursor.getColumnIndex(Habits.COLUMN_HABIT_REPEATED_HOW_MANY_TIMES);

            //display data by item, and by row, in d.log
            Log.d(LOG_TAG, "_ID |  Name  | Involves Exercise | Repeated how many times");
            while (cursor.moveToNext()) {
                int id = cursor.getInt(idColumnIndex);
                String name = cursor.getString(habitNameColumnIndex);
                String exercise = cursor.getString(anyExerciseColumnIndex);
                int repeat = cursor.getInt(habitRepeatColumnIndex);

                Log.d(LOG_TAG, id + " " + name + " " + exercise + " " + repeat);
            }//end of while stmt
        }// end of try stmt
        finally {
            cursor.close();  //delete returned read data
        }

    }//end of onCreate()

    @Override
    protected void onStart() {
        super.onStart();
        // displayDatabaseInfo();
    }

    public void onUpgrade(SQLiteDatabase db, int i, int j) {
    }

}//end of class

