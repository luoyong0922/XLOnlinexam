/*
SQLyog  v12.2.6 (64 bit)
MySQL - 5.6.35-log : Database - onlinexam  用户初始登录密码为123
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`onlinexam` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `onlinexam`;

/*Table structure for table `t_achievement_statistic` */

DROP TABLE IF EXISTS `t_achievement_statistic`;

CREATE TABLE `t_achievement_statistic` (
  `id` bigint(10) unsigned NOT NULL AUTO_INCREMENT,
  `teac_course_id` bigint(10) NOT NULL,
  `excellent_rate` decimal(4,2) NOT NULL,
  `pass_rate` decimal(4,2) NOT NULL,
  `absence_rate` decimal(4,2) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `t_achievement_statistic` */

insert  into `t_achievement_statistic`(`id`,`teac_course_id`,`excellent_rate`,`pass_rate`,`absence_rate`) values 

(1,1,0.21,0.68,0.12);

/*Table structure for table `t_admin` */

DROP TABLE IF EXISTS `t_admin`;

CREATE TABLE `t_admin` (
  `id` bigint(10) unsigned NOT NULL AUTO_INCREMENT,
  `admin_name` varchar(20) NOT NULL,
  `admin_gender` varchar(4) NOT NULL,
  `admin_birth` date NOT NULL,
  `admin_phone` varchar(20) NOT NULL,
  `admin_question` varchar(50) DEFAULT NULL,
  `admin_key` varchar(50) DEFAULT NULL,
  `admin_password` varchar(60) NOT NULL DEFAULT '$2a$10$rgsyROIoImw6btW7upK9H.24mAcxkC/Yei7GJxzD0FIEj0ldxqTUy',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `t_admin` */

insert  into `t_admin`(`id`,`admin_name`,`admin_gender`,`admin_birth`,`admin_phone`,`admin_question`,`admin_key`,`admin_password`) values 

(1,'xyp','女','2018-09-13','119','我是谁','xyp','$2a$10$rgsyROIoImw6btW7upK9H.24mAcxkC/Yei7GJxzD0FIEj0ldxqTUy'),

(2,'roy','男','2018-09-18','110','我是谁','roy','$2a$10$rgsyROIoImw6btW7upK9H.24mAcxkC/Yei7GJxzD0FIEj0ldxqTUy');

/*Table structure for table `t_calculate` */

DROP TABLE IF EXISTS `t_calculate`;

CREATE TABLE `t_calculate` (
  `id` bigint(10) unsigned NOT NULL AUTO_INCREMENT,
  `teac_course_id` bigint(10) NOT NULL,
  `exam_id` int(3) NOT NULL,
  `title` varchar(100) NOT NULL,
  `dificult` varchar(10) NOT NULL,
  `answer1` varchar(20) NOT NULL,
  `answer2` varchar(20) DEFAULT NULL,
  `answer3` varchar(20) DEFAULT NULL,
  `calculate_id` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

/*Data for the table `t_calculate` */

insert  into `t_calculate`(`id`,`teac_course_id`,`exam_id`,`title`,`dificult`,`answer1`,`answer2`,`answer3`,`calculate_id`) values 

(1,1,5,'十进制数153转换成二进制数是(    )。','1','10011001','','','c-1'),

(2,1,5,'十进制数49.875转换成八进制数是(    )。','1','61.7','','','c-2'),

(3,4,5,'十进制数58.75转换成十六进制数是(    )。','1','3A.C','','','c-3'),

(4,1,5,'八进制数35.54转换成十进制数是(    )。','1','29.6875','','','c-4'),

(5,4,5,'二进制数10011010.1011转换成八进制数是(    )。','1','232.54','','','c-5'),

(6,1,5,'二进制数10111转换成十进制数是(    )。','1','23','','','c-6'),

(7,4,5,'二进制数1111100转换成十进制数是(    )。','1','124','','','c-7'),

(8,1,5,'将二进制数10000001转换成十进制数应该是(    )。','1','129','','','c-8'),

(9,4,5,'十进制小数0.625转换成十六进制小数是(    )。','1','0.A              ','','','c-9'),

(10,4,5,'十进制小数0.6875转换成八进制小数是(    )。','1','0.54','','','c-10'),

(11,5,5,'十进制小数0.6875转换成二进制小数是(    )。','1','0.1011','','','c-11'),

(12,5,5,'十六进制数10AC转换成二进制数是(    )。','1','1000010101100','','','c-12'),

(13,5,5,'十六进制数1A8F转换成十进制数是(    )。','1','6799','','','c-13'),

(14,5,5,'十六进制数FF.1转换成十进制数是(    )。','1','255.0625','','','c-14');

(15,5,5,'十进制数49.875转换成八进制数是(    )。','1','61.7','','','c-15'),

/*Table structure for table `t_course` */

DROP TABLE IF EXISTS `t_course`;

CREATE TABLE `t_course` (
  `id` bigint(10) unsigned NOT NULL AUTO_INCREMENT,
  `course_num` varchar(20) NOT NULL,
  `course_name` varchar(20) NOT NULL,
  `course_credit` decimal(2,1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

/*Data for the table `t_course` */

insert  into `t_course`(`id`,`course_num`,`course_name`,`course_credit`) values 

(1,'c1','计算机组成原理',1.0),

(2,'c2','大学语文',2.0),

(3,'c3','大学英语',1.0),

(4,'c4','高等数学',2.0),

(5,'c5','数据结构',1.0),

(6,'c6','大数据分析',1.0),

(7,'c9','Android应用开发',1.0),

(8,'c8','高等数学',1.0),

(9,'c7','C++',1.0);

/*Table structure for table `t_fill` */

DROP TABLE IF EXISTS `t_fill`;

CREATE TABLE `t_fill` (
  `id` bigint(10) unsigned NOT NULL AUTO_INCREMENT,
  `fill_id` varchar(20) NOT NULL,
  `teac_course_id` bigint(10) NOT NULL,
  `exam_id` int(3) NOT NULL,
  `title` varchar(100) NOT NULL,
  `dificult` varchar(10) NOT NULL,
  `answer1` varchar(20) NOT NULL,
  `answer2` varchar(20) DEFAULT NULL,
  `answer3` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8;

/*Data for the table `t_fill` */

insert  into `t_fill`(`id`,`fill_id`,`teac_course_id`,`exam_id`,`title`,`dificult`,`answer1`,`answer2`,`answer3`) values 

(1,'f-1',1,4,'在WWW网页上有一些特殊的图形或文字，单击它们就可以看到相关内容，这类图形或文字称为(      )','1','超链接','',NULL),

(2,'f-2',1,4,'在Word中，可以显示水平标尺的两种视图模式是(      ) 。','1','普通模式和页面模式','',NULL),

(3,'f-3',1,4,'一般操作系统的主要功能是（     ）。 ','1','控制和管理计算机系统软、硬件资源 ','',NULL),

(4,'f-4',1,4,'用户在Word编辑文档时，选择某一段文字后，把鼠标指针置于选中文本的任一位置，按Ctrl 键并按鼠标左键不放，拖到另一位置上才放开鼠标。那么，该用户刚才的操作是（     ）。','1','复制文本 ','',NULL),

(5,'f-5',1,4,'在word的表格中，要计算一列数据的总和，应该使用哪个公式（     ）。','1','SUM','',NULL),

(6,'f-6',1,4,'在Word的页面视图方式下，在屏幕上看到的与打印出来的文稿（文字、图片、页眉、页脚等）布局格式（     ）。','1','完全相同','',NULL),

(7,'f-7',1,4,'在Word的编辑状态中，\"粘贴\"操作的组合键是（     ）。 ','1','Ctrl+V  ','',NULL),

(8,'f-8',1,4,'在Word中，被选定的文本以（     ）显示。','1','反像','',NULL),

(9,'f-9',1,4,'在Word中，添加下划线的快捷键是（     ）。','1','[Ctrl]+[U]','',NULL),

(10,'f-10',1,4,'在Word中选择某句话后，连续单击两次工具条中的“B”按钮，则（     ）。','1','这句话格式不变 ','',NULL),

(11,'f-11',2,4,'在拨号入网时，当线路接通后MODEM会发出声音，称为（     ）。','1','握手声    ','',NULL),

(12,'f-12',2,4,'在地理上局限在较小范围，属于一个部门或单位组建的网络属于（     ）。','1','LAN  ','',NULL),

(13,'f-13',2,4,'在电子邮件中，声音与图象文件一般不与邮件正文内容一同显示出来，而是通过(      )来发送。','1','附件','',NULL),

(14,'f-14',2,4,'在发送新邮件时，除了发件人之外，只有(      )是必须要填写的。','1','收件人地址','',NULL),

(15,'f-15',2,4,'在工作表Sheet1中，若A1为“20”，B1为“40”，A2为“15”，B2为“30”，在C1输入公式“=A1+B1”，将公式从C1复制到C2，再将公式复制到D2，则D2的值为(      )','1','75','',NULL),

(16,'f-16',2,4,'在幻灯片版式的链接功能中(      )不能进行链接的设置。','1','声音对象','',NULL),

(17,'f-17',2,4,'在计算机网络系统中，WAN指的是（     ）。','1','广域网 ','',NULL),

(18,'f-18',2,4,'在计算机网络中，LAN网指的是（     ）。 ','1','局域网　','',NULL),

(19,'f-19',2,4,'在计算机网络中，表征数据传输可靠性的指标是（     ）。','1','误码率 ','',NULL),

(20,'f-20',2,4,'在计算机网络中，表征数据传输有效性的指标是（     ）。','1','传输速率','',NULL),

(21,'f-21',3,4,'在计算机网络中，数据资源共享指的是（     ）。  ','1','各种数据文件和数据库的共享 ','',NULL),

(22,'f-22',3,4,'在计算机应用领域里，（     ）是其最广泛的应用方面。','1','数据处理','',NULL),

(23,'f-23',3,4,'在计算机运行时，把程序和数据一样存放在内存中，这是1946年由（     ）领导的研究小组正式提出并论证的。 ','1','冯·诺依曼　','',NULL),

(24,'f-24',3,4,'在计算机中，字节的英文名字是(      )　','1','byte','',NULL),

(25,'f-25',3,4,'在空白幻灯片中不可以直接插入(      )','1','文字','',NULL),

(26,'f-26',3,4,'在内存中，每个基本单位都被赋予一个唯一的序号，这个序号称之为（     ）。 ','1','地址　','',NULL),

(27,'f-27',3,4,'在微机中，Bit的中文含义是（     ）。 ','1','二进制位','',NULL),

(28,'f-28',3,4,'在微机中，存储容量为1MB，指的是（     ）。 ','1','1024×1024个字节　　','1024*1024个字节　　',NULL),

(29,'f-29',3,4,'在微机中，访问速度最快的存储器是(      )','1','内存','',NULL),

(30,'f-30',3,4,'在微机中，硬盘驱动器属于(      )','1','外存储器','',NULL),

(31,'f-31',4,4,'在微型计算机中，内存储器，通常采用（     ）。 ','1','半导体存储器 ','',NULL),

(32,'f-32',4,4,'在我国Internet的中文名是(      )。','1','因特网','',NULL),

(33,'f-33',4,4,'在因特网中、各计算机之间使用（     ）协议交换信息。','1','TCP/IP ','',NULL),

(34,'f-34',4,4,'在(   )中，补元是唯一的','1','有补分配格','',NULL),

(35,'f-35',4,4,'在默认的情况下，省略的类访问修饰符是（    ）','1','private','',NULL),

(36,'f-36',4,4,'6阶有限群的任何子群一定不是(   )阶','1','4','',NULL),

(37,'f-37',4,4,'二进制数1111100转换成十进制数是(    )。','1','124','',NULL),

(38,'f-38',4,4,'将二进制数10000001转换成十进制数应该是(    )。','1','129','',NULL),

(39,'f-39',4,4,'十进制小数0.625转换成十六进制小数是(    )。','1','0.A              ','',NULL),

(40,'f-40',4,4,'一个C源程序必须包含一个（  ）函数','1','main','',NULL),

(41,'f-41',5,4,'C语言中文件的存取方式是（）。','1','随机存取','',NULL),

(42,'f-42',5,4,'通过硬件和软件的功能扩充，把原来独立的设备改造成能为若干个进程共享的设备，这种设备称为（ ）。','1','虚拟设备','',NULL),

(43,'f-43',5,4,'C语言中数组下标的下限是（）。','1','0','',NULL),

(44,'f-44',5,4,'表达式:10!=9的值是（）。','1','Y','true',NULL),

(45,'f-45',5,4,'存储管理方案中，（ ）可采用覆盖技术。','1','可变分区存储管理','',NULL),

(46,'f-46',5,4,'在宏定义#define PI 3.14159中,用宏名代替一个（）。','1','字符串','',NULL),

(47,'f-47',5,4,'火车站的售票系统属于（ ）系统。','1','实时','',NULL),

(48,'f-48',5,4,'在 UNIX 系统中采用的页面置换算法是( )。','1','CLOCK','',NULL),

(49,'f-49',5,4,'在UNIX系统中，复制文件采用的命令为( )。','1','cp','',NULL),

(50,'f-50',5,4,'在UNIX系统中，以下哪个命令不属于查看文件内容的命令（ ）。','1','vi','',NULL),

(51,'f-51',4,4,'“床前明月光”出自____的《静夜思》。','1','李白',NULL,NULL),

(52,'f-52',6,4,'121-120=','1','1',NULL,NULL);

/*Table structure for table `t_homework` */

DROP TABLE IF EXISTS `t_homework`;

CREATE TABLE `t_homework` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT,
  `homeWork_content` varchar(100) NOT NULL,
  `course_name` varchar(20) NOT NULL,
  `date` date NOT NULL,
  `teac_course_id` bigint(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `t_homework` */

insert  into `t_homework`(`id`,`homeWork_content`,`course_name`,`date`,`teac_course_id`) values 

(1,'预习第一单元2-5小节。','计算机组成原理','2018-09-26',1),

(2,'完成第三单元课后练习。','大学语文','2018-09-26',3),

(3,'预习第二单元2-4小节','计算机组成原理','2018-09-26',2),

(4,'预习第三单元2-5小节','计算机组成原理','2018-10-06',2),

(5,'预习第四单元1-4小节','计算机组成原理','2018-10-06',2);

/*Table structure for table `t_judeg` */

DROP TABLE IF EXISTS `t_judeg`;

CREATE TABLE `t_judeg` (
  `id` bigint(10) unsigned NOT NULL AUTO_INCREMENT,
  `judge_id` varchar(20) NOT NULL,
  `teac_course_id` bigint(10) NOT NULL,
  `exam_id` int(3) NOT NULL DEFAULT '3',
  `title` varchar(100) NOT NULL,
  `dificult` varchar(10) NOT NULL,
  `type1` varchar(10) NOT NULL,
  `type2` varchar(10) NOT NULL,
  `answer` varchar(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8;

/*Data for the table `t_judeg` */

insert  into `t_judeg`(`id`,`judge_id`,`teac_course_id`,`exam_id`,`title`,`dificult`,`type1`,`type2`,`answer`) values 

(1,'j-1',1,3,'ROM是只读存储器，其中的内容只能读一次，下次再读就读不出来了','1','正确','错误','错误'),

(2,'j-2',1,3,'操作系统是主机与外设之间的接口','1','正确','错误','错误'),

(3,'j-3',1,3,'所有微机上都可以使用的软件称为应用软件','1','正确','错误','错误'),

(4,'j-4',4,3,'执行“开始”菜单的“程序”中的“Microsoft Word”','1','正确','错误','错误'),

(5,'j-5',1,3,'因特网是国际计算机互联网','1','正确','错误','错误'),

(6,'j-6',1,3,'因特网诞生于是1969年','1','正确','错误','错误'),

(7,'j-7',1,3,'Word一定要通过使用“打印预览”才能看到打印出来的效果','1','正确','错误','错误'),

(8,'j-8',4,3,'硬盘通常安装在主机箱内，所以硬盘属于内存','1','正确','错误','错误'),

(9,'j-9',1,3,'操作系统是软件与硬件的接口','1','正确','错误','错误'),

(10,'j-10',1,3,'操作系统是用户与计算机之间的接口','1','正确','错误','错误'),

(11,'j-11',1,3,'在桌面中Microsoft Word 的快捷方式图标上双击鼠标左键','1','正确','错误','错误'),

(12,'j-12',1,3,'因特网是计算机网络的网络','1','正确','错误','错误'),

(13,'j-13',1,3,'因特网最早的名字叫阿帕网','1','正确','错误','错误'),

(14,'j-14',1,3,'Word不能进行图文混排','1','正确','错误','错误'),

(15,'j-15',4,3,'CPU不能直接与外存打交道','1','正确','错误','错误'),

(16,'j-16',1,3,'操作系统是源程序和目标程序的接口','1','正确','错误','错误'),

(17,'j-17',1,3,'一个完整的计算机系统是由主机和输入输出设备组成的','1','正确','错误','错误'),

(18,'j-18',1,3,'执行“开始”菜单中的“运行”命令，然后在对话框中输入Word 的路径','1','正确','错误','错误'),

(19,'j-19',1,3,'因特网上提供了多种信息网络系统','1','正确','错误','错误'),

(20,'j-20',1,3,'因特网由美国国防部资助并建立在军事部门','1','正确','错误','错误'),

(21,'j-21',2,3,'任何存储器都有记忆能力，即其中的信息不会丢失','1','正确','错误','错误'),

(22,'j-22',5,3,'操作系统是用户与计算机之间的接口','1','正确','错误','错误'),

(23,'j-23',2,3,'磁盘驱动器是存储器','1','正确','错误','错误'),

(24,'j-24',2,3,'在任意一个Word 文件的图标上双击鼠标右键','1','正确','错误','错误'),

(25,'j-25',5,3,'万维网就是因特网','1','正确','错误','错误'),

(26,'j-26',2,3,'因特网由美国国防部资助但建立在4所大学和研究所','1','正确','错误','错误'),

(27,'j-27',2,3,'一个完整的计算机系统是由微处理器、存储器和输入/输出设备组成','1','正确','错误','错误'),

(28,'j-28',2,3,'裸机配置应用软件是可运行的 ','1','正确','错误','错误'),

(29,'j-29',4,3,'硬件系统不可用软件代替 ','1','正确','错误','错误'),

(30,'j-30',5,3,'计算机区别于其他计算工具的最主要特点是能存储程序和数据','1','正确','错误','错误'),

(31,'j-31',2,3,'裸机的第一次扩充要装数据库管理系统 ','1','正确','错误','错误'),

(32,'j-32',5,5,'软件不可用硬件代替 ','1','正确','错误','错误'),

(33,'j-33',5,3,'电源关闭后，ROM中的信息会丢失','1','正确','错误','错误'),

(34,'j-34',2,3,'硬件配置要尽量满足机器的可扩充性 ','1','正确','错误','错误'),

(35,'j-35',5,3,'计算机性能完全取决于CPU ','1','正确','错误','错误'),

(36,'j-36',3,3,'16位字长计算机能处理的最大数是16位十进制数','1','正确','错误','错误'),

(37,'j-37',3,3,'系统软件好坏决定计算机性能','1','正确','错误','错误'),

(38,'j-38',4,3,'软件和硬件的界线不是绝对的，有时功能是等效的 ','1','正确','错误','错误'),

(39,'j-39',5,3,'单击工具栏中的“新建”按钮与使用文件菜单中“新建”命令是一样的','1','正确','错误','错误'),

(40,'j-40',3,3,'状态栏总是位于窗口的底部，一般分为左、中、右','1','正确','错误','错误'),

(41,'j-41',5,3,'单击工具栏中的“新建”按钮，可页面布局，再出现新文档窗口','1','正确','错误','错误'),

(42,'j-42',5,3,'在幻灯片视图中，通过状态栏可以知道当前幻灯片在整个演示文稿中属于第几张','1','正确','错误','错误'),

(43,'j-43',3,3,'单击工具栏中的“新建”按钮，任务窗口直接显示“新建演示文稿”状态','1','正确','错误','错误'),

(44,'j-44',3,3,'通过状态栏可以知道演示文稿所用的模版','1','正确','错误','错误'),

(45,'j-45',5,3,'使用“文件”菜单中的“新建”命令，任务窗口直接显示“新建演示文稿”状态','1','正确','错误','错误'),

(46,'j-46',3,3,'状态栏中的拼写检查图标在没有发现错误字显示勾，在有错别字时显示叉','1','正确','错误','错误'),

(47,'j-47',3,3,'格式刷可以用来复制字符格式和段落格式','1','正确','错误','错误'),

(48,'j-48',5,3,'将选定格式复制到不同位置的方法是单击“格式刷”按钮　','1','正确','错误','错误'),

(49,'j-49',3,3,'双击格式刷只能将选定格式复制到一个位置','1','正确','错误','错误'),

(50,'j-50',3,3,'“格式刷”按钮无任何作用','1','正确','错误','错误'),

(51,'j-501',3,3,'“床前明月光”出自李白的《静夜思》。','1','正确','错误','正确'),

(52,'j-52',2,3,'1-9+8=0','1','正确','错误','正确');

/*Table structure for table `t_multi_select` */

DROP TABLE IF EXISTS `t_multi_select`;

CREATE TABLE `t_multi_select` (
  `id` bigint(10) unsigned NOT NULL AUTO_INCREMENT,
  `multi_selection_id` varchar(20) NOT NULL,
  `teac_course_id` bigint(10) NOT NULL,
  `exam_id` int(3) NOT NULL,
  `title` varchar(100) NOT NULL,
  `dificult` varchar(10) NOT NULL,
  `type1` varchar(100) NOT NULL,
  `type2` varchar(100) NOT NULL,
  `type3` varchar(100) NOT NULL,
  `type4` varchar(100) NOT NULL,
  `type5` varchar(100) NOT NULL,
  `answer` varchar(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8;

/*Data for the table `t_multi_select` */

insert  into `t_multi_select`(`id`,`multi_selection_id`,`teac_course_id`,`exam_id`,`title`,`dificult`,`type1`,`type2`,`type3`,`type4`,`type5`,`answer`) values 

(1,'ms-1',1,1,'上传和下载文件在Dreamweaver MX可以通过（   ）来实现。','1',' 预览窗口','站点管理窗口','行为控制面版','编辑窗口','站点管理窗口','BE'),

(2,'ms-2',1,1,'上网的网址应在游览器的什么栏输入（     ）。','1','标题栏','地址栏','地址栏','频道栏','链接栏','BC'),

(3,'ms-3',1,1,'在Word 2003中设定打印纸张大小时，应当使用的命令是(      )','1','\"文件\"菜单中的\"打印预览\"命令','\"文件\"菜单中的\"页面设置\"命令','\"视图\"菜单中的\"工具栏\"命令','\"视图\"菜单中的\"页面\"命令','\"文件\"菜单中的\"页面设置\"命令','BE'),

(4,'ms-4',1,1,'Word 2003中设置段落的缩进的正确操作是（     ）。','1','在\"工具\"菜单中选择\"选项\"，再选择\"视图\"并作相应的选择。','在\"编辑\"菜单中选择\"定位\"，再选择\"定位目标\"并作相应的选择。','在\"格式\"菜单中选择\"段落\"，再选择\"缩进与间距\"并作相应的选择。','在\"格式\"菜单中选择\"字体\"，再在其对话框中作相应的选择。','在\"格式\"菜单中选择\"字体\"，再在其对话框中作相应的选择。','CE'),

(5,'ms-5',1,1,'设置幻灯片放映时间的命令(      )','1','“幻灯片放映|预设动画”命令','“幻灯片放映|动作设置”命令','“幻灯片放映|排练计时”命令','“插入|日期和时间”命令','“插入|日期和时间”命令','CE'),

(6,'ms-6',1,1,'申请免费电子邮箱需要（     ）_进行申请。','1','拿单位介绍信和身份证到民政部门申请','连入因特网、进入提供免费邮箱的网站','拿单位介绍信和身份证到邮电局申请','由二个有电子邮箱的朋友介绍、上网后申请','连入因特网、进入提供免费邮箱的网站','BD'),

(7,'ms-7',1,1,'声音输入汉字是通过（     ）将讲话的声音输入计算机，然后用语音识别软件转换成对应的字、词。','1','麦克风','麦克风','音箱','麦克风','电话机','ABD'),

(8,'ms-8',1,1,'使用“开始”菜单中的查找命令，要查找的文件名中可以使用（     ）。','1','通配符?  ','通配符*   ','三者都可以','三者都不可以','通配符%','AB'),

(9,'ms-9',1,1,'使用Cache可以提高计算机运行速度，这是因为（     ）。 ','1','Cache增大了内存的容量　　',' Cache扩大了硬盘的容量 ',' Cache缩短了CPU的等待时间','Cache可以存放程序和数据 ',' Cache缩短了CPU的等待时间','CE'),

(10,'ms-10',1,1,'使用常用工具栏的按钮，可以直接进行的操作是(      )。','1','嵌入图片',' 对文章分栏','插入表格','段落首行缩进','插入表格','CE'),

(11,'ms-11',1,1,'使用电子邮件时，有时收到的邮件有古怪字符，既出现了乱码，这是由于（     ）。','1','病毒 ','接收方操作系统有问题 ','发送方计算机故障 ','编码未统一','编码未统一','DE'),

(12,'ms-12',1,1,'使用高级语言编写的程序称之为（     ）。 ','1','源程序　　','编辑程序','编译程序',' 连接程序 ',' 源程序　　　','AE'),

(13,'ms-13',4,1,'使用国际户联网时，通常使用的网络通信协议是（     ）。','1','NCP','NETBUEI','OSI ','TCP/IP','TCP/IP','DE'),

(14,'ms-14',4,1,'使用什么命令可使本来放在下层的图，移置于上层(      )','1','[绘图]下拉菜单中的[组合]命令','[绘图]下拉菜单中的[叠放次序]命令','[绘图]下拉菜单中的[叠放次序]命令','[绘图]下拉菜单中的[编辑顶点]命令','[绘图]下拉菜单中的[叠放次序]命令','BCE'),

(15,'ms-15',1,1,'世界上首次提出存储程序计算机体系结构的是（     ）。','1','艾仑•图灵','冯•诺依曼   ','莫奇莱','比尔•盖茨','冯•诺依曼   ','BE'),

(16,'ms-16',1,1,'世界上首先实现存储程序的电子数字计算机是（     ）。','1','ENIAC','UNIVAC','EDVAC ','EDSAC','EDVAC ','CE'),

(17,'ms-17',2,1,'视窗操作系统简称(      )','1','WINDOWS','DOS','UCDOS','WPS','WINDOWS','AE'),

(18,'ms-18',4,1,'收到一封邮件，再把它寄给别人，一般可以用（     ）。','1','回复','转寄 ','编辑','转寄 ','发送','BD'),

(19,'ms-19',4,1,'手写汉字输入系统一般由（     ）组成。','1','纸张和圆珠笔   ','专用笔和写字板','钢笔和扫描仪  ','圆珠笔和塑料板','专用笔和写字板','BE'),

(20,'ms-20',4,1,'鼠标是 WINDOWS环境下的一种重要的(      )工具。','1','画图','指示','输入','输出','输入','CE'),

(21,'ms-21',2,1,'数据通信中的信道传输速率单位是bps，它表示（     ）。','1','字节/秒','二进制位/秒 ','兆字节/秒','千字节/秒  ','二进制位/秒','BE'),

(22,'ms-22',2,1,'双击Windows桌面上的快捷图标，可以（     ）。','1','打开相应的应用程序窗口','删除该应用程序','在磁盘上保存该应用程序','弹出对应的命令菜单','打开相应的应用程序窗口','AE'),

(23,'ms-23',2,1,'提供不可靠传输的传输层协议是（     ）','1','TCP','IP','UDP','PPP','UDP','CE'),

(24,'ms-24',2,1,'通常把计算机网络定义为（     ）。  ','1','以共享资源为目标的计算机系统，称为计算机网络','能按网络协议实现通信的计算机系统，称为计算机网络  ','把分布在不同地点的多台计算机互联起来构成的计算机系统，称为计算机网络。 ','把分布在不同地点的多台计算机在物理上实现互联，按照网络协议实现相互间的通信，共享硬件、软件和数据资源为目标的计算机系统，称为计算机网络。','把分布在不同地点的多台计算机在物理上实现互联，按照网络协议实现相互间的通信，共享硬件、软件和数据资源为目标的计算机系统，称为计算机网络','DE'),

(25,'ms-25',4,1,'通常说的64位、32位个人计算机，其中的位数由（     ）决定。','1','存储器 ','显示器','中央处理器  ','硬盘','中央处理器  ','CE'),

(26,'ms-26',5,1,'通过（　），可以对一个网页的名称、网页背景、网页链接文字属性、网页边界等进行设置。','1','站点管理窗口','对象面版','页面属性窗口','属性面版','页面属性窗口','CE'),

(27,'ms-27',2,1,'通过（　）旁边的颜色按钮，可以选取一种颜色作为链接文字在鼠标按下时的颜色。','1','活动链接','链接','访问过的链接','文本','活动链接','AE'),

(28,'ms-28',5,1,'通过Internet发送或接收电子邮件(Email)的首要条件是应该有一个电子邮件(Email)地址，它的正确形式是（     ）。','1','用户名@域名 ','用户名#域名','用户名/域名','用户名、域名','用户名@域名 ','AE'),

(29,'ms-29',2,1,'完整的计算机系统由（     ）两大部分组成。','1','应用软件和系统软件 ','随机存储器和只读存储器','硬件系统和软件系统 ','中央处理器和外部设备','硬件系统和软件系统 ','CE'),

(30,'ms-30',2,1,'微处理器处理的数据基本单位为字。一个字的长度通常是（     ）。','1','16个二进制位 ','32个二进制位','64个二进制位','与微处理器芯片的型号有关','与微处理器芯片的型号有关','DE'),

(31,'ms-31',2,1,'微机唯一能够直接识别和处理的语言是（     ）。 ','1','汇编语言　','高级语言　','甚高级语言　','机器语言 ','机器语言 ','DE'),

(32,'ms-32',5,1,'为了保证全网的正确通信，Internet为联网的每个网络和每台主机都分配了唯一的地址，该地址由32位二进制数组成，并每隔8位用小数点分隔，将它称为(      )。','1','TCP地址','IP地址','WWW服务器地址','WWW客户机地址','IP地址','BE'),

(33,'ms-33',2,1,'为了获取Windows的帮助信息, 可以在需要帮助的时候按(      )键。','1','F1','F2','F3','F4','F1 ','AE'),

(34,'ms-34',5,1,'为了正常退出WINDOWS，用户的正确操作是(      )','1','关掉供给计算机的电源','选择系统菜单中的“关闭系统”并进行人机对话','在没有任何程序正在执行的情况下关掉计算机的电源','按ALT-CTRL-DEL键','选择系统菜单中的“关闭系统”并进行人机对话','BE'),

(35,'ms-35',2,1,'希望在PowerPoint2003编辑幻灯片内容时，其大小与窗口大小相适应，应选择(      )','1','“文件”菜单中的“页面设置”命令','“窗口”菜单中的“缩至一页”命令','工具栏上“显示比例”下拉列表中的“100%”','工具栏上“显示比例”下拉列表中的“最佳大小”','工具栏上“显示比例”下拉列表中的“最佳大小','DE'),

(36,'ms-36',5,1,'下列说法中，只有(      )是正确的。','1','ROM是只读存储器，其中的内容只能读一次，下次再读就读不出来了','硬盘通常安装在主机箱内，所以硬盘属于内存','CPU不能直接与外存打交道','任何存储器都有记忆能力，即其中的信息不会丢失','CPU不能直接与外存打交道','CE'),

(37,'ms-37',2,1,'下列叙述中，正确的是(      )','1','操作系统是主机与外设之间的接口','操作系统是软件与硬件的接口','操作系统是源程序和目标程序的接口','操作系统是用户与计算机之间的接口','操作系统是用户与计算机之间的接口','DE'),

(38,'ms-38',5,1,'下列叙述中，正确的是(      )','1','所有微机上都可以使用的软件称为应用软件','操作系统是用户与计算机之间的接口','一个完整的计算机系统是由主机和输入输出设备组成的','磁盘驱动器是存储器','操作系统是用户与计算机之间的接口','BE'),

(39,'ms-39',4,1,'下列有关启动Word 的错误方法是(      )。','1','执行“开始”菜单的“程序”中的“Microsoft Word”','在桌面中Microsoft Word 的快捷方式图标上双击鼠标左键','执行“开始”菜单中的“运行”命令，然后在对话框中输入Word 的路径','在任意一个Word 文件的图标上双击鼠标右键','在任意一个Word 文件的图标上双击鼠标右键','DE'),

(40,'ms-40',5,1,'下列有关因特网的叙述，(      )的说法是错误的。','1','因特网是国际计算机互联网','因特网是计算机网络的网络','因特网上提供了多种信息网络系统','万维网就是因特网','万维网就是因特网','DE'),

(41,'ms-41',3,1,'下列有关因特网历史的叙述中，(      )是错误的。','1','因特网诞生于是1969年','因特网最早的名字叫阿帕网','因特网由美国国防部资助并建立在军事部门','因特网由美国国防部资助但建立在4所大学和研究所','因特网由美国国防部资助并建立在军事部门','CE'),

(42,'ms-42',3,1,'下面对中文Word 的特点描述正确的是（     ）。','1','一定要通过使用“打印预览”才能看到打印出来的效果','不能进行图文混排','所见即所得','无法检查常见的英文拼写及语法错误','所见即所得','CE'),

(43,'ms-43',5,1,'下面关于ROM的说法中，正确的是（     ）。 ','1','CPU不能向ROM随机写入数据 ','ROM中的内容在断电后不会消失 ','ROM是只读存储器的英文缩写 ','ROM是只读的，所以它不是内存而是外存 ','ROM是只读的，所以它不是内存而是外存','ABC'),

(44,'ms-44',3,1,'下面关于Windows 窗口的描述中,(      )是正确的。','1','Windows环境下的窗口中都具有标题栏','在Windows中启动一个应用程序, 就打开一个窗口','在应用程序窗口中出现的其它窗口, 称为文档窗口','既可移动位置，又可改变大小','都不正确','ABD'),

(45,'ms-45',3,1,'下面关于快捷菜单的描述中,(      )是正确的。','1','快捷菜单可以显示出与某一对象相关的命令菜单','选定需要操作的对象, 单击左键, 屏幕上就会弹出快捷菜单','选定需要操作的对象, 单击右键, 屏幕上就会弹出快捷菜单','按Esc 键或单击桌面或窗口上的任一空白区域,都可以退出快捷菜单','都不正确','ACD'),

(46,'ms-46',3,1,'下面关于显示器的叙述，不正确的是(      )','1','显示器是输入设备','显示器是输出设备','显示器是输入/输出设备','显示器是存储设备','都不正确','ACD'),

(47,'ms-47',5,1,'下面哪一组菜单是Word 2003和Excel 2003都有的(      )','1','文件、编辑、视图、工具、数据','文件、视图、格式、表格、数据','插入、视图、格式、表格、数据','文件、编辑、视图、格式、工具','文件、编辑、视图、格式、工具','DE'),

(48,'ms-48',3,1,'下面是有关计算机病毒的说法，其中（     ）正确','1','计算机病毒有引导型病毒、文件型病毒、复合型病毒等','计算机病毒中也有良性病毒 ','计算机病毒实际上是一种计算机程序 ','计算机病毒是由于程序的错误编制而产生的','都不正确','ABC'),

(49,'ms-49',3,1,'下面说法中不正确的是(      )','1','一个完整的计算机系统是由微处理器、存储器和输入/输出设备组成','计算机区别于其他计算工具的最主要特点是能存储程序和数据','电源关闭后，ROM中的信息会丢失','16位字长计算机能处理的最大数是16位十进制数','都不正确','ACD'),

(50,'ms-50',3,1,'下述叙述不正确的是（     ）。 ','1','裸机配置应用软件是可运行的 ','裸机的第一次扩充要装数据库管理系统 ','硬件配置要尽量满足机器的可扩充性 ','系统软件好坏决定计算机性能','都不正确','ABD');

/*Table structure for table `t_paper` */

DROP TABLE IF EXISTS `t_paper`;

CREATE TABLE `t_paper` (
  `id` bigint(10) unsigned NOT NULL AUTO_INCREMENT,
  `teac_course_id` bigint(10) NOT NULL,
  `stu_id` bigint(10) NOT NULL,
  `selection_ids` varchar(200) DEFAULT NULL,
  `multi_selection_ids` varchar(200) DEFAULT NULL,
  `judge_ids` varchar(200) DEFAULT NULL,
  `fill_ids` varchar(200) DEFAULT NULL,
  `calculate_ids` varchar(200) DEFAULT NULL,
  `subject_ids` varchar(200) DEFAULT NULL,
  `selection_answers` varchar(200) DEFAULT NULL,
  `multi_selection_answers` varchar(200) DEFAULT NULL,
  `judge_answers` varchar(200) DEFAULT NULL,
  `fill_answers` varchar(200) DEFAULT NULL,
  `subject_answer1` varchar(500) DEFAULT NULL,
  `subject_answer2` varchar(500) DEFAULT NULL,
  `calculate_answers` varchar(500) DEFAULT NULL,
  `subject_answer3` varchar(500) DEFAULT NULL,
  `subject_answer4` varchar(500) DEFAULT NULL,
  `subject_answer5` varchar(500) DEFAULT NULL,
  `pape_state` int(3) NOT NULL DEFAULT '0',
  `total_score` int(5) DEFAULT NULL,
  `test_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

/*Data for the table `t_paper` */







/*Table structure for table `t_paper_standard` */

DROP TABLE IF EXISTS `t_paper_standard`;

CREATE TABLE `t_paper_standard` (
  `id` bigint(10) unsigned NOT NULL AUTO_INCREMENT,
  `test_type` varchar(10) DEFAULT NULL,
  `test_amount` int(3) DEFAULT '0',
  `test_value` int(3) DEFAULT '0',
  `teac_course_id` bigint(10) NOT NULL,
  `test_time` int(5) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

/*Data for the table `t_paper_standard` */






/*Table structure for table `t_select` */

DROP TABLE IF EXISTS `t_select`;

CREATE TABLE `t_select` (
  `id` bigint(10) unsigned NOT NULL AUTO_INCREMENT,
  `selection_id` varchar(20) NOT NULL,
  `teac_course_id` bigint(10) NOT NULL,
  `exam_id` int(3) NOT NULL,
  `title` varchar(100) NOT NULL,
  `dificult` varchar(10) NOT NULL,
  `type1` varchar(50) NOT NULL,
  `type2` varchar(50) NOT NULL,
  `type3` varchar(50) NOT NULL,
  `type4` varchar(50) NOT NULL,
  `answer` varchar(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8;

/*Data for the table `t_select` */

insert  into `t_select`(`id`,`selection_id`,`teac_course_id`,`exam_id`,`title`,`dificult`,`type1`,`type2`,`type3`,`type4`,`answer`) values 

(1,'sel-1',1,2,'计算机能按照人们的意图自动、高速地进行操作，是因为采用了（     ）。 ','1','程序存储在内存','高性能的CPU ','高级语言','机器语言 ','A'),

(2,'sel-2',1,2,'计算机能直接执行的程序是（     ）。 ','1','源程序　','机器语言程序 ','高级语言程序　','汇编语言程序 ','B'),

(3,'sel-3',1,2,'计算机所具有的存储程序和程序原理是（     ）提出的。','1','图灵','布尔 ','冯•诺依曼 ','爱因斯坦','C'),

(4,'sel-4',1,2,'计算机业界最初的硬件巨头“蓝色巨人”指的是（     ）。','1','IBM ','Microsoft','联想 ','Sun','A'),

(5,'sel-5',1,2,'计算机硬件能直接识别和执行的只有(      )','1','汇编语言','符号语言','高级语言','机器语言','D'),

(6,'sel-6',1,2,'计算机用户有了可以上网的计算机系统后，一般需找一家（     ）注网入网。','1','软件公司 ','系统集成商','ISP ','电信局','C'),

(7,'sel-7',1,2,'计算机用于解决科学研究与工程计算中的数学问题，称为（     ）。','1','数值计算 ','数学建模  ','数据处理','自动控制','A'),

(8,'sel-8',1,2,'计算机在工作中突然停电，（     ）中的数据将丢失。','1','硬盘     ','光盘','ROM   ','RAM','D'),

(9,'sel-9',1,2,'计算机中存储单元中存储的内容 (      )','1','可以是数据和指令','只能是程序','只能是数据','只能是指令','A'),

(10,'sel-10',1,2,'计算机中存储信息的最小单位是（     ）。','1','字','字节 ','字长 ','位','B'),

(11,'sel-11',2,2,'建设信息高速公路主要是为了（     ）。','1','解决城市交通拥挤的问题','方便快捷地交流信息','监视上网计算机的活动','解决失业问题','B'),

(12,'sel-12',2,2,'将高级语言编写的程序翻译成机器语言程序，采用的两种翻译方式是（     ）。 ','1','编译和解释　','编译和汇编 ',' 编译和链接　　','解释和汇编 ','A'),

(13,'sel-13',2,2,'将高级语言程序设计语言源程序翻译成计算机可执行代码的软件称为 （     ）','1','汇编程序','编译程序','管理程序','服务程序','B'),

(14,'sel-14',2,2,'将个人计算机的供电线路与大的动力设备用电线路分开，主要是为了避免（     ）。','1','突然停电造成损失    ','耗电量变大','供电线路发热','外电源的波动和干扰信号太强','D'),

(15,'sel-15',2,2,'将微机的主机与外设相连的是(      )','1','总线','磁盘驱动器','内存','输入/输出接口电路','D'),

(16,'sel-16',2,2,'结构化程序设计的三种基本控制结构是（     ）。','1','顺序、选择和转向','层次、网状和循环','模块、选择和循环','顺序、循环和选择','D'),

(17,'sel-17',2,2,'进入Word后，打开了一个已有文档w1.doc，又进行了\"新建\"操作，则(      )','1','w1.doc被关闭','w1.doc和新建文档均处于打开状态','\"新建\"操作失败','新建文档被打开但w1.doc被关闭','B'),

(18,'sel-18',2,2,'局域网的拓扑结构是(      )。','1','环型','星型','总线型','以上都可以','D'),

(19,'sel-19',2,2,'局域网组网完成后，决定网络使用性能的关键是（     ）。','1','网络的拓扑结构 ','网络的通信协议','网络的传输介质 ','网络的操作系统','B'),

(20,'sel-20',2,2,'具有多媒体功能的微型计算机系统，通常都配有CD—ROM，这是一种(      )','1','只读存储器','只读大容量软盘','只读硬盘存储器','只读光盘存储器','D'),

(21,'sel-21',3,2,'开发保密的计算机应用系统时，研制人员与操作使用人员（     ）。','1','最好是同一批人    ','应当分开','不能有传染病      ','应做健康检查','B'),

(22,'sel-22',3,2,'可将各种数据转换为计算机能处理的形式并输送到计算机中的设备统称为(      )','1','输入设备','输出设备','输入/输出设备','存储设备','A'),

(23,'sel-23',3,2,'客户/服务器模式的局域网，其网络硬件主要包括服务器、工作站、网卡和（     ）。','1','网络拓扑结构 ','计算机','传输介质','网络协议','C'),

(24,'sel-24',3,2,'控制面板的主要作用是（     ）。','1',' 调整窗口','设置系统配置','管理应用程序 ','设置高级语言','B'),

(25,'sel-25',3,2,'利用（     ）可以使文本快速进行格式复制。','1','格式菜单 ','格式刷','编辑命令 ','段落命令','B'),

(26,'sel-26',3,2,'利用电子邮件发出的信函是(      )。','1','直接输送到收信人的电脑硬盘中','输送到目的地主机的E-mail信箱','直接输送到收信人附近的邮局','由收到的电信局直接转交给收件人','B'),

(27,'sel-27',3,2,'连到局域网上的节点计算机必需要安装（     ）硬件。','1','调制解调器 ','交换机   ','集线器','网络适配卡','D'),

(28,'sel-28',3,2,'每台计算机必须知道对方的（     ） 才能在Internet上与之通信。','1','电话号码 ','主机号','IP地址','邮编与通信地址','C'),

(29,'sel-29',3,2,'默认的HTTP（超级文本传输协议）端口是（     ）。','1','21','23','80','8080','C'),

(30,'sel-30',3,2,'默认的情况下，Excel 自定义单元格格式使用的是“G/通用格式”，公式以(      )显示。','1','“=公式”方式','表达式方式','值方式','全0或全空格','C'),

(31,'sel-31',3,2,'默认的情况下，Excel 自定义单元格格式使用的是“G/通用格式”，文本数据(      )','1','左对齐','右对齐','居中','空一格左对齐','A'),

(32,'sel-32',4,2,'默认情况下，Excel中工作簿文档窗口的标题为Book1，其中一个工作簿中有3个工作表，当前工作表为(      )','1','工作表','工作表1','Sheet','Sheet1','D'),

(33,'sel-33',4,2,'某单位的财务管理软件属于（     ）。 ','1','工具软件　','系统软件','编辑软件　 ','应用软件','D'),

(34,'sel-34',4,2,'某单位自行开发的工资管理系统，按计算机应用的类型划分，它属于（     ）。','1','科学计算 ','辅助设计 ','数据处理 ','实时控制','C'),

(35,'sel-35',4,2,'某单元格数值格式设置为“#,##0.00”，其含义是(      )','1','整数4位，保留2位小数','整数4位，小数2位','整数4位，千位加分节符，保留2位小数','整数1位，小数2位','C'),

(36,'sel-36',5,2,'某台主机属于中国电信系统，其域名应以 (      )结尾。','1','com.cn','com','net.cn','net','C'),

(37,'sel-37',4,2,'某一文字对象设置了超级链接后，不正确的说法是(      )','1','在演示该页幻灯片时，当鼠标指针移到文字对象上会变成“手”形','在幻灯片视图窗格中，当鼠标指针移到文字对象上会变成“手”形','该文字对象的颜色会以默认的配色方案显示','可以改变文字的超级链接颜色','B'),

(38,'sel-38',4,2,'目前，一台计算机要连入Internet，必须安装的硬件是(      )。','1','调制解调器或网卡','网络操作系统','网络查询工具','WWW浏览器','A'),

(39,'sel-39',4,2,'目前计算机应用领域可大致分为三个方面，指出下列正确答案（     ）。','1','CAI、专家系统、人工智能  ','工程设计、CAI、文字处理','实时控制、科学计算、数据处理','数据分析、人工智能、计算机网络','C'),

(40,'sel-40',4,2,'目前使用的防病毒软件作用是(      )','1','查出并清除任何病毒','查出已知名的病毒、清除部分病毒','查出任何已感染的病毒','清除任何已感染的病毒','B'),

(41,'sel-41',5,2,'内存和外存相比，其主要特点是(      )','1','能存储大量信息','能长期保存信息','存取速度快','能同时存储程序和数据','C'),

(42,'sel-42',4,2,'能直接与CPU交换信息的功能单元是(      )','1','显示器','控制器','主存储器','运算器','C'),

(43,'sel-43',5,2,'你可以通过（     ）发布信息、了解信息，也可以即时与他人聊天。 它就象一块巨大的公共广告栏，你可以随便张贴，也可看他人的广告，它就像一个公共的聊天室，你可以与大家同时聊天，也可以找一个人单独交谈。','1','WEB','BBS ','FTP','IP地址','B'),

(44,'sel-44',5,2,'你想给某人通过Email发送某个小文件时，你必须（     ）。','1','在主题上写含有小文件','把这个小文件复制一下，粘贴在邮件内容里','无法办到。','使用粘贴附件功能，通过粘贴上传附件完成','D'),

(45,'sel-45',5,2,'排练计时，在哪种视图中不能进行？(      )','1','幻灯片放映视图','大纲视图','幻灯片浏览视图','幻灯片视图','A'),

(46,'sel-46',5,2,'启动Windows系统时，要想直接进入最小系统配置的安全模式，按（     ）。','1','F7键 ','F8键','F9键   ','F10键','B'),

(47,'sel-47',5,2,'全球信息网(WWW)的主要传输的通讯协议是（     ）。','1','FTP','HTTP','HTML','XMTP','B'),

(48,'sel-48',5,2,'人们把以（     ）为硬件基本部件的计算机称为第四代计算机。 ','1','大规模和超大规模集成电路　',' ROM和RAM ','小规模集成电路　','.磁带与磁盘 ','A'),

(49,'sel-49',5,2,'人们通常说的扩计算机的内存，指的是（     ）。','1','ROM ','CMOS','CPU  ','RAM','D'),

(50,'sel-50',5,2,'日常说的PC是指（     ）','1','计算机','个人计算机','单片计算机     ','小型计算机','B');

/*Table structure for table `t_stu_course` */

DROP TABLE IF EXISTS `t_stu_course`;

CREATE TABLE `t_stu_course` (
  `id` bigint(10) unsigned NOT NULL AUTO_INCREMENT,
  `stu_id` bigint(10) NOT NULL,
  `teac_course_id` bigint(10) NOT NULL,
  `course_type` varchar(10) NOT NULL DEFAULT '必修',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=85 DEFAULT CHARSET=utf8;

/*Data for the table `t_stu_course` */







/*Table structure for table `t_stu_score` */

DROP TABLE IF EXISTS `t_stu_score`;

CREATE TABLE `t_stu_score` (
  `id` bigint(10) unsigned NOT NULL AUTO_INCREMENT,
  `paper_id` bigint(10) NOT NULL,
  `stu_id` bigint(10) NOT NULL,
  `teac_course_id` bigint(10) NOT NULL,
  `selection_count` int(5) DEFAULT '0',
  `multi_selection_count` int(5) DEFAULT '0',
  `judge_count` int(5) DEFAULT '0',
  `fill_count` int(5) DEFAULT '0',
  `calculate_count` int(5) DEFAULT '0',
  `subject_count` int(5) DEFAULT '0',
  `test_time` int(5) NOT NULL DEFAULT '0',
  `score` int(5) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=74 DEFAULT CHARSET=utf8;

/*Data for the table `t_stu_score` */







/*Table structure for table `t_student` */

DROP TABLE IF EXISTS `t_student`;

CREATE TABLE `t_student` (
  `id` bigint(10) unsigned NOT NULL AUTO_INCREMENT,
  `stu_name` varchar(20) NOT NULL,
  `stu_num` varchar(20) NOT NULL,
  `stu_grade` varchar(20) NOT NULL,
  `stu_gender` varchar(4) NOT NULL,
  `stu_address` varchar(50) NOT NULL,
  `stu_class` varchar(20) NOT NULL,
  `stu_password` varchar(60) NOT NULL DEFAULT '$2a$10$rgsyROIoImw6btW7upK9H.24mAcxkC/Yei7GJxzD0FIEj0ldxqTUy',
  `stu_question` varchar(50) DEFAULT NULL,
  `stu_key` varchar(50) DEFAULT NULL,
  `stu_state` int(2) NOT NULL DEFAULT '1',
  `stu_phone` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=utf8;

/*Data for the table `t_student` */

insert into `onlinexam`.`t_student`(`id`,`stu_name`,`stu_num`,`stu_grade`,`stu_gender`,`stu_address`,`stu_class`,`stu_password`,`stu_question`,`stu_key`,`stu_state`,`stu_phone`) values (1,'Kaye Lin','1502230088','2015','男','江西省南昌市','15软件工程','$2a$10$rgsyROIoImw6btW7upK9H.24mAcxkC/Yei7GJxzD0FIEj0ldxqTUy','我的名字？','Kaye Lin',2,'15579857262');
insert into `onlinexam`.`t_student`(`id`,`stu_name`,`stu_num`,`stu_grade`,`stu_gender`,`stu_address`,`stu_class`,`stu_password`,`stu_question`,`stu_key`,`stu_state`,`stu_phone`) values (2,'Philip Morelock','1502210001','2015','女','未知','15数学应用','$2a$10$j/Gqt2IJOO7TXZLAEVyy4OJ41wdowg3IpLinYidmDOIRsFnCqPpYS','我的名字？','Philip Morelock',2,'15579857206');
insert into `onlinexam`.`t_student`(`id`,`stu_name`,`stu_num`,`stu_grade`,`stu_gender`,`stu_address`,`stu_class`,`stu_password`,`stu_question`,`stu_key`,`stu_state`,`stu_phone`) values (3,'Michael Phillips','1502200001','2015','男','江西省南昌市','15商务英语','$2a$10$rgsyROIoImw6btW7upK9H.24mAcxkC/Yei7GJxzD0FIEj0ldxqTUy','我的名字？','Michael Phillips',2,'15579857207');
insert into `onlinexam`.`t_student`(`id`,`stu_name`,`stu_num`,`stu_grade`,`stu_gender`,`stu_address`,`stu_class`,`stu_password`,`stu_question`,`stu_key`,`stu_state`,`stu_phone`) values (4,'Laker Zeng','1502230001','2015','男','江西省南昌市','15软件工程','$2a$10$rgsyROIoImw6btW7upK9H.24mAcxkC/Yei7GJxzD0FIEj0ldxqTUy','我的名字？','Laker Zeng',2,'15579857208');
insert into `onlinexam`.`t_student`(`id`,`stu_name`,`stu_num`,`stu_grade`,`stu_gender`,`stu_address`,`stu_class`,`stu_password`,`stu_question`,`stu_key`,`stu_state`,`stu_phone`) values (5,'Eric Hsu','1502230002','2015','男','江西省南昌市','15软件工程','$2a$10$rgsyROIoImw6btW7upK9H.24mAcxkC/Yei7GJxzD0FIEj0ldxqTUy','我的名字？','Eric Hsu',2,'15579857209');
insert into `onlinexam`.`t_student`(`id`,`stu_name`,`stu_num`,`stu_grade`,`stu_gender`,`stu_address`,`stu_class`,`stu_password`,`stu_question`,`stu_key`,`stu_state`,`stu_phone`) values (6,'Wendy Cheng','1502230003','2015','女','江西省南昌市','15软件工程','$2a$10$rgsyROIoImw6btW7upK9H.24mAcxkC/Yei7GJxzD0FIEj0ldxqTUy','我的名字？','Wendy Cheng',2,'15579857210');
insert into `onlinexam`.`t_student`(`id`,`stu_name`,`stu_num`,`stu_grade`,`stu_gender`,`stu_address`,`stu_class`,`stu_password`,`stu_question`,`stu_key`,`stu_state`,`stu_phone`) values (7,'Claire Dang','1502230004','2015','男','江西省南昌市','15软件工程','$2a$10$rgsyROIoImw6btW7upK9H.24mAcxkC/Yei7GJxzD0FIEj0ldxqTUy','我的名字？','Claire Dang',1,'15579857211');
insert into `onlinexam`.`t_student`(`id`,`stu_name`,`stu_num`,`stu_grade`,`stu_gender`,`stu_address`,`stu_class`,`stu_password`,`stu_question`,`stu_key`,`stu_state`,`stu_phone`) values (8,'Tethys Xu','1502230005','2015','男','江西省赣州市','15软件工程','$2a$10$nt.CzYZkB7AY.vvJT.pe8uJTF/xFneIKNwCzahnaqMUMM5MOpLlL6','我的名字？','Tethys Xu',2,'15579857212');
insert into `onlinexam`.`t_student`(`id`,`stu_name`,`stu_num`,`stu_grade`,`stu_gender`,`stu_address`,`stu_class`,`stu_password`,`stu_question`,`stu_key`,`stu_state`,`stu_phone`) values (9,'Sandy Shao','1502230006','2015','男','江西省南昌市','15软件工程','$2a$10$rgsyROIoImw6btW7upK9H.24mAcxkC/Yei7GJxzD0FIEj0ldxqTUy','我的名字？','Sandy Shao',2,'15579857213');
insert into `onlinexam`.`t_student`(`id`,`stu_name`,`stu_num`,`stu_grade`,`stu_gender`,`stu_address`,`stu_class`,`stu_password`,`stu_question`,`stu_key`,`stu_state`,`stu_phone`) values (10,'Lihua Chen','1502230007','2015','男','江西省南昌市','15软件工程','$2a$10$rgsyROIoImw6btW7upK9H.24mAcxkC/Yei7GJxzD0FIEj0ldxqTUy','我的名字？','Lihua Chen',2,'15579857214');
insert into `onlinexam`.`t_student`(`id`,`stu_name`,`stu_num`,`stu_grade`,`stu_gender`,`stu_address`,`stu_class`,`stu_password`,`stu_question`,`stu_key`,`stu_state`,`stu_phone`) values (11,'Harry Wang','1502230008','2015','男','江西省南昌市','15软件工程','$2a$10$rgsyROIoImw6btW7upK9H.24mAcxkC/Yei7GJxzD0FIEj0ldxqTUy','我的名字？','Harry Wang',2,'15579857215');
insert into `onlinexam`.`t_student`(`id`,`stu_name`,`stu_num`,`stu_grade`,`stu_gender`,`stu_address`,`stu_class`,`stu_password`,`stu_question`,`stu_key`,`stu_state`,`stu_phone`) values (12,'Erin Gong','1502230009','2015','男','江西省南昌市','15软件工程','$2a$10$rgsyROIoImw6btW7upK9H.24mAcxkC/Yei7GJxzD0FIEj0ldxqTUy','我的名字？','Erin Gong',2,'15579857216');
insert into `onlinexam`.`t_student`(`id`,`stu_name`,`stu_num`,`stu_grade`,`stu_gender`,`stu_address`,`stu_class`,`stu_password`,`stu_question`,`stu_key`,`stu_state`,`stu_phone`) values (13,'Norbert Xie','1502230010','2015','男','江西省南昌市','15软件工程','$2a$10$rgsyROIoImw6btW7upK9H.24mAcxkC/Yei7GJxzD0FIEj0ldxqTUy','我的名字？','Norbert Xie',1,'15579857217');
insert into `onlinexam`.`t_student`(`id`,`stu_name`,`stu_num`,`stu_grade`,`stu_gender`,`stu_address`,`stu_class`,`stu_password`,`stu_question`,`stu_key`,`stu_state`,`stu_phone`) values (14,'Jen Jiang','1502230011','2015','男','江西省南昌市','15软件工程','$2a$10$rgsyROIoImw6btW7upK9H.24mAcxkC/Yei7GJxzD0FIEj0ldxqTUy','我的名字？','Jen Jiang',1,'15579857218');
insert into `onlinexam`.`t_student`(`id`,`stu_name`,`stu_num`,`stu_grade`,`stu_gender`,`stu_address`,`stu_class`,`stu_password`,`stu_question`,`stu_key`,`stu_state`,`stu_phone`) values (15,'Beverly Xu','1502230012','2015','男','江西省南昌市','15软件工程','$2a$10$rgsyROIoImw6btW7upK9H.24mAcxkC/Yei7GJxzD0FIEj0ldxqTUy','我的名字？','Beverly Xu',2,'15579857219');
insert into `onlinexam`.`t_student`(`id`,`stu_name`,`stu_num`,`stu_grade`,`stu_gender`,`stu_address`,`stu_class`,`stu_password`,`stu_question`,`stu_key`,`stu_state`,`stu_phone`) values (16,'Nick Wu','1502230013','2015','男','江西省南昌市','15软件工程','$2a$10$rgsyROIoImw6btW7upK9H.24mAcxkC/Yei7GJxzD0FIEj0ldxqTUy','我的名字？','Nick Wu',2,'15579857220');
insert into `onlinexam`.`t_student`(`id`,`stu_name`,`stu_num`,`stu_grade`,`stu_gender`,`stu_address`,`stu_class`,`stu_password`,`stu_question`,`stu_key`,`stu_state`,`stu_phone`) values (17,'Emma Yang','1502230014','2015','男','江西省南昌市','15软件工程','$2a$10$rgsyROIoImw6btW7upK9H.24mAcxkC/Yei7GJxzD0FIEj0ldxqTUy','我的名字？','Emma Yang',2,'15579857221');
insert into `onlinexam`.`t_student`(`id`,`stu_name`,`stu_num`,`stu_grade`,`stu_gender`,`stu_address`,`stu_class`,`stu_password`,`stu_question`,`stu_key`,`stu_state`,`stu_phone`) values (18,'Frances Hong','1502230015','2015','男','江西省南昌市','15软件工程','$2a$10$rgsyROIoImw6btW7upK9H.24mAcxkC/Yei7GJxzD0FIEj0ldxqTUy','我的名字？','Frances Hong',2,'15579857222');
insert into `onlinexam`.`t_student`(`id`,`stu_name`,`stu_num`,`stu_grade`,`stu_gender`,`stu_address`,`stu_class`,`stu_password`,`stu_question`,`stu_key`,`stu_state`,`stu_phone`) values (19,'Chen Zhang','1502230016','2015','男','江西省南昌市','15软件工程','$2a$10$rgsyROIoImw6btW7upK9H.24mAcxkC/Yei7GJxzD0FIEj0ldxqTUy','我的名字？','Chen Zhang',0,'15579857223');
insert into `onlinexam`.`t_student`(`id`,`stu_name`,`stu_num`,`stu_grade`,`stu_gender`,`stu_address`,`stu_class`,`stu_password`,`stu_question`,`stu_key`,`stu_state`,`stu_phone`) values (20,'Sophie Chen','1502230017','2015','男','江西省南昌市','15软件工程','$2a$10$rgsyROIoImw6btW7upK9H.24mAcxkC/Yei7GJxzD0FIEj0ldxqTUy','我的名字？','Sophie Chen',2,'15579857224');
insert into `onlinexam`.`t_student`(`id`,`stu_name`,`stu_num`,`stu_grade`,`stu_gender`,`stu_address`,`stu_class`,`stu_password`,`stu_question`,`stu_key`,`stu_state`,`stu_phone`) values (21,'William Lan','1502230018','2015','男','江西省南昌市','15软件工程','$2a$10$rgsyROIoImw6btW7upK9H.24mAcxkC/Yei7GJxzD0FIEj0ldxqTUy','我的名字？','William Lan',2,'15579857225');
insert into `onlinexam`.`t_student`(`id`,`stu_name`,`stu_num`,`stu_grade`,`stu_gender`,`stu_address`,`stu_class`,`stu_password`,`stu_question`,`stu_key`,`stu_state`,`stu_phone`) values (22,'Phenia Zheng','1502230019','2015','男','江西省南昌市','15软件工程','$2a$10$rgsyROIoImw6btW7upK9H.24mAcxkC/Yei7GJxzD0FIEj0ldxqTUy','我的名字？','Phenia Zheng',2,'15579857226');
insert into `onlinexam`.`t_student`(`id`,`stu_name`,`stu_num`,`stu_grade`,`stu_gender`,`stu_address`,`stu_class`,`stu_password`,`stu_question`,`stu_key`,`stu_state`,`stu_phone`) values (23,'Jolina Chen','1502230020','2015','男','江西省南昌市','15软件工程','$2a$10$rgsyROIoImw6btW7upK9H.24mAcxkC/Yei7GJxzD0FIEj0ldxqTUy','我的名字？','Jolina Chen',0,'15579857227');
insert into `onlinexam`.`t_student`(`id`,`stu_name`,`stu_num`,`stu_grade`,`stu_gender`,`stu_address`,`stu_class`,`stu_password`,`stu_question`,`stu_key`,`stu_state`,`stu_phone`) values (24,'Yoyo Wang','1502230021','2015','女','江西省南昌市','15软件工程','$2a$10$rgsyROIoImw6btW7upK9H.24mAcxkC/Yei7GJxzD0FIEj0ldxqTUy','我的名字？','Yoyo Wang',2,'15579857228');
insert into `onlinexam`.`t_student`(`id`,`stu_name`,`stu_num`,`stu_grade`,`stu_gender`,`stu_address`,`stu_class`,`stu_password`,`stu_question`,`stu_key`,`stu_state`,`stu_phone`) values (25,'Sunny Lin','1502230022','2015','女','江西省南昌市','15软件工程','$2a$10$rgsyROIoImw6btW7upK9H.24mAcxkC/Yei7GJxzD0FIEj0ldxqTUy','我的名字？','Sunny Lin',2,'15579857229');
insert into `onlinexam`.`t_student`(`id`,`stu_name`,`stu_num`,`stu_grade`,`stu_gender`,`stu_address`,`stu_class`,`stu_password`,`stu_question`,`stu_key`,`stu_state`,`stu_phone`) values (26,'Christy Liu','1502230023','2015','女','江西省南昌市','15软件工程','$2a$10$rgsyROIoImw6btW7upK9H.24mAcxkC/Yei7GJxzD0FIEj0ldxqTUy','我的名字？','Christy Liu',2,'15579857230');
insert into `onlinexam`.`t_student`(`id`,`stu_name`,`stu_num`,`stu_grade`,`stu_gender`,`stu_address`,`stu_class`,`stu_password`,`stu_question`,`stu_key`,`stu_state`,`stu_phone`) values (27,'Jenna Tang','1502230024','2015','男','江西省南昌市','15软件工程','$2a$10$rgsyROIoImw6btW7upK9H.24mAcxkC/Yei7GJxzD0FIEj0ldxqTUy','我的名字？','Jenna Tang',2,'15579857231');
insert into `onlinexam`.`t_student`(`id`,`stu_name`,`stu_num`,`stu_grade`,`stu_gender`,`stu_address`,`stu_class`,`stu_password`,`stu_question`,`stu_key`,`stu_state`,`stu_phone`) values (28,'Elaine Yang','1502230025','2015','男','江西省南昌市','15软件工程','$2a$10$rgsyROIoImw6btW7upK9H.24mAcxkC/Yei7GJxzD0FIEj0ldxqTUy','我的名字？','Elaine Yang',2,'15579857232');
insert into `onlinexam`.`t_student`(`id`,`stu_name`,`stu_num`,`stu_grade`,`stu_gender`,`stu_address`,`stu_class`,`stu_password`,`stu_question`,`stu_key`,`stu_state`,`stu_phone`) values (29,'Ellie Lin','1502230026','2015','男','江西省南昌市','15软件工程','$2a$10$rgsyROIoImw6btW7upK9H.24mAcxkC/Yei7GJxzD0FIEj0ldxqTUy','我的名字？','Ellie Lin',2,'15579857233');
insert into `onlinexam`.`t_student`(`id`,`stu_name`,`stu_num`,`stu_grade`,`stu_gender`,`stu_address`,`stu_class`,`stu_password`,`stu_question`,`stu_key`,`stu_state`,`stu_phone`) values (30,'Zoe Zhou','1502230027','2015','男','江西省南昌市','15软件工程','$2a$10$rgsyROIoImw6btW7upK9H.24mAcxkC/Yei7GJxzD0FIEj0ldxqTUy','我的名字？','Zoe Zhou',2,'15579857234');
insert into `onlinexam`.`t_student`(`id`,`stu_name`,`stu_num`,`stu_grade`,`stu_gender`,`stu_address`,`stu_class`,`stu_password`,`stu_question`,`stu_key`,`stu_state`,`stu_phone`) values (31,'Sophie Chen','1502230028','2015','男','江西省南昌市','15软件工程','$2a$10$rgsyROIoImw6btW7upK9H.24mAcxkC/Yei7GJxzD0FIEj0ldxqTUy','我的名字？','Sophie Chen',2,'15579857235');
insert into `onlinexam`.`t_student`(`id`,`stu_name`,`stu_num`,`stu_grade`,`stu_gender`,`stu_address`,`stu_class`,`stu_password`,`stu_question`,`stu_key`,`stu_state`,`stu_phone`) values (32,'Stacey Chen','1502230029','2015','男','江西省南昌市','15软件工程','$2a$10$rgsyROIoImw6btW7upK9H.24mAcxkC/Yei7GJxzD0FIEj0ldxqTUy','我的名字？','Stacey Chen',2,'15579857236');
insert into `onlinexam`.`t_student`(`id`,`stu_name`,`stu_num`,`stu_grade`,`stu_gender`,`stu_address`,`stu_class`,`stu_password`,`stu_question`,`stu_key`,`stu_state`,`stu_phone`) values (33,'Enci Xie','1502230030','2015','女','江西省南昌市','15软件工程','$2a$10$rgsyROIoImw6btW7upK9H.24mAcxkC/Yei7GJxzD0FIEj0ldxqTUy','我的名字？','Enci Xie',1,'15579857237');
insert into `onlinexam`.`t_student`(`id`,`stu_name`,`stu_num`,`stu_grade`,`stu_gender`,`stu_address`,`stu_class`,`stu_password`,`stu_question`,`stu_key`,`stu_state`,`stu_phone`) values (34,'Una Tang','1502230031','2015','女','江西省南昌市','15软件工程','$2a$10$rgsyROIoImw6btW7upK9H.24mAcxkC/Yei7GJxzD0FIEj0ldxqTUy','我的名字？','Una Tang',2,'15579857238');
insert into `onlinexam`.`t_student`(`id`,`stu_name`,`stu_num`,`stu_grade`,`stu_gender`,`stu_address`,`stu_class`,`stu_password`,`stu_question`,`stu_key`,`stu_state`,`stu_phone`) values (35,'Athena Wu','1502230032','2015','女','江西省南昌市','15软件工程','$2a$10$rgsyROIoImw6btW7upK9H.24mAcxkC/Yei7GJxzD0FIEj0ldxqTUy','我的名字？','Athena Wu',2,'15579857239');
insert into `onlinexam`.`t_student`(`id`,`stu_name`,`stu_num`,`stu_grade`,`stu_gender`,`stu_address`,`stu_class`,`stu_password`,`stu_question`,`stu_key`,`stu_state`,`stu_phone`) values (36,'Bunny Guo','1502230033','2015','男','江西省南昌市','15软件工程','$2a$10$rgsyROIoImw6btW7upK9H.24mAcxkC/Yei7GJxzD0FIEj0ldxqTUy','我的名字？','Bunny Guo',2,'15579857240');
insert into `onlinexam`.`t_student`(`id`,`stu_name`,`stu_num`,`stu_grade`,`stu_gender`,`stu_address`,`stu_class`,`stu_password`,`stu_question`,`stu_key`,`stu_state`,`stu_phone`) values (37,'Olina Shen','1502230034','2015','女','江西省南昌市','15软件工程','$2a$10$rgsyROIoImw6btW7upK9H.24mAcxkC/Yei7GJxzD0FIEj0ldxqTUy','我的名字？','Olina Shen',2,'15579857241');
insert into `onlinexam`.`t_student`(`id`,`stu_name`,`stu_num`,`stu_grade`,`stu_gender`,`stu_address`,`stu_class`,`stu_password`,`stu_question`,`stu_key`,`stu_state`,`stu_phone`) values (38,'Cora Yan','1502230035','2015','女','江西省南昌市','15软件工程','$2a$10$rgsyROIoImw6btW7upK9H.24mAcxkC/Yei7GJxzD0FIEj0ldxqTUy','我的名字？','Cora Yan',2,'15579857242');
insert into `onlinexam`.`t_student`(`id`,`stu_name`,`stu_num`,`stu_grade`,`stu_gender`,`stu_address`,`stu_class`,`stu_password`,`stu_question`,`stu_key`,`stu_state`,`stu_phone`) values (39,'Roy Luo','1502230036','2015','男','江西省南昌市','15软件工程','$2a$10$rgsyROIoImw6btW7upK9H.24mAcxkC/Yei7GJxzD0FIEj0ldxqTUy','我的名字？','Roy Luo',2,'15579857243');
insert into `onlinexam`.`t_student`(`id`,`stu_name`,`stu_num`,`stu_grade`,`stu_gender`,`stu_address`,`stu_class`,`stu_password`,`stu_question`,`stu_key`,`stu_state`,`stu_phone`) values (40,'Poppy Hu','1502230037','2015','女','江西省南昌市','15软件工程','$2a$10$rgsyROIoImw6btW7upK9H.24mAcxkC/Yei7GJxzD0FIEj0ldxqTUy','我的名字？','Poppy Hu',2,'15579857244');
insert into `onlinexam`.`t_student`(`id`,`stu_name`,`stu_num`,`stu_grade`,`stu_gender`,`stu_address`,`stu_class`,`stu_password`,`stu_question`,`stu_key`,`stu_state`,`stu_phone`) values (41,'Jamie Chen','1502230038','2015','女','江西省南昌市','15软件工程','$2a$10$rgsyROIoImw6btW7upK9H.24mAcxkC/Yei7GJxzD0FIEj0ldxqTUy','我的名字？','Jamie Chen',2,'15579857245');
insert into `onlinexam`.`t_student`(`id`,`stu_name`,`stu_num`,`stu_grade`,`stu_gender`,`stu_address`,`stu_class`,`stu_password`,`stu_question`,`stu_key`,`stu_state`,`stu_phone`) values (42,'Kiki Ke','1502230039','2015','女','江西省南昌市','15软件工程','$2a$10$rgsyROIoImw6btW7upK9H.24mAcxkC/Yei7GJxzD0FIEj0ldxqTUy','我的名字？','Kiki Ke',2,'15579857246');
insert into `onlinexam`.`t_student`(`id`,`stu_name`,`stu_num`,`stu_grade`,`stu_gender`,`stu_address`,`stu_class`,`stu_password`,`stu_question`,`stu_key`,`stu_state`,`stu_phone`) values (43,'Hans Wang','1502230040','2015','男','江西省南昌市','15软件工程','$2a$10$rgsyROIoImw6btW7upK9H.24mAcxkC/Yei7GJxzD0FIEj0ldxqTUy','我的名字？','Hans Wang',2,'15579857247');
insert into `onlinexam`.`t_student`(`id`,`stu_name`,`stu_num`,`stu_grade`,`stu_gender`,`stu_address`,`stu_class`,`stu_password`,`stu_question`,`stu_key`,`stu_state`,`stu_phone`) values (44,'Anna Wang','1502230041','2015','女','江西省南昌市','15软件工程','$2a$10$rgsyROIoImw6btW7upK9H.24mAcxkC/Yei7GJxzD0FIEj0ldxqTUy','我的名字？','Anna Wang',2,'15579857248');
insert into `onlinexam`.`t_student`(`id`,`stu_name`,`stu_num`,`stu_grade`,`stu_gender`,`stu_address`,`stu_class`,`stu_password`,`stu_question`,`stu_key`,`stu_state`,`stu_phone`) values (45,'Jane Wu','1502230042','2015','男','江西省南昌市','15软件工程','$2a$10$rgsyROIoImw6btW7upK9H.24mAcxkC/Yei7GJxzD0FIEj0ldxqTUy','我的名字？','Jane Wu',0,'15579857249');
insert into `onlinexam`.`t_student`(`id`,`stu_name`,`stu_num`,`stu_grade`,`stu_gender`,`stu_address`,`stu_class`,`stu_password`,`stu_question`,`stu_key`,`stu_state`,`stu_phone`) values (46,'Manning Chen','1502230043','2015','男','江西省南昌市','15软件工程','$2a$10$rgsyROIoImw6btW7upK9H.24mAcxkC/Yei7GJxzD0FIEj0ldxqTUy','我的名字？','Manning Chen',2,'15579857250');
insert into `onlinexam`.`t_student`(`id`,`stu_name`,`stu_num`,`stu_grade`,`stu_gender`,`stu_address`,`stu_class`,`stu_password`,`stu_question`,`stu_key`,`stu_state`,`stu_phone`) values (47,'Ronny Li','1502230044','2015','男','江西省南昌市','15软件工程','$2a$10$rgsyROIoImw6btW7upK9H.24mAcxkC/Yei7GJxzD0FIEj0ldxqTUy','我的名字？','Ronny Li',2,'15579857251');
insert into `onlinexam`.`t_student`(`id`,`stu_name`,`stu_num`,`stu_grade`,`stu_gender`,`stu_address`,`stu_class`,`stu_password`,`stu_question`,`stu_key`,`stu_state`,`stu_phone`) values (48,'Ellie Chen','1502230045','2015','男','江西省南昌市','15软件工程','$2a$10$rgsyROIoImw6btW7upK9H.24mAcxkC/Yei7GJxzD0FIEj0ldxqTUy','我的名字？','Ellie Chen',2,'15579857252');
insert into `onlinexam`.`t_student`(`id`,`stu_name`,`stu_num`,`stu_grade`,`stu_gender`,`stu_address`,`stu_class`,`stu_password`,`stu_question`,`stu_key`,`stu_state`,`stu_phone`) values (49,'Jean Chen','1502230046','2015','男','江西省南昌市','15软件工程','$2a$10$rgsyROIoImw6btW7upK9H.24mAcxkC/Yei7GJxzD0FIEj0ldxqTUy','我的名字？','Jean Chen',2,'15579857253');
insert into `onlinexam`.`t_student`(`id`,`stu_name`,`stu_num`,`stu_grade`,`stu_gender`,`stu_address`,`stu_class`,`stu_password`,`stu_question`,`stu_key`,`stu_state`,`stu_phone`) values (50,'Michonne Liao','1502230047','2015','男','江西省南昌市','15软件工程','$2a$10$rgsyROIoImw6btW7upK9H.24mAcxkC/Yei7GJxzD0FIEj0ldxqTUy','我的名字？','Michonne Liao',2,'15579857254');
insert into `onlinexam`.`t_student`(`id`,`stu_name`,`stu_num`,`stu_grade`,`stu_gender`,`stu_address`,`stu_class`,`stu_password`,`stu_question`,`stu_key`,`stu_state`,`stu_phone`) values (51,'Joel Zhu ','1502230048','2015','男','江西省南昌市','15软件工程','$2a$10$rgsyROIoImw6btW7upK9H.24mAcxkC/Yei7GJxzD0FIEj0ldxqTUy','我的名字？','Joel Zhu ',2,'15579857255');
insert into `onlinexam`.`t_student`(`id`,`stu_name`,`stu_num`,`stu_grade`,`stu_gender`,`stu_address`,`stu_class`,`stu_password`,`stu_question`,`stu_key`,`stu_state`,`stu_phone`) values (52,'Jared Lin','1502230049','2015','男','江西省南昌市','15软件工程','$2a$10$rgsyROIoImw6btW7upK9H.24mAcxkC/Yei7GJxzD0FIEj0ldxqTUy','我的名字？','Jared Lin',2,'15579857256');
insert into `onlinexam`.`t_student`(`id`,`stu_name`,`stu_num`,`stu_grade`,`stu_gender`,`stu_address`,`stu_class`,`stu_password`,`stu_question`,`stu_key`,`stu_state`,`stu_phone`) values (53,'Shining Hong','1502230050','2015','男','江西省南昌市','15软件工程','$2a$10$rgsyROIoImw6btW7upK9H.24mAcxkC/Yei7GJxzD0FIEj0ldxqTUy','我的名字？','Shining Hong',1,'15579857257');
insert into `onlinexam`.`t_student`(`id`,`stu_name`,`stu_num`,`stu_grade`,`stu_gender`,`stu_address`,`stu_class`,`stu_password`,`stu_question`,`stu_key`,`stu_state`,`stu_phone`) values (54,'Sweeney Xu','1502230051','2015','女','江西省南昌市','15软件工程','$2a$10$rgsyROIoImw6btW7upK9H.24mAcxkC/Yei7GJxzD0FIEj0ldxqTUy','我的名字？','Sweeney Xu',2,'15579857258');
insert into `onlinexam`.`t_student`(`id`,`stu_name`,`stu_num`,`stu_grade`,`stu_gender`,`stu_address`,`stu_class`,`stu_password`,`stu_question`,`stu_key`,`stu_state`,`stu_phone`) values (55,'Barrie Wu','1502230052','2015','男','江西省南昌市','15软件工程','$2a$10$rgsyROIoImw6btW7upK9H.24mAcxkC/Yei7GJxzD0FIEj0ldxqTUy','我的名字？','Barrie Wu',2,'15579857259');
insert into `onlinexam`.`t_student`(`id`,`stu_name`,`stu_num`,`stu_grade`,`stu_gender`,`stu_address`,`stu_class`,`stu_password`,`stu_question`,`stu_key`,`stu_state`,`stu_phone`) values (56,'Raymond Zhu','1502230053','2015','男','江西省南昌市','15软件工程','$2a$10$rgsyROIoImw6btW7upK9H.24mAcxkC/Yei7GJxzD0FIEj0ldxqTUy','我的名字？','Raymond Zhu',2,'15579857260');
insert into `onlinexam`.`t_student`(`id`,`stu_name`,`stu_num`,`stu_grade`,`stu_gender`,`stu_address`,`stu_class`,`stu_password`,`stu_question`,`stu_key`,`stu_state`,`stu_phone`) values (57,'Hector Deng','1502230054','2015','男','江西省南昌市','15软件工程','$2a$10$rgsyROIoImw6btW7upK9H.24mAcxkC/Yei7GJxzD0FIEj0ldxqTUy','我的名字？','Hector Deng',2,'15579857261');


/*Table structure for table `t_subject` */

DROP TABLE IF EXISTS `t_subject`;

CREATE TABLE `t_subject` (
  `id` bigint(10) unsigned NOT NULL AUTO_INCREMENT,
  `subject_id` varchar(20) NOT NULL,
  `teac_course_id` bigint(10) NOT NULL,
  `exam_id` int(3) NOT NULL DEFAULT '6',
  `title` varchar(200) NOT NULL,
  `dificult` varchar(10) NOT NULL,
  `answer` varchar(500) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

/*Data for the table `t_subject` */

insert  into `t_subject`(`id`,`subject_id`,`teac_course_id`,`exam_id`,`title`,`dificult`,`answer`) values 

(1,'sub-1',1,6,'请写出第一章第三节的主要知识点','1','略'),

(2,'sub-2',1,6,'请谈谈你对MySQL数据库的理解','1','略'),

(3,'sub-3',1,6,'请简述分布式的特点','1','略'),

(4,'sub-4',1,6,'请简述电脑病毒的主要危害','1','略'),

(5,'sub-5',1,6,'word 2010中改变字体的操作步骤有哪些？','1','略'),

(6,'sub-6',4,6,'计算机联网的主要目的是什么？','1','略'),

(7,'sub-7',4,6,'请比较内存和外存特点','1','略'),

(8,'sub-8',4,6,'请谈谈你对计算机网络的定义理解','1','略'),

(9,'sub-9',4,6,'请列出至少三种存储器，并简述它们的特点','1','略'),

(10,'sub-91',4,6,'“床前明月光”出自李白的___________。','1','《静夜思》'),

(11,'sub-11',5,6,'“床前明月光”出自李白的___。','1','《静夜思》'),

(12,'sub-12',5,6,'《荷塘月色》是谁的作品？','1','朱自清'),

(13,'sub-13',5,6,'121-120=','1','1');

(14,'sub-14',5,6,'请简述分布式的特点','1','略'),

(15,'sub-15',5,6,'请简述电脑病毒的主要危害','1','略'),

/*Table structure for table `t_teac_course` */

DROP TABLE IF EXISTS `t_teac_course`;

CREATE TABLE `t_teac_course` (
  `id` bigint(10) unsigned NOT NULL AUTO_INCREMENT,
  `teac_id` bigint(10) NOT NULL,
  `course_id` bigint(10) NOT NULL,
  `start_time` datetime NOT NULL,
  `end_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

/*Data for the table `t_teac_course` */

insert  into `t_teac_course`(`id`,`teac_id`,`course_id`,`start_time`,`end_time`) values 

(1,3,5,'2018-09-01 00:00:00','2018-12-07 00:00:00'),

(2,1,1,'2018-09-01 00:00:00','2018-09-06 00:00:00'),

(3,4,2,'2018-09-04 00:00:00','2018-11-25 00:00:00'),

(4,3,6,'2018-09-01 00:00:00','2018-10-04 00:00:00'),

(5,3,3,'2018-09-12 00:00:00','2018-09-19 00:00:00'),

(6,1,7,'2018-10-02 00:00:00','2018-10-19 00:00:00'),

(7,1,4,'2018-10-01 10:26:15','2018-10-31 10:26:19');

/*Table structure for table `t_teacher` */

DROP TABLE IF EXISTS `t_teacher`;

CREATE TABLE `t_teacher` (
  `id` bigint(10) unsigned NOT NULL AUTO_INCREMENT,
  `teac_worknum` varchar(20) NOT NULL,
  `teac_phone` varchar(20) NOT NULL,
  `teac_gender` varchar(4) NOT NULL,
  `teac_name` varchar(20) NOT NULL,
  `teac_state` int(2) NOT NULL DEFAULT '1',
  `teac_birth` date NOT NULL,
  `teac_question` varchar(50) DEFAULT NULL,
  `teac_key` varchar(50) DEFAULT NULL,
  `teac_password` varchar(60) NOT NULL DEFAULT '$10$rgsyROIoImw6btW7upK9H.24mAcxkC/Yei7GJxzD0FIEj0ldxqTUy',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

/*Data for the table `t_teacher` */


insert into `onlinexam`.`t_teacher`(`id`,`teac_worknum`,`teac_phone`,`teac_gender`,`teac_name`,`teac_state`,`teac_birth`,`teac_question`,`teac_key`,`teac_password`) values (1,'t444','13698075567','男','张三',2,'1997-01-02 00:00:00','我的生日？','0102','$2a$10$rgsyROIoImw6btW7upK9H.24mAcxkC/Yei7GJxzD0FIEj0ldxqTUy');
insert into `onlinexam`.`t_teacher`(`id`,`teac_worknum`,`teac_phone`,`teac_gender`,`teac_name`,`teac_state`,`teac_birth`,`teac_question`,`teac_key`,`teac_password`) values (2,'t1111','13698075524','女','丽丽',2,'1989-06-04 00:00:00','我的生日？','0604','$2a$10$rgsyROIoImw6btW7upK9H.24mAcxkC/Yei7GJxzD0FIEj0ldxqTUy');
insert into `onlinexam`.`t_teacher`(`id`,`teac_worknum`,`teac_phone`,`teac_gender`,`teac_name`,`teac_state`,`teac_birth`,`teac_question`,`teac_key`,`teac_password`) values (3,'t110','15572256802','男','陈涛',2,'1994-06-15 00:00:00','我的工号？','t110','$2a$10$j5gmJjOUfLzWfpa4sxTxe..c3gaaMpxIgajhowK47I/7.CusJqMme');
insert into `onlinexam`.`t_teacher`(`id`,`teac_worknum`,`teac_phone`,`teac_gender`,`teac_name`,`teac_state`,`teac_birth`,`teac_question`,`teac_key`,`teac_password`) values (4,'t112','13698075568','女','刘婷',2,'1994-09-06 00:00:00','我的名字？','刘婷','$2a$10$rgsyROIoImw6btW7upK9H.24mAcxkC/Yei7GJxzD0FIEj0ldxqTUy');
insert into `onlinexam`.`t_teacher`(`id`,`teac_worknum`,`teac_phone`,`teac_gender`,`teac_name`,`teac_state`,`teac_birth`,`teac_question`,`teac_key`,`teac_password`) values (5,'t25','13698075512','男','黄庭经',0,'1992-08-02 00:00:00','我的手机号？','13698075512','$2a$10$rgsyROIoImw6btW7upK9H.24mAcxkC/Yei7GJxzD0FIEj0ldxqTUy');
insert into `onlinexam`.`t_teacher`(`id`,`teac_worknum`,`teac_phone`,`teac_gender`,`teac_name`,`teac_state`,`teac_birth`,`teac_question`,`teac_key`,`teac_password`) values (6,'t001','13698075535','女','李婷婷',2,'1995-09-05 00:00:00','我的姓名？','李婷婷','$2a$10$rgsyROIoImw6btW7upK9H.24mAcxkC/Yei7GJxzD0FIEj0ldxqTUy');
insert into `onlinexam`.`t_teacher`(`id`,`teac_worknum`,`teac_phone`,`teac_gender`,`teac_name`,`teac_state`,`teac_birth`,`teac_question`,`teac_key`,`teac_password`) values (7,'t1212','13657842576','男','郑明',1,'1995-10-03 00:00:00',null,null,'$2a$10$rgsyROIoImw6btW7upK9H.24mAcxkC/Yei7GJxzD0FIEj0ldxqTUy');
insert into `onlinexam`.`t_teacher`(`id`,`teac_worknum`,`teac_phone`,`teac_gender`,`teac_name`,`teac_state`,`teac_birth`,`teac_question`,`teac_key`,`teac_password`) values (8,'t10010','13698075542','男','玫玫',2,'2019-03-28 00:00:00','1+9=','10','$2a$10$3Hn7m/DGJpAKWTzs14GM2.AHYzGqx2sh.cVNcc1dprMUu9gcJBoqC');




/*Table structure for table `t_chat` */

DROP TABLE IF EXISTS `t_chat`;
CREATE TABLE `t_chat` (
  `id` bigint(10) unsigned NOT NULL AUTO_INCREMENT,
  `send_id` bigint(10) DEFAULT NULL,
  `send_name` varchar(20) DEFAULT NULL,
  `send_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `message` varchar(300) DEFAULT NULL,
  `status` int(2) DEFAULT NULL,
  `receive_ids` varchar(200) DEFAULT NULL,
  `attach` varchar(20) DEFAULT NULL,
  `tcId` bigint(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;


-- 参考  https://blog.csdn.net/u013991521/article/details/80872899
-- 查询今天的记录
-- select c.id,c.send_id,c.send_name,c.send_time,c.message,c.attach FROM t_chat c where to_days(c.send_time) = to_days(now()) and c.tcId = 1 order by c.send_time;
-- 查询最近3天的记录
-- select c.id,c.send_id,c.send_name,c.send_time,c.message,c.attach FROM t_chat c where c.tcId = 1 and to_days(NOW()) - TO_DAYS(c.send_time) < 3 order by c.send_time;
-- 查询最近7天的记录
-- select c.id,c.send_id,c.send_name,c.send_time,c.message,c.attach FROM t_chat c where c.tcId = 1 and date_sub(CURDATE(),INTERVAL 7 DAY) < DATE(c.send_time) order by c.send_time;
-- 查询最近一周的记录
-- select c.id,c.send_id,c.send_name,c.send_time,c.message,c.attach FROM t_chat c where c.tcId = 1 and YEARWEEK(date_format(c.send_time,'%Y-%m-%d')) = YEARWEEK(now()) order by c.send_time;

-- 查询最近两周的记录
-- select c.id,c.send_id,c.send_name,c.send_time,c.message,c.attach FROM t_chat c where c.tcId = 1 and YEARWEEK(date_format(c.send_time,'%Y-%m-%d')) >= YEARWEEK(now())-1 order by c.send_time;
-- 查询本月的记录
-- select c.id,c.send_id,c.send_name,c.send_time,c.message,c.attach FROM t_chat c where c.tcId = 1 and DATE_FORMAT( c.send_time, '%Y%m' ) = DATE_FORMAT( CURDATE() , '%Y%m' ) order by c.send_time;
-- 查询最近三个月的记录
-- select c.id,c.send_id,c.send_name,c.send_time,c.message,c.attach FROM t_chat c where c.tcId = 1 and  PERIOD_DIFF( date_format( now( ) , '%Y%m' ) , date_format( c.send_time, '%Y%m' ) )  <= 2 order by c.send_time;
-- 查询今年的记录
-- select c.id,c.send_id,c.send_name,c.send_time,c.message,c.attach FROM t_chat c where c.tcId = 1 and YEAR(c.send_time)=YEAR(NOW()) order by c.send_time;
-- 查询所有记录
-- select c.id,c.send_id,c.send_name,c.send_time,c.message,c.attach FROM t_chat c where c.tcId = 1 order by c.send_time;

-- Remember-me
create table persistent_logins (username varchar(64) not null, series varchar(64) primary key, token varchar(64) not null, last_used timestamp not null);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
