package com.leaf.cache.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.leaf.cache.database.entities.CityEntity

@Dao
interface CityDao {
    @Query("SELECT * FROM city where name = :name")
    suspend fun getCity(name: String): CityEntity?

    @Query("SELECT * FROM city")
    suspend fun getAllCities(): List<CityEntity>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCity(cityEntity: CityEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateCity(cityEntity: CityEntity)

    @Delete
    suspend fun deleteCity(cityEntity: CityEntity)
}