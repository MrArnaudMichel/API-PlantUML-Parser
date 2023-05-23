package pumlFromJava.classes;

import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.VariableElement;
import java.util.ArrayList;

public class Methode implements Type {
    private final ArrayList<String[]> parameters = new ArrayList<String[]>();
    private String name;
    private String returnType;
    private String visibility;
    private String isOverride;

    public Methode(Element e) {
        setName(e.getSimpleName().toString());
        String returnType = e.asType().toString().split("\\.")[e.asType().toString().split("\\.").length - 1];
        returnType = setArray(returnType);
        setReturnType(returnType);
        setVisibility(e.getModifiers().toString());
        String[] tab = new String[2];
        for (VariableElement parameter : ((ExecutableElement) e).getParameters()) {
            tab[0] = parameter.getSimpleName().toString();
            tab[1] = setArray(parameter.asType().toString().split("\\.")[parameter.asType().toString().split("\\.").length - 1]);
            parameters.add(tab);
        }
        //setIsOverride(e.getAnnotation(Override.class));

    }

    public String setArray(String type) {
        if (type.contains(")")) {
            type = type.split("\\)")[1];
            if (type.equals("void")) {
                type = "";
            } else if (type.equals("int")) {
                type = "Integer";
            } else if (type.equals("boolean")) {
                type = "Boolean";
            } else if (type.equalsIgnoreCase("float")) {
                type = "Reel";
            } else if (type.equalsIgnoreCase("double")) {
                type = "Reel";
            }
        }
        if (!type.equals("") && type.charAt(type.length() - 1) == '>') {
            type = type.substring(0, type.length() - 1);
            type += "[*]";
        }
        return type;
    }

    public String strDraw() {
        StringBuilder str = new StringBuilder();
        str.append(writeType()).append(" ").append(getName()).append("(");
        for (String[] parameter : parameters) {
            str.append(parameter[0]).append(" : ").append(parameter[1]);
            if (parameters.indexOf(parameter) != parameters.size() - 1) {
                str.append(", ");
            }
        }
        str.append(")");
        if (!getReturnType().equals("")) {
            str.append(" : ");
            str.append(getReturnType());
        }
        return str.toString();
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

    public String getIsOverride() {
        return isOverride;
    }

    public void setIsOverride(String isOverride) {
        this.isOverride = isOverride;
    }

    public ArrayList<String[]> getParameters() {
        return parameters;
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
}
