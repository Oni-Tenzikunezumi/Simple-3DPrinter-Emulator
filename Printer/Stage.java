package Printer;

//ステージの座標系のクラス
public class Stage {
	// 座標の値と最大値
	private float val = 0, max;

	// コンストラクタ
	public Stage(float max) {
		this.max = max;
	}

	// 座標の取得
	public float getVal() {
		return val;
	}

	// 最大座標の取得
	public float getMax() {
		return this.max;
	}

	// 座標の移動
	// 移動できたかどうかを真偽値で返す．
	public boolean move(float distance) {

		if (this.max < distance) {
			System.out.println("これ以上移動できません．err1");
			return false;
		} else {
			this.val = distance;
			return true;
		}

	}
}
