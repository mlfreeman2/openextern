package hr.ja.openextern.popup.actions;

import hr.ja.openextern.Activator;

import java.io.File;
import java.io.IOException;

public class ExecutorCommand {

	public static void executeCommand(final String command, final File dir) {
		try {
			Thread thread = new Thread() {
				@Override
				public void run() {
					Process exec;
					try {
						System.out.println("run command: " + command);
						Runtime r = Runtime.getRuntime();

						exec = r.exec(command, null, dir);
						exec.waitFor();
					} catch (Throwable e) {
						Activator.getDefault().logError(
								"Error on command '" + command + "'", e);
					}

				}
			};
			thread.start();

		} catch (Throwable e) {
			Activator.getDefault().logError(
					"Error on command '" + command + "'", e);
		}
	}

}
