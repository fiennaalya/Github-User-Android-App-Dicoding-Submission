package com.example.submissiondicoding1.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.submissiondicoding1.AdapterActivity
import com.example.submissiondicoding1.R
import com.example.submissiondicoding1.darkmode.DarkModeActivity
import com.example.submissiondicoding1.databinding.ActivityMainBinding
import com.example.submissiondicoding1.datauser.User
import com.example.submissiondicoding1.userFilterList
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    private var listData : ArrayList<User> = ArrayList()
    private lateinit var adapter : AdapterActivity
    private lateinit var Mainbinding: ActivityMainBinding
    private lateinit var DataView: RecyclerView

    companion object{
        private val TAG = MainActivity::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Mainbinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(Mainbinding.root)

        DataView = findViewById(R.id.rv_data)

        supportActionBar?.title = "Github User's Search"

        adapter = AdapterActivity(listData)

        recyclerViewConfig()
        searchUser()
        getDataAccount()


    }

    private fun searchUser(){ Mainbinding.search.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
        override fun onQueryTextSubmit(query: String?): Boolean {
            if(query!!.isEmpty()){
                return true
            } else {
                listData.clear()
                getUserSearch(query)
                Mainbinding.search.clearFocus()
            }
            return true
        }

        override fun onQueryTextChange(newText: String?): Boolean {
            return false
        }
    })

    }

    private fun getDataAccount() {
        Mainbinding.progressBar.visibility = View.VISIBLE
        val client = AsyncHttpClient()
        client.addHeader("User-Agent", "request")
        client.addHeader("Authorization", "token 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")
        val url = "https://api.github.com/users"
        client.get(url, object : AsyncHttpResponseHandler() {
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray
            ) {
                Mainbinding.progressBar.visibility = View.INVISIBLE
                val result = String(responseBody)
                Log.d(TAG, result)
                try {
                    val jsonArray = JSONArray(result)
                    for (i in 0 until jsonArray.length()) {
                        val jsonObject = jsonArray.getJSONObject(i)
                        val username: String = jsonObject.getString("login")
                        getDataDetail(username)
                    }
                } catch (e: Exception) {
                    Toast.makeText(this@MainActivity, e.message, Toast.LENGTH_SHORT)
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
                Mainbinding.progressBar.visibility = View.INVISIBLE
                val errorMessage = when (statusCode) {
                    401 -> "$statusCode : Bad Request"
                    403 -> "$statusCode : Forbidden"
                    404 -> "$statusCode : Not Found"
                    else -> "$statusCode : ${error.message}"
                }
                Toast.makeText(this@MainActivity, errorMessage, Toast.LENGTH_LONG)
                    .show()
            }
        })
    }

    private fun getDataDetail(id: String) {
        Mainbinding.progressBar.visibility = View.VISIBLE
        val client = AsyncHttpClient()
        client.addHeader("User-Agent", "request")
        client.addHeader("Authorization", "token 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")
        val url = "https://api.github.com/users/$id"
        client.get(url, object : AsyncHttpResponseHandler() {
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray
            ) {
                Mainbinding.progressBar.visibility = View.INVISIBLE
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
                    listData.add(
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
                } catch (e: Exception) {
                    Toast.makeText(this@MainActivity, e.message, Toast.LENGTH_SHORT)
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
                Mainbinding.progressBar.visibility = View.INVISIBLE
                val errorMessage = when (statusCode) {
                    401 -> "$statusCode : Bad Request"
                    403 -> "$statusCode : Forbidden"
                    404 -> "$statusCode : Not Found"
                    else -> "$statusCode : ${error.message}"
                }
                Toast.makeText(this@MainActivity, errorMessage, Toast.LENGTH_LONG)
                    .show()
            }
        })
    }


    private fun recyclerViewConfig() {

        DataView.layoutManager = LinearLayoutManager(DataView.context)
        DataView.setHasFixedSize(true)
        DataView.addItemDecoration(
            DividerItemDecoration(
                DataView.context,
                DividerItemDecoration.VERTICAL
            )
        )
    }

    private fun showRecyclerList() {
        DataView.layoutManager = LinearLayoutManager(this)
        val listDataAdapter = AdapterActivity(userFilterList)
        DataView.adapter = adapter

        listDataAdapter.setOnItemClickCallback(object : AdapterActivity.OnItemClickCallback {
            override fun onItemClicked(dataUsers: User) {
                showSelectedData(dataUsers)
            }
        })

    }

    private fun showSelectedData(data: User) {
        User(
            data.uname, data.name, data.foto, data.kantor, data.asal,
            data.repository, data.followers, data.following
        )
        Toast.makeText(this, "You Choose @${data.uname}", Toast.LENGTH_SHORT).show()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_settings) {
            val mIntent = Intent(this@MainActivity, DarkModeActivity::class.java)
            startActivity(mIntent)
        }
        return super.onOptionsItemSelected(item)

    }

    private fun getUserSearch(id: String){
        Mainbinding.progressBar.visibility = View.VISIBLE
        val client = AsyncHttpClient()
        client.addHeader("User-Agent", "request")
        client.addHeader("Authorization", "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")
        val url = "https://api.github.com/search/users?q=$id"

        client.get(url, object : AsyncHttpResponseHandler(){
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray
            ) {
                listData.clear()
                Mainbinding.progressBar.visibility = View.INVISIBLE
                val result = String(responseBody)
                Log.d(TAG, result)
                try {
                    val jsonArray = JSONObject(result)
                    val item = jsonArray.getJSONArray("items")
                    for (i in 0 until item.length()){
                        val jsonObject = item.getJSONObject(i)
                        val username: String = jsonObject.getString("login")

                     getDataDetail(username)
                    }

                }catch (e : Exception){
                    Toast.makeText(this@MainActivity, e.message, Toast.LENGTH_SHORT)
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
                Mainbinding.progressBar.visibility = View.INVISIBLE
                val errorMessage = when (statusCode) {
                    401 -> "$statusCode : Bad Request"
                    403 -> "$statusCode : Forbidden"
                    404 -> "$statusCode : Not Found"
                    else -> "$statusCode : ${error.message}"
                }
                Toast.makeText(this@MainActivity, errorMessage, Toast.LENGTH_LONG)
                    .show()
            }
        })

    }
}