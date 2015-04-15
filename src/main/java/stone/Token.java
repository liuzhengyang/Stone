package stone;

/**
 * Token
 * Created by liuzhengyang on 2015/4/15.
 */
public abstract class Token {
    public static final Token EOF = new Token(-1){}; // end of file
    public static final String EOL = "\\n";   // end of line

    private int linenumber;

    public Token(int line) {
        linenumber = line;
    }

    public int getLineNumber(){
        return linenumber;
    }

    public boolean isIdentifier(){
        return false;
    }
    public boolean isNumber(){
        return false;
    }
    public boolean isString(){
        return false;
    }
    public int getNumber(){
        throw new StoneException("not number token");
    }

    public String getText(){
        return "";
    }
}
