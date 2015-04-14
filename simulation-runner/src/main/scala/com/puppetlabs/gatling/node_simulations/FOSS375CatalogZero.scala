package com.puppetlabs.gatling.node_simulations

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

import com.puppetlabs.gatling.runner.SimulationWithScenario
import org.joda.time.LocalDateTime
import org.joda.time.format.ISODateTimeFormat

class FOSS375CatalogZero extends SimulationWithScenario {

	val httpProtocol = http
		.baseURL("https://puppets-macbook-pro-3.local:8140")
		.acceptHeader("pson, b64_zlib_yaml, yaml, raw")
		.acceptEncodingHeader("identity")
		.contentTypeHeader("application/x-www-form-urlencoded")

	val headers_3 = Map("Accept" -> "pson, b64_zlib_yaml, yaml, dot, raw")

	val headers_108 = Map(
		"Accept" -> "pson, yaml",
		"Content-Type" -> "text/pson")

    val uri1 = "https://puppets-macbook-pro-3.local:8140/production"

	val chain_0 = exec(http("request_0")
			.get("/production/node/centos65.localdomain?transaction_uuid=9c313c25-ef8c-4f53-b1dd-dfa119fe4197&fail_on_404=true"))
		.exec(http("request_1")
			.get("/production/file_metadatas/pluginfacts?links=manage&ignore=.svn&ignore=CVS&ignore=.git&recurse=true&checksum_type=md5"))
		.exec(http("request_2")
			.get("/production/file_metadatas/plugins?links=manage&ignore=.svn&ignore=CVS&ignore=.git&recurse=true&checksum_type=md5"))
		.pause(327 milliseconds)
		.exec(http("request_3")
			.post("/production/catalog/centos65.localdomain")
			.headers(headers_3)
			.formParam("facts_format", "pson")
			.formParam("transaction_uuid", "9c313c25-ef8c-4f53-b1dd-dfa119fe4197")
			.formParam("facts", "%7B%22expiration%22%3A%222015-04-14T03%3A58%3A39.789080000-07%3A00%22%2C%22values%22%3A%7B%22operatingsystemmajrelease%22%3A%226%22%2C%22ipaddress_eth0%22%3A%22172.16.105.128%22%2C%22blockdevices%22%3A%22sda%2Csr0%22%2C%22blockdevice_sr0_vendor%22%3A%22NECVMWar%22%2C%22bios_version%22%3A%226.00%22%2C%22swapfree%22%3A%221.94+GB%22%2C%22boardmanufacturer%22%3A%22Intel+Corporation%22%2C%22processor0%22%3A%22Intel%28R%29+Core%28TM%29+i7-3740QM+CPU+%40+2.70GHz%22%2C%22kernelrelease%22%3A%222.6.32-431.el6.x86_64%22%2C%22type%22%3A%22Other%22%2C%22domain%22%3A%22localdomain%22%2C%22mtu_eth0%22%3A%221500%22%2C%22sshdsakey%22%3A%22AAAAB3NzaC1kc3MAAACBAOZ%2FzZDPHeu8P2XS4Jr77lSC9lX4x9ut7ByFNXZsp6fHq55YfyF0Q0mmhH2diKsyIaVLGwqpQmZs%2B%2FRW8rgbRsO3vSdqlHg1iFLWmaEpeNWA9oKXVj1G2OjY0%2BHIwFArR%2Ft4jxgG0Vjn2P1GdbbWjTnTuQqqsHkbGp%2BGSjKi8q5zAAAAFQDP2WtNziegjLQzIKffkVwEEO9BBQAAAIAli3RP1X588FvbwM%2FNYe3I5L%2FoRrYTlF1XLRFV0K4t5HSGVMir%2B2zTY12ukaz0zBsdjkIcmpJjGK3m1FiRfbuUjso7bsaRgqOY0sBvni1OXzEtDOSxWY4XuiYOKtgj%2FIZdfsZ0%2FZ0mZt6BjTZN1SdxIHwFwKXUdT%2BYS0UTlt5EygAAAIB1yvPv8k1UkavkKXqpgvNcmxW%2Fi%2F41KzwtLo7ArktqQeRwkifR%2F0%2BsbmL%2FvgCbKxQ2cVpeoHbamyJ%2FPkS0KOMDpkiV1G9RSng7DnFF9xBz7h9xaVvrFrR9ddP%2Ff8k3eNg8WUNjn%2Bno63UK3Ka3nAujEYzoBxnFLeXejoPgkWfBuQ%3D%3D%22%2C%22uptime_seconds%22%3A%224256%22%2C%22bios_vendor%22%3A%22Phoenix+Technologies+LTD%22%2C%22physicalprocessorcount%22%3A%221%22%2C%22blockdevice_sda_model%22%3A%22VMware+Virtual+S%22%2C%22blockdevice_sr0_model%22%3A%22VMware+IDE+CDR10%22%2C%22hardwaremodel%22%3A%22x86_64%22%2C%22sshrsakey%22%3A%22AAAAB3NzaC1yc2EAAAABIwAAAQEAo77EHHpZ5bd6nsXYJYxmakRKv%2BfFUr4Ld6eakhFGbkw2KwO7XmYbOS6qqW2F%2F2rqrcBKmtDbnKIysqcp%2Fv01qvmfAX9S3Hi0AgSYAzfuZ6lEkWh%2FXeKuF0IzeolT8WTn8iTnR%2BCPMy46twx5C%2Bioux%2FrNrsReKN4EfI1wC3GEG01Bt4WtbRCzjVFvNTz%2BMiRoi4iMGoY4WdRLR2x3UU9kTAKpEvm23Lb%2F5p9g0N9v3LtevlVIwTSdyIHXbLjCNfuT4HuRUxTQdLPMa9kFwOtWhhbFY6VH5wbnvd0GOK%2FcrBUaeKf9DnQQu41b5B3rdHIL%2BIoC6AybsUl6ppIa%2FlNWw%3D%3D%22%2C%22netmask%22%3A%22255.255.255.0%22%2C%22architecture%22%3A%22x86_64%22%2C%22swapsize%22%3A%221.94+GB%22%2C%22boardserialnumber%22%3A%22None%22%2C%22path%22%3A%22%2Fusr%2Flocal%2Fsbin%3A%2Fusr%2Flocal%2Fbin%3A%2Fsbin%3A%2Fbin%3A%2Fusr%2Fsbin%3A%2Fusr%2Fbin%3A%2Froot%2Fbin%22%2C%22os%22%3A%22familyRedHatnameCentOSreleasefull6.5minor5major6%22%2C%22productname%22%3A%22VMware+Virtual+Platform%22%2C%22sshfp_rsa%22%3A%22SSHFP+1+1+014dbe1a811f483eadb0fdb2283b1ee22ade63d7%5CnSSHFP+1+2+12244974ff66aa9b6894883f3bffc47d670a694f9247c5090e0e408e7280b2c0%22%2C%22ps%22%3A%22ps+-ef%22%2C%22ipaddress%22%3A%22172.16.105.128%22%2C%22blockdevice_sr0_size%22%3A%221073741312%22%2C%22selinux%22%3A%22false%22%2C%22uniqueid%22%3A%2210ac8069%22%2C%22blockdevice_sda_vendor%22%3A%22VMware%2C%22%2C%22id%22%3A%22root%22%2C%22memoryfree_mb%22%3A%22870.29%22%2C%22processors%22%3A%22modelsIntel%28R%29+Core%28TM%29+i7-3740QM+CPU+%40+2.70GHzcount1physicalcount1%22%2C%22uptime%22%3A%221%3A10+hours%22%2C%22memoryfree%22%3A%22870.29+MB%22%2C%22uptime_days%22%3A%220%22%2C%22clientcert%22%3A%22centos65.localdomain%22%2C%22rubyplatform%22%3A%22x86_64-linux%22%2C%22operatingsystem%22%3A%22CentOS%22%2C%22macaddress_eth0%22%3A%2200%3A0C%3A29%3ACB%3AEC%3AED%22%2C%22virtual%22%3A%22vmware%22%2C%22augeasversion%22%3A%221.0.0%22%2C%22manufacturer%22%3A%22VMware%2C+Inc.%22%2C%22uuid%22%3A%22564D939C-9536-85C9-E8FA-53272CCBECED%22%2C%22osfamily%22%3A%22RedHat%22%2C%22memorysize%22%3A%22988.62+MB%22%2C%22clientnoop%22%3A%22false%22%2C%22ipaddress_lo%22%3A%22127.0.0.1%22%2C%22operatingsystemrelease%22%3A%226.5%22%2C%22memorysize_mb%22%3A%22988.62%22%2C%22boardproductname%22%3A%22440BX+Desktop+Reference+Platform%22%2C%22network_eth0%22%3A%22172.16.105.0%22%2C%22system_uptime%22%3A%22seconds4256days0hours1uptime1%3A10+hours%22%2C%22blockdevice_sda_size%22%3A%2221474836480%22%2C%22bios_release_date%22%3A%2207%2F31%2F2013%22%2C%22filesystems%22%3A%22ext4%2Ciso9660%22%2C%22swapsize_mb%22%3A%221983.99%22%2C%22processorcount%22%3A%221%22%2C%22facterversion%22%3A%222.4.3%22%2C%22mtu_lo%22%3A%2216436%22%2C%22hardwareisa%22%3A%22x86_64%22%2C%22rubysitedir%22%3A%22%2Fusr%2Flib%2Fruby%2Fsite_ruby%2F1.8%22%2C%22timezone%22%3A%22PDT%22%2C%22partitions%22%3A%22sda1mount%2Fbootfilesystemext4uuid984f9910-a016-4944-9d07-0aa193dd8aaesize1024000sda2filesystemLVM2_membersize40916992%22%2C%22fqdn%22%3A%22centos65.localdomain%22%2C%22swapfree_mb%22%3A%221983.99%22%2C%22netmask_eth0%22%3A%22255.255.255.0%22%2C%22puppetversion%22%3A%223.7.5%22%2C%22kernelmajversion%22%3A%222.6%22%2C%22hostname%22%3A%22centos65%22%2C%22is_virtual%22%3A%22true%22%2C%22rubyversion%22%3A%221.8.7%22%2C%22kernel%22%3A%22Linux%22%2C%22sshfp_dsa%22%3A%22SSHFP+2+1+4823e20a40a1a2dda9d1014bcfd578a2958dbf96%5CnSSHFP+2+2+91a45d52997f4885e81971c0b5ad499c5dad57bab187d6c99138dd8a813be8d4%22%2C%22kernelversion%22%3A%222.6.32%22%2C%22interfaces%22%3A%22eth0%2Clo%22%2C%22network_lo%22%3A%22127.0.0.0%22%2C%22clientversion%22%3A%223.7.5%22%2C%22gid%22%3A%22root%22%2C%22macaddress%22%3A%2200%3A0C%3A29%3ACB%3AEC%3AED%22%2C%22uptime_hours%22%3A%221%22%2C%22netmask_lo%22%3A%22255.0.0.0%22%2C%22serialnumber%22%3A%22VMware-56+4d+93+9c+95+36+85+c9-e8+fa+53+27+2c+cb+ec+ed%22%7D%2C%22name%22%3A%22centos65.localdomain%22%2C%22timestamp%22%3A%22Tue+Apr+14+03%3A28%3A39+-0700+2015%22%7D")
			.formParam("fail_on_404", "true"))
		.pause(2)
		.exec(http("request_4")
			.get("/production/file_metadata/modules/catalog-zero7/catalog-zero7-impl82.txt?links=manage&source_permissions=use"))
		.exec(http("request_5")
			.get("/production/file_metadata/modules/catalog-zero7/catalog-zero7-impl87.txt?links=manage&source_permissions=use"))
		.exec(http("request_6")
			.get("/production/file_metadata/modules/catalog-zero15/catalog-zero15-impl72.txt?links=manage&source_permissions=use"))
		.exec(http("request_7")
			.get("/production/file_metadata/modules/catalog-zero15/catalog-zero15-impl73.txt?links=manage&source_permissions=use"))
		.exec(http("request_8")
			.get("/production/file_metadata/modules/catalog-zero28/catalog-zero28-impl51.txt?links=manage&source_permissions=use"))
		.exec(http("request_9")
			.get("/production/file_metadata/modules/catalog-zero8/catalog-zero8-impl13.txt?links=manage&source_permissions=use"))
		.pause(121 milliseconds)
		.exec(http("request_10")
			.get("/production/file_metadata/modules/catalog-zero7/catalog-zero7-impl85.txt?links=manage&source_permissions=use"))
		.exec(http("request_11")
			.get("/production/file_metadata/modules/catalog-zero19/catalog-zero19-impl31.txt?links=manage&source_permissions=use"))
		.exec(http("request_12")
			.get("/production/file_metadata/modules/catalog-zero8/catalog-zero8-impl11.txt?links=manage&source_permissions=use"))
		.exec(http("request_13")
			.get("/production/file_metadata/modules/catalog-zero18/catalog-zero18-impl14.txt?links=manage&source_permissions=use"))
		.pause(116 milliseconds)
		.exec(http("request_14")
			.get("/production/file_metadata/modules/catalog-zero16/catalog-zero16-impl83.txt?links=manage&source_permissions=use"))
		.exec(http("request_15")
			.get("/production/file_metadata/modules/catalog-zero14/catalog-zero14-impl44.txt?links=manage&source_permissions=use"))
		.exec(http("request_16")
			.get("/production/file_metadata/modules/catalog-zero18/catalog-zero18-impl23.txt?links=manage&source_permissions=use"))
		.exec(http("request_17")
			.get("/production/file_metadata/modules/catalog-zero18/catalog-zero18-impl24.txt?links=manage&source_permissions=use"))
		.exec(http("request_18")
			.get("/production/file_metadata/modules/catalog-zero19/catalog-zero19-impl86.txt?links=manage&source_permissions=use"))
		.exec(http("request_19")
			.get("/production/file_metadata/modules/catalog-zero21/catalog-zero21-impl87.txt?links=manage&source_permissions=use"))
		.exec(http("request_20")
			.get("/production/file_metadata/modules/catalog-zero29/catalog-zero29-impl86.txt?links=manage&source_permissions=use"))
		.exec(http("request_21")
			.get("/production/file_metadata/modules/catalog-zero21/catalog-zero21-impl82.txt?links=manage&source_permissions=use"))
		.exec(http("request_22")
			.get("/production/file_metadata/modules/catalog-zero19/catalog-zero19-impl12.txt?links=manage&source_permissions=use"))
		.exec(http("request_23")
			.get("/production/file_metadata/modules/catalog-zero28/catalog-zero28-impl84.txt?links=manage&source_permissions=use"))
		.exec(http("request_24")
			.get("/production/file_metadata/modules/catalog-zero10/catalog-zero10-impl85.txt?links=manage&source_permissions=use"))
		.exec(http("request_25")
			.get("/production/file_metadata/modules/catalog-zero23/catalog-zero23-impl62.txt?links=manage&source_permissions=use"))
		.exec(http("request_26")
			.get("/production/file_metadata/modules/catalog-zero30/catalog-zero30-impl34.txt?links=manage&source_permissions=use"))
		.exec(http("request_27")
			.get("/production/file_metadata/modules/catalog-zero24/catalog-zero24-impl52.txt?links=manage&source_permissions=use"))
		.exec(http("request_28")
			.get("/production/file_metadata/modules/catalog-zero16/catalog-zero16-impl85.txt?links=manage&source_permissions=use"))
		.exec(http("request_29")
			.get("/production/file_metadata/modules/catalog-zero19/catalog-zero19-impl53.txt?links=manage&source_permissions=use"))
		.exec(http("request_30")
			.get("/production/file_metadata/modules/catalog-zero9/catalog-zero9-impl84.txt?links=manage&source_permissions=use"))
		.exec(http("request_31")
			.get("/production/file_metadata/modules/catalog-zero19/catalog-zero19-impl54.txt?links=manage&source_permissions=use"))
		.exec(http("request_32")
			.get("/production/file_metadata/modules/catalog-zero22/catalog-zero22-impl34.txt?links=manage&source_permissions=use"))
		.exec(http("request_33")
			.get("/production/file_metadata/modules/catalog-zero17/catalog-zero17-impl87.txt?links=manage&source_permissions=use"))
		.exec(http("request_34")
			.get("/production/file_metadata/modules/catalog-zero25/catalog-zero25-impl13.txt?links=manage&source_permissions=use"))
		.pause(108 milliseconds)
		.exec(http("request_35")
			.get("/production/file_metadata/modules/catalog-zero10/catalog-zero10-impl83.txt?links=manage&source_permissions=use"))
		.exec(http("request_36")
			.get("/production/file_metadata/modules/catalog-zero12/catalog-zero12-impl87.txt?links=manage&source_permissions=use"))
		.exec(http("request_37")
			.get("/production/file_metadata/modules/catalog-zero12/catalog-zero12-impl86.txt?links=manage&source_permissions=use"))
		.exec(http("request_38")
			.get("/production/file_metadata/modules/catalog-zero25/catalog-zero25-impl24.txt?links=manage&source_permissions=use"))
		.exec(http("request_39")
			.get("/production/file_metadata/modules/catalog-zero20/catalog-zero20-impl24.txt?links=manage&source_permissions=use"))
		.exec(http("request_40")
			.get("/production/file_metadata/modules/catalog-zero3/catalog-zero3-impl74.txt?links=manage&source_permissions=use"))
		.exec(http("request_41")
			.get("/production/file_metadata/modules/catalog-zero14/catalog-zero14-impl72.txt?links=manage&source_permissions=use"))
		.exec(http("request_42")
			.get("/production/file_metadata/modules/catalog-zero28/catalog-zero28-impl13.txt?links=manage&source_permissions=use"))
		.exec(http("request_43")
			.get("/production/file_metadata/modules/catalog-zero25/catalog-zero25-impl51.txt?links=manage&source_permissions=use"))
		.exec(http("request_44")
			.get("/production/file_metadata/modules/catalog-zero13/catalog-zero13-impl51.txt?links=manage&source_permissions=use"))
		.exec(http("request_45")
			.get("/production/file_metadata/modules/catalog-zero26/catalog-zero26-impl64.txt?links=manage&source_permissions=use"))
		.exec(http("request_46")
			.get("/production/file_metadata/modules/catalog-zero29/catalog-zero29-impl71.txt?links=manage&source_permissions=use"))
		.exec(http("request_47")
			.get("/production/file_metadata/modules/catalog-zero9/catalog-zero9-impl63.txt?links=manage&source_permissions=use"))
		.exec(http("request_48")
			.get("/production/file_metadata/modules/catalog-zero21/catalog-zero21-impl86.txt?links=manage&source_permissions=use"))
		.exec(http("request_49")
			.get("/production/file_metadata/modules/catalog-zero11/catalog-zero11-impl11.txt?links=manage&source_permissions=use"))
		.exec(http("request_50")
			.get("/production/file_metadata/modules/catalog-zero20/catalog-zero20-impl42.txt?links=manage&source_permissions=use"))
		.exec(http("request_51")
			.get("/production/file_metadata/modules/catalog-zero23/catalog-zero23-impl21.txt?links=manage&source_permissions=use"))
		.exec(http("request_52")
			.get("/production/file_metadata/modules/catalog-zero15/catalog-zero15-impl62.txt?links=manage&source_permissions=use"))
		.exec(http("request_53")
			.get("/production/file_metadata/modules/catalog-zero2/catalog-zero2-impl51.txt?links=manage&source_permissions=use"))
		.exec(http("request_54")
			.get("/production/file_metadata/modules/catalog-zero10/catalog-zero10-impl22.txt?links=manage&source_permissions=use"))
		.pause(109 milliseconds)
		.exec(http("request_55")
			.get("/production/file_metadata/modules/catalog-zero11/catalog-zero11-impl84.txt?links=manage&source_permissions=use"))
		.exec(http("request_56")
			.get("/production/file_metadata/modules/catalog-zero27/catalog-zero27-impl41.txt?links=manage&source_permissions=use"))
		.exec(http("request_57")
			.get("/production/file_metadata/modules/catalog-zero3/catalog-zero3-impl23.txt?links=manage&source_permissions=use"))
		.exec(http("request_58")
			.get("/production/file_metadata/modules/catalog-zero11/catalog-zero11-impl21.txt?links=manage&source_permissions=use"))
		.exec(http("request_59")
			.get("/production/file_metadata/modules/catalog-zero11/catalog-zero11-impl62.txt?links=manage&source_permissions=use"))
		.exec(http("request_60")
			.get("/production/file_metadata/modules/catalog-zero13/catalog-zero13-impl84.txt?links=manage&source_permissions=use"))
		.exec(http("request_61")
			.get("/production/file_metadata/modules/catalog-zero13/catalog-zero13-impl86.txt?links=manage&source_permissions=use"))
		.exec(http("request_62")
			.get("/production/file_metadata/modules/catalog-zero13/catalog-zero13-impl83.txt?links=manage&source_permissions=use"))
		.exec(http("request_63")
			.get("/production/file_metadata/modules/catalog-zero8/catalog-zero8-impl81.txt?links=manage&source_permissions=use"))
		.exec(http("request_64")
			.get("/production/file_metadata/modules/catalog-zero22/catalog-zero22-impl52.txt?links=manage&source_permissions=use"))
		.exec(http("request_65")
			.get("/production/file_metadata/modules/catalog-zero21/catalog-zero21-impl41.txt?links=manage&source_permissions=use"))
		.exec(http("request_66")
			.get("/production/file_metadata/modules/catalog-zero28/catalog-zero28-impl33.txt?links=manage&source_permissions=use"))
		.pause(132 milliseconds)
		.exec(http("request_67")
			.get("/production/file_metadata/modules/catalog-zero26/catalog-zero26-impl86.txt?links=manage&source_permissions=use"))
		.exec(http("request_68")
			.get("/production/file_metadata/modules/catalog-zero20/catalog-zero20-impl73.txt?links=manage&source_permissions=use"))
		.exec(http("request_69")
			.get("/production/file_metadata/modules/catalog-zero20/catalog-zero20-impl71.txt?links=manage&source_permissions=use"))
		.exec(http("request_70")
			.get("/production/file_metadata/modules/catalog-zero1/catalog-zero1-impl71.txt?links=manage&source_permissions=use"))
		.exec(http("request_71")
			.get("/production/file_metadata/modules/catalog-zero18/catalog-zero18-impl84.txt?links=manage&source_permissions=use"))
		.exec(http("request_72")
			.get("/production/file_metadata/modules/catalog-zero13/catalog-zero13-impl74.txt?links=manage&source_permissions=use"))
		.exec(http("request_73")
			.get("/production/file_metadata/modules/catalog-zero30/catalog-zero30-impl85.txt?links=manage&source_permissions=use"))
		.exec(http("request_74")
			.get("/production/file_metadata/modules/catalog-zero27/catalog-zero27-impl33.txt?links=manage&source_permissions=use"))
		.exec(http("request_75")
			.get("/production/file_metadata/modules/catalog-zero28/catalog-zero28-impl42.txt?links=manage&source_permissions=use"))
		.exec(http("request_76")
			.get("/production/file_metadata/modules/catalog-zero8/catalog-zero8-impl33.txt?links=manage&source_permissions=use"))
		.exec(http("request_77")
			.get("/production/file_metadata/modules/catalog-zero5/catalog-zero5-impl52.txt?links=manage&source_permissions=use"))
		.exec(http("request_78")
			.get("/production/file_metadata/modules/catalog-zero9/catalog-zero9-impl72.txt?links=manage&source_permissions=use"))
		.exec(http("request_79")
			.get("/production/file_metadata/modules/catalog-zero26/catalog-zero26-impl54.txt?links=manage&source_permissions=use"))
		.exec(http("request_80")
			.get("/production/file_metadata/modules/catalog-zero5/catalog-zero5-impl43.txt?links=manage&source_permissions=use"))
		.exec(http("request_81")
			.get("/production/file_metadata/modules/catalog-zero19/catalog-zero19-impl83.txt?links=manage&source_permissions=use"))
		.exec(http("request_82")
			.get("/production/file_metadata/modules/catalog-zero10/catalog-zero10-impl13.txt?links=manage&source_permissions=use"))
		.exec(http("request_83")
			.get("/production/file_metadata/modules/catalog-zero5/catalog-zero5-impl22.txt?links=manage&source_permissions=use"))
		.exec(http("request_84")
			.get("/production/file_metadata/modules/catalog-zero6/catalog-zero6-impl54.txt?links=manage&source_permissions=use"))
		.exec(http("request_85")
			.get("/production/file_metadata/modules/catalog-zero27/catalog-zero27-impl63.txt?links=manage&source_permissions=use"))
		.exec(http("request_86")
			.get("/production/file_metadata/modules/catalog-zero12/catalog-zero12-impl33.txt?links=manage&source_permissions=use"))
		.pause(103 milliseconds)
		.exec(http("request_87")
			.get("/production/file_metadata/modules/catalog-zero17/catalog-zero17-impl13.txt?links=manage&source_permissions=use"))
		.exec(http("request_88")
			.get("/production/file_metadata/modules/catalog-zero21/catalog-zero21-impl32.txt?links=manage&source_permissions=use"))
		.exec(http("request_89")
			.get("/production/file_metadata/modules/catalog-zero1/catalog-zero1-impl24.txt?links=manage&source_permissions=use"))
		.exec(http("request_90")
			.get("/production/file_metadata/modules/catalog-zero16/catalog-zero16-impl33.txt?links=manage&source_permissions=use"))
		.exec(http("request_91")
			.get("/production/file_metadata/modules/catalog-zero3/catalog-zero3-impl32.txt?links=manage&source_permissions=use"))

val chain_1 = exec(http("request_92")
			.get("/production/file_metadata/modules/catalog-zero11/catalog-zero11-impl24.txt?links=manage&source_permissions=use"))
		.exec(http("request_93")
			.get("/production/file_metadata/modules/catalog-zero13/catalog-zero13-impl54.txt?links=manage&source_permissions=use"))
		.exec(http("request_94")
			.get("/production/file_metadata/modules/catalog-zero6/catalog-zero6-impl32.txt?links=manage&source_permissions=use"))
		.pause(119 milliseconds)
		.exec(http("request_95")
			.get("/production/file_metadata/modules/catalog-zero25/catalog-zero25-impl22.txt?links=manage&source_permissions=use"))
		.exec(http("request_96")
			.get("/production/file_metadata/modules/catalog-zero28/catalog-zero28-impl72.txt?links=manage&source_permissions=use"))
		.exec(http("request_97")
			.get("/production/file_metadata/modules/catalog-zero12/catalog-zero12-impl82.txt?links=manage&source_permissions=use"))
		.exec(http("request_98")
			.get("/production/file_metadata/modules/catalog-zero14/catalog-zero14-impl61.txt?links=manage&source_permissions=use"))
		.exec(http("request_99")
			.get("/production/file_metadata/modules/catalog-zero5/catalog-zero5-impl42.txt?links=manage&source_permissions=use"))
		.exec(http("request_100")
			.get("/production/file_metadata/modules/catalog-zero10/catalog-zero10-impl41.txt?links=manage&source_permissions=use"))
		.exec(http("request_101")
			.get("/production/file_metadata/modules/catalog-zero24/catalog-zero24-impl83.txt?links=manage&source_permissions=use"))
		.exec(http("request_102")
			.get("/production/file_metadata/modules/catalog-zero1/catalog-zero1-impl83.txt?links=manage&source_permissions=use"))
		.pause(164 milliseconds)
		.exec(http("request_103")
			.get("/production/file_metadata/modules/catalog-zero7/catalog-zero7-impl32.txt?links=manage&source_permissions=use"))
		.exec(http("request_104")
			.get("/production/file_metadata/modules/catalog-zero7/catalog-zero7-impl34.txt?links=manage&source_permissions=use"))
		.exec(http("request_105")
			.get("/production/file_metadata/modules/catalog-zero4/catalog-zero4-impl13.txt?links=manage&source_permissions=use"))
		.exec(http("request_106")
			.get("/production/file_metadata/modules/catalog-zero10/catalog-zero10-impl71.txt?links=manage&source_permissions=use"))
		.exec(http("request_107")
			.get("/production/file_metadata/modules/catalog-zero18/catalog-zero18-impl32.txt?links=manage&source_permissions=use"))
		.pause(805 milliseconds)
		.exec(http("request_108")
			.put("/production/report/centos65.localdomain")
			.headers(headers_108)
			.body(RawFileBody("FOSS375CatalogZero_0108_request.txt")))
					
	val scn = scenario("FOSS375CatalogZero").exec(
		chain_0, chain_1)

	setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
}
