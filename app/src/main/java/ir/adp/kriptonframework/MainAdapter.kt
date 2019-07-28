package ir.adp.kriptonframework

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import ir.adp.widgets.TextView

class MainAdapter(private val list: List<MainResponse>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(@NonNull parent: ViewGroup, @NonNull viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).userViewHolder(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    internal inner class ViewHolder(@NonNull itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val content: TextView = itemView.findViewById(R.id.tv_item)

        fun userViewHolder(model: MainResponse) {
            content.text = model.name
            itemView.setOnClickListener { }
        }
    }
}