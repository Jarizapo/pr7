import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ControladorBarra implements PropertyChangeListener
{
    private Panel panel;
    private String tipo;

    public ControladorBarra(Panel panel, String tipo)
    {
        this.panel = panel;
        this.tipo = tipo;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt)
    {
        if (evt.getPropertyName().equals("progress"))
        {
            Integer newValue = (Integer) evt.getNewValue();
            switch (tipo) {
                case "TWIN":
                    panel.progreso1(newValue);
                    break;
                case "COUSIN":
                    panel.progreso2(newValue);
                    break;
                case "SEXY":
                    panel.progreso3(newValue);
                    break;
                default:
                    break;
            }
        }
    }
}
