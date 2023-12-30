package sed.lab13;

import java.io.File;
import java.io.FilenameFilter;

public class Find
{
    public String[] list;

    public Find(String directory, String extension)
    {
        File dir = new File(directory);

        if (dir.exists())
        {
            list = dir.list(new FileFilter(extension));
        }
    }

    protected static class FileFilter implements FilenameFilter
    {
        String extension = null;

        FileFilter(String extension) {
            this.extension = "." + extension;
        }
        public boolean accept(File d, String name)
        {
            return name.endsWith(extension);
        }
    }
}
