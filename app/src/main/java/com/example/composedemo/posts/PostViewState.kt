package com.example.composedemo.posts

import com.example.composedemo.network.PostModel

sealed class PostViewState {

    class Posts(val postsList:List<PostModel>):PostViewState()

    class Error(val message: String):PostViewState()

    object Loading :PostViewState()

}