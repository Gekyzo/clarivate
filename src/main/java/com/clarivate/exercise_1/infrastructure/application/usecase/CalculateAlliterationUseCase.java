package com.clarivate.exercise_1.infrastructure.application.usecase;

import com.clarivate.exercise_1.infrastructure.application.exceptions.NoConsonantsFoundException;

public interface CalculateAlliterationUseCase {

  double calculatePercentage(String phrase) throws NoConsonantsFoundException;

  /**
   * Returns the highest occurrence initial consonant in a text.
   *
   * @param phrase The input text to obtain the countable consonant from.
   * @return The consonant letter to be counted.
   * @throws NoConsonantsFoundException When there is no consonant found as the first letter in any word of the text.
   */
  char getCountableConsonant(String phrase) throws NoConsonantsFoundException;

}
