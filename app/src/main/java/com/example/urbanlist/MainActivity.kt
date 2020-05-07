package com.example.urbanlist


import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    lateinit var progerssProgressDialog: ProgressDialog
    var dataList:MutableList<DataModel1> = ArrayList()
    lateinit var recyclerView: RecyclerView
    var adapter1:DataAdpter?=null
    lateinit var etext:EditText
    private var API_KEY= "77dd5bc27dmsheae0d43519446ccp176a48jsnd1ff13df193b"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.reCycler_vview)
        recyclerView.layoutManager= LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)

        progerssProgressDialog=ProgressDialog(this)

        val thumb = resources.getStringArray(R.array.Thumbs)
        etext = findViewById(R.id.input)

        var spinner = findViewById(R.id.btn_thumbs) as Spinner
        if(spinner!=null){
            val adapter = ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,thumb)
            spinner.adapter = adapter
        }
        var spinnerSelectedCurrent:String?=null

        var spinnerSelectedPrev:String?=null
        var prevInputQuery:String?=null
        spinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {
        //        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                spinnerSelectedCurrent = thumb[p2]
            }

        }
        val btn = findViewById(R.id.btn_process) as Button
        btn.setOnClickListener{
            Toast.makeText(applicationContext,"Selected data is:"+spinnerSelectedCurrent+" input is:"+etext.text,Toast.LENGTH_LONG).show()
            if(prevInputQuery==null || prevInputQuery!=etext.text.toString()){
                prevInputQuery = etext.text.toString()
                spinnerSelectedPrev = spinnerSelectedCurrent
                getData(prevInputQuery!!,spinnerSelectedPrev!!)
            }else{
                if(spinnerSelectedPrev!=spinnerSelectedCurrent){
                    adapter1!!.sortData()
                    spinnerSelectedPrev = spinnerSelectedCurrent
                }
            }
        }

    }

    private fun getData(query:String, upordn:String) {
        progerssProgressDialog.setTitle("Loading")
        progerssProgressDialog.setCancelable(false)
        progerssProgressDialog.show()
        val call = ApiClient.getClient.getList(query)
        call.enqueue(object : Callback<DataModel> {

            override fun onResponse(call: Call<DataModel>?, response: Response<DataModel>?) {
                var subscriber: MutableList<DataModel1> = ArrayList()
                if (response!!.isSuccessful) {
                    if (response != null && response.body() != null) {
                        var dat: DataModel? = response.body()

                        println("Number of elements: ${dat!!.data.size}")
                        for(dat1 in dat!!.data){
                            subscriber.add(dat1)
                        }
                        recyclerView.apply {
                            setHasFixedSize(true)
                            layoutManager = LinearLayoutManager(this@MainActivity)
                            this.adapter =DataAdpter(subscriber,upordn)
                            adapter1 = this.adapter as DataAdpter
                        }
                    }
                }else{
                    println("Network Failure")
                }
                progerssProgressDialog.dismiss()
            }

            override fun onFailure(call: Call<DataModel>, t: Throwable) {
                progerssProgressDialog.dismiss()
                Toast.makeText(applicationContext,"Network Failure",Toast.LENGTH_LONG).show()
            }

        })
    }


}


