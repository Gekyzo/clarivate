package com.clarivate.exercise_1.application.usecase.impl;

import com.clarivate.exercise_1.application.exceptions.NoInitialConsonantFoundException;
import com.clarivate.exercise_1.application.usecase.CalculateAlliterationUseCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.MessageFormat;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class CalculateAlliterationUseCaseTest {

  @Autowired
  private CalculateAlliterationUseCase useCase;

  /**
   * List of datasets to perform the tests.
   * <p>Each dataset is made up of the input phrase and the expected alliteration percentage for that phrase.</p>
   *
   * @return Stream
   */
  private static Stream<Arguments> listOfPhrasesWithAlliterationValue() {

    return Stream.of(
        Arguments.of("Mike made mellow music with his new microphone.", 0.63d),
        Arguments.of("Yarvis yanked his ankle at yoga, and Yolanda yelled out in surprise.", 0.42d)
    );
  }

  /**
   * List of datasets to perform the tests.
   * <p>Each dataset is made up of the input phrase and the expected principal consonant.</p>
   *
   * @return Stream
   */
  private static Stream<Arguments> listOfTextsWithConsonant() {

    return Stream.of(
        Arguments.of("Mike made mellow music with his new microphone.", 'm'),
        Arguments.of("Yarvis yanked his ankle at yoga, and Yolanda yelled out in surprise.", 'y')
    );
  }

  /**
   * List of datasets to perform the tests.
   * <p>Each dataset is made up of input phrases without any word beginning in consonant.</p>
   *
   * @return Stream
   */
  private static Stream<Arguments> listOfTextsWithoutConsonant() {

    return Stream.of(
        Arguments.of("ankle at and out in entry untitled.")
    );
  }

  @ParameterizedTest
  @MethodSource("listOfPhrasesWithAlliterationValue")
  @DisplayName("Method returns correct alliteration percentage")
  void method_returns_correct_alliteration_percentage(String phrase, double expectedValue) throws NoInitialConsonantFoundException {

    assertEquals(expectedValue, useCase.calculatePercentage(phrase));
  }

  @ParameterizedTest
  @MethodSource("listOfTextsWithConsonant")
  @DisplayName("Method returns correct countable consonant")
  void method_returns_correct_countable_consonant(String phrase, char expectedChar) throws NoInitialConsonantFoundException {

    assertEquals(expectedChar, useCase.getCountableConsonant(phrase));
  }

  @ParameterizedTest
  @MethodSource("listOfTextsWithoutConsonant")
  @DisplayName("Method throws expected exception when no word begins with consonant")
  void method_throws_expected_exception(String phrase) {

    String expectedExceptionText = MessageFormat.format("The phrase \"{0}\" doesn''t contain any word that begins with consonant", phrase);

    Exception ex = assertThrows(NoInitialConsonantFoundException.class, () -> useCase.getCountableConsonant(phrase));
    assertEquals(expectedExceptionText, ex.getMessage());
  }

}