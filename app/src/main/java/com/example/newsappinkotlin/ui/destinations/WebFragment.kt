package com.example.newsappinkotlin.ui.destinations

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.newsappinkotlin.R
import com.example.newsappinkotlin.model.NewsModel
import com.example.newsappinkotlin.ui.ViewModel.NewsViewModel
import kotlinx.android.synthetic.main.fragment_web.*

class WebFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? { return inflater.inflate(R.layout.fragment_web, container, false) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        lateinit var nModel:NewsModel

        var fmodel:NewsViewModel = ViewModelProvider(requireActivity()).get(NewsViewModel::class.java)
        // observes the changes in the NewsViewModel
        fmodel.getSelectedNewsModel().observe(requireActivity(),
            Observer<NewsModel> { t -> nModel=t!! })

        super.onViewCreated(view, savedInstanceState)
        // retrieves the link of the webpage
        wb.settings.javaScriptEnabled = true
        wb.webViewClient = WebViewClient()
        wb.loadUrl(nModel.url.toString())
    }
}