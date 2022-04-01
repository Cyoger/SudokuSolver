public class BoardGen {
	
	int[] mat[];	
	int n;
	int sqrt;
	int k;

	public BoardGen(int n, int k) {
		this.n = n;
		this.k = k;

		Double sqrtn = Math.sqrt(n);
		sqrt = sqrtn.intValue();

		mat = new int[n][n];
	}

	public void Fill() {
		FillDiagonal();
		fillRemaining(0, sqrt);
		removeDigits();
	}

	public void FillDiagonal() {
		for(int i = 0; i<n; i+=sqrt){
			fillBox(i, i);
		}
	}	

	public int RandomGenerator(int num){
		return (int) Math.floor((Math.random() * num + 1));
	}

	public boolean unUsedCol(int row, int col) {
		for (int i = 0; i < n; i++) {
			if (mat[i][row] == col) {
				return false;
			}
		}
		return true;
	}

	public boolean unUsedRow(int i, int num){
		for (int j = 0; j < n; j++) {
			if (mat[i][j] == num) {
				return false;
			}
		}
		return true;
	}

	public boolean unUsedSquare(int i, int j, int num){
		for (int k = 0; k < sqrt; k++) {
			for (int l = 0; l < sqrt; l++) {
				if (mat[i + k][j + l] == num) {
					return false;
				}
			}
		}
		return true;
	}

	public boolean fillRemaining(int i, int j){
		if(j>=n && i<n-1){
			i++;
			j=0;
		}
		if(i>=n && j>=n){
			return true;
		}

		if(i < sqrt){
			if(j < sqrt){
				j = sqrt;
			}
		}else if(i < n-sqrt){
			if(j == (int) (i/sqrt)*sqrt){
				j = j+sqrt;
			}
		}else{
			if(j == n-sqrt){
				i++;
				j = 0;
				if(i>=n){
					return true;
				}
			}
		}

		for(int num = 1; num <= n; num++){
			if(check(i,j,num)){
				mat[i][j] = num;
				if(fillRemaining(i,j+1)){
					return true;
				}

				mat[i][j] = 0;
			}	
		}
		return false;
	}

	public void fillBox(int row, int col){
		int num;
		for(int i = 0; i < sqrt; i++){
			for(int j = 0; j < sqrt; j++){
				do{
					num = RandomGenerator(n);
				}while(!unUsedSquare(row,col,num));
				mat[row+i][col+j] = num;
			}
		}
	}

	public boolean check(int i, int j, int num){
		return(unUsedCol(j, num) && unUsedRow(i, num) && unUsedSquare(i-i%sqrt, j-j%sqrt, num));
	}

	public void removeDigits(){
		int count = k;
		while(count != 0){
			int cellid = RandomGenerator(n*n)-1;

			int row = cellid/n;
			int col = cellid%9;
			
			if(col != 0){
				col--;
			}

			if(mat[row][col] != 0){
				count--;
				mat[row][col] = 0;
			}
		}
	}

	public void printBoard(){
		for(int i = 0; i < n; i++){
			for(int j = 0; j < n; j++){
				System.out.print(mat[i][j] + " ");
			}
			System.out.println();
		}
	}

}
