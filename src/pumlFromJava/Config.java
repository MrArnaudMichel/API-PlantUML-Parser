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
                switch (words[0]) {
                    case "typeDiagram" -> saveOption.setTypeDiagram(words[1].trim());
                    case "strPrimitive" -> saveOption.setStrPrimitive(words[1].trim().equals("true"));
                    case "association" -> saveOption.setAssociation(words[1].trim().equals("true"));
                    case "constructor" -> saveOption.setConstructor(words[1].trim().equals("true"));
                    case "method" -> saveOption.setMethod(words[1].trim().equals("true"));
                    case "drawPrimitive" -> saveOption.setDrawPrimitive(words[1].trim().equals("true"));
                    case "drawUnPrimitive" -> saveOption.setDrawUnPrimitive(words[1].trim().equals("true"));
                    case "drawExtends" -> saveOption.setDrawExtends(words[1].trim().equals("true"));
                    case "drawImplements" -> saveOption.setDrawImplements(words[1].trim().equals("true"));
                    case "drawUses" -> saveOption.setDrawUse(words[1].trim().equals("true"));
                }
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
