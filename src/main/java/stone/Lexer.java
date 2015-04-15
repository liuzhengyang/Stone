package stone;

import java.io.IOException;
import java.io.LineNumberReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by liuzhengyang on 2015/4/15.
 */
public class Lexer {
    public static String numPat = "[0-9]+";
    public static String strPat = "\"(\\\\\"|\\\\\\\\|\\\\n|[^\"])*\"";
    public static String identifyPat = "[a-z_A-Z][a-z_A-Z0-9]*|==|<=|>=|&&|\\|\\||\\p{Punct}";

    public static String regexPat = "\\s*((//.*)|("+ numPat +")|("+strPat+")|("+identifyPat+")?";
    private Pattern pattern = Pattern.compile(regexPat);
    private ArrayList<Token> queue = new ArrayList<>();
    private boolean hasMore;
    private LineNumberReader reader;

    public Lexer(Reader r){
        hasMore = true;
        reader = new LineNumberReader(r);
    }

    public Token read(){

    }

    private boolean fillQueue(int i)throws ParseException{
        while(i>=queue.size()){
            if(hasMore){
                readLine();
            }else{
                return false;
            }
        }
        return true;
    }

    private void readLine() throws ParseException{
        String line;
        try {
            line = reader.readLine();
        } catch (IOException e) {
            throw new ParseException();
        }
        if(line == null){
            hasMore = false;
            return;
        }
        int lineNo = reader.getLineNumber();
        Matcher matcher = pattern.matcher(line);
        matcher.useTransparentBounds(true).useAnchoringBounds(false);
        int pos = 0;
        int endPos = line.length();
        while(pos < endPos){
            matcher.region(pos, endPos);
            if(matcher.lookingAt()){
                addToken(lineNo, matcher);
                pos = matcher.end();
            }else{
                throw new ParseException("bad token at line " + lineNo);
            }
        }
    }

    private void addToken(int lineNo, Matcher matcher) {

    }
}
