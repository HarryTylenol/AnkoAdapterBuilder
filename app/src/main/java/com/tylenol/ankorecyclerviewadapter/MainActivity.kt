package com.tylenol.ankorecyclerviewadapter

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import org.jetbrains.anko.UI
import org.jetbrains.anko.frameLayout
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.toast

/**
 * Created by baghyeongi on 2017. 11. 5..
 */
class MainActivity : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(UI {
            frameLayout {
                recyclerView = recyclerView {
                    layoutManager = LinearLayoutManager(this@MainActivity)
                }
            }
        }.view)

        val data = arrayListOf(User("Harry"), User("Tom"), User("Jane"))

        AnkoAdapterBuilder<User, UserListItemView.UserListItemViewHolder>()
                .setData(data)
                .setView(UserListItemView(this))
                .setOnItemClickListener { user -> toast("User${adapterPosition} : ${user.name} Selected") }
                .bindData { user -> nameTextView.text = user.name }
                .attatchToRecyclerView(recyclerView)

    }
}