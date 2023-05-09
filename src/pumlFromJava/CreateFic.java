package pumlFromJava;

import java.io.IOException;
import java.nio.file.Path;

import static java.nio.file.Files.createFile;

public class CreateFic {
    public static final String DEFAULT_NAME = "fichier.puml";
    public static final String DEFAULT_PATH = "./";

    private String outFileName;
    private String outFilePath;
    public String creationFichier() throws IOException {
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

        createFile(Path.of(out));
        return out;
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
