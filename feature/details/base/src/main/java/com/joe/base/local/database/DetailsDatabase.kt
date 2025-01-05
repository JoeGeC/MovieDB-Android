package com.joe.base.local.database

import com.joe.base.local.dao.DetailsDao

interface DetailsDatabase {
    fun getDao(): DetailsDao
}