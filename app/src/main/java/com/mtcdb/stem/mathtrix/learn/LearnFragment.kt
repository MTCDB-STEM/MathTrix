package com.mtcdb.stem.mathtrix.learn

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.mtcdb.stem.mathtrix.MainActivity
import com.mtcdb.stem.mathtrix.R

public class LearnFragment : Fragment(), LearningListener {

    companion object {
        public val TAG : String = LearnFragment::class.java.getSimpleName()

        public fun newInstance() : LearnFragment = LearnFragment()
    }

    private var mainActivity : MainActivity? = null
    private var recyclerView : RecyclerView? = null

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivity = requireActivity() as MainActivity
    }
    override fun onCreateView (inflater : LayoutInflater, container : ViewGroup?, savedInstanceState : Bundle?) : View? {
        return inflater.inflate(R.layout.fragment_learning_menu, container, false)
    }

    override fun onViewCreated(view : View, savedInstanceState : Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.recycler_view)
        recyclerView?.setAdapter( LearningAdapter(this@LearnFragment,
            listOf<LearnModel>(
                LearnModel(0, title = "Simple Interest", html = "simple_interest.html"),
                LearnModel(0, title = "Compound Interest", html = "compound_interest.html"),
                LearnModel(3, title = "Profit Margin", html = "profit_margin.html"),
                LearnModel(2, title = "Net Profit Value", html =  "net_profit_value.html"),
                LearnModel(4, title = "Return of Interest", html = "return_of_interest.html"),
            )
        ) )
    }

    override fun onClickModel(model : LearnModel, position: Int) {
        mainActivity?.supportFragmentManager?.beginTransaction()
            ?.add(R.id.fragment_container, TipsFragment.newInstance(model), TipsFragment::class.java.getSimpleName())
            ?.addToBackStack(TipsFragment.TAG)
            ?.commit()
        mainActivity?.toolbar?.title = model.title ?: TipsFragment.TAG
    }

    override fun onDestroy() {
        super.onDestroy()
        mainActivity?.toolbar?.title = getString(R.string.app_name)
    }
}