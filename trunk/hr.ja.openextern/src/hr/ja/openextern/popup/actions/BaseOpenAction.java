package hr.ja.openextern.popup.actions;

import hr.ja.openextern.Activator;
import hr.ja.openextern.Commands;
import hr.ja.openextern.OS;

import java.io.File;
import java.text.ParseException;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.mapping.ResourceMapping;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.PlatformObject;
import org.eclipse.jdt.core.IJarEntryResource;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.pde.core.plugin.IPluginModel;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.model.IWorkbenchAdapter;

public abstract class BaseOpenAction implements IObjectActionDelegate {

	private IWorkbenchPart targetPart;
	private ISelection selection;

	public boolean execCommand(String command, String openName) {

		String selectedPath = getSelectedFolderPath(selection);
		
		if (selectedPath == null) {
			MessageDialog
					.openError(targetPart.getSite().getShell(),
							"Error "  + openName,
							"Path is not find");
			return false;
		}
		File file = new File(selectedPath);
		if (!file.exists()) {
			MessageDialog.openError(targetPart.getSite().getShell(),
					"Error " + openName, "Path is not find: "
							+ file);
			return false;
		}

		if (file.isFile()) {
			selectedPath = file.getParent();
		}

		try {
			String parseCommand = "";
			// if (Activator.getDefault().getInitPlugin().getOS() != OS.WINDOWS)
			// {
			parseCommand = Commands.parse(command, selectedPath);

			ExecutorCommand
					.executeCommand(parseCommand, new File(selectedPath));
			return true;
		} catch (ParseException e) {
			MessageDialog.openError(targetPart.getSite().getShell(),
					"Error " + openName, e.getMessage());
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

	/**
	 * 
	 * May return null!!
	 * @param selection
	 * @return full path 
	 */
	public String getSelectedFolderPath(ISelection selection) {
		
		TreeSelection d;
		
		
		if (selection instanceof IStructuredSelection) {
			Object sel = ((IStructuredSelection) selection).getFirstElement();
//
//			System.out
//					.println("AbstractCommandAction.getSelectedFolderPath() sel: "
//							+ sel.getClass());
			if (sel instanceof PlatformObject) {
				PlatformObject p = (PlatformObject) sel;
				IResource resource = (IResource) p.getAdapter(IResource.class);
				if (resource != null)
					return getPath(resource);
			}

			if (sel instanceof IResource) {
				IResource res = (IResource) sel;
				if (res != null)
					return getPath(res);

			}
			
			if(sel instanceof IJavaElement) {
				IJavaElement je = (IJavaElement) sel;
				try {
					IResource correspondingResource = je.getCorrespondingResource();
					if(correspondingResource != null)
					getPath(correspondingResource);
				} catch (JavaModelException ignore) {
				}
			}
			
			if(sel instanceof IPluginModel) {
				IPluginModel mod = (IPluginModel) sel;
				String installLocation = mod.getInstallLocation();
				return installLocation;
			}
			if(sel instanceof IJarEntryResource) {
				IJarEntryResource jar = (IJarEntryResource) sel;
				 IPath fullPath = jar.getFullPath();
				
				 return fullPath.makeAbsolute().toOSString();
			}
			
			if (sel instanceof IAdaptable) {
				IAdaptable ad = (IAdaptable) sel;
				
				IResource resource = (IResource) ad.getAdapter(IResource.class);
				if (resource != null) {
					return getPath(resource);
				}

			}
			Activator.getDefault().logError("Can't find path, not implemented " + sel.getClass(), null);
			return null;
		}
		
		Activator.getDefault().logError("Can't find path, not implemented " + selection.getClass(), null);
		// System.err.println("cant find path: ");
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
