//import java.util.Set;
import java.util.ArrayList;
import java.util.Random;


public class Flappy implements Jogo{
	
	public double ground_offset = 0;
	public double gvx = 70;
	public double background_offset = 0;
	public double bgvx = 50;
	public Passaro passaro;
	public ArrayList<Cano> canos = new ArrayList<Cano>();
	public Random gerador = new Random();
	public Timer timer_cano;
	
	public Flappy() {
		passaro = new Passaro(40, (getLargura()-112)/2);
		timer_cano = new Timer(3, true, addCano());
	} 
	
	private Acao addCano() {
		return new Acao() {
			public void executa() {
				canos.add(new Cano (getLargura()+50, gerador.nextInt(getAltura()-112-Cano.HOLESIZE), -gvx));
			}
		};
	}
	 
	public String getTitulo() {
		
		return "Flappy Game";
	}

	public int getLargura() {
		
		return 384;
	}

	public int getAltura() {
		
		return 512;
	}
	
	public void tique(java.util.Set<String> teclas, double dt) {
		
		// To Ground
		ground_offset += dt*gvx;
		ground_offset = ground_offset%308;
	
		// To Background
		background_offset +=dt*bgvx;
		background_offset = background_offset%288;
		
		timer_cano.tique(dt);
		
		passaro.atualiza(dt);
		
		if(passaro.y+24 >= getAltura()-112) {
			//gameOver
		}else if(passaro.vy <= 0) {
			
		}
		
		for(Cano cano: canos) {
			cano.atualiza(dt);
			if(passaro.box.intersecao(cano.boxcima) !=0 || passaro.box.intersecao(cano.boxbaixo) !=0) {
				//gameOver
			}
		}
		
		if(canos.size()>0 && canos.get(0).x < - 60) {
			canos.remove(0);
		}
	}

	public void tecla(String tecla) {
		if(tecla.equals(" ")) {
			passaro.flap();
		}
	}
	
	public void desenhar(Tela tela) {
		//Background
		tela.imagem("flappy.png", 0, 0, 288, 512, 0, -background_offset, getAltura()-512);
		tela.imagem("flappy.png", 0, 0, 288, 512, 0, 288 -background_offset, getAltura()-512);
		tela.imagem("flappy.png", 0, 0, 288, 512, 0, 288*2 -background_offset, getAltura()-512);
		
		for(Cano cano: canos) {
			cano.desenha(tela);
		}
		
		//Ground
		tela.imagem("flappy.png", 292, 0, 308, 112, 0, -ground_offset, getAltura()-112);
		tela.imagem("flappy.png", 292, 0, 308, 112, 0, 308 -ground_offset, getAltura()-112);
		tela.imagem("flappy.png", 292, 0, 308, 112, 0, 308*2 -ground_offset, getAltura()-112);

		//Bird
		passaro.desenhar(tela);				
	}
	
	public static void main (String[] args) {
		roda();
	}
	
	private static void roda() {
		new Motor (new Flappy());
	}
	
}
