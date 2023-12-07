class PortfolioManager:
    def __init__(self):
        self.portfolio = {}

    def add_asset(self, asset_name, quantity, price):
        if asset_name in self.portfolio:
            self.portfolio[asset_name]['quantity'] += quantity
            self.portfolio[asset_name]['price'] = price
        else:
            self.portfolio[asset_name] = {'quantity': quantity, 'price': price}

    def remove_asset(self, asset_name):
        if asset_name in self.portfolio:
            del self.portfolio[asset_name]
        else:
            print(f"{asset_name} n'est pas dans le portefeuille.")

    def update_asset(self, asset_name, quantity, price):
        if asset_name in self.portfolio:
            self.portfolio[asset_name]['quantity'] = quantity
            self.portfolio[asset_name]['price'] = price
        else:
            print(f"{asset_name} n'est pas dans le portefeuille. Utilisez l'option 'Ajouter un actif'.")

    def view_portfolio(self):
        if not self.portfolio:
            print("Le portefeuille est vide. Ajoutez d'abord un actif.")
        else:
            print("Portefeuille actuel:")
            for asset, data in self.portfolio.items():
                print(f"{asset}: {data['quantity']} unités à {data['price']} par unité")

    def calculate_balance(self):
        balance = 0
        for asset, data in self.portfolio.items():
            balance += data['quantity'] * data['price']
        print(f"Solde total du portefeuille: {balance} Ariary")


portfolio_manager = PortfolioManager()

while True:
    print("\n1. Ajouter un actif")
    print("2. Supprimer un actif")
    print("3. Mettre à jour un actif")
    print("4. Consulter le portefeuille")
    print("5. Calculer le solde")
    print("6. Quitter")

    choice = input("Choisissez une option (1-6): ")

    if choice == '1':
        asset_name = input("Nom de l'actif: ")

        while True:
            try:
                quantity = int(input("Quantité: "))
                break
            except ValueError:
                print("Erreur : La quantité doit être un nombre entier.")

        while True:
            try:
                price = float(input("Prix par unité: "))
                break
            except ValueError:
                print("Erreur : Le prix doit être un nombre.")
        portfolio_manager.add_asset(asset_name, quantity, price)
    elif choice == '2':
        asset_name = input("Nom de l'actif à supprimer: ")
        portfolio_manager.remove_asset(asset_name)
    elif choice == '3':
        asset_name = input("Nom de l'actif à mettre à jour: ")
        while True:
            try:
                quantity = int(input("Nouvelle quantité: "))
                break
            except ValueError:
                print("Erreur : La quantité doit être un nombre entier.")


        while True:
            try:
                price = float(input("Prix par unité: "))
                break
            except ValueError:
                print("Erreur : Le prix doit être un nombre.")
        portfolio_manager.update_asset(asset_name, quantity, price)
    elif choice == '4':
        portfolio_manager.view_portfolio()
    elif choice == '5':
        portfolio_manager.calculate_balance()
    elif choice == '6':
        print("Programme terminé.")
        break
    else:
        print("Option invalide. Veuillez choisir une option de 1 à 6.")
