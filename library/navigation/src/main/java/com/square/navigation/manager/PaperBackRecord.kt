package com.square.navigation.manager

import com.square.navigation.bean.Pager

class PaperBackRecord : IPaperBackRecord {

    private val mBackRecordList: MutableList<Pager> by lazy {
        mutableListOf()
    }

    override fun pop() {
        mBackRecordList.lastOrNull()
    }

    override fun popPosition(position: Int) {
        mBackRecordList.removeAt(position)
    }

    override fun push(pager: Pager) {
        mBackRecordList.add(pager)
    }

    override fun pushPosition(position: Int, pager: Pager) {
        mBackRecordList.add(position, pager)
    }
}