package pumlFromJava.classes;

import pumlFromJava.SaveOption;

import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeMirror;
import java.util.ArrayList;
import java.util.Objects;

public class Classe extends Instance implements Type {
    private ArrayList<Attributs> attributes = new ArrayList<Attributs>();
    private ArrayList<Contructor> constructors = new ArrayList<Contructor>();

    public Classe(Element element) {
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

    public String strDrawDiagram(SaveOption saveOption) {
        StringBuilder str = new StringBuilder();
        str.append("class ").append(getName()).append(" {\n");
        for (Attributs attribut : attributes) {
            if (attribut.getType().getKind().isPrimitive() ||
                    (saveOption.getStrPrimitive() && (attribut.getType().toString().equals("java.lang.String")))) {
                str.append("\t").append(attribut.strDrawAttributs()).append("\n");
            }
        }
        if (!saveOption.getTypeDiagram().equals("DCA")) {
            for (Contructor constructor : constructors) {
                str.append("\t").append(constructor.strDraw()).append("\n");
            }
            for (Methode methode : getMethods()) {
                str.append("\t").append(methode.strDraw()).append("\n");
            }
        }
        str.append("}\n");
        return str.toString();
    }

    public String strRelation(SaveOption saveOption) {
        StringBuilder str = new StringBuilder();
        if (saveOption.getAssociation()){
            if (!getExtendsClasse().equals("Object")) {
                str.append(getExtendsClasse()).append(" <|-- ").append(getName()).append("\n");
            }
            for (TypeMirror type : getImplementsInterface()) {
                str.append(type.toString().split("\\.")[type.toString().split("\\.").length - 1]).append(" <|.. ").append(getName()).append("\n");
            }
            for (Attributs attribut : attributes) {
                if (!attribut.getType().getKind().isPrimitive() && !(saveOption.getStrPrimitive() && (attribut.getType().toString().equals("java.lang.String")))) {
                    if (attribut.getType().toString().split("\\.")[attribut.getType().toString().split("\\.").length - 1].contains(">")) {
                        String type = attribut.getType().toString().split("\\.")[attribut.getType().toString().split("\\.").length - 1].split(">")[0];
                        if (type.equals("String[]")) {
                            type = "java.lang.String";
                        }
                        str.append(type).append("\" [*] \\n ").append(attribut.getName()).append("\"").append(" <--* ").append(getName()).append("\n");
                    } else {
                        if (attribut.getType().toString().split("\\.")[attribut.getType().toString().split("\\.").length - 1].equals("String") || attribut.getType().toString().split("\\.")[attribut.getType().toString().split("\\.").length - 1].equals("String[]")) {
                            str.append("java.lang.String").append("\" 1 \\n ").append(attribut.getName()).append("\"").append(" <--* ").append(getName()).append("\n");
                        } else {
                            str.append(attribut.getType().toString().split("\\.")[attribut.getType().toString().split("\\.").length - 1]).append("\" 1 \\n ").append(attribut.getName()).append("\"").append(" <--* ").append(getName()).append("\n");
                        }

                    }
                }
            }
        }
        return str.toString();
    }

    public ArrayList<Contructor> getConstructors() {
        return constructors;
    }

    public void setConstructors(ArrayList<Contructor> constructors) {
        this.constructors = constructors;
    }

    public ArrayList<Attributs> getAttributes() {
        return attributes;
    }

    public void setAttributes(ArrayList<Attributs> attributes) {
        this.attributes = attributes;
    }
}
