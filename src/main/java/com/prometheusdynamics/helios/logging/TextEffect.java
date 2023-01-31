package com.prometheusdynamics.helios.logging;

public class TextEffect {
    
    private static final String RESET = "\033[0m";

    private static final String FORMAT = "\033[%s%d%dm%s";

    private boolean isBold = false;
    private boolean isBackground = false;
    private boolean isGlow = false;
    private boolean isUnderline = false;
    private String text;
    private Colour colour;

    public static enum Colour{
        Black(0),
        Red(1),
        Green(2),
        Yellow(3),
        Blue(4),
        Purple(5),
        Cyan(6),
        White(7);
        private int code;
        private Colour(int code){
            this.code = code;
        }

        public static Colour getColour(long id){
            int colour = (int) (id % Colour.values().length);
            return Colour.values()[colour];
        }
    }

    public static String r(){
        return RESET;
    }

    public static TextEffect colour(Colour colour){
        return colour(colour, null);
    }

    public static TextEffect colour(Colour colour, String text){
        return new TextEffect(text, colour);
    }
    
    public static TextEffect background(Colour colour){
        return background(colour, null);
    }

    public static TextEffect background(Colour colour, String text){
        TextEffect effect = new TextEffect(text, colour);
        effect.isBackground = true;
        return effect;
    }

    private TextEffect(String text, Colour colour){
        this.text = text;
        this.colour = colour;
    }

    public TextEffect b(){
        isBold = true;
        return this;
    }

    public TextEffect u(){
        isUnderline = true;
        return this;
    }
    public TextEffect g(){
        isGlow = true;
        return this;
    }

    @Override
    public String toString() {
        String style = !(isBackground && !isGlow) ? !isBold ? isUnderline ? "4;" : "0;": "1;" : "";
        int prefix = isGlow ? isBackground ? 10 : 9 : isBackground ? 4 : 3;
        if(text != null){
            text += RESET;
        }else{
            text = "";
        }
        return String.format(FORMAT, style, prefix, colour.code,text);
    }
    

}
