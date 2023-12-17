package com.example.submissiondicoding1.detailactivity

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.submissiondicoding1.followers.FollowesFragment
import com.example.submissiondicoding1.following.FollowingFragment

class SectionsPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity)  {
    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment?=null
        when(position){
            0 -> fragment = FollowesFragment()
            1 -> fragment = FollowingFragment()
        }
        return fragment as Fragment
    }

    override fun getItemCount(): Int {
        return 2
    }

}