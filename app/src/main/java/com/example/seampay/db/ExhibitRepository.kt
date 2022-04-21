package com.example.seampay.db

import androidx.room.withTransaction
import com.example.seampay.model.ExhibitsLoader
import com.example.seampay.util.networkBoundResource
import kotlinx.coroutines.delay
import javax.inject.Inject

class ExhibitRepository @Inject constructor(
    private val api: ExhibitsLoader,
    private val db: ExhibitDatabase
) {
    private val exhibitDao = db.exhibitDao()

    fun getExhibit() = networkBoundResource(
        query = {
            exhibitDao.getExhibits()
        },
        fetch = {
            delay(2000)
            api.getExhibitList()
        },
        saveFetchResult = {exhibits ->
            db.withTransaction {
                exhibitDao.insertExhibits(exhibits)
            }
        }
    )
}