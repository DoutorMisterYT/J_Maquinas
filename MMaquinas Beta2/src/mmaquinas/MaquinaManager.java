package mmaquinas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import net.eduard.api.setup.StorageAPI;
import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;

public class MaquinaManager implements StorageAPI.Storable {
  private ArrayList<Maquina> maquinas = new ArrayList<>();
  
  private ArrayList<Combustivel> combustiveis = new ArrayList<>();
  
  private ArrayList<MaquinaInstalada> instaladas = new ArrayList<>();
  
  private Map<Location, MaquinaInstalada> maquinaCache = new HashMap<>();
  
  public ArrayList<Maquina> getMaquinas() {
    return this.maquinas;
  }
  
  public void setMaquinas(ArrayList<Maquina> maquinas) {
    this.maquinas = maquinas;
  }
  
  public Maquina getMaquinas(ItemStack item) {
    for (Maquina maquina : this.maquinas) {
      if (maquina.getIcon().isSimilar(item))
        return maquina; 
    } 
    return null;
  }
  
  public MaquinaInstalada getMaquinas(Location loc) {
    return this.maquinaCache.get(loc);
  }
  
  public Maquina getMaquinas(String nome) {
    for (Maquina maquina : this.maquinas) {
      if (maquina.getTipo().equalsIgnoreCase(nome))
        return maquina; 
    } 
    return null;
  }
  
  public ArrayList<MaquinaInstalada> getInstaladas() {
    return this.instaladas;
  }
  
  public void setInstaladas(ArrayList<MaquinaInstalada> instaladas) {
    this.instaladas = instaladas;
  }
  
  public Object restore(Map<String, Object> arg0) {
    return null;
  }
  
  public void store(Map<String, Object> arg0, Object arg1) {}
  
  public Map<Location, MaquinaInstalada> getMaquinaCache() {
    return this.maquinaCache;
  }
  
  public void setMaquinaCache(Map<Location, MaquinaInstalada> maquinaCache) {
    this.maquinaCache = maquinaCache;
  }
  
  public Combustivel getCombustiveis(ItemStack item) {
    for (Combustivel c1 : this.combustiveis) {
      if (c1.getIcon().isSimilar(item))
        return c1; 
    } 
    return null;
  }
  
  public Combustivel getCombustiveis(String nome) {
    for (Combustivel c1 : this.combustiveis) {
      if (c1.getNome().equalsIgnoreCase(nome))
        return c1; 
    } 
    return null;
  }
  
  public ArrayList<Combustivel> getCombustiveis() {
    return this.combustiveis;
  }
  
  public void setCombustiveis(ArrayList<Combustivel> combustiveis) {
    this.combustiveis = combustiveis;
  }
}
