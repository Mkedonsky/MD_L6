package ru.mkedonsky.mdlesson6

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val data = arrayListOf(
            Data(0, " Элемент списка"),
            Data(1, " Элемент списка")
        )
        val adapter = RecyclerActivityAdapter(data)
        recyclerView.adapter = adapter
        recyclerActivityFAB.setOnClickListener { adapter.appendItem() }
        ItemTouchHelper(ItemTouchHelperCallback(adapter))
            .attachToRecyclerView(recyclerView)


    }
}
data class Data(
    val id: Int = 0,
    val someText: String = "Text"
)














