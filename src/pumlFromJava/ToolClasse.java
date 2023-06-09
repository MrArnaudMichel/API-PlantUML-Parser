package pumlFromJava;

import java.util.ArrayList;

public class ToolClasse {

    /**
     * Liste des types primitifs
     */
    public static final ArrayList<String> primitiveTypes = new ArrayList<String>() {{
        add("Byte");
        add("Short");
        add("Int");
        add("Long");
        add("Float");
        add("Double");
        add("Boolean");
        add("Char");
    }};


    /**
     *
     * @param type String
     * @return String array
     */
    public static String setUmlType(String type) {
        type = type.split("\\.")[type.split("\\.").length - 1];
        if (type.contains(")")) {
            type = type.split("\\)")[1];
        }
        if (type.equals("void")) {
            type = "";
        } else if (type.equals("int")) {
            type = "Integer";
        } else if (type.equals("boolean")) {
            type = "Boolean";
        } else if (type.equalsIgnoreCase("float")) {
            type = "Float";
        } else if (type.equalsIgnoreCase("double")) {
            type = "Reel";
        } else if (type.equalsIgnoreCase("short")) {
            type = "Short";
        } else if (type.equalsIgnoreCase("long")) {
            type = "Long";
        } else if (type.equalsIgnoreCase("byte")) {
            type = "Byte";
        } else if (type.equalsIgnoreCase("char")) {
            type = "Character";
        }
        if (!type.equals("") && type.charAt(type.length() - 1) == '>') {
            type = type.substring(0, type.length() - 1);
            type += "[*]";
        } else if (type.contains("[")){
            type = type.split("\\[")[0];
            type += "[*]";
        }
        return type;
    }
}
