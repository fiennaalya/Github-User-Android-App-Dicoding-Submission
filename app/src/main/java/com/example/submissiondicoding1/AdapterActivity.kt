package com.example.submissiondicoding1

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.submissiondicoding1.datauser.User
import com.example.submissiondicoding1.detailactivity.DetailUserActivity
import java.util.*
import kotlin.collections.ArrayList


var userFilterList = ArrayList<User>()


class AdapterActivity(val listUser : ArrayList<User>) : RecyclerView.Adapter<AdapterActivity.ListViewHolder>(), Filterable  {


    init {
        userFilterList = listUser
    }

    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }


    interface OnItemClickCallback {
        fun onItemClicked(dataUsers: User)
    }

    inner class ListViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView)    {
        var tvUsername: TextView = itemView.findViewById(R.id.id_adapter_username)
        var tvName: TextView = itemView.findViewById(R.id.id_adapter_name)
        var imgPhoto: ImageView = itemView.findViewById(R.id.img_adapter)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListViewHolder {
        val view: View = LayoutInflater.from(viewGroup.context).inflate(R.layout.activity_adapter, viewGroup, false)
        return ListViewHolder(view)
    }


    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = userFilterList[position]
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

        val mContext = holder.itemView.context

        holder.itemView.setOnClickListener {
            val datauser = User(
                data.uname, data.name, data.foto,
                data.kantor, data.asal,
                data.repository, data.followers, data.following
            )
            val DetailItem = Intent(mContext, DetailUserActivity::class.java)
            DetailItem.putExtra(DetailUserActivity.EXTRA_DATA, datauser)
            mContext.startActivity(DetailItem)

        }

    }

    override fun getItemCount(): Int {
        return userFilterList.size
    }

    override fun getFilter(): Filter {
        return object : Filter(){
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                userFilterList = if (charSearch.isEmpty()){
                    listUser
                } else {
                    val finalList = ArrayList<User>()
                    for (row in userFilterList){
                        if ((row.uname.toString().toLowerCase(Locale.ROOT)
                                .contains(charSearch.toLowerCase(Locale.ROOT)))
                        ){
                            finalList.add(
                                User(
                                    row.uname, row.name, row.foto, row.kantor,
                                    row.asal, row.repository, row.followers, row.following
                                )
                            )
                        }
                    }
                    finalList
                }
                val filterFinal = FilterResults()
                filterFinal.values = userFilterList
                return filterFinal
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence, results: FilterResults) {
                userFilterList = results?.values as ArrayList<User>
                notifyDataSetChanged()
            }
        }
    }

}