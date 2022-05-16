package com.clarivate.exercise_1.infrastructure.application.usecase.impl;

import com.clarivate.exercise_1.infrastructure.application.exceptions.NoConsonantsFoundException;
import com.clarivate.exercise_1.infrastructure.application.usecase.CalculateAlliterationUseCase;
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
  public double calculatePercentage(String phrase) throws NoConsonantsFoundException {

    List<String> inputWordsList = this.splitPhrase(phrase);
    char countableConsonant = this.getCountableConsonant(phrase);
    long totalAlliterationWords = inputWordsList.stream().filter(s -> s.toLowerCase().startsWith(String.valueOf(countableConsonant))).count();

    double percentage = (double) totalAlliterationWords / (double) inputWordsList.size();

    return BigDecimal.valueOf(percentage).setScale(2, RoundingMode.HALF_UP).doubleValue();
  }

  // TODO: improve split
  private List<String> splitPhrase(String phrase) {

    return List.of(phrase.split(WHITESPACE_CHAR));
  }


  @Override
  public char getCountableConsonant(String phrase) throws NoConsonantsFoundException {

    List<String> phraseWords = this.splitPhrase(phrase);
    Map<String, Integer> firstLetters = new HashMap<>();
    String[] vowels = new String[]{"a", "e", "i", "o", "u"};

    for (String word : phraseWords) {
      String firstChar = String.valueOf(word.charAt(0)).toLowerCase();

      // Check if letter is consonant
      if (List.of(vowels).contains(firstChar)) {
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
    return firstLetters.entrySet().stream().max(Map.Entry.comparingByValue()).orElseThrow(() -> new NoConsonantsFoundException(phrase)).getKey().charAt(0);
  }


}
