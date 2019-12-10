package hu.unideb.pedometer.data

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import hu.unideb.pedometer.R

class JsonAdapter(private val jsons: MutableList<JsonObject>) : RecyclerView.Adapter<JsonAdapter.ViewHolder>() {

    class ViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val textView = LayoutInflater.from(parent.context)
            .inflate(R.layout.json_fragment, parent, false) as TextView

        return ViewHolder(textView)    }

    override fun getItemCount() = jsons.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = jsons[position].toString()
    }


}