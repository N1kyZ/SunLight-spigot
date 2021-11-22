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

public class EnderchestCommand extends GeneralCommand<SunLight> {

    public static final String NAME = "enderchest";

    public EnderchestCommand(@NotNull SunLight plugin) {
        super(plugin, CommandConfig.getAliases(NAME), SunPerms.CMD_ENDERCHEST);
    }

    @Override
    @NotNull
    public String getUsage() {
        return plugin.lang().Command_Enderchest_Usage.getMsg();
    }

    @Override
    @NotNull
    public String getDescription() {
        return plugin.lang().Command_Enderchest_Desc.getMsg();
    }

    @Override
    public boolean isPlayerOnly() {
        return false;
    }

    @Override
    @NotNull
    public List<String> getTab(@NotNull Player player, int i, @NotNull String[] args) {
        if (i == 1 && player.hasPermission(SunPerms.CMD_ENDERCHEST_OTHERS)) {
            return PlayerUT.getPlayerNames();
        }
        return super.getTab(player, i, args);
    }

    @Override
    public void onExecute(@NotNull CommandSender sender, @NotNull String label, @NotNull String[] args) {
        if (args.length == 0 && !(sender instanceof Player)) {
            this.errorSender(sender);
            return;
        }

        Player pTarget = (Player) sender;
        Player pFrom = pTarget;

        if (args.length == 1) {
            if (!sender.hasPermission(SunPerms.CMD_ENDERCHEST_OTHERS)) {
                this.errorPermission(sender);
                return;
            }

            pFrom = plugin.getServer().getPlayer(args[0]);
            if (pFrom == null) {
                this.errorPlayer(sender);
                return;
            }
        }
        pTarget.openInventory(pFrom.getEnderChest());
    }
}
