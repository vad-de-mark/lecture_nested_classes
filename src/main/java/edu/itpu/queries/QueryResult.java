package edu.itpu.queries;

public interface QueryResult {

  /**
   * Returns the next array of the result.
   */
  String[] next();

  /**
   * Returns the total entities found by query.
   */
  int total();

  /**
   * Returns the current resulting index.
   */
  int current();
}
