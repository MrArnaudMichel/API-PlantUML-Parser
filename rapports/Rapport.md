# Rapport SAE 21
## Projet PumlFromJava
### Semaine 1 : les *doclets* de javadoc
#### Objectif
Prendre en main `javadoc`, les *doclets* et leurs options afin de générer dans un fichier `.puml` un diagramme énumérant les éléments sélectionnés.

#### Rendus
- DS `Doclet`, DCA et DCC des classes `Doclet`
- le source de la commande `Java2Puml` et du doclet `PumlDoclet`
- rapport hebdomadaire
- diagramme de séquence de l'appel d'un doclet avec les options `-out` et `-d`

#### Bilan de la semaine
Nous avons dû d'abord comprendre le fonctionnement de `javadoc` et des *doclets* afin de pouvoir générer un diagramme de classe à partir d'un package. Nous avons donc commencé par faire un diagramme de séquence de l'appel d'un doclet.
Il a été difficile de comprendre comment fonctionnait les *doclets* et comment les utiliser. Nous avons ainsi dû faire des recherches pour comprendre comment les utiliser et comment les implémenter.
Nous avons ensuite implémenté le doclet `PumlDoclet` qui permet de générer un diagramme de classe à partir d'un package. Nous avons aussi implémenté la commande `Java2Puml` qui permet de générer un fichier `.puml` à partir d'un package.
Nous avons aussi implémenté les options `-out` et `-d` qui permettent de choisir le nom du fichier `.puml` et le dossier de sortie.
Ajout de toutes les fakes classes, interfaces et énumérations. Elles s'ont automatiquement completes avec les methods de PumlDoclet.
Ces classes sont faites des classes Methods et attributes ainsi que d'autres elements.
Ajout de la création du fichier avec les options --out et --d. Ensuite si la valeur n'est pas choisi alors on met le nom de fichier initial et le dossier de sortie est le dossier courant.
Puis on lance la classe pour générer le fichier .puml et l'ecrire.

---

## Semaine 2 : Java Language API et DCA sans associations
### Objectif
- Produire le DCA avec les associations :
- les généralisations et les réalisations
- les agrégations

### Rendus
- DCC de Java Language API
- DCA et DCC API pumlFromJava
- commande Java2Puml de production d'un DCA sans relations
- rapport hebdomadaire

### Bilan de la semaine
Ajout de la classe CreateFic qui permet de créer le fichier.
Nous avons ajouter les classes et les attributs de type primitif. Nous avons aussi terminé le fait de mettre les fonctions avec leur visibilité. Nous pouvons également afficher tous les attributs quelques soit leurs types. Nous n'avons pas considéré le type String comme un type primitif car il est un objet. Nous avons aussi ajouté les classes, les interfaces et les enumeration.
Pour activer l'affichage des méthodes, il faut commenter ou décommenter les fonctions drawMethods dans la classe PumlDoclet.
