package com.example.nakanokugarbage.Fragment

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.nakanokugarbage.Model.Block
import com.example.nakanokugarbage.R
import com.example.nakanokugarbage.databinding.SearchLayoutBinding
import com.google.firebase.database.*

class SearchFragment : Fragment() {

    private lateinit var mBinding: SearchLayoutBinding
    private lateinit var mSearchEditTextView: AutoCompleteTextView
    private lateinit var mNowChildFragment: BaseSearchChildFragment

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = SearchLayoutBinding.inflate(inflater, container, false)
        mSearchEditTextView = mBinding.SearchTextView
        mSearchEditTextView.setOnItemClickListener { parent, view, position, id ->
            mNowChildFragment.setSearchResultText(getText(view as TextView))
        }
        mSearchEditTextView.setOnKeyListener { v, keyCode, event ->
            if (event.keyCode == KeyEvent.KEYCODE_ENTER) {
                mNowChildFragment.setSearchResultText(getText(v as TextView))
            }
            false
        }
        val blockDb =
            FirebaseDatabase.getInstance().reference.child(getString(R.string.firebase_db_block))
        blockDb.addValueEventListener(BlcokDatabaseValueEventListener())

        return mBinding.root
    }

    private fun getText(textView: TextView): String {
        return textView.text.toString()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mNowChildFragment = GoogleMapFragment()
        val transaction = childFragmentManager.beginTransaction()
        transaction.replace(mBinding.SearchContentsLayout.id, mNowChildFragment)
        transaction.commit()
    }

    inner class BlcokDatabaseValueEventListener : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            val type = object : GenericTypeIndicator<List<Block>>() {}
            val blocks = snapshot.getValue(type)
            blocks?.let {
                val blockArray: ArrayList<String> = ArrayList()
                for (block in blocks) {
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

        }

        override fun onCancelled(error: DatabaseError) {
            TODO("Not yet implemented")
        }
    }
}