//制御コード読み込み用の抽象クラス
package codeReader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class CodeReader {
	ArrayList<String> codeList = new ArrayList<String>();// 1行ごとにコードを配列に入れる
	HashMap<String, String> infoMap = new HashMap<String, String>();// 印刷物の情報を入れる
	// 制御コードのリスト
	ArrayList<HashMap<Character, Float>> mapList = new ArrayList<HashMap<Character, Float>>();

	// ファイル名の設定
	// ファイルが存在するかどうかを確認し，booleanを返す．
	// ファイルが存在している場合，コードを読み込む．
	public boolean setFilePath(String filePath) {
		File code = new File(filePath);
		if (code.exists()) {
			this.readCode(code);// 存在する場合読み込み
			return true;
		} else {
			return false;
		}
	}

	// 制御コードを読み込み，一行ずつ配列にする．
	// コードを読み込んだのち，連想配列にする．
	void readCode(File code) {
		FileReader fr = null;
		BufferedReader br = null;

		try {
			fr = new FileReader(code);
			br = new BufferedReader(fr);
			String data = null;

			while ((data = br.readLine()) != null) {
				if (data.length() != 0) {
					this.codeList.add(data);
				}
			}

		} catch (FileNotFoundException e) {
			System.err.println("ファイルがありません");
		} catch (IOException e) {
			System.err.println("IO Error");
		} finally {
			try {
				br.close();
			} catch (IOException e) {
			}
		}

		this.makeMap();
	}

	// 読み込んだコードを返す．
	public ArrayList<HashMap<Character, Float>> getCode() {
		return this.mapList;
	}

	abstract void makeMap();// 読み込んだコードを連想配列にする

	public abstract float[] getSize();// 印刷するモデルのサイズを返す

	public abstract float getRequiredFilament();// フィラメントの必要量を返す

}
