package com.example.submissiondicoding1.followers

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.submissiondicoding1.R
import com.example.submissiondicoding1.datauser.User

var followersFilterList = ArrayList<User>()

class AdapterFollowers (val listFollowers : ArrayList<User>) : RecyclerView.Adapter<AdapterFollowers.ListViewHolder>(){
    init {
        followersFilterList = listFollowers
    }

    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(dataFollower: User)
    }

    inner class ListViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView){
        var tvUsername: TextView = itemView.findViewById(R.id.id_adapter_username)
        var tvName: TextView = itemView.findViewById(R.id.id_adapter_name)
        var imgPhoto: ImageView = itemView.findViewById(R.id.img_adapter)
    }


    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(viewGroup.context).inflate(R.layout.activity_adapter, viewGroup, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = followersFilterList[position]
        Glide.with(holder.itemView.context)
            .load(data.foto)
            .apply(RequestOptions().override(60,60))
            .into(holder.imgPhoto)

        holder.tvUsername.text =("@" + data.uname)

        holder.tvName.text = data.name
        if(data.name == "null"){
            holder.tvName.visibility = View.INVISIBLE
        }else{
            holder.tvName.visibility = View.VISIBLE
        }

        if(data.kantor == "null"){
            data.kantor = " - "
        }


        if(data.asal == "null"){
            data.asal = " - "
        }

        holder.itemView.setOnClickListener{
        }
    }

    override fun getItemCount(): Int {
        return followersFilterList.size
    }
}