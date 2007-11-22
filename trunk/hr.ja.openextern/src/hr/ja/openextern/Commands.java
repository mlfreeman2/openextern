package hr.ja.openextern;

import java.text.ParseException;

public class Commands {

	
	public static final String PATH_VAR = "{path}";
	
	public static final String OPEN_FOLDER_WINDOWS = "explorer "+ PATH_VAR + "";
	public static final String OPEN_FOLDER_LINUX_GNOME = "nautilus "+ PATH_VAR + "";
	public static final String OPEN_FOLDER_LINUX_KDE = "kfmclient "+ PATH_VAR + "";
	
	public static final String OPEN_SHELL_WINDOWS = "cmd +"+ PATH_VAR + "";
	
	/**
	 * "color xterm," "regular xterm," and "gnome-terminal" 
	 */
	public static final String OPEN_SHELL_LINUX_GNOME = "gnome-terminal --working-directory="+ PATH_VAR + "";
	
	/**
	 * konsole" and "terminal"
	 */
	public static final String OPEN_SHELL_LINUX_KDE = "konsole "+ PATH_VAR + "";
	
	
	public static String parse(String command, String path) throws ParseException {
		if(command == null || path == null) {
			throw new ParseException("Nemoze se parsirati: "+ command, 0);
		}
		if(!command.contains(PATH_VAR)) {
			throw new ParseException("Komanda ne sadrzi string: " +PATH_VAR+ ", command:"+ command, 0);
		}
		return command.replace(PATH_VAR, path);
	}
	
}
