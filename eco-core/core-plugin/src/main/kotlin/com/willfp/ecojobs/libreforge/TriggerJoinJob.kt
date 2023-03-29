package com.willfp.ecojobs.libreforge

import com.willfp.ecojobs.api.event.PlayerJobJoinEvent
import com.willfp.ecojobs.api.getJobLevel
import com.willfp.libreforge.triggers.Trigger
import com.willfp.libreforge.triggers.TriggerData
import com.willfp.libreforge.triggers.TriggerParameter
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler

object TriggerJoinJob : Trigger("join_job") {
    override val parameters = setOf(
        TriggerParameter.PLAYER,
        TriggerParameter.LOCATION,
        TriggerParameter.EVENT
    )

    @EventHandler(ignoreCancelled = true)
    fun handle(event: PlayerJobJoinEvent) {
        val player = event.player as? Player ?: return

        this.dispatch(
            player,
            TriggerData(
                player = player,
                location = player.location,
                event = event,
                value = player.getJobLevel(event.job).toDouble()
            )
        )
    }
}
