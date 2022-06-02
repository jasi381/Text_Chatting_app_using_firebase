package com.jasmeet.chatting.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.jasmeet.chatting.adapter.ChatAdapter
import com.jasmeet.chatting.databinding.FragmentChatBinding
import com.jasmeet.chatting.model.UserModel


class ChatFragment : Fragment() {


    lateinit var binding: FragmentChatBinding
    private var database :FirebaseDatabase? = null
    lateinit var userList: ArrayList<UserModel>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentChatBinding.inflate(layoutInflater)

        database= FirebaseDatabase.getInstance()
        userList = ArrayList()

        database!!.reference.child("users")
            .addValueEventListener(object:ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    userList.clear()

                    for(snapshot1 in snapshot.children){
                        val user = snapshot1.getValue(UserModel::class.java)
                        if(user!!.uid != FirebaseAuth.getInstance().uid){
                            userList.add(user)
                        }

                    }
                    binding.userListRecyclerView.adapter = ChatAdapter(requireContext(),userList)
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }
            })

        return binding.root
    }


}