class PortfolioManager
  def initialize
    @portfolio = {}
  end

  def add_asset(asset_name, quantity, price)
    if @portfolio.key?(asset_name)
      @portfolio[asset_name]['quantity'] += quantity
      @portfolio[asset_name]['price'] = price
    else
      @portfolio[asset_name] = {'quantity' => quantity, 'price' => price}
    end
  end

  def remove_asset(asset_name)
    if @portfolio.key?(asset_name)
      @portfolio.delete(asset_name)
    else
      puts "#{asset_name} n'est pas dans le portefeuille."
    end
  end

  def update_asset(asset_name, quantity, price)
    if @portfolio.key?(asset_name)
      @portfolio[asset_name]['quantity'] = quantity
      @portfolio[asset_name]['price'] = price
    else
      puts "#{asset_name} n'est pas dans le portefeuille. Utilisez l'option 'Ajouter un actif'."
    end
  end

  def view_portfolio
    if @portfolio.empty?
      puts "Le portefeuille est vide. Ajoutez d'abord un actif."
    else
      puts "Portefeuille actuel:"
      @portfolio.each do |asset, data|
        puts "#{asset}: #{data['quantity']} unités à #{data['price']} par unité"
      end
    end
  end

  def calculate_balance
    balance = 0
    @portfolio.each do |_asset, data|
      balance += data['quantity'] * data['price']
    end
    puts "Solde total du portefeuille: #{balance} Ariary"
  end
end

portfolio_manager = PortfolioManager.new

loop do
  puts "\n1. Ajouter un actif"
  puts "2. Supprimer un actif"
  puts "3. Mettre à jour un actif"
  puts "4. Consulter le portefeuille"
  puts "5. Calculer le solde"
  puts "6. Quitter"

  print "Choisissez une option (1-6): "
  choice = gets.chomp

  case choice
  when '1'
    print "Nom de l'actif: "
    asset_name = gets.chomp
    quantity = 0
    price = 0
    loop do
      begin
        print "Quantité: "
        quantity = Integer(gets.chomp)
        break
      rescue ArgumentError
        puts "Erreur : La quantité doit être un nombre entier."
      end
    end

    loop do
      begin
        print "Prix par unité: "
        price = Float(gets.chomp)
        break
      rescue ArgumentError
        puts "Erreur : Le prix doit être un nombre."
      end
    end

    portfolio_manager.add_asset(asset_name, quantity, price)
  when '2'
    print "Nom de l'actif à supprimer: "
    asset_name = gets.chomp
    portfolio_manager.remove_asset(asset_name)
  when '3'
    print "Nom de l'actif à mettre à jour: "
    asset_name = gets.chomp

    loop do
      begin
        print "Nouvelle quantité: "
        quantity = Integer(gets.chomp)
        break
      rescue ArgumentError
        puts "Erreur : La quantité doit être un nombre entier."
      end
    end

    loop do
      begin
        print "Prix par unité: "
        price = Float(gets.chomp)
        break
      rescue ArgumentError
        puts "Erreur : Le prix doit être un nombre."
      end
    end

    portfolio_manager.update_asset(asset_name, quantity, price)
  when '4'
    portfolio_manager.view_portfolio
  when '5'
    portfolio_manager.calculate_balance
  when '6'
    puts "Programme terminé."
    break
  else
    puts "Option invalide. Veuillez choisir une option de 1 à 6."
  end
end
