package Printer;

//工具を扱うクラス
public abstract class Module {
	private PowerSupply cooler = new PowerSupply();

	// クーラーの電源を入れる
	public void coolerOn() {
		this.cooler.on();
	}

	// クーラーの電源を切る
	public void coolerOff() {
		this.cooler.off();
	}

	public boolean getCoolerState() {
		return this.cooler.getState();
	}

	public abstract String getState();
}