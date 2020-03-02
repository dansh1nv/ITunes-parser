package com.vladimir.itunesparser.ui.album

import com.vladimir.itunesparser.ui.SingleFragmentActivity

class AlbumDetailActivity : SingleFragmentActivity() {

    override fun createFragment() = AlbumDetailFragment.newInstance()

}
