package ir.adp.framework.base.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView


abstract class BaseAdapter<T>(var model: ArrayList<T>) : RecyclerView.Adapter<BaseAdapter.ViewHolder<T>>() {

    var layout: Int = 0
    lateinit var listener: IAdapter<T>
    lateinit var view: View
    lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder<T> {
        val v = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        context = parent.context
        view = v
        return ViewHolder(v, listener)
    }

    override fun onBindViewHolder(holder: ViewHolder<T>, position: Int) {
        holder.bindItems(model[position])
    }

    override fun getItemCount(): Int {
        return model.size
    }

    class ViewHolder<T>(itemView: View, val listener: IAdapter<T>) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(model: T) {
            listener.onBind(model)
        }
    }

    interface IAdapter<T> {
        fun onBind(model: T)
    }

}
