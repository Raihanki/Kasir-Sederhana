package org.d3if2033.kasirsederhana.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [MenuEntity::class], version = 1, exportSchema = false)
abstract class MenuDb : RoomDatabase() {
    abstract val dao: MenuDao
    companion object {
        @Volatile
        private var INSTANCE: MenuDb? = null
        fun getInstance(context: Context): MenuDb {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        MenuDb::class.java,
                        "menu.db"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}