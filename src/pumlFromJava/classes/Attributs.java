package pumlFromJava.classes;

import javax.lang.model.type.TypeMirror;

public class Attributs implements Type {
    private String name;
    private TypeMirror type;
    private String visibility;

    public Attributs(){}

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
