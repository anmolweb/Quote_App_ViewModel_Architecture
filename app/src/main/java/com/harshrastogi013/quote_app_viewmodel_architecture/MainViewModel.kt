package com.harshrastogi013.quote_app_viewmodel_architecture

import android.content.Context
import androidx.lifecycle.ViewModel
import com.google.gson.Gson

class MainViewModel(val context:Context):ViewModel() {
    var quoteList: Array<quote> = emptyArray()
    var index=0

    init {
        quoteList=loadQuotesFromAssets()
    }

    private fun loadQuotesFromAssets(): Array<quote> {
        val inputStream= context.assets.open("quote.json")/* we need context to read the data from the assest like json*/
        val size:Int = inputStream.available()/*finding the size  */
        val buffer=ByteArray(size)/* */
        inputStream.read(buffer)
        inputStream.close()
        val json= String(buffer,Charsets.UTF_8)/*json UTF_8 mein encode hote hian*/
        /*byte array ko encode kr k string formate mein return kare ga*/
        val gson= Gson()
         return gson.fromJson(json,Array<quote>::class.java)
        /*so like this we can read the data from assest folder*/
    }
    fun getQuotes()= quoteList[index]
    fun nextQuotes()=quoteList[++index]
    fun previousQuotes()=quoteList[--index]
}