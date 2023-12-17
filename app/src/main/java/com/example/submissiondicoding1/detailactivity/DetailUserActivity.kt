package com.example.submissiondicoding1.detailactivity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.submissiondicoding1.FavoriteMainActivity
import com.example.submissiondicoding1.R
import com.example.submissiondicoding1.databinding.ActivityDetailUserBinding
import com.example.submissiondicoding1.datauser.User
import com.google.android.material.tabs.TabLayoutMediator

class DetailUserActivity : AppCompatActivity() {

    private lateinit var Detailbinding: ActivityDetailUserBinding

    companion object{
        const val EXTRA_DATA = "extra_data"

        private val TAB_TITLES = intArrayOf(
            R.string.tab_text_1,
            R.string.tab_text_2
        )
    }


    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Detailbinding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(Detailbinding.root)

        val actionbar = supportActionBar
        actionbar!!.title = "Github Profile"
        actionbar.setDisplayHomeAsUpEnabled(true)

        Detailbinding.fabAdd.setOnClickListener{
            val intent = Intent(this@DetailUserActivity, FavoriteMainActivity::class.java)
            startActivity(intent)
        }

        setDetailData()

        viewPagerConfig()

    }

    private fun viewPagerConfig() {
        val sectionsPagerAdapter = SectionsPagerAdapter(this)
        val ViewPager = Detailbinding.viewPager
        val Tab = Detailbinding.tabs
        ViewPager.adapter = sectionsPagerAdapter

        TabLayoutMediator(Tab, ViewPager){ tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()
        supportActionBar?.elevation = 0f
    }

    @SuppressLint("SetTextI18n", "StringFormatInvalid")
    private fun setDetailData(){
        val data = intent.getParcelableExtra(EXTRA_DATA) as User?
        Detailbinding.detailUsername.text = getString(R.string.username_detail,data!!.uname)
        Detailbinding.detailNama.text = data!!.name
        if(data.name == "null"){
          Detailbinding.detailNama.visibility = View.INVISIBLE
        }
        Detailbinding.detailAsal.text = getString(R.string.asal_detail,data!!.asal)
        Detailbinding.detailKantor.text = getString(R.string.kantor_detail, data!!.kantor)
        Detailbinding.detailRepository.text = getString(R.string.repository_detail, data!!.repository)
        Detailbinding.detailFollowers.text = getString(R.string.followers_detail, data!!.followers)
        Detailbinding.detailFollowing.text = getString(R.string.following_detail, data!!.following)

        Glide.with(this)
            .load(data.foto)
            //.apply(RequestOptions())
            .into(Detailbinding.imgDetail)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}