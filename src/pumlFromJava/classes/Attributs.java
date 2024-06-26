package pumlFromJava.classes;

import com.sun.source.util.DocTrees;
import pumlFromJava.ToolClasse;

import javax.lang.model.element.Element;
import javax.lang.model.type.TypeMirror;

/**
 * Classe Attributs
 * <p>
 * Classe qui permet de créer un attribut
 * d'une classe
 * <br>
 * Elle implémente l'interface Type
 * <br>
 * Elle possède les méthodes :
 * <ul>
 *     <li>strDrawAttributs</li>
 *     <li>writeType</li>
 *     <li>getName</li>
 *     <li>getType</li>
 *     <li>getVisibility</li>
 *     <li>setName</li>
 *     <li>setType</li>
 *     <li>setVisibility</li>
 *     <li>toString</li>
 *     <li>strDraw</li>
 * </ul>
 * <br>
 * Elle possède les attributs :
 * <ul>
 *     <li>name</li>
 *     <li>type</li>
 *     <li>visibility</li>
 * </ul>
 *
 * @pumlUse ToolClasse
 */
public class Attributs implements Type {
    private String name;

    /**
     * @pumlNameAssociation à un
     */
    private TypeMirror type;

    private String typeAssociation = "* ";
    private String visibility;

    private String pumlMultiplicity = "[*]";

    private String nameAssociation = "";

    /**
     * Constructeur de la classe Attributs
     *
     * @param element  Element
     * @param docTrees the doc trees
     */
    public Attributs(Element element, DocTrees docTrees) {
        setName(element.getSimpleName().toString());
        setType(element.asType());
        setVisibility(element.getModifiers().toString());
        //Pour chaque commentiare si le tag est @pumlDiagram alors afficher valeur
        //Sinon afficher ""
        if (docTrees.getDocCommentTree(element) != null) {
            for (var comment : docTrees.getDocCommentTree(element).getBlockTags()) {
                if (comment.toString().contains("@pumlNameAssociation")) {
                    for (String s : comment.toString().split(" ")) {
                        if (!s.equals("@pumlNameAssociation")) {
                            this.nameAssociation += s + " ";
                        }
                    }
                } else if (comment.toString().contains("@pumlComposition")) {
                    this.typeAssociation = "o ";
                } else if (comment.toString().contains("@pumlMultiplicity")) {
                    this.pumlMultiplicity = "[" + comment.toString().split(" ")[1] + "] ";
                }
            }
        }
    }

    /**
     * Méthode qui permet de dessiner un attribut
     *
     * @return String string
     */
    public String strDrawAttributs() {
        if (type.toString().equals("java.util.ArrayList<java.lang.String[]>")) {
            return writeType() + " " + name + " : String" + pumlMultiplicity;
        } else if (type.toString().equals("java.lang.String")) {
            return writeType() + " " + name + " : String";
        } else if (type.toString().equals("java.util.ArrayList<java.lang.String>")) {
            return writeType() + " " + name + " : String" + pumlMultiplicity;
        }
        return writeType() + " " + name + " : " + ToolClasse.setUmlType(type.toString());
    }

    public String strDrawAttributs(boolean isTab) {
        return writeType() + " " + name + " : " + ToolClasse.setUmlType(type.toString().split("\\[")[0]) + pumlMultiplicity;
    }

    /**
     * Méthode qui renvoie le type d'un attribut sous forme puml
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
     * Méthode qui renvoie le nom d'un attribut
     *
     * @return String name
     */
    public String getName() {
        return name;
    }

    /**
     * Méthode qui permet de modifier le nom d'un attribut
     *
     * @param name String
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Méthode qui renvoie le type d'un attribut
     *
     * @return TypeMirror type
     */
    public TypeMirror getType() {
        return type;
    }

    /**
     * Méthode qui permet de modifier le type d'un attribut
     *
     * @param type TypeMirror
     */
    public void setType(TypeMirror type) {
        this.type = type;
    }

    /**
     * Méthode qui renvoie la visibilité d'un attribut
     *
     * @return String
     */
    public String getVisibility() {
        return visibility;
    }

    /**
     * Méthode qui permet de modifier la visibilité d'un attribut
     *
     * @param visibility String
     */
    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    /**
     * Méthode qui renvoie le nom d'une association
     *
     * @return String name association
     */
    public String getNameAssociation() {
        return nameAssociation;
    }

    /**
     * Méthode qui permet de modifier le nom d'une association
     *
     * @param nameAssociation String
     */
    public void setNameAssociation(String nameAssociation) {
        this.nameAssociation = nameAssociation;
    }

    /**
     * Méthode qui renvoie le type d'une association
     *
     * @return String type association
     */
    public String getTypeAssociation() {
        return typeAssociation;
    }

    /**
     * Méthode qui renvoie la multiplicité d'une association
     *
     * @return String puml multiplicity
     */
    public String getPumlMultiplicity() {
        return pumlMultiplicity;
    }
}
