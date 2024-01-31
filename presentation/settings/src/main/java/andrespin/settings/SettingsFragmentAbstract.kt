package andrespin.settings

import andrespin.presentation.BaseFragment
import andrespin.settings.databinding.FragmentSettingsBinding
import android.view.View


abstract class SettingsFragmentAbstract<VB : FragmentSettingsBinding, VM : SettingsViewModel> :
    BaseFragment<VB, VM>() {


    protected fun setOnNavigationClickListener() {
        binding.topAppBar.setNavigationOnClickListener {

        }
    }

    protected fun setInsertKeyClickListener() {
        binding.btnInsertKey.setOnClickListener {

        }
    }

    protected fun showKey(it: SettingsState.ShowKey) = binding.editKey.setText(it.key)

    protected fun showKeyIsChecking() {
        hideAll()
        keyIsChecking()
    }

    protected fun showKeyIsNotCorrect() {
        hideAll()
        keyIsNotCorrect()
    }

    protected fun showKeyIsCorrect() {
        hideAll()
        keyIsCorrect()
    }

    protected fun showSetKey() = hideAll()

    private fun keyIsChecking() {
        binding.txtHintKeyIsChecking.visibility = View.VISIBLE
    }

    private fun keyIsNotCorrect() {
        binding.txtHintKeyIsNotCorrect.visibility = View.VISIBLE
    }

    private fun keyIsCorrect() {
        binding.txtHintKeyIsCorrect.visibility = View.VISIBLE
    }

    private fun hideAll() {
        binding.txtHintKeyIsCorrect.visibility = View.GONE
        binding.txtHintKeyIsNotCorrect.visibility = View.GONE
        binding.txtHintKeyIsChecking.visibility = View.GONE
    }

}