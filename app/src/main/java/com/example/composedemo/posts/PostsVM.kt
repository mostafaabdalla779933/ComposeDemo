package com.example.composedemo.posts

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composedemo.network.NetworkManager
import com.example.composedemo.network.PostModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostsVM @Inject constructor(private val networkRepo: NetworkManager) : ViewModel() {

  //  var state by mutableStateOf<PostViewState>(PostViewState.Loading)

    var statePaging by mutableStateOf(ScreenState())

    private val paginator : DefaultPaginator<Int,PostModel> = DefaultPaginator(
        initialKey = statePaging.page,
        onLoadUpdated = {
            statePaging = statePaging.copy(isLoading = it)
        },
        onRequest = { nextPage ->
            Result.success(networkRepo.getPosts(nextPage).body() ?: listOf())

        },
        getNextKey = {
            statePaging.page + 1
        },
        onError = {
            statePaging = statePaging.copy(error = it?.localizedMessage)
        },
        onSuccess = { items, newKey ->
            statePaging = statePaging.copy(
                items = statePaging.items + items,
                page = newKey,
                endReached = items.isEmpty()
            )
        }
    )

    init {
        loadNextItems()
//        viewModelScope.launchCatching(block = {
//            networkRepo.getPosts(1).apply {
//                state = if (isSuccessful)
//                    PostViewState.Posts(body() ?: listOf())
//                else
//                    PostViewState.Error("something went wrong")
//            }
//        }, {
//            state = PostViewState.Error("something went wrong")
//        })
    }

    fun loadNextItems() {
        viewModelScope.launch {
            paginator.loadNextItems()
        }
    }


}

inline fun CoroutineScope.launchCatching(
    noinline block: suspend CoroutineScope.() -> Unit,
    crossinline onError: (Throwable) -> Unit,
) {
    launch(
        CoroutineExceptionHandler { _, throwable -> onError(throwable) },
        block = block
    )
}