package hr.ja.openextern.popup.actions;

import java.io.File;

import hr.ja.openextern.Activator;
import hr.ja.openextern.Commands;
import hr.ja.openextern.preferences.PreferenceConstants;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

public class OpenFolderAction implements IObjectActionDelegate {
	private ISelection selection;
private IWorkbenchPart targetPart;

	/**
	 * Constructor for Action1.
	 */
	public OpenFolderAction() {
		super();
	}

	/**
	 * @see IObjectActionDelegate#setActivePart(IAction, IWorkbenchPart)
	 */
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		this.targetPart = targetPart;
	}

	/**
	 * @see IActionDelegate#run(IAction)
	 */
	public void run(IAction action) {
		String commandOpenFolder = Activator.getDefault().getPreferenceStore().getString(PreferenceConstants.P_OPEN_FOLDER);
		String selectedPath = BaseOpenAction.getSelectedFolderPath(selection);
		if(selectedPath == null) {
			MessageDialog.openError(targetPart.getSite().getShell(), "Error kod otvaranja foldera", "Nemogu otvoriti folder");
			
		}
		File file = new File(selectedPath);
		if(!file.exists()) {
			
		}
		ExecutorCommand.executeCommand(Commands.parse(commandOpenFolder, selectedPath));
	}

	/**
	 * @see IActionDelegate#selectionChanged(IAction, ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection) {
		this.selection = selection;
	}

}
