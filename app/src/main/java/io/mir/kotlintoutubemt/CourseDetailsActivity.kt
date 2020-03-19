package io.mir.kotlintoutubemt

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.GsonBuilder
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.course_lesson_rpw.view.*
import kotlinx.android.synthetic.main.video_row.view.*
import okhttp3.*
import java.io.IOException

class CourseDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
//        recyclerView_main.setBackgroundColor(Color.RED)
        recyclerView_main.layoutManager = LinearLayoutManager(this)
//        recyclerView_main.adapter = CourseDetailsAdapter()

        //we'll change the nav ber title..
        val navbertitle = intent.getStringExtra(CustomViewHolder.VIDEO_TITLE_KEY)
        supportActionBar?.title = navbertitle

        fetchJSON()
    }

    private fun fetchJSON() {
        val videoId = intent.getIntExtra(CustomViewHolder.VIDEO_ID, -1)
        val courseDetailsUrl =
            "https://api.letsbuildthatapp.com/youtube/course_detail?id=" + videoId
        println(courseDetailsUrl)

        var client = OkHttpClient()
        var request = Request.Builder().url(courseDetailsUrl).build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
            }
            override fun onResponse(call: Call, response: Response) {
                var body = response?.body?.string()

                val gson = GsonBuilder().create()
                val courseLesson = gson.fromJson(body, Array<CourseLesson>::class.java)
                runOnUiThread {
                    recyclerView_main.adapter = CourseDetailsAdapter(courseLesson)
                }
                println(body)
            }

        })

    }

    private class CourseDetailsAdapter(val courseLessons: Array<CourseLesson>): RecyclerView.Adapter<CourseLessonViewHolder>() {


        override fun getItemCount(): Int {
            return courseLessons.size
        }
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseLessonViewHolder {
            val layoutInflater = LayoutInflater.from(parent?.context)
            val cutomView = layoutInflater.inflate(R.layout.course_lesson_rpw, parent, false)
/*
            val blueView = View(parent?.context)
            blueView.setBackgroundColor(Color.BLUE)
            blueView.minimumHeight = 50*/
            return CourseLessonViewHolder(cutomView)
        }

        override fun onBindViewHolder(holder: CourseLessonViewHolder, position: Int) {
            val courseLesson = courseLessons.get(position)
            holder?.customView?.course_lesson_title?.text = courseLesson.name
            holder?.customView?.textView_duration?.text = courseLesson.duration

            val imageView = holder?.customView?.imageView_courseLissen_thumbnail
            Picasso.with(holder?.customView?.context).load(courseLesson.imageUrl).into(imageView)

            holder.courseLesson = courseLesson
        }



    }

     class CourseLessonViewHolder(val customView: View,var courseLesson:CourseLesson?=null) :
        RecyclerView.ViewHolder(customView) {

        companion object {
            val COURSE_LESSON_LINK_KEY = "COURSE_LESSON_LINK"
         }
        init {
            customView.setOnClickListener {
                println("Attempt to load webview somehow???")
                val intent = Intent(customView.context, CourseLessonActivity::class.java)
                intent.putExtra(COURSE_LESSON_LINK_KEY,courseLesson?.link)
                customView.context.startActivity(intent)
            }
        }
    }
}