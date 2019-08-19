package com.example.fourtitudetask1.room;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.fourtitudetask1.dao.DummyDao;
import com.example.fourtitudetask1.model.Dummy;

import java.util.concurrent.Executors;

@Database(entities = {Dummy.class}, version = 1, exportSchema = false)
public abstract class DummyDatabase extends RoomDatabase {

    public abstract DummyDao dummyDao();

    private static DummyDatabase INSTANCE;

    public synchronized static DummyDatabase getAppDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = buildDatabase(context);
        }
        return INSTANCE;
    }

    private static DummyDatabase buildDatabase(final Context context) {
        return Room.databaseBuilder(context,
                DummyDatabase.class,
                "dummy-database")
                .addCallback(new Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        Executors.newSingleThreadScheduledExecutor().execute(new Runnable() {
                            @Override
                            public void run() {
                                getAppDatabase(context).dummyDao().insertAllDummies(Dummy.loadDummyList());
                            }
                        });
                    }
                })
                .build();
    }
}
