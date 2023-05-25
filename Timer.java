public class Timer {

    long tempoInicial = 0, tempoFinal = 0, tempoDurado = 0, tempoDuracao = 0;

    public Timer(){}
    
    public Timer(long tempoDuracao){
        this.tempoDuracao = tempoDuracao;
    }

    public void contarInicio(){
        tempoInicial = System.currentTimeMillis();
    }

    public void contarFim(){
        tempoFinal = System.currentTimeMillis();
    }

    private long calcularIntervaloTempo(){
        tempoDurado = tempoFinal - tempoInicial;
        return tempoDurado;
    }

    public long intervaloEmSegundos(){
        calcularIntervaloTempo();
        return tempoDurado/1000;
    }

    public long intervaloEmMinutos(){
        calcularIntervaloTempo();
        return tempoDurado/(1000*60);
    }
    
    public long intervaloEmHoras(){
        calcularIntervaloTempo();
        return tempoDurado/(1000*60*24);
    }
    
    public long tempoRestanteSegundos(){
        calcularIntervaloTempo();
        return tempoDuracao - intervaloEmSegundos();
    }
    
    public void contabilizar(long segundos) {
    	Timer t = new Timer(segundos);
		t.contarInicio();
		System.out.println("Contando " +segundos +" segundos.");
		long tempoRestante = t.tempoRestanteSegundos();
		t.contarFim();
		tempoRestante = t.tempoRestanteSegundos();
		while(tempoRestante>=0) {
			if(tempoRestante > t.tempoRestanteSegundos()) {
				System.out.println(tempoRestante +" segundos restantes");
				tempoRestante = t.tempoRestanteSegundos();
			}
			t.contarFim();
		}
		
		System.out.println("Fim...");
    }
    public static void main(String[] args) {
		new Timer().contabilizar(60);
	}
}
