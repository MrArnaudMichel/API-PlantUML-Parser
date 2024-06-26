# Rapport SAE 21
## Projet PumlFromJava
### Membres du groupe
- __MORAWIEC__ Benjamin
- __MICHEL__ Arnaud

### Objectif
Le but de ce projet est de créer un programme qui permet de générer un diagramme de classe à partir d'un package Java. Ce programme doit être utilisable en ligne de commande et doit pouvoir être utilisé avec `javadoc`.

### Organisation
Nous avons décidé de nous répartir le travail de la manière suivante :
Pour la première semaine, nous avons décidé de travailler ensemble sur la compréhension de `javadoc` et des *doclets*.
Pour la répartition :
- __MORAWIEC__ Benjamin : Création du fichier, des options.
- __MICHEL__ Arnaud : Création des classes, des interfaces, des énumérations, des méthodes et des attributs.
- __MICHEL__ Arnaud : Création du DCA versions 1.
- __MORAWIEC__ Benjamin et __MICHEL__ Arnaud : Création des associations.
- __MORAWIEC__ Benjamin : Création des options --out et --d.
- __MORAWIEC__ Benjamin : Amélioration des options.
- __MICHEL__ Arnaud : Création du DCC et des relations avec les types primitifs / non primitifs.
- __MORAWIEC__ Benjamin : Gestions des extends et des implements.
- __MORAWIEC__ Benjamin : Amélioration des options.
- __MICHEL__ Arnaud : Ajout des nouvelles options.
- __MORAWIEC__ Benjamin : Finalisation des commentaires.
- __MICHEL__ Arnaud : Ajout de l'option --use et de tout le système de configuration.
- __MORAWIEC__ Benjamin : Ajout des @Override dans le diagramme.
- __MICHEL__ Arnaud et __MORAWIEC__ Benjamin : Obtention des commentaires javadoc pour les intégrer au diagramme.
- __MICHEL__ Arnaud et __MORAWIEC__ Benjamin : Ajout des commentaires javadoc dans le diagramme.
- __MORAWIEC__ Benjamin et __MICHEL__ Arnaud : Correction des bugs.

### Semaine 1 : les *doclets* de javadoc
#### Objectif
Prendre en main `javadoc`, les *doclets* et leurs options afin de générer dans un fichier `.puml` un diagramme énumérant les éléments sélectionnés.

#### Rendus
- DS `Doclet`, DCA et DCC des classes `Doclet`
- le source de la commande `Java2Puml` et du doclet `PumlDoclet`
- rapport hebdomadaire
- diagramme de séquence de l'appel d'un doclet avec les options `-out` et `-d`

#### Bilan de la semaine
- Nous avons dû d'abord comprendre le fonctionnement de `javadoc` et des *doclets* afin de pouvoir générer un diagramme de classe à partir d'un package. Nous avons donc commencé par faire un diagramme de séquence de l'appel d'un doclet.
- Il a été difficile de comprendre comment fonctionnait les *doclets* et comment les utiliser. Nous avons ainsi dû faire des recherches pour comprendre comment les utiliser et comment les implémenter.
- Nous avons ensuite implémenté le doclet `PumlDoclet` qui permet de générer un diagramme de classe à partir d'un package. Nous avons aussi implémenté la commande `Java2Puml` qui permet de générer un fichier `.puml` à partir d'un package.
- Nous avons aussi implémenté les options `-out` et `-d` qui permettent de choisir le nom du fichier `.puml` et le dossier de sortie.
- Ajout de toutes les fakes classes, interfaces et énumérations. Elles s'ont automatiquement completes avec les methods de PumlDoclet.
- Ces classes sont faites des classes Methods et attributes ainsi que d'autres elements.
- Ajout de la création du fichier avec les options --out et --d. Ensuite si la valeur n'est pas choisi alors on met le nom de fichier initial et le dossier de sortie est le dossier courant.
- Puis, on lance la classe pour générer le fichier .puml et l'écrire.

---

### Semaine 2 : Java Langage API et DCA sans associations
#### Objectif
- Produire le DCA avec les associations :
- les généralisations et les réalisations
- les agrégations

#### Rendus
- DCC de Java Language API
- DCA et DCC API pumlFromJava
- commande Java2Puml de production d'un DCA sans relations
- rapport hebdomadaire

#### Bilan de la semaine
__Pour toutes nos options, il faut deux tirets,__ car nous voulons les séparer des options de 'bases' de javadoc.
- Les instances sont aussi créé dans le constructeur de l'instance. Par exemple pour créer une classe il faut faire Classe(element).
- Ajout de la classe CreateFic qui permet de créer le fichier. Avant tout était dans la même classe. Le puml est maintenant généré également dans la classe respective du nom de l'instance. Par exemple le code pour générer le puml d'une classe est généré dans l'objet.
- Nous avons ajouté les classes et les attributs de type primitif. Nous avons aussi terminé le fait de mettre les fonctions avec leur visibilité. Nous pouvons aussi afficher tous les attributs quelques soit leurs types. Nous n'avons pas considéré le type String comme un type primitif, car il est un objet. Nous avons aussi ajouté les classes, les interfaces et les enumeration. Les constructeurs sont également présents.
- L'option --help a été ajouté pour afficher l'aide. Nous avons aussi ajouté les options --out et --d. Pour afficher tout le diagramme, il faut enlever l'option --dca.

#### Notes d'amélioration
Nous pourrons changer les types qui sont affichés sous cette forme: jdk.javadoc.doclet.Doclet.Option.
Pour le DCA et le DCC de cette semaine, nous l'avons génerer depuis l'API puis modifié. Et donc nous n'avons toujours pas considéré le type String comme un type primitif.

---

### Semaine 3 : DCA avec associations
#### Objectif
- Produire le DCA avec les associations :
- les généralisations et les réalisations
- les agrégations

#### Rendus
- DCA et DCC API pumlFromJava mis à jour
- sources des classes
- rapport hebdomadaire

#### Bilan de la semaine
- Tout d'abord, nous avons, du gérer les extends et les implements. Nous avons donc ajouté cette fonctionnalité dans nos fausses classes Classe et Interface. Nous avons ajouté le write de ces associations dans une fonction qui se fait appeler dans la classe PumlWriter.
- Ensuite, nous avons dû ajouter le fait que nous ne considérons pas le type String comme un type primitif. Pour chaque parametre, nous avons donc regardé si le type est un String et si c'est le cas, nous créons une association vers le type java.lang.String.
- Nous avons aussi ajouté les associations vers les autres types non-primitifs. Nous avons donc ajouté une fonction qui permet de créer une association vers un type non-primitif.
- Il nous a aussi fallu différencier les liste et les elements simples. Si c'est une liste, on rajoute [*] avant le nom de l'élement. Sinon, on met 1.

#### Notes d'amélioration
- L'implémentation récursive des packages n'est pas encore présente. Nous ferons cela la semaine prochaine.
- Rajouter une option pour définir le type String comme un type primitif.
- Rajouter plusieurs options pour mettre/retirer les associations, les constructeurs, les méthodes, les attributs, les types primitifs, les types non-primitifs, les classes, les interfaces, les énumérations, les packages, les extends, les implements.
- (Vérifier possibilité) Rajouter les uses.

---

### Semaine 4 : Production d'un premier DCC

#### Objectif
- Produire un DCC sans les associations.
- Ajouter au doclet une option --dca pour demander la production du DCA.
- Par défaut, le diagramme produit sera le DCC.

#### Rendus
- DCA et DCC API pumlFromJava mis à jour
- sources des classes
- rapport hebdomadaire

#### Bilan de la semaine
- Nous avons fini la gestion des packages. Pour cela, nous avons modifié nos classes afin d'avoir une classe Package. Cela permet d'avoir des packages dans des packages.
- Nous avons aussi ajouté les associations vers les packages. 
- Nous avons également rajouté l'option --str qui permet de considérer le type String comme un type primitif.
- Nous avons aussi rajouté toutes les options de SaveOption qui permettre une plus grande flexibilité de l'API dans la création du diagram.
- Il est également possible de rajouter des descriptions aux classes tel que 'use' pour spécifier toutes les classes que la classe use et 'author' pour spécifier les auteurs de la classe. Il faut mettre un ';' entre chaque option types et ',' entre chaque valeur.

---

### Semaine 5 : Associations et dépendances

#### Objectif
- Produire le DCA et le DCC avec toutes les associations et les dépendances.

#### Rendus
- DCA et DCC API pumlFromJava mis à jour
- sources des classes
- rapport hebdomadaire

#### Bilan de la semaine
- Nous avons finalisé de commenter toute l'API avec la javadoc.
- Nous avons également rajouté l'option --use qui permet d'activer ou pas les dépendances.
- Et nous avons ajouté les Doctree à l'API pour prendre en compte les commentaires. Ils peuvent notamment servir pour le nommage des associations. Pur cela, on peut ajouter le tag @pumlNameAssociation @pumlUse @pumlMultiplicity @pumlAgregation (par default) @pumlComposition 
- Ajout de l'option --config aui prend en paramètre un fichier de config afin de mettre directement les paramètres dans un fichier de configuration. Tout est géré dans la methode static setConfig de la classe Config.
- __Ajout du dossier javadocs qui contient la javadoc de l'API.__

---

### Semaine 6 : Finalisation

#### Objectif
- Préparation du rendu final et de la présentation

#### Rendus
- DCA et DCC API pumlFromJava mis à jour
- sources des classes
- rapport hebdomadaire

#### Bilan de la semaine
Nous avons corrigé les bugs de notre application et vérifié tout le code. Nous avons aussi fait la javadoc de notre application.
Nous avons aussi fait la présentation de notre application.

---

## Conclusion

Nous avons réussi à produire un DCA et un DCC avec toutes les associations et les dépendances.
Nos plus grandes difficultés ont été de comprendre comment fonctionnait l'API de javadoc et de la réutiliser pour produire notre diagramme.
Nous avons aussi eu du mal à comprendre comment créer un DocTree car nous n'avions pas vu que DocletEnvironment avait une méthode pour cela.
Pour finir, nous sommes vraiment content du résultat, car l'API est très souple notamment dans le choix des options ce qui permet de faire un diagramme très personnalisé.

---

__MICHEL__ Arnaud et __MORAWIEC__ Benjamin

## Annexes

### Diagramme de l'API

#### Digramme de classe détaillé
![Diagramme DCC de l'API](../uml/Semaine6/DCC_API.png)

#### Diagramme de classe simplifié
![Diagramme DCA de l'API](../uml/Semaine6/DCA_API.png)