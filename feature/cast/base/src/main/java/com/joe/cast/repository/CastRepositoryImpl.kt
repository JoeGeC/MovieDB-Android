package com.joe.cast.repository

import com.joe.cast.data.CastRemote
import com.joe.cast.domain.entity.CastListEntity
import com.joe.cast.local.CastLocal
import com.joe.cast.repository.converter.CastRepositoryConverter
import com.joe.core.entity.Either
import com.joe.core.entity.ErrorEntity
import javax.inject.Inject

class CastRepositoryImpl @Inject constructor(
    private val remote: CastRemote,
    private val local: CastLocal,
    private val converter: CastRepositoryConverter
): CastRepository {

    override suspend fun getCastOf(mediaId: Int): Either<CastListEntity?, ErrorEntity?> =
        fetchLocal(mediaId).fold(
            onSuccess = { Either.Success(it) },
            onFailure = { fetchRemote(mediaId) }
        )

    private fun fetchLocal(mediaId: Int): Either<CastListEntity?, ErrorEntity?> {
        val localResult = local.getCastOf(mediaId)
        return if (localResult is Either.Success) {
            Either.Success(converter.localToEntity(localResult.value))
        } else Either.Failure(ErrorEntity("No data in local"))
    }

    private fun fetchRemote(page: Int): Either<CastListEntity?, ErrorEntity?> {
        val remoteResult = remote.getCastOf(page)
        remoteResult.fold(
            onSuccess = { remoteData ->
                local.insert(converter.remoteToLocal(remoteData))
                return Either.Success(converter.remoteToEntity(remoteData))
            },
            onFailure = { error ->
                return Either.Failure(ErrorEntity(error?.statusMessage ?: ""))
            }
        )
    }

}