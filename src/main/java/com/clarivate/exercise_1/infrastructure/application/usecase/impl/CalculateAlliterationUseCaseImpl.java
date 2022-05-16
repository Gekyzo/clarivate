package com.clarivate.exercise_1.infrastructure.application.usecase.impl;

import com.clarivate.exercise_1.infrastructure.application.usecase.CalculateAlliterationUseCase;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
public class CalculateAlliterationUseCaseImpl implements CalculateAlliterationUseCase {

  private final String WHITESPACE_CHAR = "\\s+";

  @Override
  public double calculatePercentage(String phrase) {

    List<String> inputWordsList = this.splitPhrase(phrase);
    String countableConsonant = String.valueOf(this.getCountableConsonant(inputWordsList.get(0)));
    long totalAlliterationWords = inputWordsList.stream().filter(s -> s.toLowerCase().startsWith(countableConsonant)).count();

    double percentage = (double) totalAlliterationWords / (double) inputWordsList.size();

    return BigDecimal.valueOf(percentage).setScale(2, RoundingMode.HALF_UP).doubleValue();
  }

  // TODO: improve split
  private List<String> splitPhrase(String phrase) {

    return List.of(phrase.split(WHITESPACE_CHAR));
  }

  // TODO: obtain the REAL countable consonant of all the possible words
  private char getCountableConsonant(String word) {

    return Character.toLowerCase(word.charAt(0));
  }


}
