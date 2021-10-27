package com.example.headsUpRoom

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Celebrity")
data class CelebrityDetails (
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "ID") val ID: Int,
    @ColumnInfo(name = "name")
    val name: String?,
    @ColumnInfo(name = "taboo1")
    val taboo1: String?,
    @ColumnInfo(name = "taboo2")
    val taboo2: String?,
    @ColumnInfo(name = "taboo3")
    val taboo3: String?
    )