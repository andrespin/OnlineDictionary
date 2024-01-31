package andrespin.dictionary.adapter.word

import andrespin.dictionary.databinding.ItemPartOfSpeechBinding
import andrespin.domain.entity.WordDescription
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class WordAdapter : RecyclerView.Adapter<WordViewHolder>() {

    private var data: List<WordDescription> = arrayListOf()

    fun setData(data: List<WordDescription>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        WordViewHolder(
            ItemPartOfSpeechBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) =
        holder.bind(data[position])

    override fun getItemCount() = data.size


}
