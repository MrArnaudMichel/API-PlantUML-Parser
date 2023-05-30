package pumlFromJava.classes;

import pumlFromJava.SaveOption;

import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import java.util.Objects;

/**
 * Classe Interface
 * <p>
 * Classe qui permet de créer une interface
 * d'une classe
 * <br>
 * Elle possède les méthodes :
 *   <ul>
 *       <li>strDraw</li>
 *       <li>getName</li>
 *       <li>setName</li>
 *       <li>getNamePackage</li>
 *       <li>setNamePackage</li>
 *       <li>getMethods</li>
 *       <li>setMethods</li>
 *       <li>getExtendsClasse</li>
 *       <li>setExtendsClasse</li>
 *       <li>getImplementsInterface</li>
 *       <li>setImplementsInterface</li>
 *  </ul>
 *  <br>
 *  Elle possède les attributs :
 *  <ul>
 *      <li>name</li>
 *      <li>methods</li>
 *      <li>extendsClasse</li>
 *      <li>implementsInterface</li>
 *      <li>namePackage</li>
 * </ul>
 */
public class Interface extends Instance {


    /**
     * Constructeur de la classe Interface
     *
     * @param element the element
     */
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

    /**
     * Constructeur de la classe Interface
     *
     * @param saveOption SaveOption
     * @return String string
     */
    public String strDraw(SaveOption saveOption) {
        StringBuilder str = new StringBuilder();
        str.append("interface ").append(getName()).append("<<interface>>").append(" {\n");
        if (!saveOption.getTypeDiagram().equals("DCA")) {
            if (saveOption.getMethod()) {
                for (Methode methode : getMethods()) {
                    str.append("\t").append(methode.strDraw()).append("\n");
                }
            }
        }
        str.append("}\n");
        return str.toString();
    }

    /**
     * Méthode qui permet de récupérer le nom de l'interface
     *
     * @param saveOption SaveOption
     * @return String string
     */
    public String strRelation(SaveOption saveOption) {
        if (!saveOption.getAssociation() && !saveOption.getDrawExtends()) {
            return "";
        }
        if (!Objects.equals(getExtendsClasse(), "none")) {
            return getName() + " <|-- " + getExtendsClasse() + "\n";
        }
        return "";
    }
}
