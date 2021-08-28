package com.recipe.android.hereismycard.CardBox.db

import androidx.room.*

@Dao
interface CardDao {
    @Query("SELECT * FROM card")
    fun getCardAll(): List<Card>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCard(cards:Card)

    @Delete
    fun deleteCard(cards:Card)
}