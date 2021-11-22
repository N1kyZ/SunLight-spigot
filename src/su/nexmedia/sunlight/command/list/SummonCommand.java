package su.nexmedia.sunlight.command.list;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import su.nexmedia.engine.api.command.GeneralCommand;
import su.nexmedia.engine.utils.PlayerUT;
import su.nexmedia.sunlight.SunLight;
import su.nexmedia.sunlight.SunPerms;
import su.nexmedia.sunlight.config.CommandConfig;

import java.util.List;

public class SummonCommand extends GeneralCommand<SunLight> {

    public static final String NAME = "summon";

    public SummonCommand(@NotNull SunLight plugin) {
        super(plugin, CommandConfig.getAliases(NAME), SunPerms.CMD_SUMMON);
    }

    @Override
    @NotNull
    public String getUsage() {
        return plugin.lang().Command_Summon_Usage.getMsg();
    }

    @Override
    @NotNull
    public String getDescription() {
        return plugin.lang().Command_Summon_Desc.getMsg();
    }

    @Override
    public boolean isPlayerOnly() {
        return true;
    }

    @Override
    @NotNull
    public List<String> getTab(@NotNull Player player, int i, @NotNull String[] args) {
        if (i == 1) {
            return PlayerUT.getPlayerNames();
        }
        return super.getTab(player, i, args);
    }

    @Override
    public void onExecute(@NotNull CommandSender sender, @NotNull String label, @NotNull String[] args) {
        if (args.length != 1) {
            this.printUsage(sender);
            return;
        }

        Player pTarget = plugin.getServer().getPlayer(args[0]);
        if (pTarget == null) {
            this.errorPlayer(sender);
            return;
        }

        Player pFrom = (Player) sender;
        pTarget.teleport(pFrom);
    }
}
