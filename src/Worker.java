import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;

public class Worker extends SwingWorker<Void, Primos>
{
    private int n;
    private Panel panel;
    private String tipo;

    public Worker(int n, Panel panel, String tipo)
    {
        this.n = n;
        this.panel = panel;
        this.tipo = tipo;
    }

    private boolean esPrimo(int num)
    {
        boolean primo = true;
        
        if (num < 2)
            primo = false;
        else
        {
            int i = 2;
            
            while (i <= Math.sqrt(num) && primo)
            {
                if (num % i == 0)
                    primo = false;
                
                i++;
            }
        }
        
        return primo;
    }

    private int distanciaDeTipo()
    {
        int distanciaEntrePrimos = 0;
    	
    	switch (tipo) {
            case "TWIN":
            	distanciaEntrePrimos = 2;
            	break;
            case "COUSIN":
            	distanciaEntrePrimos = 4;
            	break;
            case "SEXY":
            	distanciaEntrePrimos = 6;
            	break;
    	}
    	
    	return distanciaEntrePrimos;
    }
    
    @Override
    protected Void doInBackground() throws Exception
    {
        int primosTotales = 0;
        int num = 3;
        
        boolean parNumerosValido;
        
        while (primosTotales < n && !isCancelled())
        {            
        	parNumerosValido = esPrimo(num) && esPrimo(num + distanciaDeTipo());
        	
            if (parNumerosValido)
            {
                publish(new Primos(num, num + distanciaDeTipo(), primosTotales));
                primosTotales++;
                
                setProgress((primosTotales * 100) / n);
            }
            
            num++;
        }
        
        return null;
    }

    @Override
    protected void process(List<Primos> chunks)
    {
        for (Primos primo : chunks)
        {
            switch (tipo)
            {
                case "TWIN":
                    panel.escribePrimosTwin(List.of(primo));
                    break;
                case "COUSIN":
                    panel.escribePrimosCousin(List.of(primo));
                    break;
                case "SEXY":
                    panel.escribePrimosSexy(List.of(primo));
                    break;
            }
        }
    }
    
    private void tareaCancelada()
    {
        switch (tipo)
        {
	        case "TWIN":
	            panel.mensajeTwin("Operación cancelada");
	            break;
	        case "COUSIN":
	            panel.mensajeCousin("Operación cancelada");
	            break;
	        case "SEXY":
	            panel.mensajeSexy("Operación cancelada");
	            break;
        }
    }
    
    @Override
    protected void done()
    {
        try
        {
            switch (tipo)
            {
                case "TWIN":
                    panel.mensajeTwin("Primos twin calculados");
                    break;
                case "COUSIN":
                    panel.mensajeCousin("Primos cousin calculados");
                    break;
                case "SEXY":
                    panel.mensajeSexy("Primos sexy calculados");
                    break;
            }
        }
        catch (CancellationException e)
        {
        	tareaCancelada();
        }
    }
}
