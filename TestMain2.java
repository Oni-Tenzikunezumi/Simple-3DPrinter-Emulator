//SimuRenderingクラスのインスタンス化と情報の取得
//正常にレンダリングが行われているか，アニメーションの操作が行えるか，を確認する．

import simuRendering.SimuRendering;

public class TestMain2 {

	public static void main(String[] args) {
		Double[][] pointData = {
				{ -10.0, 0.0, 0.0, 1.0 }, { 0.0, 10.0, 0.0, 10.0 }, { 0.0, 0.0, -10.0, 10.0 },
				{ 10.0, 0.0, 0.0, 1.0 }, { 0.0, 0.0, 10.0, 10.0 }, { 0.0, -10.0, 0.0, 10.0 } };

		String name = "TestMain2.java";
		int[] size = { 600, 600 };
		SimuRendering sr = new SimuRendering(name, size, pointData, false);
		sr.show();
		// ドラッグ：回転
		// ホイール：拡大縮小
		// 矢印キー：平行移動
		// 一度クリックすると出る

	}

}
