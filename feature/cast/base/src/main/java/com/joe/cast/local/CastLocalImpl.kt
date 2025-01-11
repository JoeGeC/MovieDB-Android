package com.joe.cast.local

import com.joe.cast.local.model.CastListLocalModel
import com.joe.core.entity.Either
import com.joe.data.response.ErrorResponse

class CastLocalImpl(
    private val daoHelper: CastDaoHelper,
): CastLocal {

    override fun insert(castListModel: CastListLocalModel?) {
        try {
            if(castListModel == null) return
            daoHelper.insert(castListModel)
        } catch (_: Exception){}
    }

    override fun getCastOf(mediaId: Int): Either<CastListLocalModel?, ErrorResponse?> {
        try {
            val response = daoHelper.getById(mediaId)
                ?: return Either.Failure(ErrorResponse("Null local response"))
            return Either.Success(response)
        } catch (e: Exception) {
            return Either.Failure(ErrorResponse(e.localizedMessage ?: "Something went wrong in local"))
        }
    }
}