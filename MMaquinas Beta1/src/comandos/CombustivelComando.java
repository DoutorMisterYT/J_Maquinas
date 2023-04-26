package comandos;

import net.eduard.api.setup.Extra;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import manager.MaquinaAPI;
import mmaquinas.Combustivel;

public class CombustivelComando implements CommandExecutor {
	public boolean onCommand(CommandSender s, Command c, String l, String[] a) {
		if (c.getName().equalsIgnoreCase("combustivel")) {
			Player p = (Player) s;
			if (a.length < 2) {
				p.sendMessage("<combustivel> <qnt>");
			} else {
				Combustivel combu = MaquinaAPI.getManager().getCombustiveis(a[0]);
				if (combu == null) {
					p.sendMessage("Esse combustivel nexiste!");
				} else {
					ItemStack c1 = combu.getIcon().clone();
					c1.setAmount(Extra.toInt(a[1]).intValue());
					p.getInventory().addItem(new ItemStack[] { c1 });
				}
			}
		}
		return false;
	}
}
