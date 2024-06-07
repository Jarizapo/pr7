import java.util.List;

import javax.swing.SwingWorker;

public class Worker extends SwingWorker<List<Primos>, Void>{

	private int workerNumber;
	private Panel panel;
	
	public Worker(int wn, Panel p)
	{
		workerNumber = wn;
		panel = p;
	}
	
	@Override
	protected List<Primos> doInBackground() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
