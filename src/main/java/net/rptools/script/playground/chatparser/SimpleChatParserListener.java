package net.rptools.script.playground.chatparser;

import net.rptools.script.parser.ChatParserBaseListener;
import net.rptools.script.playground.model.DiceRoller;

public class SimpleChatParserListener extends ChatParserBaseListener {

  private final StringBuilder outputText = new StringBuilder();

  public String getOutputText() {
    return outputText.toString();
  }

  @Override
  public void enterText(net.rptools.script.parser.ChatParser.TextContext ctx) {
    outputText.append(ctx.getText());
  }


  @Override
  public void enterRollBody(net.rptools.script.parser.ChatParser.RollBodyContext ctx) {
    String diceRollExpr = ctx.getText();

    DiceRoller diceRoller = new DiceRoller();
    outputText.append(diceRoller.roll(diceRollExpr));

  }

  @Override
  public void enterScriptBody(net.rptools.script.parser.ChatParser.ScriptBodyContext ctx) {
    outputText.append("{{Script not implemented yet:").append(ctx.getText()).append("}}");
  }
}
