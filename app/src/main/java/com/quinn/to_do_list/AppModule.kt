package com.quinn.to_do_list

import android.content.Context
import androidx.room.Room
import com.quinn.to_do_list.data.local.dao.TasksDao
import com.quinn.to_do_list.data.local.database.AppDatabase
import com.quinn.to_do_list.data.repository.TaskRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class) // Available app-wide
object AppModule {

    // App Local Database
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext app: Context): AppDatabase {
        return Room.databaseBuilder(
            app,
            AppDatabase::class.java,
            "task_database"
        ).build()
    }

    // Provide Task DAO
    @Provides
    fun provideTaskDao(db: AppDatabase): TasksDao {
        return db.tasksDao()
    }

    // Provide Task Repository
    @Provides
    @Singleton
    fun provideTaskRepository(taskDao: TasksDao): TaskRepository {
        return TaskRepository(taskDao)
    }
}
