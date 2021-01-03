package ExternalClasses;


import java.util.PrimitiveIterator;

public class TableObject{

    private String name;
    private ScopeType scopeType;
    private int lineNumber;

    public TableObject(String name,ScopeType scopeType, int lineNumber){
        this.name = name;
        this.scopeType = scopeType;
        this.lineNumber = lineNumber;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }


    public int getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }



    public void setScopeType(ScopeType scopeType) {
        this.scopeType = scopeType;
    }

    public ScopeType getScopeType() {
        return scopeType;
    }

    public String toString(){
        String str = scopeType + " : " + "(name : " + name  + ")";
        return str;
    }
}