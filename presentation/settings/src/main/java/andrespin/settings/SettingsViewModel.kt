package andrespin.settings

import andrespin.presentation.AppViewModel
import android.util.Log
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job

//@HiltViewModel
class SettingsViewModel : AppViewModel<SettingsIntent, SettingsState>() {
    override fun handleIntent(): Job {
        TODO("Not yet implemented")
    }

    init {
        Log.d(vmTag, "Init")
    }

    override val vmTag: String
        get() = "SettingsViewModel"


}