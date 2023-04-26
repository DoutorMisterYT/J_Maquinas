package manager;

import net.eduard.api.setup.ConfigAPI;
import net.eduard.api.setup.Mine;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.Plugin;

import mmaquinas.Combustivel;
import mmaquinas.Main;
import mmaquinas.Maquina;

public class MaquinaAPI {
  public static ConfigAPI config = new ConfigAPI("maquinas.yml", (Plugin)Main.pl);
  
  public static ConfigAPI configCombu = new ConfigAPI("combustivel.yml", (Plugin)Main.pl);
  
  public static MaquinaManager manager;
  
  @SuppressWarnings("deprecation")
public static void reload() {
    manager = new MaquinaManager();
    ConfigurationSection sec = config.getConfig().getConfigurationSection("maquinas");
    ConfigurationSection sec2 = configCombu.getConfig().getConfigurationSection("combustiveis");
    if (sec != null)
      for (String key : sec.getKeys(false)) {
        String tipo = sec.getString(String.valueOf(key) + ".Tipo");
        String nome = sec.getString(String.valueOf(key) + ".Nome").replace("&", "§");
        int data = sec.getInt(String.valueOf(key) + ".DATA");
        int id = sec.getInt(String.valueOf(key) + ".ID");
        String dnome = sec.getString(String.valueOf(key) + ".Drop.Nome").replace("&", "§");
        int did = sec.getInt(String.valueOf(key) + ".Drop.ID");
        int ddata = sec.getInt(String.valueOf(key) + ".Drop.DATA");
        int qnt = sec.getInt(String.valueOf(key) + ".Drop.Quantidade");
        Maquina maquina = new Maquina();
        maquina.setTipo(tipo);
        maquina.setIcon(Mine.newItem(nome, Material.getMaterial(id), (short)data));
        if (dnome.equalsIgnoreCase("null")) {
          maquina.setDrop(Mine.newItem(null, Material.getMaterial(did), (short)ddata));
          maquina.getDrop().setAmount(qnt);
          manager.getMaquinas().add(maquina);
          continue;
        } 
        maquina.setDrop(Mine.newItem(dnome, Material.getMaterial(did), (short)ddata));
        maquina.getDrop().setAmount(qnt);
        manager.getMaquinas().add(maquina);
      }  
    if (sec2 != null)
      for (String key : sec2.getKeys(false)) {
        String tipo = sec2.getString(String.valueOf(key) + ".Tipo");
        String nome = sec2.getString(String.valueOf(key) + ".Nome").replace("&", "§");
        int data = sec2.getInt(String.valueOf(key) + ".DATA");
        int id = sec2.getInt(String.valueOf(key) + ".ID");
        int tempo = sec2.getInt(String.valueOf(key) + ".Tempo");
        Combustivel combu = new Combustivel();
        combu.setNome(tipo);
        combu.setDuration(tempo);
        combu.setIcon(Mine.newItem(Material.getMaterial(id), nome, (short)data));
        System.out.println(tipo);
        manager.getCombustiveis().add(combu);
      }  
  }
  
  @SuppressWarnings("deprecation")
public static void save() {
    manager = new MaquinaManager();
    Maquina maquina = new Maquina();
    Combustivel combu = new Combustivel();
    maquina.setTipo("Ferro");
    maquina.setIcon(Mine.newItem(Material.IRON_BLOCK, "§fMaquina de §7Ferro"));
    maquina.setDrop(Mine.newItem(Material.IRON_INGOT, "null"));
    maquina.getDrop().setAmount(5);
    combu.setNome("Comum");
    combu.setIcon(Mine.newItem(Material.COAL, "- 1M"));
    combu.setDuration(1L);
    if (!config.contains("maquinas")) {
      config.set("maquinas." + maquina.getTipo() + ".Tipo", maquina.getTipo());
      config.set("maquinas." + maquina.getTipo() + ".Nome", maquina.getIcon().getItemMeta().getDisplayName());
      config.set("maquinas." + maquina.getTipo() + ".ID", Integer.valueOf(maquina.getIcon().getTypeId()));
      config.set("maquinas." + maquina.getTipo() + ".DATA", Byte.valueOf(maquina.getIcon().getData().getData()));
      config.set("maquinas." + maquina.getTipo() + ".Drop.Nome", maquina.getDrop().getItemMeta().getDisplayName());
      config.set("maquinas." + maquina.getTipo() + ".Drop.ID", Integer.valueOf(maquina.getDrop().getTypeId()));
      config.set("maquinas." + maquina.getTipo() + ".Drop.DATA", Byte.valueOf(maquina.getDrop().getData().getData()));
      config.set("maquinas." + maquina.getTipo() + ".Drop.Quantidade", Integer.valueOf(maquina.getDrop().getAmount()));
      config.saveConfig();
    } else {
      config.get("maquinas");
    } 
    if (!configCombu.contains("combustiveis")) {
      configCombu.set("combustiveis." + combu.getNome() + ".Tipo", combu.getNome());
      configCombu.set("combustiveis." + combu.getNome() + ".Nome", combu.getIcon().getItemMeta().getDisplayName());
      configCombu.set("combustiveis." + combu.getNome() + ".ID", Integer.valueOf(combu.getIcon().getTypeId()));
      configCombu.set("combustiveis." + combu.getNome() + ".DATA", Byte.valueOf(combu.getIcon().getData().getData()));
      configCombu.set("combustiveis." + combu.getNome() + ".Tempo", Long.valueOf(combu.getDuration()));
      configCombu.saveConfig();
    } else {
      configCombu.get("combustiveis");
    } 
  }
  
  public static ConfigAPI getConfig() {
    return config;
  }
  
  public static void setConfig(ConfigAPI config) {
    MaquinaAPI.config = config;
  }
  
  public static MaquinaManager getManager() {
    return manager;
  }
  
  public static void setManager(MaquinaManager manager) {
    MaquinaAPI.manager = manager;
  }
}
