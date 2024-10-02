package andrespin.dictionary.adapter.previous_word

import andrespin.dictionary.DictionaryFragment
import andrespin.dictionary.DictionaryIntent
import andrespin.dictionary.DictionaryViewModel
import andrespin.dictionary.databinding.ItemPreviousWordBinding
import andrespin.domain.entity.PreviousWord
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch

class PreviousWordsAdapter(
    private val dictionaryFragment: DictionaryFragment,
    private val dictionaryViewModel: DictionaryViewModel,
) : RecyclerView.Adapter<PreviousWordsViewHolder>() {

    private val tag = "PreviousWordsAdapter"

    private var prevWords: List<PreviousWord> = arrayListOf()

    fun setData(prevWords: List<PreviousWord>) {
        this.prevWords = prevWords
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PreviousWordsViewHolder =
        PreviousWordsViewHolder(
            ItemPreviousWordBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ),
            dictionaryFragment
        ).apply {
            itemView.setOnClickListener {
                val pos = this.layoutPosition
                dictionaryFragment.lifecycleScope.launch {
                    dictionaryViewModel.intent.emit(
                        DictionaryIntent.GetPrevWord(prevWords[pos].word.txtOrig)
                    )
                }
            }
        }

    override fun onBindViewHolder(holder: PreviousWordsViewHolder, position: Int) =
        holder.bind(prevWords[position])

    override fun getItemCount() = prevWords.size

}
