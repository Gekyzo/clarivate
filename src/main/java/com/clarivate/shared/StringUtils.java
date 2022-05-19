package com.clarivate.shared;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StringUtils {

  private static final String WHITESPACE_CHAR = "\\s+";

  public static List<String> splitByWhitespace(String phrase) {

    return List.of(phrase.split(WHITESPACE_CHAR));
  }

  public static long countWordsBeginWith(List<String> words, char character) {

    return words.stream().filter(s -> s.toLowerCase().startsWith(String.valueOf(character))).count();
  }

}
