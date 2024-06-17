package andrespin.settings

import andrespin.domain.NoKey
import andrespin.domain.entity.Result
import andrespin.domain.usecase.UseCaseException
import andrespin.domain.usecase.local.key.CheckKeyUseCase
import andrespin.domain.usecase.local.key.GetKeyUseCase
import andrespin.presentation.AppViewModel
import android.util.Log
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel
@Inject constructor(
    private val checkKey: CheckKeyUseCase,
    private val getKeyUseCase: GetKeyUseCase,
) : AppViewModel<SettingsIntent, SettingsState>() {

    init {
        handleIntent()
    }

    override val vmTag: String
        get() = "SettingsViewModel"

    override fun handleIntent(): Job = viewModelScope.launch {
        getIntent.collectLatest {
            when (it) {
                SettingsIntent.CheckKey -> getKey()
                is SettingsIntent.InsertKey -> insertKey(it.key)
            }
        }
    }

    private suspend fun insertKey(key: String) =
        if (key.isNotEmpty()) {
            emitState.emit(SettingsState.Loading)
            checkKey.invoke(key).collect {
                when(it) {
                    is Result.Error -> emitState.emit(SettingsState.KeyIsNotCorrect)
                    is Result.Success -> emitState.emit(SettingsState.KeyIsCorrect)
                }
            }
        } else {
            emitState.emit(SettingsState.KeyFieldIsEmpty)
        }

    private suspend fun getKey() {
        getKeyUseCase.invoke().collect{
            when(it) {
                is Result.Success -> emitState.emit(SettingsState.KeyIsCorrectData(it.data))
                is Result.Error -> emitState.emit(SettingsState.KeyIsNotCorrectData(""))
            }
        }
    }

}