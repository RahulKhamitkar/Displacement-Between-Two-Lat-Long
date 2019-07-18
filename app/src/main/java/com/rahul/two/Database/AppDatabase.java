package com.rahul.two.Database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = Details.class,version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract DetailsDao detailsDao();
}
