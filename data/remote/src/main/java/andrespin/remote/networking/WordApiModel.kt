package andrespin.remote.networking

import andrespin.domain.entity.Word
import andrespin.domain.entity.WordDescription

data class WordApiModel(
    val def: List<Def>,
    val head: Head
) {

    fun mapToWord(): Word {
        var text = ""
        var ts = ""
        var isFound = true
        text = def[0]?.text ?: ""
        ts = def[0]?.ts ?: ""
        return Word(
            text,
            ts,
            convertToWordDescription(this, isFound),
            isFound
        )
    }

    private fun convertToWordDescription(response: WordApiModel, isFound: Boolean)
            : List<WordDescription> {
        val listWordDesc = mutableListOf<WordDescription>()

        if (!isFound) return listWordDesc

        for (i in 0 until response.def.size) {
            listWordDesc.add(
                WordDescription(
                    response.def[i].pos,
                    response.def[i].text,
                    convertToTextWithWords(response.def[i].tr)
                )
            )
        }
        return listWordDesc
    }

    private fun convertToTextWithWords(listWordsTr: List<Tr>): String {
        var text = ""
        for (i in 0 until listWordsTr.size) {
            text += if (i == 0) {
                listWordsTr[i].text
            } else {
                ", ${listWordsTr[i].text}"
            }
        }
        return text
    }

    data class Def(

        // часть речи
        val pos: String,
        // Слово в оригинале
        val text: String,
        // переводы слова
        val tr: List<Tr>,
        // транскрипция
        val ts: String


    )

    data class Tr(
        val fr: Int,
        val mean: List<Mean>,
        val pos: String,
        val syn: List<Syn>,
        val text: String
    )

    data class Mean(
        val text: String
    )

    data class Syn(
        val fr: Int,
        val pos: String,
        val text: String
    )

    class Head

}
