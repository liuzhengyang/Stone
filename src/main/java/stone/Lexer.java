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

    public static String regexPat = "\\s*((//.*)|("+ numPat +")|("+strPat+")|("+identifyPat+"))?";
    private Pattern pattern = Pattern.compile(regexPat);
    private ArrayList<Token> queue = new ArrayList<Token>();
    private boolean hasMore;
    private LineNumberReader reader;

    public Lexer(Reader r){
        hasMore = true;
        reader = new LineNumberReader(r);
    }

    public Token read() throws ParseException{
        if(fillQueue(0)){
            return queue.remove(0);
        }else{
            return Token.EOF;
        }
    }

    public Token peek(int i)throws ParseException{
        if(fillQueue(i)){
            return queue.get(i);
        }else{
            return Token.EOF;
        }
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
            throw new ParseException(e);
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
        String m = matcher.group(1);
        if(m != null){ // if not a space
            if(matcher.group(2) == null){ // if not a comment
                Token token;
                if(matcher.group(3)!=null){
                    token = new NumToken(lineNo, Integer.parseInt(m));
                }else if(matcher.group(4) != null){
                    token = new StrToken(lineNo, toStringLiteral(m));
                }else{
                    token = new IdToken(lineNo, m);
                }
                queue.add(token);
            }
        }
    }

    private String toStringLiteral(String s) {
        StringBuilder sb = new StringBuilder();
        int len = s.length() - 1;
        for(int i = 1; i < len; i++){
            char c = s.charAt(i);
            if(c == '\\' && i + 1 < len){
                int c2 = s.charAt(i+1);
                if(c2 == '"' || c2 == '\\'){
                    c = s.charAt(++i);
                }else if(c2 == 'n'){
                    ++i;
                    c = '\n';
                }
            }
            sb.append(c);
        }
        return sb.toString();
    }
}
