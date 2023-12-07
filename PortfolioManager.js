const prompt = require('prompt-sync')();

class PortfolioManager {
    constructor() {
        this.portfolio = {};
    }

    addAsset(assetName, quantity, price) {
        if (assetName in this.portfolio) {
            this.portfolio[assetName]['quantity'] += quantity;
            this.portfolio[assetName]['price'] = price;
        } else {
            this.portfolio[assetName] = { 'quantity': quantity, 'price': price };
        }
    }

    removeAsset(assetName) {
        if (assetName in this.portfolio) {
            delete this.portfolio[assetName];
        } else {
            console.log(`${assetName} n'est pas dans le portefeuille.`);
        }
    }

    updateAsset(assetName, quantity, price) {
        if (assetName in this.portfolio) {
            this.portfolio[assetName]['quantity'] = quantity;
            this.portfolio[assetName]['price'] = price;
        } else {
            console.log(`${assetName} n'est pas dans le portefeuille. Utilisez l'option 'Ajouter un actif'.`);
        }
    }

    viewPortfolio() {
        if (Object.keys(this.portfolio).length === 0) {
            console.log("Le portefeuille est vide. Ajoutez d'abord un actif.");
        } else {
            console.log("Portefeuille actuel:");
            for (const [asset, data] of Object.entries(this.portfolio)) {
                console.log(`${asset}: ${data['quantity']} unités à ${data['price']} par unité`);
            }
        }
    }

    calculateBalance() {
        let balance = 0;
        for (const [asset, data] of Object.entries(this.portfolio)) {
            balance += data['quantity'] * data['price'];
        }
        console.log(`Solde total du portefeuille: ${balance} Ariary`);
    }
}

const portfolioManager = new PortfolioManager();

let quantity;

while (true) {
    console.log("\n1. Ajouter un actif");
    console.log("2. Supprimer un actif");
    console.log("3. Mettre à jour un actif");
    console.log("4. Consulter le portefeuille");
    console.log("5. Calculer le solde");
    console.log("6. Quitter");

    const choice = prompt("Choisissez une option (1-6): ");

    if (choice === '1') {
        const assetName = prompt("Nom de l'actif: ");
        let quantity;
        while (true) {
            const quantityInput = prompt("Quantité: ");
            quantity = parseFloat(quantityInput);

            if (!isNaN(quantity) && quantity > 0 && quantity % 1 === 0) {
                break;
            } else {
                console.log("Erreur : La quantité doit être un nombre entier.");
            }
        }
        let price
        while (true) {
            const priceInput = prompt("Prix par unité: ");
            price = parseFloat(priceInput);

            if (!isNaN(price)) {
                break;
            } else {
                console.log("Erreur : Veuillez entrer un prix valide.");
            }
        }

        portfolioManager.addAsset(assetName, quantity, price);
    } else if (choice === '2') {
        const assetName = prompt("Nom de l'actif à supprimer: ");
        portfolioManager.removeAsset(assetName);
    } else if (choice === '3') {
        const assetName = prompt("Nom de l'actif à mettre à jour: ");

        let quantity;
        while (true) {
            const quantityInput = prompt("Quantité: ");
            quantity = parseFloat(quantityInput);

            if (!isNaN(quantity) && quantity > 0 && quantity % 1 === 0) {
                break;
            } else {
                console.log("Erreur : La quantité doit être un nombre entier.");
            }
        }

        let price
        while (true) {
            const priceInput = prompt("Prix par unité: ");
            price = parseFloat(priceInput);

            if (!isNaN(price)) {
                break;
            } else {
                console.log("Erreur : Veuillez entrer un prix valide.");
            }
        }
        portfolioManager.updateAsset(assetName, quantity, price);
    } else if (choice === '4') {
        portfolioManager.viewPortfolio();
    } else if (choice === '5') {
        portfolioManager.calculateBalance();
    } else if (choice === '6') {
        console.log("Programme terminé.");
        break;
    } else {
        console.log("Option invalide. Veuillez choisir une option de 1 à 6.");
    }
}
