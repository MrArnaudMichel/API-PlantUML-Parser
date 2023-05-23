package pumlFromJava.classes;

import javax.lang.model.element.Element;
import javax.lang.model.type.TypeMirror;

public class Attributs implements Type {
    private String name;
    private TypeMirror type;
    private String visibility;

    public Attributs(Element element) {
        setName(element.getSimpleName().toString());
        setType(element.asType());
        setVisibility(element.getModifiers().toString());
    }

    public String strDrawAttributs() {
        if (type.toString().equals("java.util.ArrayList<java.lang.String[]>")) {
            return writeType() + " " + name + " : String[*]";
        }else if (type.toString().equals("java.lang.String")){
            return writeType() + " " + name + " : String";
        }
        return writeType() + " " + name + " : " + type.toString();
    }

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TypeMirror getType() {
        return type;
    }

    public void setType(TypeMirror type) {
        this.type = type;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

}
