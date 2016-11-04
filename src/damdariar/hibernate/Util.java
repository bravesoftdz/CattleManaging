package damdariar.hibernate;

public class Util extends Thread{
	
	private static void optimizeDataBaseObjects(){
		
	}
	private static void collectGarbageAutomated(){
		
	}
	@Override
	public void run() {
		HibernateUtil.close();
	}

	
	
}
