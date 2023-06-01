package pumlFromJava.classes;

/**
 * Interface Type
 * <p>
 * Interface qui permet de créer une méthode
 * d'une classe
 * <br>
 * Elle possède les méthodes :
 * <ul>
 *     <li>isPublic</li>
 *     <li>isPrivate</li>
 *     <li>isProtected</li>
 *     <li>isAbstract</li>
 *     <li>isStatic</li>
 *     <li>getVisibility</li>
 * </ul>
 */
public interface Type {

    /**
     * Méthode qui permet de savoir si la méthode est public
     *
     * @param parameters String
     * @return boolean boolean
     */
    default boolean isPublic(String parameters) {
        return parameters.contains("public");
    }

    /**
     * Méthode qui permet de savoir si la méthode est private
     *
     * @param parameters String
     * @return boolean boolean
     */
    default boolean isPrivate(String parameters) {
        return parameters.contains("private");
    }

    /**
     * Méthode qui permet de savoir si la méthode est protected
     *
     * @param parameters String
     * @return boolean boolean
     */
    default boolean isProtected(String parameters) {
        return parameters.contains("protected");
    }

    /**
     * Méthode qui permet de savoir si la méthode est abstract
     *
     * @param parameters String
     * @return boolean boolean
     */
    default boolean isAbstract(String parameters) {
        return parameters.contains("abstract");
    }

    /**
     * Méthode qui permet de savoir si la méthode est static
     *
     * @param parameters String
     * @return boolean boolean
     */
    default boolean isStatic(String parameters) {
        return parameters.contains("static");
    }

    /**
     * Méthode qui permet de récupérer la visibilité
     *
     * @return String visibility
     */
    default String getVisibility() {
        return "";
    }
}
