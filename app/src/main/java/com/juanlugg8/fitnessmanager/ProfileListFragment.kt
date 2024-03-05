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
import com.juanlugg8.fitnessmanager.adapter.ProfileAdapter
import com.juanlugg8.fitnessmanager.databinding.FragmentProfileListBinding
import com.juanlugg8.fitnessmanager.entity.Profile
import com.juanlugg8.fitnessmanager.usecase.ProfileViewModel
import com.juanlugg8.fitnessmanager.utils.Notification

class ProfileListFragment : Fragment() {
    private var _binding: FragmentProfileListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ProfileViewModel by viewModels()

    private lateinit var profileAdapter: ProfileAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        channel = NotificationChannel(
            CHANNEL_ID,
            "Channel Profile List",
            NotificationManager.IMPORTANCE_LOW
        )
            .apply {
                description = "Profile List"
            }

        _binding = FragmentProfileListBinding.inflate(inflater, container, false)
        binding.viewmodel = this.viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        profileAdapter = ProfileAdapter({ profile: Profile, nav: Int ->
            var bundle = Bundle()
            bundle.putSerializable("profile", profile)
            parentFragmentManager.setFragmentResult("key", bundle)
        }, { profile: Profile -> })
        binding.cvProfileList.adapter = profileAdapter
        binding.cvProfileList.layoutManager = LinearLayoutManager(requireContext())

        viewModel.allProfiles.observe(viewLifecycleOwner) {
            Notification.showNotificationWithNav(
                requireContext(), "Profile List", "GO TO MAIN", channel, CHANNEL_ID,
                NOTIFICATION_ID, R.id.UserListFragment
            )
            profileAdapter.submitList(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        lateinit var channel: NotificationChannel
        private val NOTIFICATION_ID = 800
        private val CHANNEL_ID = "profile_list"
    }
}