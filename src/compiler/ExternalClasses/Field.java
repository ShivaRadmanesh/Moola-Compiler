package ExternalClasses;

public class Field extends TableObject{
    private String accessModifier;
    private String kind;

    public Field(String name, int line , String accessModifier, String kind){
        super(name , ScopeType.FIELD ,line);
        this.accessModifier = accessModifier;
        this.kind = kind;
    }

    @Override
    public String toString() {
        String str =  super.toString();
        str += "(Type : " + kind  + ")";
        str += "(Access Modifier : " + accessModifier + ")";
        return str;
    }
}
