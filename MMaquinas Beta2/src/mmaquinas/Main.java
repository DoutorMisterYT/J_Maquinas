package mmaquinas;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
  public static Main pl;
  
  public void onEnable() {
    pl = this;
    MaquinaAPI.reload();
    Bukkit.getConsoleSender().sendMessage("Maquinas carregadas com sucesso!");
    registerComandos();
    registerEventos();
  }
  
  public void onDisable() {
    MaquinaAPI.save();
  }
  
  public void registerComandos() {
    getCommand("maquina").setExecutor(new MaquinaComando());
    getCommand("combustivel").setExecutor(new CombustivelComando());
  }
  
  public void registerEventos() {
    Bukkit.getPluginManager().registerEvents(new MaquinaController(), (Plugin)pl);
  }
}
