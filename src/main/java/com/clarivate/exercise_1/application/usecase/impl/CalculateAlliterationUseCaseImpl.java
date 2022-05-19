package com.clarivate.exercise_1.application.usecase.impl;

import com.clarivate.exercise_1.application.exceptions.NoInitialConsonantFoundException;
import com.clarivate.exercise_1.application.usecase.CalculateAlliterationUseCase;
import com.clarivate.shared.CharUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CalculateAlliterationUseCaseImpl implements CalculateAlliterationUseCase {

  private final String WHITESPACE_CHAR = "\\s+";

  @Override
  public double calculatePercentage(String phrase) throws NoInitialConsonantFoundException {

    List<String> phraseWords = List.of(phrase.split(WHITESPACE_CHAR));
    char countableConsonant = this.getCountableConsonant(phrase);
    long totalAlliterationWords = phraseWords.stream().filter(s -> s.toLowerCase().startsWith(String.valueOf(countableConsonant))).count();

    double percentage = (double) totalAlliterationWords / (double) phraseWords.size();

    return BigDecimal.valueOf(percentage).setScale(2, RoundingMode.HALF_UP).doubleValue();
  }

  @Override
  public char getCountableConsonant(String phrase) throws NoInitialConsonantFoundException {

    List<String> phraseWords = List.of(phrase.split(WHITESPACE_CHAR));
    Map<String, Integer> firstLetters = new HashMap<>();

    for (String word : phraseWords) {
      String firstChar = String.valueOf(word.charAt(0)).toLowerCase();

      // If the first letter of the word is a vowel, skip from iteration
      if (CharUtils.isVowel(word.charAt(0))) {
        continue;
      }

      // Add consonant to letters map
      if (!firstLetters.containsKey(firstChar)) {
        firstLetters.put(firstChar, 1);
      } else {
        firstLetters.put(firstChar, firstLetters.get(firstChar) + 1);
      }
    }

    // Return highest occurrence consonant or throw exception if none found
    return firstLetters.entrySet().stream().max(Map.Entry.comparingByValue()).orElseThrow(() -> new NoInitialConsonantFoundException(phrase)).getKey().charAt(0);
  }


}
