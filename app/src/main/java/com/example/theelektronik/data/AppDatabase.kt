package com.example.theelektronik.data

import android.content.Context
import android.provider.CalendarContract.Instances
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.theelektronik.data.dao.ProdukDao
import com.example.theelektronik.data.entity.Produk

@Database(entities = [Produk::class], version = 1,)
abstract class AppDatabase : RoomDatabase(){
    abstract fun produkDao(): ProdukDao

    companion object {
        private var instance: AppDatabase? = null

    fun getInstance(context: Context): AppDatabase {
        if (instance == null) {
            instance = Room.databaseBuilder(context, AppDatabase::class.java,"app-database")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build()
        }
        return instance!!
        }
    }
}