package pumlFromJava;

import java.io.FileReader;

/**
 * The type Config.
 */
public class Config {
    /**
     * Sets config.
     *
     * @param path       the path
     * @param saveOption the save option
     */
    public static void setConfig(String path, SaveOption saveOption) {
        try {
            FileReader reader = new FileReader(path);
            int character;
            String str = "";
            while ((character = reader.read()) != -1) {
                str += (char) character;
            }
            String[] lines = str.split("\n");
            for (String line : lines) {
                String[] words = line.split(": ");
                if (words[0].equals("typeDiagram")) {
                    saveOption.setTypeDiagram(words[1]);
                } else if (words[0].equals("strPrimitive")) {
                    saveOption.setStrPrimitive(Boolean.parseBoolean(words[1]));
                } else if (words[0].equals("association")) {
                    saveOption.setAssociation(Boolean.parseBoolean(words[1]));
                } else if (words[0].equals("constructor")) {
                    saveOption.setConstructor(Boolean.parseBoolean(words[1]));
                } else if (words[0].equals("method")) {
                    saveOption.setMethod(Boolean.parseBoolean(words[1]));
                } else if (words[0].equals("drawPrimitive")) {
                    saveOption.setDrawPrimitive(Boolean.parseBoolean(words[1]));
                } else if (words[0].equals("drawUnPrimitive")) {
                    saveOption.setDrawUnPrimitive(Boolean.parseBoolean(words[1]));
                } else if (words[0].equals("drawExtends")) {
                    saveOption.setDrawExtends(Boolean.parseBoolean(words[1]));
                } else if (words[0].equals("drawImplements")) {
                    saveOption.setDrawImplements(Boolean.parseBoolean(words[1]));
                } else if (words[0].equals("drawUses")) {
                    saveOption.setDrawUse(Boolean.parseBoolean(words[1]));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
