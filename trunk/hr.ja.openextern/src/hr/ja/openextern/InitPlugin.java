package hr.ja.openextern;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class InitPlugin {

	private static final String OS_NAME = getSystemProperty("os.name");
	private static final boolean IS_OS_LINUX = getOSMatches("Linux")
			|| getOSMatches("LINUX");
	private static final boolean IS_OS_MAC = getOSMatches("Mac");
	private static final boolean IS_OS_WINDOWS = getOSMatches("Windows");

	private Desktop desktop = null;

	public InitPlugin() {
		if(IS_OS_LINUX) {
			this.desktop = getLinuxDesktop();
		}
	}
	
	
	public Desktop getDesktop() {
		return desktop;
	}

	public OS getOS() {
		if (IS_OS_LINUX) {
			return OS.LINUX;
		}
		if (IS_OS_WINDOWS) {
			return OS.WINDOWS;
		}
//		if (IS_OS_MAC) {
//			return OS.MAC;
//		}
		return OS.UNKNOWN;
	}

	private Desktop getLinuxDesktop() {

		Process exec2;
		BufferedReader r = null;
		try {
			exec2 = Runtime.getRuntime().exec("ps ax");
			exec2.waitFor();
			InputStream inputStream = exec2.getInputStream();

			BufferedInputStream in = new BufferedInputStream(inputStream);
			r = new BufferedReader(new InputStreamReader(in));

			String l = r.readLine();
			while (l != null) {
				if (l.contains("kwin")) {
					return Desktop.KDE;
				}
				if (l.contains("gnome-settings-daemon")) {
					return Desktop.GNOME;
				}
				l = r.readLine();
			}
		} catch (Throwable e) {
			Activator.getDefault().logError("ps aux command error", e);
		} finally {
			if (r != null)
				try {
					r.close();
				} catch (IOException e) {
					Activator.getDefault().logError("ps aux command error", e);
				}
		}
		return Desktop.UNKNOWN;
	}

	private static String getSystemProperty(String property) {
		try {
			return System.getProperty(property);
		} catch (SecurityException ex) {
			// we are not allowed to look at this property
			String msg = "Caught a SecurityException reading the system property '"
					+ property + "'; the property value will default to null.";
			Activator.getDefault().logError(msg, ex);
			return null;
		}
	}

	/**
	 * <p>
	 * Decides if the operating system matches.
	 * </p>
	 * 
	 * @param osNamePrefix
	 *            the prefix for the os name
	 * @return true if matches, or false if not or can't determine
	 */
	private static boolean getOSMatches(String osNamePrefix) {
		if (OS_NAME == null) {
			return false;
		}
		return OS_NAME.startsWith(osNamePrefix);
	}

}
