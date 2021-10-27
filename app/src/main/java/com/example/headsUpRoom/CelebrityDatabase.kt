package com.example.headsUpRoom

import android.content.Context
import android.provider.ContactsContract
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase



@Database(entities = [CelebrityDetails::class],version = 1,exportSchema = false)
abstract class CelebrityDatabase:RoomDatabase() {

    companion object{
        var instance:CelebrityDatabase?=null;
        fun getInstance(ctx: Context):CelebrityDatabase
        {
            if(instance!=null)
            {
                return  instance as CelebrityDatabase;
            }
            instance= Room.databaseBuilder(ctx,CelebrityDatabase::class.java,"details").run { allowMainThreadQueries() }.build();
            return instance as CelebrityDatabase;
        }
    }
    abstract fun CelebrityDao():CelebrityDao;
}