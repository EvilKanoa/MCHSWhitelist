package ca.kanoa.whitelist;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import org.bukkit.entity.Player;

public class WhitelistFile {

	private File file;
	private LinkedList<String> users;
	private Set<String> cache;
	
	public WhitelistFile(File file) {
		this.file = file;
		users = new LinkedList<String>();
		cache = new HashSet<String>();
	}
	
	public boolean load() {
		if (!file.exists() || file.isDirectory()) {
			try {
				file.createNewFile();
			} catch (IOException e) {}
			return false;
		}
		
		users.clear();
		
		BufferedReader reader = null;
		
		try {
			reader = new BufferedReader(new FileReader(file));
			
			String line;
			while ((line = reader.readLine()) != null) {
				users.add(line.toLowerCase());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		}
		
		return true;
	}
	
	public boolean isWhitelisted(String username) {
		long start = System.nanoTime();
		username = username.toLowerCase();
		boolean whitelisted = false;
		if (cache.contains(username)) {
			whitelisted = true;
		} else if (users.contains(username)) {
			cache.add(username);
			whitelisted = true;
		}
		System.out.println("Time: " + (System.nanoTime() - start));
		return whitelisted;
	}
	
	public boolean isWhitelisted(Player player) {
		return isWhitelisted(player.getName());
	}
	
	public LinkedList<String> getUserSet() {
		return new LinkedList<String>(users);
	}
	
	public String[] getUserArray() {
		return users.toArray(new String[0]);
	}
	
	public Set<String> getCache() {
		return cache;
	}
	
}
