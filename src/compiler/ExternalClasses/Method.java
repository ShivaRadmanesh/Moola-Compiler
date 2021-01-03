package ExternalClasses;

public class Method extends TableObject{
    private String returnValue;
    private String accessModifier;
    public Method(String name ,int line, String accessModifier,String returnValue){

        super(name , ScopeType.METHOD , line);
        this.accessModifier = accessModifier;
        this.returnValue = returnValue;

    }

    @Override
    public String toString() {
        String str = super.toString();
        str += " (Return Value : " + returnValue + ")" ;
        str += " (access Modifier : " + accessModifier +  ")";
        return str;
    }
}
