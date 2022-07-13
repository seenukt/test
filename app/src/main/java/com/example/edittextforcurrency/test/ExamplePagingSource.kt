package com.example.edittextforcurrency.test

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.edittextforcurrency.ApiInterFace
import com.example.edittextforcurrency.model.Data


class ExamplePagingSource(
    private val backend: ApiInterFace,
    val query: String? =null
) : PagingSource<Int, Data>() {
    override suspend fun load(
        params: LoadParams<Int>
    ): LoadResult<Int, Data> {
        return try {

            val nextPageNumber = params.key ?: 1
            val response = backend.pagingData(page = nextPageNumber)
            LoadResult.Page(
                data = response.body()?.data as List<Data> ,
                prevKey = null, // Only paging forward.
                nextKey = if (response.body()?.totalPages==nextPageNumber) null else nextPageNumber.plus(1)
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Data>): Int? {

        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}