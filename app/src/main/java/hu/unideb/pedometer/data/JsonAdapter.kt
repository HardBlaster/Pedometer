package hu.unideb.pedometer.data

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import hu.unideb.pedometer.R
import hu.unideb.pedometer.databinding.ItemLayoutBinding

private const val ITEM_VIEW_TYPE_HEADER = 0
private const val ITEM_VIEW_TYPE_ITEM = 1
class JsonAdapter (val clickListener:JsonListener):
    ListAdapter<DataItem, RecyclerView.ViewHolder>(JsonDiffCallBack()) {

    /*
    var jsons = listOf<JsonObject>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
     */
    fun addHeaderAndSubmitList(list: List<JsonObject>?) {
        val items = when (list) {
            null -> listOf(DataItem.Header)
            else -> listOf(DataItem.Header) + list.map { DataItem.JsonItem(it) }
        }
        submitList(items)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) :RecyclerView.ViewHolder{
        return when (viewType) {
            ITEM_VIEW_TYPE_HEADER -> TextViewHolder.from(parent)
            ITEM_VIEW_TYPE_ITEM -> ViewHolder.from(parent)
            else -> throw ClassCastException("Unknown viewType ${viewType}")
        }
    }
    // ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false))
    //override fun getItemCount() = jsons.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ViewHolder -> {
                val jsonItem = getItem(position) as DataItem.JsonItem
                holder.bind(jsonItem.jsonObject, clickListener)
            }
        }
    }

    class ViewHolder(val binding: ItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        val item_userId = itemView.findViewById(R.id.item_userId) as TextView
        val item_id = itemView.findViewById(R.id.item_id) as TextView
        val item_title = itemView.findViewById(R.id.item_title) as TextView

        fun bind(item: JsonObject,clickListener:JsonListener) {
            /*
            item_userId.text = "Name: " + item.userId.toString()
            item_id.text = "Id: " + item.id.toString()
            item_title.text = "Post Id: " + item.title

             */
            binding.clickListener=clickListener

            binding.jsonObject=item
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemLayoutBinding.inflate(layoutInflater, parent, false)
                val view = layoutInflater
                    .inflate(R.layout.item_layout, parent, false)

                return ViewHolder(binding)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is DataItem.Header -> ITEM_VIEW_TYPE_HEADER
            is DataItem.JsonItem -> ITEM_VIEW_TYPE_ITEM
        }
    }

    class TextViewHolder(view: View): RecyclerView.ViewHolder(view) {
        companion object {
            fun from(parent: ViewGroup): TextViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(R.layout.header, parent, false)
                return TextViewHolder(view)
            }
        }
    }


}

class JsonDiffCallBack: DiffUtil.ItemCallback<DataItem>(){
    override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
        return oldItem.id==newItem.id
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
        return oldItem == newItem
    }
}

class JsonListener(val clickListener: (jsonId: Long) -> Unit) {
    fun onClick(jsonObject: JsonObject) = clickListener(jsonObject.id)
}

sealed class DataItem {
    data class JsonItem(val jsonObject: JsonObject): DataItem() {
        override val id = jsonObject.id
    }

    object Header: DataItem() {
        override val id = Long.MIN_VALUE
    }

    abstract val id: Long
}