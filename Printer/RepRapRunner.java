package Printer;

//制御コードを実行するクラス
import java.util.ArrayList;
import java.util.HashMap;

import simuRendering.SimuRendering;

public class RepRapRunner {
	private Stage[] stage = new Stage[3];// 動かすステージの座標
	private Extruder ext;// 制御するエクストルーダー
	private ArrayList<HashMap<Character, Float>> mapList;// 実行するコード
	private float oldE = 0f;// 材料の押出速度の履歴
	private float e = 0f;// 材料の押出速度
	private float[] home = { 0, 0, 0 };// リファレンス点の座標
	private Log log;
	private ArrayList<Double[]> logArray = new ArrayList<Double[]>();// 座標と押出量の配列（シミュレーション用）

	// コンストラクター
	public RepRapRunner(Stage[] stage, Extruder ext, ArrayList<HashMap<Character, Float>> rep) {
		this.stage = stage;
		this.ext = ext;
		mapList = rep;
	}

	// 制御コードの実行
	public void start() {
		log = new Log();

		for (int i = 0; i < mapList.size(); i++) {
			HashMap<Character, Float> codeLine = mapList.get(i);
			if (codeLine.containsKey('G'))
				runGCode(codeLine);
			else if (codeLine.containsKey('M'))
				runMCode(codeLine);

			String form = "%.3f %.3f %.3f %.5f";
			String line = String.format(form, stage[0].getVal(), stage[1].getVal(), stage[2].getVal(), e);
			Double[] logLine = { (double) stage[0].getVal(), (double) stage[1].getVal(), (double) stage[2].getVal(),
					(double) e };
			logArray.add(logLine);
			log.writeLog(line);

			oldE = e;
			e = 0;// 押出を行っていた場合は停止する．

		}
	}

	public void simurate() {// シミュレーションの実行を行う．
		String name = "RepRap";
		int[] size = { 1000, 1000 };
		Double[][] pointData = logArray.toArray(new Double[logArray.size()][4]);

		SimuRendering sr = new SimuRendering(name, size, pointData, true);
		sr.show();
	}

	private void runGCode(HashMap<Character, Float> code) {
		switch (Math.round(code.get('G'))) {
			case 0:
				g0(code);
				break;

			case 1:
				g1(code);
				break;

			case 28:
				g28(code);
				break;

			case 92:
				g92(code);
				break;
		}
	}

	private void runMCode(HashMap<Character, Float> code) {
		switch (Math.round(code.get('M'))) {
			case 104:
				m104(code);
				break;

			case 106:
				m106(code);
				break;

			case 107:
				m107(code);
				break;

			case 109:
				m109(code);
				break;
		}
	}

	// Gコード
	private void g0(HashMap<Character, Float> code) {// エクストルーダーの移動
		if (code.containsKey('X'))
			stage[0].move(code.get('X'));
		if (code.containsKey('Y'))
			stage[1].move(code.get('Y'));
		if (code.containsKey('Z'))
			stage[2].move(code.get('Z'));
	}

	private void g1(HashMap<Character, Float> code) {// 材料の押出
		if (code.containsKey('X'))
			stage[0].move(code.get('X'));
		if (code.containsKey('Y'))
			stage[1].move(code.get('Y'));
		if (code.containsKey('Z'))
			stage[2].move(code.get('Z'));

		if (code.containsKey('E'))
			e = code.get('E');
		else
			e = oldE;

	}

	private void g28(HashMap<Character, Float> code) {// リファレンス点復帰
		if (code.containsKey('X'))
			stage[0].move(code.get('X'));
		else
			stage[0].move(home[0]);// 座標指定がない場合はリファレンス点に移動
		if (code.containsKey('Y'))
			stage[1].move(code.get('Y'));
		else
			stage[0].move(home[1]);
		if (code.containsKey('Z'))
			stage[2].move(code.get('Z'));
		else
			stage[0].move(home[2]);

	}

	private void g92(HashMap<Character, Float> code) {// リファレンス点を変更する
		if (code.size() == 1) {
			for (int i = 0; i < home.length; i++)
				home[i] = 0;// 座標の指定がない場合はすべて０にする．

		} else {
			if (code.containsKey('X'))
				home[0] = code.get('X');
			if (code.containsKey('Y'))
				home[1] = code.get('Y');
			if (code.containsKey('Z'))
				home[2] = code.get('Z');

			if (code.containsKey('E'))
				oldE = code.get('E');
		}

	}

	// mコード
	private void m104(HashMap<Character, Float> code) {// エクストルーダーの温度設定
		ext.setTemp(code.get('S'));
	}

	private void m106(HashMap<Character, Float> code) {// クーラーの回転数を設定してONにする
		ext.coolerOn();
		ext.setRpm(code.get('S'));
	}

	private void m107(HashMap<Character, Float> code) {// クーラーをOFFにする
		ext.setRpm(0);
		ext.coolerOff();
	}

	private void m109(HashMap<Character, Float> code) {// エクストルーダーの温度設定
		// 本来設定した温度まで操作を一時停止する命令だが，今回はm104と同じ仕様にした．
		ext.setTemp(code.get('S'));
	}

}
