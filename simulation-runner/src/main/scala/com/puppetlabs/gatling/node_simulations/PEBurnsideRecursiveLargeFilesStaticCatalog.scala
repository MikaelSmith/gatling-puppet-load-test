package com.puppetlabs.gatling.node_simulations
import com.puppetlabs.gatling.runner.SimulationWithScenario
import org.joda.time.LocalDateTime
import org.joda.time.format.ISODateTimeFormat
import java.util.UUID

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
// import io.gatling.jdbc.Predef._

class PEBurnsideRecursiveLargeFilesStaticCatalog extends SimulationWithScenario {
	val concurrentScn = null

// 	val httpProtocol = http
// 		.baseURL("https://${node}:8140")

	val reportBody = ELFileBody("PEBurnsideRecursiveLargeFilesStaticCatalog_0035_request.txt")

	val headers_0 = Map(
		"Accept" -> "pson, yaml, binary",
		"X-Puppet-Version" -> "4.4.0")

	val headers_3 = Map(
		"Accept" -> "pson, yaml, dot, binary",
		"X-Puppet-Version" -> "4.4.0")

	val headers_35 = Map(
		"Accept" -> "pson, yaml",
		"Content-Type" -> "text/pson",
		"X-Puppet-Version" -> "4.4.0",
	  "Connection" -> "close")
// val uri1 = "https://${node}:8140/puppet/v3"

	val scn = scenario("PEBurnsideRecursiveLargeFilesStaticCatalog")
		.exec(http("node")
			.get("/puppet/v3/node/${node}?environment=production&transaction_uuid=4a446752-7acf-46e8-bc8b-9c3d6e0bada9&fail_on_404=true")
			.headers(headers_0))
		.exec(http("filemeta pluginfacts")
			.get("/puppet/v3/file_metadatas/pluginfacts?environment=production&links=follow&recurse=true&source_permissions=use&ignore=.svn&ignore=CVS&ignore=.git&checksum_type=md5")
			.headers(headers_0))
		.exec(http("filemeta plugins")
			.get("/puppet/v3/file_metadatas/plugins?environment=production&links=follow&recurse=true&source_permissions=ignore&ignore=.svn&ignore=CVS&ignore=.git&checksum_type=md5")
			.headers(headers_0))
		.pause(207 milliseconds)
		.exec(http("catalog")
			.post("/puppet/v3/catalog/${node}?environment=production")
			.headers(headers_3)
			.formParam("environment", "production")
			.formParam("facts_format", "pson")
			.formParam("facts", "%7B%22name%22%3A%22${node}%22%2C%22values%22%3A%7B%22aio_agent_build%22%3A%221.3.5.328.g7a3dae3%22%2C%22aio_agent_version%22%3A%221.3.5.328%22%2C%22architecture%22%3A%22x86_64%22%2C%22augeas%22%3A%7B%22version%22%3A%221.4.0%22%7D%2C%22augeasversion%22%3A%221.4.0%22%2C%22bios_release_date%22%3A%2209%2F01%2F2013%22%2C%22bios_vendor%22%3A%22HP%22%2C%22bios_version%22%3A%22P80%22%2C%22blockdevice_sda_model%22%3A%22LOGICAL+VOLUME%22%2C%22blockdevice_sda_size%22%3A1000171331584%2C%22blockdevice_sda_vendor%22%3A%22HP%22%2C%22blockdevice_sdb_model%22%3A%22LOGICAL+VOLUME%22%2C%22blockdevice_sdb_size%22%3A300035497984%2C%22blockdevice_sdb_vendor%22%3A%22HP%22%2C%22blockdevices%22%3A%22sda%2Csdb%22%2C%22chassistype%22%3A%22Rack+Mount+Chassis%22%2C%22custom_auth_conf%22%3Afalse%2C%22dhcp_servers%22%3A%7B%22eth0%22%3A%2210.0.22.10%22%2C%22system%22%3A%2210.0.22.10%22%7D%2C%22disks%22%3A%7B%22sda%22%3A%7B%22model%22%3A%22LOGICAL+VOLUME%22%2C%22size%22%3A%22931.48+GiB%22%2C%22size_bytes%22%3A1000171331584%2C%22vendor%22%3A%22HP%22%7D%2C%22sdb%22%3A%7B%22model%22%3A%22LOGICAL+VOLUME%22%2C%22size%22%3A%22279.43+GiB%22%2C%22size_bytes%22%3A300035497984%2C%22vendor%22%3A%22HP%22%7D%7D%2C%22dmi%22%3A%7B%22bios%22%3A%7B%22release_date%22%3A%2209%2F01%2F2013%22%2C%22vendor%22%3A%22HP%22%2C%22version%22%3A%22P80%22%7D%2C%22chassis%22%3A%7B%22type%22%3A%22Rack+Mount+Chassis%22%7D%2C%22manufacturer%22%3A%22HP%22%2C%22product%22%3A%7B%22name%22%3A%22ProLiant+DL320e+Gen8+v2%22%2C%22serial_number%22%3A%22USE346L3KS%22%2C%22uuid%22%3A%2237323233-3135-5553-4533-34364C334B53%22%7D%7D%2C%22domain%22%3A%22delivery.puppetlabs.net%22%2C%22facterversion%22%3A%223.1.5%22%2C%22filesystems%22%3A%22ext4%2Ciso9660%22%2C%22fqdn%22%3A%22${node}%22%2C%22gid%22%3A%22root%22%2C%22hardwareisa%22%3A%22x86_64%22%2C%22hardwaremodel%22%3A%22x86_64%22%2C%22hostname%22%3A%22perf-bl16%22%2C%22id%22%3A%22root%22%2C%22identity%22%3A%7B%22gid%22%3A0%2C%22group%22%3A%22root%22%2C%22uid%22%3A0%2C%22user%22%3A%22root%22%7D%2C%22interfaces%22%3A%22eth0%2Ceth1%2Clo%22%2C%22ipaddress%22%3A%2210.0.150.34%22%2C%22ipaddress6%22%3A%22fe80%3A%3A8634%3A97ff%3Afe11%3Ad5e4%22%2C%22ipaddress6_eth0%22%3A%22fe80%3A%3A8634%3A97ff%3Afe11%3Ad5e4%22%2C%22ipaddress6_lo%22%3A%22%3A%3A1%22%2C%22ipaddress_eth0%22%3A%2210.0.150.34%22%2C%22ipaddress_lo%22%3A%22127.0.0.1%22%2C%22is_pe%22%3Afalse%2C%22is_virtual%22%3Afalse%2C%22kernel%22%3A%22Linux%22%2C%22kernelmajversion%22%3A%222.6%22%2C%22kernelrelease%22%3A%222.6.32-358.el6.x86_64%22%2C%22kernelversion%22%3A%222.6.32%22%2C%22load_averages%22%3A%7B%2215m%22%3A0.03%2C%221m%22%3A0.41%2C%225m%22%3A0.12%7D%2C%22macaddress%22%3A%2284%3A34%3A97%3A11%3Ad5%3Ae4%22%2C%22macaddress_eth0%22%3A%2284%3A34%3A97%3A11%3Ad5%3Ae4%22%2C%22macaddress_eth1%22%3A%2284%3A34%3A97%3A11%3Ad5%3Ae5%22%2C%22manufacturer%22%3A%22HP%22%2C%22memory%22%3A%7B%22swap%22%3A%7B%22available%22%3A%22477.89+MiB%22%2C%22available_bytes%22%3A501100544%2C%22capacity%22%3A%2252.21%25%22%2C%22total%22%3A%22999.99+MiB%22%2C%22total_bytes%22%3A1048567808%2C%22used%22%3A%22522.11+MiB%22%2C%22used_bytes%22%3A547467264%7D%2C%22system%22%3A%7B%22available%22%3A%222.23+GiB%22%2C%22available_bytes%22%3A2392285184%2C%22capacity%22%3A%2270.38%25%22%2C%22total%22%3A%227.52+GiB%22%2C%22total_bytes%22%3A8077553664%2C%22used%22%3A%225.29+GiB%22%2C%22used_bytes%22%3A5685268480%7D%7D%2C%22memoryfree%22%3A%222.23+GiB%22%2C%22memoryfree_mb%22%3A2281.4609375%2C%22memorysize%22%3A%227.52+GiB%22%2C%22memorysize_mb%22%3A7703.35546875%2C%22mountpoints%22%3A%7B%22%2F%22%3A%7B%22available%22%3A%22253.92+GiB%22%2C%22available_bytes%22%3A272641691648%2C%22capacity%22%3A%227.29%25%22%2C%22device%22%3A%22%2Fdev%2Fsdb3%22%2C%22filesystem%22%3A%22ext4%22%2C%22options%22%3A%5B%22rw%22%5D%2C%22size%22%3A%22273.89+GiB%22%2C%22size_bytes%22%3A294086156288%2C%22used%22%3A%2219.97+GiB%22%2C%22used_bytes%22%3A21444464640%7D%2C%22%2Fboot%22%3A%7B%22available%22%3A%22162.37+MiB%22%2C%22available_bytes%22%3A170261504%2C%22capacity%22%3A%2216.17%25%22%2C%22device%22%3A%22%2Fdev%2Fsdb1%22%2C%22filesystem%22%3A%22ext4%22%2C%22options%22%3A%5B%22rw%22%5D%2C%22size%22%3A%22193.69+MiB%22%2C%22size_bytes%22%3A203097088%2C%22used%22%3A%2231.31+MiB%22%2C%22used_bytes%22%3A32835584%7D%2C%22%2Fdata%22%3A%7B%22available%22%3A%22728.67+GiB%22%2C%22available_bytes%22%3A782399291392%2C%22capacity%22%3A%2220.53%25%22%2C%22device%22%3A%22%2Fdev%2Fsda1%22%2C%22filesystem%22%3A%22ext4%22%2C%22options%22%3A%5B%22rw%22%5D%2C%22size%22%3A%22916.86+GiB%22%2C%22size_bytes%22%3A984475844608%2C%22used%22%3A%22188.20+GiB%22%2C%22used_bytes%22%3A202076553216%7D%7D%2C%22mtu_eth0%22%3A1500%2C%22mtu_eth1%22%3A1500%2C%22mtu_lo%22%3A16436%2C%22netmask%22%3A%22255.255.255.0%22%2C%22netmask6%22%3A%22ffff%3Affff%3Affff%3Affff%3A%3A%22%2C%22netmask6_eth0%22%3A%22ffff%3Affff%3Affff%3Affff%3A%3A%22%2C%22netmask6_lo%22%3A%22ffff%3Affff%3Affff%3Affff%3Affff%3Affff%3Affff%3Affff%22%2C%22netmask_eth0%22%3A%22255.255.255.0%22%2C%22netmask_lo%22%3A%22255.0.0.0%22%2C%22network%22%3A%2210.0.150.0%22%2C%22network6%22%3A%22fe80%3A%3A%22%2C%22network6_eth0%22%3A%22fe80%3A%3A%22%2C%22network6_lo%22%3A%22%3A%3A1%22%2C%22network_eth0%22%3A%2210.0.150.0%22%2C%22network_lo%22%3A%22127.0.0.0%22%2C%22networking%22%3A%7B%22dhcp%22%3A%2210.0.22.10%22%2C%22domain%22%3A%22delivery.puppetlabs.net%22%2C%22fqdn%22%3A%22${node}%22%2C%22hostname%22%3A%22perf-bl16%22%2C%22interfaces%22%3A%7B%22eth0%22%3A%7B%22bindings%22%3A%5B%7B%22address%22%3A%2210.0.150.34%22%2C%22netmask%22%3A%22255.255.255.0%22%2C%22network%22%3A%2210.0.150.0%22%7D%5D%2C%22bindings6%22%3A%5B%7B%22address%22%3A%22fe80%3A%3A8634%3A97ff%3Afe11%3Ad5e4%22%2C%22netmask%22%3A%22ffff%3Affff%3Affff%3Affff%3A%3A%22%2C%22network%22%3A%22fe80%3A%3A%22%7D%5D%2C%22dhcp%22%3A%2210.0.22.10%22%2C%22ip%22%3A%2210.0.150.34%22%2C%22ip6%22%3A%22fe80%3A%3A8634%3A97ff%3Afe11%3Ad5e4%22%2C%22mac%22%3A%2284%3A34%3A97%3A11%3Ad5%3Ae4%22%2C%22mtu%22%3A1500%2C%22netmask%22%3A%22255.255.255.0%22%2C%22netmask6%22%3A%22ffff%3Affff%3Affff%3Affff%3A%3A%22%2C%22network%22%3A%2210.0.150.0%22%2C%22network6%22%3A%22fe80%3A%3A%22%7D%2C%22eth1%22%3A%7B%22mac%22%3A%2284%3A34%3A97%3A11%3Ad5%3Ae5%22%2C%22mtu%22%3A1500%7D%2C%22lo%22%3A%7B%22bindings%22%3A%5B%7B%22address%22%3A%22127.0.0.1%22%2C%22netmask%22%3A%22255.0.0.0%22%2C%22network%22%3A%22127.0.0.0%22%7D%5D%2C%22bindings6%22%3A%5B%7B%22address%22%3A%22%3A%3A1%22%2C%22netmask%22%3A%22ffff%3Affff%3Affff%3Affff%3Affff%3Affff%3Affff%3Affff%22%2C%22network%22%3A%22%3A%3A1%22%7D%5D%2C%22ip%22%3A%22127.0.0.1%22%2C%22ip6%22%3A%22%3A%3A1%22%2C%22mtu%22%3A16436%2C%22netmask%22%3A%22255.0.0.0%22%2C%22netmask6%22%3A%22ffff%3Affff%3Affff%3Affff%3Affff%3Affff%3Affff%3Affff%22%2C%22network%22%3A%22127.0.0.0%22%2C%22network6%22%3A%22%3A%3A1%22%7D%7D%2C%22ip%22%3A%2210.0.150.34%22%2C%22ip6%22%3A%22fe80%3A%3A8634%3A97ff%3Afe11%3Ad5e4%22%2C%22mac%22%3A%2284%3A34%3A97%3A11%3Ad5%3Ae4%22%2C%22mtu%22%3A1500%2C%22netmask%22%3A%22255.255.255.0%22%2C%22netmask6%22%3A%22ffff%3Affff%3Affff%3Affff%3A%3A%22%2C%22network%22%3A%2210.0.150.0%22%2C%22network6%22%3A%22fe80%3A%3A%22%2C%22primary%22%3A%22eth0%22%7D%2C%22operatingsystem%22%3A%22CentOS%22%2C%22operatingsystemmajrelease%22%3A%226%22%2C%22operatingsystemrelease%22%3A%226.4%22%2C%22os%22%3A%7B%22architecture%22%3A%22x86_64%22%2C%22family%22%3A%22RedHat%22%2C%22hardware%22%3A%22x86_64%22%2C%22name%22%3A%22CentOS%22%2C%22release%22%3A%7B%22full%22%3A%226.4%22%2C%22major%22%3A%226%22%2C%22minor%22%3A%224%22%7D%2C%22selinux%22%3A%7B%22enabled%22%3Afalse%7D%7D%2C%22osfamily%22%3A%22RedHat%22%2C%22partitions%22%3A%7B%22%2Fdev%2Fsda1%22%3A%7B%22filesystem%22%3A%22ext4%22%2C%22mount%22%3A%22%2Fdata%22%2C%22size%22%3A%22931.48+GiB%22%2C%22size_bytes%22%3A1000169537536%2C%22uuid%22%3A%2207113772-e65f-418a-9a48-1700b2bd0cf2%22%7D%2C%22%2Fdev%2Fsdb1%22%3A%7B%22filesystem%22%3A%22ext4%22%2C%22mount%22%3A%22%2Fboot%22%2C%22size%22%3A%22200.00+MiB%22%2C%22size_bytes%22%3A209715200%2C%22uuid%22%3A%22abed2320-91c7-4218-b4f7-c773adec9e68%22%7D%2C%22%2Fdev%2Fsdb2%22%3A%7B%22filesystem%22%3A%22swap%22%2C%22size%22%3A%221000.00+MiB%22%2C%22size_bytes%22%3A1048576000%2C%22uuid%22%3A%2222789f92-ebf7-4161-b20e-b348c57a9db7%22%7D%2C%22%2Fdev%2Fsdb3%22%3A%7B%22filesystem%22%3A%22ext4%22%2C%22mount%22%3A%22%2F%22%2C%22size%22%3A%22278.26+GiB%22%2C%22size_bytes%22%3A298776002560%2C%22uuid%22%3A%22b386e330-ca45-4239-ba1d-25141842c08a%22%7D%7D%2C%22path%22%3A%22%2Fusr%2Flocal%2Fsbin%3A%2Fusr%2Flocal%2Fbin%3A%2Fsbin%3A%2Fbin%3A%2Fusr%2Fsbin%3A%2Fusr%2Fbin%3A%2Fopt%2Fpuppetlabs%2Fbin%3A%2Froot%2Fbin%22%2C%22pe_build%22%3A%222016.1.0-rc2-420-gb432a65%22%2C%22pe_concat_basedir%22%3A%22%2Fopt%2Fpuppetlabs%2Fpuppet%2Fcache%2Fpe_concat%22%2C%22pe_razor_server_version%22%3A%22package+pe-razor-server+is+not+installed%22%2C%22pe_server_version%22%3A%222016.1.0%22%2C%22physicalprocessorcount%22%3A1%2C%22platform_symlink_writable%22%3Atrue%2C%22platform_tag%22%3A%22el-6-x86_64%22%2C%22processor0%22%3A%22Intel%28R%29+Xeon%28R%29+CPU+E3-1280+v3+%40+3.60GHz%22%2C%22processor1%22%3A%22Intel%28R%29+Xeon%28R%29+CPU+E3-1280+v3+%40+3.60GHz%22%2C%22processor2%22%3A%22Intel%28R%29+Xeon%28R%29+CPU+E3-1280+v3+%40+3.60GHz%22%2C%22processor3%22%3A%22Intel%28R%29+Xeon%28R%29+CPU+E3-1280+v3+%40+3.60GHz%22%2C%22processor4%22%3A%22Intel%28R%29+Xeon%28R%29+CPU+E3-1280+v3+%40+3.60GHz%22%2C%22processor5%22%3A%22Intel%28R%29+Xeon%28R%29+CPU+E3-1280+v3+%40+3.60GHz%22%2C%22processor6%22%3A%22Intel%28R%29+Xeon%28R%29+CPU+E3-1280+v3+%40+3.60GHz%22%2C%22processor7%22%3A%22Intel%28R%29+Xeon%28R%29+CPU+E3-1280+v3+%40+3.60GHz%22%2C%22processorcount%22%3A8%2C%22processors%22%3A%7B%22count%22%3A8%2C%22isa%22%3A%22x86_64%22%2C%22models%22%3A%5B%22Intel%28R%29+Xeon%28R%29+CPU+E3-1280+v3+%40+3.60GHz%22%2C%22Intel%28R%29+Xeon%28R%29+CPU+E3-1280+v3+%40+3.60GHz%22%2C%22Intel%28R%29+Xeon%28R%29+CPU+E3-1280+v3+%40+3.60GHz%22%2C%22Intel%28R%29+Xeon%28R%29+CPU+E3-1280+v3+%40+3.60GHz%22%2C%22Intel%28R%29+Xeon%28R%29+CPU+E3-1280+v3+%40+3.60GHz%22%2C%22Intel%28R%29+Xeon%28R%29+CPU+E3-1280+v3+%40+3.60GHz%22%2C%22Intel%28R%29+Xeon%28R%29+CPU+E3-1280+v3+%40+3.60GHz%22%2C%22Intel%28R%29+Xeon%28R%29+CPU+E3-1280+v3+%40+3.60GHz%22%5D%2C%22physicalcount%22%3A1%2C%22speed%22%3A%223.60+GHz%22%7D%2C%22productname%22%3A%22ProLiant+DL320e+Gen8+v2%22%2C%22puppet_files_dir_present%22%3Afalse%2C%22puppetversion%22%3A%224.4.0%22%2C%22ruby%22%3A%7B%22platform%22%3A%22x86_64-linux%22%2C%22sitedir%22%3A%22%2Fopt%2Fpuppetlabs%2Fpuppet%2Flib%2Fruby%2Fsite_ruby%2F2.1.0%22%2C%22version%22%3A%222.1.8%22%7D%2C%22rubyplatform%22%3A%22x86_64-linux%22%2C%22rubysitedir%22%3A%22%2Fopt%2Fpuppetlabs%2Fpuppet%2Flib%2Fruby%2Fsite_ruby%2F2.1.0%22%2C%22rubyversion%22%3A%222.1.8%22%2C%22selinux%22%3Afalse%2C%22serialnumber%22%3A%22USE346L3KS%22%2C%22ssh%22%3A%7B%22dsa%22%3A%7B%22fingerprints%22%3A%7B%22sha1%22%3A%22SSHFP+2+1+ab3be65a9564b95605b5fd63863b751f6b8d20bb%22%2C%22sha256%22%3A%22SSHFP+2+2+8616274a8fc4433885585379e70b5e9f9a99d8454fc9e4a07e3cd5e28ff9d1cb%22%7D%2C%22key%22%3A%22AAAAB3NzaC1kc3MAAACBAMg3gtp0uTqtsMcQ7ZcknxRE3%2FiRUjxtsWu6pidgl1W619INqGZH8LhSqxl165VhXryyOIa%2FQfm6g3MEk1pbifn9SgOkX3p3PU%2BV1ow%2FK%2FxKnSx8eGZAlfVTRIOirbWuMiVLLkeKVEsF5wYxgmBdOqIFdZ13Qv0axQDeGV%2BTG0aDAAAAFQC3%2Blz1DbBp2psAsk44tVQuYoBKywAAAIEAkXotLZaAE8yorIgUNb%2Bhk2aEb83aVHo8QJ1F8GoGGNPD5G7i%2Bph%2FCyEBDVwLjqreGbNIEs9DT66OEEptSA01EoJ5RI3jgsbt23aH31DLdCLqP0pAXtdbIHDySNyopfjEn%2F19EYhUMEsIEJ5Q4KaBynFHR1F7H%2FDOSfGBqgGRoykAAACAF08pIrzOV7CLdjliA3kEv3Flkkxyxjc1g9ACX8BNBSj%2Fxmpi4Dt8WJ60aMzqO8EyH5Da6uN%2BQypJNqp2u34CGPtqUatdZq5%2F3IH1qSD7w4xRj2z1atCU4VDb6OIPEPEPPHj4d1oy4BFBrcIyhmlYxjcBdKscCYVUqbLwDwqYCy4%3D%22%7D%2C%22rsa%22%3A%7B%22fingerprints%22%3A%7B%22sha1%22%3A%22SSHFP+1+1+d9b33fe36073290d28ef83002715edd8234dd084%22%2C%22sha256%22%3A%22SSHFP+1+2+ccc5bbb652332f783867624bddc447c4f5f1803db2c3fab2430ef3430c479c05%22%7D%2C%22key%22%3A%22AAAAB3NzaC1yc2EAAAABIwAAAQEA2OJzX%2F3Y%2FL3rbamiatm%2Fn3olzn6l%2FGsFywupgQwf4a3Oi%2BLT6XG2IOM%2BQcO3dDGM6Cud4cJsYYCoJGr9eQAa0%2FRDwE%2FQ6yosuXEYFgdocwiGSawY2ibBFC25m8hz%2BuTze2OCI6noam9ww7t%2FIxNyC6i1%2B9B6JbbFfBVf329i6tBdM7staIKld9fguAjSoLT4KvE5b9PEb6s2usdMAwA3S36K%2Betoge5KnYXk1SpZWBIEUD8vhF5zFOdZPn8KWo1uRWeCiTQgRinA0EAImgRy27Cexi3ICTQwI42IyGx9QrWR5itkqBFQN28RgviUsy%2Fsr7ftDJ8xo8Yb716cod8UIQ%3D%3D%22%7D%7D%2C%22sshdsakey%22%3A%22AAAAB3NzaC1kc3MAAACBAMg3gtp0uTqtsMcQ7ZcknxRE3%2FiRUjxtsWu6pidgl1W619INqGZH8LhSqxl165VhXryyOIa%2FQfm6g3MEk1pbifn9SgOkX3p3PU%2BV1ow%2FK%2FxKnSx8eGZAlfVTRIOirbWuMiVLLkeKVEsF5wYxgmBdOqIFdZ13Qv0axQDeGV%2BTG0aDAAAAFQC3%2Blz1DbBp2psAsk44tVQuYoBKywAAAIEAkXotLZaAE8yorIgUNb%2Bhk2aEb83aVHo8QJ1F8GoGGNPD5G7i%2Bph%2FCyEBDVwLjqreGbNIEs9DT66OEEptSA01EoJ5RI3jgsbt23aH31DLdCLqP0pAXtdbIHDySNyopfjEn%2F19EYhUMEsIEJ5Q4KaBynFHR1F7H%2FDOSfGBqgGRoykAAACAF08pIrzOV7CLdjliA3kEv3Flkkxyxjc1g9ACX8BNBSj%2Fxmpi4Dt8WJ60aMzqO8EyH5Da6uN%2BQypJNqp2u34CGPtqUatdZq5%2F3IH1qSD7w4xRj2z1atCU4VDb6OIPEPEPPHj4d1oy4BFBrcIyhmlYxjcBdKscCYVUqbLwDwqYCy4%3D%22%2C%22sshfp_dsa%22%3A%22SSHFP+2+1+ab3be65a9564b95605b5fd63863b751f6b8d20bb%5CnSSHFP+2+2+8616274a8fc4433885585379e70b5e9f9a99d8454fc9e4a07e3cd5e28ff9d1cb%22%2C%22sshfp_rsa%22%3A%22SSHFP+1+1+d9b33fe36073290d28ef83002715edd8234dd084%5CnSSHFP+1+2+ccc5bbb652332f783867624bddc447c4f5f1803db2c3fab2430ef3430c479c05%22%2C%22sshrsakey%22%3A%22AAAAB3NzaC1yc2EAAAABIwAAAQEA2OJzX%2F3Y%2FL3rbamiatm%2Fn3olzn6l%2FGsFywupgQwf4a3Oi%2BLT6XG2IOM%2BQcO3dDGM6Cud4cJsYYCoJGr9eQAa0%2FRDwE%2FQ6yosuXEYFgdocwiGSawY2ibBFC25m8hz%2BuTze2OCI6noam9ww7t%2FIxNyC6i1%2B9B6JbbFfBVf329i6tBdM7staIKld9fguAjSoLT4KvE5b9PEb6s2usdMAwA3S36K%2Betoge5KnYXk1SpZWBIEUD8vhF5zFOdZPn8KWo1uRWeCiTQgRinA0EAImgRy27Cexi3ICTQwI42IyGx9QrWR5itkqBFQN28RgviUsy%2Fsr7ftDJ8xo8Yb716cod8UIQ%3D%3D%22%2C%22staging_http_get%22%3A%22curl%22%2C%22swapfree%22%3A%22477.89+MiB%22%2C%22swapfree_mb%22%3A477.88671875%2C%22swapsize%22%3A%22999.99+MiB%22%2C%22swapsize_mb%22%3A999.9921875%2C%22system_uptime%22%3A%7B%22days%22%3A4%2C%22hours%22%3A110%2C%22seconds%22%3A396281%2C%22uptime%22%3A%224+days%22%7D%2C%22timezone%22%3A%22PDT%22%2C%22uptime%22%3A%224+days%22%2C%22uptime_days%22%3A4%2C%22uptime_hours%22%3A110%2C%22uptime_seconds%22%3A396281%2C%22uuid%22%3A%2237323233-3135-5553-4533-34364C334B53%22%2C%22virtual%22%3A%22physical%22%2C%22clientcert%22%3A%22${node}%22%2C%22clientversion%22%3A%224.4.0%22%2C%22clientnoop%22%3Afalse%7D%2C%22timestamp%22%3A%222016-03-17T11%3A31%3A33.536465735-07%3A00%22%2C%22expiration%22%3A%222025-03-17T12%3A01%3A33.536673211-07%3A00%22%7D")
			.formParam("transaction_uuid", "4a446752-7acf-46e8-bc8b-9c3d6e0bada9")
			.formParam("static_catalog", "true")
			.formParam("checksum_type", "md5.sha256")
			.formParam("fail_on_404", "true"))
		.pause(17)
		.exec(http("filemeta")
			.get("/puppet/v3/file_metadata/modules/pe_repo/GPG-KEY-puppetlabs?environment=production&links=manage&checksum_type=md5&source_permissions=ignore")
			.headers(headers_0))
		.pause(2)
		.exec(http("filemeta mco plugins")
			.get("/puppet/v3/file_metadatas/modules/puppet_enterprise/mcollective/plugins?environment=production&links=manage&recurse=true&source_permissions=ignore&checksum_type=md5")
			.headers(headers_0))
		.pause(263 milliseconds)
		.exec(http("static file content")
			.get("/puppet/v3/static_file_content/modules/recursive_with_large_files/files/dir_with_large_files/faunajaponica1.pdf?environment=production&code_id=urn%3Apuppet%3Acode-id%3A1%3A8fe7987207b279319fbb01428318c39bb146c160%3Bproduction"))
		.pause(7)
		.exec(http("static file content")
			.get("/puppet/v3/static_file_content/modules/recursive_with_large_files/files/dir_with_large_files/faunajaponica10.pdf?environment=production&code_id=urn%3Apuppet%3Acode-id%3A1%3A8fe7987207b279319fbb01428318c39bb146c160%3Bproduction"))
		.pause(7)
		.exec(http("static file content")
			.get("/puppet/v3/static_file_content/modules/recursive_with_large_files/files/dir_with_large_files/faunajaponica11.pdf?environment=production&code_id=urn%3Apuppet%3Acode-id%3A1%3A8fe7987207b279319fbb01428318c39bb146c160%3Bproduction"))
		.pause(7)
		.exec(http("static file content")
			.get("/puppet/v3/static_file_content/modules/recursive_with_large_files/files/dir_with_large_files/faunajaponica12.pdf?environment=production&code_id=urn%3Apuppet%3Acode-id%3A1%3A8fe7987207b279319fbb01428318c39bb146c160%3Bproduction"))
		.pause(7)
		.exec(http("static file content")
			.get("/puppet/v3/static_file_content/modules/recursive_with_large_files/files/dir_with_large_files/faunajaponica13.pdf?environment=production&code_id=urn%3Apuppet%3Acode-id%3A1%3A8fe7987207b279319fbb01428318c39bb146c160%3Bproduction"))
		.pause(7)
		.exec(http("static file content")
			.get("/puppet/v3/static_file_content/modules/recursive_with_large_files/files/dir_with_large_files/faunajaponica14.pdf?environment=production&code_id=urn%3Apuppet%3Acode-id%3A1%3A8fe7987207b279319fbb01428318c39bb146c160%3Bproduction"))
		.pause(7)
		.exec(http("static file content")
			.get("/puppet/v3/static_file_content/modules/recursive_with_large_files/files/dir_with_large_files/faunajaponica15.pdf?environment=production&code_id=urn%3Apuppet%3Acode-id%3A1%3A8fe7987207b279319fbb01428318c39bb146c160%3Bproduction"))
		.pause(7)
		.exec(http("static file content")
			.get("/puppet/v3/static_file_content/modules/recursive_with_large_files/files/dir_with_large_files/faunajaponica16.pdf?environment=production&code_id=urn%3Apuppet%3Acode-id%3A1%3A8fe7987207b279319fbb01428318c39bb146c160%3Bproduction"))
		.pause(7)
		.exec(http("static file content")
			.get("/puppet/v3/static_file_content/modules/recursive_with_large_files/files/dir_with_large_files/faunajaponica17.pdf?environment=production&code_id=urn%3Apuppet%3Acode-id%3A1%3A8fe7987207b279319fbb01428318c39bb146c160%3Bproduction"))
		.pause(7)
		.exec(http("static file content")
			.get("/puppet/v3/static_file_content/modules/recursive_with_large_files/files/dir_with_large_files/faunajaponica18.pdf?environment=production&code_id=urn%3Apuppet%3Acode-id%3A1%3A8fe7987207b279319fbb01428318c39bb146c160%3Bproduction"))
		.pause(7)
		.exec(http("static file content")
			.get("/puppet/v3/static_file_content/modules/recursive_with_large_files/files/dir_with_large_files/faunajaponica19.pdf?environment=production&code_id=urn%3Apuppet%3Acode-id%3A1%3A8fe7987207b279319fbb01428318c39bb146c160%3Bproduction"))
		.pause(7)
		.exec(http("static file content")
			.get("/puppet/v3/static_file_content/modules/recursive_with_large_files/files/dir_with_large_files/faunajaponica2.pdf?environment=production&code_id=urn%3Apuppet%3Acode-id%3A1%3A8fe7987207b279319fbb01428318c39bb146c160%3Bproduction"))
		.pause(7)
		.exec(http("static file content")
			.get("/puppet/v3/static_file_content/modules/recursive_with_large_files/files/dir_with_large_files/faunajaponica20.pdf?environment=production&code_id=urn%3Apuppet%3Acode-id%3A1%3A8fe7987207b279319fbb01428318c39bb146c160%3Bproduction"))
		.pause(7)
		.exec(http("static file content")
			.get("/puppet/v3/static_file_content/modules/recursive_with_large_files/files/dir_with_large_files/faunajaponica21.pdf?environment=production&code_id=urn%3Apuppet%3Acode-id%3A1%3A8fe7987207b279319fbb01428318c39bb146c160%3Bproduction"))
		.pause(7)
		.exec(http("static file content")
			.get("/puppet/v3/static_file_content/modules/recursive_with_large_files/files/dir_with_large_files/faunajaponica22.pdf?environment=production&code_id=urn%3Apuppet%3Acode-id%3A1%3A8fe7987207b279319fbb01428318c39bb146c160%3Bproduction"))
		.pause(7)
		.exec(http("static file content")
			.get("/puppet/v3/static_file_content/modules/recursive_with_large_files/files/dir_with_large_files/faunajaponica23.pdf?environment=production&code_id=urn%3Apuppet%3Acode-id%3A1%3A8fe7987207b279319fbb01428318c39bb146c160%3Bproduction"))
		.pause(7)
		.exec(http("static file content")
			.get("/puppet/v3/static_file_content/modules/recursive_with_large_files/files/dir_with_large_files/faunajaponica24.pdf?environment=production&code_id=urn%3Apuppet%3Acode-id%3A1%3A8fe7987207b279319fbb01428318c39bb146c160%3Bproduction"))
		.pause(7)
		.exec(http("static file content")
			.get("/puppet/v3/static_file_content/modules/recursive_with_large_files/files/dir_with_large_files/faunajaponica25.pdf?environment=production&code_id=urn%3Apuppet%3Acode-id%3A1%3A8fe7987207b279319fbb01428318c39bb146c160%3Bproduction"))
		.pause(7)
		.exec(http("static file content")
			.get("/puppet/v3/static_file_content/modules/recursive_with_large_files/files/dir_with_large_files/faunajaponica3.pdf?environment=production&code_id=urn%3Apuppet%3Acode-id%3A1%3A8fe7987207b279319fbb01428318c39bb146c160%3Bproduction"))
		.pause(7)
		.exec(http("static file content")
			.get("/puppet/v3/static_file_content/modules/recursive_with_large_files/files/dir_with_large_files/faunajaponica4.pdf?environment=production&code_id=urn%3Apuppet%3Acode-id%3A1%3A8fe7987207b279319fbb01428318c39bb146c160%3Bproduction"))
		.pause(7)
		.exec(http("static file content")
			.get("/puppet/v3/static_file_content/modules/recursive_with_large_files/files/dir_with_large_files/faunajaponica5.pdf?environment=production&code_id=urn%3Apuppet%3Acode-id%3A1%3A8fe7987207b279319fbb01428318c39bb146c160%3Bproduction"))
		.pause(7)
		.exec(http("static file content")
			.get("/puppet/v3/static_file_content/modules/recursive_with_large_files/files/dir_with_large_files/faunajaponica6.pdf?environment=production&code_id=urn%3Apuppet%3Acode-id%3A1%3A8fe7987207b279319fbb01428318c39bb146c160%3Bproduction"))
		.pause(7)
		.exec(http("static file content")
			.get("/puppet/v3/static_file_content/modules/recursive_with_large_files/files/dir_with_large_files/faunajaponica7.pdf?environment=production&code_id=urn%3Apuppet%3Acode-id%3A1%3A8fe7987207b279319fbb01428318c39bb146c160%3Bproduction"))
		.pause(8)
		.exec(http("static file content")
			.get("/puppet/v3/static_file_content/modules/recursive_with_large_files/files/dir_with_large_files/faunajaponica8.pdf?environment=production&code_id=urn%3Apuppet%3Acode-id%3A1%3A8fe7987207b279319fbb01428318c39bb146c160%3Bproduction"))
		.pause(7)
		.exec(http("static file content")
			.get("/puppet/v3/static_file_content/modules/recursive_with_large_files/files/dir_with_large_files/faunajaponica9.pdf?environment=production&code_id=urn%3Apuppet%3Acode-id%3A1%3A8fe7987207b279319fbb01428318c39bb146c160%3Bproduction"))
		.pause(8)
		.exec(http("filemeta")
			.get("/puppet/v3/file_metadata/modules/pe_concat/concatfragments.sh?environment=production&links=manage&checksum_type=md5&source_permissions=ignore")
			.headers(headers_0))
		.pause(599 milliseconds)
		.exec(http("filemeta")
			.get("/puppet/v3/file_metadata/modules/puppet_enterprise/console/dhparam_puppetproxy.pem?environment=production&links=manage&checksum_type=md5&source_permissions=ignore")
			.headers(headers_0))
		.pause(543 milliseconds)
		.exec(http("filemeta")
			.get("/puppet/v3/file_metadata/modules/pe_accounts/shell/bashrc?environment=production&links=manage&checksum_type=md5&source_permissions=ignore")
			.headers(headers_0))
		.exec(http("filemeta")
			.get("/puppet/v3/file_metadata/modules/pe_accounts/shell/bash_profile?environment=production&links=manage&checksum_type=md5&source_permissions=ignore")
			.headers(headers_0))
		.pause(369 milliseconds)
		.exec((session:Session) => {
			session.set("reportTimestamp",
				LocalDateTime.now.toString(ISODateTimeFormat.dateTime()))
		})
		.exec((session:Session) => {
			session.set("transactionUuid",
				UUID.randomUUID().toString())
		})
		.exec(http("report")
			.put("/puppet/v3/report/${node}?environment=production&")
			.headers(headers_35)
			.body(reportBody))
// setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
}