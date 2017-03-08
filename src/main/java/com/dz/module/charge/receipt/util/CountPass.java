package com.dz.module.charge.receipt.util;

/**
 * @author doggy
 *         Created on 16-3-6.
 */
public class CountPass {
    private final int start;
    private final int end;
    private int number;
    public CountPass(int start,int end){
        this.start = start;
        this.end =  end;
        this.number = (end - start+1)/100;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
