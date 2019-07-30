package ir.adp.kriptonframework

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import ir.adp.widgets.TextView


class MyAdapter(private val list: List<MainResponse>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemViewType(position: Int): Int {
        return when (list[position].type) {
            MainResponse.TYPE_USER -> MainResponse.TYPE_USER
            MainResponse.TYPE_FRIEND -> MainResponse.TYPE_FRIEND
            else -> 0
        }
    }

    override fun onCreateViewHolder(@NonNull parent: ViewGroup, @NonNull viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            MainResponse.TYPE_USER -> UserViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_list,
                    parent,
                    false
                )
            )
            MainResponse.TYPE_FRIEND -> FriendViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_list,
                    parent,
                    false
                )
            )
            else -> null!!
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            MainResponse.TYPE_USER -> (holder as UserViewHolder).userViewHolder(list[position])
            MainResponse.TYPE_FRIEND -> (holder as FriendViewHolder).friendViewHolder(list[position])
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    internal inner class UserViewHolder(@NonNull itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val content: TextView = itemView.findViewById(R.id.tv_item)

        fun userViewHolder(model: MainResponse) {
            content.text = model.name
        }
    }


    internal inner class FriendViewHolder(@NonNull itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val content: TextView = itemView.findViewById(R.id.tv_item)

        fun friendViewHolder(model: MainResponse) {
            content.text = model.name
        }
    }
}