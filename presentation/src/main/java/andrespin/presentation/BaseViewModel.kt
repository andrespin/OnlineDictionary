package andrespin.presentation

import android.util.Log
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {

    abstract val vmTag: String
    override fun onCleared() {
        super.onCleared()
        clear()
        Log.d("$vmTag lifecycle", "onCleared")
    }

    open fun clear() {}

}