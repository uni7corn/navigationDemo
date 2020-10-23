package com.square.navigation.manager

import androidx.annotation.UiThread
import com.square.navigation.bean.Pager

@UiThread
interface IPaperBackRecord {
    fun pop()
    fun popPosition(position: Int)
    fun push(pager: Pager)
    fun pushPosition(position: Int, pager: Pager)
}