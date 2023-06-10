package com.example.effectiveclub.data.basket

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow


@Dao
interface ItemDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(dish:Basket)

    @Update
    suspend fun update(dish:Basket)

    @Update
    suspend fun delete(dish: Basket)

    @Query("delete from basket")
    suspend fun deleteAll()

    @Query("select * from basket where id = :id")
    fun getItem(id:Int): Flow<Basket>

    @Query("SELECT * FROM basket ORDER BY name ASC")
    fun getAllBasket(): Flow<List<Basket>>

}