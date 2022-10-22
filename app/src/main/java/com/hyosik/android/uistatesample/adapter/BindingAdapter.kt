package com.hyosik.android.uistatesample.adapter

import android.view.View
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hyosik.android.uistatesample.Person


@BindingAdapter("item_list")
fun RecyclerView.setItemList(personList : List<Person>?) {
    val adapter = adapter as? PersonAdapter

    personList?.let {
        adapter?.submitList(it)
    }
}


@BindingAdapter("android:visibility")
fun View.setVisible(isVisible: Boolean) {
    this.isVisible = isVisible
}
