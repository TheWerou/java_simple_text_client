package Main_facade.Log_class;


import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class log_gen {

    private static final Logger LOGGER = Logger.getLogger(log_gen.class.getName());
    public log_gen()
    {
        Handler consoleHandler = null;
        Handler fileHandler  = null;
        try{

            consoleHandler = new ConsoleHandler();
            fileHandler  = new FileHandler("./src/logs/java_clientc_logs.log");

            LOGGER.addHandler(consoleHandler);
            LOGGER.addHandler(fileHandler);

            consoleHandler.setLevel(Level.ALL);
            fileHandler.setLevel(Level.ALL);
            LOGGER.setLevel(Level.ALL);

            LOGGER.config("Configuration done.");

            LOGGER.removeHandler(consoleHandler);

            LOGGER.log(Level.FINE, "Finer logged");
        }catch(IOException exception){
            LOGGER.log(Level.SEVERE, "Error occur in FileHandler.", exception);
        }
    }

    public void WARNING_log(String msg)
    {
        LOGGER.warning(msg);
    }

    public void INFO_log(String msg)
    {
        LOGGER.info(msg);
    }

    public void CONFIG_log(String msg)
    {
        LOGGER.config(msg);
    }

}
