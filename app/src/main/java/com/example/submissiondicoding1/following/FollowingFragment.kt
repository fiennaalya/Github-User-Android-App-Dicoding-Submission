package com.example.submissiondicoding1.following

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.submissiondicoding1.databinding.FragmentFollowingBinding
import com.example.submissiondicoding1.datauser.User
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import org.json.JSONArray
import org.json.JSONObject

class FollowingFragment : Fragment() {

    private var listFollowing : ArrayList<User> = ArrayList()
    private lateinit var adapterFollowing: AdapterFollowing
    private var _followingbinding : FragmentFollowingBinding? = null
    private val Followingbinding get() = _followingbinding

    companion object{
        private val TAG = FollowingFragment::class.java.simpleName
        const val EXTRA_DATA = "extra_data"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _followingbinding = FragmentFollowingBinding.inflate(inflater, container,false)
        val view = Followingbinding!!.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _followingbinding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapterFollowing = AdapterFollowing(listFollowing)
        listFollowing.clear()
       val dataFollowing = requireActivity().intent.getParcelableExtra(EXTRA_DATA) as User?
        getUserFollowing(dataFollowing?.uname.toString())

    }

    private fun getUserFollowing(id: String) {
        Followingbinding!!.progressBarFollowing.visibility = View.VISIBLE
        val client = AsyncHttpClient()
        client.addHeader("User-Agent", "request")
        client.addHeader("Authorization", "token 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")
        val url = "https://api.github.com/users/$id/following"

        client.get(url, object : AsyncHttpResponseHandler(){
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray
            ) {
                Followingbinding!!.progressBarFollowing.visibility = View.INVISIBLE
                val result = String(responseBody)
                Log.d(TAG, result)

                try {
                    val jsonArray = JSONArray(result)
                    for (i in 0 until jsonArray.length()){
                        val jsonObject = jsonArray.getJSONObject(i)
                        val username: String = jsonObject.getString("login")
                        getFollowingDetail(username)
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
                Followingbinding!!.progressBarFollowing.visibility = View.INVISIBLE
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

    private fun getFollowingDetail(id: String) {
        Followingbinding!!.progressBarFollowing.visibility = View.VISIBLE
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
                Followingbinding!!.progressBarFollowing.visibility = View.INVISIBLE
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
                    listFollowing.add(
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
                Followingbinding!!.progressBarFollowing.visibility = View.INVISIBLE
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
        Followingbinding!!.rvFollowing.layoutManager = LinearLayoutManager(activity)
        val listFollowingAdapter = AdapterFollowing(followingFilterList)
        Followingbinding!!.rvFollowing.adapter = adapterFollowing

        listFollowingAdapter.setOnItemClickCallback(object :
            AdapterFollowing.OnItemClickCallback {
            override fun onItemClicked(dataFollowing : User) {
              //  showSelectedFollowing(dataFollowing)
            }
        })
    }

    private fun showSelectedFollowing(dataFollowing: User) {
        User(
            dataFollowing.uname, dataFollowing.name, dataFollowing.foto, dataFollowing.kantor, dataFollowing.asal,
            dataFollowing.repository, dataFollowing.followers, dataFollowing.following
        )
        Toast.makeText(activity, "@${dataFollowing.uname}", Toast.LENGTH_SHORT).show()

    }


}