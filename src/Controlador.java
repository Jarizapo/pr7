import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controlador implements ActionListener
{
    private Panel panel;
    private Worker worker;

    public Controlador(Panel panel)
    {
        this.panel = panel;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        String tipo = e.getActionCommand();
        int num = 0;

        switch (tipo) {
            case "TWIN":
                num = panel.numero1();
                panel.limpiaAreaTwin();
                break;
            case "COUSIN":
            	num = panel.numero2();
                panel.limpiaAreaCousin();
                break;
            case "SEXY":
            	num = panel.numero3();
                panel.limpiaAreaSexy();
                break;
            case "FIN":
                if (worker != null)
                    worker.cancel(true);
                
                panel.mensaje("Operaciones canceladas");
                break;
        }
        
        worker = new Worker(num, panel, tipo);
        worker.execute();
    }
}
