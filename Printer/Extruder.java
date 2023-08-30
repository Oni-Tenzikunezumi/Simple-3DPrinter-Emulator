package Printer;

//エクストルーダーのクラス
public class Extruder extends Module {
	// 現在のエクストルーダー温度，エクストルーダーの最大温度
	private final float normalTemp = 27;// 常温は27度に固定
	private float coolerRpm = 0;// 冷却ファンの回転数
	private float temp, maxTemp;// 現在温度は初期条件で常温

	// コンストラクタ：引数は最大温度のみ．
	public Extruder(float maxTemp) {
		this.maxTemp = maxTemp;
		this.temp = this.normalTemp;
	}

	// エクストルーダーの情報を返す．
	@Override
	public String getState() {
		String form1 = String.format("Temperature : %.1f", temp);
		String form2 = String.format("Fan:%s(%.1f)", getCoolerState() == true ? "On" : "Off", coolerRpm);
		return "Extruder\n" + form1 + "\n" + form2;
	}

	// エクストルーダーの温度設定を行う．
	public boolean setTemp(float param) {
		if (param < maxTemp) {
			this.temp = param;
			return true;

		} else {
			System.out.println(this.maxTemp + "より高い温度は設定できません．");
			return false;
		}
	}

	// クーラーの回転数を設定する．
	public void setRpm(float rpm) {
		this.coolerRpm = rpm;
	}
}
