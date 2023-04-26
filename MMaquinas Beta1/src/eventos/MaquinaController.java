package eventos;

import net.eduard.api.setup.Mine;

import java.sql.PreparedStatement;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import manager.MaquinaAPI;
import mmaquinas.Combustivel;
import mmaquinas.Main;
import mmaquinas.Maquina;
import mmaquinas.MySql;

public class MaquinaController implements Listener {
	@EventHandler
	public void colocarMaquina(BlockPlaceEvent e) {
		Player p = e.getPlayer();
		Maquina maquina = MaquinaAPI.getManager().getMaquinas(e.getItemInHand());
		if (maquina != null) {
			maquina.setDono(p.getName());
			maquina.setLevel(1);
			maquina.setLigada(false);
			maquina.setLocation(e.getBlock().getLocation());
			MaquinaAPI.getManager().getMaquinaCache().put(maquina.getLocation(), maquina);
			try {
				PreparedStatement criar = MySql.getConnection().prepareStatement("INSERT INTO maquina SET tipo = ?, dono = ?, x = ?, y = ?, z = ?, world = ?");
				criar.setString(1, maquina.getTipo());
				criar.setString(2, p.getDisplayName());
				criar.setLong(3, maquina.getLocation().getBlockX());
				criar.setLong(4, maquina.getLocation().getBlockY());
				criar.setLong(5, maquina.getLocation().getBlockZ());
				criar.setString(6, maquina.getLocation().getWorld().getName());
				criar.execute();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			p.sendMessage("§aVocê colocou uma maquina de §5" + maquina.getTipo() + " §ano chão!");
		}
	}

	@EventHandler
	public void ClicarMaquina(final PlayerInteractEvent e) {
		final Player p = e.getPlayer();
		Combustivel combu = MaquinaAPI.getManager().getCombustiveis(p.getItemInHand());
		if (combu == null)
			return;
		Maquina maquina = MaquinaAPI.getManager().getMaquinas(e.getClickedBlock().getLocation());
		if (maquina == null)
			return;
		if (maquina.getFuel() <= 100L) {
			final ArmorStand as = (ArmorStand) e.getClickedBlock().getLocation().getWorld()
					.spawnEntity(e.getClickedBlock().getLocation().add(+0.5, +1.0, +0.5), EntityType.ARMOR_STAND);
			as.setGravity(false);
			as.setCanPickupItems(false);
			as.setCustomNameVisible(true);
			as.setMarker(true);
			as.setVisible(false);
			Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.pl, new Runnable() {
				long seconds = combu.getDuration();

				public void run() {
					if (this.seconds >= 0L) {
						maquina.setLigada(true);
						as.setCustomName("§7Status: §aON");
						e.getClickedBlock().getLocation().getWorld()
								.dropItem(e.getClickedBlock().getLocation().add(+0.5, +1.0, +0.5), maquina.getDrop());
						maquina.setFuel(maquina.getFuel() - 1);
						this.seconds--;
					}
					if (this.seconds == 0L) {
						maquina.setLigada(false);
						as.remove();
						p.sendMessage("§cO combustivel da maquina de §7" + maquina.getTipo() + " §cacabou!");
						this.seconds--;
					}
				}
			}, 0, 20);
		}
		if (maquina.getFuel() == 100L) {
			p.sendMessage("tanque da sua maquina estcheio!");
		} else {
			maquina.setFuel(maquina.getFuel() + combu.getDuration());
			Mine.remove((Inventory) p.getInventory(), p.getItemInHand(), 1);
		}
	}

	@EventHandler
	public void removerMaquina(BlockBreakEvent e) {
		Player p = e.getPlayer();
		Maquina maquina = MaquinaAPI.getManager().getMaquinas(e.getBlock().getLocation());
		if (maquina == null)
			return;
		if (maquina.isLigada()) {
			e.setCancelled(true);
			p.sendMessage("§c Você não pode remover uma maquina ligada!");
		} else {
			e.setCancelled(true);
			e.getBlock().setType(Material.AIR);
			ItemStack item = maquina.getIcon();
			item.setAmount(1);
			p.getInventory().addItem(new ItemStack[] { item });
			MaquinaAPI.getManager().getMaquinaCache().remove(maquina.getLocation(), maquina);
			p.sendMessage("§cVocê removeu uma maquina de " + maquina.getTipo());
		}
	}
}
