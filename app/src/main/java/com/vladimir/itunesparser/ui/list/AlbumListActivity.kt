package com.vladimir.itunesparser.ui.list

import com.vladimir.itunesparser.ui.SingleFragmentActivity

class AlbumListActivity : SingleFragmentActivity() {

    override fun createFragment() = AlbumListFragment.newInstance()

}
