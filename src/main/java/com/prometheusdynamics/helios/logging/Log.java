package com.prometheusdynamics.helios.logging;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Date;

import com.prometheusdynamics.helios.logging.TextEffect.Colour;

public class Log {
    
    private static boolean isDebug = false;
    private static Path location;
    private static File logFile;
    private static FileWriter writer;
    private static final String NAME_FORMAT = "%s[%s%s] "+TextEffect.r();
    private static final Colour SYSTEM_COLOUR = Colour.Blue;
    private static final Colour ERROR_COLOUR = Colour.Red;
    private static final Colour DEBUG_COLOUR = Colour.Yellow;


    static {
        location = Path.of(System.getProperty("user.dir")+"/Log.txt");
        try {
            logFile = location.toFile();
            if(logFile.exists()){
                logFile.delete();
            }
            logFile.createNewFile();
            writer = new FileWriter(logFile);
            debug("Created log file at "+location);
        } catch (IOException e) {
            Debug("Failed to create log file "+ e.getLocalizedMessage());
        }
    }
    
    public static void setDebugMode(boolean debug){
        isDebug = debug;
    }

    private static String getName(){
        Thread thread = Thread.currentThread();
        Colour colour = Colour.getColour(thread.getId());
        return TextEffect.colour(colour, thread.getName()).u().toString();
    }

    private static String formatName(String name, TextEffect effect){
        String colour = effect.toString()+TextEffect.r();
        return String.format(NAME_FORMAT, colour, name, colour);
    }

    public static void System(String text){
        system(text, true);
    }
   
    public static void system(String text){
        system(text, false);
    }

    public static void system(String text, boolean important){
        out(text, SYSTEM_COLOUR, important);
    }

    public static void Error(String text){
        error(text, true);
    }

    public static void error(String text){
        error(text, false);
    }

    public static void error(String text, boolean important){
        out(text, ERROR_COLOUR, important);
    }

    public static void Debug(String text){
        debug(text, true);
    }

    public static void debug(String text){
        debug(text, false);
    }

    public static void debug(String text, boolean important){
        if(isDebug)out(text, DEBUG_COLOUR, important);
    }

    private static void out(String text, Colour colour, boolean important){
        String name = formatName(getName(), TextEffect.colour(colour)); 
        TextEffect effect = important ? TextEffect.background(colour,text).g() : TextEffect.colour(colour,text).b();
        String output =name + effect.toString();
        if(writer != null) writeToFile(output);
        System.out.println(output);
    }

    private static void writeToFile(String text){
        try {
            writer.write(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
