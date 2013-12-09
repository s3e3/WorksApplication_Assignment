package jp.co.wap.exam;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;


import jp.co.wap.exam.lib.Interval;


class myComparator implements Comparator<Interval>{
	public int compare(Interval interval1,Interval interval2) {
        return interval1.getEndMinuteUnit()-interval2.getEndMinuteUnit();
    }
}

public class Problem2 {
        
		 ///////////////////////////////////////////
		 //sortList - stores the sorted list in increasing order of getEndMinuteUnit().
		 //indices  - stores the index of an interval which is nearest to the given interval such that BeginMinuteUnit() of given interval is
		 //           greater than EndMinuteUnit() of other interval.
		 //maxTime  - stores the maximum value of working time corresponding to the given interval.
		 ///////////////////////////////////////////
        public int getMaxWorkingTime(List<Interval> intervals) {
                if(null==intervals || intervals.size()<1)   return 0;
				
                if(intervals.size()==1)    return intervals.get(0).getIntervalMinute();
                
                ArrayList<Interval> sortList = new ArrayList<Interval>(intervals);
                Collections.sort(sortList,new myComparator());
				
                int[] indices=getIndices(sortList);
                
				for(int i=0;i<indices.length;i++) System.out.print(indices[i]+" ");
				int[] maxTime=new int[sortList.size()+1];
            
				for(int i=1;i<=sortList.size();i++){
					maxTime[i]=Math.max(maxTime[i-1],maxTime[indices[i-1]]+sortList.get(i-1).getIntervalMinute());
				}
				return maxTime[sortList.size()];
        }

		 //Returns and array of indices.
        private int[] getIndices(List<Interval> intervals) {
            int[] result = new int[intervals.size()];
                for (int i=0;i<intervals.size();i++){
					Interval current=intervals.get(i);
                    result[i]=getNearestIndex(intervals,current.getBeginMinuteUnit());
				}
                
			return result;
        }
		
		private int getNearestIndex(List<Interval> intervals,int beginMinUnit){
			System.out.println("begin"+beginMinUnit);
            int i=intervals.size()-1;
			int j=0;
			int k;
            while(i>=j){
                k=(j+i)/2;
                if(intervals.get(k).getEndMinuteUnit()<beginMinUnit)       j=k+1;
                else   i=k-1;
			}
			
            return i+1;
			
        }
		
}
