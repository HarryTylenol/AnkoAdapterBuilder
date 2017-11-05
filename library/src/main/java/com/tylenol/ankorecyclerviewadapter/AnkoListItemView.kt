package com.tylenol.ankorecyclerviewadapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext

/**
 * Created by baghyeongi on 2017. 11. 5..
 */
abstract class AnkoListItemView<ViewHolder : RecyclerView.ViewHolder>(var context: Context) : AnkoComponent<Context> {

    fun getItemViewHolder() = createView(AnkoContext.Companion.create(context)).tag as ViewHolder

}