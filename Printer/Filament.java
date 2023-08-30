package Printer;

//フィラメント（材料）のクラス
public class Filament {
	private String Material;// 材料名
	private float remaining;// 残量

	// コンストラクタ：引数は材料名と残量
	public Filament(String Material, float remaining) {
		this.Material = Material;
		this.remaining = remaining;
	}

	// フィラメントの情報を返す．
	public String getState() {
		String form1 = String.format("Material: %s", this.Material);
		String form2 = String.format("Remaining : %.1f", this.remaining);
		return "Filament\n" + form1 + "\n" + form2;
	}

	// フィラメントを利用する．
	public void useFilament(float amount) {
		if (this.remaining < amount) {
			System.out.println("フィラメントの残量が足りません．");
		} else {
			this.remaining -= amount;
		}
	}

	public float getRemaining() {
		return remaining;
	}

}
