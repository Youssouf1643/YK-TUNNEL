# YK TUNNEL — projet Android de départ

Cette archive contient une première interface Android native en Kotlin.

## Contenu
- Nom de l'application : YK TUNNEL
- Thème bleu nuit et bleu électrique
- Champs : serveur, port UDP (5667 par défaut), mot de passe
- Boutons Connecter et Déconnecter
- Validation basique des champs

## Ouvrir le projet
1. Installe Android Studio.
2. Décompresse l'archive.
3. Dans Android Studio, choisis **Open** et sélectionne le dossier `YK_TUNNEL_Android`.
4. Accepte la synchronisation Gradle.
5. Lance l'application sur un émulateur ou un téléphone Android.

## Important : le tunnel n'est pas encore implémenté
Cette version est uniquement le squelette de l'interface. Le bouton Connecter valide les champs, mais n'établit pas de connexion VPN. Pour une connexion réelle, il faut intégrer un client compatible avec le protocole ZIVPN UDP utilisé par le serveur, vérifier les conditions de licence du code client et implémenter le service Android `VpnService`. Ne présente pas cette version comme un VPN fonctionnel.

Le port par défaut 5667 vient de la configuration du serveur communiquée dans la conversation. Assure-toi que le trafic UDP est autorisé dans le pare-feu du VPS et chez l'hébergeur.

## Sécurité
Le mot de passe n'est ni enregistré ni envoyé par cette interface de démonstration. Ne mets jamais de mot de passe réel dans le code source ou dans un dépôt public. Puisque le mot de passe précédent a été partagé dans une conversation, remplace-le sur le serveur avant une utilisation réelle.
