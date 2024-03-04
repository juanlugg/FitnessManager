package com.juanlugg8.fitnessmanager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.fragment.findNavController
import com.juanlugg8.fitnessmanager.database.Locator
import com.juanlugg8.fitnessmanager.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnUser.setOnClickListener {
            findNavController().navigate(R.id.action_MainFragment_to_UserFragment)
        }
        binding.btnProfile.setOnClickListener {
            findNavController().navigate(R.id.action_MainFragment_to_ProfileListFragment)
        }
        initTheme()
        initPreferences()
    }
    private fun initTheme() {
        var value = Locator.PreferencesRepository.getTheme()
        if (value == "true") {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }

    private fun initPreferences() {
        var value = Locator.PreferencesRepository.getPreference()

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}