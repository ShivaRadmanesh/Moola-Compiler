import ExternalClasses.*;
import com.sun.tools.javac.util.Name;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;
import sun.tools.jconsole.Tab;

public class ProgramObjects implements MoolaListener {
    Scope scope;
    String mainClassName;

    /*
    private ObjectType convertType(String type){
        if(type.equals("int")){
            return ObjectType.INT;
        }
        if(type.equals("bool")){
            return ObjectType.BOOL;
        }
        if(type.equals("string")){
            return ObjectType.STRING;
        }
        else{
            return ObjectType.OBJECT;
        }

    }*/

    @Override
    public void enterProgram(MoolaParser.ProgramContext ctx) {
        scope = new Scope(null);
    }

    @Override
    public void exitProgram(MoolaParser.ProgramContext ctx) {
        System.out.println("----------------- program : " + ctx.getStart().getLine() + " ---------------------");
        System.out.println(scope.toString());


    }

    @Override
    public void enterClassDeclaration(MoolaParser.ClassDeclarationContext ctx) {
        if(!ctx.className.getText().equals(mainClassName)) {
            TableObject object = new DefClass(ctx.className.getText(), ScopeType.CLASS, ctx.getStart().getLine());
            String parent = ctx.classParent == null ? null : ctx.classParent.getText();
            ((DefClass) object).setParent(parent);

            scope.insertToSymbolTable(ctx.className.getText(), object);

            scope = new Scope(scope);
        }

    }

    @Override
    public void exitClassDeclaration(MoolaParser.ClassDeclarationContext ctx) {
        if(!ctx.className.getText().equals(mainClassName)) {
            System.out.println("----------------- " + ctx.className.getText() + " : " + ctx.getStart().getLine() + " ---------------------");
            System.out.println(scope.toString());
            scope = scope.getParent();
        }

    }

    @Override
    public void enterEntryClassDeclaration(MoolaParser.EntryClassDeclarationContext ctx) {
        mainClassName = ctx.classDeclaration().className.getText();
        TableObject object = new DefClass(ctx.classDeclaration().className.getText() , ScopeType.MAINCLASS ,ctx.getStart().getLine());
        scope.insertToSymbolTable(ctx.classDeclaration().className.getText() , object);

        scope = new Scope(scope);
    }

    @Override
    public void exitEntryClassDeclaration(MoolaParser.EntryClassDeclarationContext ctx) {
        System.out.println("----------------- " + ctx.classDeclaration().className.getText() +  " : "  + ctx.getStart().getLine() + " ---------------------");
        System.out.println(scope.toString());
        scope = scope.getParent();

    }

    @Override
    public void enterFieldDeclaration(MoolaParser.FieldDeclarationContext ctx) {
        String access_modifier = ctx.fieldAccessModifier == null ? "public" : ctx.fieldAccessModifier.getText();
        TableObject object = new Field(ctx.fieldName.getText() , ctx.getStart().getLine() , access_modifier , ctx.fieldType.getText());
        scope.insertToSymbolTable(ctx.fieldName.getText() , object);

    }

    @Override
    public void exitFieldDeclaration(MoolaParser.FieldDeclarationContext ctx) {


    }

    @Override
    public void enterAccess_modifier(MoolaParser.Access_modifierContext ctx) {

    }

    @Override
    public void exitAccess_modifier(MoolaParser.Access_modifierContext ctx) {

    }

    @Override
    public void enterMethodDeclaration(MoolaParser.MethodDeclarationContext ctx) {
        String access_modifier = ctx.methodAccessModifier == null ? "public" : ctx.methodAccessModifier.getText();
        TableObject object = new Method(ctx.methodName.getText() , ctx.getStart().getLine()  , access_modifier, ctx.t.getText());
        scope.insertToSymbolTable(ctx.methodName.getText() , object);

        scope = new Scope(scope);

        if(ctx.param2 != null){
            TableObject param2 = new Variable(ctx.param2.getText() , ScopeType.PARAMETERS , ctx.getStart().getLine() , ctx.typeP2.getText());
            scope.insertToSymbolTable(ctx.param2.getText() , param2);
        }
    }

    @Override
    public void exitMethodDeclaration(MoolaParser.MethodDeclarationContext ctx) {
        System.out.println("----------------- " + ctx.methodName.getText() +  " : "  + ctx.getStart().getLine() + " ---------------------");
        System.out.println(scope.toString());
        scope = scope.getParent();
    }

    @Override
    public void enterClosedStatement(MoolaParser.ClosedStatementContext ctx) {

    }

    @Override
    public void exitClosedStatement(MoolaParser.ClosedStatementContext ctx) {

    }

    @Override
    public void enterClosedConditional(MoolaParser.ClosedConditionalContext ctx) {

    }

    @Override
    public void exitClosedConditional(MoolaParser.ClosedConditionalContext ctx) {

    }

    @Override
    public void enterOpenConditional(MoolaParser.OpenConditionalContext ctx) {
        scope = new Scope(scope);
    }

    @Override
    public void exitOpenConditional(MoolaParser.OpenConditionalContext ctx) {
        System.out.println("----------------- if : "  + ctx.getStart().getLine() + " ---------------------");
        System.out.println(scope.toString());
        scope = scope.getParent();
    }

    @Override
    public void enterOpenStatement(MoolaParser.OpenStatementContext ctx) {


    }

    @Override
    public void exitOpenStatement(MoolaParser.OpenStatementContext ctx) {

    }

    @Override
    public void enterStatement(MoolaParser.StatementContext ctx) {

    }

    @Override
    public void exitStatement(MoolaParser.StatementContext ctx) {

    }

    @Override
    public void enterStatementVarDef(MoolaParser.StatementVarDefContext ctx) {
        TableObject object = new TableObject(ctx.i1.getText() , ScopeType.LOCALVARIABLE ,ctx.getStart().getLine());
        scope.insertToSymbolTable(ctx.i1.getText() , object);
    }

    @Override
    public void exitStatementVarDef(MoolaParser.StatementVarDefContext ctx) {

    }

    @Override
    public void enterStatementBlock(MoolaParser.StatementBlockContext ctx) {
//        scope = new Scope(scope);
    }

    @Override
    public void exitStatementBlock(MoolaParser.StatementBlockContext ctx) {
//        System.out.println("----------------- if : "  + ctx.getStart().getLine() + " ---------------------");
//        System.out.println(scope.toString());
//        scope = scope.getParent();


    }

    @Override
    public void enterStatementContinue(MoolaParser.StatementContinueContext ctx) {

    }

    @Override
    public void exitStatementContinue(MoolaParser.StatementContinueContext ctx) {

    }

    @Override
    public void enterStatementBreak(MoolaParser.StatementBreakContext ctx) {

    }

    @Override
    public void exitStatementBreak(MoolaParser.StatementBreakContext ctx) {

    }

    @Override
    public void enterStatementReturn(MoolaParser.StatementReturnContext ctx) {

    }

    @Override
    public void exitStatementReturn(MoolaParser.StatementReturnContext ctx) {

    }

    @Override
    public void enterStatementClosedLoop(MoolaParser.StatementClosedLoopContext ctx) {
        scope = new Scope(scope);
    }

    @Override
    public void exitStatementClosedLoop(MoolaParser.StatementClosedLoopContext ctx) {
        System.out.println("----------------- while"  +  " : "  + ctx.getStart().getLine() + " ---------------------");
        System.out.println(scope.toString());
        scope = scope.getParent();

    }

    @Override
    public void enterStatementOpenLoop(MoolaParser.StatementOpenLoopContext ctx) {


    }

    @Override
    public void exitStatementOpenLoop(MoolaParser.StatementOpenLoopContext ctx) {

    }

    @Override
    public void enterStatementWrite(MoolaParser.StatementWriteContext ctx) {

    }

    @Override
    public void exitStatementWrite(MoolaParser.StatementWriteContext ctx) {

    }

    @Override
    public void enterStatementAssignment(MoolaParser.StatementAssignmentContext ctx) {

    }

    @Override
    public void exitStatementAssignment(MoolaParser.StatementAssignmentContext ctx) {

    }

    @Override
    public void enterStatementInc(MoolaParser.StatementIncContext ctx) {

    }

    @Override
    public void exitStatementInc(MoolaParser.StatementIncContext ctx) {

    }

    @Override
    public void enterStatementDec(MoolaParser.StatementDecContext ctx) {

    }

    @Override
    public void exitStatementDec(MoolaParser.StatementDecContext ctx) {

    }

    @Override
    public void enterExpression(MoolaParser.ExpressionContext ctx) {

    }

    @Override
    public void exitExpression(MoolaParser.ExpressionContext ctx) {

    }

    @Override
    public void enterExpressionOr(MoolaParser.ExpressionOrContext ctx) {

    }

    @Override
    public void exitExpressionOr(MoolaParser.ExpressionOrContext ctx) {

    }

    @Override
    public void enterExpressionOrTemp(MoolaParser.ExpressionOrTempContext ctx) {

    }

    @Override
    public void exitExpressionOrTemp(MoolaParser.ExpressionOrTempContext ctx) {

    }

    @Override
    public void enterExpressionAnd(MoolaParser.ExpressionAndContext ctx) {

    }

    @Override
    public void exitExpressionAnd(MoolaParser.ExpressionAndContext ctx) {

    }

    @Override
    public void enterExpressionAndTemp(MoolaParser.ExpressionAndTempContext ctx) {

    }

    @Override
    public void exitExpressionAndTemp(MoolaParser.ExpressionAndTempContext ctx) {

    }

    @Override
    public void enterExpressionEq(MoolaParser.ExpressionEqContext ctx) {

    }

    @Override
    public void exitExpressionEq(MoolaParser.ExpressionEqContext ctx) {

    }

    @Override
    public void enterExpressionEqTemp(MoolaParser.ExpressionEqTempContext ctx) {

    }

    @Override
    public void exitExpressionEqTemp(MoolaParser.ExpressionEqTempContext ctx) {

    }

    @Override
    public void enterExpressionCmp(MoolaParser.ExpressionCmpContext ctx) {

    }

    @Override
    public void exitExpressionCmp(MoolaParser.ExpressionCmpContext ctx) {

    }

    @Override
    public void enterExpressionCmpTemp(MoolaParser.ExpressionCmpTempContext ctx) {

    }

    @Override
    public void exitExpressionCmpTemp(MoolaParser.ExpressionCmpTempContext ctx) {

    }

    @Override
    public void enterExpressionAdd(MoolaParser.ExpressionAddContext ctx) {

    }

    @Override
    public void exitExpressionAdd(MoolaParser.ExpressionAddContext ctx) {

    }

    @Override
    public void enterExpressionAddTemp(MoolaParser.ExpressionAddTempContext ctx) {

    }

    @Override
    public void exitExpressionAddTemp(MoolaParser.ExpressionAddTempContext ctx) {

    }

    @Override
    public void enterExpressionMultMod(MoolaParser.ExpressionMultModContext ctx) {

    }

    @Override
    public void exitExpressionMultMod(MoolaParser.ExpressionMultModContext ctx) {

    }

    @Override
    public void enterExpressionMultModTemp(MoolaParser.ExpressionMultModTempContext ctx) {

    }

    @Override
    public void exitExpressionMultModTemp(MoolaParser.ExpressionMultModTempContext ctx) {

    }

    @Override
    public void enterExpressionUnary(MoolaParser.ExpressionUnaryContext ctx) {

    }

    @Override
    public void exitExpressionUnary(MoolaParser.ExpressionUnaryContext ctx) {

    }

    @Override
    public void enterExpressionMethods(MoolaParser.ExpressionMethodsContext ctx) {

    }

    @Override
    public void exitExpressionMethods(MoolaParser.ExpressionMethodsContext ctx) {

    }

    @Override
    public void enterExpressionMethodsTemp(MoolaParser.ExpressionMethodsTempContext ctx) {

    }

    @Override
    public void exitExpressionMethodsTemp(MoolaParser.ExpressionMethodsTempContext ctx) {

    }

    @Override
    public void enterExpressionOther(MoolaParser.ExpressionOtherContext ctx) {

    }

    @Override
    public void exitExpressionOther(MoolaParser.ExpressionOtherContext ctx) {

    }

    @Override
    public void enterMoolaType(MoolaParser.MoolaTypeContext ctx) {

    }

    @Override
    public void exitMoolaType(MoolaParser.MoolaTypeContext ctx) {

    }

    @Override
    public void enterSingleType(MoolaParser.SingleTypeContext ctx) {

    }

    @Override
    public void exitSingleType(MoolaParser.SingleTypeContext ctx) {

    }

    @Override
    public void visitTerminal(TerminalNode terminalNode) {

    }

    @Override
    public void visitErrorNode(ErrorNode errorNode) {

    }

    @Override
    public void enterEveryRule(ParserRuleContext parserRuleContext) {

    }

    @Override
    public void exitEveryRule(ParserRuleContext parserRuleContext) {

    }
}
