package com.ebogoliub.spacex.data.daos

import androidx.room.*
import com.ebogoliub.spacex.data.entities.SpacexEntity

@Dao
abstract class BaseDao <in T : SpacexEntity> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insert(entity: T): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertAll(vararg entity: T)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertAll(entities: List<T>)

    @Update
    abstract suspend fun update(entity: T)

    @Delete
    abstract suspend fun deleteEntity(entity: T): Int

    @Transaction
    open suspend fun withTransaction(tx: suspend () -> Unit) = tx()

    suspend fun insertOrUpdate(entity: T): Long {
        return if (entity.id == 0L) {
            insert(entity)
        } else {
            update(entity)
            entity.id
        }
    }

    @Transaction
    open suspend fun insertOrUpdate(entities: List<T>) {
        entities.forEach {
            insertOrUpdate(it)
        }
    }
}