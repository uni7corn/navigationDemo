package com.square.navigation.container

import android.content.Context
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import com.square.navigation.BasePaperActivity

abstract class BasePaperFragment : Fragment() {

    private val mOnBackPressedCallback: OnBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            findNavigationActivity().fragmentOnBackPressed(this@BasePaperFragment)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        findNavigationActivity().addOnBackPressCallback(this, mOnBackPressedCallback)
    }

    private fun findNavigationActivity(): BasePaperActivity {
        return (requireActivity() as BasePaperActivity)
    }
}