/**
 * @author http://software-engineering-lab.com/
 *
 */
public class Dijkstra {

	/**
	 * 各バーテックスの名称一覧
	 */
	static final char[] CHAR_MAPPING = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};

	/**
	 * 各バーテックスのルートとコストを示す二次元配列
	 */
	static final Integer[][] VERTEXES = {{0,null,null,null,null,null,null,1,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null},
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
	/**
	 * 経路順(最短ではない)
	 */
	static Integer[] sequence = new Integer[26];

	/**
	 * 各バーテックスへ到達するまでのコストと隣接バーテックスを示す結果セットの二次元配列
	 */
	static Integer[][] matrix = new Integer[26][2];

	/**
	 * 経路順がどこまで確立したかを示すカウンタ
	 */
	static int counter = 1;

	/**
	 * 現在確立している地点
	 */
	static int current = 0;

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		// 経路を確立するために並び替え
		sequence[0] = 8;
		int number = sequence[0];

		while(counter < 26) {
			for(int i=0;i<26;i++) {
				if(VERTEXES[number][i] != null && VERTEXES[number][i] != 0) {
					boolean flg = true;//座標をまだ採用していない
					for(int j=0;j<26;j++) {
						if(sequence[j] != null) {
							if(sequence[j] == i) {
								flg = false;//座標を既に採用している
								break;
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
					boolean flg = true;//座標をまだ採用していない
					for(int j=0;j<26;j++) {
						if(sequence[j] != null) {
							if(sequence[j] == i) {
								flg = false;//座標を既に採用している
								break;
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

		// 孤立ルートからスタートかどうかを判断
		if (current == 1) {
			// 最初で終わっていれば孤立ルートとみなして全て０を代入する
			for(int i=0;i<26;i++){
				matrix[sequence[i]][0] = sequence[i];
				matrix[sequence[i]][1] = 0;
			}
		} else {
			// 最短経路を探索
			for(int i=0;i<26;i++) {
				for(int j=0;j<26;j++) {
					if (VERTEXES[sequence[i]][sequence[j]] != null) {
						// 対象バーテックスと隣接している
						if(matrix[sequence[j]][1] != null) {
							// この隣接バーテックスの経路はもう確立している
							if(matrix[sequence[i]][1] > VERTEXES[sequence[i]][sequence[j]] + matrix[sequence[j]][1]) {
								// この隣接バーテックス経由の方がすでに確立している経路より対象バーテックスへの距離が短い
								matrix[sequence[i]][0] = sequence[j];
								matrix[sequence[i]][1] = VERTEXES[sequence[i]][sequence[j]] + matrix[sequence[j]][1];
								// 確立した経路により隣接バーテックスの経路が更に短くなるか遡及する
								for(int k=0;k<26;k++) {
									if(VERTEXES[sequence[i]][k] != null && matrix[k][1] != null) {
										// 対象バーテックスと隣接するバーテックスの経路がすでに確立している
										if(matrix[k][1] > VERTEXES[sequence[i]][k] + matrix[sequence[i]][1]) {
											// この隣接バーテックスと確立したルートより対象バーテックスと隣接するバーテックスの距離も短くなる
											matrix[k][0] = sequence[i];
											matrix[k][1] = VERTEXES[sequence[i]][k] + matrix[sequence[i]][1];
										}
									}
								}
							} else {
								// この隣接バーテックス経由の方がすでに確立している経路より対象バーテックスへの距離が長いか同じ（処理なし）
							}
						} else {
							// この隣接バーテックスの経路はまだ確立していない
							if(matrix[sequence[i]][1] != null){
								// 対象バーテックスの経路は既に確立している
								matrix[sequence[j]][0] = sequence[i];
								matrix[sequence[j]][1] = VERTEXES[sequence[i]][sequence[j]] + matrix[sequence[i]][1];
							} else {
								// 対象バーテックスの経路はまだ確立していない
								matrix[sequence[j]][0] = sequence[i];
								matrix[sequence[j]][1] = VERTEXES[sequence[i]][sequence[j]];
							}
						}
					}
				}
			}
		}
		// 最短経路のコストを表示
		System.out.println("Total:" + matrix[13][1]);
	}
}
