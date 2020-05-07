package com.example.urbanlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DataAdpter(private var dataList: MutableList<DataModel1>,private var updn:String) : RecyclerView.Adapter<DataAdpter.ViewHolder>() {

    var sortUp:Boolean?=null
    var datalist:MutableList<DataModel1> = ArrayList()
    var upDown:MutableList<UpDown> = ArrayList()
    init {
        datalist = dataList
        sortUp = updn=="ThumbsUp"
        if(sortUp as Boolean) {
            datalist.sortBy {it.thumbs_up  }
            println("Sorting based on thumbs up")
        } else {
            datalist.sortBy { it.thumbs_down }
            println("Sorting based on thumbs dn")
        }
        println("Adapter Data:")

        for(d in datalist){
            upDown.add(UpDown(d.thumbs_up,d.thumbs_down))
            println("Input data: ${d.definition}")
        }
        println(upDown)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.items_list, parent, false))
    }

    override fun getItemCount(): Int {
        return datalist.size
    }
    fun sortData(){
        if(sortUp==true){
            sortUp = false
            datalist.sortBy { it.thumbs_down }
            println("sort by dn")
        }else{
            sortUp = true;
            datalist.sortBy { it.thumbs_up }
            println("sort by up")
        }
//        println("Sorted list is: ${datalist}")
        upDown.clear()
        for(d in datalist){
            upDown.add(UpDown(d.thumbs_up,d.thumbs_down))
        }
   //     println(upDown)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dataModel=datalist.get(position)

        holder.titleTextView.text=dataModel.definition
       // holder.titleTextView.movementMethod
        holder.thumsUpTextView.text = "Up:"+dataModel.thumbs_up
        holder.thumsDnTextView.text = "Dn:"+dataModel.thumbs_down
    }



    class ViewHolder(itemLayoutView: View) : RecyclerView.ViewHolder(itemLayoutView) {
        lateinit var titleTextView: TextView
        lateinit var thumsUpTextView:TextView
        lateinit var thumsDnTextView:TextView
        init {
            titleTextView=itemLayoutView.findViewById(R.id.desCription)
            thumsUpTextView=itemLayoutView.findViewById(R.id.thumUps)
            thumsDnTextView=itemLayoutView.findViewById(R.id.thumDn)

        }

    }

}

