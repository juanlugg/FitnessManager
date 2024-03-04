package com.juanlugg8.fitnessmanager.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.juanlugg8.fitnessmanager.entity.Profile
import com.juanlugg8.fitnessmanager.entity.ProfileDao
import com.juanlugg8.fitnessmanager.entity.User
import com.juanlugg8.fitnessmanager.entity.UserDao
import com.juanlugg8.fitnessmanager.repository.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

@Database(entities = [User::class, Profile::class], version = 1, exportSchema = false)
@TypeConverters(UserConverter::class)
abstract class FitnessDatabase : RoomDatabase() {

    abstract fun userDao() : UserDao
    abstract fun profileDao() : ProfileDao
    companion object {
        @Volatile
        private var INSTANCE: FitnessDatabase? = null
        fun getInstance(): FitnessDatabase {
            return INSTANCE ?: synchronized(FitnessDatabase::class) {
                val instance = buildDatabase()
                INSTANCE = instance
                instance
            }
        }

        private fun buildDatabase(): FitnessDatabase {
            return Room.databaseBuilder(
                Locator.requiredApplication, FitnessDatabase::class.java, "FitnessManager"

            ).fallbackToDestructiveMigration().allowMainThreadQueries()
                .addTypeConverter(UserConverter())
                .addCallback(RoomDbInitializer(INSTANCE))
                .build()
        }

        class RoomDbInitializer(val instance: FitnessDatabase?) : RoomDatabase.Callback() {

            private val applicationScope = CoroutineScope(SupervisorJob())

            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                applicationScope.launch(Dispatchers.IO) {
                    populateDatabase()
                }
            }
        }

        private fun populateDatabase() {

            getInstance().userDao().insert(User(1,
                "Juanlu","666777888"
            ))
            getInstance().profileDao().insert(
                Profile(1,UserRepository.getUser(1), "04/03/2024",180.0,80.0)
            )
        }
    }
}