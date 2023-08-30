package Printer;

//実行した内容をlogファイルにするクラス
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Log {
	// ログの名前を実行時の時間にする．
	private File logFile;
	private String logName = null;

	// コンストラクタ
	public Log() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
		String logName = format.format(new Date()) + ".log";// 時間をログファイルの名前にする．
		logFile = new File(".\\src\\log\\" + logName);
	}

	// logを書き込むためのファイルを作る
	public void writeLog(String line) {
		FileWriter fw = null;
		BufferedWriter bw = null;

		if (logFile.exists()) {
			try {
				fw = new FileWriter(logFile, true);
				bw = new BufferedWriter(fw);
				bw.write(line);
				bw.newLine();
				bw.flush();

			} catch (FileNotFoundException e) {
				System.err.println("ファイルがありません");
			} catch (IOException e) {
				System.err.println("IO Error");
			} finally {
				try {
					bw.close();
				} catch (IOException e) {
				}
			}

		} else {
			createLog();
		}

	}

	// 新しくログファイルを作成する．
	private void createLog() {
		try {
			logFile.createNewFile();
		} catch (IOException e) {
		}
	}

	// ログファイルの名前を返す．
	public String getLogName() {
		if (logName != null) {
			return logName;
		} else
			return null;
	}
}
