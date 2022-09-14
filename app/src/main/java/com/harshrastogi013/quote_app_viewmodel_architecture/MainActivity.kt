package com.harshrastogi013.quote_app_viewmodel_architecture

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.style.QuoteSpan
import android.view.View
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {
    lateinit var mainViewModel: MainViewModel
    private val quoteText:TextView
    get()=findViewById(R.id.quote)
    private val quoteAuthor:TextView
    get()=findViewById(R.id.quoteAuthor)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainViewModel=ViewModelProvider(this,MainViewModelFactory(application)).get(MainViewModel::class.java)/*activity bhi context se inherited hoti hain*/
        /*yaha pr context application ka send kre ge kyu ki vo destroy nhi hoga*/
        setQuote(mainViewModel.getQuotes())


    }
    fun setQuote(quote:quote)
    {
        quoteText.text= quote.text
        quoteAuthor.text=quote.author
    }

    fun onPrevious(view: View) {setQuote(mainViewModel.previousQuotes())}
    fun onNext(view: View) {setQuote(mainViewModel.nextQuotes())}
    fun onShare(view: View)
    {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_TEXT,mainViewModel.getQuotes().text)
        startActivity(intent)

    }

}