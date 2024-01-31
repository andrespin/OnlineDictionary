package andrespin.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch


abstract class BaseViewModel : ViewModel() {



    abstract val vmTag: String
    override fun onCleared() {
        super.onCleared()
        clear()
        Log.d("$vmTag lifecycle", "onCleared")
    }

    open fun clear() {}

}

abstract class AppViewModel<i : Intent, s : State> : BaseViewModel() {

    val intent = MutableSharedFlow<i>()
    protected val getIntent: SharedFlow<i> = intent

    protected val emitState = MutableSharedFlow<s>()
    val state: SharedFlow<s> get() = emitState

    abstract fun handleIntent(): Job

//    init {
//        viewModelScope.launch {
////            actionFlow.collect {
////                handleAction(it)
////            }
//        }
//    }

}
