package stone;

import java.io.IOException;

/**
 * Created by liuzhengyang on 2015/4/15.
 */
public class ParseException extends Exception {
    public ParseException(String s) {
        super(s);
    }
    public ParseException(String s, Token t) {
        super("syntax error around " + location(t) + ". " + s);
    }

    private static String location(Token t) {
        if(t == Token.EOF){
            return "the last line";
        }else{
            return "\"" + t.getText() + "\" at line " + t.getLineNumber();
        }
    }

    public ParseException(IOException e){
        super(e);
    }

    public ParseException(Token t) {
        this("",t);
    }
}
