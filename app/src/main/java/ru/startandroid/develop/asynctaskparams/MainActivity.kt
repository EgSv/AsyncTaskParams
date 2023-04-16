package ru.startandroid.develop.asynctaskparams

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private var mt: MyTask? = null
    var tvInfo: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvInfo = findViewById<View>(R.id.tvInfo) as TextView
    }

    fun onClick(v: View) {
        mt = MyTask()
        mt!!.execute("file_path_1", "file_path_2", "file_path_3", "file_path_4")
    }

    internal inner class MyTask : AsyncTask<String?, Int?, Void?>() {
        override fun onPreExecute() {
            super.onPreExecute()
            tvInfo!!.text = "Begin"
        }

        override fun doInBackground(vararg urls: String?): Void? {
            try {
                var cnt = 0
                for (i in urls) {
                    downloadFile(i!!)
                    publishProgress(++cnt)
                }
                Thread.sleep(1000)
            } catch (e:InterruptedException) {
                e.printStackTrace()
            }
            return null
        }

        override fun onProgressUpdate(vararg values: Int?) {
            super.onProgressUpdate(*values)
            tvInfo!!.text = "Downloaded ${values[0]} files"
        }

        override fun onPostExecute(result: Void?) {
            super.onPostExecute(result)
            tvInfo!!.text = "End"
        }

        private fun downloadFile(url: String) {
            Thread.sleep(2000)
        }
    }
}