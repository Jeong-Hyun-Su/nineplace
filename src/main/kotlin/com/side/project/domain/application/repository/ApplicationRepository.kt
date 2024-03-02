package com.side.project.domain.application.repository

import com.side.project.domain.application.entity.StoreApplication
import java.util.*

interface ApplicationRepository {
    fun getStoreApplicationByIds(id: UUID): StoreApplication
    fun saveStoreApplication(storeApplication: StoreApplication): StoreApplication
    fun deleteStoreApplication(storeApplication: StoreApplication)
}