package com.ebogoliub.spacex.data.daos

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.ebogoliub.spacex.data.entities.Rocket
import com.ebogoliub.spacex.data.entities.Launch
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.runBlocking

@Dao
abstract class RocketsDao : BaseDao<Rocket>() {

    @Query(
        """
        SELECT *
        FROM rocket
        ORDER BY rocket_name
    """
    )
    abstract fun all(): Flow<List<Rocket>>

    @Query(
        """
        SELECT *
        FROM rocket
        WHERE rocket_id = :rocketId
    """
    )
    abstract fun one(rocketId: String): Flow<Rocket?>

    @Query(
        """
        SELECT *
        FROM launch
        WHERE rocket_id = :rocketId AND upcoming = 0
        ORDER BY flight_number DESC
        LIMIT :limit
        OFFSET :offset
    """
    )
    abstract fun launchesForRocket(
        rocketId: String,
        limit: Int,
        offset: Int = 0
    ): Flow<List<Launch>>

    @Query(
        """
        SELECT *
        FROM rocket
        WHERE rocket_name LIKE :query
        ORDER BY rocket_name
        LIMIT :limit
        OFFSET :offset
    """
    )
    abstract fun forQuery(query: String, limit: Int, offset: Int = 0): Flow<List<Rocket>>

    @Query(
        """
        DELETE FROM rocket
    """
    )
    abstract suspend fun clearTable()

    @Transaction
    open fun clearAndInsert(allRockets: List<Rocket>) = runBlocking {
        clearTable()
        insertAll(allRockets)
    }
}