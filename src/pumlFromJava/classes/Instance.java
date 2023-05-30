package pumlFromJava.classes;

import javax.lang.model.type.TypeMirror;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe Instance
 * <p>
 * Classe qui permet de créer une instance
 * d'une classe
 * <br>
 * Elle possède les méthodes :
 * <ul>
 *     <li>strDraw</li>
 *     <li>getName</li>
 *     <li>setName</li>
 *     <li>getMethods</li>
 *     <li>setMethods</li>
 *     <li>getExtendsClasse</li>
 *     <li>setExtendsClasse</li>
 *     <li>getImplementsInterface</li>
 *     <li>setImplementsInterface</li>
 *     <li>getNamePackage</li>
 *     <li>setNamePackage</li>
 * </ul>
 * <br>
 * Elle possède les attributs :
 * <ul>
 *     <li>name</li>
 *     <li>methods</li>
 *     <li>extendsClasse</li>
 *     <li>implementsInterface</li>
 *     <li>namePackage</li>
 * </ul>
 */
public class Instance {
    private String name;

    /**
     * contient
     */
    private ArrayList<Methode> methods = new ArrayList<Methode>();
    private String extendsClasse;

    /**
     * implémente
     */
    private List<? extends TypeMirror> implementsInterface = new ArrayList<>();
    private String namePackage;

    /**
     * Constructeur de la classe Instance
     */
    public Instance() {
    }

    /**
     * Methode qui permet de récupérer le nom de l'instance
     *
     * @return String name
     */
    public String getName() {
        return name;
    }

    /**
     * Methode qui permet de modifier le nom de l'instance
     *
     * @param name String
     */
    public void setName(String name) {
        this.name = name;
    }


    /**
     * Methode qui permet de récupérer les méthodes de l'instance
     *
     * @return ArrayList<Methode> methods
     */
    public ArrayList<Methode> getMethods() {
        return methods;
    }

    /**
     * Methode qui permet de modifier les méthodes de l'instance
     *
     * @param methods ArrayList<Methode>
     */
    public void setMethods(ArrayList<Methode> methods) {
        this.methods = methods;
    }

    /**
     * Methode qui permet de récupérer la classe dont l'instance hérite
     *
     * @return String extends classe
     */
    public String getExtendsClasse() {
        return extendsClasse;
    }

    /**
     * Methode qui permet de modifier la classe dont l'instance hérite
     *
     * @param extendsClasse String
     */
    public void setExtendsClasse(String extendsClasse) {
        this.extendsClasse = extendsClasse;
    }

    /**
     * Methode qui permet de récupérer les interfaces que l'instance implémente
     *
     * @return List<? extends TypeMirror> implements interface
     */
    public List<? extends TypeMirror> getImplementsInterface() {
        return implementsInterface;
    }

    /**
     * Methode qui permet de modifier les interfaces que l'instance implémente
     *
     * @param implementsInterface List<? extends TypeMirror>
     */
    public void setImplementsInterface(List<? extends TypeMirror> implementsInterface) {
        this.implementsInterface = implementsInterface;
    }

    /**
     * Methode qui permet de récupérer le nom du package de l'instance
     *
     * @return String name package
     */
    public String getNamePackage() {
        return namePackage;
    }

    /**
     * Methode qui permet de modifier le nom du package de l'instance
     *
     * @param namePackage String
     */
    public void setNamePackage(String namePackage) {
        this.namePackage = namePackage;
    }
}