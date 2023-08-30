//RepRapクラスのインスタンス化と情報の取得
//コードが読み込めているかを確認する．

import java.util.ArrayList;
import java.util.HashMap;

import codeReader.RepRap;

public class TestMain1 {
	public static void main(String[] args) {
		RepRap r1 = new RepRap();

		String fileName = "boat.gcode";

		// コードの読み込み，連想配列にする．
		if (r1.setFilePath(".\\Gcode\\" + fileName)) {
			System.out.println(r1.getRequiredFilament());
			ArrayList<HashMap<Character, Float>> c = r1.getCode();

			// 表示
			for (int i = 0; i < c.size(); i++) {
				System.out.println(c.get(i));
			}

		} else {
			System.out.println("ファイルが見つかりません．");
		}

	}
}