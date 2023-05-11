package pumlFromJava.classes;

import javax.lang.model.element.Element;
import java.util.ArrayList;

public class Methode implements Type {
    private String name;
    private String returnType;
    private String visibility;
    private ArrayList<String> parameters = new ArrayList<String>();

    public Methode(Element e){
        setName(e.getSimpleName().toString());
        setReturnType(e.asType().toString());
        setVisibility(e.getModifiers().toString());
    }

    public String strDraw(){
        StringBuilder str = new StringBuilder();
        str.append(writeType()).append(" ").append(getName()).append("(");
        for (String parameter : parameters) {
            str.append(parameter).append(", ");
        }
        str.append(getReturnType().split("\\)")[0].substring(1)).append(") : ").append(getReturnType().split("\\)")[1]);
        return str.toString();
    }

    public String writeType(){
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

    public String getReturnType() {
        return returnType;
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }

    @Override
    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public ArrayList<String> getParameters() {
        return parameters;
    }

    public void setParameters(ArrayList<String> parameters) {
        this.parameters = parameters;
    }

}
