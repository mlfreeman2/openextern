package hr.ja.openextern.popup.actions;

import hr.ja.openextern.Activator;
import hr.ja.openextern.Commands;
import hr.ja.openextern.OS;

import java.io.File;
import java.text.ParseException;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.PlatformObject;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

public abstract class BaseOpenAction implements IObjectActionDelegate {

	private IWorkbenchPart targetPart;
	private ISelection selection;

	public boolean execCommand(String command, String openName) {

		String selectedPath = getSelectedFolderPath(selection);
		if (selectedPath == null) {
			MessageDialog
					.openError(targetPart.getSite().getShell(),
							"Error kod otvaranja " + openName,
							"Nemogu otvoriti folder");
			return false;
		}
		File file = new File(selectedPath);
		if (!file.exists()) {
			MessageDialog.openError(targetPart.getSite().getShell(),
					"Error kod otvaranja " + openName, "Path ne postoji: "
							+ file);
			return false;
		}

		if (file.isFile()) {
			selectedPath = file.getParent();
		}

		try {
			String parseCommand = "";
//			if (Activator.getDefault().getInitPlugin().getOS() != OS.WINDOWS) {
				parseCommand = Commands.parse(command, selectedPath);
				
			ExecutorCommand
					.executeCommand(parseCommand, new File(selectedPath));
			return true;
		} catch (ParseException e) {
			MessageDialog.openError(targetPart.getSite().getShell(),
					"Error kod otvaranja " + openName, e.getMessage());
			return false;
		}

	}

	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		this.targetPart = targetPart;

	}

	abstract public void run(IAction action);

	public void selectionChanged(IAction action, ISelection selection) {
		this.selection = selection;
	}

	public String getSelectedFolderPath(ISelection selection) {
		if (selection instanceof IStructuredSelection) {
			Object sel = ((IStructuredSelection) selection).getFirstElement();

			System.out
					.println("AbstractCommandAction.getSelectedFolderPath() sel: "
							+ sel.getClass());
			if (sel instanceof PlatformObject) {
				PlatformObject p = (PlatformObject) sel;
				IResource resource = (IResource) p.getAdapter(IResource.class);
				return getPath(resource);
			}

			if (sel instanceof IResource) {
				IResource res = (IResource) sel;
				return getPath(res);

			}
			if (sel instanceof IAdaptable) {
				IAdaptable ad = (IAdaptable) sel;
				IResource resource = (IResource) ad.getAdapter(IResource.class);
				if (resource != null) {
					return getPath(resource);
				}

			}

		}

		System.err.println("cant find path: ");
		return null;
	}

	private String getPath(IResource resource) {
		IPath loc = resource.getLocation();
		if (loc != null) {
			return loc.toOSString();
		}
		return null;
	}

	protected File getJarFile(IAdaptable adaptable) {
		// JarPackageFragmentRoot jpfr = (JarPackageFragmentRoot) adaptable;
		// File selected = (File) jpfr.getPath().makeAbsolute().toFile();
		// if (!((File) selected).exists()) {
		// File projectFile = new File(jpfr.getJavaProject().getProject()
		// .getLocation().toOSString());
		// selected = new File(projectFile.getParent() + selected.toString());
		// }
		// return selected;
		return null;
	}

}
