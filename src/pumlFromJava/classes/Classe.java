package pumlFromJava.classes;

import jdk.jfr.Description;
import pumlFromJava.SaveOption;

import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeMirror;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Classe qui permet de créer une classe à partir d'un élément
 * <br>
 * Elle implémente l'interface Type
 * <br>
 * Elle possède les méthodes :
 * <ul>
 *     <li>strDraw</li>
 *     <li>strDrawAttributs</li>
 *     <li>strDrawConstructors</li>
 *     <li>strDrawMethods</li>
 *     <li>writeType</li>
 *     <li>getName</li>
 *     <li>getType</li>
 *     <li>getVisibility</li>
 *     <li>setName</li>
 *     <li>setType</li>
 *     <li>setVisibility</li>
 * </ul>
 * <br>
 * Elle possède les attributs :
 * <ul>
 *     <li>name</li>
 *     <li>type</li>
 *     <li>visibility</li>
 *     <li>attributes</li>
 *     <li>constructors</li>
 *     <li>methods</li>
 *     <li>usedClasses</li>
 *     <li>author</li>
 * </ul>
 *
 * @see Type
 */
@Description(
        "author: Arnaud, Benjamin;"
)
public class Classe extends Instance implements Type {
    /**
     * @pumlDiagram contient
     */
    private final ArrayList<Attributs> attributes = new ArrayList<Attributs>();
    /**
     * @pumlDiagram contient
     */
    private final ArrayList<Contructor> constructors = new ArrayList<Contructor>();

    /**
     * @pumlDiagram contient
     */
    private final ArrayList<String> usedClasses = new ArrayList<String>();

    /**
     * @pumlDiagram contient
     */
    private final ArrayList<String> author = new ArrayList<String>();

    /**
     * Constructeur de la classe Classe
     *
     * @param element Element
     */
    public Classe(Element element) {

        try {
            String[] split = element.getAnnotation(Description.class).toString().split(";");
            for (String s : split) {
                if (s.contains("author:")) {
                    String[] split1 = s.split(":");
                    String[] split2 = split1[1].split(",");
                    for (String s1 : split2) {
                        author.add(s1.trim());
                    }
                }
            }
        } catch (NullPointerException ignored) {
        }
        setName(element.getSimpleName().toString());
        for (Element e : element.getEnclosedElements()) {
            if (e.getKind().isField()) {
                attributes.add(new Attributs(e));
            } else if (Objects.equals(e.getKind().toString(), "METHOD")) {
                getMethods().add(new Methode(e));

            } else if (Objects.equals(e.getKind().toString(), "CONSTRUCTOR")) {
                constructors.add(new Contructor(e, getName()));
            }
        }
        setExtendsClasse(((TypeElement) element).getSuperclass().toString().split("\\.")[((TypeElement) element).getSuperclass().toString().split("\\.").length - 1]);
        setImplementsInterface(((TypeElement) element).getInterfaces());
        StringBuilder packageName = new StringBuilder();
        while (Objects.equals(element.getEnclosingElement().getKind().toString(), "PACKAGE")) {
            packageName.insert(0, element.getEnclosingElement().getSimpleName().toString() + ".");
            element = element.getEnclosingElement();

        }
        setNamePackage(packageName.toString());
    }

    /**
     * Méthode qui permet de dessiner une classe
     *
     * @param saveOption SaveOption
     * @return String string
     */
    public String strDrawDiagram(SaveOption saveOption) {
        StringBuilder str = new StringBuilder();
        str.append("class ").append(getName());
        if (author.size() > 0) {
            str.append("<< ");
        }
        for (String s : author) {
            str.append(s);
            if (author.indexOf(s) != author.size() - 1) {
                str.append(", ");
            } else {
                str.append(" >>");
            }
        }
        str.append(" {\n");
        if (saveOption.getDrawPrimitive()) {
            for (Attributs attribut : attributes) {
                if (attribut.getType().getKind().isPrimitive() || (saveOption.getStrPrimitive() && (attribut.getType().toString().equals("java.lang.String") || attribut.getType().toString().equals("java.util.ArrayList<java.lang.String>") || attribut.getType().toString().equals("java.util.ArrayList<java.lang.String[]>")))) {
                    str.append("\t").append(attribut.strDrawAttributs()).append("\n");
                }
            }
        }
        if (!saveOption.getTypeDiagram().equals("DCA")) {
            if (saveOption.getConstructor()) {
                for (Contructor constructor : constructors) {
                    str.append("\t").append(constructor.strDraw()).append("\n");
                }
            }
            if (saveOption.getMethod()) {
                for (Methode methode : getMethods()) {
                    str.append("\t").append(methode.strDraw()).append("\n");
                }
            }
        }
        str.append("}\n");
        return str.toString();
    }


    /**
     * Méthode qui permet de dessiner les attributs d'une classe
     *
     * @param saveOption SaveOption
     * @return String string
     */
    public String strRelation(SaveOption saveOption) {
        drawUse();
        StringBuilder str = new StringBuilder();
        if (saveOption.getAssociation()) {
            if (saveOption.getDrawExtends() && !Objects.equals(getExtendsClasse(), "Object")) {
                str.append(getExtendsClasse()).append(" <|-- ").append(getName()).append("\n");
                usedClasses.remove(getExtendsClasse());
            }
            if (saveOption.getDrawImplements()) {
                for (TypeMirror type : getImplementsInterface()) {
                    str.append(type.toString().split("\\.")[type.toString().split("\\.").length - 1]).append(" <|.. ").append(getName()).append("\n");
                    usedClasses.remove(type.toString().split("\\.")[type.toString().split("\\.").length - 1]);
                }
            }
            if (saveOption.getAssociation() && saveOption.getDrawUnPrimitive()) {
                for (Attributs attribut : attributes) {
                    if (saveOption.getTypeDiagram().equals("DCA")) {
                        attribut.setName("");
                    }
                    if (!attribut.getType().getKind().isPrimitive() && !(saveOption.getStrPrimitive() && (attribut.getType().toString().equals("java.lang.String") || attribut.getType().toString().equals("java.util.ArrayList<java.lang.String>") || attribut.getType().toString().equals("java.util.ArrayList<java.lang.String[]>")))) {
                        if (attribut.getType().toString().split("\\.")[attribut.getType().toString().split("\\.").length - 1].contains(">")) {
                            String type = attribut.getType().toString().split("\\.")[attribut.getType().toString().split("\\.").length - 1].split(">")[0];
                            if (type.equals("String[]")) {
                                type = "java.lang.String";
                            }
                            str.append(type).append("\" [*] \\n ").append(attribut.getName()).append("\"").append(" <--* ").append(getName()).append("\n");
                            usedClasses.remove(type);
                        } else {
                            if (attribut.getType().toString().split("\\.")[attribut.getType().toString().split("\\.").length - 1].equals("String") || attribut.getType().toString().split("\\.")[attribut.getType().toString().split("\\.").length - 1].equals("String[]")) {
                                str.append("java.lang.String").append("\" 1 \\n ").append(attribut.getName()).append("\"").append(" <--* ").append(getName()).append("\n");
                                usedClasses.remove("String");
                            } else {
                                str.append(attribut.getType().toString().split("\\.")[attribut.getType().toString().split("\\.").length - 1]).append("\" 1 \\n ").append(attribut.getName()).append("\"").append(" <--* ").append(getName()).append("\n");
                                usedClasses.remove(attribut.getType().toString().split("\\.")[attribut.getType().toString().split("\\.").length - 1]);
                            }

                        }
                    }
                }
                if (!saveOption.getTypeDiagram().equals("DCA")) {
                    for (String usedClass : usedClasses) {
                        str.append(getName()).append(" ..> ").append(" \"<<use>>\" ").append(usedClass).append("\n");
                    }
                }
            }
        }
        return str.toString();
    }

    /**
     * Méthode qui permet de dessiner les associations de type <<use>> d'une classe
     */

    private void drawUse() {
        ArrayList<String> typeMethod = new ArrayList<>();
        ArrayList<String> primitive = new ArrayList<>() {
            {
                add("Integer");
                add("Reel");
                add("Boolean");
                add("Char");
                add("String");
                add("boolean");
            }
        };
        for (Methode methode : getMethods()) {
            if (!primitive.contains(methode.getReturnType().split("\\[")[methode.getReturnType().split("\\[").length - 1]) && !typeMethod.contains(methode.getReturnType().split("\\[")[methode.getReturnType().split("\\[").length - 1]) && !methode.getReturnType().equals("") && !methode.getReturnType().contains("String")) {
                typeMethod.add(methode.getReturnType().split("\\[")[methode.getReturnType().split("\\[").length - 1]);
            }
            for (String[] type : methode.getParameters()) {
                if (!primitive.contains(type[1].split("\\[")[type[1].split("\\[").length - 1]) && !typeMethod.contains(type[1].split("\\[")[type[1].split("\\[").length - 1]) && !type[1].contains("String")) {
                    typeMethod.add(type[1].split("\\[")[type[1].split("\\.").length - 1]);
                }
            }
        }
        for (Contructor constructor : constructors) {
            for (String[] type : constructor.getParameters()) {
                if (!primitive.contains(type[1].split("\\[")[type[1].split("\\[").length - 1]) && !typeMethod.contains(type[1].split("\\[")[type[1].split("\\[").length - 1]) && !type[1].contains("String")) {
                    typeMethod.add(type[1].split("\\[")[type[1].split("\\[").length - 1]);
                }
            }
        }
        for (String type : typeMethod) {
            if (type.equals("*]")) {
                typeMethod.remove(type);
                break;
            }
        }
        usedClasses.addAll(typeMethod);
    }
}
