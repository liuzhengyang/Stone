package stone;

/**
 * Created by liuzhengyang on 2015/4/16.
 */
public class NumToken extends Token {
    private int value;
    public NumToken(int lineNo, int number) {
        super(lineNo);
        this.value = number;
    }

    @Override
    public boolean isNumber() {
        return true;
    }

    @Override
    public String getText() {
        return Integer.toString(value);
    }

    @Override
    public int getNumber() {
        return value;
    }
}
