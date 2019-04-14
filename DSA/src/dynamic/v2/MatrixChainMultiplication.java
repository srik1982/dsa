package dynamic.v2;

import java.util.ArrayList;
import java.util.List;

/**
 * Multiply matrices m1, m2, m3,.. mn
 * Let T(i,j) indicate the multiplication of matrices mi * mi+1 * .. mj
 * T(i,j) =  { 0 if i==j because there is only 1 matrix and no multiplication
 *           { mi * mj i.e., mix * miy * Miy  if i+1 = j  we are multiplying 2 matrices
 *           { Min ( T(i,j-1) + m(i->j-1) * mj , mi + T(i+1,j) otherwise 
 *
 */
public class MatrixChainMultiplication {

	public static void main(String[] args) {
		MatrixChainMultiplication obj = new MatrixChainMultiplication();
		List<Matrix> matrices = new ArrayList<>();
		
		Matrix m = obj.new Matrix(2,3);
		matrices.add(m);

		m = obj.new Matrix(3,6);
		matrices.add(m);
		
		m = obj.new Matrix(6,4);
		matrices.add(m);
		
		m = obj.new Matrix(4,5);
		matrices.add(m);
		
		Matrix result = obj.chainMultiply(matrices);
		System.out.println(result);
	}
		
	class Matrix{
		int x;
		int y;
		int result = 0;
		StringBuilder components = new StringBuilder();
		
		Matrix(int x, int y){
			this.x = x;
			this.y = y;
		}
		
		Matrix(int x, int y, int result){
			this(x,y);
			this.result = result;
		}
		
		void addComponents(String m1, String m2) {
			
			components.append(" { ").append(m1).append(" , ");
			components.append(m2).append(" } ");
		}
		
		
		
		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append("<<< (").append(x).append(" , ");
			sb.append(y).append(" ) => ").append(result);
			sb.append('\n');
			sb.append(components!=null ? components : "");
			sb.append(" >>>");
			return sb.toString();
			
		}
	}
	
	
	public Matrix chainMultiply(List<Matrix> matrices) {
		Matrix[][] table = new Matrix[matrices.size()][matrices.size()];
		
		for(int count=0;count<matrices.size();count++) {
			for(int j=0;j<matrices.size();j++) {
				for(int k=0;k<matrices.size();k++) {
					if(k-j == count) {
						if(count == 0) {
							table[j][k] = matrices.get(count);
						}else if(count == 1){
							Matrix left = matrices.get(j);
							Matrix right = matrices.get(k);
							Matrix result = new Matrix(left.x, right.y, left.x * left.y * right.y);
							table[j][k] = result;
							result.addComponents(left.toString(), right.toString());
						}else {
							Matrix leftNeighbor = table[j][k-1];
							Matrix bottomNeighbor = table[j+1][k];
							
							Matrix left = matrices.get(j);
							Matrix right = matrices.get(k);
							
							int r1 = leftNeighbor.result + leftNeighbor.x * leftNeighbor.y * right.y;
							int r2 = left.x * left.y * bottomNeighbor.y + bottomNeighbor.result;
							
							if(r1 < r2) {
								Matrix result = new Matrix(leftNeighbor.x, right.y, r1);
								result.addComponents(leftNeighbor.components.toString(), right.toString());
								table[j][k] = result;
							}else {
								Matrix result = new Matrix(left.x, bottomNeighbor.y, r2);
								result.addComponents(left.toString(),bottomNeighbor.components.toString());
								table[j][k] = result;
							}
						}
					}
				}
			}
		}
		return table[0][matrices.size()-1];
		
	}
}
