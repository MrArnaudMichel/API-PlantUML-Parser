package pumlFromJava.classes;

import pumlFromJava.SaveOption;

import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import java.util.Objects;

public class Interface extends Instance {

    public Interface(Element element) {
        for (Element e : element.getEnclosedElements()) {
            if (e.getKind().toString().equals("METHOD")) {
                getMethods().add(new Methode(e));
            }
        }
        setName(element.getSimpleName().toString());
        setExtendsClasse(((TypeElement) element).getSuperclass().toString().split("\\.")[((TypeElement) element).getSuperclass().toString().split("\\.").length - 1]);
        setImplementsInterface(((TypeElement) element).getInterfaces());
        setNamePackage(element.getEnclosingElement().getSimpleName().toString());
    }

    public String strDraw(SaveOption saveOption) {
        StringBuilder str = new StringBuilder();
        str.append("interface ").append(getName()).append("<<interface>>").append(" {\n");
        if (!saveOption.getTypeDiagram().equals("DCA")) {
            for (Methode methode : getMethods()) {
                str.append("\t").append(methode.strDraw()).append("\n");
            }
        }
        str.append("}\n");
        return str.toString();
    }

    public String strRelation() {
        if (!Objects.equals(getExtendsClasse(), "none")) {
            return getName() + " <|-- " + getExtendsClasse() + "\n";
        }
        return "";
    }
}
