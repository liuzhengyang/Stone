package stone;

/**
 * Created by liuzhengyang on 2015/4/16.
 */
public class IdToken extends Token {
    private String text;
    public IdToken(int lineNo, String text) {
        super(lineNo);
        this.text = text;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public boolean isIdentifier() {
        return true;
    }
}
