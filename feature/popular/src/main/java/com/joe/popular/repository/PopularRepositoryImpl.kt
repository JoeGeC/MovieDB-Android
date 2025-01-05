package com.joe.popular.repository

import com.joe.core.entity.Either
import com.joe.core.entity.ErrorEntity
import com.joe.popular.data.PopularRemote
import com.joe.popular.domain.entity.MediaListEntity
import com.joe.popular.local.PopularLocal
import com.joe.popular.repository.converter.PopularRepositoryConverter
import com.joe.popular.repository.response.PopularResponse

class PopularRepositoryImpl<LocalModel, ResponseListItem, Response : PopularResponse<ResponseListItem>>(
    private val remote: PopularRemote<ResponseListItem, Response>,
    private val local: PopularLocal<LocalModel>,
    private val converter: PopularRepositoryConverter<LocalModel, Response>
) : PopularRepository {

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
