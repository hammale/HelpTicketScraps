package tk.tyzoid.plugins;

import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import com.nijiko.permissions.PermissionHandler;
import com.nijikokun.bukkit.Permissions.Permissions;

public class HelpTicket extends JavaPlugin {
	String pluginname = "HelpTicket";
	
    private final cmdExecuter cmdExecuter = new cmdExecuter(this);
    public settings cSettings = new settings();
    public PermissionHandler permissionHandler;
    public boolean permissionsExists = false;
    public boolean useSuperperms = false;

    public void onDisable() {
        System.out.println("[" + pluginname +"] " + pluginname + " is closing...");
    }

    public void onEnable() {
        // TODO: Place any custom enable code here including the registration of any events

        // Register our events
        
    	getCommand("newticket").setExecutor(cmdExecuter);
    	getCommand("nticket").setExecutor(cmdExecuter);
    	getCommand("htreload").setExecutor(cmdExecuter);
		getCommand("htr").setExecutor(cmdExecuter);
        
        PluginDescriptionFile pdfFile = this.getDescription();
        System.out.println("[" + pluginname + "] Starting " + pluginname + " v" + pdfFile.getVersion() + "...");
        setupPermissions();
        cSettings.readSettings();
    }
    
    private void setupPermissions() {
        Plugin permissionsPlugin = this.getServer().getPluginManager().getPlugin("Permissions");
        
        if (permissionHandler == null) {
            if (permissionsPlugin != null) {
            	permissionsExists = true;
                permissionHandler = ((Permissions) permissionsPlugin).getHandler();
                System.out.println("[" + pluginname + "] Permissions found!");
            } else {
                System.out.println("[" + pluginname + "] Permissions not detected. Using defaults.");
                permissionsExists = false;
                
                try{
                	@SuppressWarnings("unused")
					Permission fakePerm = new Permission("fake.perm");
                	useSuperperms = true;
                } catch(Exception e){
                	//superperms doesn't exist
                }
            }
        }
    }
    
    /* Valid nodes:
     * helpticket.*
     * helpticket.send
     */
    public boolean hasPermission(Player p, String node){
    	if(!useSuperperms){
    		return permissionHandler.has(p, node);
    	} else {
    		return p.hasPermission(node);
    	}
    }
}