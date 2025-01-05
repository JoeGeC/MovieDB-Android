package com.joe.base.repository

import com.joe.base.data.DetailsRemote
import com.joe.base.local.DetailsLocal
import com.joe.base.repository.converter.DetailsRepositoryConverter
import com.joe.core.entity.Either
import com.joe.core.entity.ErrorEntity

class DetailsRepositoryImpl<Entity, LocalModel, Response>(
    private val remote: DetailsRemote<Response>,
    private val local: DetailsLocal<LocalModel>,
    private val converter: DetailsRepositoryConverter<Entity, LocalModel, Response>
) : DetailsRepository<Entity> {

    override suspend fun getDetails(mediaId: Int): Either<Entity?, ErrorEntity?> =
        fetchLocal(mediaId).fold(
            onSuccess = { Either.Success(it) },
            onFailure = { fetchRemote(mediaId) }
        )

    private fun fetchLocal(mediaId: Int): Either<Entity?, ErrorEntity?> {
        val localResult = local.getById(mediaId)
        return if (localResult is Either.Success) {
            Either.Success(converter.localToEntity(localResult.value))
        } else Either.Failure(ErrorEntity("No data in local"))
    }

    private fun fetchRemote(mediaId: Int): Either<Entity?, ErrorEntity?> {
        val remoteResult = remote.getDetails(mediaId)
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
