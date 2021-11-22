package su.nexmedia.sunlight.modules.bans.command.list;

import org.jetbrains.annotations.NotNull;
import su.nexmedia.sunlight.config.CommandConfig;
import su.nexmedia.sunlight.modules.bans.BanManager;
import su.nexmedia.sunlight.modules.bans.BanPerms;
import su.nexmedia.sunlight.modules.bans.command.api.AbstractListCommand;
import su.nexmedia.sunlight.modules.bans.punishment.PunishmentType;

public class WarnListCommand extends AbstractListCommand {

    public static final String NAME = "warnlist";

    public WarnListCommand(@NotNull BanManager banManager) {
        super(banManager, CommandConfig.getAliases(NAME), BanPerms.CMD_WARNLIST, PunishmentType.WARN);
    }

    @Override
    @NotNull
    public String getDescription() {
        return this.module.getLang().Command_List_Warn_Desc.getMsg();
    }
}
