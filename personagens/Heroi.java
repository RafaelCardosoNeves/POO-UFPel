package personagens;

import java.util.Set;
import java.util.HashMap;
import java.util.Iterator;
import personagens.Personagem;
import itens.*;
import java.util.Map;

public class Heroi extends Personagem {
	private int energiaMaxima;
	private int limiteDePeso;
	private int comida;
        private int pocoes;
        private Map<String, Item> mochila;
        private Permanente equipArma;
      
  
        private Permanente equipShield;
       private Permanente equippedArmor;

        
	
	public Heroi(String nome, int energia, int energiaMaxima, int limiteDePeso,int gold,int defesa,int dano,int armor) {
		super(nome, energia,gold,defesa,dano,armor);
		this.energiaMaxima = energiaMaxima;
		this.limiteDePeso = limiteDePeso;
		mochila = new HashMap();
	}
	
	private int calcularPeso() {
		int pesoTotal = 0;
		for(Item item : mochila.values()) {
			pesoTotal += item.pegaPeso();
		}
                if(this.getGold()>1000){
			pesoTotal+= (this.getGold()/1000)+1; //Calcula o peso do ouro acima de 1000 moedas
		}
                else{
                pesoTotal+=1;
                }
                for(int i=0; i<comida-1; i++){
			pesoTotal+=this.getItem("Comida").pegaPeso();}
                for(int i=0; i<pocoes-1; i++){
			pesoTotal+=this.getItem("pocoes").pegaPeso();        
                }		
                
                return pesoTotal;
	
     
        }
        public void feed() {
		incremento();
		incremento();
	}
	
    
        public Item getItem(String nome){
    	return this.mochila.get(nome);
    }
	public boolean inserirItem(Item item) {
		if (calcularPeso() + item.pegaPeso() <= limiteDePeso) {
			this.mochila.put(item.pegaNome(), item);
                 	if(item instanceof Consumivel){
				if(item.pegaNome()=="Potion"){
					this.pocoes++;
				}else{
					this.comida++;
			
                                }
                                
			}
                        return true;
                }  else{
                        }
            return false;
        }
        public String getInventario(){ // Criei esse metodo que exibe a mochila do personagem		
		// OBS: Modifiquei esse mÃ©todo para ter funcionamento igual aos outros comandos, exemplo: look
		String returnString = "\n====================================================\n"+"Inventario:";
		returnString += "\n\tLife: "+this.pegaEnergia()+" / "+this.getMaximumLife();
		returnString += "\n\tDamage: "+this.getDano();
		returnString += "\n\tDefense: "+this.getDefensa();
		returnString += "\n\tArmor: "+this.getArmor();
                Set<String> keys = this.mochila.keySet();
		for(String nome : keys) {
			if(this.getItem(nome) instanceof Consumivel){
				if(this.getItem(nome).pegaNome()=="Potion"){
					returnString += "\n\t"+this.getItem(nome).pegaDescricao()+" ("+this.pocoes+")";
				}else{
					returnString += "\n\t"+this.getItem(nome).pegaDescricao()+" ("+this.comida+")";
				}
			}else
				returnString += "\n\t" + this.getItem(nome).pegaDescricao();
		}
		
                
                returnString += "\nGold: \n\t"+getGold()+"\n";
		returnString += "\nWeight: \n\t"+calcularPeso()+" / "+this.limiteDePeso+"\n";
		returnString += this.getEquippedItens()+"\n";
		
                return returnString+"====================================================\n";
        }		

        
        public String getEquippedItens(){//INFORMA OS ITENS QUE ESTAO EQUIPADOS COM O HEROI
		
		String returnString = "\nEquipped Itens:";
		if (this.equipArma==null){
			returnString += "\n\tArma: None";
		}else{
			returnString += "\n\tArma: "+this.equipArma.pegaDescricao();
		}
		if (this.equippedArmor==null){
			returnString += "\n\tArmor: None";
		}else{
			returnString += "\n\tArmor: "+this.equippedArmor.pegaDescricao();
		}
		if (this.equipShield==null){
			returnString += "\n\tShield: None";
		}else{
			returnString += "\n\tShield: "+this.equipShield.pegaDescricao();
		}
	
		return returnString;
	
}

        
	
        public Item removerItem(String nome) {
		Item item = mochila.get(nome);
		if (item != null)
			mochila.remove(nome);
		else
			System.out.println("\n# O item '" + nome + "' nao esta na mochila do heroi!\n");
		return item;
	}
	
	public void alimentar() {
		incremento();
		incremento();
	}
	
	public Permanente getEquipShield() {
		return this.equipShield;
	}
	public void imprimir() {
		System.out.println("#####################");
		System.out.println("# Dados do Heroi");
		super.imprimir();
	}
	
	public int pegaEnergiaMaxima() {
		return energiaMaxima;
	}
       public Permanente getEquipArma() {
		return this.equipArma;
	}

	/**
	 * MÃ©todo modificador da armadura equipada
     * @param equipaArma
	 
	 */
	public void setEquipArma(Permanente equipaArma) {
		if(this.equipArma!= null){ // Se jÃ¡ tiver um item equipado entÃ£o coloca ele no inventario
			this.inserirItem(this.getEquipArma());
                        
		}
		this.equipArma = equipArma;
                
	}
      
        
       
	
  
        public void setEquippedArmor(Permanente equippedArmor) {
		if(this.equippedArmor!= null){ // Se jÃ¡ tiver um item equipado entÃ£o coloca ele no inventario
			this.inserirItem(this.getEquippedArmor());
		}
		this.equippedArmor = equippedArmor;
	}
             public Permanente getEquippedArmor() {
		return this.equippedArmor;
	}
public void setEquipShield(Permanente equipShield) {
		if(this.equipShield != null){ // Se jÃ¡ tiver um item equipado entÃ£o coloca ele no inventario
			this.inserirItem(this.getEquipShield());
		}
		this.equipShield = this.equipShield;
}
               
	
	
public void setequipArma(Permanente equipAmar) {
		if(this.equipArma != null){ // Se jÃ¡ tiver um item equipado entÃ£o coloca ele no inventario
			this.inserirItem(this.getEquipArma());
		}
		this.equipArma = equipAmar;
	}
public int getDefensa(){
		if (this.equipShield==null){
			return super.getDefensa();
		} else{
			return (super.getDefensa()+this.equipShield.getDefBonus());
		}
	}

  

@Override
    public int getMaximumLife() {
      	
			return this.energiaMaxima;
	}
       
	
      
}
