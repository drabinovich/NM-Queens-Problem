/**
 * N-Queen problem solved for M queens (M between 1 and N)
 *************************************************************************/
public class Queens {
	
	static int solutions = 0;
	static int TARGET_QUEENS;

   /**
    * Entry point
    ***********************************************************************/	
	public static void main(String[] args) {						
		TARGET_QUEENS = 8;
		solve(new boolean[8*8], 0);
		System.out.println("Solutions: "+solutions);		
	}	
	
   /**
    * True if added queen on square <s> doesn't conflict with existing queens
	* Assumes there are no queens on squares > s
    ***********************************************************************/
	public static boolean isConsistent(boolean[] b, int s) {
		int N = (int)Math.sqrt(b.length);
		for (int i = 0; i < s; i++) {
			if ( b[i] ) {
				if ( i/N == s/N )			return false; //same row
				if ( i%N == s%N )			return false; //same column
				if ( i/N-i%N == s/N-s%N )	return false; //same major diag
				if ( i/N+i%N == s/N+s%N )	return false; //same minor diag
			}
		}
		return true;
	}

   /**
    * How many queens on board <b>
    ***********************************************************************/	
	public static int queensCount(boolean[] b) {
		int qty = 0;
		for (int i = 0; i < b.length; i++) {
			if ( b[i] ) qty++;
		}
		return qty;
	}	

   /**
    * Draws board <b>
    ***********************************************************************/	
    public static void print(boolean[] b) {		
        for (int i = 0; i < b.length; i++) {
			if ( i%Math.sqrt(b.length) == 0 ) System.out.println();
			System.out.print(( b[i] ? "Q " : "* " ) ); 
        }  
        System.out.println();
    }

   /**
    * Solves board <b> from square <s> 
    ***********************************************************************/	
    public static void solve(boolean[] b, int s) {
		if ( queensCount(b) == TARGET_QUEENS ) {
			solutions++;
			print(b);
		} else if ( s >= b.length ) {
			return; //not a solution
		} else {
			for (int i = s; i < b.length; i++) {
				b[i] = true;
				if ( isConsistent(b, i) ) {
					solve(b, i+1);
				}
				b[i] = false;
			}
		}
    }

}