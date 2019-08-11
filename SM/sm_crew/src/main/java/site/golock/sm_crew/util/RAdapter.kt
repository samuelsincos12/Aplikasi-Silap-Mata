package site.golock.sm_crew.util

import android.annotation.SuppressLint
import android.support.v4.widget.CircularProgressDrawable
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import site.golock.sm_crew.R
import site.golock.sm_crew.model.MPeta
import site.golock.sm_crew.util.Utils.iAlt
import site.golock.sm_crew.util.Utils.slcd

class RAdapter(
    private val dPeta: ArrayList<MPeta>?,
    private var a: Double,
    private var b: Double,
    private val c: RAdapterCallback
): RecyclerView.Adapter<RAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(
            R.layout.content_info_list_report,
            parent,
            false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.vHolder(dPeta!![position], a, b)
        holder.vMap.setOnClickListener {
            c.onMapListener(dPeta, position)
        }
        holder.vDet.setOnClickListener {
            c.onDetailListener(dPeta, position)
        }
    }

    override fun getItemCount(): Int {
        return dPeta!!.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val vImL: ImageView = itemView.findViewById(R.id.vImL)
        private val tJdL: TextView  = itemView.findViewById(R.id.tJdL)
        private val tAtL: TextView  = itemView.findViewById(R.id.tAtL)
        private val tTaL: TextView  = itemView.findViewById(R.id.tTaL)
        val vMap: TextView  = itemView.findViewById(R.id.vMap)
        val vDet: TextView  = itemView.findViewById(R.id.vDet)

        @SuppressLint("SetTextI18n")
        fun vHolder(dPeta: MPeta, a: Double, b: Double) {
            val vCPD = CircularProgressDrawable(vImL.context).also {
                it.strokeWidth = 5f
                it.centerRadius = 20f
                it.start()
            }

            Glide.with(vImL.context)
                 .load(Constant.IMG_URL + dPeta.img)
                 .apply(RequestOptions()
                     .placeholder(vCPD)
                     .diskCacheStrategy(DiskCacheStrategy.RESOURCE))
                 .into(vImL)
            tJdL.text = dPeta.jns
            tAtL.text = iAlt(tAtL.context, dPeta.lat, dPeta.lon)
            tTaL.text = "${Math.round(slcd(a, b, dPeta.lat, dPeta.lon) * 100.0) / 100.0} km"
        }
    }

    interface RAdapterCallback {
        fun onMapListener(p0: ArrayList<MPeta>, i: Int)
        fun onDetailListener(p0: ArrayList<MPeta>, i: Int)
    }
}