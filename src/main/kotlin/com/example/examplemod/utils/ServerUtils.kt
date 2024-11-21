package com.example.examplemod.utils

import com.example.examplemod.ExampleMod
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.text.Text
import java.util.*

object ServerUtils {
    fun broadcast(message: String) {
        val server = ExampleMod.serverInstance

        for (player in server.playerManager.playerList) {
            if (player is ServerPlayerEntity) {
                player.sendMessage(Text.literal(message), false)
            }
        }
    }

    fun getPlayerByUUID(uuid: UUID): ServerPlayerEntity? {
        val server = ExampleMod.serverInstance
        val playerManager = server.playerManager

        return playerManager.getPlayer(uuid)
    }

    fun getPlayerByName(name: String): ServerPlayerEntity? {
        val server = ExampleMod.serverInstance
        val playerManager = server.playerManager

        return playerManager.getPlayer(name)
    }

    fun getAllPlayerNames(): Array<out String>? {
        val server = ExampleMod.serverInstance
        val playerManager = server.playerManager

        return playerManager.playerNames
    }

    fun getAllPlayers(): MutableList<ServerPlayerEntity> {
        val server = ExampleMod.serverInstance
        val playerManager = server.playerManager

        return playerManager.playerList
    }
}