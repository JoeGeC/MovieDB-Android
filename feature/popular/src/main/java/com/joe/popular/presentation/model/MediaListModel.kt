package com.joe.popular.presentation.model

data class MediaListModel(
    val page: Int,
    val items: List<MediaListItemModel>,
    val isFinalPage: Boolean,
){
    override fun equals(other: Any?): Boolean =
        other is MediaListModel
                && this.page == other.page
                && this.items == other.items
                && this.isFinalPage == other.isFinalPage

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + page
        result = 31 * result + items.hashCode()
        result = 31 * result + isFinalPage.hashCode()
        return result
    }
}