package pumlFromJava.classes;

import pumlFromJava.SaveOption;

import javax.lang.model.element.Element;
import java.util.ArrayList;

/**
 * Classe Enumerations
 * <p>
 * Classe qui permet de créer une énumération
 * d'une classe
 * <br>
 * Elle possède les méthodes :
 *   <ul>
 *       <li>strDraw</li>
 *       <li>getName</li>
 *       <li>setName</li>
 *       <li>getNamePackage</li>
 *       <li>setNamePackage</li>
 *       <li>getAttributes</li>
 *       <li>setAttributes</li>
 *  </ul>
 *  <br>
 *  Elle possède les attributs :
 *  <ul>
 *      <li>name</li>
 *      <li>attributes</li>
 *      <li>namePackage</li>
 *      <li>saveOption</li>
 *      <li>str</li>
 * </ul>
 */
public class Enumerations {
    private String name;

    /**
     * contient
     */
    private ArrayList<String> attributes = new ArrayList<String>();
    private String namePackage;

    /**
     * Constructeur de la classe Enumerations
     *
     * @param element Element
     */
    public Enumerations(Element element) {
        for (Element e : element.getEnclosedElements()) {
            if (e.getKind().isField()) {
                attributes.add(e.getSimpleName().toString());
            }
        }
        setName(element.getSimpleName().toString());
        setNamePackage(element.getEnclosingElement().getSimpleName().toString());
    }

    /**
     * Méthode qui permet de dessiner une énumération
     *
     * @param saveOption SaveOption
     * @return String string
     */
    public String strDraw(SaveOption saveOption) {
        StringBuilder str = new StringBuilder();
        str.append("enum ").append(getName()).append("<<enumeration>>").append(" {\n");
        if (saveOption.getDrawPrimitive()) {
            for (String attribut : attributes) {
                str.append("\t").append(attribut).append("\n");
            }
        }
        str.append("}\n");
        return str.toString();
    }

    /**
     * Méthode qui permet de récupérer le nom de l'énumération
     *
     * @return String name
     */
    public String getName() {
        return name;
    }

    /**
     * Procédure qui permet de modifier le nom de l'énumération
     *
     * @param name String
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Méthode qui permet de récupérer le nom du package
     *
     * @return ArrayList<String> attributes
     */
    public ArrayList<String> getAttributes() {
        return attributes;
    }

    /**
     * Procédure qui permet de modifier le nom du package
     *
     * @param attributes ArrayList<String>
     */
    public void setAttributes(ArrayList<String> attributes) {
        this.attributes = attributes;
    }

    /**
     * Méthode qui permet de récupérer le nom du package
     *
     * @return String name package
     */
    public String getNamePackage() {
        return namePackage;
    }

    /**
     * Procédure qui permet de modifier le nom du package
     *
     * @param namePackage String
     */
    public void setNamePackage(String namePackage) {
        this.namePackage = namePackage;
    }
}
