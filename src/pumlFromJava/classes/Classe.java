package pumlFromJava.classes;

import javax.lang.model.element.Element;
import java.util.ArrayList;
import java.util.Objects;

public class Classe extends Instance implements Type {
    private ArrayList<Attributs> attributes = new ArrayList<Attributs>();
    private ArrayList<String> constructors = new ArrayList<String>();

    public Classe(Element element){
        for (Element e : element.getEnclosedElements()) {
            if (e.getKind().isField()) {
                attributes.add(new Attributs(e));
            } else if (Objects.equals(e.getKind().toString(), "METHOD")) {
                getMethods().add(new Methode(e));
            } else if (Objects.equals(e.getKind().toString(), "CONSTRUCTOR")) {
                constructors.add(typeConstructor(e.getModifiers().toString()) + "<<create>> " + e.toString());
            }
        }
        setName(element.getSimpleName().toString());
        setNamePackage(element.getEnclosingElement().getSimpleName().toString());
    }

    public String typeConstructor(String visibility){
        if (this.isPublic(visibility)){
            return "+";
        }
        else if (this.isPrivate(visibility)){
            return "-";
        } else if (this.isProtected(visibility)){
            return "#";
        }
        return "";
    }

    public String strDraw(String nameDiagram){
        StringBuilder str = new StringBuilder();
        str.append("class ").append(getNamePackage()).append(".").append(getName()).append(" {\n");
        for (Attributs attribut : attributes) {
            if (nameDiagram.equals("DCA") && attribut.getType().getKind().isPrimitive()){
                str.append(attribut.strDrawAttributs()).append("\n");
            }
            else if (!nameDiagram.equals("DCA")){
                str.append(attribut.strDrawAttributs()).append("\n");
            }
        }
        if (!nameDiagram.equals("DCA")){
            for (String constructor : constructors) {
                str.append(constructor).append("\n");
            }
            for (Methode methode : getMethods()) {
                str.append(methode.strDraw()).append("\n");
            }
        }
        str.append("}\n");
        return str.toString();
    }

    public ArrayList<String> getConstructors() {
        return constructors;
    }

    public void setConstructors(ArrayList<String> constructors) {
        this.constructors = constructors;
    }

    public ArrayList<Attributs> getAttributes() {
        return attributes;
    }

    public void setAttributes(ArrayList<Attributs> attributes) {
        this.attributes = attributes;
    }
}
