package pumlFromJava.classes;

import javax.lang.model.element.Element;

public class Interface extends Instance {

        public Interface(Element element){
            for (Element e : element.getEnclosedElements()) {
                 if (e.getKind().toString().equals("METHOD")) {
                    getMethods().add(new Methode(e));
                }
            }
            setName(element.getSimpleName().toString());
            setNamePackage(element.getEnclosingElement().getSimpleName().toString());
        }

        public String strDraw(String nameDiagram){
            StringBuilder str = new StringBuilder();
            str.append("interface ").append(getNamePackage()).append(".").append(getName()).append("<<interface>>").append(" {\n");;
            if (!nameDiagram.equals("DCA")){
                for (Methode methode : getMethods()) {
                    str.append(methode.strDraw()).append("\n");
                }
            }
            str.append("}\n");
            return str.toString();
        }

}
