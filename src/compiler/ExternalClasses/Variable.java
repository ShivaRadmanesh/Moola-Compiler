package ExternalClasses;

public class Variable extends TableObject{
    private String kind;
    public Variable(String name, ScopeType st , int line, String kind){
        super(name , st , line);
        this.kind = kind;
    }

    @Override
    public String toString() {
        String str =  super.toString();
        str += "(type = " + kind + ")";
        return str;
    }
}
