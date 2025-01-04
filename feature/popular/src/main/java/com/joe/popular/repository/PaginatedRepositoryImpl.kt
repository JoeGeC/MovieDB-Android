package com.joe.popular.repository

import com.joe.core.entity.Either
import com.joe.core.entity.ErrorEntity
import com.joe.popular.data.PaginatedRemote
import com.joe.popular.domain.entity.MediaListEntity
import com.joe.popular.local.PaginatedLocal
import com.joe.popular.repository.converter.PaginatedRepositoryConverter
import com.joe.popular.repository.response.PaginatedResponse

class PaginatedRepositoryImpl<LocalModel, ResponseListItem, Response : PaginatedResponse<ResponseListItem>>(
    private val remote: PaginatedRemote<ResponseListItem, Response>,
    private val local: PaginatedLocal<LocalModel>,
    private val converter: PaginatedRepositoryConverter<LocalModel, Response>
) : PaginatedRepository {

    override suspend fun getItems(page: Int): Either<MediaListEntity?, ErrorEntity?> =
        fetchLocal(page).fold(
            onSuccess = { Either.Success(it) },
            onFailure = { fetchRemote(page) }
        )

    private fun fetchLocal(page: Int): Either<MediaListEntity?, ErrorEntity?> {
        val localResult = local.get(page)
        return if (localResult is Either.Success) {
            Either.Success(converter.localToEntity(localResult.value))
        } else Either.Failure(ErrorEntity("No data in local"))
    }

    private fun fetchRemote(page: Int): Either<MediaListEntity?, ErrorEntity?> {
        val remoteResult = remote.getItems(page)
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
