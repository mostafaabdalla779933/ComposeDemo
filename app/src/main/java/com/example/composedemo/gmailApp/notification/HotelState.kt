package com.example.composedemo.gmailApp.notification

import com.example.composedemo.data.Hotel


sealed class HotelState {

    class Hotels(val postsList:List<Hotel>):HotelState()

    class Error(val message: String):HotelState()

    object Loading :HotelState()

}
