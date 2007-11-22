package hr.ja.openextern.popup.actions;

import hr.ja.openextern.Activator;
import hr.ja.openextern.Commands;
import hr.ja.openextern.preferences.PreferenceConstants;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

public class OpenShellAction extends BaseOpenAction{

	
	public OpenShellAction() {
		
	}


	public void run(IAction action) {
		String commandOpenFolder = Activator.getDefault().getPreferenceStore().getString(PreferenceConstants.P_OPEN_SHELL);
	
		execCommand(commandOpenFolder, "shell");
	}


}
