package scheduleGenerator;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class SerFileFilter{
	 
    //Accept only .ser files
    public static boolean accept(File f) {
        if (f.isDirectory()) {
            return false;
        }
        String extension = getExtension(f);
        if (extension != null) {
            if (extension.equals("ser")){
                    return true;
            } else {
                return false;
            }
        }
 
        return false;
    }
    
    private static String getExtension(File f) {
        String ext = null;
        String s = f.getName();
        int i = s.lastIndexOf('.');

        if (i > 0 &&  i < s.length() - 1) {
            ext = s.substring(i+1).toLowerCase();
        }
        return ext;
    }

}