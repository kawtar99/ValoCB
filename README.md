# ValoCB

Ce projet est un Kata réalisé pour répondre à l'énoncé suivant:

Nous souhaitons développer une application qui s’appelle ValoCB de reporting pour agréger les
prix des produits détenus par des clients dans différents portefeuilles.

* Un portefeuille contient plusieurs produits. Un produit peut appartenir à un seul portefeuille.
Ex: PTF2 contient les produits X1 et X2

* Un produit peut avoir un ou plusieurs underlyings.
  EX : Le produit P1 contient 3 underlyings U11, U12 et U13

* Chaque client détient plusieurs produits qui peuvent être dans différents portefeuilles.
  Ex : Le client C2 détient les produits
  - 40 P1 et 100 P3 qui sont dans le PTF1
  - 20 X2 qui sont dans le portefeuille PTF2

* L’objectif de l’application est de calculer:
  - Le prix d’un portefeuille en EUR
  - Le prix des produits par client en EUR
 
* On aura en input 3 fichiers csv :
  - Prices.csv
  - Product.csv
  - Forex.csv

* Ces fichiers seront insérés dans les ressources du projets `(src/main/resources)` pour faciliter le
dev.

* L’application doit générer dans le dossier de votre choix 2 fichiers csv :
  - Reporting-portfolio.csv : contient les prix des portefeuilles : 2 colonnes PTF et price
  - Reporting-client.csv : contient les capitaux des clients : 2 colonnes Client et capital
  Ces fichiers son aussi générés dans le dossier `(src/main/resources)`

# Stack technique:
* [Java](https://www.java.com/fr/): le projet est réalisé en java 17
* [Apache Maven](https://maven.apache.org/) : Version 3.8.3
* [JUnit](https://junit.org/junit5) : Version JUnit 5 pour effectuer le test
