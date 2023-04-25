package mmaquinas;

import org.bukkit.Location;

public class MaquinaInstalada {
  private String dono;
  
  private Maquina maquina;
  
  private Location location;
  
  private int level;
  
  private long fuel;
  
  private boolean ligada;
  
  public boolean isLigada() {
    return this.ligada;
  }
  
  public void setLigada(boolean ligada) {
    this.ligada = ligada;
  }
  
  public String getDono() {
    return this.dono;
  }
  
  public void setDono(String dono) {
    this.dono = dono;
  }
  
  public Location getLocation() {
    return this.location;
  }
  
  public void setLocation(Location location) {
    this.location = location;
  }
  
  public int getLevel() {
    return this.level;
  }
  
  public void setLevel(int level) {
    this.level = level;
  }
  
  public long getFuel() {
    return this.fuel;
  }
  
  public void setFuel(long l) {
    this.fuel = l;
  }
  
  public Maquina getMaquina() {
    return this.maquina;
  }
  
  public void setMaquina(Maquina maquina) {
    this.maquina = maquina;
  }
}
