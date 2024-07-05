package com.bolicstudio.localstorage.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.bolicstudio.localstorage.constan.Db
import com.bolicstudio.localstorage.dao.ArticleDao
import com.bolicstudio.localstorage.entity.ArticleEntity

@Database(
    entities = [ArticleEntity::class,
    ],
    version = 5,
    exportSchema = false
)

abstract class PlaygroundDb : RoomDatabase() {

    companion object {
        fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                PlaygroundDb::class.java,
                Db.name
            ).build()
    }

    internal abstract fun articleDao(): ArticleDao
}