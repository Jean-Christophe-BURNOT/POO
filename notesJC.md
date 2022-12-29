# Questions


## 4.1.2 Commentaires
Les commentaires en java sont gérées de la même
manière qu'en C.

## 4.1.3 Les bonnes pratiques
- Est-ce que le code est commenté?
- Est-ce que les variables respectent le même schéma (camelCase, snake_case)?
- La portée des variable?
- etc

## 4.1.4 Attributs
1. Les Types construits sont "Points", "Etat", "Sexe" (coord, etat, sexe).
2. Les Types primitifs sont des "int" (currentId, id, age).
3. Les attributs de classe sont "currentId" qui est un attribut statique, tous les autres sont des attributs d'instances.
4. Etat et Sexe sont des énumérations elles ont leurs propre fichier java. 
5. L'objet Etat n'est pas primitif si on ne l'initialise pas, il prendra la valeur déjà présente dans la mémoire.
6. Onillustre ici le pilier de l'Abstraction. On déclare les différentes variables sans les utiliser.

## 4.1.5 Les constructeurs
1. C'est le mot "this"
2. Oui c'est ce que l'on a utilisé pour Animals.
3. Oui on le peut mais ce n'est pas très réaliste.
4. Oui on le peut désormais, le code va aller vers le constructeur adéquat et il va lui attribuer les coordonées (0,0)

## 4.1.6 Création des accesseurs et des mutateurs
Les Mutateurs (getters et setters) sont des méthodes qui respectivement permettent de lire ou d'écrire un valeur. La plupart du temps leurs accessibilité est public.
Ils permettent de faire de la saisie protégée ou de rajouter des conditions pour la modification d'une variable
Ils permettent également de structurer mieux le code et d'éviter que on place tous les attributs en public. La bonne pratique est de mettre les attributs en privés et intéragir avec par le biai des mutateurs.
La bonne pratique est de dire que seules le méthodes doivent permettre aux objets d'intéragir (code plus structuré et plus logique).
1. Les mutateurs sont crées.
2. Les mutateurs fonctionnent 
3. Les énumérations définissent quels sont les différentes possibilités, ce n'est pas le cas d'un objet "classique" 
4. Les tests se sont avérés concluents
5. On remarque que sans les accesseurs, au niveau du main de la classe on peut récupérer et modifier les attributs même si ils sont en private. Celà est du au fait que l'ont intéragit avec les attributs en étant dans la même classe.
6. Je ne vois pas l'interêt de mettre un accesseur en privé (c'est le seul accès qui me paraît inutile). En effet, les mutateurs permettent des interactions entre les classes (Accès et modification d'attributs), placer ces méthode en privé, empêcherait ces méthodes de réaliser ce pourquoi elles ont été concues.

## 4.1.7 Programmation des méthodes
### 4.1.7.1 La méthode vieillir
1. La bonne manière semble celle où on utilise les mutateurs codés précédement
2. J'ai changé d'avis les mutateur ont une utilité à être privés. En effet, on peut imaginer que l'age ne puisse être changé que par des éléments de la classe animal, le rôle du mutateur sera alors simplement d'ajouter une contrainte à ce changement d'attribut. En effet le fait de faire un mutateur pour changer proprement l'age dans notre classe nous permet d'éviter qu'un élément se mette à rajeunir. 