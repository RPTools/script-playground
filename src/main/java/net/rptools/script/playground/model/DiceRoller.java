package net.rptools.script.playground.model;

import net.rptools.dice.DiceExpression;
import net.rptools.dice.result.html.HTMLResultFormatter;
import net.rptools.dice.symbols.DefaultDiceExpressionSymbolTable;

public class DiceRoller {



  public String roll(String expr) {
    DiceExpression diceExpression = DiceExpression.fromString(expr);

    DefaultDiceExpressionSymbolTable symbolTable = new DefaultDiceExpressionSymbolTable();
    diceExpression.execute(symbolTable);
    return diceExpression.format(new HTMLResultFormatter()).orElse("<b>==== No Output ==== </b>");



    /*System.out.println("Local Variables");
    symbolTable
        .getVariableNames(DiceEvalScope.LOCAL)
        .forEach(
            name -> {
              var val = symbolTable.getVariableValue(DiceEvalScope.LOCAL, name);
              System.out.println(val.getType() + ":" + name + " = " + val.getStringResult());
            });
    System.out.println();

    System.out.println("Global Variables");
    symbolTable
        .getVariableNames(DiceEvalScope.GLOBAL)
        .forEach(
            name -> {
              var val = symbolTable.getVariableValue(DiceEvalScope.GLOBAL, name);
              System.out.println(val.getType() + ":" + name + " = " + val.getStringResult());
            });
    System.out.println();

    System.out.println("Property Variables");
    symbolTable
        .getVariableNames(DiceEvalScope.PROPERTY)
        .forEach(
            name -> {
              var val = symbolTable.getVariableValue(DiceEvalScope.PROPERTY, name);
              System.out.println(val.getType() + ":" + name + " = " + val.getStringResult());
            });
    System.out.println();*/
  }

}
