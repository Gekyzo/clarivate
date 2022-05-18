package com.clarivate.exercise_2.application.usecase.impl;

import com.clarivate.exercise_2.application.usecase.LoadBalancerUseCase;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@Service
public class LoadBalancerUseCaseImpl implements LoadBalancerUseCase {

  /**
   * Number of workers/groups of requests.
   */
  private static final int NUM_WORKERS = 3;

  /**
   * Number of requests dropped/avoided by the workers.
   */
  private static final int NUM_DROPPED = 2;

  @Override
  public boolean requestsCanBeBalanced(List<Integer> requests) {

    // Guard clause to avoid testing too short lists
    if (requests.size() <= NUM_WORKERS + NUM_DROPPED) {
      return false;
    }

    int requestsSum = requests.stream().mapToInt(Integer::intValue).sum();
    int maxSubsetSum = BigDecimal.valueOf(requestsSum / NUM_WORKERS).setScale(0, RoundingMode.DOWN).intValue();

    return this.requestsCanBeBalancedForMaximum(requests, maxSubsetSum);
  }

  private boolean requestsCanBeBalancedForMaximum(List<Integer> requests, int maxSubsetSum) {

    int sum1 = 0;
    int sum2 = 0;
    int sum3 = 0;

    List<Integer> list1 = new ArrayList<>();
    List<Integer> list2 = new ArrayList<>();
    List<Integer> list3 = new ArrayList<>();

    for (int i = 0; i < requests.size(); i++) {
      if (sum1 + requests.get(i) <= maxSubsetSum) {
        sum1 += requests.get(i);
        list1.add(requests.get(i));
      } else {
        break;
      }
    }

    for (int i = list1.size() + 1; i < requests.size(); i++) {
      if (sum2 + requests.get(i) <= sum1) {
        sum2 += requests.get(i);
        list2.add(requests.get(i));
      } else {
        break;
      }
    }

    if (!Objects.equals(list1.stream().mapToInt(Integer::intValue).sum(), list2.stream().mapToInt(Integer::intValue).sum())) {
      int index = list1.size() - 1;
      list1.remove(index);
      int newMaxSubsetSum = list1.stream().mapToInt(Integer::intValue).sum();
      if (this.requestsCanBeBalancedForMaximum(requests, newMaxSubsetSum))
        return true;
    }

    for (int i = list1.size() + list2.size() + 2; i < requests.size(); i++) {
      if (sum3 + requests.get(i) <= sum1) {
        sum3 += requests.get(i);
        list3.add(requests.get(i));
      } else {
        break;
      }
    }

    if (!Objects.equals(list1.stream().mapToInt(Integer::intValue).sum(), list3.stream().mapToInt(Integer::intValue).sum())) {
      int index = list1.size() - 1;
      list1.remove(index);
      int newMaxSubsetSum = list1.stream().mapToInt(Integer::intValue).sum();
      if (this.requestsCanBeBalancedForMaximum(requests, newMaxSubsetSum))
        return true;
    }

    boolean listsSizeMatch = requests.size() == list1.size() + list2.size() + list3.size() + NUM_DROPPED;
    boolean listsTimeMatch = Stream.of(sum1, sum2, sum3).distinct().count() == 1;

    return listsSizeMatch && listsTimeMatch;

  }

}
