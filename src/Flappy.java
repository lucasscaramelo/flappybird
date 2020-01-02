import java.util.Set;
import java.util.ArrayList;
import java.util.Random;


public class Flappy implements Jogo{
	
	public double ground_offset = 0;
	public double gvx = 50;
	public double background_offset =0;
	public double bgvx = 50;
	public Passaro passaro;
	public ArrayList<Cano> canos = new ArrayList<Cano>();
	
	public Flappy() {
		passaro = new Passaro(40, (getLargura()-112)/2);
		canos.add(new Cano (getLargura()-200, 10, -gvx));
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
	
	public void tique(Set<String> teclas, double dt) {
		
		// To Ground
		ground_offset += dt*gvx;
		ground_offset = ground_offset%308;
	
		// To Background
		background_offset +=dt*bgvx;
		background_offset = background_offset%288;
		
		passaro.atualiza(dt);
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
		
		//Ground
		tela.imagem("flappy.png", 292, 0, 308, 112, 0, -ground_offset, getAltura()-112);
		tela.imagem("flappy.png", 292, 0, 308, 112, 0, 308 -ground_offset, getAltura()-112);
		tela.imagem("flappy.png", 292, 0, 308, 112, 0, 308*2 -ground_offset, getAltura()-112);
	
		//Cano
		for(Cano cano: canos) {
			cano.desenha(tela);
		}
		
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
