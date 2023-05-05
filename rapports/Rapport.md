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
Ajout de la création du fichier avec les options -out et -d. Pour l'instant deux fichiers sont créés l'un dans le dossier courant avec le bon nom et l'autre dans le dossier choisi avec le mauvais nom.
Nous n'avons pas encore écris dans le fichier mais nous avons réussi à le créer.

---
