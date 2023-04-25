package mmaquinas;

import org.bukkit.inventory.ItemStack;

public class Maquina {
  private String tipo;
  
  private ItemStack icon;
  
  private ItemStack drop;
  
  private int maxLevel = 1;
  
  public String getTipo() {
    return this.tipo;
  }
  
  public void setTipo(String tipo) {
    this.tipo = tipo;
  }
  
  public ItemStack getIcon() {
    return this.icon;
  }
  
  public void setIcon(ItemStack icon) {
    this.icon = icon;
  }
  
  public ItemStack getDrop() {
    return this.drop;
  }
  
  public void setDrop(ItemStack drop) {
    this.drop = drop;
  }
  
  public int getMaxLevel() {
    return this.maxLevel;
  }
  
  public void setMaxLevel(int maxLevel) {
    this.maxLevel = maxLevel;
  }
}
