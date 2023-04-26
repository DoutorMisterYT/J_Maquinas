package mmaquinas;

import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;

public class Maquina {

	private String tipo;
	private String dono;
	private ItemStack icon;
	private ItemStack drop;
	private int level;
	private long fuel;
	private Location location;
	private boolean ligada;

	public String getDono() {
		return dono;
	}

	public void setDono(String dono) {
		this.dono = dono;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public long getFuel() {
		return fuel;
	}

	public void setFuel(long fuel) {
		this.fuel = fuel;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public boolean isLigada() {
		return ligada;
	}

	public void setLigada(boolean ligada) {
		this.ligada = ligada;
	}

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

}
