package andrespin.settings

import andrespin.settings.databinding.FragmentSettingsBinding
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SettingsFragment : SettingsFragmentAbstract<FragmentSettingsBinding, SettingsViewModel>() {
    override val viewModelClass: Class<SettingsViewModel>
        get() = SettingsViewModel::class.java
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentSettingsBinding
        get() = FragmentSettingsBinding::inflate
    override val frTag: String
        get() = "SettingsFragment"


    override fun initClickListeners() {
        setOnNavigationClickListener()
        initInsertKeyClickListener()
    }

    override fun process() {
        getKey()
    }

    override fun observeViewModel(): Job = lifecycleScope.launch {
        model.state.collect {
            when(it) {
                is SettingsState.ShowKey -> showKey(it)
                SettingsState.KeyIsCorrect -> showKeyIsCorrect()
                SettingsState.KeyIsNotCorrect -> showKeyIsNotCorrect()
                SettingsState.Loading -> showKeyIsChecking()
                SettingsState.SetKey -> showSetKey()
                SettingsState.KeyFieldIsEmpty -> showKeyFieldIsEmpty()
                is SettingsState.KeyIsCorrectData -> showKeyIsCorrect(it.key)
                is SettingsState.KeyIsNotCorrectData -> showKeyIsNotCorrect(it.key)
            }
        }
    }

}