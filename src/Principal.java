import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Principal {
	
	public static void crearGUI()
	{
       	System.out.println("crearGUI() - isEventDispatchThread? "+ SwingUtilities.isEventDispatchThread());

        JFrame ventana = new JFrame("Numeros Primos");
        
        Panel panel = new Panel();
        Controlador controlador = new Controlador(panel);
        panel.controlador(controlador);

        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.getContentPane().add(panel);
        ventana.pack();
        ventana.setVisible(true);
	}
	
    public static void main(String[] args)
    {

        SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
            	try
            	{
            		crearGUI();
            	}
            	catch(Exception e)
            	{
            		System.out.println("Tarea cancelada");
            	}
            }
        });
    }
}
