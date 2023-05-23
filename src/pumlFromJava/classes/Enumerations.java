package pumlFromJava.classes;

import javax.lang.model.element.Element;
import java.util.ArrayList;

public class Enumerations {
    private String name;
    private ArrayList<String> attributes = new ArrayList<String>();
    private String namePackage;

    public Enumerations(Element element) {
        for (Element e : element.getEnclosedElements()) {
            if (e.getKind().isField()) {
                attributes.add(e.getSimpleName().toString());
            }
        }
        setName(element.getSimpleName().toString());
        setNamePackage(element.getEnclosingElement().getSimpleName().toString());
    }

    public String strDraw() {
        StringBuilder str = new StringBuilder();
        str.append("enum ").append(getName()).append("<<enumeration>>").append(" {\n");
        for (String attribut : attributes) {
            str.append("\t").append(attribut).append("\n");
        }
        str.append("}\n");
        return str.toString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getAttributes() {
        return attributes;
    }

    public void setAttributes(ArrayList<String> attributes) {
        this.attributes = attributes;
    }

    public String getNamePackage() {
        return namePackage;
    }

    public void setNamePackage(String namePackage) {
        this.namePackage = namePackage;
    }
}
