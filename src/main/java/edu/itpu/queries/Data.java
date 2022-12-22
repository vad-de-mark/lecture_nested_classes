package edu.itpu.queries;

import java.util.ArrayList;
import java.util.List;

public class Data {

  private static final String FILE = "data.csv";

  private final String[][] data;

  public Data() {
    this.data = Utils.readCsvFile(FILE);
  }

  /**
   * Returns the first N elements of the data.
   * 
   * @param N how many elements must be returned
   */
  public QueryResult firstN(int N) {
    return new PageQueryResult(N, Data.this.data);
  }

  /**
   * Returns all the records which are older than specified age.
   * 
   * @param age exclusive lower border for age
   */
  public QueryResult olderThan(int age) {
    List<String[]> records = new ArrayList<>();
    for (String[] record : Data.this.data) {
      int recordAge = Integer.parseInt(record[2]);
      if (recordAge > age) records.add(record);
    }
    final String[][] result = records.toArray(new String[0][0]);
    
    return new PageQueryResult(result);
  }
  
  private static class PageQueryResult implements QueryResult {
    
    private int current = 0;
    
    private final int pageSize;
    private final String[][] data;

    private PageQueryResult(int pageSize, String[][] data) {
      this.pageSize = pageSize;
      this.data = data;
    }
    
    private PageQueryResult(String[][] data) {
      this(data.length, data); // new PageQueryResult(data.length, data);
    }

    @Override
    public String[] next() {
      return data[current++];
    }

    @Override
    public int total() {
      return Math.min(data.length, pageSize);
    }

    @Override
    public int current() {
      return current;
    }
  }
  
}
