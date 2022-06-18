package van.per.hoccachkiemthemtien.fragment

import amp.er.kiemtientainha.data.Data
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import van.per.hoccachkiemthemtien.R
import van.per.hoccachkiemthemtien.databinding.ItemBannerBinding

class BannerAdapter(
    private val listener: Listener
): RecyclerView.Adapter<BannerAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_banner, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        with(holder.binding){
            bannerImage.setBackgroundResource(Data.contents[position].resource)
            bannerImage.setOnClickListener {
                listener.onItemClick(position)
            }
        }
    }

    override fun getItemCount(): Int = Data.contents.size

    class Holder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val binding by lazy {
            ItemBannerBinding.bind(itemView)
        }
    }

    interface Listener {
        fun onItemClick(position: Int)
    }
}