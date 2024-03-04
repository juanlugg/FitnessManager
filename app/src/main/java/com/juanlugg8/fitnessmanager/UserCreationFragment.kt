package com.juanlugg8.fitnessmanager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.juanlugg8.fitnessmanager.databinding.FragmentUserCreationBinding
import com.juanlugg8.fitnessmanager.usecase.UserState
import com.juanlugg8.fitnessmanager.usecase.UserViewModel

class UserCreationFragment : Fragment() {
    private var _binding : FragmentUserCreationBinding? = null
    private val binding get() = _binding!!

    private val viewModel : UserViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUserCreationBinding.inflate(inflater,container,false)
        binding.viewmodel = this.viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getState().observe(viewLifecycleOwner){
            when(it){
                UserState.NameIsMandatoryError -> {}
                UserState.Success -> {
                    viewModel.makeUser()
                    findNavController().popBackStack()
                }
            }
        }
    }
}