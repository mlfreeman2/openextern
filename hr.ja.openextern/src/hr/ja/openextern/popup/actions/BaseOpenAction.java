package hr.ja.openextern.popup.actions;

import java.io.File;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.PlatformObject;
import org.eclipse.jdt.internal.core.JavaProject;
import org.eclipse.jdt.internal.core.Openable;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

public abstract class BaseOpenAction implements IObjectActionDelegate {

	private IWorkbenchPart targetPart;
	private ISelection selection;
	
	public boolean execCommand(String command) {
		return true;
	}
	

	public void setActivePart(IAction action, IWorkbenchPart targetPart) {

		
	}

	abstract public void run(IAction action);
		
		

	public void selectionChanged(IAction action, ISelection selection) {
		// TODO Auto-generated method stub
		
	}
	
	public static String getSelectedFolderPath(ISelection selection) {
		if (selection instanceof IStructuredSelection) {
			Object sel = ((IStructuredSelection) selection).getFirstElement();

			System.out
					.println("AbstractCommandAction.getSelectedFolderPath() sel: "
							+ sel.getClass());
			if (sel instanceof PlatformObject) {
				PlatformObject p = (PlatformObject) sel;
				IResource adapter = (IResource) p.getAdapter(IResource.class);
				IPath loc = adapter.getLocation();
				if (loc != null) {
					return loc.toOSString();
				}
			}

			if (sel instanceof IResource) {
				IResource res = (IResource) sel;
				IPath loc = res.getLocation();
				if (loc != null) {
					return loc.toOSString();
				}

			}

		}
		
		System.err.println("cant find path: ");
		return null;

		// try {
		// IAdaptable adaptable = null;
		// String selected = "unknown";
		// if (selection instanceof IStructuredSelection) {
		// adaptable = (IAdaptable) ((IStructuredSelection) selection)
		// .getFirstElement();
		// Class<? extends IAdaptable> selectedClass = adaptable.getClass();
		// if (adaptable instanceof IResource) {
		// selected = (IResource) adaptable;
		// } else if (adaptable instanceof PackageFragment
		// && ((PackageFragment) adaptable)
		// .getPackageFragmentRoot() instanceof JarPackageFragmentRoot) {
		// this.selected = getJarFile(((PackageFragment) adaptable)
		// .getPackageFragmentRoot());
		// } else if (adaptable instanceof JarPackageFragmentRoot) {
		// this.selected = getJarFile(adaptable);
		// } else {
		// this.selected = (IResource) adaptable
		// .getAdapter(IResource.class);
		// }
		// }
		// } catch (Throwable e) {
		// EasyExplorePlugin.log(e);
		// }
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
