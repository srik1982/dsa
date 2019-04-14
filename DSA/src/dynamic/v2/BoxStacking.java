package dynamic.v2;

import java.util.Arrays;

public class BoxStacking {
	
	 public static void main(String args[]) {
	        BoxStacking bs = new BoxStacking();
	        Box input[] = { bs.new Box(3, 2, 5), bs.new Box(1, 2, 4) };
	        int maxHeight = bs.findMaxHeight(input);
	        System.out.println("Max height is " + maxHeight);
	        assert 11 == maxHeight;
	    }

	class Box implements Comparable<Box>{
		int l;
		int w;
		int h;
		int area;
		
		Box(int l, int w, int h){
			this.l = l;
			this.w = w;
			this.h = h;
			
			area = l * w;
		}

		@Override
		public int compareTo(Box o) {
			return o.area > this.area ? 1 : -1;
		}
		
		public boolean canHostOnTop(Box o) {
			return this.l >= o.l && this.w >= o.w;
		}
		
		public String toString() {
			return "[ length="+l+" ,width="+w+" ,height="+h+" ]";
		}
	}
	
	public int findMaxHeight(Box[] boxes) {
		int n = boxes.length;
		Box[] rotate = new Box[3*n];
		System.out.println(Arrays.toString(boxes));
		//populate the rotate array
		for(int i=0;i<n;i++) {
			rotate[3*i] = new Box(Math.max(boxes[i].l, boxes[i].w), Math.min(boxes[i].l, boxes[i].w), boxes[i].h);
			rotate[3*i+1] = new Box(Math.max(boxes[i].w, boxes[i].h), Math.min(boxes[i].w, boxes[i].h), boxes[i].l);
			rotate[3*i+2] = new Box(Math.max(boxes[i].h, boxes[i].l), Math.min(boxes[i].h, boxes[i].l), boxes[i].w);
		}
		System.out.println(Arrays.toString(rotate));
		
		Arrays.sort(rotate);
		System.out.println("After Sorting");
		System.out.println(Arrays.toString(rotate));
		
		
		int[] max = new int[rotate.length];
		int[] result = new int[rotate.length];
		
		for(int i=0;i<max.length;i++) {
			max[i] = rotate[i].h;
			result[i] = -1;
		}
		
		for(int i=1;i<rotate.length;i++) {
			for(int j=0;j<i;j++) {
				Box prev = rotate[j];
				Box curr = rotate[i];
				if(prev.canHostOnTop(curr)) {
					if(max[j] + curr.h > max[i]) {
						max[i] = max[j] + curr.h;
						result[i] = j;
					}
				}
			}
		}
		
		System.out.println("Max array");
		System.out.println(Arrays.toString(max));
		System.out.println("result array");
		System.out.println(Arrays.toString(result));
		
		int maxHeight = 0;
		int maxIndex = -1;
		for(int i=0;i<max.length;i++) {
			if(max[i] > maxHeight) {
				maxHeight = max[i];
				maxIndex = i;
			}
		}
		
		int i = maxIndex;
		while(i>=0) {
			System.out.println("Val : "+rotate[i]);
			i = result[i];
		}
		
		return maxHeight;
	}
}
