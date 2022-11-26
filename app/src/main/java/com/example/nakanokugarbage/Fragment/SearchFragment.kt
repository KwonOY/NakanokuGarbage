package com.example.nakanokugarbage.Fragment

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.nakanokugarbage.Activity.MainActivity
import com.example.nakanokugarbage.Dialog.CustomSelectDialog
import com.example.nakanokugarbage.Helper.SharedPreferenceHelper
import com.example.nakanokugarbage.Interface.AfterActionListener
import com.example.nakanokugarbage.Interface.ChildSelectedListener
import com.example.nakanokugarbage.Manager.ShowNextDisplayManager
import com.example.nakanokugarbage.Model.BlockModel
import com.example.nakanokugarbage.R
import com.example.nakanokugarbage.View.ActionHideKeyboardTextView
import com.example.nakanokugarbage.ViewModel.BlockNameViewModel
import com.example.nakanokugarbage.databinding.SearchLayoutBinding

class SearchFragment : Fragment() {

    private lateinit var mBinding: SearchLayoutBinding
    private lateinit var mSearchEditTextView: ActionHideKeyboardTextView
    private lateinit var mNowChildFragment: BaseSearchChildFragment
    private lateinit var mViewModel: BlockNameViewModel

    private val mAfterActionListener = object: AfterActionListener {
        override fun onAfterActionListener(view: View) {
            mNowChildFragment.setSearchResultText(getText(view as TextView))
        }
    }
    private val mChildSeletedListener = object: ChildSelectedListener {
        override fun onReceiveSelected(selectedText: String) {
            var searchText = ""
            mViewModel.getBlockLiveData()?.let {
                for(block in it.value!!){
                    if(block.name in selectedText) {
                        searchText = block.name + "丁目"
                    }
                }
            }
            Handler(Looper.getMainLooper()).post {
                mSearchEditTextView.setText(searchText)
            }
        }
    }
    private val mSelectDialog = DialogInterface.OnClickListener { dialog, which ->
        when(which) {
            DialogInterface.BUTTON_POSITIVE -> {
                selectedPositive()
                moveNextActivity()
            }
            DialogInterface.BUTTON_NEGATIVE ->
                Log.d("myTest","negative")
        }
    }

    private val mObserver =
        Observer<List<BlockModel>> { blockModels ->
            if(mSearchEditTextView.adapter == null && blockModels != null) {
                val blockArray: ArrayList<String> = ArrayList()
                for (block in blockModels) {
                    blockArray.add(block.name + "丁目")
                }
                val adapter = ArrayAdapter(
                    requireContext(),
                    android.R.layout.simple_dropdown_item_1line,
                    blockArray
                )
                mSearchEditTextView.setAdapter(adapter)
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = SearchLayoutBinding.inflate(inflater, container, false)
        mSearchEditTextView = mBinding.SearchTextView
        mSearchEditTextView.setAfterActionListener(mAfterActionListener)

        mBinding.selectButton.setOnClickListener {
            val message = String.format(getString(R.string.block_select_message), getText(mSearchEditTextView))
            val dialog = CustomSelectDialog(requireContext())
            dialog.createDialog(R.string.block_select, message, R.mipmap.ic_launcher)
            dialog.setButtonListener(mSelectDialog)
            dialog.show()
        }

        mViewModel = ViewModelProvider(this)[BlockNameViewModel::class.java]
        mViewModel.getBlockLiveData()?.observe(viewLifecycleOwner, mObserver)

        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mNowChildFragment = ShowNextDisplayManager(requireContext()).getChildFragment(this) as BaseSearchChildFragment
        if( mNowChildFragment is GoogleMapFragment) {
            (mNowChildFragment as GoogleMapFragment).setListener(mChildSeletedListener)
        }
        val transaction = childFragmentManager.beginTransaction()
        transaction.replace(mBinding.SearchContentsLayout.id, mNowChildFragment)
        transaction.commit()
    }

    private fun selectedPositive(){
        val blockName = getText(mSearchEditTextView)
        var blockNo = -1
        mViewModel.getBlockLiveData()?.let {
            for (block in it.value!!) {
                if (block.name in blockName) {
                    blockNo = block.index
                }
            }
        }
        if(blockNo > -1) {
            SharedPreferenceHelper(requireContext()).setUserLocation(blockNo, blockName)
            activity?.finish()
        } else {
            Toast.makeText(requireContext(), "Wrong Location", Toast.LENGTH_SHORT).show()
        }
    }

    private fun moveNextActivity() {
        val intetn = Intent(requireContext(), MainActivity::class.java)
        startActivity(intetn)
        activity?.finish()
    }

    private fun getText(textView: TextView): String {
        return textView.text.toString()
    }
}