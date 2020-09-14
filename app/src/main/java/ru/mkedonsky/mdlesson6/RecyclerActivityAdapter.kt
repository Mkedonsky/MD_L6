package ru.mkedonsky.mdlesson6

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item.view.*

class RecyclerActivityAdapter(private var data: MutableList<Data>) :
    RecyclerView.Adapter<RecyclerActivityAdapter.MainViewHolder>(),ItemTouchHelperAdapter {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MainViewHolder {
        return MainViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item, parent, false) as View
        )
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun appendItem() {
        data.add(generateItem())
        notifyItemInserted(itemCount - 1)
    }

    private fun generateItem() = Data(itemCount, " Элемент списка")

    override fun onItemDismiss(position: Int) {
        data.removeAt(position)
        notifyItemRemoved(position)
    }

    inner class MainViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(dataItem: Data) {
            itemView.mainTextView.text = dataItem.id.toString() + " " + dataItem.someText
            itemView.moveItemDown.setOnClickListener { moveDown() }
            itemView.moveItemUp.setOnClickListener { moveUp() }
        }

        private fun moveUp() {
            val currentPosition = layoutPosition
            if (currentPosition > 0) {
                val currentData = data[currentPosition]
                data.removeAt(currentPosition)
                data.add(currentPosition - 1, currentData)
                notifyItemMoved(currentPosition, currentPosition - 1)
            }
        }

        private fun moveDown() {
            val currentPosition = layoutPosition
            if (currentPosition < data.size - 1) {
                val currentData = data[currentPosition]
                data.removeAt(currentPosition)
                data.add(currentPosition + 1, currentData)
                notifyItemMoved(currentPosition, currentPosition + 1)
            }
        }

    }
}
