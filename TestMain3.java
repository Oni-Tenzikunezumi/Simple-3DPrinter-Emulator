//printerクラスのインスタンス化と情報の取得,印刷とそのシミュレーションを行う
//印刷後にフィラメントの残量に変化があるかを確認する．
//また，logフォルダ内に.logファイルが生成されているか確認する．

import Printer.Extruder;
import Printer.Filament;
import Printer.Printer3D;

public class TestMain3 {
	public static void main(String[] args) {
		String name = "XYZ";
		Extruder ext = new Extruder(300);
		Filament fila = new Filament("ABS", 200);
		float[] size = { 150f, 150f, 150f };

		Printer3D p1 = new Printer3D(name, ext, fila, size);
		System.out.println(p1.getInfo());// 情報の取得

		String fileName = "test.gcode";
		p1.setRepRap(".\\Gcode\\" + fileName);// パスの指定
		p1.startPrint(true);

		System.out.println(p1.getInfo());// 印刷後のパラメータ変化を確認する．

	}
}

/*
 * サンプルファイル
 *
 * 船のおもちゃ
 * "boat.gcode"
 *
 * 台形ボルトモデル
 * "bolt.gcode"
 *
 * 花状の軌跡1
 * "flower1.gcode"
 *
 * 花形のクッキー型2
 * "flower2.gcode"
 *
 * マリオ人形
 * "mario.gcode"
 *
 * クモデザインのPCファンカバー
 * "spider.gcode"
 *
 * 直方体
 * "test.gcode"
 *
 */