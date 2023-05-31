package pumlFromJava.classes;

import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.VariableElement;
import java.util.ArrayList;

/**
 * Classe Contructor
 * <p>
 * Classe qui permet de créer un constructeur
 * d'une classe
 * <br>
 * Elle implémente l'interface Type
 * <br>
 * Elle possède les méthodes :
 *  <ul>
 *      <li>strDraw</li>
 *      <li>typeConstructor</li>
 *      <li>getName</li>
 *      <li>getVisibility</li>
 *      <li>setName</li>
 *      <li>setVisibility</li>
 *      <li>getParameters</li>
 *      <li>setParameters</li>
 * </ul>
 * <br>
 * Elle possède les attributs :
 * <ul>
 *     <li>name</li>
 *     <li>visibility</li>
 *     <li>parameters</li>
 * </ul>
 */
public class Contructor implements Type {
    private String name;
    private String visibility;

    /**
     * @pumlNameAssociation est fait de
     */
    private ArrayList<String[]> parameters = new ArrayList<String[]>();

    /**
     * Constructeur de la classe Contructor
     *
     * @param e    Element
     * @param name String
     */
    public Contructor(Element e, String name) {
        setName(name);
        setVisibility(e.getModifiers().toString());
        String[] tab = new String[2];
        for (VariableElement parameter : ((ExecutableElement) e).getParameters()) {
            tab[0] = parameter.getSimpleName().toString();
            tab[1] = parameter.asType().toString().split("\\.")[parameter.asType().toString().split("\\.").length - 1];
            parameters.add(tab.clone());
        }
    }

    /**
     * Méthode qui permet de dessiner le constructeur
     *
     * @return String string
     */
    public String strDraw() {
        StringBuilder str = new StringBuilder();
        str.append(typeConstructor(getVisibility())).append("<<Create>>").append(" ").append(getName()).append("(");
        for (String[] parameter : parameters) {
            String type = parameter[1];
            if (type.equals("int")) {
                type = "Integer";
            } else if (type.equals("boolean")) {
                type = "Boolean";
            } else if (type.equals("float")) {
                type = "Float";
            } else if (type.equals("double")) {
                type = "Double";
            } else if (type.charAt(type.length() - 1) == '>') {
                type = type.substring(0, type.length() - 1);
                type += "[*]";
            }
            str.append(parameter[0]).append(" : ").append(type);
            if (parameters.indexOf(parameter) != parameters.size() - 1) {
                str.append(", ");
            }
        }
        str.append(")");
        return str.toString();
    }

    /**
     * Méthode qui permet de définir le type du constructeur
     *
     * @param visibility String
     * @return String
     */
    private String typeConstructor(String visibility) {
        if (this.isPublic(visibility)) {
            return "+";
        } else if (this.isPrivate(visibility)) {
            return "-";
        } else if (this.isProtected(visibility)) {
            return "#";
        }
        return "";
    }

    /**
     * Méthode qui permet de récupérer le nom du constructeur
     *
     * @return String name
     */
    public String getName() {
        return name;
    }

    /**
     * Méthode qui permet de définir le nom du constructeur
     *
     * @param name String
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Méthode qui permet de récupérer la visibilité du constructeur
     *
     * @return String
     */
    public String getVisibility() {
        return visibility;
    }

    /**
     * Méthode qui permet de définir la visibilité du constructeur
     *
     * @param visibility String
     */
    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    /**
     * Méthode qui permet de récupérer les paramètres du constructeur
     *
     * @return ArrayList<String [ ]> parameters
     */
    public ArrayList<String[]> getParameters() {
        return parameters;
    }

    /**
     * Méthode qui permet de définir les paramètres du constructeur
     *
     * @param parameters ArrayList<String[]>
     */
    public void setParameters(ArrayList<String[]> parameters) {
        this.parameters = parameters;
    }
}
