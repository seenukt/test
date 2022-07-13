package com.example.edittextforcurrency

import android.os.Bundle
import android.os.PersistableBundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.AbsListView
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.edittextforcurrency.test.PagingAdapters
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.component.KoinComponent
import java.text.DecimalFormat
import kotlin.math.roundToInt


class MainActivity : AppCompatActivity(), KoinComponent {

    lateinit var adapter: Recycler

    val vm: MainViewModel by viewModel()

    var countriesList: ArrayList<PayoutCountriesResponse> = arrayListOf()

    var paginatedList: MutableList<PayoutCountriesResponse> = mutableListOf()

    var isScrolling = false

    var startItem = 10
    var endItem = 19


    val conversionRate = 50
    var current = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val send = findViewById<EditText>(R.id.send)
        val receive = findViewById<EditText>(R.id.receive)

//        setAdapter()
        val prefs = this.getSharedPreferences("name", MODE_PRIVATE)


        val editor = prefs.edit()
        editor.apply()




        send.filters = arrayOf(DecimalDigitsInputFilter(2))

        val watcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
//h
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                println("Values-->" + s.toString());
            }

            override fun afterTextChanged(s: Editable?) {
                send.removeTextChangedListener(this)
                if (s?.toString()?.last() == '.' && s.toString().isNotEmpty()) {
                    send.append(".")
                    receive.append(".")
                    send.addTextChangedListener(this)
                    return
                }
                if (s?.toString() == current) {
                    var longval: String = s.toString()
                    if (longval.contains(",")) {
                        longval = longval.replace(",", "")
                    }

//                    s?.delete(s.length -1, s.length)
                    if (longval.last() != '.') {

                        val formatter = DecimalFormat("#,###,###.##")
                        val formattedString = formatter.format(longval.toDouble())
                        send.setText(formattedString.toString())
                        current = formattedString

                        val formattedStringr = formatter.format(longval.toDouble())
                        send.setSelection(send.text.toString().length)

                    } else {
                        send.append(".")
                        receive.append(".")
                    }
                }
                send.addTextChangedListener(this)

            }
        }
        val receiveWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                receive.text.apply {
                    val removedText = this.toString().replace(",", "").toDouble().roundToInt()
                    val formtd = DecimalFormat("#,###,###.##")
                    val txt = formtd.format(removedText).toString()
                    send.setText(txt)
                    receive.setSelection(receive.text.toString().length)
                }
            }

        }

//        receive.setOnFocusChangeListener { _, hasFocus ->
//            if (hasFocus) {
//                send.removeTextChangedListener(watcher)
//                receive.filters = arrayOf(DecimalDigitsInputFilter(0))
//
//                receive.text.apply {
//                    val removedText = this.toString().replace(",", "").toDouble().roundToInt()
//                    val formtd = DecimalFormat("#############################")
//                    val txt = formtd.format(removedText).toString()
//                    receive.removeTextChangedListener(receiveWatcher)
//                    receive.setText(txt)
//                    receive.addTextChangedListener(receiveWatcher)
//                    receive.filters = arrayOf(DecimalDigitsInputFilter(2))
//                }
//            } else {
//                receive.filters = arrayOf(DecimalDigitsInputFilter(2))
//            }
//        }

//        send.setOnFocusChangeListener { _, hasFocus ->
//            if (hasFocus) {
//                receive.removeTextChangedListener(receiveWatcher)
//                send.addTextChangedListener(watcher)
//            }
//        }

        send.addTextChangedListener(watcher)
//        receive.addTextChangedListener(receiveWatcher)


    }

//    private fun setData() {
//        for ( i in 1..100){
//            countriesList.add(PayoutCountriesResponse("country $i"))
//        }
//    }

//    private fun setAdapter() {
//       val rv = findViewById<RecyclerView>(R.id.rv_recycler)
//        val layout = LinearLayoutManager(this)
//        adapter = Recycler()
//        rv.adapter = adapter
//        rv.layoutManager = layout
//        rv.setHasFixedSize(true)
//        rv.addOnScrollListener(object : RecyclerView.OnScrollListener() {
//
//
//            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
//                super.onScrollStateChanged(recyclerView, newState)
//
//                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
//                    isScrolling = true
//                }
//
//            }
//
//            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//                super.onScrolled(recyclerView, dx, dy)
//                val visibleItemCount = recyclerView.childCount
//                val totalItemCount = layout.itemCount
//                val firstVisibleItem = layout.findFirstVisibleItemPosition()
//                if ( firstVisibleItem + visibleItemCount >= totalItemCount ){
//                    isScrolling = false
//                    val adapterCountriesList = arrayListOf<PayoutCountriesResponse>()
//                    for (item in startItem..endItem ){
//                        if (item>=countriesList.size-1){
//                            break
//                        }
//                        countriesList[item].let { adapterCountriesList.add(it) }
//
//                    }
//                    startItem+=10
//                    endItem+=10
//                    paginatedList.addAll(adapterCountriesList)
//                    adapter.submitList(paginatedList)
//                    adapter.notifyDataSetChanged()
//
//                }
//            }
//        })
//
//        setData()
//        paginatedList.addAll(countriesList.subList(0,10))
//        adapter.setList(paginatedList.toCollection(arrayListOf()))
//    }

//    override fun onResume() {
//        super.onResume()
//        Thread.sleep(5000)
//        vm.pagingData(1,10)
//        vm.data.observe(this@MainActivity){
//            it?.let {
//                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
//            }
//        }
//    }

    //  ---------------------------------------- paging
//
//    private lateinit var adapters: PagingAdapters
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.paging_layout)
//        setupView()
//        setupList()
//    }
//
//    private fun setupView() {
//        val rv = findViewById<RecyclerView>(R.id.rv_paging)
//        adapters = PagingAdapters()
//        rv.layoutManager = LinearLayoutManager(this)
//        rv.adapter = adapters
//    }
//
//    private fun setupList() {
//        Thread.sleep(5000)
//        vm.listData.observe(this@MainActivity) {
//            Log.d("seenu", "setupList:$it ")
//            adapters.submitData(lifecycle, it)
//        }
//    }
}