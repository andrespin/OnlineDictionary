package andrespin.presentation

import androidx.viewbinding.ViewBinding
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

abstract class MviViewModel<i : Intent, s : State> : BaseViewModel() {

    val intent = MutableSharedFlow<i>()
    protected val getIntent: SharedFlow<i> = intent

    protected val emitState = MutableSharedFlow<s>()
    val state: SharedFlow<s> get() = emitState

    abstract fun handleIntent(): Job

}