import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;

public class Worker extends SwingWorker<List<Primos>, Void>
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
    protected List<Primos> doInBackground() throws Exception
    {
        List<Primos> lista = new ArrayList<>();
        
        int primosTotales = 0;
        int num = 3;
        
        boolean parNumerosValido;
        
        while (primosTotales < n && !isCancelled())
        {            
        	parNumerosValido = esPrimo(num) && esPrimo(num + distanciaDeTipo());
        	
            if (parNumerosValido)
            {
                lista.add(new Primos(num, num + distanciaDeTipo(), primosTotales));
                
                primosTotales++;
                
                setProgress((primosTotales * 100) / n);
            }
            
            num++;
        }
        
        return lista;
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
                    panel.escribePrimosTwin(get());
                    panel.mensajeTwin("Primos twin calculados");
                    break;
                case "COUSIN":
                    panel.escribePrimosCousin(get());
                    panel.mensajeCousin("Primos cousin calculados");
                    break;
                case "SEXY":
                    panel.escribePrimosSexy(get());
                    panel.mensajeSexy("Primos sexy calculados");
                    break;
            }
        }
        catch (InterruptedException | ExecutionException | CancellationException e)
        {
        	tareaCancelada();
        }
    }
}
