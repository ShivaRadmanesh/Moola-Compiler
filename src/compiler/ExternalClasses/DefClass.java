package ExternalClasses;

public class DefClass extends TableObject{
    String parent;
    public DefClass(String name , ScopeType st ,int line){
        super(name  , st , line);
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    @Override
    public String toString() {
        String str = super.toString();
        if(parent != null)
            str += "(inherits : " + parent + ")";
        return str;
    }
}
