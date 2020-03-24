/**
 * @author http://software-engineering-lab.com/
 *
 */
public class Dijkstra {

	/**
	 * 配列の添字は番号であるためアルファベットに変換するための定数文字列。
	 */
	private static final char[] CHAR_MAPPING = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
	
	/**
	 * マップを表現する二次元配列添字が座標を表し、一次元目の各座標から二次元目の各座標までの距離を表す。nullの場合はルートが確立しておらず、0の場合は自座標。
	 */
	private static final Integer[][] VERTEXES = {{0,null,null,null,null,null,null,1,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null},
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
	 * 最短ルートの座標順を配列で表す。vertexesを利用する際はこの配列とコンバートする。
	 */
	private static Integer[] sequence = new Integer[VERTEXES.length];
	
	/**
	 * アルゴリズムの結果を格納する配列
	 */
	private static Integer[][] matrix = new Integer[VERTEXES.length][2];
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		/**
		 * 開始座標
		 */
		final char start_vertex = args[0].toCharArray()[0];
		
		/**
		 * 終了座標
		 */
		final char end_vertex = args[1].toCharArray()[0];
		
		/**
		 * 経路順の順番をカウント
		 */
		int counter = 1;
		
		/**
		 * 現在経路探索している座標
		 */
		int current = 0;

		// 経路を確立するために並び替え
		sequence[0] = convertIndex(start_vertex);
		int number = sequence[0];

		while(counter < sequence.length) {
			for(int i=0;i<sequence.length;i++) {
				if(VERTEXES[number][i] != null && VERTEXES[number][i] != 0) {
					if(isNothing(i)) {
						sequence[counter] = i;
						counter++;
					}
				}
			}
			current++;
			// 孤立ルートを代入する
			if(sequence[current] == null) {
				for(int i=0;i<sequence.length;i++) {
					if(isNothing(i)) {
						sequence[counter] = i;
						counter++;
					}
				}
			}
			number = sequence[current];
		}

		// 最短経路を探索
		for(int i=0;i<sequence.length;i++) {
			for(int j=0;j<sequence.length;j++) {		
				if (VERTEXES[sequence[i]][sequence[j]] != null) {
				// 対象ノードと隣接している
					if(matrix[sequence[j]][1] != null) {
					// この隣接ノードの経路はもう確立している
						if(matrix[sequence[i]][1] > VERTEXES[sequence[i]][sequence[j]] + matrix[sequence[j]][1]) {
						// この隣接ノード経由の方がすでに確立している経路より対象ノードへの距離が短い
							matrix[sequence[i]][0] = sequence[j];
							matrix[sequence[i]][1] = VERTEXES[sequence[i]][sequence[j]] + matrix[sequence[j]][1];
						} else {
						// この隣接ノード経由の方がすでに確立している経路より対象ノードへの距離が長いか同じ（処理なし）
						}
					} else {
					// この隣接ノードの経路はまだ確立していない
						if(matrix[sequence[i]][1] != null){
						// 対象ノードの経路は既に確立している
							matrix[sequence[j]][0] = sequence[i];
							matrix[sequence[j]][1] = VERTEXES[sequence[i]][sequence[j]] + matrix[sequence[i]][1];
						} else {
						// 対象ノードの経路はまだ確立していない
							matrix[sequence[j]][0] = sequence[i];
							matrix[sequence[j]][1] = VERTEXES[sequence[i]][sequence[j]];
						}
					}
				}
			}
		}
		System.out.println(getRoute(start_vertex,end_vertex));;
		System.out.println(getFastestCost(end_vertex));
		showEndPoints();
	}

	/**
	 * 座標を既に取得しているかを判定
	 * 
	 * @param number
	 * @return true:座標を既に採用している。 false:座標をまだ採用していない。
	 */
	private static boolean isNothing(int number) {
		for(int i=0;i<sequence.length;i++) {
			if(sequence[i] != null) {
				if(sequence[i] == number) {
					return false;
				}
			}
		}
		return true;
	}
	
	/**
	 * マップの座標を示すキャラクタをインデックスに変換
	 * 
	 * @param c
	 * @return　インデックス
	 */
	private static int convertIndex(char c) {
		int i = 0;
		while(CHAR_MAPPING[i] != c) {
			i++;
		}
		return i;
	}
	
	/**
	  * 再起呼び出しで開始位置から終了位置のルートを表示
	 * Matrixは開始位置からのすべての位置までの最短ルートを
	   *  設定してあるので知りたい終了位置から開始位置まで逆順で辿る
	 * 
	 * @param startVertex
	 * @param endVertex
	 */
	private static String getRoute(char startVertex, char endVertex) {
		// スタート地点まで来たまたは孤立座標が終点なら終了でブレークする
		if(CHAR_MAPPING[matrix[convertIndex(endVertex)][0]] == startVertex || matrix[convertIndex(endVertex)][1] == 0) {
			return startVertex+"->"+endVertex;
		}
		// 再起呼び出しはFIFO(First in Last Out)なので正順で表示される。
		return getRoute(startVertex,CHAR_MAPPING[matrix[convertIndex(endVertex)][0]]) +"->"+endVertex;
	}
		
	/**
	 * 最短経路のコストを表示
	 * 
	 * @param endVertex
	 */
	private static String getFastestCost(char endVertex) {
		return "Total:" + matrix[convertIndex(endVertex)][1];
	}
	
	/**
	 * 全終点までの中継点とコストを表示
	 */
	private static void showEndPoints() {
		for(int i=0;i<26;i++) {
			System.out.print(CHAR_MAPPING[matrix[i][0]]+"->"+CHAR_MAPPING[i]+":");
			System.out.println(String.format("%02d", matrix[i][1]));
		}
	}
}
