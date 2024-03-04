package com.juanlugg8.fitnessmanager.preferences

import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.ListPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreference
import com.juanlugg8.fitnessmanager.R
import com.juanlugg8.fitnessmanager.database.Locator

class SettingsFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.setting, rootKey)
        initPrefences()
        initPreferencesTheme()
    }

    private fun initPreferencesTheme() {
        val option = preferenceManager.findPreference<Preference>(getString(R.string.theme)) as SwitchPreference?

        option?.setOnPreferenceChangeListener { preference, newValue ->
            if(newValue == true){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                Locator.PreferencesRepository.saveTheme("true")
            }else{
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                Locator.PreferencesRepository.saveTheme("false")
            }
            true
        }

    }
    //EXAMPLE
    private fun initPrefences(){
        findPreference<SwitchPreference>(getString(R.string.preference))?.apply {
            onPreferenceChangeListener = Preference.OnPreferenceChangeListener { preference, newValue ->
                if(newValue == true){
                    Locator.PreferencesRepository.savePreference("true")
                }else{
                    Locator.PreferencesRepository.savePreference("false")
                }
                true
            }
        }
    }
}