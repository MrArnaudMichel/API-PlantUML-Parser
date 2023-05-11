package pumlFromJava.classes;

import javax.lang.model.element.Element;
import javax.lang.model.type.TypeMirror;

public class Attributs implements Type {
    private String name;
    private TypeMirror type;
    private String visibility;

    public Attributs(Element element){
        setName(element.getSimpleName().toString());
        setType(element.asType());
        setVisibility(element.getModifiers().toString());
    }

    public String strDrawAttributs(){
        return writeType() + " " + name + " : " + type.toString();
    }

    private String writeType(){
        if (this.isPublic(getVisibility())){
            return "+";
        }
        else if (this.isPrivate(getVisibility())){
            return "-";
        } else if (this.isProtected(getVisibility())){
            return "#";
        } else if (this.isAbstract(getVisibility())){
            return "{abstract}";
        } else if (this.isStatic(getVisibility())){
            return "{static}";
        }
        return "";
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
