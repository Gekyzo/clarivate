package com.clarivate.exercise_1.application.usecase.impl;

import com.clarivate.exercise_1.application.exceptions.NoInitialConsonantFoundException;
import com.clarivate.exercise_1.application.usecase.CalculateAlliterationUseCase;
import com.clarivate.shared.StringUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.clarivate.shared.CharUtils.isVowel;
import static com.clarivate.shared.MathUtils.asDecimal;

@Service
public class CalculateAlliterationUseCaseImpl implements CalculateAlliterationUseCase {

  @Override
  public double calculatePercentage(String phrase) throws NoInitialConsonantFoundException {

    List<String> phraseWords = StringUtils.splitByWhitespace(phrase);
    char countableConsonant = this.getCountableConsonant(phrase);
    long totalWordsBeginByConsonant = StringUtils.countWordsBeginWith(phraseWords, countableConsonant);

    double alliterationPercentage = (double) totalWordsBeginByConsonant / (double) phraseWords.size();

    return asDecimal(alliterationPercentage);
  }

  @Override
  public char getCountableConsonant(String phrase) throws NoInitialConsonantFoundException {

    List<String> phraseWords = StringUtils.splitByWhitespace(phrase);
    Map<String, Integer> firstLetters = new HashMap<>();

    for (String word : phraseWords) {
      char firstChar = word.charAt(0);

      // If the first letter of the word is a vowel, skip from iteration
      if (isVowel(firstChar)) {
        continue;
      }

      // Add consonant to letters map
      String firstCharStr = String.valueOf(firstChar);
      if (!firstLetters.containsKey(firstCharStr)) {
        firstLetters.put(firstCharStr, 1);
      } else {
        firstLetters.put(firstCharStr, firstLetters.get(firstCharStr) + 1);
      }
    }

    // Return highest occurrence consonant or throw exception if none found
    return firstLetters.entrySet().stream().max(Map.Entry.comparingByValue()).orElseThrow(() -> new NoInitialConsonantFoundException(phrase)).getKey().charAt(0);
  }


}
