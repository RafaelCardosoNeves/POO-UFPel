/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personagens;


import java.util.HashMap;
import java.util.Map;

import itens.Item;
import java.util.Set;


/**
 *
 * @author Neves
 */
public class Chefao extends Vilao{
    private Map<String, Item> mochila;
    private final HashMap loot;
    
    public Chefao(String nome, int energia,int gold,int defesa ,int dano,int armor) {
        super(nome, energia, gold,defesa,dano, armor);
        mochila = new HashMap();
        this.loot = new HashMap();

    }
    public void insertItem(Item item) { 
		this.mochila.put(item.pegaNome(), item);
                
                
	}
	
	/**
	 * Método para remover um item do inventario do heroi através do nome do item.
	 * @param nome. O nome do item deve ser passado por parametro.
	 * @return item. Retorna o item removido do inventario.
	 */
	public Item removeItem(String nome) {
		Item item = this.mochila.get(nome);
		if (item != null)
			this.mochila.remove(nome);
		else
			System.out.println("\n# O item '" + nome + "' nao esta na mochila do heroi!\n");
		return item;
	}
	
	/**
	 * Método de acesso a um item do inventario através do seu nome
	 * @param nome. String que deve conter o nome do item buscado
	 * @return item. Retorna o item correspondente ao nome passado por parametro
	 */
	public Item getItem(String nome){
    	return this.mochila.get(nome);
    }
	
	/**
	 * Método de acesso a um item dropado aleatoriamente do inventario do Vilão Chefe
	 * @return item retorna o item a ser dropado do inventario do Vilão Chefe
	 */
	
public Item getDrop(){
	
		return getItem("arma");
	}
 
	
}


