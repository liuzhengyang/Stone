package stone;

/**
 * Created by liuzhengyang on 2015/4/15.
 */
public class StoneException extends RuntimeException {
    public StoneException(String s) {
        super(s);
    }
//    public StoneException(String s, ASTree t){
//        super(s + " " + t.location())
//    }
}
