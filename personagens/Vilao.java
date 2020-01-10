package personagens;

public class Vilao extends Personagem {
	private static final int energiaMaxima = 10;

	public Vilao(String nome, int energia,int gold,int defesa,int dano,int armor) {
		super(nome, energia,gold,defesa,dano,armor);
	}
	
	public int pegaEnergiaMaxima() {
		return energiaMaxima;
	}
	
	public void imprimir() {
		System.out.println("#####################");
		System.out.println("# Dados do Vilao");
		super.imprimir();
	}

    @Override
    public int getMaximumLife() {
        return energiaMaxima; //To change body of generated methods, choose Tools | Templates.
    }
}
