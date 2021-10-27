package com.example.headsUpRoom

import android.provider.ContactsContract
import androidx.room.*

@Dao
interface CelebrityDao {

    @Query("SELECT * FROM Celebrity")
    fun getCelebrity(): List<CelebrityDetails>

    @Insert
    fun insertCelebrity(celebrity: CelebrityDetails)

    @Delete
    fun deleteCelebrity(celebrity: CelebrityDetails)

    @Update
    fun updateCelebrity(celebrity: CelebrityDetails)

}