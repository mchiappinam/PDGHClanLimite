package me.mchiappinam.pdghclanlimite;

import net.sacredlabyrinth.phaed.simpleclans.ClanPlayer;
import net.sacredlabyrinth.phaed.simpleclans.SimpleClans;
import net.sacredlabyrinth.phaed.simpleclans.api.events.SimpleClansJoinEvent;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {
	protected SimpleClans core2;
	protected boolean clan = false;

	public void onEnable() {
		if (getServer().getPluginManager().getPlugin("SimpleClans") != null) {
			getServer().getConsoleSender().sendMessage("§3[PDGHClanLimite] §2SimpleClans encontrado! Ativando limite de clan...");
			core2 = ((SimpleClans)getServer().getPluginManager().getPlugin("SimpleClans"));
			clan=true;
		}else{
			getServer().getConsoleSender().sendMessage("§3[PDGHClanLimite] §2SimpleClans não encontrado! As TAGs não serão ativadas.");
			clan=false;
		}
		
		if(clan)
			getServer().getPluginManager().registerEvents(this, this);

		getServer().getConsoleSender().sendMessage("§3[PDGHClanLimite] §2ativado - Plugin by: mchiappinam");
		getServer().getConsoleSender().sendMessage("§3[PDGHClanLimite] §2Acesse: http://pdgh.com.br/");
	}

	public void onDisable() {
		getServer().getConsoleSender().sendMessage("§3[PDGHClanLimite] §2desativado - Plugin by: mchiappinam");
		getServer().getConsoleSender().sendMessage("§3[PDGHClanLimite] §2Acesse: http://pdgh.com.br/");
	}

	@EventHandler
	public void onJoinClan(SimpleClansJoinEvent e) {
		if(e.getClan().getAllMembers().size() > 10) {
			e.getClanPlayer().toPlayer().sendMessage("§3[PDGH] §cLimite de jogadores atingido no clan! §e(10/10)");
			e.getClan().removeMember(e.getClanPlayer().toPlayer().getName());
			e.getClan().removePlayerFromClan(e.getClanPlayer().toPlayer().getName());
			for (ClanPlayer p : e.getClan().getOnlineMembers())
				p.toPlayer().sendMessage("§bO jogador "+e.getClanPlayer().toPlayer().getName()+" tentou entrar no clan. §e(10/10)");
		}
	}
}

