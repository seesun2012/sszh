# sszh
sszh项目框架

项目启动顺序：
<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;1、`sszh` -> `sszh-server` -> `sszh-server-eureka` -> `SszhServerEurekaApplication.java`
<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2、`sszh` -> `sszh-server` -> `sszh-server-config` -> `SszhServerConfigApplication.java`
<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;3、`sszh` -> `sszh-server` -> `sszh-server-boot-admin` -> `SszhServerBootAdminApplication.java`
<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4、`sszh` -> `sszh-server` -> `sszh-txlcn-tm` -> `SszhTxlcnTmApplication.java`
<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;5、`sszh` -> `sszh-server` -> `sszh-server-sso` -> `SszhServerSsoApplication.java`
<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;6、`sszh` -> `sszh-server` -> `sszh-server-order` -> `SszhServerOrderApplication.java`

项目依赖关系：
<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;略...