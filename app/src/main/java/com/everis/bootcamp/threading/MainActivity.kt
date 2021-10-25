package com.everis.bootcamp.threading

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.activity_main.*
import com.everis.bootcamp.threading.AstroPeople as AstroPeople

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        button_load_data.setOnClickListener {
            launchAstroTask()
        }
    }


    fun showData(list: List<AstroPeople>?) {
        textview_data.text = ""
        list?.forEach { people ->

            textview_data.append("${people.name} - ${people.craft} \n\n")
        }
    }

    fun showLoadingIndicator() {
        progress_bar_load_indicator.visibility = View.VISIBLE
    }

    fun hideLoadingIndicator() {
        progress_bar_load_indicator.visibility = View.GONE
    }

    fun launchAstroTask() {
        val task = TaskAstros()
        task.execute()
    }


    inner class TaskAstros() : AsyncTask<Void,Int, List<AstroPeople>>() {
    private val repository = AstrosRepository()

        override fun onPreExecute() {
            super.onPreExecute()
            showLoadingIndicator()
        }
        override fun onProgressUpdate(vararg values : Int?) {
            super.onProgressUpdate(*values)

        }
        override fun doInBackground(vararg p0 : Void?) : List<AstroPeople>? {
            return repository.loadData()
        }

        override fun onPostExecute(result : List<AstroPeople>?) {
            super.onPostExecute(result)
            hideLoadingIndicator()
            showData(result)
        }
    }
}
