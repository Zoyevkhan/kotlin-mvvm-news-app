package com.news.app.utils.extensions

import android.content.Context
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import java.lang.StringBuilder


public fun ImageView.loadWithGlide(url: String?) {
    url?.let {url->
        Glide.with(this)
            .load(url)
            .into(this)
    } ?: run {
        Toast.makeText(this.context, "URL Not Found", Toast.LENGTH_SHORT).show()
    }

}



public fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun View.showSnackBar(message: String?) {
    message?.let { message ->
        val snackbar = Snackbar.make(
            this, message,
            Snackbar.LENGTH_LONG
        ).setAction("Action", null)
      var  view= snackbar.view
        snackbar.show()
    }
}
fun View.setVisible(isShow: Boolean){
    if(isShow){
       this.visibility=VISIBLE
    }else{
        this.visibility=GONE
    }
}
