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
    int maxSublistSum = BigDecimal.valueOf(requestsSum / NUM_WORKERS).setScale(0, RoundingMode.DOWN).intValue();

    return this.requestsCanBeBalancedForMaximum(requests, maxSublistSum);
  }

  private boolean requestsCanBeBalancedForMaximum(List<Integer> requests, int maxSublistSum) {

    int sum1 = 0;
    int sum2 = 0;
    int sum3 = 0;

    List<Integer> list1 = new ArrayList<>();
    List<Integer> list2 = new ArrayList<>();
    List<Integer> list3 = new ArrayList<>();

    for (int i = 0; i < requests.size(); i++) {
      if (sum1 + requests.get(i) <= maxSublistSum) {
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

    if (this.checkShouldDoRecursiveCall(list1, list2)) {
      int newMaxSublistSum = calculateNewMaxSublistSum(list1);
      return this.requestsCanBeBalancedForMaximum(requests, newMaxSublistSum);
    }

    for (int i = list1.size() + list2.size() + 2; i < requests.size(); i++) {
      if (sum3 + requests.get(i) <= sum1) {
        sum3 += requests.get(i);
        list3.add(requests.get(i));
      } else {
        break;
      }
    }

    if (this.checkShouldDoRecursiveCall(list1, list3)) {
      int newMaxSublistSum = calculateNewMaxSublistSum(list1);
      return this.requestsCanBeBalancedForMaximum(requests, newMaxSublistSum);
    }

    boolean listsSizeMatch = requests.size() == list1.size() + list2.size() + list3.size() + NUM_DROPPED;
    boolean listsTimeMatch = Stream.of(sum1, sum2, sum3).distinct().count() == 1;

    return listsSizeMatch && listsTimeMatch;

  }

  private boolean checkShouldDoRecursiveCall(List<Integer> listA, List<Integer> listB) {

    return !Objects.equals(listA.stream().mapToInt(Integer::intValue).sum(), listB.stream().mapToInt(Integer::intValue).sum());
  }

  /**
   * Calculates the new value of the maximum sublist total.
   *
   * @param sublist The sublist obtained from the original request list.
   * @return int The new maxSublistSum value
   */
  private int calculateNewMaxSublistSum(List<Integer> sublist) {

    // Remove last element from the list
    sublist.remove(sublist.size() - 1);

    // Subtracts the last element value from the total of the list
    return sublist.stream().mapToInt(Integer::intValue).sum();
  }

}
