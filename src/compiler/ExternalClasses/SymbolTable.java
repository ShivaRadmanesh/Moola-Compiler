
package ExternalClasses;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Set;

public class SymbolTable{
    private Hashtable<String , ArrayList<ExternalClasses.TableObject> > symbolTable;

    public SymbolTable(){
        symbolTable  = new Hashtable<>();
    }

     void insert(java.lang.String symbol, ExternalClasses.TableObject object){
        if(symbolTable.get(symbol) == null) {
            ArrayList<ExternalClasses.TableObject> list = new ArrayList<>();
            list.add(object);
            symbolTable.put(symbol, list);
        }
        else{
            symbolTable.get(symbol).add(object);
        }
    }

    @Override
    public String toString() {
        Set<String> keySet = symbolTable.keySet();
        String str = "";

        for (String key : keySet) {
            for (TableObject object : symbolTable.get(key)) {
                str += "Key = " + object.getScopeType() + "_" +  key + " | " + "value = " +  object.toString() + "\n";
            }
        }
        return str;
    }
}