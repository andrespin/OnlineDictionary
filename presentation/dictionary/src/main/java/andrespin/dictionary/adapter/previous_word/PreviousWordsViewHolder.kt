package andrespin.dictionary.adapter.previous_word

import andrespin.dictionary.DictionaryFragment
import andrespin.dictionary.R
import andrespin.dictionary.databinding.ItemPreviousWordBinding
import andrespin.domain.entity.PreviousWord
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class PreviousWordsViewHolder(
    private val vb: ItemPreviousWordBinding,
    private val dictionaryFragment: DictionaryFragment,
) : RecyclerView.ViewHolder(vb.root) {


    private val highlightColor =
        dictionaryFragment.context?.let {
            ContextCompat.getColor(
                it, andrespin.presentation.R.color.txt_underline_prev_words_color
            )
        }

    fun bind(prevWord: PreviousWord) {
        val txt = prevWord.word.txtOrig
        val span = SpannableString(txt)
        span.setSpan(
            highlightColor?.let { ForegroundColorSpan(it) },
            prevWord.start,
            prevWord.end,
            0
        )
        vb.txtWord.setText(span, TextView.BufferType.SPANNABLE)
    }

}