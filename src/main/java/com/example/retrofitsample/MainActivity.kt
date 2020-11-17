package com.example.retrofitsample


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.accessurl.ApiClient
import com.example.accessurl.DataAdapter
import com.example.accessurl.IRetrofitAPI
import com.example.accessurl.PostModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {


    var dataList = ArrayList<PostModel>()
    lateinit var recyclerView: RecyclerView  //to to avoid null checks and initializing a not-null property outside of a constructor
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // get the reference of RecyclerView
        recyclerView = findViewById(R.id.recycler_view)
         getData()

        //setting up the adapter
        recyclerView.adapter= DataAdapter.DataAdpter(dataList, this)
        recyclerView.layoutManager= LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)



    }

    private fun getData() {
        val call = ApiClient.getApiClient().create(IRetrofitAPI::class.java)
        call.getposts().enqueue(object : Callback<List<PostModel>> {

            override fun onResponse(call: Call<List<PostModel>>?, response: Response<List<PostModel>>?) {
                dataList.addAll(response!!.body()!!) // Appends all of the elements in the specified collection to the end of this list,
                recyclerView.adapter?.notifyDataSetChanged() //notify any registered observers data set has changed.Retrieves the previously set adapter or null if no adapter is set.
            }

            override fun onFailure(call: Call<List<PostModel>>?, t: Throwable?) {
               //TODO : toast message
            }

        })
    }


}




