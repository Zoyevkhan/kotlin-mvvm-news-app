package com.news.app.ui.home

import android.content.Context
import android.util.Log
import androidx.lifecycle.*
import com.google.gson.Gson
import com.news.app.R
import com.news.app.database.sessionManager.PreferenceManager
import com.news.app.pojo.Article
import com.news.app.pojo.NewsResponse
import com.news.app.utils.Helper
import com.news.app.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    @ApplicationContext val context: Context,
    val repo: HomeRepo,
    val session: PreferenceManager
) : ViewModel() {

    private var news: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    private var newsSearch: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    private var saveNews: MutableLiveData<List<Article>> = MutableLiveData()
    private var voiceText: MutableLiveData<String> = MutableLiveData()



    init {
        Log.d("zoyeb", ": object creation")
        getNewsByCountry("IN")
    }

    fun getNewsByCountry(country: String,category: String="") {
        news.value = Resource.isLoading()
        viewModelScope.launch {
            if (Helper.isOnline(context)) {
                try {
                    var apiResponse = repo.getNewsByCountry(country,category)
                    if (apiResponse.isSuccessful) {
                        apiResponse.body()?.let { newsResponse ->
                            news.value = Resource.Success(newsResponse)
                        } ?: run {
                            news.value = Resource.Eror("data is null")
                        }
                    } else {
                        news.value = Resource.Eror(apiResponse.message())

                    }
                } catch (e: Exception) {
                    news.value = Resource.Eror(e.message)
                }

            } else {
                news.value = Resource.Eror(context.resources.getString(R.string.NO_INERNET))

            }
        }
    }

    fun getNewsBySearch(query: String?) {
        newsSearch.value = Resource.isLoading()
        viewModelScope.launch {
            if (Helper.isOnline(context)) {
                try {
                    var apiResponse = repo.getNewsBySearch(query!!)
                    if (apiResponse.isSuccessful) {
                        apiResponse.body()?.let { newsResponse ->
                            newsSearch.value = Resource.Success(newsResponse)
                        } ?: run {
                            newsSearch.value = Resource.Eror("data is null")
                        }
                    } else {
                        newsSearch.value = Resource.Eror(apiResponse.message())

                    }
                } catch (e: Exception) {
                    newsSearch.value = Resource.Eror(e.message)
                }

            } else {
                newsSearch.value = Resource.Eror(context.resources.getString(R.string.NO_INERNET))

            }
        }
    }
    fun addArticleToDatabase(article: Article){
        viewModelScope.launch {
            repo.addArticleToDatabase(article)
        }
    }
    fun getAllArtciles(){
            viewModelScope.launch {
                saveNews.value=repo.getAllArticle()
            }
    }


    fun getNews():MutableLiveData<Resource<NewsResponse>> {
        return news
    }
    fun getNewsSearch():MutableLiveData<Resource<NewsResponse>> {
        return newsSearch
    }

    fun getVoiceText():MutableLiveData<String> {
        return voiceText
    }
    fun setVoiceText(text:String){
      voiceText.value=text
    }
    fun getSavedNews()=saveNews
    fun deleteArticle(deleteItem: Article) {
        viewModelScope.launch {
            repo.deleteArticle(deleteItem)
        }

    }

}