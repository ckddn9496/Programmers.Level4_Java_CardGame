import java.util.Arrays;

public class Main {

	public static void main(String[] args) {
		int[] left = {3,2,5};
		int[] right = {2,4,1}; // return 7
		
//		int[] left = {1,1,9,1,1,1,1};
//		int[] right = {8,9,1,1,1,1,1}; // return 8

//		int[] left = {1,1,9,1,1};
//		int[] right = {8,9,2,2,2};	//	return 8

		System.out.println(new Solution().solution(left, right));
	}

}

class Solution {

	public int solution(int[] left, int[] right) {
    	int score = 0;
        int len = left.length;
        int[][] cardMap = new int[len+1][len+1];
        
        int leftMax = Arrays.stream(left).max().getAsInt();
        int rightMax = Arrays.stream(right).max().getAsInt();
        if (leftMax > rightMax) return Arrays.stream(right).sum();
        
        for (int i = 0; i <= len; i++) {
        	for (int j = 0; j <= len; j++) {
        		cardMap[i][j] = -1;
        	}
        }
        cardMap[0][0] = 0;
        
        for (int i = 0; i < len; i++) {
        	for (int j = 0; j < len; j++) {
        		int value = cardMap[i][j];
        		if (value == -1) {
        			continue;
        		}
        		// left > right일때 오른쪽 카드 버리기, right[j]만큼 점수 증가
        		if (left[i] > right[j] && cardMap[i][j+1] < value + right[j]) {
        			cardMap[i][j+1] = value + right[j];
        		}
        		// 왼쪽 오른쪽 동시에 버리기
        		if (cardMap[i+1][j+1] < value) {
        			cardMap[i+1][j+1] = value;
        		}
        		// 왼쪽 카드만 버리기
        		if (cardMap[i+1][j] < value) {
        			cardMap[i+1][j] = value;
        		}
        	}
        }
        int max = Arrays.stream(cardMap[len]).max().getAsInt();
        for (int i = 0; i <= len; i++) {
        	if (max < cardMap[i][len])
        		max = cardMap[i][len];
        }
        
//        printCardMap(left, right, cardMap);
        return max;
    }
    
    void printCardMap(int[] left, int[] right, int[][] cardMap) {
    	System.out.print("    ");
    	for (int r : right) System.out.print(String.format("%4d", r));
    	for (int i = 0; i < cardMap.length; i++) {
    		System.out.println();
    		if (i < cardMap.length-1)
    			System.out.print(String.format("%4d", left[i]));
    		else
    			System.out.print("    ");
    		for (int j = 0; j < cardMap.length; j++) {
    			System.out.print(String.format("%4d", cardMap[i][j]));
    		}
    	}
    	System.out.println();
    }
}