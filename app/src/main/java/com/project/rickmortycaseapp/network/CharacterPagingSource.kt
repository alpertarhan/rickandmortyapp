package com.project.rickmortycaseapp.network

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.project.rickmortycaseapp.models.Character
import com.project.rickmortycaseapp.network.ApiService
import java.lang.Exception

class CharacterPagingSource(private val apiService:ApiService)

    :PagingSource<Int,Character>() {
    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        return null;
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        return try {

            //setResponseData - set prev-page,next-page and current-page and catch when error occurs

            val currentPage = params.key ?: 1
            val response = apiService.getAllCharacters(currentPage)
            val responseData = mutableListOf<Character>()
            val data = response.body()?.results ?: emptyList()
            responseData.addAll(data)

            LoadResult.Page(
                data = responseData,
                prevKey = if (currentPage == 1) null else -1,
                nextKey = currentPage.plus(1)
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}