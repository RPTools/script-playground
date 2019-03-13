package net.rptools.script.playground.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import javafx.concurrent.Worker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.web.WebView;
import net.rptools.script.playground.chatparser.SimpleChatParserListener;
import net.rptools.script.playground.model.ChatData;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

public class MainScreenController {
  @FXML // fx:id="inputTextArea"
  private JFXTextArea inputTextArea; // Value injected by FXMLLoader

  @FXML // fx:id="executeInputButton"
  private JFXButton executeInputButton; // Value injected by FXMLLoader

  @FXML // fx:id="outputWebview"
  private WebView outputWebView; // Value injected by FXMLLoader


  private final ChatData chatData = new ChatData();

  @FXML
  void doExecute(ActionEvent event) {

    outputWebView.getEngine().loadContent(chatData.getHTMLContent());
    outputWebView.getEngine().getLoadWorker().stateProperty().addListener( (o, oldVal, newVal) -> {
      if (newVal == Worker.State.SUCCEEDED) {
        outputWebView.getEngine().executeScript("window.scrollTo(0, document.body.scrollHeight);");
      }
    });

    // Create a lexer that feeds off of input CharStream
    net.rptools.script.parser.ChatLexer chatLexer = new net.rptools.script.parser.ChatLexer(
        CharStreams.fromString(inputTextArea.getText()));

    // create a buffer of tokens pulled from the lexer
    CommonTokenStream tokens = new CommonTokenStream(chatLexer);

    // create a parser that feeds off the tokens buffer
    net.rptools.script.parser.ChatParser parser = new net.rptools.script.parser.ChatParser(tokens);

    ParseTree parseTree = parser.chat();

    // Create a generic parse expressiontree walker that can trigger callbacks
    ParseTreeWalker walker = new ParseTreeWalker();

    SimpleChatParserListener listener = new SimpleChatParserListener();
    walker.walk(listener, parseTree);

    // TODO Naive for now but will improve it later
    chatData.addChatLine("<div>" + listener.getOutputText() + "</div>");
    System.out.println(listener.getOutputText());
    outputWebView.getEngine().loadContent(chatData.getHTMLContent());


  }


  @FXML // This method is called by the FXMLLoader when initialization is complete
  void initialize() {
    assert inputTextArea != null : "fx:id=\"inputTextArea\" was not injected: check FXML file 'Playground.fxml'.";
    assert executeInputButton != null : "fx:id=\"executeInputButton\" was not injected: check FXML file 'Playground.fxml'.";
    assert outputWebView != null : "fx:id=\"outputWebView\" was not injected: check FXML file 'Playground.fxml'.";

    String css = getClass().getResource("/net/rptools/script/playground/css/OutputDefault.css").toString();
    outputWebView.getEngine().setUserStyleSheetLocation(css);
    outputWebView.getEngine().load(getClass().getResource(
        "/net/rptools/script/playground/html/OutputDefault.html").toString());

  }

}
