package edu.itpu.queries;

import static edu.itpu.queries.Utils.print;

public class Main {
  
  public static void main(String[] args) {
    var data = new Data();
    QueryResult result = data.firstN(4);
    System.out.println(result.getClass());
    print(result);
  }
}
