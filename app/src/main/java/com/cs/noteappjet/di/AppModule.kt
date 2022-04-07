package com.cs.noteappjet.di

import android.content.Context
import androidx.room.Room
import com.cs.noteappjet.data.NoteDatabase
import com.cs.noteappjet.data.NoteDatabaseDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

// dependency injection uses dagger and hilt to perform operations

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    // if module is not created then all the values should be written in NoteDatabase
    // 1. this function give us dao
    @Singleton   // singleton is used for creating just one instance(object) of class
    @Provides // used for provide something to the application //so here we create function to provide notes
    fun provideNotesDao(noteDatabase: NoteDatabase): NoteDatabaseDao = noteDatabase.noteDao()


    // 2. this function give us database
    // application context is used to get all data of application
    //here we are creation the actual database
    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): NoteDatabase =
        Room.databaseBuilder(
            context,
            NoteDatabase::class.java,
            "notes_db"
        ).fallbackToDestructiveMigration()
            .build()

}