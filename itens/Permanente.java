/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itens;

/**
 *
 * @author Neves
 */
public class Permanente extends Item {
    private int atkBonus;
	private int armBonus;
	private int defBonus;
	private int lifeBonus;
        
        
        
    public Permanente(String nome, String descricao, int peso,int atkBonus,int defBonus,int armBonus) {
        super(nome, descricao, peso);
       
		this.atkBonus=atkBonus;
		this.defBonus=defBonus;
		
		this.armBonus= armBonus;
    }
    
    
    public int getDefBonus(){
		return this.defBonus;
	}
	
	/**
	 * Método de acesso ao Ataque Bonus do item
	 * @return this.atkBonus retorna um inteiro com adicional de ataque do item
	 */
	public int getAtkBonus(){
		return this.atkBonus;
	}
	
	/**
	 * Método de acesso ao Bonus de Armadura do item
	 * @return this.armBonus retorna um inteiro com o adicional de armadura do item
	 */
	public int getArmBonus() {
		return this.armBonus;
	}
        
	
}
    

