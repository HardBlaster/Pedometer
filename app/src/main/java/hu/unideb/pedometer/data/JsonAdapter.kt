package hu.unideb.pedometer.data

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import hu.unideb.pedometer.R

class JsonAdapter(private val jsons: MutableList<JsonObject>) :
    RecyclerView.Adapter<JsonAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val item_userId = itemView.findViewById(R.id.item_userId) as TextView
        val item_id = itemView.findViewById(R.id.item_id) as TextView
        val item_title = itemView.findViewById(R.id.item_title) as TextView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false))


    override fun getItemCount() = jsons.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val json: JsonObject = jsons[position]

        holder.item_userId.text = json.userId.toString()
        holder.item_id.text = json.id.toString()
        holder.item_title.text = json.title
    }


}