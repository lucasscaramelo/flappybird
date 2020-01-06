public class Cano {
	
	public double vxcano;
	public double x,y;
	public static int HOLESIZE = 120;
	public Hitbox boxcima;
	public Hitbox boxbaixo;
	
	public Cano(double x, double y, double vx) {
		this.x = x;
		this.y = y;
		this.vxcano = vx;
		
		boxcima = new Hitbox(x, y-270, x+52, y);
		boxbaixo = new Hitbox(x, y+Cano.HOLESIZE, x+52, y+Cano.HOLESIZE+242);
	}

	public void atualiza(double dt) {
		x += vxcano*dt;
		boxcima.mover(vxcano*dt, 0);
		boxbaixo.mover(vxcano*dt, 0);
	}
	
	public void desenha(Tela t) {
		t.imagem("flappy.png", 604, 0, 52, 270, 0, x, y-270);
		t.imagem("flappy.png", 660, 0, 52, 242, 0, x, y+Cano.HOLESIZE);		
	}

}
