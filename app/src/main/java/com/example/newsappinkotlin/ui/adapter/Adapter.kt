package com.example.newsappinkotlin.ui.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.example.newsappinkotlin.R
import com.example.newsappinkotlin.database.NewsDatabase
import com.example.newsappinkotlin.model.NewsModel
import kotlinx.android.synthetic.main.news_card.view.*

class Adapter(var News:MutableList<NewsModel>, var listener:(NewsModel)->Unit): RecyclerView.Adapter<Adapter.Viewholder>() {

    lateinit var dbNews: NewsDatabase

    // creates a viewholder and assigns its data
    class Viewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun onbind(NewsStory: NewsModel) {
            itemView.headlineTitle.text = NewsStory.title
            itemView.website.text = NewsStory.source.name
            Glide.with(itemView)
                .load(NewsStory.urlToImage)
                .transform(CenterCrop())
                .into(itemView.newsImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):Adapter.Viewholder =
        Adapter.Viewholder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.news_card,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: Viewholder, position: Int)  {

        dbNews= NewsDatabase.getSavedItems(holder.itemView.context)
        var Newsitem = News!![position]

        holder.onbind(Newsitem)
        holder.itemView.setOnClickListener {
            listener(Newsitem) }

        // checks if entity is present; if present it changes the color of the icon to purple
        if (dbNews.getNewsDao().getCount(Newsitem.title) == 1)
            holder.itemView.menuIcon.setColorFilter(Color.parseColor("#6200EE"))

        holder.itemView.menuIcon.setOnClickListener {
            // checks if entity is present; if present the entity will get saved , icon changes to purple and a toast will be shown
            if (dbNews.getNewsDao().getCount(Newsitem.title) == 0) {
                dbNews.getNewsDao().insertNews(Newsitem)
                holder.itemView.menuIcon.setColorFilter(Color.parseColor("#6200EE"))
                Toast.makeText(holder.itemView.context, "News Saved", Toast.LENGTH_SHORT).show() }

            // checks if entity is present; if not present the entity will get deleted, icon changes to grey and a toast will be shown
            else {
                dbNews.getNewsDao().delete(Newsitem)
                holder.itemView.menuIcon.setColorFilter(Color.parseColor("#484a49"))
                Toast.makeText(holder.itemView.context, "News Unsaved", Toast.LENGTH_SHORT).show() }
        }
    }

    override fun getItemCount(): Int = News!!.size

    // adds data to the recycler view
    fun appendNews(updateNews: List<NewsModel>){
        this.News!!.addAll(updateNews)
        notifyItemRangeInserted(
            this.News!!.size,
            News!!.size - 1
        )
    }
}
