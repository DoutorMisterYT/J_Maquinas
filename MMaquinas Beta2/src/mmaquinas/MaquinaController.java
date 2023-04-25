package mmaquinas;

import net.eduard.api.setup.Mine;
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
import org.bukkit.plugin.Plugin;

public class MaquinaController implements Listener {
  @EventHandler
  public void colocarMaquina(BlockPlaceEvent e) {
    Player p = e.getPlayer();
    Maquina maquina2 = MaquinaAPI.getManager().getMaquinas(e.getItemInHand());
    if (maquina2 != null) {
      MaquinaInstalada maquina = new MaquinaInstalada();
      maquina.setDono(p.getName());
      maquina.setLevel(1);
      maquina.setMaquina(maquina2);
      maquina.setLigada(false);
      maquina.setLocation(e.getBlock().getLocation());
      MaquinaAPI.getManager().getInstaladas().add(maquina);
      MaquinaAPI.getManager().getMaquinaCache().put(maquina.getLocation(), maquina);
      p.sendMessage("§aVocê colocou uma maquina de" + maquina2.getTipo() + " §ano chão!");
    } 
  }
  
  @EventHandler
  public void ClicarMaquina(final PlayerInteractEvent e) {
    final Player p = e.getPlayer();
    Combustivel combu = MaquinaAPI.getManager().getCombustiveis(p.getItemInHand());
    if (combu == null)
      return; 
    final MaquinaInstalada maquina = MaquinaAPI.getManager().getMaquinas(e.getClickedBlock().getLocation());
    if (maquina == null)
      return; 
    if (maquina.getFuel() <= 100L) {
      final ArmorStand as = (ArmorStand)e.getClickedBlock().getLocation().getWorld().spawnEntity(e.getClickedBlock().getLocation().add(0.5D, 1.0D, 0.5D), EntityType.ARMOR_STAND);
      as.setGravity(false);
      as.setCanPickupItems(false);
      as.setCustomNameVisible(true);
      as.setMarker(true);
      as.setVisible(false);
      Bukkit.getScheduler().scheduleSyncRepeatingTask((Plugin)Main.pl, new Runnable() {
            long seconds;
            
            public void run() {
              if (this.seconds >= 0L) {
                maquina.setLigada(true);
                as.setCustomName("§7Status: §aON");
                e.getClickedBlock().getLocation().getWorld().dropItem(e.getClickedBlock().getLocation().add(0.5D, 1.0D, 0.5D), maquina.getMaquina().getDrop());
                maquina.setFuel(maquina.getFuel() - 1L);
                this.seconds--;
              } 
              if (this.seconds == 0L) {
                maquina.setLigada(false);
                as.remove();
                p.sendMessage("§cO combustivel da maquina de §7" + maquina.getMaquina().getTipo() + " §cacabou!");
                this.seconds--;
              } 
            }
          }, 0, 20);
    } 
    if (maquina.getFuel() == 100L) {
      p.sendMessage("tanque da sua maquina estcheio!");
    } else {
      maquina.setFuel(maquina.getFuel() + combu.getDuration());
      Mine.remove((Inventory)p.getInventory(), p.getItemInHand(), 1);
    } 
  }
  
  @EventHandler
  public void removerMaquina(BlockBreakEvent e) {
    Player p = e.getPlayer();
    MaquinaInstalada maquina = MaquinaAPI.getManager().getMaquinas(e.getBlock().getLocation());
    if (maquina == null)
      return; 
    if (maquina.isLigada()) {
      e.setCancelled(true);
      p.sendMessage("§c Você não pode remover uma maquina ligada!");
    } else {
      e.setCancelled(true);
      e.getBlock().setType(Material.AIR);
      ItemStack item = maquina.getMaquina().getIcon();
      item.setAmount(1);
      p.getInventory().addItem(new ItemStack[] { item });
      MaquinaAPI.getManager().getMaquinaCache().remove(maquina.getLocation(), maquina);
      p.sendMessage("§cVocê removeu uma maquina de "+ maquina.getMaquina().getTipo());
    } 
  }
}
