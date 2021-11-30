package me.stevenlol.bots.baritone;

import java.util.concurrent.CompletableFuture;

public class WebInteraction {

    public CompletableFuture<String> getCommand(final String bot) {
        return CompletableFuture.supplyAsync(() -> {
            HTTPRequest resp = new HTTPRequest("http://bots.retroxd.me/baritonebots/api/get_command.php", "GET");
            resp.addArgument("username", bot);
            return resp.send();
        });
    }

    public boolean setCommand(final String bot, final String command) {
        HTTPRequest resp = new HTTPRequest("http://bots.retroxd.me/baritonebots/api/update_command.php", "GET");
        resp.addArgument("username", bot);
        resp.addArgument("command", command);
        return resp.send().equals("COMMAND_SET");
    }

}
