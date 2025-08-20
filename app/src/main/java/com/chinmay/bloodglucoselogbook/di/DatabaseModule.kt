package com.chinmay.bloodglucoselogbook.di

import android.app.Application
import androidx.room.Room
import com.chinmay.bloodglucoselogbook.data.AppDatabase
import com.chinmay.bloodglucoselogbook.data.GlucoseMeasureDao
import org.koin.dsl.module

fun provideDataBase(application: Application): AppDatabase =
    Room.databaseBuilder(
        application,
        AppDatabase::class.java,
        "glucose-measure-db"
    ).fallbackToDestructiveMigration(false).build()

fun provideDao(appDatabase: AppDatabase): GlucoseMeasureDao = appDatabase.glucoseMeasureDao()


val dataBaseModule = module {
    single { provideDataBase(get()) }
    single<GlucoseMeasureDao> { provideDao(get()) }
}