import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controlador implements ActionListener
{
    private Panel panel;
    private Worker workerTwin;
    private Worker workerCousin;
    private Worker workerSexy;

    public Controlador(Panel panel)
    {
        this.panel = panel;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        String tipo = e.getActionCommand();
        int num = 0;

        switch (tipo)
        {
            case "TWIN":
                num = panel.numero1();
                panel.limpiaAreaTwin();
                workerTwin = new Worker(num, panel, tipo);
                workerTwin.execute();
                break;
            case "COUSIN":
            	num = panel.numero2();
                panel.limpiaAreaCousin();
                workerCousin = new Worker(num, panel, tipo);
                workerCousin.execute();
                break;
            case "SEXY":
            	num = panel.numero3();
                panel.limpiaAreaSexy();
                workerSexy = new Worker(num, panel, tipo);
                workerSexy.execute();
                break;
            case "FIN":
                if (workerTwin != null)
                	workerTwin.cancel(true);
                if (workerCousin != null)
                	workerCousin.cancel(true);
                if (workerSexy != null)
                	workerSexy.cancel(true);
                
                panel.mensaje("Operaciones canceladas");
                break;
        }
    }
}
