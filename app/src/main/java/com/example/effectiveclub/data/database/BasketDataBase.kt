package com.example.effectiveclub.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.effectiveclub.data.basket.Basket
import com.example.effectiveclub.data.basket.ItemDAO


@Database(entities = [Basket::class], version = 1, exportSchema = false)
abstract class BasketDataBase:RoomDatabase() {
    abstract fun itemDao():ItemDAO

    companion object{
        @Volatile
        private var Instance:BasketDataBase? = null

        fun getDataBase(context: Context):BasketDataBase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context = context, BasketDataBase::class.java,"basket")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also {
                        Instance = it
                    }
            }
        }
    }
}