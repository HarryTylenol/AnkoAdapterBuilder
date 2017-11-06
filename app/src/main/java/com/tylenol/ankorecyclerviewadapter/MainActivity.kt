package com.tylenol.ankorecyclerviewadapter

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.sdk25.coroutines.onClick

/**
 * Created by baghyeongi on 2017. 11. 5..
 */
class MainActivity : AppCompatActivity() {

    val adapterBuilder = AdapterBuilder<User, UserListItemView.UserListItemViewHolder>(
            arrayListOf(User("Harry"), User("Tom"), User("Jane")))

    lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(UI {
            verticalLayout {
                button("Add") {
                    onClick { adapterBuilder.addData(User("Single New User")) }
                }
                button("Add List") {
                    onClick { adapterBuilder.addData(listOf(User("New User1"), User("New User2"), User("New User3"))) }
                }
                recyclerView = recyclerView().lparams(matchParent)
            }
        }.view)

        adapterBuilder.setView(UserListItemView(this))
                .setOnItemClickListener { user -> adapterBuilder.removeData(user) }
                .setOnDataChangedListener { data -> toast(data.toString()) }
                .bindData { user -> nameTextView.text = user.name }
                .attatchToRecyclerView(LinearLayoutManager(this@MainActivity), recyclerView)

    }
}