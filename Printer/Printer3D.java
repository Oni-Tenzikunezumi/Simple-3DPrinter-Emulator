package Printer;

import codeReader.CodeReader;
import codeReader.RepRap;

//３Dプリンター本体のクラス
public class Printer3D {
	private String name;// プリンター本体の名前
	private Extruder ext;
	private Filament fila;
	private Stage x, y, z;

	private String fileName;// 印刷するデータの名前
	private CodeReader code;
	private RepRapRunner RRR = null;

	public Printer3D(String name, Extruder ext, Filament fila, float[] size) {
		this.name = name;
		this.ext = ext;
		this.fila = fila;
		this.x = new Stage(size[0]);
		this.y = new Stage(size[1]);
		this.z = new Stage(size[2]);
	}

	public String getInfo() {
		String printerInfo = "Printername : " + this.name;
		String extInfo = ext.getState();
		String filaInfo = fila.getState();
		String stageInfo = String.format("Stage:%.1f*%.1f*%.1f", this.x.getMax(), this.y.getMax(), this.z.getMax());
		return printerInfo + "\n\n" + extInfo + "\n\n" + filaInfo + "\n\n" + stageInfo + "\n\n";

	}

	// ファイル名を指定し，読み込む．
	public void setRepRap(String fileName) {
		code = new RepRap();

		if (code.setFilePath(fileName)) {
			boolean printable = isPrintableSize(code.getSize()) & isFilaEnough(code.getRequiredFilament());
			if (printable) {
				this.fileName = fileName;
				Stage[] stage = { x, y, z };
				RRR = new RepRapRunner(stage, ext, code.getCode());
				System.out.println(this.fileName + "の印刷準備ができました．");
			}

		} else {
			System.out.println("ファイルが見つかりません．");
		}

	}

	// 印刷できるサイズかの確認．
	public boolean isPrintableSize(float[] size) {
		boolean xValid = x.getMax() >= size[0];
		boolean yValid = y.getMax() >= size[1];
		boolean zValid = z.getMax() >= size[2];

		boolean ans = (xValid & yValid) & zValid;
		if (ans) {
			String form = "印刷可能なサイズです．(%.1f*%.1f*%.1f)";
			System.out.println(String.format(form, size[0], size[1], size[2]));
		} else {
			System.out.println("サイズが大きすぎます．");
		}

		return ans;
	}

	// 印刷できるフィラメント残量かの確認．
	public boolean isFilaEnough(float require) {
		boolean ans = fila.getRemaining() >= require;
		if (ans) {
			System.out.println("フィラメント十分．");
		} else {
			System.out.println("フィラメントの残量が足りません．");
		}
		return ans;
	}

	// 印刷を行う．
	public void startPrint(boolean isSimurate) {
		if (RRR != null) {
			System.out.println("印刷中");
			RRR.start();
			System.out.println("印刷完了");
			fila.useFilament(code.getRequiredFilament());
			if (isSimurate)
				RRR.simurate();

		} else {
			System.out.println("まだコードの読み込みがされていません．");
		}
	}
}
