import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class PortfolioManager {
    private Map<String, Map<String, Double>> portfolio;

    public PortfolioManager() {
        this.portfolio = new HashMap<>();
    }

    public void addAsset(String assetName, int quantity, double price) {
        if (portfolio.containsKey(assetName)) {
            Map<String, Double> assetData = portfolio.get(assetName);
            assetData.put("quantity", assetData.get("quantity") + quantity);
            assetData.put("price", price);
        } else {
            Map<String, Double> assetData = new HashMap<>();
            assetData.put("quantity", (double) quantity);
            assetData.put("price", price);
            portfolio.put(assetName, assetData);
        }
    }

    public void removeAsset(String assetName) {
        if (portfolio.containsKey(assetName)) {
            portfolio.remove(assetName);
        } else {
            System.out.println(assetName + " n'est pas dans le portefeuille.");
        }
    }

    public void updateAsset(String assetName, int quantity, double price) {
        if (portfolio.containsKey(assetName)) {
            Map<String, Double> assetData = portfolio.get(assetName);
            assetData.put("quantity", (double) quantity);
            assetData.put("price", price);
        } else {
            System.out.println(assetName + " n'est pas dans le portefeuille. Utilisez l'option 'Ajouter un actif'.");
        }
    }

    public void viewPortfolio() {
        if (portfolio.isEmpty()) {
            System.out.println("Le portefeuille est vide. Ajoutez d'abord un actif.");
        } else {
            System.out.println("Portefeuille actuel:");
            for (Map.Entry<String, Map<String, Double>> entry : portfolio.entrySet()) {
                String asset = entry.getKey();
                Map<String, Double> data = entry.getValue();
                System.out.printf("%s: %.0f unités à %.2f par unité%n", asset, data.get("quantity"), data.get("price"));
            }
        }
    }

    public void calculateBalance() {
        double balance = 0;
        for (Map.Entry<String, Map<String, Double>> entry : portfolio.entrySet()) {
            Map<String, Double> data = entry.getValue();
            balance += data.get("quantity") * data.get("price");
        }
        System.out.printf("Solde total du portefeuille: %.2f Ariary%n", balance);
    }

    public static void main(String[] args) {
        PortfolioManager portfolioManager = new PortfolioManager();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Ajouter un actif");
            System.out.println("2. Supprimer un actif");
            System.out.println("3. Mettre à jour un actif");
            System.out.println("4. Consulter le portefeuille");
            System.out.println("5. Calculer le solde");
            System.out.println("6. Quitter");

            System.out.print("Choisissez une option (1-6): ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Nom de l'actif: ");
                    String assetName = scanner.nextLine();

                    while (true) {
                        try {
                            System.out.print("Quantité: ");
                            int quantity = Integer.parseInt(scanner.nextLine());
                            portfolioManager.addAsset(assetName, quantity, 0);
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("Erreur : La quantité doit être un nombre entier.");
                        }
                    }

                    System.out.print("Prix par unité: ");
                    double price = Double.parseDouble(scanner.nextLine());
                    portfolioManager.addAsset(assetName, 0, price);
                    break;

                case "2":
                    System.out.print("Nom de l'actif à supprimer: ");
                    assetName = scanner.nextLine();
                    portfolioManager.removeAsset(assetName);
                    break;

                case "3":
                    System.out.print("Nom de l'actif à mettre à jour: ");
                    assetName = scanner.nextLine();

                    while (true) {
                        try {
                            System.out.print("Nouvelle quantité: ");
                            int quantity = Integer.parseInt(scanner.nextLine());
                            portfolioManager.updateAsset(assetName, quantity, 0);
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("Erreur : La quantité doit être un nombre entier.");
                        }
                    }

                    System.out.print("Nouveau prix par unité: ");
                    price = Double.parseDouble(scanner.nextLine());
                    portfolioManager.updateAsset(assetName, 0, price);
                    break;

                case "4":
                    portfolioManager.viewPortfolio();
                    break;

                case "5":
                    portfolioManager.calculateBalance();
                    break;

                case "6":
                    System.out.println("Programme terminé.");
                    scanner.close();
                    System.exit(0);

                default:
                    System.out.println("Option invalide. Veuillez choisir une option de 1 à 6.");
            }
        }
    }
}
