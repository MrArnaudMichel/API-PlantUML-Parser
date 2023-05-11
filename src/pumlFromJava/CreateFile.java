package pumlFromJava;

import java.io.File;
import java.io.IOException;

public class CreateFile {
    public static final String DEFAULT_NAME = "fichier";
    public static final String DEFAULT_PATH = "./";

    private String outFileName = null;
    private String outFilePath = null;
    public String creationFichier() throws IOException {
        System.out.println("outFileName = " + outFileName);
        System.out.println("outFilePath = " + outFilePath);
        if (outFileName == null)
        {
            outFileName = DEFAULT_NAME;
        }
        else if (!outFileName.endsWith(".puml"))
        {
            outFileName += ".puml";
        }
        if (outFilePath == null)
        {
            outFilePath = DEFAULT_PATH;
        }
        else if (!outFilePath.endsWith("/"))
        {
            outFilePath += "/";
        }
        String out = outFilePath + outFileName;

        createFile(out);
        return out;
    }

    public void createFile(String fileName)
    {
        try {
            File myObj = new File(fileName);
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public String getOutFileName() {
        return outFileName;
    }

    public void setOutFileName(String outFileName) {
        this.outFileName = outFileName;
    }

    public String getOutFilePath() {
        return outFilePath;
    }

    public void setOutFilePath(String outFilePath) {
        this.outFilePath = outFilePath;
    }

}
