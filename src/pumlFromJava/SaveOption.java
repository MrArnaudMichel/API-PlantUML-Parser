package pumlFromJava;

/**
 * Classe SaveOption
 * <p>
 *     Classe qui permet de créer une option de sauvegarde
 *     d'un diagramme
 *     <br>
 *    Elle possède les attributs :
 *    <ul>
 *        <li>typeDiagram</li>
 *        <li>strPrimitive</li>
 *        <li>association</li>
 *        <li>constructor</li>
 *        <li>method</li>
 *        <li>drawPrimitive</li>
 *        <li>drawUnPrimitive</li>
 *        <li>drawExtends</li>
 *        <li>drawImplements</li>
 *    </ul>
 *     Elle possède les méthodes :
 *     <ul>
 *         <li>getTypeDiagram</li>
 *         <li>setTypeDiagram</li>
 *         <li>getStrPrimitive</li>
 *         <li>setStrPrimitive</li>
 *         <li>getAssociation</li>
 *         <li>setAssociation</li>
 *         <li>getConstructor</li>
 *         <li>setConstructor</li>
 *         <li>getMethod</li>
 *         <li>setMethod</li>
 *         <li>getDrawPrimitive</li>
 *         <li>setDrawPrimitive</li>
 *         <li>getDrawUnPrimitive</li>
 *         <li>setDrawUnPrimitive</li>
 *         <li>getDrawExtends</li>
 *         <li>setDrawExtends</li>
 *         <li>getDrawImplements</li>
 *         <li>setDrawImplements</li>
 *    </ul>
 */
public class SaveOption {
    public String typeDiagram = "DCC"; //Check
    public boolean strPrimitive = true; //Check
    public boolean association = true;
    public boolean constructor = true;
    public boolean method = true;
    public boolean drawPrimitive = true;
    public boolean drawUnPrimitive = true;
    public boolean drawExtends = true;
    public boolean drawImplements = true;

    /**
     * Constructeur SaveOption
     * <p>
     *     Constructeur qui permet de créer une option de sauvegarde
     *     d'un diagramme
     * </p>
     */
    public SaveOption() {
    }

    /**
     * Méthode qui permet de récupérer le type de diagramme
     * @return String
     */
    public String getTypeDiagram() {
        return typeDiagram;
    }

    /**
     * Méthode qui permet de modifier le type de diagramme
     * @param typeDiagram String
     */
    public void setTypeDiagram(String typeDiagram) {
        this.typeDiagram = typeDiagram;
    }

    /**
     * Méthode qui permet de récupérer si on veut afficher les primitives
     * @return boolean
     */
    public boolean getStrPrimitive() {
        return strPrimitive;
    }

    /**
     * Méthode qui permet de modifier si on veut afficher les primitives
     * @param strPrimitive boolean
     */
    public void setStrPrimitive(boolean strPrimitive) {
        this.strPrimitive = strPrimitive;
    }

    /**
     * Méthode qui permet de récupérer si on veut afficher les associations
     * @return boolean
     */
    public boolean getAssociation() {
        return association;
    }

    /**
     * Méthode qui permet de modifier si on veut afficher les associations
     * @param association boolean
     */
    public void setAssociation(boolean association) {
        this.association = association;
    }

    /**
     * Méthode qui permet de récupérer si on veut afficher les constructeurs
     * @return boolean
     */
    public boolean getConstructor() {
        return constructor;
    }

    /**
     * Méthode qui permet de modifier si on veut afficher les constructeurs
     * @param constructor boolean
     */
    public void setConstructor(boolean constructor) {
        this.constructor = constructor;
    }

    /**
     * Méthode qui permet de récupérer si on veut afficher les méthodes
     * @return boolean
     */
    public boolean getMethod() {
        return method;
    }

    /**
     * Méthode qui permet de modifier si on veut afficher les méthodes
     * @param method boolean
     */
    public void setMethod(boolean method) {
        this.method = method;
    }

    /**
     * Méthode qui permet de récupérer si on veut afficher les primitives
     * @return boolean
     */
    public boolean getDrawPrimitive() {
        return drawPrimitive;
    }

    /**
     * Méthode qui permet de modifier si on veut afficher les primitives
     * @param drawPrimitive boolean
     */
    public void setDrawPrimitive(boolean drawPrimitive) {
        this.drawPrimitive = drawPrimitive;
    }

    /**
     * Méthode qui permet de récupérer si on veut afficher les primitives
     * @return boolean
     */
    public boolean getDrawUnPrimitive() {
        return drawUnPrimitive;
    }

    /**
     * Méthode qui permet de modifier si on veut afficher les primitives
     * @param drawUnPrimitive boolean
     */
    public void setDrawUnPrimitive(boolean drawUnPrimitive) {
        this.drawUnPrimitive = drawUnPrimitive;
    }

    /**
     * Méthode qui permet de récupérer si on veut afficher les extends
     * @return boolean
     */
    public boolean getDrawExtends() {
        return drawExtends;
    }

    /**
     * Méthode qui permet de modifier si on veut afficher les extends
     * @param drawExtends boolean
     */
    public void setDrawExtends(boolean drawExtends) {
        this.drawExtends = drawExtends;
    }

    /**
     * Méthode qui permet de récupérer si on veut afficher les implements
     * @return boolean
     */
    public boolean getDrawImplements() {
        return drawImplements;
    }

    /**
     * Méthode qui permet de modifier si on veut afficher les implements
     * @param drawImplements boolean
     */
    public void setDrawImplements(boolean drawImplements) {
        this.drawImplements = drawImplements;
    }
}
