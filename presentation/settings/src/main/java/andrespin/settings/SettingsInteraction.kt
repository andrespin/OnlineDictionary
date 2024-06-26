package andrespin.settings

import andrespin.presentation.Intent
import andrespin.presentation.State

sealed class SettingsState : State {

    data class ShowKey(val key: String) : SettingsState()

    object Loading : SettingsState()
    object KeyIsCorrect: SettingsState()
    object KeyIsNotCorrect: SettingsState()

    data class KeyIsCorrectData(val key: String): SettingsState()

    data class KeyIsNotCorrectData(val key: String): SettingsState()

    data object KeyFieldIsEmpty: SettingsState()
    object SetKey: SettingsState()

}

sealed class SettingsIntent : Intent {

    object CheckKey : SettingsIntent()

    data class InsertKey(val key: String) : SettingsIntent()

}
