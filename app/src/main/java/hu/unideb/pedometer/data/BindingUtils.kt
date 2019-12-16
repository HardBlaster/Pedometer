package hu.unideb.pedometer.data

import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("jsonId")
fun TextView.setJsonId(item : JsonObject){
    text=item.id.toString()
}

@BindingAdapter("jsonUserId")
fun TextView.setUserId(item : JsonObject){
    text=item.userId.toString()
}


@BindingAdapter("jsonTitle")
fun TextView.jsonTitle(item : JsonObject){
    text=item.title
}