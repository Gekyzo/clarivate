package com.clarivate.exercise_1.application.exceptions;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.text.MessageFormat;

/**
 * Exception thrown when we try to find a initial consonant in all the words of a phrase, but none are found.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class NoInitialConsonantFoundException extends Exception {

  public NoInitialConsonantFoundException(String phrase) {

    super(MessageFormat.format("The phrase \"{0}\" doesn''t any word that begins with consonant", phrase));

  }

}
