package com.joe.popular.local.database

import com.joe.popular.local.dao.PaginatedDao

interface PaginatedDatabase {
    fun getDao(): PaginatedDao
}