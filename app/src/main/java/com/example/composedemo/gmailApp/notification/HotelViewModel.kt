package com.example.composedemo.gmailApp.notification


import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composedemo.network.NetworkManager
import com.example.composedemo.posts.launchCatching
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class HotelViewModel @Inject constructor(private val networkRepo: NetworkManager) : ViewModel() {

    var stateHotel by mutableStateOf<HotelState>(HotelState.Loading)


    init {
        getHotels()
    }


    private fun getHotels(){

        viewModelScope.launchCatching(block = {
            val response = networkRepo.getHotels()
            stateHotel = if(response.isSuccessful ){
                HotelState.Hotels(response.body()?: emptyList())
            }else{
                HotelState.Error("something wrong")
            }
        },
        onError = {
            stateHotel =  HotelState.Error("something wrong")
        })

    }


}