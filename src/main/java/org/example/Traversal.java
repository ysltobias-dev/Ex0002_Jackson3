package org.example;

import java.io.File;

public class Traversal {

    public static void traversal(File directory,DirectoryVisitor visitor)
    {
        if (!directory.isDirectory())
        {
            throw new IllegalArgumentException(String.format("%s is not a valid dir",directory.toString()));
        }

        visitor.enterDirectory(directory);

        var files = directory.listFiles();
        for(var file : files)
        {
            if (file.isDirectory())
            {
                traversal(file,visitor);
            }
            else {
                visitor.visitFile(file);
            }


        }

        visitor.leaveDirectory(directory);

    }

}
