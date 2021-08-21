package com.recipe.android.hereismycard.CardBox.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CardDao {
    @Query("SELECT * FROM card")
    fun getCardAll(): List<Card>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCard(cards:Card)
}