package com.jasmeet.chatting

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.jasmeet.chatting.activity.NumberActivity
import com.jasmeet.chatting.adapter.ViewPagerAdapter
import com.jasmeet.chatting.databinding.ActivityMainBinding
import com.jasmeet.chatting.ui.CallFragment
import com.jasmeet.chatting.ui.ChatFragment
import com.jasmeet.chatting.ui.StatusFragment

class MainActivity : AppCompatActivity() {
    private  var binding : ActivityMainBinding? =null
    private lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        val fragmentArrayList = ArrayList<Fragment>()
        fragmentArrayList.add(ChatFragment())
        fragmentArrayList.add(StatusFragment())
        fragmentArrayList.add(CallFragment())

        auth = FirebaseAuth.getInstance()

        if(auth.currentUser == null){
            startActivity(Intent(this,NumberActivity::class.java))
            finish()
        }


        val adapter = ViewPagerAdapter(this,supportFragmentManager,fragmentArrayList)
        binding!!.viewPager.adapter = adapter

        binding!!.tabs.setupWithViewPager(binding!!.viewPager)

    }
}