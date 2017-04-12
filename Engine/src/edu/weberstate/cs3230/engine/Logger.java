package edu.weberstate.cs3230.engine;

import java.io.IOException;
import java.util.logging.*;

/**
 * Created by parker on 3/12/17.
 */
public class Logger {

    private static java.util.logging.Logger logger;

    static {
        logger = java.util.logging.Logger.getLogger("edu.weberstate.cs3230.engine.Battleship");

        logger.setLevel(Level.ALL);

        Handler handler = null;

        try {
            handler = new FileHandler("battleship.log", 0xfffff, 3);
            handler.setFormatter(new SimpleFormatter());
        } catch (IOException e) {
            e.printStackTrace();
        }

        handler.setLevel(Level.ALL);
        logger.addHandler(handler);
    }

    public static java.util.logging.Logger getLogger()
    {
        return logger;
    }
}
