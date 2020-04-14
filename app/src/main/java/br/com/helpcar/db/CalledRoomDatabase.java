package br.com.helpcar.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import br.com.helpcar.model.Called;
import br.com.helpcar.model.User;

@Database(entities = {Called.class, User.class}, version = 1, exportSchema = false)
public abstract class CalledRoomDatabase extends RoomDatabase {
    public abstract CalledDAO calledDAO();
    public abstract UserDAO userDAO();

    private static final String DATABASE = "HelpCarDB";
    private static volatile CalledRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;

    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static CalledRoomDatabase getInstance(final Context context) {
        if (INSTANCE == null) {
            synchronized (CalledRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            CalledRoomDatabase.class, DATABASE)
                            .allowMainThreadQueries().build();
                }
            }
        }
        return INSTANCE;
    }
}
