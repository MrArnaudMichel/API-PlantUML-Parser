package pumlFromJava.classes;

import pumlFromJava.SaveOption;

import javax.lang.model.element.Element;
import java.util.ArrayList;

/**
 * Classe Enumerations
 * <p>
 *     Classe qui permet de créer une énumération
 *     d'une classe
 *     <br>
 *     Elle possède les méthodes :
 *     <ul>
 *         <li>strDraw</li>
 *         <li>getName</li>
 *         <li>setName</li>
 *         <li>getNamePackage</li>
 *         <li>setNamePackage</li>
 *         <li>getAttributes</li>
 *         <li>setAttributes</li>
 *    </ul>
 *    <br>
 *    Elle possède les attributs :
 *    <ul>
 *        <li>name</li>
 *        <li>attributes</li>
 *        <li>namePackage</li>
 *        <li>saveOption</li>
 *        <li>str</li>
 *   </ul>
 */
public class Enumerations {
    private String name;
    private ArrayList<String> attributes = new ArrayList<String>();
    private String namePackage;

    /**
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
     * @param saveOption SaveOption
     * @return String
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
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * @param name String
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return ArrayList<String>
     */
    public ArrayList<String> getAttributes() {
        return attributes;
    }

    /**
     * @param attributes ArrayList<String>
     */
    public void setAttributes(ArrayList<String> attributes) {
        this.attributes = attributes;
    }

    /**
     * @return String
     */
    public String getNamePackage() {
        return namePackage;
    }

    /**
     * @param namePackage String
     */
    public void setNamePackage(String namePackage) {
        this.namePackage = namePackage;
    }
}
