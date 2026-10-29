package org.example;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DirectoryJsonVisitor implements DirectoryVisitor {

    private List<File> jsonFiles = new ArrayList<>();

    private String getFileExtension(File file)
    {
        if (file.isDirectory())
        {
            throw new IllegalArgumentException("not a file");
        }

        String fileName = file.getName();
        int indexDot = fileName.lastIndexOf(".");

        if (indexDot < 0 )
        {
            return "";
        }

        return fileName.substring(indexDot);



    }


    @Override
    public void enterDirectory(File dir) {

    }

    @Override
    public void leaveDirectory(File dir) {

    }

    @Override
    public void visitFile(File file) {
        String ext = getFileExtension(file);

        if (".json".equals(ext))
        {
            jsonFiles.add(file);
        }



    }


    public List<File> getJsonFiles() {
        return jsonFiles;
    }
}
