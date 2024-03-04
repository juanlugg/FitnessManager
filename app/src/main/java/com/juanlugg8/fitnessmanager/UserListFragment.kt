package com.juanlugg8.fitnessmanager

import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.juanlugg8.fitnessmanager.adapter.UserAdapter
import com.juanlugg8.fitnessmanager.databinding.FragmentUserListBinding
import com.juanlugg8.fitnessmanager.entity.User
import com.juanlugg8.fitnessmanager.usecase.UserViewModel
import com.juanlugg8.fitnessmanager.utils.Notification

class UserListFragment : Fragment() {

    private var _binding: FragmentUserListBinding? = null
    private val binding get() = _binding!!

    private val viewModel : UserViewModel by viewModels()

    private lateinit var userAdapter : UserAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        channel = NotificationChannel(CHANNEL_ID,"Channel User List", NotificationManager.IMPORTANCE_LOW)
            .apply {
                description = "User List"
            }

        _binding = FragmentUserListBinding.inflate(inflater, container, false)
        binding.viewmodel = this.viewModel
        binding.lifecycleOwner = this
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userAdapter = UserAdapter({user: User, nav : Int ->
            var bundle = Bundle()
            bundle.putSerializable("user",user)
            parentFragmentManager.setFragmentResult("key", bundle)
        }, {user : User -> })
        binding.cvUserList.adapter = userAdapter
        binding.cvUserList.layoutManager = LinearLayoutManager(requireContext())

        viewModel.allUsers.observe(viewLifecycleOwner){
            Notification.showNotification(requireContext(),"List User", "Hello", channel, CHANNEL_ID , NOTIFICATION_ID)
            userAdapter.submitList(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    companion object{
        lateinit var channel : NotificationChannel
        private val NOTIFICATION_ID = 800
        private val CHANNEL_ID = "user_list"
    }
}