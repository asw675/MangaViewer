package com.example.mangaviewer.fragment

import android.app.DatePickerDialog
import android.os.Bundle
import android.os.HandlerThread
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mangaviewer.R
import com.example.mangaviewer.adapter.SearchTypeAdapter
import com.example.mangaviewer.viewmodel.SearchViewModel
import java.util.*
import java.util.concurrent.ConcurrentHashMap
import kotlin.collections.HashMap
import kotlin.collections.HashSet

class SearchFragment : Fragment() {

    var searchViewModel: SearchViewModel? = null
    var etSearch: EditText? = null
    var recyType : RecyclerView? = null
    var tvStartTime: TextView? = null
    var tvEndTime: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        searchViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(activity!!.application)
            .create(SearchViewModel::class.java)
        searchViewModel!!.initData()
        searchViewModel!!.getSearchOption({},{})
        bindViewAndData()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_search, container, false)
        return initView(view)
    }

    private fun initView(view:View):View{
        recyType = view.findViewById(R.id.recy_type)
        etSearch = view.findViewById(R.id.et_search)
        tvStartTime = view.findViewById(R.id.tv_start_time)
        tvEndTime = view.findViewById(R.id.tv_end_time)

        tvStartTime?.setOnClickListener {
            val ca = Calendar.getInstance()
            var mYear = ca[Calendar.YEAR]
            var mMonth = ca[Calendar.MONTH]
            var mDay = ca[Calendar.DAY_OF_MONTH]

            val datePickerDialog = DatePickerDialog(
                context!!,
                DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                    mYear = year
                    mMonth = month
                    mDay = dayOfMonth
                    val mDate = "${year}/${month + 1}/${dayOfMonth}"
                    // 将选择的日期赋值给TextView
                    tvStartTime?.text = mDate
                    searchViewModel?.setStartTime(year,month,dayOfMonth)
                },
                mYear, mMonth, mDay
            )
            datePickerDialog.show()
        }

        tvEndTime?.setOnClickListener{
            val ca = Calendar.getInstance()
            var mYear = ca[Calendar.YEAR]
            var mMonth = ca[Calendar.MONTH]
            var mDay = ca[Calendar.DAY_OF_MONTH]

            val datePickerDialog = DatePickerDialog(
                context!!,
                DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                    mYear = year
                    mMonth = month
                    mDay = dayOfMonth
                    val mDate = "${year}/${month + 1}/${dayOfMonth}"
                    // 将选择的日期赋值给TextView
                    tvEndTime?.text = mDate
                    searchViewModel?.setEndTime(year,month,dayOfMonth)
                },
                mYear, mMonth, mDay
            )
            datePickerDialog.show()
        }

//        recyType?.adapter = SearchTypeAdapter(context,searchViewModel!!.optionList.value)
        val recyTypeLayoutManager = LinearLayoutManager(context)
        recyTypeLayoutManager.orientation = RecyclerView.VERTICAL
        recyType?.layoutManager = recyTypeLayoutManager


        etSearch?.requestFocus()

        return view
    }

    private fun bindViewAndData() {
//        searchViewModel?.optionList?.observe(this,
//            Observer<HashMap<String,HashMap<String, String>>> { ani -> //收到回调后更新UI界面
//            (recyType!!.adapter as SearchTypeAdapter).setItem(ani)
//        })
    }
}