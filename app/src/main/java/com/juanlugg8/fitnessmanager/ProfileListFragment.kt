package com.juanlugg8.fitnessmanager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.juanlugg8.fitnessmanager.adapter.ProfileAdapter
import com.juanlugg8.fitnessmanager.adapter.UserAdapter
import com.juanlugg8.fitnessmanager.databinding.FragmentProfileListBinding
import com.juanlugg8.fitnessmanager.databinding.FragmentUserListBinding
import com.juanlugg8.fitnessmanager.entity.Profile
import com.juanlugg8.fitnessmanager.entity.User
import com.juanlugg8.fitnessmanager.usecase.ProfileViewModel
import com.juanlugg8.fitnessmanager.usecase.UserViewModel

class ProfileListFragment : Fragment() {
    private var _binding: FragmentProfileListBinding? = null
    private val binding get() = _binding!!

    private val viewModel : ProfileViewModel by viewModels()

    private lateinit var profileAdapter : ProfileAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileListBinding.inflate(inflater, container, false)
        binding.viewmodel = this.viewModel
        binding.lifecycleOwner = this
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        profileAdapter = ProfileAdapter({ profile: Profile, nav : Int ->
            var bundle = Bundle()
            bundle.putSerializable("profile",profile)
            parentFragmentManager.setFragmentResult("key", bundle)
        }, {profile : Profile -> })
        binding.cvProfileList.adapter = profileAdapter
        binding.cvProfileList.layoutManager = LinearLayoutManager(requireContext())

        viewModel.allProfiles.observe(viewLifecycleOwner){
            profileAdapter.submitList(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}