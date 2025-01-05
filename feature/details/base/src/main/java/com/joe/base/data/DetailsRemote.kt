package com.joe.base.data

import com.joe.core.entity.Either
import com.joe.data.response.ErrorResponse

interface DetailsRemote<Response> {
    fun getDetails(mediaId: Int): Either<Response?, ErrorResponse?>
}