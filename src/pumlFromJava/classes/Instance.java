package pumlFromJava.classes;

import javax.lang.model.type.TypeMirror;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe Instance
 * <p>
 *     Classe qui permet de créer une instance
 *     d'une classe
 *     <br>
 *     Elle possède les méthodes :
 *     <ul>
 *         <li>strDraw</li>
 *         <li>getName</li>
 *         <li>setName</li>
 *         <li>getMethods</li>
 *         <li>setMethods</li>
 *         <li>getExtendsClasse</li>
 *         <li>setExtendsClasse</li>
 *         <li>getImplementsInterface</li>
 *         <li>setImplementsInterface</li>
 *         <li>getNamePackage</li>
 *         <li>setNamePackage</li>
 *     </ul>
 *     <br>
 *     Elle possède les attributs :
 *     <ul>
 *         <li>name</li>
 *         <li>methods</li>
 *         <li>extendsClasse</li>
 *         <li>implementsInterface</li>
 *         <li>namePackage</li>
 *     </ul>
 */
public class Instance {
    private String name;

    private ArrayList<Methode> methods = new ArrayList<Methode>();
    private String extendsClasse;
    private List<? extends TypeMirror> implementsInterface = new ArrayList<>();
    private String namePackage;

    /**
     * Constructeur de la classe Instance
     */
    public Instance() {
    }

    /**
     *
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name String
     */
    public void setName(String name) {
        this.name = name;
    }


    /**
     *
     * @return ArrayList<Methode>
     */
    public ArrayList<Methode> getMethods() {
        return methods;
    }

    /**
     *
     * @param methods ArrayList<Methode>
     */
    public void setMethods(ArrayList<Methode> methods) {
        this.methods = methods;
    }

    /**
     *
     * @return String
     */
    public String getExtendsClasse() {
        return extendsClasse;
    }

    /**
     *
     * @param extendsClasse String
     */
    public void setExtendsClasse(String extendsClasse) {
        this.extendsClasse = extendsClasse;
    }

    /**
     *
     * @return List<? extends TypeMirror>
     */
    public List<? extends TypeMirror> getImplementsInterface() {
        return implementsInterface;
    }

    /**
     *
     * @param implementsInterface List<? extends TypeMirror>
     */
    public void setImplementsInterface(List<? extends TypeMirror> implementsInterface) {
        this.implementsInterface = implementsInterface;
    }

    /**
     *
     * @return String
     */
    public String getNamePackage() {
        return namePackage;
    }

    /**
     *
     * @param namePackage String
     */
    public void setNamePackage(String namePackage) {
        this.namePackage = namePackage;
    }
}