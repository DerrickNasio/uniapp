package com.example.uniapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.uniapp.Programme.Companion.fromJson
import org.json.JSONObject
import java.io.InputStream


/**
 * A simple [Fragment] subclass.
 * Use the [ProgrammesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProgrammesFragment : Fragment() {
    private var columnCount = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_item_list, container, false)

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                val inputStream: InputStream = requireActivity().assets.open("bachelors.json")
                val jsonString = inputStream.bufferedReader()
                    .use { it.readText() }
                val jsonObject = JSONObject(jsonString)
                val jsonArray = jsonObject.getJSONArray("programmes")
                adapter = MyCourseRecyclerViewAdapter(fromJson(jsonArray))
            }
        }
        return view
    }

    companion object {

        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(columnCount: Int) =
            ProgrammesFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}