package com.clarivate.exercise_2.application.usecase.impl;

import com.clarivate.exercise_2.application.usecase.LoadBalancerUseCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class LoadBalancerUseCaseTest {

  @Autowired
  private LoadBalancerUseCase useCase;

  /**
   * List of datasets to perform the tests.
   * <p>Each dataset is made up of the input list and the expected output value for that list.</p>
   *
   * @return Stream<Arguments>
   */
  private static Stream<Arguments> sampleRequestList() {

    return Stream.of(
        // Should return true lists
        Arguments.of(List.of(1, 3, 4, 2, 2, 2, 1, 1, 2), true),
        Arguments.of(List.of(2, 1, 1, 2, 2, 2, 4, 3, 1), true),
        Arguments.of(List.of(1, 1, 1, 1, 1, 1, 1, 1), true),
        Arguments.of(List.of(0, 0, 1, 0, 0, 1, 0, 0), true),

        // Should return false lists
        Arguments.of(List.of(1, 1, 1), false),
        Arguments.of(List.of(1, 1, 1, 1, 1, 1), false)
    );
  }

  @ParameterizedTest
  @MethodSource("sampleRequestList")
  @DisplayName("Request can be balanced or not")
  void check_requests_can_be_balanced(List<Integer> requests, boolean expectedResult) {

    assertEquals(expectedResult, useCase.requestsCanBeBalanced(requests));
  }

}