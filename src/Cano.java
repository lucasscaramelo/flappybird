public class Cano {
	
	public double vxcano;
	public double x,y;
	public static int HOLESIZE = 120;
	
	public Cano(double x, double y, double vx) {
		this.x = x;
		this.y = y;
		this.vxcano = vx;
	}

	public void atualiza(double dt) {
		x += vxcano*dt;
	}
	
	public void desenha(Tela t) {
		t.imagem("flappy.png", 604, 0, 52, 270, 0, x, y-270);
		t.imagem("flappy.png", 660, 0, 52, 242, 0, x, y+Cano.HOLESIZE);		
	}

}
