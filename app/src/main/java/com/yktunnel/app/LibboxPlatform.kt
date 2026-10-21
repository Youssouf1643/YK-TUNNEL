package com.yktunnel.app

import android.net.VpnService
import android.os.Build
import android.os.ParcelFileDescriptor
import io.nekohasekai.libbox.*
import java.net.Inet4Address
import java.net.Inet6Address

class LibboxPlatform(private val service: VpnService) : PlatformInterface {

    private var tun: ParcelFileDescriptor? = null

    override fun openTun(options: TunOptions): Int {
        tun?.close()
        tun = null

        val builder = service.Builder()
            .setSession("YK TUNNEL")
            .setMtu(options.mTU)
        
        val ipv4 = options.inet4Address
        while (ipv4.hasNext()) {
            val route = ipv4.next()
            builder.addAddress(route.address(), route.prefix())
        }

        val ipv6 = options.inet6Address
        while (ipv6.hasNext()) {
            val route = ipv6.next()
            builder.addAddress(route.address(), route.prefix())
        }

        val dns = options.dnsServerAddress
        while (dns.hasNext()) {
            builder.addDnsServer(dns.next())
        }

        val routes4 = options.inet4RouteAddress
        while (routes4.hasNext()) {
            val route = routes4.next()
            builder.addRoute(route.address(), route.prefix())
        }

        val routes6 = options.inet6RouteAddress
        while (routes6.hasNext()) {
            val route = routes6.next()
            builder.addRoute(route.address(), route.prefix())
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            builder.setMetered(false)
        }

        tun = builder.establish()
            ?: throw IllegalStateException("Impossible de créer l'interface VPN")

        return tun!!.fd
    }

    override fun includeAllNetworks(): Boolean = false
    override fun underNetworkExtension(): Boolean = false
    override fun usePlatformAutoDetectInterfaceControl(): Boolean = false
    override fun usePlatformBridge(): Boolean = false
    override fun usePlatformShell(): Boolean = false
    override fun useProcFS(): Boolean = false
    override fun tailscaleHostname(): String = ""
    override fun registerMyInterface(name: String) {}
    override fun clearDNSCache() {}
    override fun closeDefaultInterfaceMonitor(listener: InterfaceUpdateListener) {}
    override fun closeNeighborMonitor(listener: NeighborUpdateListener) {}
    override fun startDefaultInterfaceMonitor(listener: InterfaceUpdateListener) {}
    override fun startNeighborMonitor(listener: NeighborUpdateListener) {}
    override fun autoDetectInterfaceControl(fd: Int) {}
    override fun checkPlatformShell() {}
    override fun cancelNotification(identifier: String, typeID: Int) {}
    override fun sendNotification(notification: Notification) {}
    override fun setSystemProxyEnabled(enabled: Boolean) {}
    override fun getInterfaces(): NetworkInterfaceIterator =
        object : NetworkInterfaceIterator {
            override fun hasNext(): Boolean = false
            override fun next(): NetworkInterface = NetworkInterface()
        }
    override fun findConnectionOwner(
        ipProtocol: Int,
        sourceAddress: String,
        sourcePort: Int,
        destinationAddress: String,
        destinationPort: Int
    ): ConnectionOwner = ConnectionOwner()
    override fun connectSSHAgent(): Int = -1
    override fun getSystemProxyStatus(): SystemProxyStatus = SystemProxyStatus()
    override fun serviceReload() {}
    override fun serviceStop() {}
    override fun triggerNativeCrash() {}
    override fun writeDebugMessage(message: String) {}
    override fun localDNSTransport(): LocalDNSTransport? = null
    override fun lookupSFTPServer(): String = ""
    override fun lookupUser(username: String): PlatformUser = PlatformUser()
    override fun openShellSession(
        user: PlatformUser,
        command: String,
        environ: StringIterator,
        term: String,
        rows: Int,
        cols: Int
    ): ShellSession? = null
    override fun readSystemSSHHostKey(): String = ""
    override fun readWIFIState(): WIFIState = WIFIState("", "")
    override fun usePlatformBridge(): Boolean = false
    override fun createBridge(options: BridgeOptions): BridgeSession? = null
}
