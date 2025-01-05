package com.joe.popular.local.database

import com.joe.popular.local.dao.PopularDao

interface PopularDatabase {
    fun getDao(): PopularDao
}