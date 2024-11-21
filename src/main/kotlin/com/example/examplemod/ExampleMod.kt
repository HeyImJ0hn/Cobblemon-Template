package com.example.examplemod

import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents
import net.fabricmc.loader.api.FabricLoader
import net.kyori.adventure.platform.fabric.FabricServerAudiences
import net.luckperms.api.LuckPerms
import net.luckperms.api.LuckPermsProvider
import net.minecraft.server.MinecraftServer
import net.minecraft.text.Text
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class ExampleMod : ModInitializer {

    companion object {
        private lateinit var _serverInstance: MinecraftServer

        val serverInstance: MinecraftServer
            get() = _serverInstance

        var luckPerms: LuckPerms? = null

        var adventure: FabricServerAudiences? = null

        var LOGGER: Logger = LoggerFactory.getLogger("ExampleMod")

        var hasEconomy = false
    }

    override fun onInitialize() {
        ServerLifecycleEvents.SERVER_STARTED.register { server: MinecraftServer ->
            _serverInstance = server

            try {
                luckPerms = LuckPermsProvider.get()
            } catch (e: Exception) {
                LOGGER.warn("LuckPerms not found")
            }

            adventure = FabricServerAudiences.of(server)

            if (FabricLoader.getInstance().isModLoaded("impactor"))
                hasEconomy = true
            else
                LOGGER.warn("Economy service not found.")
        }
    }

}