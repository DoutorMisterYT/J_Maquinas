package mmaquinas;

import org.bukkit.inventory.ItemStack;

public class Combustivel {
  private String nome;
  
  private ItemStack icon;
  
  private long duration;
  
  public String getNome() {
    return this.nome;
  }
  
  public void setNome(String nome) {
    this.nome = nome;
  }
  
  public ItemStack getIcon() {
    return this.icon;
  }
  
  public void setIcon(ItemStack icon) {
    this.icon = icon;
  }
  
  public long getDuration() {
    return this.duration;
  }
  
  public void setDuration(long duration) {
    this.duration = duration;
  }
}
