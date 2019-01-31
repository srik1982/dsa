package divideandconquer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ClosestPoints {
	
	SorterForXAxis xAxisSorter = new SorterForXAxis();
	SorterForYAxis yAxisSorter = new SorterForYAxis();
	public static void main(String[] args) {
		ClosestPoints obj = new ClosestPoints();
		List<Point> points = new ArrayList<ClosestPoints.Point>();
		
		Point p = obj.new Point(1,4);
		points.add(p);
		
		p = obj.new Point(2,3);
		points.add(p);
		
		p = obj.new Point(9,12);
		points.add(p);
		
		p = obj.new Point(3,5);
		points.add(p);
		
		p = obj.new Point(4,12);
		points.add(p);
		
		p = obj.new Point(6,3);
		points.add(p);
		
		p = obj.new Point(5,1);
		points.add(p);
		
		p = obj.new Point(4,4);
		points.add(p);
		
		p = obj.new Point(7,8);
		points.add(p);
		
		p = obj.new Point(8,3);
		points.add(p);
		
		List<Point> pointsByX = new ArrayList<ClosestPoints.Point>(points);
		Collections.sort(pointsByX,obj.xAxisSorter);
		List<Point> pointsByY = new ArrayList<ClosestPoints.Point>(points);
		Collections.sort(pointsByY,obj.yAxisSorter);
		
		Pair pair = obj.findClosestPair(pointsByX, pointsByY);
		System.out.println(pair);
	}
	
	class Point{
		double x;
		double y;
		
		Point(double x,double y){
			this.x = x;
			this.y = y;
		}
		
		public String toString() {
			return "[ x= "+x+" , y= "+y+" ]";
		}
	}
	
	class Pair{
		Point p1;
		Point p2;
		double distance;
		
		Pair(Point p1, Point p2, double distance){
			this.p1 = p1;
			this.p2 = p2;
			this.distance = distance;
		}
		
		public String toString() {
			return p1.toString()+" <-> "+p2.toString()+" = "+distance;
		}
	}
	
	class SorterForXAxis implements Comparator<Point>{

		@Override
		public int compare(Point o1, Point o2) {
			// TODO Auto-generated method stub
			return ((Double)o1.x).compareTo(o2.x);
		}
		
	}
	
	class SorterForYAxis implements Comparator<Point>{

		@Override
		public int compare(Point o1, Point o2) {
			// TODO Auto-generated method stub
			return ((Double)o1.y).compareTo(o2.y);
		}
		
	}
	
	private double computeDistance(Point p1, Point p2) {
		double distanceX = Math.abs(p1.x - p2.x);
		double distanceY = Math.abs(p1.y - p2.y);
		double d = Math.hypot(distanceX, distanceY);
		System.out.println("Distance between "+p1+" and "+p2+" = "+d);
		return d;
	}
	
	/**
	   * Time Complexity
	   * Time Complexity Let Time complexity of above algorithm be T(n). 
	   * Let us assume that we use a O(nLogn) sorting algorithm. 
	   * The above algorithm divides all points in two sets and recursively calls for two sets.
	   *  It sorts the list by yaxis in O(nLogn)
	   *  After dividing, it finds the strip in O(n) time, and finally finds the closest points in strip in O(n) time.
	   *  So T(n) can expressed as follows
	   *   T(n) = 2T(n/2) + O(nlogn) + O(n) + O(n)
	   *  T(n) = 2T(n/2) + O(nlogn)
	   * 
	   *   T(n) = aT(n/b) + 0(n^k log^p+1 n )
	   *   a = 2, b = 2, k=1, p=1
	   *   Applying case (2a) T(n) = 0(n^ log a base b * log ^p+1 n)
	   *   => T(n) = 0(n*log^2 n) => T(n) = 0(n log square n)
	   */
	public Pair findClosestPair(List<Point> pointsByX, List<Point> pointsByY) {
		if(pointsByX.size()<=3) {
			return findClosestPairBruteForce(pointsByX);
		}
		
		int mid = pointsByX.size() >>> 1;
		
		List<Point> left = pointsByX.subList(0, mid);
		List<Point> right = pointsByX.subList(mid, pointsByX.size());
		
		List<Point> temp = new ArrayList<Point>(left);
		Collections.sort(temp,yAxisSorter);
		
		Pair leftPair = findClosestPair(left,temp);
		
		temp = new ArrayList<Point>(right);
		Collections.sort(temp, yAxisSorter);
		
		Pair rightPair = findClosestPair(right,temp);
		
		Pair closestPair = leftPair.distance < rightPair.distance ? leftPair : rightPair;
		double shortestDistance = closestPair.distance;
		
		//Now lets find the middle strip
		//since we are taking point sorted by Y axis and finding x axis distance alone here, we find all those points at a distance of < shortestDistance
		// from the closest to center
		// of course we are not taking hypotinuse but only x distance here. So this just a shorlisting. No guarantee that all of them have shorter distances
		
		Point closestToCenter = right.get(0);// alternatively we can choose the last element of left i guess. we shall try that later.
		List<Point> strip = new ArrayList<ClosestPoints.Point>();
		for(Point p : pointsByY) {
			if(Math.abs(p.x - closestToCenter.x) <= shortestDistance) {
				strip.add(p);
			}
		}
		
		for(int i=0;i<strip.size()-1;i++) {
			Point p1 = strip.get(i);
			for(int j=i+1;j<strip.size();j++) {
				Point p2 = strip.get(j);
				
				if(p1.y - p2.y >= shortestDistance)break; // if distance in y itself is > shortest distance, no need to compute hypotenuse
				
				double d = computeDistance(p1,p2);
				if(d < shortestDistance) {
					closestPair.p1 = p1;
					closestPair.p2 = p2;
					closestPair.distance = d;
				}
			}
		}
		
		return closestPair;
	}

	
	public Pair findClosestPairBruteForce(List<Point> points) {
		double distance = Double.POSITIVE_INFINITY;
		Pair pair = null;
		
		for(int i=0;i<points.size()-1;i++) {
			Point p1 = points.get(i);
			for(int j=i+1;j<points.size();j++) {
				Point p2 = points.get(j);
				
				double d = computeDistance(p1,p2);
				if(d < distance) {
					distance = d;
					pair = new Pair(p1,p2,distance);
				}
			}
		}
		return pair;
	}

}
