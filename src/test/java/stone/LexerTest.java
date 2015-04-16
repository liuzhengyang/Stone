package stone;

import org.junit.Test;

import java.util.Scanner;

/**
 * Created by liuzhengyang on 2015/4/16.
 */
public class LexerTest {

    @Test
    public void testTokeizer() throws ParseException {
        Lexer l = new Lexer(new CodeDialog());
        for(Token t; (t = l.read())!= Token.EOF;){
            System.out.println("=> " + t.getText());
        }
    }
}
