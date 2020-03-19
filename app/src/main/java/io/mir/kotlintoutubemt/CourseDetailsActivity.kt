package io.mir.kotlintoutubemt

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class CourseDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
//        recyclerView_main.setBackgroundColor(Color.RED)
        recyclerView_main.layoutManager = LinearLayoutManager(this)
        recyclerView_main.adapter = CourseDetailsAdapter()
    }
    private class CourseDetailsAdapter : RecyclerView.Adapter<CourseLessonViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseLessonViewHolder {

            val layoutInflater = LayoutInflater.from(parent?.context)
            val cutomView= layoutInflater.inflate(R.layout.course_lesson_rpw,parent,false)
/*
            val blueView = View(parent?.context)
            blueView.setBackgroundColor(Color.BLUE)
            blueView.minimumHeight = 50*/
            return CourseLessonViewHolder(cutomView)
        }

        override fun getItemCount(): Int {
            return 5
        }

        override fun onBindViewHolder(holder: CourseLessonViewHolder, position: Int) {
        }


    }

    private class CourseLessonViewHolder(val customView: View): RecyclerView.ViewHolder(customView){

    }
}