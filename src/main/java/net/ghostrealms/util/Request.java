package net.ghostrealms.util;

public enum Request {
  
  TOWN_JOIN(1),
  TOWN_ADD(2),
  FRIEND(3);
  
  private final int val;
  
  Request(int val) {
    this.val = val;
  }
  
  public int getType() {
    return val;
  }
}
