import java.util.Scanner;

public class BORW {
	
	static Scanner in = new Scanner(System.in);
	
	static int n;
	
	static int a[];
	
	static int dp[][][];
	
	/*
	*
	* we can solve it using 3 state dp.
	* we mainly use bottom up approach to solve
	  this. 
	* state 1 mention that the index of the array a.
	* state 2 mention that the index of the 
	  increasing sequence.
	* state 3 mention that the index of the decreasing
      element index.
    * and their value mention that the number of 
      step to need to gain that state. 	
	* if the value is increasing condition only 
	  then
	  we call the state only by increasing that
	  count and other is the skip that part. we 
      have to collect the minimum among this two
      state. 	  
	* this is same for the decreasing state.
    * other wise we consider the skip state. or the
      increasing state of the count variable. 	
	* and the complexity of the problem is O(n^3).
	* the space complexity of the problem is O(n^3).
	*
	*/
	
	static int solve(int pos, int increasing, int decreasing) {
		
		if(pos > n) {// if we count all the value
			
			return 0;// just return or stop our
			         // iteration
			
		}
		
		// if we calculate this state already
		// just skip it.
		
		if(dp[pos][increasing][decreasing] != -1) {
			
			return dp[pos][increasing][decreasing];
			
		}
		
		int value = (int)(1e9);
		
		if((increasing == 0 || a[pos] > a[increasing]) && (decreasing == 0 || a[pos] < a[decreasing]) ) {
			
			// if the both increasing and
			// decreasing state is present
			// count the minimum among the 
			// increasing state, decreasing state
			// and the skiping state.
			
			value = Math.min(solve(pos + 1, pos, decreasing), Math.min(solve(pos + 1, increasing, pos), 1 + solve(pos + 1, increasing, decreasing) ) );
			
		} else if(increasing == 0 || a[pos] > a[increasing]) {
			
			// if the only increasing state
			// is possible then we just 
			// calculate the minimum among
			// the counting this increasing state
			// as operation or skip this present
			// state.
			
			value = Math.min(solve(pos + 1, pos, decreasing), 1 + solve(pos + 1, increasing, decreasing));
			
		} else if(decreasing == 0 || a[pos] < a[decreasing]) {
			
			// if the only decreasing state
			// is possible then we just 
			// calculate the minimum among
			// the counting this decreasing state
			// as operation or skip this present
			// state.
			
			value = Math.min(solve(pos + 1, increasing, pos), 1 + solve(pos + 1, increasing, decreasing));
			
		} else {
			
			// if no condition is possible
			// then increasing our count.
			
			value = 1 + solve(pos + 1, increasing, decreasing);
			
		}
		
		return dp[pos][increasing][decreasing] = value;
		
	}
	
	static void solve() {
		
		dp = new int[n + 5][n + 5][n + 5];
		
		// initial state, considering all value
		// as the -1.
		
		for(int i = 0; i <= n + 4; ++i) {
			
			for(int j = 0; j <= n + 4; ++j) {
				
				for(int k = 0; k <= n + 4; ++k) {
					
					dp[i][j][k] = -1;
					
				} 
				
			}
			
		}
		
		int ans = solve(0, 0, 0);
		
		System.out.println(ans);
		
	}
	
	public static void main(String [] amit) {
		
		int testCases = 0;
		
		while(testCases != -1) {
			
			testCases = in.nextInt();
			
			if(testCases == -1) {
				
				return;
				
			}
			
			n = testCases;
			
			a = new int[n + 1];
			
			for(int i = 1; i <= n; ++i) {
				
				a[i] = in.nextInt();
				
			}
			
			solve();
			
		}
		
	}
	
}