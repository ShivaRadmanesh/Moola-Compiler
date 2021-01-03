
package ExternalClasses;

public class Scope{

    private Scope parent;
    ExternalClasses.SymbolTable symbolTable;

    public Scope(Scope parent){
        this.parent = parent;
        symbolTable = new ExternalClasses.SymbolTable();

    }

    public void setParent(Scope parent) {
        this.parent = parent;
    }

    public Scope getParent() {
        return parent;
    }

    public void insertToSymbolTable(String symbol, ExternalClasses.TableObject object){
        symbolTable.insert(symbol , object);
    }

    @Override
    public String toString() {
        return symbolTable.toString();
    }
}