# ダイクストラアルゴリズム実証モデル
## このプログラムの実証するもの
1. ダイクストラアルゴリズムの実装方法
2. 入力と出力そしてあらかじめ保持しているデータの違い
3. 再帰関数の出力がLIFOであること
## 学習方法
### 1. ダイクストラアルゴリズムのロジックについて検証します
ダイクストラアルゴリズムは経路順に隣接する分岐点へのコストを累計していき、よりコストの少ないルートを見つけだすアルゴリズムです。 実装方法にはいくつかの選択肢がありますが、本プログラムでは決められた一つの始点に対して全終点への最短コストと隣接ノードを2次元配列で格納します。 この2次元配列が指し示す隣接ノードを再帰的に呼び出せば始点にたどり着くことが可能なので結果的に始点から各終点への最短経路が導き出せます。 このアルゴリズムは隣接する経路順に分岐点を比較する必要があるのであらかじめ、経路を確立しておかなければなりません。
### 2. データの持つ役割の違いについて検証します
本プログラムでは使われるデータにいくつか種類があります。具体的には始点を示す入力データと各終点への経路及びコストを示す出力データ、 そしてマップを表すグラフデータとロジックを制御するデータです。これらはそれぞれに異なる役割を担っています。 本プログラムは任意の始点を入力することで出力データが変わってきます。そしてこれらはプログラムを利用する人間が目的とするデータになります。 マップを表すグラフデータは利用者が目的とするデータですが本プログラム上で変更することはありません。 また、プログラムを制御するためのデータは利用者が意識する必要がありません、つまり利用者が目的とするデータではありません。 これらはプログラマとコンピュータだけが意識するデータです。
### 3. 再帰関数の活用を理解します
アルゴリズムにより導き出された結果は2次元配列に格納されますが、プログラムの利用者が特定の終点に対する最短経路とコストを知りたい場合は 配列の中から適したデータを選び出して適切な順序で出力する必要があります。本プログラムのようなケースでは再帰関数の利用が適しています。 再帰関数は呼び出しに必要な入力値を引数として渡し、その出力値である戻り値をまた関数に引数として渡して処理を繰り返す方法です。 再帰関数ではデータが後入れ先出しで出力されるため、本プログラムのように終点から始点まで処理を続けながら、結果は始点から終点で出力する場合に適しています
## 構成
ブランチ「Full」に対してブランチ「Less」は関数によるルーチン化を進めていないバージョンです。また、始点と終点の入力も非可変的に実装しています。 この二つを比較してデータの位置づけがいかにプログラムの汎用化にとって重要であるかを検証してください。
## 関連項目
#アルゴリズム #ダイクストラアルゴリズム #ルート探索 #関数 #再帰関数 #二次元配列 #多重ループ #無向グラフ
