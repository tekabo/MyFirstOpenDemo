package com.ysj.mergeadapterlibrary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.MergeAdapter
import androidx.recyclerview.widget.RecyclerView

class TestMergeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_merge)
        val titleItems  = generateTitleItems()
        val titleAdapter = TitleAdapter(titleItems)

        val bodyItems = generateBodyItems()
        val bodyAdapter = BodyAdapter(bodyItems)

        val mergeAdapter = MergeAdapter(titleAdapter, bodyAdapter)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = mergeAdapter

    }


    private fun generateTitleItems():List<String>{
        val list = ArrayList<String>()
        repeat(15){
            index->
            list.add("Title $index")
        }
        return  list
    }

    private fun generateBodyItems():List<String>{
        val list = ArrayList<String>()
        repeat(25){
            index->
            list.add("Body $index")
        }
        return  list
    }
}
