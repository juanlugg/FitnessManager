package com.juanlugg8.fitnessmanager

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


class UserListFragment : Fragment() {

    private var _binding: FragmentUserListBinding? = null
    private val binding get() = _binding!!

    private val viewModel : UserViewModel by viewModels()

    private lateinit var userAdapter : UserAdapter

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

        userAdapter = UserAdapter({user: User, nav : Int ->
            var bundle = Bundle()
            bundle.putSerializable("user",user)
            parentFragmentManager.setFragmentResult("key", bundle)
        }, {user : User -> })
        binding.cvUserList.adapter = userAdapter
        binding.cvUserList.layoutManager = LinearLayoutManager(requireContext())

        viewModel.allUsers.observe(viewLifecycleOwner){
            userAdapter.submitList(it)
            //println("HOLAAAAAAAAAAAAAAAAAAAAAAAAAA "+it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}