package Printer;

//電源のクラス
public class PowerSupply {
	private boolean power = false;// 初期条件はOｆｆ

	// 電源を入れる
	public void on() {
		this.power = true;
	}

	// 電源を切る
	public void off() {
		this.power = false;
	}

	// 電源の状態を返す．
	public boolean getState() {
		return this.power;
	}
}
