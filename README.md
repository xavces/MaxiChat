# Maxi Chat

Le maxi chat est un chat à but de discution pour les étudiants d'Ynov.

Etudiants ayant participé au projet :
* BOZUKLIAN Maxime
* LIM-QUARTIER Sereinirornn 
* PAVE Tanguy
* COSTA Maximilien

## Suivi d'installation

### API

Nous avons mis en place un serveur pour que l'application s'y connecte:
> https://github.com/Dudusk/api_maxichat

Pour l'installation, tout est fait dans le Readme de l'API.

### Application

Il faudra obtenir l'adresse ip de l'ordinateur où il y a l'API avec :
> ipconfig sous windows 

ou 

> ifconfig sous mac.

Ensuite, il faudra modifié l'adresse IP du serveur dans les fichiers :
* MessageActivity.kt

Une fois le serveur installé, il ne restera plus qu'à lancer l'application sur un émulateur ou un téléphone connécté au même réseau que l'API.
