package jp.co.wap.exam;

import java.util.List;
import jp.co.wap.exam.lib.Interval;

public class Problem1 {
	public int getMaxIntervalOverlapCount(List<Interval> intervals) {
        if (intervals==null || intervals.isEmpty())     return 0;
		
		//number of minutes in 24 hours...
		int TOTAL=24*60+1;
		
		//initially all these are set to zero.
        int[] begin=new int[TOTAL];
        int[] end=new int[TOTAL];
		
		
        for(Interval list : intervals){
			begin[list.getBeginMinuteUnit()]++;
            end[list.getEndMinuteUnit()]++;
        }
        begin[TOTAL-1]+=begin[0];
	
        int max=0;
        int count=0;
        for (int i=0;i<TOTAL;i++){
            count+=begin[i];
            if (count>max)    max=count;
            count-=end[i];
		}
        return max;
    }
}
