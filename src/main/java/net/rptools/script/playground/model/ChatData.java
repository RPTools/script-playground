package net.rptools.script.playground.model;

import java.util.LinkedList;
import java.util.List;

// TODO this is a very naive implementation that needs to be improved later
public class ChatData {
  private final List<String> chatLines = new LinkedList<>();


  public void addChatLine(String chatLine) {
    chatLines.add(chatLine);
  }

  public String getHTMLContent() {
    StringBuilder sb = new StringBuilder();
    sb.append("<html>\n  <body>");
    chatLines.forEach(sb::append);
    sb.append("  </body>\n</html>");

    return sb.toString();
  }

}
