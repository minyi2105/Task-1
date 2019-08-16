package com.example.fourtitudetask1.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.fourtitudetask1.dao.DummyDao;
import com.example.fourtitudetask1.model.Dummy;

@Database(entities = {Dummy.class}, version = 1)
public abstract class DummyDatabase extends RoomDatabase {

    public abstract DummyDao dummyDao();

    private static DummyDatabase INSTANCE;

    public static DummyDatabase getAppDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), DummyDatabase.class, "dummy-database").build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}
