package com.news.app.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import androidx.activity.viewModels
import com.news.app.database.sessionManager.PreferenceManager
import com.news.app.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.news.app.network.ApiService
import com.news.app.utils.extensions.toast
import com.hbb20.CountryCodePicker
import com.news.app.pojo.Article
import java.util.*
import android.view.MenuItem
import android.view.Window

import androidx.core.content.ContextCompat

import android.view.WindowManager
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.news.app.R
import com.news.app.ui.fragments.NewsFragment
import com.news.app.ui.fragments.NewsSavedFragment
import com.news.app.ui.fragments.NewsSearchFragment
import com.news.app.ui.listners.NewsAdapterListener
import com.news.app.utils.extensions.setVisible


@AndroidEntryPoint
class HomeActivity : AppCompatActivity(), CountryCodePicker.OnCountryChangeListener,
    NewsAdapterListener, BottomNavigationView.OnNavigationItemSelectedListener {


    @Inject
    lateinit var pref: PreferenceManager
    var _binding: ActivityMainBinding? = null
    val binding get() = _binding!!
    lateinit var selectedCountryName: String
    var lastSelectedCountryName: String = "India"
    val mHomeViewModel: HomeViewModel by viewModels()
    lateinit var textToSpeech: TextToSpeech

    @Inject
    lateinit var apiService: ApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = DataBindingUtil.setContentView(this, com.news.app.R.layout.activity_main)
        val window: Window = getWindow()
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.black))
        setContentView(binding.root)
        binding.bottomNav.setOnItemSelectedListener(this)
        //  setRecycler()
        initViews()
        registerObserver()
        addFragment(NewsFragment())

    }



    private fun registerObserver() {
        mHomeViewModel.getVoiceText().observe(this, Observer { voiceData ->
            textToSpeech.speak(voiceData, TextToSpeech.QUEUE_FLUSH, null);
        })
    }

    private fun initViews() {

        binding.toolbar.ccp.setOnCountryChangeListener(this)
        textToSpeech = TextToSpeech(applicationContext) { i ->
            if (i != TextToSpeech.ERROR) {
                textToSpeech.language = Locale.ENGLISH
            }
        }
    }

    override fun onCountrySelected() {
        selectedCountryName = binding.toolbar.ccp.selectedCountryEnglishName
        if (!lastSelectedCountryName.equals(selectedCountryName, true)) {
            mHomeViewModel.getNewsByCountry(binding.toolbar.ccp.selectedCountryNameCode)
        } else {
            toast("You Have Selected Same Country")
        }
        lastSelectedCountryName = selectedCountryName

    }

    override fun onNewsItemClick(article: Article) {
        startActivity(Intent(this,WebviewActivity::class.java).apply {
            putExtra("URL",article.url)
        })


    }

    override fun onClickItemToSave(article: Article) {
        mHomeViewModel.addArticleToDatabase(article)
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.home1 -> navigatetoFragment(NewsFragment())
            R.id.searc1 -> navigatetoFragment(NewsSearchFragment(this))
            R.id.saved1 -> navigatetoFragment(NewsSavedFragment(this))

        }
        return true
    }

    fun navigatetoFragment(nextFragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container, nextFragment)
            commit()
        }
    }

    fun addFragment(nextFragment: Fragment) {
        if (supportFragmentManager.findFragmentById(R.id.fragment_container) == null) {
            supportFragmentManager.beginTransaction().apply {
                add(R.id.fragment_container, nextFragment)
                commit()
            }
        }
    }

    fun setTitle(title: String) {
        binding.toolbar.title.text = title
    }

    fun setBottomMenuVisibility(state: Boolean) {
        if (state) {
            binding.bottomNav.visibility = VISIBLE
        } else {
            binding.bottomNav.visibility = GONE
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    fun showCountryPicker(shouldShow: Boolean) {
        binding.toolbar.ccp.setVisible(shouldShow)
    }

}