package pumlFromJava.classes;

import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.VariableElement;
import java.util.ArrayList;

/**
 * Classe Methode
 * <p>
 * Classe qui permet de créer une méthode
 * d'une classe
 * <br>
 * Elle implémente l'interface Type
 * <br>
 * Elle possède les méthodes :
 * <ul>
 *     <li>strDraw</li>
 *     <li>setArray</li>
 *     <li>getName</li>
 *     <li>getReturnType</li>
 *     <li>getVisibility</li>
 *     <li>getParameters</li>
 *     <li>setName</li>
 *     <li>setReturnType</li>
 *     <li>setVisibility</li>
 *     <li>setParameters</li>
 *     <li>toString</li>
 * </ul>
 * <br>
 * Elle possède les attributs :
 * <ul>
 *     <li>name</li>
 *     <li>returnType</li>
 *     <li>visibility</li>
 *     <li>parameters</li>
 * </ul>
 */
public class Methode implements Type {
    private final ArrayList<String[]> parameters = new ArrayList<String[]>();
    private String name;
    private String returnType;
    private String visibility;
    private boolean isOverride;

    /**
     * Constructeur de la classe Methode
     *
     * @param e Element
     */
    public Methode(Element e) {
        setName(e.getSimpleName().toString());
        String returnType = e.asType().toString().split("\\.")[e.asType().toString().split("\\.").length - 1];
        returnType = setArray(returnType);
        setReturnType(returnType);
        setVisibility(e.getModifiers().toString());
        String[] tab = new String[2];
        for (VariableElement parameter : ((ExecutableElement) e).getParameters()) {
            tab[0] = parameter.getSimpleName().toString();
            tab[1] = setArray(parameter.asType().toString());
            parameters.add(tab.clone());
            tab[0] = "";
            tab[1] = "";
        }
        setIsOverride(e.getAnnotation(Override.class) != null);

    }

    /**
     * Constructeur de la classe Methode
     *
     * @param type String
     * @return String array
     */
    public static String setArray(String type) {
        type = type.split("\\.")[type.split("\\.").length - 1];
        if (type.contains(")")) {
            type = type.split("\\)")[1];
            if (type.equals("void")) {
                type = "";
            } else if (type.equals("int")) {
                type = "Integer";
            } else if (type.equals("boolean")) {
                type = "Boolean";
            } else if (type.equalsIgnoreCase("float")) {
                type = "Reel";
            } else if (type.equalsIgnoreCase("double")) {
                type = "Reel";
            }
        }
        if (!type.equals("") && type.charAt(type.length() - 1) == '>') {
            type = type.substring(0, type.length() - 1);
            type += "[*]";
        }
        return type;
    }

    /**
     * Méthode qui permet de dessiner une méthode
     *
     * @return String string
     */
    public String strDraw() {
        StringBuilder str = new StringBuilder();
        str.append(writeType()).append(" ").append(getName()).append("(");
        if (getIsOverride()){
            str.append("Override");
        }
        else {
            for (String[] parameter : parameters) {
                str.append(parameter[0]).append(" : ").append(parameter[1]);
                if (parameters.indexOf(parameter) != parameters.size() - 1 && parameters.indexOf(parameter) != parameters.size()) {
                    str.append(", ");
                }
            }
        }
        str.append(")");
        if (!getReturnType().equals("")) {
            str.append(" : ");
            str.append(getReturnType());
        }
        return str.toString();
    }

    /**
     * Méthode qui permet de mettre le type de la méthode sous forme uml
     *
     * @return String
     */
    private String writeType() {
        StringBuilder str = new StringBuilder();
        if (this.isPublic(getVisibility())) {
            str.append("+");
        } else if (this.isPrivate(getVisibility())) {
            str.append("-");
        } else if (this.isProtected(getVisibility())) {
            str.append("#");
        }
        if (this.isAbstract(getVisibility())) {
            str.append(" {abstract}");
        } else if (this.isStatic(getVisibility())) {
            str.append(" {static}");
        }
        return str.toString();
    }

    /**
     * Méthode qui permet renvoie True si la méthode est Override
     *
     * @return String is override
     */
    public boolean getIsOverride() {
        return isOverride;
    }

    /**
     * Méthode qui permet de mettre la méthode Override
     *
     * @param isOverride String
     */
    public void setIsOverride(boolean isOverride) {
        this.isOverride = isOverride;
    }

    /**
     * Méthode qui permet de récupérer les parameters d'une méthode
     *
     * @return String parameters
     */
    public ArrayList<String[]> getParameters() {
        return parameters;
    }

    /**
     * Méthode qui permet de récupérer le nom d'une méthode
     *
     * @return String name
     */
    public String getName() {
        return name;
    }

    /**
     * Procédure qui permet de mettre le nom d'une méthode
     *
     * @param name String
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Méthode qui permet de récupérer le type de retour d'une méthode
     *
     * @return String return type
     */
    public String getReturnType() {
        return returnType;
    }

    /**
     * Procédure qui permet de mettre le type de retour d'une méthode
     *
     * @param returnType String
     */
    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }

    /**
     * Méthode qui permet de récupérer la visibilité d'une méthode
     *
     * @return String
     */
    @Override
    public String getVisibility() {
        return visibility;
    }

    /**
     * Procédure qui permet de mettre la visibilité d'une méthode
     *
     * @param visibility String
     */
    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }
}
