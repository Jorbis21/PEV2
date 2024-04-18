package src.utils;

public class Pair {
  private int first;
  private int second;
  
  public Pair(int first, int second) {
    this.first = first;
    this.second = second;
  }
  
  public Pair() {
	this.first = -1;
	this.second = -1;
  }
  
  public Pair suma(Pair that, int dimension) {
	  this.first += that.first;
	  this.second += that.second;
	  if(this.first > dimension) {
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

}