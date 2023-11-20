package andrespin.local.db.word

import andrespin.domain.EmptyField
import andrespin.domain.adjective
import andrespin.domain.adverb
import andrespin.domain.adverbial_participle
import andrespin.domain.conjunction
import andrespin.domain.entity.Word
import andrespin.domain.entity.WordDescription
import andrespin.domain.interjection
import andrespin.domain.noun
import andrespin.domain.numeral
import andrespin.domain.particle
import andrespin.domain.preposition
import andrespin.domain.pronoun
import andrespin.domain.verb

open class WordEntityMapper {

        protected fun mapWordEntityToWord(wordEntity: WordEntity) : Word {
            val descList = mutableListOf<WordDescription>()
            addTranslationsOfNoun(descList, wordEntity)
            addTranslationsOfPronoun(descList, wordEntity)
            addTranslationsOfAdjective(descList, wordEntity)
            addTranslationsOfVerb(descList, wordEntity)
            addTranslationsOfAdverb(descList, wordEntity)
            addTranslationsOfPreposition(descList, wordEntity)
            addTranslationsOfConjunction(descList, wordEntity)
            addTranslationsOfInterjection(descList, wordEntity)
            addTranslationsOfNumeral(descList, wordEntity)
            addTranslationsOfParticle(descList, wordEntity)
            addTranslationsOfAdverbialParticiple(descList, wordEntity)
            return Word(
                wordEntity.txtOrig, wordEntity.txtPhonetics, descList, true
            )
        }

    private fun addTranslationsOfNoun(
        descList: MutableList<WordDescription>,
        wordEntity: WordEntity,
    ) {
        println("wordEntity.translationsOfNoun ${wordEntity.translationsOfNoun}")
        if (wordEntity.translationsOfNoun != EmptyField) {
            descList.add(
                WordDescription(
                    noun, wordEntity.txtOrig, wordEntity.translationsOfNoun
                )
            )
        }
    }

    private fun addTranslationsOfPronoun(
        descList: MutableList<WordDescription>,
        wordEntity: WordEntity,
    ) {
        println("wordEntity.translationsOfPronoun ${wordEntity.translationsOfPronoun}")
        if (wordEntity.translationsOfPronoun != EmptyField) {
            descList.add(
                WordDescription(
                    pronoun, wordEntity.txtOrig, wordEntity.translationsOfPronoun
                )
            )
        }
    }

    private fun addTranslationsOfAdjective(
        descList: MutableList<WordDescription>,
        wordEntity: WordEntity,
    ) {
        if (wordEntity.translationsOfAdjective != EmptyField) {
            descList.add(
                WordDescription(
                    adjective, wordEntity.txtOrig, wordEntity.translationsOfAdjective
                )
            )
        }
    }

    private fun addTranslationsOfVerb(
        descList: MutableList<WordDescription>,
        wordEntity: WordEntity,
    ) {
        if (wordEntity.translationsOfVerb != EmptyField) {
            descList.add(
                WordDescription(
                    verb, wordEntity.txtOrig, wordEntity.translationsOfVerb
                )
            )
        }
    }

    private fun addTranslationsOfAdverb(
        descList: MutableList<WordDescription>,
        wordEntity: WordEntity,
    ) {
        if (wordEntity.translationsOfAdverb != EmptyField) {
            descList.add(
                WordDescription(
                    adverb, wordEntity.txtOrig, wordEntity.translationsOfAdverb
                )
            )
        }
    }

    private fun addTranslationsOfPreposition(
        descList: MutableList<WordDescription>,
        wordEntity: WordEntity,
    ) {
        if (wordEntity.translationsOfPreposition != EmptyField) {
            descList.add(
                WordDescription(
                    preposition, wordEntity.txtOrig, wordEntity.translationsOfPreposition
                )
            )
        }
    }

    private fun addTranslationsOfConjunction(
        descList: MutableList<WordDescription>,
        wordEntity: WordEntity,
    ) {
        if (wordEntity.translationsOfConjunction != EmptyField) {
            descList.add(
                WordDescription(
                    conjunction, wordEntity.txtOrig, wordEntity.translationsOfConjunction
                )
            )
        }
    }

    private fun addTranslationsOfInterjection(
        descList: MutableList<WordDescription>,
        wordEntity: WordEntity,
    ) {
        if (wordEntity.translationsOfInterjection != EmptyField) {
            descList.add(
                WordDescription(
                    interjection, wordEntity.txtOrig, wordEntity.translationsOfInterjection
                )
            )
        }
    }

    private fun addTranslationsOfNumeral(
        descList: MutableList<WordDescription>,
        wordEntity: WordEntity,
    ) {
        if (wordEntity.translationsOfNumeral != EmptyField) {
            descList.add(
                WordDescription(
                    numeral, wordEntity.txtOrig, wordEntity.translationsOfNumeral
                )
            )
        }
    }

    private fun addTranslationsOfParticle(
        descList: MutableList<WordDescription>,
        wordEntity: WordEntity,
    ) {
        if (wordEntity.translationsOfParticle != EmptyField) {
            descList.add(
                WordDescription(
                    particle, wordEntity.txtOrig, wordEntity.translationsOfParticle
                )
            )
        }
    }

    private fun addTranslationsOfAdverbialParticiple(
        descList: MutableList<WordDescription>,
        wordEntity: WordEntity,
    ) {
        if (wordEntity.translationsOfAdverbialParticiple != EmptyField) {
            descList.add(
                WordDescription(
                    adverbial_participle,
                    wordEntity.txtOrig,
                    wordEntity.translationsOfAdverbialParticiple
                )
            )
        }
    }


}