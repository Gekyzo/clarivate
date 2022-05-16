package com.clarivate.exercise_1.infrastructure.application.exceptions;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.text.MessageFormat;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class NoConsonantsFoundException extends Exception {

  public NoConsonantsFoundException(String phrase) {

    super(MessageFormat.format("The phrase \"{0}\" doesn''t contain any first consonant in any word", phrase));

  }

}
