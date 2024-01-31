package andrespin.dictionary

import andrespin.dictionary.adapter.previous_word.PreviousWordsAdapter
import andrespin.dictionary.adapter.word.WordAdapter
import andrespin.dictionary.databinding.FragmentDictionaryBinding
import andrespin.domain.entity.PreviousWord
import andrespin.presentation.BaseFragment
import android.util.Log
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.launch

abstract class DictionaryFragmentAbstract<
        VB : FragmentDictionaryBinding,
        VM : DictionaryViewModel,
        > : BaseFragment<VB, VM>() {

    lateinit var previousWordsAdapter: PreviousWordsAdapter
    lateinit var wordAdapter: WordAdapter

    protected fun initAdapters(fragment: DictionaryFragment) {
        initPreviousWordsAdapter(PreviousWordsAdapter(fragment, model))
        initWordAdapter(WordAdapter())
    }

    private fun initPreviousWordsAdapter(previousWordsAdapter: PreviousWordsAdapter) {
        this.previousWordsAdapter = previousWordsAdapter
        binding.rvWords.layoutManager = GridLayoutManager(
            requireContext(), 2, LinearLayoutManager.HORIZONTAL, false
        )
        binding.rvWords.adapter = previousWordsAdapter
    }

    private fun initWordAdapter(wordAdapter: WordAdapter) {
        this.wordAdapter = wordAdapter
        binding.layoutDictionary.rvTranslation.layoutManager = LinearLayoutManager(requireContext())
        binding.layoutDictionary.rvTranslation.adapter = wordAdapter
    }

    protected fun initOnQueryTextListener() =
        binding.layoutSearch.search.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                hideAll()
                emitIntentSearchNewWord(query)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                hideAll()
                emitIntentShowPreviousWords(newText)
                return false
            }
        })

    protected fun initOnSearchClickListener() = binding.layoutSearch.search.setOnClickListener {
        turnSearchStateOn()
        emitIntentShowPreviousWords(null)
    }

    protected fun initOnSearchCloseListener() = binding.layoutSearch.search.setOnCloseListener {
        emitIntentHidePreviousWords()
        false
    }

    private fun turnSearchStateOn() {
        binding.layoutSearch.search.isIconified = false
    }

    private fun emitIntentShowPreviousWords(newText: String?) = lifecycleScope.launch {
        Log.d("DictionaryFragmentAbstract", "emitIntentShowPreviousWords $newText")
        model.intent.emit(DictionaryIntent.ShowPreviousWords(newText))
    }


    private fun emitIntentSearchNewWord(query: String?) = lifecycleScope.launch {
        if (!query.isNullOrBlank()) model.intent.emit(DictionaryIntent.SearchNewWord(query))
    }


    protected fun showPrevWords(it: DictionaryState.ShowPreviousWords) {
        setWordsToWordsAdapter(it.words)
    }

    protected fun setWordsToWordsAdapter(words: List<PreviousWord>) {
        Log.d("DictionaryFragmentAbstract", "prevWords ${words}")
        previousWordsAdapter.setData(words)
    }


    protected fun showUnknown() {
        hideAll()
        showUnknownLayout()
    }

    protected fun showNetworkTrouble() {
        hideAll()
        showNetworkTroubleLayout()
    }

    protected fun showNotFound() {
        hideAll()
        showNotFoundLayout()
    }


    protected fun showNoKey() {
        hideAll()
        showNoKeyLayout()
    }


    private fun showNotFoundLayout() {
        binding.layoutDictionaryErrorNotFound.root.visibility = View.VISIBLE
    }

    private fun showNoKeyLayout() {
        binding.layoutDictionaryErrorNoKey.root.visibility = View.VISIBLE
    }

    private fun showUnknownLayout() {
        binding.layoutDictionaryErrorUnknown.root.visibility = View.VISIBLE
    }

    private fun showNetworkTroubleLayout() {
        binding.layoutDictionaryErrorNetwork.root.visibility = View.VISIBLE
    }

    protected fun showNoConnection() {
        hideAll()
        showNoConnectionLayout()
    }

    protected fun showInvalidKey() {
        hideAll()
        showInvalidKeyLayout()
    }

    private fun showInvalidKeyLayout() {
        binding.layoutDictionaryErrorInvalidKey.root.visibility = View.VISIBLE
    }

    private fun showNoConnectionLayout() {
        binding.layoutDictionaryErrorNoConnection.root.visibility = View.VISIBLE
    }

    protected fun showFoundWord(it: DictionaryState.ShowWord) {
        emitIntentHidePreviousWords()
        showDictionaryLayout()
        setWord(it.word.txtOrig, it.word.txtPhonetics)
        setTranslatedWordDataToAdapter(it)
    }

    private fun setTranslatedWordDataToAdapter(it: DictionaryState.ShowWord) =
        it.word.wordDescriptions?.let { it1 -> wordAdapter.setData(it1) }


    private fun showDictionaryLayout() {
        hideAll()
        setLayoutDictionaryOn()
    }



    private fun setWord(word: String, transcription: String) {
        binding.layoutDictionary.txtTranslatedWord.text = word
        binding.layoutDictionary.txtTranscription.text = transcription
    }

    private fun setLayoutDictionaryOn() {
        binding.layoutDictionary.root.visibility = View.VISIBLE
    }

    protected fun showLoading() {
        hideAll()
        setLoadingOn()
    }

    private fun setLoadingOn() {
        binding.layoutDictionaryLoading.root.visibility = View.VISIBLE
    }

    private fun hideAll() {
        emitIntentHidePreviousWords()
        setAllGone()
    }

    private fun emitIntentHidePreviousWords() = lifecycleScope.launch {
        model.intent.emit(DictionaryIntent.HidePreviousWords)
    }

    private fun setAllGone() {
        binding.layoutDictionary.root.visibility = View.GONE
        binding.layoutDictionaryErrorInvalidKey.root.visibility = View.GONE
        binding.layoutDictionaryErrorNoConnection.root.visibility = View.GONE
        binding.layoutDictionaryErrorNoKey.root.visibility = View.GONE
        binding.layoutDictionaryErrorNotFound.root.visibility = View.GONE
        binding.layoutDictionaryErrorUnknown.root.visibility = View.GONE
        binding.layoutDictionaryLoading.root.visibility = View.GONE
    }



    protected fun initOnMenuItemClickListener(
        navController: NavController
    ) {

        binding.topAppBar.setOnMenuItemClickListener { menuItem ->

            when (menuItem.itemId) {

                andrespin.presentation.R.id.settings -> {
                    navigateAboutApp(navController)
                    true
                }

                andrespin.presentation.R.id.aboutApp -> {
                    navigateSettings(navController)
                    true
                }

                else -> false
            }
        }
    }

    protected fun navigateBack(
        navController: NavController
    ) = navController.popBackStack()


    private fun navigateAboutApp(
        navController: NavController,
    ) = navController.navigate(andrespin.presentation.R.id.aboutApp)


    private fun navigateSettings(
        navController: NavController
    ) = navController.navigate(andrespin.presentation.R.id.settings)






}