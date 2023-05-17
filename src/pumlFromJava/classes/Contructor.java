package pumlFromJava.classes;

import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.VariableElement;
import java.util.ArrayList;

public class Contructor implements Type {
    private String name;
    private String visibility;
    private ArrayList<String[]> parameters = new ArrayList<String[]>();

    public Contructor(Element e, String name){
        setName(name);
        setVisibility(e.getModifiers().toString());
        String[] tab = new String[2];
        for (VariableElement parameter : ((ExecutableElement) e).getParameters()) {
            tab[0] = parameter.getSimpleName().toString();
            tab[1] = parameter.asType().toString().split("\\.")[parameter.asType().toString().split("\\.").length - 1];
            parameters.add(tab);
        }
    }

    public String strDraw(){
        StringBuilder str = new StringBuilder();
        str.append(typeConstructor(getVisibility())).append("<<Create>>").append(" ").append(getName()).append("(");
        for (String[] parameter : parameters) {
            String type = parameter[1];
            if (type.equals("int")){
                type = "Integer";
            }else if (type.equals("boolean")) {
                type = "Boolean";
            }else if (type.equals("float")) {
                type = "Float";
            }else if (type.equals("double")) {
                type = "Double";
            }else if (type.charAt(type.length() - 1) == '>') {
                type = type.substring(0, type.length() - 1);
                type += "[*]";
            }
            str.append(parameter[0]).append(" : ").append(type);
            if (parameters.indexOf(parameter) != parameters.size() - 1){
                str.append(", ");
            }
        }
        str.append(")");
        return str.toString();
    }

    private String typeConstructor(String visibility){
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public ArrayList<String[]> getParameters() {
        return parameters;
    }

    public void setParameters(ArrayList<String[]> parameters) {
        this.parameters = parameters;
    }
}
