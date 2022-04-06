package com.example.newsappinkotlin.ui.destinations

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.example.newsappinkotlin.R
import com.example.newsappinkotlin.database.NewsDatabase
import com.example.newsappinkotlin.model.NewsModel
import com.example.newsappinkotlin.ui.ViewModel.NewsViewModel
import kotlinx.android.synthetic.main.fragment_item_details.*

class ItemDetailsFragment() : Fragment() {

    lateinit var nModel:NewsModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_item_details, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        var dbNews: NewsDatabase = NewsDatabase.getSavedItems(requireActivity().applicationContext)
        var fmodel:NewsViewModel = ViewModelProvider(requireActivity()).get(NewsViewModel::class.java)
        // observes the changes in the NewsViewModel
        fmodel.getSelectedNewsModel().observe(requireActivity(),
            Observer<NewsModel> { it ->
                nModel = it })

        super.onViewCreated(view, savedInstanceState)
        addData()

        readFull.setOnClickListener{
            findNavController().navigate(R.id.action_itemDetailsFragment_to_webFragment3) }

        // checks if entity is present; if present it changes the color of the icon to purple
        if (dbNews.getNewsDao().getCount(nModel.title )==1 )
            bookmark_icon.setColorFilter(Color.parseColor("#6200EE"))

        // checks if entity is present; if present the entity will get saved , icon changes to purple and a toast will be shown
        bookmark_icon.setOnClickListener {
            if (dbNews.getNewsDao().getCount(nModel.title ) ==0 ) {
                dbNews.getNewsDao().insertNews(nModel)
                bookmark_icon.setColorFilter(Color.parseColor("#6200EE"))
                Toast.makeText(context, "News Saved", Toast.LENGTH_SHORT).show() }

            // checks if entity is present; if not present the entity will get deleted, icon changes to grey and a toast will be shown
            else {
                dbNews.getNewsDao().delete(nModel)
                bookmark_icon.setColorFilter(Color.parseColor("#484a49"))
                Toast.makeText(context, "News Unsaved", Toast.LENGTH_SHORT).show() }
        }
    }

    // adds the data to the ItemViewDetailsFragment
    fun addData(){
        date_time.text = nModel.publishedAt
        des.text = nModel.desciption
        website2.text = nModel.source.name
        var image = nModel.urlToImage
        Glide.with(requireActivity())
            .load(image)
            .transform(CenterCrop())
            .into(imageView3)}
}
