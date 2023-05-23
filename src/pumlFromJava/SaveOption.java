package pumlFromJava;

public class SaveOption {
    public String typeDiagram = "DCC"; //Check
    public boolean strPrimitive = false; //Check
    public boolean association = true;
    public boolean constructor = true;
    public boolean method = true;
    public boolean field = true;
    public boolean drawPrimitive = true;
    public boolean drawUnPrimitive = true;
    public boolean drawExtends = true;
    public boolean drawImplements = true;

    public SaveOption() {
    }

    public String getTypeDiagram() {
        return typeDiagram;
    }

    public void setTypeDiagram(String typeDiagram) {
        this.typeDiagram = typeDiagram;
    }

    public boolean getStrPrimitive() {
        return strPrimitive;
    }

    public void setStrPrimitive(boolean strPrimitive) {
        this.strPrimitive = strPrimitive;
    }

    public boolean getAssociation() {
        return association;
    }

    public void setAssociation(boolean association) {
        this.association = association;
    }

    public boolean getConstructor() {
        return constructor;
    }

    public void setConstructor(boolean constructor) {
        this.constructor = constructor;
    }

    public boolean getMethod() {
        return method;
    }

    public void setMethod(boolean method) {
        this.method = method;
    }

    public boolean getField() {
        return field;
    }

    public void setField(boolean field) {
        this.field = field;
    }

    public boolean getDrawPrimitive() {
        return drawPrimitive;
    }

    public void setDrawPrimitive(boolean drawPrimitive) {
        this.drawPrimitive = drawPrimitive;
    }

    public boolean getDrawUnPrimitive() {
        return drawUnPrimitive;
    }

    public void setDrawUnPrimitive(boolean drawUnPrimitive) {
        this.drawUnPrimitive = drawUnPrimitive;
    }

    public boolean getDrawExtends() {
        return drawExtends;
    }

    public void setDrawExtends(boolean drawExtends) {
        this.drawExtends = drawExtends;
    }

    public boolean getDrawImplements() {
        return drawImplements;
    }

    public void setDrawImplements(boolean drawImplements) {
        this.drawImplements = drawImplements;
    }
}
