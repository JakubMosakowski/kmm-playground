package com.jakmos.counter.kmm.shared.cache

import com.jakmos.counter.sqldelight.data.AppDatabase

class Database(databaseDriverFactory: DatabaseDriverFactory) {
    private val database = AppDatabase(databaseDriverFactory.createDriver())
    private val dbQuery = database.appDatabaseQueries

    internal fun clearDatabase() {
        dbQuery.transaction {
            dbQuery.removeAllUsers()
        }
    }

    suspend fun getAllUsers(): List<User> {
        return dbQuery.getUsers().executeAsList()
    }

    fun insertUser(fullName: String) {
        dbQuery.insertUser(fullName)
    }
}
