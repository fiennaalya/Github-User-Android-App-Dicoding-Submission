package com.example.submissiondicoding1.followers

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.submissiondicoding1.databinding.FragmentFollowesBinding
import com.example.submissiondicoding1.datauser.User
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import org.json.JSONArray
import org.json.JSONObject


class FollowesFragment : Fragment() {

    private var listFollowers : ArrayList<User> = ArrayList()
    private lateinit var adapterFollowers: AdapterFollowers
    private lateinit var FollowersView: RecyclerView
    private var _followersbinding : FragmentFollowesBinding? = null
    private val Followersbinding get() = _followersbinding

    companion object{
        private val TAG = FollowesFragment::class.java.simpleName
        const val EXTRA_DATA = "extra_data"
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _followersbinding= FragmentFollowesBinding.inflate(inflater, container,false)
        val view = Followersbinding!!.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _followersbinding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapterFollowers = AdapterFollowers(listFollowers)
        listFollowers.clear()
        val dataFollowers = requireActivity().intent.getParcelableExtra(EXTRA_DATA) as User?
        getUserFollowers(dataFollowers?.uname.toString())
    }

    private fun getUserFollowers(id: String) {
        Followersbinding!!.progressBarFollowers.visibility = View.VISIBLE
        val client = AsyncHttpClient()
        client.addHeader("User-Agent", "request")
        client.addHeader("Authorization", "token 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")
        val url = "https://api.github.com/users/$id/followers"

        client.get(url, object : AsyncHttpResponseHandler(){
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray
            ) {
                Followersbinding!!.progressBarFollowers.visibility = View.INVISIBLE
                val result = String(responseBody)
                Log.d(TAG, result)

                try {
                    val jsonArray = JSONArray(result)
                    for (i in 0 until jsonArray.length()){
                        val jsonObject = jsonArray.getJSONObject(i)
                        val username: String = jsonObject.getString("login")
                        getFollowersDetail(username)
                    }

                }catch (e : Exception){
                    Toast.makeText(activity, e.message, Toast.LENGTH_SHORT)
                        .show()
                    e.printStackTrace()
                }
            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?,
                error: Throwable
            ) {
                Followersbinding!!.progressBarFollowers.visibility = View.INVISIBLE
                val errorMessage = when (statusCode) {
                    401 -> "$statusCode : Bad Request"
                    403 -> "$statusCode : Forbidden"
                    404 -> "$statusCode : Not Found"
                    else -> "$statusCode : ${error.message}"
                }
                Toast.makeText(activity, errorMessage, Toast.LENGTH_LONG)
                    .show()
            }
        })

    }

    private fun getFollowersDetail(id: String) {
        Followersbinding!!.progressBarFollowers.visibility = View.VISIBLE
        val client = AsyncHttpClient()
        client.addHeader("User-Agent", "request")
        client.addHeader("Authorization", "token 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")
        val url = "https://api.github.com/users/$id"

        client.get(url, object : AsyncHttpResponseHandler(){
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray
            ) {
                Followersbinding!!.progressBarFollowers.visibility = View.INVISIBLE
                val result = String(responseBody)
                Log.d(TAG, result)
                try {
                    val jsonObject = JSONObject(result)
                    val username: String = jsonObject.getString("login").toString()
                    val name: String = jsonObject.getString("name").toString()
                    val avatar: String = jsonObject.getString("avatar_url").toString()
                    val company: String = jsonObject.getString("company").toString()
                    val location: String = jsonObject.getString("location").toString()
                    val repository: String? = jsonObject.getString("public_repos")
                    val followers: String? = jsonObject.getString("followers")
                    val following: String? = jsonObject.getString("following")
                    listFollowers.add(
                        User(
                            username,
                            name,
                            avatar,
                            company,
                            location,
                            repository,
                            followers,
                            following
                        )
                    )
                    showRecyclerList()
                }catch (e : Exception){
                    Toast.makeText(activity, e.message, Toast.LENGTH_SHORT)
                        .show()
                    e.printStackTrace()
                }
            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?,
                error: Throwable
            ) {
                Followersbinding!!.progressBarFollowers.visibility = View.INVISIBLE
                val errorMessage = when (statusCode) {
                    401 -> "$statusCode : Bad Request"
                    403 -> "$statusCode : Forbidden"
                    404 -> "$statusCode : Not Found"
                    else -> "$statusCode : ${error.message}"
                }
                Toast.makeText(activity, errorMessage, Toast.LENGTH_LONG)
                    .show()
            }
        })
    }

    private fun showRecyclerList() {
        Followersbinding!!.rvFollowers.layoutManager = LinearLayoutManager(activity)
        val listFollowersAdapter = AdapterFollowers(followersFilterList)
        Followersbinding!!.rvFollowers.adapter = adapterFollowers

        listFollowersAdapter.setOnItemClickCallback(object :
            AdapterFollowers.OnItemClickCallback {
            override fun onItemClicked(dataFollowers : User) {
               // showSelectedFollowers(dataFollowers)
            }
        })

    }

    private fun showSelectedFollowers(dataFollowers: User) {
        User(
            dataFollowers.uname, dataFollowers.name, dataFollowers.foto, dataFollowers.kantor, dataFollowers.asal,
            dataFollowers.repository, dataFollowers.followers, dataFollowers.following
        )
        Toast.makeText(activity, "@${dataFollowers.uname}", Toast.LENGTH_SHORT).show()
    }




}