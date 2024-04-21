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
	  if(this.first > 8) {
		  this.first = this.first % dimension;
	  }
	  if(this.second > dimension) {
		  this.second = this.second % dimension;
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