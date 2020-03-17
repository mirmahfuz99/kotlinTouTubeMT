package io.mir.kotlintoutubemt

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.video_row.view.*

class MainAdapter(val homeFeed: HomeFeed) : RecyclerView.Adapter<CustomViewHolder>() {

    val videoTitles = listOf("First Item", "Second Item", "Third Item")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent?.context)
        val cellForRow = layoutInflater.inflate(R.layout.video_row, parent, false)
        return CustomViewHolder(cellForRow)
    }


    override fun getItemCount(): Int {
        return homeFeed.videos.size
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
//        val videoTitles = videoTitles.get(position)
        val video = homeFeed.videos.get(position)
        holder?.view?.textView_video_title?.text = video.name
    }

}

class CustomViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

}

//https://www.youtube.com/watch?v=jS0buQyfJfs&list=PLTlO09YjSC4Rk2BRz1AHQnwoqS92L1jIz