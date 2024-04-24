package src.utils;

public class Pair {
  private int first;
  private int second;
  
  public Pair(int first, int second) {
    this.first = first;
    this.second = second;
  }
  
  public Pair() {
    this.first = 0;
    this.second = 0;
  }
  
  public Pair suma(Pair that, int dimension) {
    this.first += that.first;
    this.second += that.second;
    if (this.first >= dimension) {
      this.first %= dimension;
    }
    if (this.second >= dimension) {
      this.second %= dimension;
    }
    if (this.first < 0) {
      this.first = dimension - Math.abs(this.first) % dimension;
    }
    if (this.second < 0) {
      this.second = dimension - Math.abs(this.second) % dimension;
    }
    return this;
  }

  public int getFirst() {
    return first;
  }
  
  public int getSecond() {
    return second;
  }
  
  public void setFirst(int first) {
    this.first = first;
  }
  
  public void setSecond(int second) {
    this.second = second;
  }

  public String toString() {
    return "(" + first + "," + second + ")";
  }
}