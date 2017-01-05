package discordBot;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

/**
 * Created by JohanvonHacht on 06/12/16.
 */
public interface Command {

    public boolean called(String[] args, MessageReceivedEvent event);
    public void action(String[] args, MessageReceivedEvent event);
    public void executed(boolean success, MessageReceivedEvent event);
    
    // coool
}
