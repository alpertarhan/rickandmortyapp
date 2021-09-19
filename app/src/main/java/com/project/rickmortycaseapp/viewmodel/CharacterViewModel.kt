package com.project.rickmortycaseapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.project.rickmortycaseapp.network.ApiService
import com.project.rickmortycaseapp.network.CharacterPagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class CharacterViewModel
@Inject constructor(private val api:ApiService):ViewModel(){

    //infinite scroll
    val dataList = Pager(PagingConfig(pageSize = 1)){
            CharacterPagingSource(api)
    }.flow.cachedIn(viewModelScope)

}