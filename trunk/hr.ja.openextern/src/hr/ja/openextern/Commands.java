package hr.ja.openextern;

public class Commands {

	
	public static final String PATH_VAR = "{path}";
	
	public static final String OPEN_FOLDER_WINDOWS = "explorer "+ PATH_VAR;
	public static final String OPEN_FOLDER_LINUX_GNOME = "nautilus "+ PATH_VAR;
	public static final String OPEN_FOLDER_LINUX_KDE = "kfmclient "+ PATH_VAR;
	
	public static final String OPEN_SHELL_WINDOWS = "cmd +'"+ PATH_VAR + "'";
	public static final String OPEN_SHELL_LINUX_GNOME = "gnome-terminal --working-directory='"+ PATH_VAR + "'";
	public static final String OPEN_SHELL_LINUX_KDE = "??? "+ PATH_VAR;
	
	
	public static String parse(String command, String path) {
		if(command == null || path == null) {
			return ""+ command;
		}
		
		return command.replace(PATH_VAR, path);
	}
	
}
