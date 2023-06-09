package mmaquinas;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import comandos.CombustivelComando;
import comandos.MaquinaComando;
import eventos.MaquinaController;
import manager.MaquinaAPI;

public class Main extends JavaPlugin {
  public static Main pl;
  
  public void onEnable() {
    pl = this;
    MaquinaAPI.reload();
    Bukkit.getConsoleSender().sendMessage("Maquinas carregadas com sucesso!");
    registerComandos();
    registerEventos();
    MySql.connect();
  }
  
  public void onDisable() {
    MaquinaAPI.save();
    MySql.disconnect();
  }
  
  public void registerComandos() {
    getCommand("maquina").setExecutor(new MaquinaComando());
    getCommand("combustivel").setExecutor(new CombustivelComando());
  }
  
  public void registerEventos() {
    Bukkit.getPluginManager().registerEvents(new MaquinaController(), (Plugin)pl);
  }
}
