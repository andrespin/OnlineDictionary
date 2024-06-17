package andrespin.local

import andrespin.domain.EmptyField
import andrespin.domain.adjective
import andrespin.domain.adverb
import andrespin.domain.adverbial_participle
import andrespin.domain.conjunction
import andrespin.domain.entity.Word
import andrespin.domain.noun
import andrespin.local.db.word.WordEntity
import andrespin.domain.entity.WordDescription
import andrespin.domain.interjection
import andrespin.domain.invariable
import andrespin.domain.numeral
import andrespin.domain.participle
import andrespin.domain.particle
import andrespin.domain.preposition
import andrespin.domain.pronoun
import andrespin.domain.verb

private var translationsOfNoun = EmptyField
private var translationsOfPronoun = EmptyField
private var translationsOfAdjective = EmptyField

private var translationsOfVerb = EmptyField
private var translationsOfAdverb = EmptyField
private var translationsOfPreposition = EmptyField

private var translationsOfConjunction = EmptyField
private var translationsOfInterjection = EmptyField
private var translationsOfNumeral = EmptyField

private var translationsOfParticle = EmptyField
private var translationsOfInvariable = EmptyField
private var translationsOfParticiple = EmptyField

private var translationsOfAdverbialParticiple = EmptyField

fun Word.mapToWordEntity(): WordEntity {
    val wordDescriptions = this.wordDescriptions
    if (wordDescriptions != null) associateTranslationsWithPartOfSpeechSections(wordDescriptions)
    return WordEntity(
        this.txtOrig,
        this.txtPhonetics,
        translationsOfNoun,
        translationsOfPronoun,
        translationsOfAdjective,
        translationsOfVerb,
        translationsOfAdverb,
        translationsOfPreposition,
        translationsOfConjunction,
        translationsOfInterjection,
        translationsOfNumeral,
        translationsOfParticle,
        translationsOfInvariable,
        translationsOfParticiple,
        translationsOfAdverbialParticiple
    )
}

private fun associateTranslationsWithPartOfSpeechSections(wordDescriptions: List<WordDescription>) {

    for (i in 0 until wordDescriptions.size) {

        when (wordDescriptions[i].partOfSpeech) {

            noun -> {
                translationsOfNoun = wordDescriptions[i].wordTranslationsOneLine
            }

            pronoun -> {
                translationsOfPronoun = wordDescriptions[i].wordTranslationsOneLine
            }

            adjective -> {
                translationsOfAdjective = wordDescriptions[i].wordTranslationsOneLine
            }

            verb -> {
                translationsOfVerb = wordDescriptions[i].wordTranslationsOneLine
            }

            adverb -> {
                translationsOfAdverb = wordDescriptions[i].wordTranslationsOneLine
            }

            preposition -> {
                translationsOfPreposition = wordDescriptions[i].wordTranslationsOneLine
            }

            conjunction -> {
                translationsOfConjunction = wordDescriptions[i].wordTranslationsOneLine
            }

            interjection -> {
                translationsOfInterjection = wordDescriptions[i].wordTranslationsOneLine
            }

            numeral -> {
                translationsOfNumeral = wordDescriptions[i].wordTranslationsOneLine
            }

            particle -> {
                translationsOfParticle = wordDescriptions[i].wordTranslationsOneLine
            }

            invariable -> {
                translationsOfInvariable = wordDescriptions[i].wordTranslationsOneLine
            }

            participle -> {
                translationsOfParticiple = wordDescriptions[i].wordTranslationsOneLine
            }

            adverbial_participle -> {
                translationsOfAdverbialParticiple = wordDescriptions[i].wordTranslationsOneLine
            }

        }
    }
}
