package nit2x.paba.latihanrecyclerview


import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso


class adapterRecView (private val listWayang: ArrayList<wayang>) : RecyclerView
.Adapter<adapterRecView.ListViewHolder> () {
    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var _namaWayang = itemView.findViewById<TextView>(R.id.tvNamaWayang)
        var _karakterWayanag = itemView.findViewById<TextView>(R.id.tvKarakterWayang)
        var _deskripsiWayang = itemView.findViewById<TextView>(R.id.tvDeskripsiWayang)
        var _gambarWayang = itemView.findViewById<ImageView>(R.id.ivGambarWayang)
    }


    private lateinit var onItemClickCallback: OnItemClickCallback


    interface OnItemClickCallback {
        fun onItemClicked(data:wayang)
    }


    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recycler, parent, false)
        return ListViewHolder(view)
    }


    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        var wayang = listWayang[position]
        holder._namaWayang.setText(wayang.nama)
        holder._deskripsiWayang.setText(wayang.deskripsi)
        holder._karakterWayanag.setText(wayang.karakter)
        Log.d("TEST", wayang.foto)
        Picasso.get()
            .load(wayang.foto)
            .into(holder._gambarWayang)


        holder._gambarWayang.setOnClickListener {
//            Toast.makeText(holder.itemView.context, wayang.nama, Toast.LENGTH_LONG).show()
            onItemClickCallback.onItemClicked(listWayang[position])
        }
    }


    override fun getItemCount(): Int {
        return listWayang.size
    }
}

