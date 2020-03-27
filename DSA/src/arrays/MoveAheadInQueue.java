package arrays;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class MoveAheadInQueue {

	public static void main(String[] args) {
		int[] q = new int[] {1, 2, 5, 3, 7, 8, 6, 4};
		minimumBribes(q);
	}
	
	static void minimumBribes(int[] q) {
        int swaps = 0;
        boolean chaotic = false;
        int[] swap = new int[q.length];
        for(int i=0;i<q.length;i++){
            if(q[i] != (i+1)){
                if(q[i] > i){
                    int ct = q[i] - (i + 1); // on the queue numering starts from 1
                    if(ct>2){
                        System.out.println("Too chaotic");
                        chaotic = true;
                        break;
                    }else{
                        swaps+= ct;
                    }
                    swap[i] = ct;
                }else{
                    swap[i] = q[i] - (i+1);
                }
            }
        }

        for(int i=0;i<q.length;i++){
            int greaterThanCurrent = 0;
            System.out.println(swap[i]);
            for(int j=0;j<i;j++){
                if(q[j]>q[i]){
                    greaterThanCurrent++;
                }
            }
            if(swap[i]<0 && greaterThanCurrent > Math.abs(swap[i])){
                swaps += greaterThanCurrent - Math.abs(swap[i]);
            }
        }

        if(!chaotic)
            System.out.println(swaps);

    }

}



