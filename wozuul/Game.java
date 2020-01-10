package wozuul;
import personagens.*;

import personagens.Heroi;
import personagens.Personagem;
import personagens.Vilao;
import itens.*;
import static java.lang.Compiler.command;
import personagens.Chefao;


public class Game 
{
    private Parser parser;
    private Room currentRoom;
    private Heroi heroi;
    private Chefao chefao;
        
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
        heroi = new Heroi("Batman", 10, 20, 100,100,0,3,0);
       
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room EntradaPrincipal, teatro, pub, lab, office,entradasegundo,corredor1,corredor2,corredor3,salaSecreta,escada,auditorio;
      
        // create the rooms
        EntradaPrincipal = new Room("voce esta em Entrada Principal");
        teatro = new Room("voce esta emTeatro");
        pub = new Room("voce esta em Pub");
        lab = new Room("voce esta emlab");
        office = new Room("voce esta em office");
        entradasegundo= new Room ("voce esta em entrada segundo andar");
        corredor1 =new Room("voce esta em corredor 1");
        corredor2 =new Room("voce esta em corredor 2");
        corredor3 =new Room("voce esta em corredor 3");
        salaSecreta = new Room("voce esta em Sala Secreta");
        escada= new Room("voce esta em acesso ao segundo andar ");
        auditorio=new Room("voce esta em auditorio");

        
        // initialise room exits
        //Entrada Principal
        EntradaPrincipal.setExit("east", teatro);
        EntradaPrincipal.setExit("south", corredor1);
        EntradaPrincipal.setExit("west", pub);
       
        //Corredor1
        corredor1.setExit("south",corredor2);
        corredor1.setExit("north",EntradaPrincipal);

        //Corredor2
        corredor2.setExit("north",corredor1);
        corredor2.setExit("east",office);
        corredor2.setExit("west",escada);
        //office
        office.setExit("west", corredor2);
        office.setExit("east", auditorio);
        Personagem capanga4 = new Vilao("Capanga4", 2,3,0,4,0);

           
       
        
        //teatro
        teatro.setExit("west", EntradaPrincipal);
        //Pub
        pub.setExit("east", EntradaPrincipal);
        Personagem capanga3 = new Vilao("Capanga3", 2,2,0,4,0);
        pub.inserirPersonagem(capanga3);

        //escada
        escada.setExit("east",corredor2);
        escada.setExit("south", entradasegundo);
        
        //Entrada 2 andar
        entradasegundo.setExit("north", teatro);
        entradasegundo.setExit("east", corredor3);
                Personagem capanga5 = new Vilao("Capanga1", 2,3,0,4,0);

        //corredor3
        corredor3.setExit("east", lab);
        corredor3.setExit("west",entradasegundo);
        
        //lab
        lab.setExit("east", salaSecreta);
        lab.setExit("west", corredor3);
        Personagem capanga1 = new Vilao("Capanga1", 2,3,0,4,0);
        Personagem capanga2 = new Vilao("Capanga2", 2,6,0,4,0);
        lab.inserirPersonagem(capanga1);
        lab.inserirPersonagem(capanga2);
        
        //sala secreta
                 
        salaSecreta.setExit("west",lab);
       Chefao charada = new Chefao("charada", 8,10,2,4,0);
        salaSecreta.inserirPersonagem(charada);

        //auditorio
        auditorio.setExit("west", office);

     
        
        
        
      
        
        
        
        //ITENS
       
        Permanente arma = new Permanente("arma","pistola", 20,10,0,0);
        Permanente Armadura = new Permanente("armadura","armadura", 20,0,0,5);
        Permanente escudo = new Permanente("escudo", "escudo", 15,0,10,0);
        Consumivel potion = new Consumivel("Potion", "Potion",  20);
        Consumivel Comida = new Consumivel("Comida", "Comida", 10);
       

        
       Item item3 = new Item("Potion", "Potion", 2);
        
        lab.insertItem(item3);
       
        pub.insertItem(Armadura);       
        pub.insertItem(arma);
        lab.insertItem(Comida);
        pub.insertItem(Comida);
        salaSecreta.insertItem(potion);
        salaSecreta.insertItem(escudo);
        teatro.insertItem(escudo);
        corredor2.insertItem(potion);
         escada.insertItem(potion);
//heroi.inserirItem(arma);
//       heroi.inserirItem(potion);

        Chefao coringa = new Chefao("Coringa", 7,100,0,4,0);
       
        
        
        
        office.inserirPersonagem(coringa);
        coringa.insertItem(arma);
        charada.insertItem(arma);
        currentRoom = EntradaPrincipal;  // start game outside
    
        }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        if(commandWord == CommandWord.UNKNOWN) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        if (commandWord == CommandWord.HELP) {
            printHelp();
        }
        else if (commandWord == CommandWord.GO) {
            goRoom(command);
        }
        else if (commandWord == CommandWord.QUIT) {
            wantToQuit = quit(command);
        }
        else if (commandWord == CommandWord.LOOK) {
        	look();
        }
        else if (commandWord == CommandWord.ATTACK) {
        	attack(command);
        }
        else if (commandWord == CommandWord.PICK) {
        	
                pick(command);
        }
        else if (commandWord == CommandWord.INVENTARIO) {//comando para ver inventario
            inventario();
        }
        else if (commandWord == CommandWord.DROP) {//adicionado comando drop
            drop(command);
        }
        else if (commandWord == CommandWord.USE) {//adicionado comando USE
               use(command);
        }
        
        // else command not recognised.
        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    /** 
     * Try to go to one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
        }
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }

    private void look()
    {
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
    }
    private void pick(Command command){ // METODO PARA PEGAR ITENS
        if(!command.hasSecondWord()){
            System.out.println("Pick what?");
            return;
        }
   
     Item item = this.currentRoom.pegaItem(command.getSecondWord());
        if(item != null){
            if(this.heroi.inserirItem(item)){ 
                System.out.println("\nVoce pegou  "+item.pegaNome()+"\n");
                this.currentRoom.removeItem(item);
            }else{
                System.out.println("\nNao pode Pegar o item "+item.pegaNome()+",  liberar espaco  "+item.pegaPeso()+"\n");
            }
        }else {
            System.out.println("O item '" + command.getSecondWord() + "' não está na sala .\n");
        
        }
    }
    
    private void drop(Command command){//METODO QUE DROPA ITENS
        if(!command.hasSecondWord()){
            // if there is no second word, I do not know what to drop... 
            System.out.println("drop qual item?");
            return;
        }

        Item item = this.heroi.getItem(command.getSecondWord());
        if(item != null){
                if(this.heroi.getEquipArma()!= null && this.heroi.getEquipArma().pegaNome() == item.pegaNome()){
                System.out.println("\nIt is not possible to drop an item equipped!!\n");
                return;
            }
            
           if(this.heroi.getEquipShield()!=null && this.heroi.getEquipShield().pegaNome() == item.pegaNome()){
                System.out.println("\nIt is not possible to drop an item equipped!!\n");
                return;
            }
           if(this.heroi.getEquippedArmor()!= null && this.heroi.getEquippedArmor().pegaNome() == item.pegaNome()){
                System.out.println("\nItNão é possível largar  item equipado !!!!\n");
                return;
            }
    
            this.heroi.removerItem(item.pegaNome());
            System.out.println("\nO item "+item.pegaNome()+" foi removido!\n");
            this.currentRoom.insertItem(item);
             } else{
            System.out.println("\nO item '" + command.getSecondWord() + "' nao esta no invetario.\n");
        }
        }
   
 
    
    
    
    private void use(Command command){//METODO QUE USA ITENS
        if(!command.hasSecondWord()){
            // if there is no second word, we don't know where to go... 
            System.out.println("usa qual item?");
            return;
        }

        Item item = this.heroi.getItem(command.getSecondWord());

        if(item != null){
            if (item instanceof Permanente){//VERIFICA SE E UM ITEM PERMANENTE, CASO FOR, ALTERA O ITEM EQUIPADO DO HEROI PELO NOVO ITEM
                if (((Permanente) item).getAtkBonus()>0){//Verifica se Ã© uma arma
                    Permanente newArma = (Permanente)item;
                    this.heroi.setequipArma(newArma);
                    this.heroi.removerItem(newArma.pegaNome()); // Retira o item do inventario pois estÃ¡ equipado
                    System.out.println("\nNova Arma: "+newArma.pegaNome()+"\n");
                    this.heroi.setdano(newArma.getAtkBonus());
                }
              
                if (((Permanente) item).getDefBonus()>0){//verifica se Ã© um escudo
                    Permanente newShield = (Permanente)item;
                    this.heroi.setEquipShield(newShield);
                    this.heroi.removerItem(newShield.pegaNome()); // Retira o item do inventario pois estÃ¡ equipado
                    System.out.println("\nnove defesa equipada: "+newShield.pegaNome()+"\n");
                    this.heroi.setdefesa(newShield.getDefBonus());
                }
                if (((Permanente) item).getArmBonus()>0){//Verifica se Ã© uma armadura
                    Permanente newArmor = (Permanente)item;
                    this.heroi.setEquippedArmor(newArmor);
                    this.heroi.removerItem(newArmor.pegaNome()); // Retira o item do inventario pois estÃ¡ equipado
                    System.out.println("\nnova armadura: "+newArmor.pegaNome()+"\n");
                    this.heroi.setAtributos(newArmor.getArmBonus());
                }
        

            }else{//CASO NAO E PERMANENTE, ENTAO E UM ITEM CONSUMIVEL
                if(this.heroi.pegaEnergia() != this.heroi.getMaximumLife()){
                    if(item.pegaNome()=="Potion"){
                        this.heroi.feed();
                    }else{
                        this.heroi.incremento();
                    }

                    this.heroi.removerItem(item.pegaNome());
                    System.out.println("\nO item "+item.pegaNome()+" Foi usado!");
                    System.out.println(" status atual:");
                    this.heroi.printOut();
                }else{
                    System.out.println("\nSua energia  está cheia!\n");
                }

            }

        }
        else{
            System.out.println("\nO item '" + command.getSecondWord() + "' nao esta no inventario.\n");
        }
    }


        private void inventario() { //METODO QUE VE INVENTARIO, chamando o comando na classe heroi
        System.out.println(this.heroi.getInventario());
    
    }
    
    private void attack(Command command) {
    	if(!command.hasSecondWord()) {
            // if there is no second word, I do not know what to attack...
            System.out.println("Attack quem?");
            return;
        }

        Personagem vilao = this.currentRoom.pegaPersonagem(command.getSecondWord());
        if (vilao != null) {
            if(!vilao.estaMorto()){

                System.out.println("\n"+this.heroi.pegaNome()+" attack "+vilao.pegaNome());
                this.heroi.fight(vilao); //realiza o ataque do heroi

                if(!vilao.estaMorto()){
                    System.out.println(vilao.pegaNome()+" attack "+heroi.pegaNome());
                    vilao.fight(this.heroi); //realiza o ataque do vilao
                }

                System.out.println("==== STATUS FIGHT ====");
                this.heroi.printOut();
                vilao.printOut();

                if(vilao.estaMorto()){
                    this.heroi.addGold(vilao.getGold());
                    System.out.println("Drop gold: "+vilao.getGold()+"\n");
                    if(vilao instanceof Chefao){
                        Item drop = ((Chefao) vilao).getDrop(); // Pega um dos itens do inventario do Boss
                        String returnDropString = "\nDrop Boss: "; 
                        returnDropString+="\n\t"+drop.pegaDescricao();
                        this.currentRoom.insertItem(drop);	// Adiciona item a sala
                        drop = ((Chefao) vilao).getDrop();
                        returnDropString+="\n\t"+drop.pegaDescricao();
                        this.currentRoom.insertItem(drop);
                        System.out.println(returnDropString+"\n"); // Imprime uma string com os itens dropados do Boss
                        if(vilao.pegaNome()=="Coringa"){
                            System.out.println("\nParabens, vc matou o coringa");
                            

                        }
                    }
                    this.currentRoom.removePersonagem(vilao);
                

                if(this.heroi.estaMorto()){
                    
                        System.out.println("\nVoce Morreu!\n");
                    
                       
                        }
                    
                }
            }else{
                System.out.println("\nO inimigo Morreu!\n");
        }
    } else {
            System.out.println("\nThe character '" + command.getSecondWord() + "' nao esta na sala.\n");
        }
    }



    
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Game game = new Game();
		game.play();
	}
	//
        
        
}
