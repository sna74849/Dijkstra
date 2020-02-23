/**
 * @author http://software-engineering-lab.com/
 *
 */
public class Dijkstra {

	/**
	 * 配列の添字は番号であるためアルファベットに変換するための定数文字列。
	 */
	static char[] charMapping = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
	
	/**
	 * マップを表現する二次元配列添字が座標を表し、一次元目の各座標から二次元目の各座標までの距離を表す。nullの場合はルートが確立しておらず、0の場合は自座標。
	 */
	static Integer[][] vertexes = {{0,null,null,null,null,null,null,1,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null},
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
	static Integer[] sequence = new Integer[26];
	
	/**
	 * 経路順の順番をカウント
	 */
	static int counter = 1;
	
	/**
	 * 現在経路探索している座標
	 */
	static int current;
	
	/**
	 * アルゴリズムの結果を格納する配列
	 */
	static Integer[][] matrix = new Integer[26][2];

	/**
	 * @param args
	 */
	public static void main(String[] args) {

//		char[] charMapping = {'A','H','B','G','I','O','M','C','J','N','P','T','U','V','S','D','K','Q','Z','W','E','L','R','X','F','Y'};
//
//
//		Integer[][] vertexes = {{0,1,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null},
//								{1,0,1,1,1,3,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null},
//								{null,1,0,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null},
//								{null,1,null,0,null,null,1,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null},
//								{null,1,null,null,0,3,null,3,1,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null},
//								{null,3,null,null,3,0,null,null,3,1,2,3,3,3,null,null,null,null,null,null,null,null,null,null,null,null},
//								{null,null,null,1,null,null,0,null,null,null,null,null,null,null,1,null,null,null,null,null,null,null,null,null,null,null},
//								{null,null,null,null,3,null,null,0,1,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null},
//								{null,null,null,null,1,3,null,1,0,null,2,null,null,null,null,2,2,null,null,null,null,null,null,null,null,null},
//								{null,null,null,null,null,1,null,null,null,0,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null},
//								{null,null,null,null,null,2,null,null,2,null,0,null,null,2,null,null,null,2,null,null,null,null,null,null,null,null},
//								{null,null,null,null,null,3,null,null,null,null,null,0,null,null,1,null,null,null,1,null,null,null,null,null,null,null},
//								{null,null,null,null,null,3,null,null,null,null,null,null,0,null,null,null,null,null,1,null,null,null,null,null,null,null},
//								{null,null,null,null,null,3,null,null,null,null,2,null,null,0,null,null,null,null,null,1,null,null,null,null,null,null},
//								{null,null,null,null,null,null,1,null,null,null,null,1,null,null,0,null,null,null,null,null,null,null,null,null,null,null},
//								{null,null,null,null,null,null,null,null,2,null,null,null,null,null,null,0,null,null,null,null,2,null,null,null,null,null},
//								{null,null,null,null,null,null,null,null,2,null,null,null,null,null,null,null,0,2,null,null,2,2,null,null,null,null},
//								{null,null,null,null,null,null,null,null,null,null,2,null,null,null,null,null,2,0,null,null,null,null,2,null,null,null},
//								{null,null,null,null,null,null,null,null,null,null,null,1,1,null,null,null,null,null,0,null,null,null,null,null,null,null},
//								{null,null,null,null,null,null,null,null,null,null,null,null,null,1,null,null,null,null,null,0,null,null,null,1,null,null},
//								{null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,2,2,null,null,null,0,null,null,null,2,null},
//								{null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,2,null,null,null,null,0,2,null,2,null},
//								{null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,2,null,null,null,2,0,null,null,null},
//								{null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,1,null,null,null,0,null,null},
//								{null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,2,2,null,null,0,null},
//								{null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,0}};
		/*
		 * Integer[][] vertexes = {{0,2,3,null,null,null,null},
		 * {2,0,1,5,null,null,null}, {3,1,0,5,1,null,null}, {null,5,5,0,2,1,4},
		 * {null,null,1,2,0,null,null}, {null,null,null,1,null,0,null},
		 * {null,null,null,4,null,null,0}};
		 */
		

		/* 経路を確立するために並び替え */
		sequence[0] = convertIndex('A');
		enqueue(sequence[0]);

		/* 最短経路を探索 */
		invokeAlgorithm();

		showRoute('A','Z');
		showEndPoints();
		
//		for(int i=0;i<26;i++) {
//			System.out.print(charMapping[matrix[i][0]]+":");
//			System.out.println(String.format("%02d", matrix[i][1]));
//		}
	}

	/**
	 * アルゴリズムを実行
	 */
	private static void invokeAlgorithm() {
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
					// 対象ノードと隣接していない（処理なし）
				}
			}
		}
	}
	
	/**
	 * マップの座標を示すキャラクタをインデックスに変換
	 * 
	 * @param c
	 * @return　インデックス
	 */
	private static int convertIndex(char c) {
		int i = 0;
		while(charMapping[i] != c) {
			i++;
		}
		return i;
	}
	
	/**
	 * 再起呼び出しで開始位置から終了位置のルートを表示
	 * Matrixは開始位置からのすべての位置までの最短ルートを
	 * 設定してあるので知りたい終了位置から開始位置まで逆順で辿る
	 * 
	 * @param startVertex
	 * @param endVertex
	 */
	private static void showRoute(char startVertex, char endVertex) {
		
		// スタート地点まで来たら再起をブレークする
		if(charMapping[matrix[convertIndex(endVertex)][0]] == startVertex) {
			System.out.print(startVertex+"->"+endVertex);
			return;
		}
		
		// 再起呼び出し
		showRoute(startVertex,charMapping[matrix[convertIndex(endVertex)][0]]);
		// 再起呼び出しはFIFO(First in Last Out)なので正順で表示される。
		System.out.print("->"+endVertex);
	}
	
	private static void showEndPoints() {
		for(int i=0;i<26;i++) {
			System.out.print(charMapping[matrix[i][0]]+"->"+charMapping[i]+":");
			System.out.println(String.format("%02d", matrix[i][1]));
		}
	}
	
	/**
	 * 再起呼び出しを利用することにより探索順を確立する。
	 * 
	 * @param number
	 */
	private static void enqueue(int number) {
		for(int i=0;i<26;i++) {
			if(vertexes[number][i] != null && vertexes[number][i] != 0) {
				if(isNothing(i)) {
					sequence[counter] = i;
					counter++;
				}
			}
		}
		current++;
		if(current<25) {
			// 再起呼び出し
			enqueue(sequence[current]);
		}else {
			sequence[25] = 24;
			return;
		}
	}
	
	/**
	 * 座標を既に採用している。
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
}
