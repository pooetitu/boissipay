## Context

Le SI d’une agence de voyages est composé de nombreux Microservices.

### **Éléments fonctionnels**

Le responsable du SI souhaite mettre en œuvre un système de paiement différé pour ses clients grands compte (ex : le comité d’entreprise d’une très grande entreprise qui pré-réserve des voyages pour ses collaborateurs).

Le principe est que ces clients, après souscription à un contrat de paiement différé, pourront payer leurs achats de voyages en fin du mois.

### **Éléments techniques**

Ce service de paiement différé sera supporté principalement par deux Microservices : le Microservice de facturation et le Microservice de gestion de contrat.

### 1. Service de facturation

Le service de facturation accumule des opérations (principalement des achats) et génère à la fin du mois une facture.

Le Microservice de facturation a les fonctions suivantes :

- Enregistrement des souscripteurs à ce contrat
- Acception des opérations (achat ou remboursement)
- Gestion des encours : accumulation des achats
- Run de facturation : génération d’une facture à la fin du mois pour chacun des clients

**Règles de gestion :**

- Un achat est accepté selon les conditions suivantes
    - Le contrat est toujours valide
    - Le mandat de prélèvement est toujours valide
    - Le seuil de l’encours n’est pas dépassé

### 2. Service de contrat

Le service de contrat gère la mastérisation d’un contrat pour son client.

**Règles de gestion :**

- Diffusion des souscriptions au service de facturation
- Diffusion des changements (suspension/annulation des contrats)

### En entrée

- API Swagger Service Facturation
- API Swagger Service de gestion de contrat

### Objectif

Décrire et illustrer l’interaction entre ces deux microservices en prenant en compte les problématiques d’usage

### Recommandations

1. Ne pas utiliser tous les endpoints REST existant
2. Votre design doit limiter les interactions (ex: le système de facturation doit contenir tous les éléments pour savoir si le contrat est actif)
3. Ne pas oublier de publier

### Contraintes

1. Conserver une approche contract-first pour les API REST
    - Possibilité de rajouter /modifier les API données en entrée
2. Utiliser Apache Kafka
    - Un seul broker est suffisant
    - Une seule partition pour chacun des topics est suffisant

### Livrables

- Présentation :
    - Spécifier en détail les usages API ou Event
    - Identifier les choix CQRS
- Code/Démo
    - Se restreindre sur les contrats d’interface, les mécanismes d’interaction
        - Le développement de la logique métier n’est pas attendu
        - Pas de base de données est attendu

## Spécifications