package com.example.nakanokugarbage.Fragment

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.nakanokugarbage.ViewModel.BlockNameViewModel
import com.example.nakanokugarbage.databinding.SearchLayoutBinding

class SearchFragment : Fragment() {

    private lateinit var mBinding: SearchLayoutBinding
    private lateinit var mSearchEditTextView: AutoCompleteTextView
    private lateinit var mNowChildFragment: BaseSearchChildFragment
    private lateinit var mViewModel: BlockNameViewModel

    interface ChildResultListener{
        fun onReceiveResult(resultText: String)
    }

    private val mListener = object: ChildResultListener {
        override fun onReceiveResult(resultText: String) {
            var seachText = ""
            mViewModel.getBlockLiveData()?.let {
                for(block in it.value!!){
                    if(block.name in resultText) {
                        seachText = block.name + "丁目"
                    }
                }
            }
            Handler(Looper.getMainLooper()).post {
                mSearchEditTextView.setText(seachText)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = SearchLayoutBinding.inflate(inflater, container, false)
        mSearchEditTextView = mBinding.SearchTextView

        mSearchEditTextView.setOnItemClickListener { parent, view, position, id ->
            mNowChildFragment.setSearchResultText(getText(view as TextView))
            hideKeyboard(mBinding.root)
        }
        mSearchEditTextView.setOnKeyListener { v, keyCode, event ->
            if (event.keyCode == KeyEvent.KEYCODE_ENTER) {
                mNowChildFragment.setSearchResultText(getText(v as TextView))
                hideKeyboard(mBinding.root)
            }
            false
        }
        mViewModel = ViewModelProvider(this)[BlockNameViewModel::class.java]
        mViewModel.getBlockLiveData()?.observe(viewLifecycleOwner, Observer {
            if(mSearchEditTextView.adapter == null) {
                val blockArray: ArrayList<String> = ArrayList()
                for (block in it) {
                    // TODO:　丁目削除
                    blockArray.add(block.name + "丁目")
                }
                val adapter = ArrayAdapter(
                    requireContext(),
                    android.R.layout.simple_dropdown_item_1line,
                    blockArray
                )
                mSearchEditTextView.setAdapter(adapter)
            }
        })

        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mNowChildFragment = GoogleMapFragment(mListener)
        val transaction = childFragmentManager.beginTransaction()
        transaction.replace(mBinding.SearchContentsLayout.id, mNowChildFragment)
        transaction.commit()
    }

    private fun getText(textView: TextView): String {
        return textView.text.toString()
    }

    private fun hideKeyboard(view: View) {
        val inputMethodManager = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0);
        view.clearFocus()
    }
}