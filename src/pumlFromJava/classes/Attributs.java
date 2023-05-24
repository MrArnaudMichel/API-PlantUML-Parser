package pumlFromJava.classes;

import javax.lang.model.element.Element;
import javax.lang.model.type.TypeMirror;

/**
 * Classe Attributs
 * <p>
 *     Classe qui permet de créer un attribut
 *     d'une classe
 *     <br>
 *     Elle implémente l'interface Type
 *     <br>
 *     Elle possède les méthodes :
 *     <ul>
 *         <li>strDrawAttributs</li>
 *         <li>writeType</li>
 *         <li>getName</li>
 *         <li>getType</li>
 *         <li>getVisibility</li>
 *         <li>setName</li>
 *         <li>setType</li>
 *         <li>setVisibility</li>
 *         <li>toString</li>
 *         <li>strDraw</li>
 *     </ul>
 *     <br>
 *     Elle possède les attributs :
 *     <ul>
 *         <li>name</li>
 *         <li>type</li>
 *         <li>visibility</li>
 *     </ul>
 */
public class Attributs implements Type {
    private String name;
    private TypeMirror type;
    private String visibility;

    /**
     * @param element Element
     */
    public Attributs(Element element) {
        setName(element.getSimpleName().toString());
        setType(element.asType());
        setVisibility(element.getModifiers().toString());
    }

    /**
     * @return String
     */
    public String strDrawAttributs() {
        if (type.toString().equals("java.util.ArrayList<java.lang.String[]>")) {
            return writeType() + " " + name + " : String[*]";
        }else if (type.toString().equals("java.lang.String")){
            return writeType() + " " + name + " : String";
        }else if (type.toString().equals("java.util.ArrayList<java.lang.String>")) {
            return writeType() + " " + name + " : String[*]";
        }
        return writeType() + " " + name + " : " + Methode.setArray(type.toString());
    }

    /**
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
     *
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name String
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return TypeMirror
     */
    public TypeMirror getType() {
        return type;
    }

    /**
     *
     * @param type TypeMirror
     */
    public void setType(TypeMirror type) {
        this.type = type;
    }

    /**
     *
     * @return String
     */
    public String getVisibility() {
        return visibility;
    }

    /**
     *
     * @param visibility String
     */
    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

}
