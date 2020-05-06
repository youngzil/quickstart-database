JDBC 链接oracle的三种URL写法：SID方式 、ServerName方式、RAC方式 

SID是数据库实例的名字
Service_name参数，该参数对应一个数据库，而不是一个实例
一个数据库对应多个实例（SID）
一个数据库可以对应多个Service_name


---------------------------------------------------------------------------------------------------------------------

JDBC 链接oracle的三种URL写法
1.普通SID方式 
jdbc:oracle:thin:username/password@x.x.x.1:1521:SID 
2.普通ServerName方式 
jdbc:oracle:thin:username/password@//x.x.x.1:1522/ABCD 
3.RAC方式 
jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS_LIST=(ADDRESS=(PROTOCOL=TCP)(HOST=x.x.x.1)(PORT=1521))(ADDRESS=(PROTOCOL=TCP)(HOST=x.x.x.2)(PORT=1521)))(LOAD_BALANCE=yes)(CONNECT_DATA=(SERVER=DEDICATED)(SERVICE_NAME=xxrac)))

格式一: ORACLE JDBC Thin using a Service_Name:    
jdbc:oracle:thin:@//192.168.6.66:1521/rac  （数据库的服务名Service_name:rac）  
  
格式二: Oracle JDBC Thin using an SID:    
jdbc:oracle:thin:@192.168.6.66:1521:rac1   （数据库的实例名SID:rac1）  
  
格式三：Oracle JDBC Thin using  连接串      (一般在连接oracle RAC使用)
jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS_LIST=(ADDRESS=(PROTOCOL=TCP)(HOST=192.168.6.66)(PORT=1521)))(CONNECT_DATA=(SERVICE_NAME=rac)(INSTANCE_NAME=rac1)))
或者
 jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS_LIST=(ADDRESS=(PROTOCOL=TCP)(HOST=192.168.6.66)(PORT=1521))(ADDRESS=(PROTOCOL=TCP)(HOST=192.168.6.68)(PORT=1521)))(CONNECT_DATA=(SERVICE_NAME=rac)))
或者 
jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS_LIST=(ADDRESS=(PROTOCOL=TCP)(HOST=192.168.6.66)(PORT=1521))(ADDRESS=(PROTOCOL=TCP)(HOST=192.168.6.68)(PORT=1521))(FAILOVER=on)(LOAD_BALANCE=on))(CONNECT_DATA=(SERVER=DEDICATED)(SERVICE_NAME=rac)))


注意:使用SID和SERVICE_NAME方式的区别"/" 与 ":"很多人没有注意.


Java JDBC Thin Driver 连接 Oracle有三种方法，如下： 

格式一: Oracle JDBC Thin using a ServiceName: 
jdbc:oracle:thin:@//<host>:<port>/<service_name> 
Example: jdbc:oracle:thin:@//192.168.2.1:1521/XE 
注意这里的格式，@后面有//, 这是与使用SID的主要区别。 
这种格式是Oracle 推荐的格式，因为对于集群来说，每个节点的SID 是不一样的，但是SERVICE_NAME 确可以包含所有节点。 

格式二: Oracle JDBC Thin using an SID: 
jdbc:oracle:thin:@<host>:<port>:<SID> 
Example: jdbc:oracle:thin:@192.168.2.1:1521:X01A 
Note: 
Support for SID is being phased out. Oracle recommends that users switch over to usingservice names. 

格式三：Oracle JDBC Thin using a TNSName: 
jdbc:oracle:thin:@<TNSName> 
Example: jdbc:oracle:thin:@GL 
Note: 
Support for TNSNames was added in the driver release 10.2.0.1 

linux下查询sid的方法： 
在配置oracle环境变量的情况可以使用 echo $ORACLE_SID,如果没有可以使用ps -ef |grep oracle 来查询： 
oracle    2548     1  0 Aug17 ?        00:00:00 ora_pmon_xxxx 
后面的xxxx就是对应的sid。 

在windows环境下,oracle是以后台服务的方式被管理的,所以看"控制面板->管理工具->服务 里面的名称:"OracleServiceORCL",则ORCL就是sid; 

service_name 和sid的区别： 
Service_name：该参数是由oracle8i引进的。在8i以前，使用SID来表示标识数据库的一个实例，但是在Oracle的并行环境中，一个数据库对应多个实例，这样就需要多个网络服务名，设置繁琐。为了方便并行环境中的设置，引进了Service_name参数，该参数对应一个数据库，而不是一个实例，而且该参数有许多其它的好处。该参数的缺省值为Db_name. Db_domain，即等于Global_name。一个数据库可以对应多个Service_name，以便实现更灵活的配置。该参数与SID没有直接关系，即不必Service name 必须与SID一样 
sid是数据库实例的名字，每个实例各不相同。 



tnsnames.ora文件内容：
zjmsg =
（DESCRIPTION =
   (ADDRESS_LIST =
      (ADDRESS = (PROTOCOL = TCP)(HOST = 192.168.6.6)(PORT = 1521))
    )
    (CONNECT_DATA =
      (SERVER = SHARED)
     （SERVICE_NAME = sales.us.example.com）
     （INSTANCE_NAME = sales1）
     （SID = sid_name）
      (PRESENTATION = http://admin)
    )
  ）


pdb_aimsg=
(DESCRIPTION_LIST =
     (LOAD_BALANCE = off)
     (FAILOVER = on)
        (DESCRIPTION =
           (ADDRESS_LIST =
              (LOAD_BALANCE=OFF)
              (FAILOVER=ON)
              (ADDRESS = (PROTOCOL = TCP)(HOST =hxkzyc2.yw.zj.chinamobile.com)(PORT = 1521))
           )
           (CONNECT_DATA =
             (SERVICE_NAME = AIMSG)
             (FAILOVER_MODE=(TYPE=session)(METHOD=basic)(RETRIES=4)(DELAY=1))
           )
        )
        (DESCRIPTION =
           (ADDRESS_LIST =
              (LOAD_BALANCE=OFF)
              (FAILOVER=ON)
              (ADDRESS = (PROTOCOL = TCP)(HOST =hxkzyc1.yw.zj.chinamobile.com )(PORT = 1521))
           )
           (CONNECT_DATA =
              (SERVICE_NAME = AIMSG)
              (FAILOVER_MODE=(TYPE=session)(METHOD=basic)(RETRIES=4)(DELAY=1))
           )
        )
  )
  
  
pdb_MSPSEC=
(DESCRIPTION_LIST =
     (LOAD_BALANCE = off)
     (FAILOVER = on)
        (DESCRIPTION =
           (ADDRESS_LIST =
              (LOAD_BALANCE=OFF)
              (FAILOVER=ON)
              (ADDRESS = (PROTOCOL = TCP)(HOST =10.78.137.110 )(PORT = 1521))
           )
           (CONNECT_DATA =
             (SERVICE_NAME = MSPSEC)
             (FAILOVER_MODE=(TYPE=session)(METHOD=basic)(RETRIES=4)(DELAY=1))
           )
        )
        (DESCRIPTION =
           (ADDRESS_LIST =
              (LOAD_BALANCE=OFF)
              (FAILOVER=ON)
              (ADDRESS = (PROTOCOL = TCP)(HOST = 10.78.137.111 )(PORT = 1521))
           )
           (CONNECT_DATA =
              (SERVICE_NAME = MSPSEC)
              (FAILOVER_MODE=(TYPE=session)(METHOD=basic)(RETRIES=4)(DELAY=1))
           )
        )
  )




listener.ora：listener进程接受远程对数据库的接入请求
#SID_LIST_LISTENER 定义, 定义LISTENER进程监听SID
　　SID_LIST_LISTENER =
　　(SID_LIST =                            #可以监听多个SID,都存在一个SID表中
  　　(SID_DESC =
　      　(GLOBAL_DBNAME = boway)         # GLOBAL_DBNAME不是必需的除非使用HOSTNAME做数据库连接
      　　(ORACLE_HOME = E:\oracle\product\10.1.0\Db_2)
      　　(SID_NAME = orcl)
  　　)
　　)
　　
　　#监听器定义，一台数据库可以有不止一个监听器
　　LISTENER =
  　　(DESCRIPTION =
       　　(ADDRESS = (PROTOCOL = TCP)(HOST = boway)(PORT = 1521))
   　　)
   
总结:
1 .三个配置文件都是放在$ORACLE_HOME\network\admin目录下。
2 .sqlnet.ora确定解析方式
3 .listener.ora上设SID_NAME,通常用于JDBC访问,对应的错误码为12505
4 .tnsnames.ora上设SERVICE_NAME,通常用于linux sqlplus客户端,对应的错误码为12514


三个配置文件 listener.ora、sqlnet.ora、tnsnames.ora ，都是放在$ORACLE_HOME/network/admin目录下。 
1.  sqlnet.ora—–作用类似于linux或者其他unix的nsswitch.conf文件，通过这个文件来决定怎么样找一个连接中出现的连接字符串。 
2.  Tnsnames.ora——这个文件类似于unix 的hosts文件，提供的tnsname到主机名或者ip的对应，只有当sqlnet.ora中类似 
3. listener.ora——listener监听器进程的配置文件 
关于listener进程就不多说了，接受远程对数据库的接入申请并转交给oracle的服务器进程。所以如果不是使用的远程的连接，listener进程就不是必需的，同样的如果关闭listener进程并不会影响已经存在的数据库连接。 
https://blog.csdn.net/usbdrivers/article/details/7970968



在init.ora中有db_name，instance_name，service_name
在操作系统中需要配置oracle_sid
在listener.ora中有SID_NAME，GLOBAL_DBNAME，
在tnsname.ora中有SERVICE_NAME，SID



在init.ora中有db_name，instance_name，service_name
db_name是数据库的名称，在db安装时就已经设置了，这里不可修改，它觉得了数据库安装文件的位置。
instance_name是实例名，是数据库运行中名称，其实在OO中db_name相当于类而instance_name向当于对象，它也 是代表数据库运行中的内存及其进程，同时影响到了这些进程的名称，譬如：一个数据库db_name＝cus，而其实例instance_name= aking，那么数据库起来后，其进程名可能为：Pmon_aking_1。这里的实例名称要和PWDsid.ora和initSid.ora等文件匹配 上，否则，db起动报错。从这里可以看出db_name是类名，定义后是不可修改的，而对于instance_name实例名向当于对象，所以我们可以设 定自己喜欢的对象名称。不过话虽这样讲，但改了instance_name后，牵扯到很多其他的设置，还是最好不要动他，默认和db_name是一样的， 这样多好。
service_name我觉得应该是指数据库网络连接时的名称，在listener配置中会有所考虑的。这个值也是可以随意改动的，并且还可以有多个值。alter system set service_name=serv1,serv2 scope=both;
 
在listener.ora中有SID_NAME，GLOBAL_DBNAME
这里SID_NAME指数据库的运行的实例名，应该是和instance_name一致
而对于GLOBAL_DBNAME是listener配置的对外网络连接名称，我们在配置tnsname.ora时会考虑这个参数。这个参数可以任意的设置。
另外有一点需要注意，一般我们会在listener.ora手工配置数据库实例的监听配置。但oracle可以通过pmon进程支持自动注册， 这时自动注册的对外网络连接名称就会用到init.ora文件中service_name，有多个值的话就会注册多个，对于上面的例子，在这里就会注册 serv1和serv2两个监听服务。如果你还手工配置了一个GLOBAL_DBNAME＝serv3的监听服务的话，那么对于实例 instance_name=aking就会有三个监听服务。
 
在tnsname.ora中有SERVICE_NAME，SID
下面配置客户端的tnsname.ora
对于这里的配置主要要给出要连接的数据库的IP及其连接的实例或服务
在监听配置中我们提到了对外网络连接名称，在这里如果我们用SERVICE_NAME的话，就需要SERVICE_NAME＝ （GLOBAL_DBNAME或者service_name这里要求oracle已经自动注册到了监听器中），对于SID＝ （instance_name）即可，譬如：
SERVICE_NAME＝serv1，serv2，serv3都可以，或者
SID=aking
 
最后一个是ORACLE_SID参数，这个参数是操作系统中用到的，它是描述我们要默认连接的数据库实例，对于一个机器上有多个实例的情况下，要修改后才能通过 conn / as sysdba连接，因为这里用到了默认的实例名。

SID_NAME跟安装oracle时的ORACLE_SID相同，SERVICE_NAME=DBNAME.DB_DOMAIN
DB_NAME即是数据库名，它是oracle数据库的内部标识，安装以后轻易不要修改



init.ora中
Db_name：对一个数据库（Oracle database）的唯一标识，该数据库为第一章讲到的Oracle database。这种表示对于单个数据库是足够的，但是随着由多个数据库构成的分布式数据库的普及，这种命令数据库的方法给数据库的管理造成一定的负担，因为各个数据库的名字可能一样，造成管理上的混乱。为了解决这种情况，引入了Db_domain参数，这样在数据库的标识是由Db_name和Db_domain两个参数共同决定的，避免了因为数据库重名而造成管理上的混乱。这类似于互连网上的机器名的管理。我们将Db_name和Db_domain两个参数用’.’连接起来，表示一个数据库，并将该数据库的名称称为Global_name，即它扩展了Db_name。Db_name参数只能由字母、数字、’_’、’#’、’$’组成，而且最多8个字符。

Db_domain：定义一个数据库所在的域，该域的命名同互联网的’域’没有任何关系，只是数据库管理员为了更好的管理分布式数据库而根据实际情况决定的。当然为了管理方便，可以将其等于互联网的域。

Global_name：对一个数据库（Oracle database）的唯一标识，oracle建议用此种方法命令数据库。该值是在创建数据库是决定的，缺省值为Db_name. Db_domain。在以后对参数文件中Db_name与Db_domain参数的任何修改不影响Global_name的值，如果要修改Global_name，只能用ALTER DATABASE RENAME GLOBAL_NAME TO <db_name.db_domain>命令进行修改，然后修改相应参数。

Service_name：该参数是oracle8i新引进的。在8i以前，我们用SID来表示标识数据库的一个实例，但是在Oracle的并行环境中，一个数据库对应多个实例，这样就需要多个网络服务名，设置繁琐。为了方便并行环境中的设置，引进了Service_name参数，该参数对应一个数据库，而不是一个实例，而且该参数有许多其它的好处。该参数的缺省值为Db_name. Db_domain，即等于Global_name。一个数据库可以对应多个Service_name，以便实现更灵活的配置。该参数与SID没有直接关系，即不必Service name 必须与SID一样。

Net service name：网络服务名，又可以称为数据库别名（database alias）。是客户端程序访问数据库时所需要，屏蔽了客户端如何连接到服务器端的细节，实现了数据库的位置透明的特性。
 
实例 就是管理相关库的内存结构的名字（由SGA、PGA、服务器进程、用户进程、后台进程等组成）
数据库 就是实际的磁盘上的文件（数据文件、日志文件、控制文件等），负责保存数据，但由对应的实例来操作它的数据
服务名 就是对外公布的名称，为网络监听服务
其实，在我们传统的概念里，数据库是一个统称的名字，在Oracle中，你可以把“数据库”理解成一个大概念，也要把它理解成一个小概念
1、一个Oracle数据库系统中可以同时安装几个数据库，每一个数据库对应一个唯
一的实例，但是OPS系统除外，可以多个实例同时对一个数据库操作，称为并行服务
器
2、只是一个名字，SID即是INSTANCE_NAME，SERVICE_NAMES主要用在监听器中。
在listener.ora中有SID_NAME，GLOBAL_DBNAME
这里SID_NAME指数据库的运行的实例名，应该是和instance_name一致
而对于GLOBAL_DBNAME是listener配置的对外网络连接名称，我们在配置tnsname.ora时会考虑这个参数。这个参数可以任意的设置。
另外有一点需要注意，一般我们会在listener.ora手工配置数据库实例的监听配置。但oracle可以通过pmon进程支持自动注册，这时自动注册的对外网络连接名称就会用到init.ora文件中service_name，有多个值的话就会注册多个，对于上面的例子，在这里就会注册serv1和serv2两个监听服务。如果你还手工配置了一个GLOBAL_DBNAME＝serv3的监听服务的话，那么对于实例instance_name=aking就会有三个监听服务。
 
在tnsname.ora中有SERVICE_NAME，SID
下面配置客户端的tnsname.ora
对于这里的配置主要要给出要连接的数据库的IP及其连接的实例或服务
在监听配置中我们提到了对外网络连接名称，在这里如果我们用SERVICE_NAME的话，就需要SERVICE_NAME＝（GLOBAL_DBNAME或者service_name这里要求oracle已经自动注册到了监听器中），对于SID＝（instance_name）即可，譬如：
SERVICE_NAME＝serv1，serv2，serv3都可以，或者
SID=aking
 
最后一个是ORACLE_SID参数，这个参数是操作系统中用到的，它是描述我们要默认连接的数据库实例，对于一个机器上有多个实例的情况下，要修改后才能通过 conn / as sysdba连接，因为这里用到了默认的实例名。
 
1.ORACLE_SID：（ORACLE SYSTEM IDENTIFIER）
     以环境变量的形式出现的。
     Oracle实例是由SGA和一组后台进程组成的，实例的创建和启动需要一个参数文件，而参数文件的名称就是由ORACLE_SID决定的。对于init文件，缺省的文件名称是init<ORACLE_SID>.ora，对于spfile文件，缺省的文件名称是spfile<ORACLE_SID>.ora
     设置不同的ORACLE_SID值，就可以默认使用不同的参数文件启动不同的数据库实例。
     另外，ORACLE_SID的作用远远不是作为一个实例入口这么简单的，在实例启动后，实例名称INSTANCE_NAME也是从ORACLE_SID得到的。
2.INSTANCE_NAME:
     实例名称，这是Oracle实例的名字，用来区分不通的实例。在Oracle9i之前，该名字存储在两个地方：参数文件和数据库的内部试图（V$INSTANCE）.
     而在Oracle10g之后的版本中，该名字不再出现在参数文件中，而是动态从系统中获得，默认是取自ORACLE_SID。
     INSTANCE_NAME的作用除了区别不同实例之外，在监听器动态注册时，还会用于向监听器注册。比如instance_name=kanon,监听中将动态注册Instance "kanon",status READY信息。
3.DB_NAME：    
     DB_NAME概念相比于INSTANCE_NAME要重要的多，它决定实例将挂在的数据文件。它出现在数据文件，控制文件，日志文件中。在参数文件中也出现，且必须出现。这个参数涉及到系统的物理文件。
4.SERVICE_NAME和GLOBAL_DBNAME：
     这两个参数之所以放在一起讲，是因为他们往往是成对出现的。SERVICE_NAME出现在Tnsnames.ora文件中，是客户端要请求的服务名。
     GLOBAL_DBNAME出现在Listener.ora文件中，是服务器提供的服务名，可以通过show paramerer service_names查看，并可以通过alter system set service_name='servicename' scope=both来修改。
     二者对应，实现了Listerner.ora/Tnsnames.ora的重要功能----监听、请求与验证。
总结：一条startup命令，究竟是如何启动庞大的oracle数据库的呢？下面我们来贯穿起来整个启动流程，一探究竟：
      首先，系统接收到startup命令，立刻采取行动，取得环境变量ORACLE_SID的值，启动第一阶段--实例创建。系统根据找到的参数文件启动ORACLE数据库实例，实例启动后，一切由实例接管：注册INSTANCE_NAME,往往INSTANCE_NAME就是来自ORACLE_SID，接着向监听器动态注册实例自己，并将INSTANCE_NAME写入系统数据字典表，
      接下来，实例进一步读取参数文件，取得DB_NAME、控制文件、检查点等信息，进入第二阶段--挂载数据库。实例从控制文件中取得DB_NAME，并取得数据文件、日志文件等信息，进行DB_NAME的一致性检验、文件的存在性判断等工作之后，实例将挂载数据库，挂载的数据库就是DB_NAME指定的数据库。
      最后，实例进入第三阶段--启动数据库。这一阶段，实例进行了两项检查：检查点和更改点检查，之后启动数据库。





