package hr.ja.openextern.popup.actions;

import hr.ja.openextern.Activator;
import hr.ja.openextern.Commands;
import hr.ja.openextern.preferences.PreferenceConstants;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

public class OpenShellAction implements IObjectActionDelegate {

	private ISelection selection;
	
	public OpenShellAction() {
		
	}

	public void setActivePart(IAction action, IWorkbenchPart targetPart) {

		
	}

	public void run(IAction action) {
		String commandOpenFolder = Activator.getDefault().getPreferenceStore().getString(PreferenceConstants.P_OPEN_SHELL);
		ExecutorCommand.executeCommand(Commands.parse(commandOpenFolder, BaseOpenAction.getSelectedFolderPath(selection)));
		
	}

	public void selectionChanged(IAction action, ISelection selection) {
		this.selection = selection;
	
	}

}
