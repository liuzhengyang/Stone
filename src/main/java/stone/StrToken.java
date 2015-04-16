package stone;

/**
 * Created by liuzhengyang on 2015/4/16.
 */
public class StrToken extends Token {
    private String literal;
    public StrToken(int lineNo, String literal) {
        super(lineNo);
        this.literal = literal;
    }

    @Override
    public String getText() {
        return literal;
    }

    @Override
    public boolean isString() {
        return true;
    }
}
