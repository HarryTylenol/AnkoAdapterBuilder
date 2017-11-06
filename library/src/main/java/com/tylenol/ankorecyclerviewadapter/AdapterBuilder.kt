package com.tylenol.ankorecyclerviewadapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup

/**
 * Created by baghyeongi on 2017. 11. 5..
 */
open class AdapterBuilder<Model : Any, ViewHolder : RecyclerView.ViewHolder>(initialList: ArrayList<Model> = arrayListOf())
    : RecyclerView.Adapter<ViewHolder>() {

    init {
        setHasStableIds(true)
    }

    fun setData(newList: ArrayList<Model>) = this.apply {
        this.list = newList
        notifyDataSetChanged()
    }

    fun addData(newList: List<Model>) {
        this.list.addAll(newList)
        notifyDataSetChanged()
    }

    fun addData(model: Model) {
        this.list.add(model)
        notifyDataSetChanged()
    }

    fun removeData(model: Model) {
        this.list.remove(model)
        notifyDataSetChanged()
    }

    fun setView(view: ListItemView<ViewHolder>) = this.apply {
        this.view = view
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

    private lateinit var view: ListItemView<ViewHolder>

    private var itemClickListener: ViewHolder.(Model) -> Unit = { _ -> }

    private var bind: ViewHolder.(Model) -> Unit = { _ -> }

    private var list: ArrayList<Model> = initialList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = view.getItemViewHolder()

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.setOnClickListener { itemClickListener(holder, list[position]) }
        bind(holder, list[position])
    }

}