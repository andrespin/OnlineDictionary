package andrespin.domain.entity

data class Word(
    val txtOrig: String,
    val txtPhonetics: String = "null",
    val wordDescriptions: List<WordDescription>? = null,
    val isFound: Boolean = false,
    val language: Language = Language.NotIdentified
)
