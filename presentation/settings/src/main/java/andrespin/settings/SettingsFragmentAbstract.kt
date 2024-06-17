package andrespin.settings

import andrespin.presentation.BaseFragment
import andrespin.presentation.NavRoutes
import andrespin.settings.databinding.FragmentSettingsBinding
import android.view.View
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch


abstract class SettingsFragmentAbstract<VB : FragmentSettingsBinding, VM : SettingsViewModel> :
    BaseFragment<VB, VM>() {


    protected fun setOnNavigationClickListener() =
        binding.topAppBar.setNavigationOnClickListener {
            navigate(NavRoutes.NavigateBack)
        }

    protected fun initInsertKeyClickListener() =
        binding.btnInsertKey.setOnClickListener {
            lifecycleScope.launch {
                model.intent.emit(
                    SettingsIntent.InsertKey(
                        binding.editKey.text.toString()
                    )
                )
            }
        }

    protected fun getKey() =
        lifecycleScope.launch {
            model.intent.emit(SettingsIntent.CheckKey)
        }

    protected fun showKey(it: SettingsState.ShowKey) = binding.editKey.setText(it.key)


    protected fun showKeyFieldIsEmpty() {
        hideAll()
        keyIsEmpty()
    }

    protected fun showKeyIsChecking() {
        hideAll()
        keyIsChecking()
    }

    protected fun showKeyIsNotCorrect() {
        hideAll()
        keyIsNotCorrect()
    }

    protected fun showKeyIsNotCorrect(key: String) {
        hideAll()
        keyIsNotCorrect(key)
    }

    protected fun showKeyIsCorrect() {
        hideAll()
        keyIsCorrect()
    }

    protected fun showKeyIsCorrect(key: String) {
        hideAll()
        keyIsCorrect(key)
    }

    protected fun showSetKey() = hideAll()

    private fun keyIsChecking() {
        binding.txtHintKeyIsChecking.visibility = View.VISIBLE
    }

    private fun keyIsEmpty() {
        binding.txtHintKeyIsEmpty.visibility = View.VISIBLE
    }

    private fun keyIsNotCorrect() {
        binding.txtHintKeyIsNotCorrect.visibility = View.VISIBLE
    }

    private fun keyIsNotCorrect(key: String) {
        binding.editKey.setText(key)
        binding.txtHintKeyIsNotCorrect.visibility = View.VISIBLE
    }

    private fun keyIsCorrect() {
        binding.txtHintKeyIsCorrect.visibility = View.VISIBLE
    }

    private fun keyIsCorrect(key: String) {
        binding.editKey.setText(key)
        binding.txtHintKeyIsCorrect.visibility = View.VISIBLE
    }

    private fun hideAll() {
        binding.txtHintKeyIsCorrect.visibility = View.GONE
        binding.txtHintKeyIsNotCorrect.visibility = View.GONE
        binding.txtHintKeyIsChecking.visibility = View.GONE
        binding.txtHintKeyIsEmpty.visibility = View.GONE
    }

}