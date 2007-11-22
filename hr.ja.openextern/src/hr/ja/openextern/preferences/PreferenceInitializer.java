package hr.ja.openextern.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

import hr.ja.openextern.Activator;
import hr.ja.openextern.Commands;
import hr.ja.openextern.Desktop;
import hr.ja.openextern.InitPlugin;
import hr.ja.openextern.OS;

/**
 * Class used to initialize default preference values.
 */
public class PreferenceInitializer extends AbstractPreferenceInitializer {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer#initializeDefaultPreferences()
	 */
	public void initializeDefaultPreferences() {
		IPreferenceStore store = Activator.getDefault().getPreferenceStore();
		store.setDefault(PreferenceConstants.P_OPEN_FOLDER,
				getOpenFolderCommand());
		store.setDefault(PreferenceConstants.P_OPEN_SHELL,
				getOpenShellCommand());
	}

	private String getOpenFolderCommand() {

		InitPlugin initPlugin = Activator.getDefault().getInitPlugin();
		if (initPlugin.getOS() == OS.LINUX) {
			if (initPlugin.getDesktop() == Desktop.GNOME) {
				return Commands.OPEN_FOLDER_LINUX_GNOME;
			}

			if (initPlugin.getDesktop() == Desktop.KDE) {
				return Commands.OPEN_FOLDER_LINUX_KDE;
			}

		} else if (initPlugin.getOS() == OS.WINDOWS) {
			return Commands.OPEN_FOLDER_WINDOWS;
		}

		return "unknown";
	}

	private String getOpenShellCommand() {
		InitPlugin initPlugin = Activator.getDefault().getInitPlugin();
		if (initPlugin.getOS() == OS.LINUX) {
			if (initPlugin.getDesktop() == Desktop.GNOME) {
				return Commands.OPEN_SHELL_LINUX_GNOME;
			}

			if (initPlugin.getDesktop() == Desktop.KDE) {
				return Commands.OPEN_SHELL_LINUX_KDE;
			}

		} else if (initPlugin.getOS() == OS.WINDOWS) {
			return Commands.OPEN_SHELL_WINDOWS;
		}

		return "unknown";
	}
}
