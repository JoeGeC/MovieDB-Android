package com.joe.base.local

import com.joe.base.local.dao.DetailsDaoHelper
import com.joe.core.entity.Either
import com.joe.data.response.ErrorResponse

class DetailsLocalImpl<LocalModel>(
    private val daoHelper: DetailsDaoHelper<LocalModel>,
): DetailsLocal<LocalModel> {

    override fun insert(detailsModel: LocalModel?) {
        try {
            if(detailsModel == null) return
            daoHelper.insert(detailsModel)
        } catch (_: Exception){}
    }

    override fun getById(movieId: Int): Either<LocalModel?, ErrorResponse?> {
        val response = daoHelper.getById(movieId)
            ?: return Either.Failure(ErrorResponse("Null local response"))
        return Either.Success(response)
    }
}