package hr.ja.openextern;

import java.io.File;
import java.io.IOException;

public class Proba {
	public static void main(String[] args) {
		try {
			Runtime.getRuntime().exec("cmd.exe /C start", null, new File("c:/"));
			System.out.println("finish");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
