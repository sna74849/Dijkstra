/**
 * @author http://software-engineering-lab.com/
 *
 */
public class Dijkstra {

	static final char[] charMapping = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
	
	static final Integer[][] vertexes = {{0,null,null,null,null,null,null,1,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null},
							{null,0,null,null,null,null,null,1,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null},
							{null,null,0,null,null,null,null,null,3,1,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null},
							{null,null,null,0,2,null,null,null,null,2,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null},
							{null,null,null,2,0,2,null,null,null,null,2,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null},
							{null,null,null,null,2,0,null,null,null,null,null,2,null,null,null,null,null,null,null,null,null,null,null,null,null,null},
							{null,null,null,null,null,null,0,1,null,null,null,null,1,null,null,null,null,null,null,null,null,null,null,null,null,null},
							{1,1,null,null,null,null,1,0,1,null,null,null,null,null,3,null,null,null,null,null,null,null,null,null,null,null},
							{null,null,3,null,null,null,null,1,0,1,null,null,null,null,3,null,null,null,null,null,null,null,null,null,null,null},
							{null,null,1,2,null,null,null,null,1,0,2,null,null,null,3,2,null,null,null,null,null,null,null,null,null,null},
							{null,null,null,null,2,null,null,null,null,2,0,2,null,null,null,null,2,null,null,null,null,null,null,null,null,null},
							{null,null,null,null,null,2,null,null,null,null,2,0,null,null,null,null,null,2,null,null,null,null,null,null,null,null},
							{null,null,null,null,null,null,1,null,null,null,null,null,0,null,null,null,null,null,1,null,null,null,null,null,null,null},
							{null,null,null,null,null,null,null,null,null,null,null,null,null,0,1,null,null,null,null,null,null,null,null,null,null,null},
							{null,null,null,null,null,null,null,3,3,3,null,null,null,1,0,2,null,null,null,3,3,3,null,null,null,null},
							{null,null,null,null,null,null,null,null,null,2,null,null,null,null,2,0,2,null,null,null,null,2,null,null,null,null},
							{null,null,null,null,null,null,null,null,null,null,2,null,null,null,null,2,0,2,null,null,null,null,null,null,null,null},
							{null,null,null,null,null,null,null,null,null,null,null,2,null,null,null,null,2,0,null,null,null,null,null,null,null,null},
							{null,null,null,null,null,null,null,null,null,null,null,null,1,null,null,null,null,null,0,1,null,null,null,null,null,null},
							{null,null,null,null,null,null,null,null,null,null,null,null,null,null,3,null,null,null,1,0,null,null,null,null,null,1},
							{null,null,null,null,null,null,null,null,null,null,null,null,null,null,3,null,null,null,null,null,0,null,null,null,null,1},
							{null,null,null,null,null,null,null,null,null,null,null,null,null,null,3,2,null,null,null,null,null,0,1,null,null,null},
							{null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,1,0,1,null,null},
							{null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,1,0,null,null},
							{null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,0,null},
							{null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,1,1,null,null,null,null,0}};

	static Integer[] sequence = new Integer[26];
	
	private static Integer[][] matrix = new Integer[26][2];
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		int counter = 1;
		
		int current = 0;

		// 経路を確立するために並び替え
		sequence[0] = 0;
		int number = sequence[0];

		while(counter < 26) {
			for(int i=0;i<26;i++) {
				if(vertexes[number][i] != null && vertexes[number][i] != 0) {
					boolean flg = true;//座標を既に採用している。
					for(int j=0;j<26;j++) {
						if(sequence[j] != null) {
							if(sequence[j] == i) {
								flg = false;//座標をまだ採用していない。
							}
						}
					}		
					if(flg) {
						sequence[counter] = i;
						counter++;
					}
				}
			}
			current++;
			// 孤立ルートを代入する
			if(sequence[current] == null) {
				for(int i=0;i<26;i++) {
					boolean flg = true;//座標を既に採用している。
					for(int j=0;j<26;j++) {
						if(sequence[j] != null) {
							if(sequence[j] == i) {
								flg = false;//座標をまだ採用していない。
							}
						}
					}		
					if(flg) {
						sequence[counter] = i;
						counter++;
					}
				}
			}
			number = sequence[current];
		}

		// 最短経路を探索
		for(int i=0;i<26;i++) {
			for(int j=0;j<26;j++) {		
				if (vertexes[sequence[i]][sequence[j]] != null) {
				// 対象ノードと隣接している
					if(matrix[sequence[j]][1] != null) {
					// この隣接ノードの経路はもう確立している
						if(matrix[sequence[i]][1] > vertexes[sequence[i]][sequence[j]] + matrix[sequence[j]][1]) {
						// この隣接ノード経由の方がすでに確立している経路より対象ノードへの距離が短い
							matrix[sequence[i]][0] = sequence[j];
							matrix[sequence[i]][1] = vertexes[sequence[i]][sequence[j]] + matrix[sequence[j]][1];
						} else {
						// この隣接ノード経由の方がすでに確立している経路より対象ノードへの距離が長いか同じ（処理なし）
						}
					} else {
					// この隣接ノードの経路はまだ確立していない
						if(matrix[sequence[i]][1] != null){
						// 対象ノードの経路は既に確立している
							matrix[sequence[j]][0] = sequence[i];
							matrix[sequence[j]][1] = vertexes[sequence[i]][sequence[j]] + matrix[sequence[i]][1];
						} else {
						// 対象ノードの経路はまだ確立していない
							matrix[sequence[j]][0] = sequence[i];
							matrix[sequence[j]][1] = vertexes[sequence[i]][sequence[j]];
						}
					}
				}
			}
		}
		String route = charMapping[25]+"<-";
		int e = 25;
		while(true){
			if(matrix[e][0] == 0 || matrix[e][1] == 0) {
				break;
			}
			route =  route + charMapping[matrix[e][0]] +"<-";
			e = matrix[e][0];
		}
		System.out.println(route + "<-" + charMapping[0]);
		System.out.println("Total:" + matrix[25][1]);
		showEndPoints();
	}
	
	/**
	 * 全終点までの中継点とコストを表示
	 */
	private static void showEndPoints() {
		for(int i=0;i<26;i++) {
			System.out.print(charMapping[matrix[i][0]]+"->"+charMapping[i]+":");
			System.out.println(String.format("%02d", matrix[i][1]));
		}
	}
}
