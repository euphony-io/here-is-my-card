package com.recipe.android.hereismycard.CardBox.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "card")
data class Card(
    @PrimaryKey(autoGenerate = true) var uid: Int? = 1,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "tel") var tel: String,
    @ColumnInfo(name = "email") var email: String,
    @ColumnInfo(name = "address") var address: String,
    @ColumnInfo(name = "bg") var bg: Int
)



