package com.tylenol.ankorecyclerviewadapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import org.jetbrains.anko.*

/**
 * Created by baghyeongi on 2017. 11. 5..
 */
class UserListItemView(context: Context) : ListItemView<UserListItemView.UserListItemViewHolder>(context) {

    class UserListItemViewHolder(view: View, var nameTextView: TextView) : RecyclerView.ViewHolder(view)

    lateinit var nameTextView: TextView

    override fun createView(ui: AnkoContext<Context>) = with(ui) {
        frameLayout {
            lparams(matchParent)
            padding = dip(24)
            nameTextView = textView()
            tag = UserListItemViewHolder(this, nameTextView)
        }
    }

}