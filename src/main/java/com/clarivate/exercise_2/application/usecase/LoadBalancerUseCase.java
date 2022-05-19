package com.clarivate.exercise_2.application.usecase;

import java.util.List;

public interface LoadBalancerUseCase {

  /**
   * Checks if a list can be balanced according to the established rules.
   *
   * @param requests The list of requests to be balanced.
   * @return true if the requests can be balanced. False otherwise.
   */
  boolean requestsCanBeBalanced(List<Integer> requests);

}
