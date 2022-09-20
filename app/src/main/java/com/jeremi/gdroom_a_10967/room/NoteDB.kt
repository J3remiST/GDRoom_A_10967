package com.jeremi.gdroom_a_10967.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.internal.synchronized

@Database(
    entities = [Note::class],
    version = 1
)
abstract class NoteDB: RoomDatabase() {

    abstract fun noteDao() : NoteDao

    companion object {

        @Volatile private var instance : NoteDB? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?:
kotlin.synchronized(LOCK){
                    instance ?: buildDataBase(context).also {
                        instance = it
                    }
}

        private fun buildDataBase(context: Context) =
Room.databaseBuilder(
            context.applicationContext,
            NoteDB::class.java,
            "note12345.db"
        ).build()
    }
}