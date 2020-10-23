package com.square.navigation

import android.os.Bundle
import android.util.Log
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.lifecycle.Lifecycle
import com.square.navigation.bean.Pager
import com.square.navigation.manager.IPaperBackRecord
import com.square.navigation.manager.PaperBackRecord

abstract class BasePaperActivity : AppCompatActivity() {

    private val paperBackRecord: IPaperBackRecord = PaperBackRecord()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        Log.e(TAG, "onBackPressed: ------->")
    }

    fun addOnBackPressCallback(fragment: Fragment, onBackPressedCallback: OnBackPressedCallback) {
        supportFragmentManager.commit {
            val fragmentTag = fragment::class.java.simpleName
            val findFragmentByTag = findFragment(fragmentTag) ?: fragment
            if (findFragmentByTag.isAdded) {
                show(findFragmentByTag)
                this.setMaxLifecycle(findFragmentByTag, Lifecycle.State.RESUMED)
            } else {
                this.setMaxLifecycle(fragment, Lifecycle.State.RESUMED)
                add(R.id.fragment_container, fragment, fragment::class.java.simpleName)
                val build = Pager.build(0, fragment)
                paperBackRecord.push(build)
            }
        }
        onBackPressedDispatcher.addCallback(fragment, onBackPressedCallback)
    }

    fun fragmentOnBackPressed(fragment: Fragment) {
        val fragmentTag = fragment::class.java.simpleName
        val findFragmentByTag = findFragment(fragmentTag) ?: fragment
        if (findFragmentByTag.isAdded) {
            supportFragmentManager.commit {
                remove(findFragmentByTag)
                this.setMaxLifecycle(findFragmentByTag, Lifecycle.State.DESTROYED)
            }
        }
        paperBackRecord.pop()
    }

    private fun findFragment(fragmentTag: String): Fragment? {
        return supportFragmentManager.findFragmentByTag(fragmentTag)
    }

    companion object {
        private val TAG = BasePaperActivity::class.java.simpleName
    }
}