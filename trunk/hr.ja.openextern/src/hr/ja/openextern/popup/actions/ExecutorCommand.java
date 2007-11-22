package hr.ja.openextern.popup.actions;

import hr.ja.openextern.Activator;

import java.io.IOException;

public class ExecutorCommand {

	
	public  static void  executeCommand(final String command) {
		try {
			Thread thread = new Thread() {
				@Override
				public void run() {
					Process exec;
					try {
						exec = Runtime.getRuntime().exec(command);
						exec.waitFor();
					} catch (Throwable e) {
						Activator.getDefault().logError("Error on command '" +command+ "'", e);
					}
				
				}
			};
			thread.start();
			
			
		} catch (Throwable e) {
			Activator.getDefault().logError("Error on command '" +command+ "'", e);
		}
	}
	
}
