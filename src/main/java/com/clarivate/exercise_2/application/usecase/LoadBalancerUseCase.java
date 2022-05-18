package com.clarivate.exercise_2.application.usecase;

import java.util.List;

public interface LoadBalancerUseCase {

  boolean requestsCanBeBalanced(List<Integer> requests);

}
