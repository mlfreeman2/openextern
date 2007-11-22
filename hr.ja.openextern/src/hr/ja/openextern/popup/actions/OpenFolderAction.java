package hr.ja.openextern.popup.actions;

import hr.ja.openextern.Activator;
import hr.ja.openextern.preferences.PreferenceConstants;

import org.eclipse.jface.action.IAction;
import org.eclipse.ui.IActionDelegate;

public class OpenFolderAction extends BaseOpenAction {

	public OpenFolderAction() {
		super();
	}



	/**
	 * @see IActionDelegate#run(IAction)
	 */
	public void run(IAction action) {
		String commandOpenFolder = Activator.getDefault().getPreferenceStore().getString(PreferenceConstants.P_OPEN_FOLDER);
		execCommand(commandOpenFolder, "folder");
	}

	
}
