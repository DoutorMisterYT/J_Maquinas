package comandos;

import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import manager.MaquinaAPI;
import mmaquinas.Maquina;

public class MaquinaComando implements CommandExecutor {
	public boolean onCommand(CommandSender s, Command c, String l, String[] a) {
		if (c.getName().equalsIgnoreCase("maquina")) {
			Player p = (Player) s;
			if (a.length < 1) {
				p.sendMessage("/maquina <maquina>");
			} else {
				Set<String> maquinas = MaquinaAPI.config.getConfig().getConfigurationSection("maquinas").getKeys(false);
				for (String maquina : maquinas) {
					ConfigurationSection maquinaConfig = MaquinaAPI.config.getConfig().getConfigurationSection("maquinas." + maquina);
					String tipo = maquinaConfig.getString("Tipo");
					if (a[0].equalsIgnoreCase(tipo)) {
						Maquina maq = MaquinaAPI.getManager().getMaquinas(tipo);
						p.getInventory().addItem(maq.getIcon().clone());
						p.sendMessage("Você pegou uma maquina de " + tipo);
					}
				}
			}
		}
		return false;
	}
}
