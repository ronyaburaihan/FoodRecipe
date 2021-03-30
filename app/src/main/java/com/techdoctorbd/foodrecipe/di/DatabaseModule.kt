package com.techdoctorbd.foodrecipe.di

import android.content.Context
import androidx.room.Room
import com.techdoctorbd.foodrecipe.data.database.RecipesDatabase
import com.techdoctorbd.foodrecipe.utils.Constants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(
            context,
            RecipesDatabase::class.java,
            DATABASE_NAME
        ).build()

    @Singleton
    @Provides
    fun provideRecipesDao(database: RecipesDatabase) = database.recipesDao()

}