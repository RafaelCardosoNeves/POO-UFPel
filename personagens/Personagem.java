package personagens;

import java.util.Random;

public abstract class Personagem {
	private String nome;
	private int energia;
        private int gold;
        private int defesa;
        private int dano;
        private int armor;
       
	private static Random dado = new Random();
        
	
	public Personagem(String nome, int energia,int gold,int defesa ,int dano,int armor ) {
		this.nome = nome;
                this.defesa=defesa;
                this.dano=dano;
		this.energia = energia;
                this.gold=gold;
             
                this.armor=armor;
	}
	
        public void setAtributos(int armor){
		this.armor += armor;
		
		
	}
	
        public void setdano(int dano){
		
		
		this.dano += dano;
	}
        
        public void setdefesa(int defesa){
		
		
		this.defesa += defesa;
	}
        
        
  
        public String pegaNome() {
		return nome;
	}
	
	public int pegaEnergia() {
		return energia;
	}
	
	public abstract int getMaximumLife();
        public boolean estaMorto() {
		if (energia == 0)
			return true;
		else
		    return false;
	}
        public int getDano() {
		return this.dano;
	}
        public int getDefesa() {
		return this.defesa;
	}
	
	public abstract int pegaEnergiaMaxima();
	
	public void incremento() {
		if (energia < pegaEnergiaMaxima())
			energia++;
	}
	public int getDefensa() {
		return this.defesa;
	}
	
	public void decremento(int dano) {
			if(this.getDefensa()>=dano){
			System.out.println("ataque bloqueado!!");
		} else {
			dano-=this.getDefesa();//realiza o "desconto" da defesa no dano do personagem
			if (this.energia-dano > 0){
				this.energia-=dano;
			}else{
				System.out.println("\n# " + this.nome + " is dead!\n");
				this.energia=0;
			}
			
		}
        }
	
	
      
        
        public int getGold(){
		return this.gold;
	}
        
        public int sorte(int valorMaximo) {
		return dado.nextInt(valorMaximo) + 1;
	}
        
        public void addGold(int quantia){
		if(quantia > 0)
			this.gold+=quantia;
	}
        public boolean removeGold(int quantia){
		if(quantia > 0){
			if(this.gold >= quantia){
				this.gold-=quantia;
				return true;
			}
		}
		return false;
	}
	
		public void fight(Personagem oponente) {
		int dado;
		
		if(this instanceof Chefao){
			dado = sorte(25);
		}else
			dado = sorte(20);
		
		if (dado > oponente.getDefesa()) {
			System.out.println("Successfully Attack!!\n");
			oponente.decremento(this.getDano()-oponente.getArmor());
		}else{
			System.out.println("Attack Missed!!\n");
		}
	}
	public int getArmor() {
		return this.armor;
	}
	
	
 	public void imprimir() {
		System.out.println("#####################");
		System.out.println("# Nome: " + nome);
		System.out.println("# Energia: " + energia);
                System.out.println("# dano: " + dano);
                System.out.println("#####################");
	}
       public void printOut() {
		System.out.println("\tName: " + this.nome);
		System.out.println("\tLife: " + this.energia+" / "+this.getMaximumLife());
		System.out.println("======================");
	}



}

