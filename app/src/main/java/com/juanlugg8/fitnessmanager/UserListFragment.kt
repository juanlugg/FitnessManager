package com.juanlugg8.fitnessmanager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.juanlugg8.fitnessmanager.databinding.FragmentUserListBinding
import com.juanlugg8.fitnessmanager.usecase.UserViewModel


class UserListFragment : Fragment() {

    private var _binding: FragmentUserListBinding? = null
    private val binding get() = _binding!!

    private val viewModel : UserViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

            _binding = FragmentUserListBinding.inflate(inflater, container, false)
        binding.viewmodel = this.viewModel
        binding.lifecycleOwner = this
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.allUsers.observe(viewLifecycleOwner){
            println("HOLAAAAAAAAAAAAAAAAAAAAAAAAAA "+it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}