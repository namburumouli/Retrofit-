package com.example.accessurl



import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.accessurl.R.layout.list_item_home


class DataAdapter {



    class DataAdpter(private var dataList: List<PostModel>, private val context: Context) : RecyclerView.Adapter<DataAdpter.ViewHolder>() {


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder { //it will create a new view holder for which the recycleview can reuse
            return ViewHolder(LayoutInflater.from(context).inflate(list_item_home, parent, false))
        }//Throws InflateException if there is an error.

        override fun getItemCount(): Int {
            return dataList.size
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) { // automatically reuse and  calls each time
            val dataModel= dataList[position]

            holder.titleTextView.text=dataModel.title
        }


        class ViewHolder(itemLayoutView: View) : RecyclerView.ViewHolder(itemLayoutView) {
            var titleTextView:TextView
            init {
                titleTextView=itemLayoutView.findViewById(R.id.title)

            }

        }

    }
}