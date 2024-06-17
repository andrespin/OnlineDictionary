package andrespin.dictionary

import andrespin.dictionary.databinding.FragmentDictionaryBinding
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint

import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DictionaryFragment :
    DictionaryFragmentAbstract<FragmentDictionaryBinding, DictionaryViewModel>() {

    override val viewModelClass: Class<DictionaryViewModel>
        get() = DictionaryViewModel::class.java
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentDictionaryBinding
        get() = FragmentDictionaryBinding::inflate
    override val frTag: String
        get() = "DictionaryFragment"

    override fun initClickListeners() {
        initOnQueryTextListener()
        initOnSearchClickListener()
        initOnSearchCloseListener()
        initOnMenuItemClickListener()
        initNavigationOnClickListener()
    }

    override fun process() = initAdapters(this)

    override fun observeViewModel() = lifecycleScope.launch {
        model.state.collectLatest {
            when (it) {
                is DictionaryState.ShowPreviousWords -> showPrevWords(it)
                is DictionaryState.ShowWord -> showFoundWord(it)
                DictionaryState.Loading -> showLoading()
                DictionaryState.InvalidKeyException -> showInvalidKey()
                DictionaryState.NoConnectionException -> showNoConnection()
                DictionaryState.NoKeyException -> showNoKey()
                DictionaryState.NotFoundException -> showNotFound()
                DictionaryState.UnknownException -> showUnknown()
                DictionaryState.WordNetworkException -> showNetworkTrouble()
            }
        }
    }

}