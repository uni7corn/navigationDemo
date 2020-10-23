package com.square.navigation.bean

import androidx.fragment.app.Fragment

class Pager private constructor(val index: Int, val fragment: Fragment) {

    companion object {
        @JvmStatic
        fun build(index: Int, fragment: Fragment): Pager {
            return Pager(index = index, fragment = fragment)
        }
    }
}