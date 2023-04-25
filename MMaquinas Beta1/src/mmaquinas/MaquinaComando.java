package mmaquinas;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class MaquinaComando implements CommandExecutor {
  public boolean onCommand(CommandSender s, Command c, String l, String[] a) {
    if (c.getName().equalsIgnoreCase("maquina")) {
      Player p = (Player)s;
      if (a.length < 1) {
        p.sendMessage("<maquina>");
      } else {
        Maquina maquina = MaquinaAPI.manager.getMaquinas(a[0]);
        if (maquina == null) {
          p.sendMessage("Essa maquina nexiste!");
        } else {
          ItemStack maqItem = maquina.getIcon().clone();
          maqItem.setAmount(1);
          p.getInventory().addItem(new ItemStack[] { maqItem });
        } 
      } 
    } 
    return false;
  }
}
