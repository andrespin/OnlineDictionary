package andrespin.dictionary.adapter.word

import andrespin.dictionary.databinding.ItemPartOfSpeechBinding
import andrespin.domain.entity.WordDescription
import androidx.recyclerview.widget.RecyclerView

class WordViewHolder(private val vb: ItemPartOfSpeechBinding) : RecyclerView.ViewHolder(vb.root) {

    fun bind(response: WordDescription) {
        vb.txtItemPartOfSpeech.text = response.partOfSpeech
        vb.txtItemWordTranslation.text = response.wordTranslationsOneLine
    }

}