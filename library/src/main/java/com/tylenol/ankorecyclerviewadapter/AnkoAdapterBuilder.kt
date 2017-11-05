package com.tylenol.ankorecyclerviewadapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup

/**
 * Created by baghyeongi on 2017. 11. 5..
 */
open class AnkoAdapterBuilder<Model : Any, ViewHolder : RecyclerView.ViewHolder> : RecyclerView.Adapter<ViewHolder>() {

    fun setData(list: ArrayList<Model>) = this.apply {
        this.list = list
    }

    fun setView(view: AnkoListItemView<ViewHolder>) = this.apply {
        this.ankoView = view
    }

    fun setOnItemClickListener(itemClickListener: ViewHolder.(Model) -> Unit) = this.apply {
        this.itemClickListener = itemClickListener
    }

    fun bindData(bind: (ViewHolder.(Model) -> Unit)) = this.apply {
        this.bind = bind
    }

    fun attatchToRecyclerView(recyclerView: RecyclerView) {
        recyclerView.adapter = this
    }

    private lateinit var ankoView: AnkoListItemView<ViewHolder>

    private var itemClickListener: ViewHolder.(Model) -> Unit = { _ -> }

    private var bind: ViewHolder.(Model) -> Unit = { _ -> }

    private var list: ArrayList<Model> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ankoView.getItemViewHolder()

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.setOnClickListener { itemClickListener(holder, list[position]) }
        bind(holder, list[position])
    }

}