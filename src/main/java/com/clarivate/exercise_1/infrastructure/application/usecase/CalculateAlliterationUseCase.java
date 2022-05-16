package com.clarivate.exercise_1.infrastructure.application.usecase;

import com.clarivate.exercise_1.infrastructure.application.exceptions.NoInitialConsonantFoundException;

public interface CalculateAlliterationUseCase {

  /**
   * Returns the alliteration percentage of a phrase.
   *
   * @param phrase The input phrase to calculate the alliteration percentage.
   * @return The alliteration percentage expressed as a double number.
   * @throws NoInitialConsonantFoundException When there is no consonant found as the first letter in any word of the phrase.
   */
  double calculatePercentage(String phrase) throws NoInitialConsonantFoundException;

  /**
   * Returns the highest occurrence initial consonant in a phrase.
   *
   * @param phrase The input phrase to obtain the countable consonant from.
   * @return The consonant letter to be counted.
   * @throws NoInitialConsonantFoundException When there is no consonant found as the first letter in any word of the phrase.
   */
  char getCountableConsonant(String phrase) throws NoInitialConsonantFoundException;

}
