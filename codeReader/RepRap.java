//RepRapの規格で記述された制御コードの読み込み．
package codeReader;

import java.util.HashMap;

public class RepRap extends CodeReader {
	private int infoLine = 10;// 印刷物の情報が入る行数
	private int startLine = 12;// 制御コードの開始位置

	// codeListを連想配列にする．
	@Override
	void makeMap() {
		for (int i = 0; i < infoLine; i++) {// 設定情報の連想配列を作る
			String[] words = this.codeList.get(i).split(":");
			infoMap.put(words[0].substring(1), words[1]);
		}

		for (int i = startLine - 1; i < codeList.size(); i++) {// 制御コードの配列を各行を連想配列にして作る．
			String[] words = this.codeList.get(i).split(" ");

			HashMap<Character, Float> Map = new HashMap<Character, Float>();// 制御コードを入れる
			for (int j = 0; j < words.length; j++) {
				char key = words[j].charAt(0);

				if (key != ';') {// コメントアウト（；以降）は切り捨て
					float value = Float.parseFloat(words[j].substring(1));
					Map.put(key, value);
					// M83 ;relative extrusion mode
				} else
					break;
			}

			if (Map.size() > 0)
				mapList.add(Map);// コードがあるときのみ配列追加
		}

	}

	// 印刷物のサイズを返す．
	@Override
	public float[] getSize() {
		float[] size = new float[3];
		size[0] = Float.parseFloat(infoMap.get("MAXX"));
		size[1] = Float.parseFloat(infoMap.get("MAXY"));
		size[2] = Float.parseFloat(infoMap.get("MAXZ"));

		return size;
	}

	// 必要なフィラメント量を返す．
	@Override
	public float getRequiredFilament() {
		return Float.parseFloat(infoMap.get("Filament used").replace("m", ""));
	}

}
