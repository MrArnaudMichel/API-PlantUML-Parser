package pumlFromJava;

import java.io.File;
import java.io.IOException;

/**
 * Classe CreateFile
 * <p>
 * Classe qui permet de créer un fichier
 * </p>
 * <br>
 * Elle possède les méthodes :
 *   <ul>
 *       <li>creationFichier</li>
 *       <li>createFile</li>
 *       <li>getOutFileName</li>
 *       <li>setOutFileName</li>
 *       <li>getOutFilePath</li>
 *       <li>setOutFilePath</li>
 *  </ul>
 *  <br>
 *  Elle possède les attributs :
 *  <ul>
 *      <li>outFileName</li>
 *      <li>outFilePath</li>
 *  </ul>
 *  <br>
 *  Elle possède les constantes :
 *  <ul>
 *      <li>DEFAULT_NAME</li>
 *      <li>DEFAULT_PATH</li>
 * </ul>
 */
public class CreateFile {
    /**
     * The constant DEFAULT_NAME.
     */
    public static final String DEFAULT_NAME = "fichier";
    /**
     * The constant DEFAULT_PATH.
     */
    public static final String DEFAULT_PATH = "./";

    private String outFileName = null;
    private String outFilePath = null;

    /**
     * Méthode qui permet de créer un fichier
     *
     * @return String string
     * @throws IOException IOException
     */
    public String creationFichier() throws IOException {
        System.out.println("outFileName = " + outFileName);
        System.out.println("outFilePath = " + outFilePath);
        if (outFileName == null) {
            outFileName = DEFAULT_NAME;
        } else if (!outFileName.endsWith(".puml")) {
            outFileName += ".puml";
        }
        if (outFilePath == null) {
            outFilePath = DEFAULT_PATH;
        } else if (!outFilePath.endsWith("/")) {
            outFilePath += "/";
        }
        String out = outFilePath + outFileName;

        createFile(out);
        return out;
    }

    /**
     * Méthode qui permet de créer un fichier
     *
     * @param fileName String
     */
    public void createFile(String fileName) {
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

    /**
     * Méthode qui permet de récupérer le nom du fichier
     *
     * @return String out file name
     */
    public String getOutFileName() {
        return outFileName;
    }

    /**
     * Méthode qui permet de modifier le nom du fichier
     *
     * @param outFileName String
     */
    public void setOutFileName(String outFileName) {
        this.outFileName = outFileName;
    }

    /**
     * Méthode qui permet de récupérer le chemin du fichier
     *
     * @return String out file path
     */
    public String getOutFilePath() {
        return outFilePath;
    }

    /**
     * Méthode qui permet de modifier le chemin du fichier
     *
     * @param outFilePath String
     */
    public void setOutFilePath(String outFilePath) {
        this.outFilePath = outFilePath;
    }

}
