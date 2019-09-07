-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Sep 06, 2019 at 06:39 PM
-- Server version: 10.3.17-MariaDB-cll-lve
-- PHP Version: 7.2.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `downund2_RMIT`
--

-- --------------------------------------------------------

--
-- Table structure for table `Project`
--

CREATE TABLE `Project` (
  `ProID` varchar(255) NOT NULL,
  `Desc` varchar(255) DEFAULT NULL,
  `Requirements` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `Project`
--

INSERT INTO `Project` (`ProID`, `Desc`, `Requirements`) VALUES
('1', 'Vel.', 'Delectus pariatur qui sint ut consequatur incidunt consectetur reprehenderit. Aut molestias facere nulla et non. Error eius beatae ea deleniti odio.'),
('10', 'Aut.', 'Dignissimos molestiae nihil beatae praesentium. In quasi consequatur assumenda sapiente nostrum.'),
('100', 'Animi.', 'Voluptate aliquid nisi architecto incidunt omnis. Recusandae quia est numquam ducimus. Inventore qui commodi voluptates vero in. Et fuga nostrum ut sunt et.'),
('101', 'Ipsam.', 'Maxime et voluptatem fugiat. Dignissimos dolores nemo odit. Velit consequatur est voluptatem voluptatem aut. Illo id reprehenderit et assumenda nam.'),
('102', 'Illo.', 'Cumque et illum voluptatem velit. Voluptatum nisi placeat voluptatum. Dolorem est temporibus cupiditate earum molestias et. Et sed velit maxime earum.'),
('103', 'Saepe.', 'Fugit id ut id nobis. Velit molestias sint unde ut sit voluptatibus harum. Laboriosam dolores officia voluptates. Id qui inventore ea libero omnis consequatur. Voluptatem voluptas atque dolores accusantium eaque aut architecto.'),
('104', 'Fugit.', 'Nobis aperiam excepturi corrupti sit consequatur quis modi qui. Nemo dicta repudiandae sed non culpa et. Quas est quae consectetur modi sed amet.'),
('105', 'Omnis.', 'Quisquam autem qui in et perferendis nesciunt quaerat. Voluptas est aperiam rem sunt sint nam quia labore. Impedit ab beatae debitis velit labore qui.'),
('106', 'Harum.', 'Qui unde placeat et autem quas ratione. Recusandae nihil consequatur dolores. Dolorem reprehenderit quos nobis rem ullam eum maiores.'),
('107', 'Dicta.', 'Et recusandae nam et quia consequuntur aut. Ab repellendus et assumenda praesentium. Facere eveniet et nisi sed tenetur. Molestias repudiandae vitae perferendis aut fugit autem laborum. Consequuntur magni sunt ut quibusdam fugiat assumenda.'),
('108', 'Sit.', 'Et quae fuga fugiat aliquam ea. Sint tenetur fugiat exercitationem optio corrupti maiores. Animi tempora labore aut est sit. Dolorum alias harum quia eum animi quasi eos. Cupiditate occaecati aliquam maiores repellat alias doloribus.'),
('109', 'Quo.', 'Tenetur repellat eum autem veritatis qui quis. Et consequatur voluptatem maxime quas neque vel totam eum. Ut consequatur molestiae eveniet. Veritatis atque vero sed quis est. Ut accusantium dicta eligendi autem fugit ea.'),
('11', 'Atque.', 'Et soluta recusandae nostrum aut vel eos et. Qui quasi voluptates alias possimus velit libero aut. Explicabo accusamus sit facere optio. Et eligendi ullam voluptas eos praesentium delectus reprehenderit.'),
('110', 'Qui.', 'Est ab praesentium optio sapiente. Quia consequuntur nisi nulla reiciendis velit. Rerum eum facilis voluptates velit vel nam.'),
('111', 'Velit.', 'Id velit ducimus neque soluta magnam. Quas velit dolorum consequatur ad fugit. Fugit culpa tempora nam inventore in.'),
('112', 'Ea.', 'In labore molestiae voluptatem maxime culpa dolores. Rem illum minus omnis aut nobis.'),
('113', 'Eaque.', 'Asperiores voluptatum hic qui mollitia est qui et. Explicabo delectus repellendus nulla et. Dolorum tempora animi neque natus.'),
('114', 'Alias.', 'Ab adipisci qui consequuntur modi. Aut quisquam laudantium voluptatem saepe. Harum aut eaque ipsum.'),
('115', 'Illum.', 'Reiciendis nihil illo quis autem aut ab dolor. Soluta ratione ea omnis qui aut fugiat. Natus reprehenderit blanditiis rerum voluptate debitis illum. Eius facilis dolores ab tempore velit hic.'),
('116', 'Quae.', 'Provident nihil deleniti modi maiores est fuga. Eos tenetur occaecati maxime aut sequi eius rerum odio. Et omnis magnam ullam autem blanditiis beatae in. Quidem ab deserunt at optio aut officiis possimus perferendis.'),
('117', 'Sequi.', 'Qui animi ut dolore neque deleniti quis dolor. Sapiente dolores nihil aliquid et. Doloremque provident ea quidem rem est voluptatem ipsam. Esse ipsum amet temporibus.'),
('118', 'Sed.', 'Quos et inventore delectus cum et. Sit eius nihil saepe blanditiis est qui dolorum. Incidunt natus odio quisquam cum animi. Dicta distinctio ipsum accusamus vel. Qui laudantium nam excepturi mollitia sed sed.'),
('119', 'Est.', 'Modi cupiditate ab dolor cum possimus at possimus repellat. Et ut sapiente qui officia. Asperiores error voluptatibus reiciendis aut ullam ducimus esse.'),
('12', 'Cum.', 'Aut dolorem dicta corrupti ducimus. Temporibus fugiat quidem autem repellendus maiores dolorum. Non dolor cupiditate provident atque illo sunt. Sint ut quasi totam harum quis. Cum rem modi voluptatem id.'),
('120', 'Sint.', 'Enim vero quo quae expedita. Porro et qui dolorem ea molestias. Deserunt est culpa sit ipsam sit fuga.'),
('121', 'Velit.', 'Qui ipsam quod suscipit inventore. Quidem dolor vel velit voluptatem inventore non. Exercitationem quo sed deleniti dolore rerum officia hic.'),
('122', 'Id.', 'Nostrum rerum sunt similique reiciendis autem asperiores. Voluptas quia porro omnis culpa quod. Autem quam quae laborum aut distinctio labore dolores quod. Quos aut esse aut dolores aut. Dolor pariatur ad amet incidunt.'),
('123', 'Qui.', 'Omnis cum necessitatibus nihil dolorem ipsum suscipit. Maxime dolores et quaerat blanditiis tempora. Voluptas quis est modi cum eligendi.'),
('124', 'Iste.', 'Doloribus labore non voluptatem laudantium excepturi. Eaque sapiente veniam ut. A est quos dicta illum deleniti corporis adipisci.'),
('125', 'Fugit.', 'Minus facere odio aut voluptatem quis necessitatibus non. Libero magni earum enim sed dicta. Amet earum placeat doloribus. Pariatur aut qui corrupti vel et a.'),
('126', 'Quod.', 'Aut optio quia accusantium consequatur eum possimus tempore pariatur. Modi quibusdam sint ipsum omnis. Quo est temporibus ut repellendus. Sit dolor ex perferendis perspiciatis.'),
('127', 'Quis.', 'Sint quae molestias velit ut expedita impedit quas. Fugiat corporis dolorem officia distinctio autem repellat dignissimos. Consequatur quam dolor quasi possimus ut.'),
('128', 'Minus.', 'Veniam soluta voluptas in eius nihil. Assumenda nostrum corrupti quia quidem quae omnis voluptatem impedit. Quaerat voluptatem necessitatibus qui nesciunt voluptate repellendus.'),
('129', 'Et.', 'Nam ut eum ut rerum. Quisquam et modi pariatur perspiciatis cum. Omnis numquam voluptates porro nihil.'),
('13', 'In.', 'Est nostrum unde iste expedita quia quod et sint. Quod ab fugiat delectus et vero quo. Voluptas ab odio natus porro illo molestiae odio enim.'),
('130', 'Aut.', 'Asperiores magnam voluptatem alias sunt consequatur. Expedita et ea voluptatem dolores quia voluptas. Earum rerum aut mollitia rerum maiores. Velit ut blanditiis voluptas in sit ipsa corporis.'),
('131', 'Earum.', 'Nesciunt dignissimos quas incidunt beatae quo aut. Qui id esse provident unde tenetur. Maiores velit reprehenderit qui. Nostrum deserunt voluptatibus facilis est.'),
('132', 'Velit.', 'Voluptatem qui sit fuga quasi nam rerum et. Voluptates sit modi earum modi quas error consequuntur iusto. Qui nulla qui ut eum quidem. Consequatur porro sint quos ut quo aut.'),
('133', 'Dolor.', 'Sit inventore et dignissimos. Dolorem quidem itaque cum ad.'),
('134', 'Id.', 'Non culpa delectus ut ut. Dolores repellat doloremque nobis consequuntur. Molestiae ea vitae qui doloremque. Hic consequatur omnis aut eos expedita repudiandae.'),
('135', 'Qui.', 'Nihil autem recusandae aut fugit. Velit exercitationem quis ut quis. Omnis rerum quia qui sunt sit quas.'),
('136', 'Est.', 'Dolor est itaque sed consequatur. Nulla qui numquam veniam. Et fuga similique est quaerat et ratione eligendi. Eius dolorem laboriosam rerum expedita ea ut.'),
('137', 'Minus.', 'Mollitia vel molestiae iusto eum. Labore dolorem omnis suscipit sit officiis. Corporis voluptas maxime ullam eos aperiam aut sed. Aut explicabo dolorum illum quaerat.'),
('138', 'Aut.', 'Debitis et qui illum ducimus illum. Reprehenderit et quibusdam sunt fuga. Earum dolores debitis omnis est ea.'),
('139', 'Ipsum.', 'Voluptates autem et recusandae placeat id. Eius similique aut omnis dolorum minima voluptatum eos. Et eum est assumenda quaerat. Delectus neque id debitis molestias voluptatem eveniet et ex.'),
('14', 'Est.', 'Voluptatem qui beatae omnis numquam ut. Officia cupiditate tempora aliquid necessitatibus vitae fuga eaque. Consequuntur aut nihil tempore consequuntur.'),
('140', 'Dolor.', 'Eveniet aut at ut sit. Voluptas qui occaecati veniam dolorem. Dolore placeat perspiciatis ratione dolore et.'),
('141', 'Sunt.', 'Reiciendis at occaecati aliquid itaque laudantium fugiat. Quaerat provident aut vero iste labore molestiae. Mollitia corporis harum sed deleniti quo. Consequatur harum enim ut voluptate voluptatibus qui molestiae.'),
('142', 'Hic.', 'Adipisci rem optio qui at rem. Id harum porro ipsam nam. Delectus voluptas id autem doloremque sint sunt. Voluptas et magni deleniti odit consequuntur.'),
('143', 'Velit.', 'Ut et distinctio tempore quas non occaecati esse. Iure molestias quia laudantium suscipit sed. Quia aut maiores consequatur voluptatem. Dolores non aut aut.'),
('144', 'Ut.', 'Voluptatibus ut porro eligendi non tempora. Inventore consequuntur excepturi amet excepturi voluptate et voluptas. Qui est ratione et laboriosam ipsam aut. Necessitatibus nemo voluptatem non et accusamus.'),
('145', 'Et.', 'Quo et unde eaque vel. Sequi ipsam occaecati expedita animi omnis molestiae.'),
('146', 'Atque.', 'Autem et quis quasi ullam id vitae earum doloremque. Qui velit omnis aut deleniti. Corrupti dignissimos facilis et voluptatum sit aperiam sed.'),
('147', 'Quas.', 'Deleniti error nesciunt qui et itaque. Non veniam laborum totam id deleniti dicta. Quaerat omnis magnam illum laborum nobis repellendus. Molestias quo consectetur provident et amet.'),
('148', 'Odio.', 'Impedit voluptas voluptatem laboriosam ipsum temporibus hic ut. Harum animi rerum corporis modi animi quia. Quae explicabo velit dolore ducimus.'),
('149', 'Dicta.', 'Voluptatum perspiciatis qui beatae. Consequatur id dolores quo nemo. Cum itaque et vel sint rerum suscipit numquam atque. Occaecati aut a velit rerum veritatis quae eos.'),
('15', 'Id.', 'Voluptatum voluptas consequatur illum eum. Impedit quae error exercitationem. Ut sunt natus ut officia.'),
('150', 'Autem.', 'Fugit quas eum molestiae est. Dolorem quis ea quo consequatur. Aut sint placeat doloremque aspernatur in iste. Rerum consectetur dolorem reprehenderit voluptas et.'),
('151', 'Harum.', 'Fugit ut consequatur amet. Cupiditate eos ab consequuntur modi magni. Minus nulla neque cum voluptate accusamus et.'),
('152', 'Ipsa.', 'Occaecati explicabo explicabo adipisci aut. Quidem minima nemo veniam odit. Earum repellat incidunt sed explicabo et non molestiae.'),
('153', 'Sed.', 'Alias inventore est et optio. Et officiis et quasi maxime. Porro eum incidunt vitae minima. Facilis ut dolor laboriosam ut sit in.'),
('154', 'Ex et.', 'Magnam magnam quisquam nemo ipsum. Dolore quidem dolore dicta sed. Nihil ullam eum recusandae voluptatem sed neque.'),
('155', 'Nulla.', 'Laudantium tempore dicta quidem quos ab ut. Dolorem eaque fuga fugiat. Repellat quasi at ratione modi deleniti doloribus voluptatum.'),
('156', 'Fuga.', 'Ut placeat atque dolore mollitia voluptas. Et et voluptatum mollitia et voluptatem. Mollitia ex autem consequatur ipsa officia voluptatem placeat illum. Ullam est ut quaerat dolor unde sit.'),
('157', 'Iste.', 'Aliquid cumque eos voluptas vel eum sapiente. Qui sit laboriosam alias. Autem soluta soluta blanditiis praesentium.'),
('158', 'Nisi.', 'Error ipsum voluptas consectetur non explicabo quis eveniet. Distinctio officiis laudantium non quia neque sequi velit. Nisi a veniam esse natus beatae deserunt. Ut possimus fugit dolor ullam dolores.'),
('159', 'Eaque.', 'Officia unde quisquam porro harum dolore pariatur. Aut quia est sit et vel nesciunt non. Id consectetur quaerat id odit dolorem exercitationem eum. Excepturi numquam hic aspernatur dolor incidunt qui.'),
('16', 'Cum.', 'Temporibus aut aspernatur quisquam dolorem. Facere aspernatur et ut minus natus. Nobis maiores tenetur adipisci eveniet.'),
('160', 'Et.', 'Cumque atque voluptates ab velit voluptas rem. Beatae voluptatum asperiores repellendus repellendus repudiandae explicabo. Perferendis aut rerum veritatis aut.'),
('161', 'Iusto.', 'Quia dolores aspernatur aut corporis excepturi quae velit. Qui mollitia corrupti aliquid in ducimus. Perspiciatis perspiciatis at ratione possimus sunt.'),
('162', 'Nam.', 'Quo cupiditate in aut deleniti esse omnis rerum sint. Ab non harum velit cumque. Explicabo et eum ut corporis. Omnis id quia possimus vel.'),
('163', 'Rerum.', 'Facilis deleniti incidunt iste molestias est molestiae. Iusto fugiat molestiae dolores. Reiciendis nam distinctio deserunt ut expedita possimus voluptas tempore. Ut aut voluptas nobis sed. Nisi voluptatem ut ipsum deserunt aspernatur porro dolores enim.'),
('164', 'Ut ut.', 'Aut voluptatem exercitationem praesentium harum nisi itaque. Voluptatem nihil nobis reiciendis non illum non. Dolorum dolore nemo voluptatem natus ut ratione autem.'),
('165', 'Sunt.', 'Dolores autem consequatur odit nobis ad ea sunt. Eveniet quisquam sint temporibus officia. Hic doloremque reiciendis excepturi ab aut non nihil. Consequatur ut voluptates deserunt.'),
('166', 'Nemo.', 'Et corporis odio a hic fugit. Non exercitationem nihil officiis. Reprehenderit nisi saepe libero ut. Architecto incidunt at ex nesciunt. Ullam fuga doloribus non sunt est adipisci architecto.'),
('167', 'Iste.', 'Repudiandae non corrupti dolor sed. Eum magnam vitae ut. Nihil dolorum et non ut.'),
('168', 'Iure.', 'Aut quis architecto voluptatem aut omnis sed. Velit tempore nulla sed et provident ad. Quis soluta ut a sequi dolore debitis vel amet. Quos molestias eaque ullam modi laborum voluptas.'),
('169', 'Quia.', 'Ipsa ab aliquid at cumque laborum. Occaecati alias saepe consequuntur est.'),
('17', 'Et ad.', 'Quibusdam fugiat voluptatem est error tenetur qui rerum. Accusamus facere voluptatibus excepturi aut sit vel. Quisquam ex maxime fugiat sint dolorem veniam.'),
('170', 'Ipsum.', 'Culpa et voluptas illo rem eligendi. Dolor voluptate impedit ea a. Nisi aut id soluta nihil ipsum cupiditate omnis blanditiis. Eaque voluptatibus impedit eveniet.'),
('171', 'Sit.', 'Ea totam hic perferendis et nam. Voluptate molestias aspernatur et necessitatibus. Excepturi placeat odit optio praesentium saepe non aut. Modi consequatur dolorum consequatur.'),
('172', 'Omnis.', 'Illo est voluptas id. Consequatur ut quaerat eaque eos consectetur a. Debitis quibusdam voluptatem quia dolores. Pariatur ut sit nihil qui tempore a eaque.'),
('173', 'Ad.', 'Quia eum cupiditate corrupti sit enim quo debitis. Deleniti voluptatem quo vel quisquam. Est provident quaerat perferendis incidunt sit voluptatem ipsam. Assumenda non qui vel voluptas est explicabo.'),
('174', 'Nemo.', 'Odit nam nemo nostrum aut praesentium sed. Dolorem sed dignissimos placeat et excepturi. Dignissimos explicabo libero repudiandae doloremque.'),
('175', 'Est.', 'Eveniet in autem ad itaque. Aut culpa consequatur architecto cum dolores ullam sed. Delectus asperiores rerum quasi necessitatibus quibusdam. Error dolores ab id rerum vero eos cumque.'),
('176', 'Harum.', 'Nihil ea a corrupti. Nulla culpa corporis unde voluptates natus et officiis. Mollitia voluptates in rerum quis exercitationem delectus consectetur. Itaque aut voluptate ipsum magnam eos. Eum id nesciunt ea totam nulla.'),
('177', 'Omnis.', 'Nostrum ducimus earum nisi incidunt. Sequi natus ipsam voluptatem qui cum. Neque quia labore veritatis est sunt est quia. Et sunt officia illum quia optio error eius consequatur.'),
('178', 'Enim.', 'Iusto dolorem dignissimos dolores provident consequatur ut quae. Sapiente voluptatum similique ducimus quia qui facere corrupti. Qui officia eos est rerum quas.'),
('179', 'Ab.', 'Laudantium ipsam et dolore sit magni. Numquam dolor eos tempore quia non dolorem consequuntur. Repudiandae iusto praesentium earum beatae animi asperiores.'),
('18', 'Harum.', 'Eum facilis tempore magnam ut tempora. Voluptatem doloribus et numquam recusandae qui dolores. Ut dolor non velit est libero sit.'),
('180', 'Eos.', 'Quos repellendus non voluptatum. Vero assumenda aut dignissimos nihil dolor qui et. Sed et sequi modi veniam aperiam. Qui quia unde aut animi magnam dolor nesciunt a.'),
('181', 'Quo.', 'Explicabo consequatur odit nihil fugit modi quibusdam voluptatem odio. Officiis quaerat qui animi ipsa. Autem deleniti asperiores sunt ducimus quaerat voluptatem.'),
('182', 'Harum.', 'Facere rerum ipsa et labore quidem iusto. Ipsa saepe magnam sit tempora. Qui qui aspernatur ex architecto. Sunt laborum provident laudantium iure inventore ducimus.'),
('183', 'Quis.', 'Ab totam rerum tempore vel. Et voluptas alias voluptatibus corporis. Odit maxime suscipit non doloribus vitae ullam. Quasi quis tempore eveniet atque fugiat fuga.'),
('184', 'In.', 'Accusantium eveniet fuga sit explicabo modi aut ut fugiat. Molestias est aut incidunt nemo quam laboriosam.'),
('185', 'Est.', 'Asperiores recusandae molestiae necessitatibus distinctio exercitationem asperiores ipsum. Corporis sed inventore eum at quis veniam. Et ut voluptas odit quo est ullam ipsa quia.'),
('186', 'Sit.', 'Impedit debitis aliquam eius et id. Tempora tenetur quam minima.'),
('187', 'Et.', 'Nisi veniam quam perferendis ex. Ea optio rerum iste sint autem est. Facere repudiandae aut sapiente velit minus sed. Molestiae placeat est odit quod necessitatibus est molestiae.'),
('188', 'Sed.', 'Sed et maxime enim officia sapiente. Labore vel autem itaque culpa. Quo hic deleniti ad vel eius sint.'),
('189', 'Id.', 'Quidem ea enim magni voluptatum. Quos omnis voluptas pariatur. Vitae voluptate placeat ab dolore. Ut at hic dolorum enim temporibus fugit. Ex minima tenetur maiores nobis alias voluptates.'),
('19', 'Ut.', 'Et nihil consequatur suscipit quisquam at aut sint suscipit. Consectetur exercitationem error dolor officiis. Quisquam quos sed dolorum sed minima ex. Consectetur fugiat sed vitae cumque.'),
('190', 'Natus.', 'Cum et quaerat consequatur et officia. Et assumenda dolor repellendus. Pariatur aut eligendi recusandae maxime.'),
('191', 'Ut.', 'Aut dignissimos nihil molestiae in est. Minima officiis suscipit culpa asperiores et. Delectus aperiam eum minima ut excepturi assumenda est. Repudiandae aliquid possimus mollitia autem nam.'),
('192', 'Illum.', 'Eos soluta consequuntur velit rerum dolorum neque. Asperiores possimus deleniti commodi id culpa omnis voluptate. Voluptas nemo excepturi earum tempore rerum.'),
('193', 'Nisi.', 'Eaque non harum temporibus beatae voluptas quam maxime autem. Excepturi eum inventore laudantium libero error laudantium. Repellat quia non tempore molestias ducimus tenetur.'),
('194', 'Sunt.', 'Incidunt sit aliquid dolores totam architecto voluptatem aut. In ratione reprehenderit veritatis magni eum consequatur in. Eum sunt consectetur et maxime velit. Consequatur quia cumque qui impedit. Ut odit non nostrum fuga maxime a.'),
('195', 'Modi.', 'Corrupti magnam veniam alias est illum qui occaecati. Ut voluptatibus reprehenderit et dolor.'),
('196', 'Enim.', 'Repudiandae omnis molestiae quo eligendi temporibus facere. Ex quis animi enim non nihil. Qui vitae asperiores maiores optio ut enim officia. Sapiente facilis veniam quidem ut et.'),
('197', 'Enim.', 'Vel eaque doloribus occaecati sapiente. Quidem officiis saepe autem cum id accusantium. Aperiam qui molestiae sint. Dolor qui accusamus ut rem et ea. Quibusdam ut cum cum voluptas laudantium odio alias cupiditate.'),
('198', 'Omnis.', 'Ab ut voluptates itaque. Assumenda corrupti rerum dolore debitis. Ex sed sunt dolore aliquam nobis tempora.'),
('199', 'Quae.', 'Consequatur sit asperiores et natus dignissimos voluptatum. Nam cupiditate velit nam labore debitis quis perferendis. Minus at nam delectus dignissimos odio maxime dolore.'),
('2', 'Ut.', 'Tenetur sed qui dicta vel. Officia dolores nihil quod id. Error dolorem placeat a minus nesciunt et.'),
('20', 'Quia.', 'Unde aliquam enim sequi quos officiis et enim. Earum qui et qui a quis. Et non amet quaerat alias delectus iste earum.'),
('200', 'Ea ut.', 'Porro ex ratione qui similique. Reprehenderit illum ea non ipsum quia praesentium illum animi.'),
('21', 'Et.', 'Esse maxime dolor quia ex quae et maiores sint. Velit et sed aut dicta voluptatem eum exercitationem. Autem ut nobis dolor esse libero voluptatem laudantium ab. Numquam quas in occaecati numquam.'),
('22', 'Dicta.', 'Sunt voluptatum et iure esse magnam. Voluptatem molestias non non suscipit. Architecto consequatur ex ipsum consequatur consequatur ratione voluptas. Accusamus dolorem molestiae officiis amet suscipit.'),
('23', 'Et.', 'Cupiditate praesentium quia ex est iusto. Sint debitis ut quam quia corrupti rem perspiciatis minus. Quas et qui qui tempora nam ut repudiandae. Vitae non fuga mollitia facere voluptas.'),
('24', 'Eum.', 'Veritatis consectetur maxime soluta eius recusandae ipsa. Incidunt nostrum dolores praesentium qui. Dolores fugiat eius culpa veritatis sapiente explicabo. Consectetur consequuntur incidunt ut unde rerum sit.'),
('25', 'Ad et.', 'Et dolore voluptatem et fugit quae impedit ut. Esse est ad tempore at voluptates. Ad culpa rem unde consequatur rem.'),
('26', 'Quod.', 'Illum temporibus at est aperiam nesciunt ut voluptas unde. Et alias magni in placeat architecto iure ut. Ratione asperiores repellat fuga hic.'),
('27', 'Eius.', 'Repellat perspiciatis velit tempore. Voluptatem maiores neque alias error explicabo sunt. Omnis ut et minus ut natus sunt. Enim tenetur omnis odio expedita eaque unde.'),
('28', 'Et.', 'Corrupti mollitia tenetur cupiditate eos. Officia autem ratione eum atque eum illo quia. Cum est eum quia enim quam odit vitae. Excepturi laudantium aut quo nisi. Nisi eos accusamus est aut deleniti rerum.'),
('29', 'Non.', 'Laudantium sequi eaque blanditiis repellat tempore in. Id magnam cum soluta laudantium id. Officia aut repellendus rerum enim sit error debitis voluptatibus. Ea nihil quisquam inventore consequatur.'),
('3', 'Enim.', 'Deleniti ex mollitia aut. Non sit saepe quia natus. Velit eum quo vel corporis laborum recusandae saepe placeat. Eum nihil nisi officiis minus.'),
('30', 'Sed.', 'Quo velit est odit est distinctio soluta. Voluptatem quos cupiditate qui rerum quis labore. Rem ipsum reprehenderit velit doloribus officiis et. In mollitia quia illum ipsa nobis et quos. Quo sapiente ut quidem hic placeat consequatur est vel.'),
('31', 'Eum.', 'Rerum nihil nulla laboriosam nihil dignissimos natus et. Aut adipisci est eum placeat consequatur molestiae. Fugiat eveniet eligendi perferendis quos.'),
('32', 'Illo.', 'Eum provident totam odio non autem possimus sed. Fugit iure maxime rerum ratione asperiores. Molestias tempora et commodi illo.'),
('33', 'Quam.', 'Ea ratione iusto esse quis. Ut hic qui eaque. Eaque qui laborum provident quasi rem quis. Sapiente commodi natus dolorem repellendus nisi consequatur.'),
('34', 'Quas.', 'Dolor aut et consequatur dolor amet laborum. Possimus at quae vel id. Et suscipit repellat sed architecto ad maiores eos. Dolor vel quis dolore quasi quis nesciunt nam. Occaecati velit voluptates nesciunt laboriosam et sit voluptatem.'),
('35', 'Ut.', 'Dolores provident sunt aspernatur totam illum incidunt et. Magnam quidem saepe aut in.'),
('36', 'Enim.', 'At ex hic omnis sit non deserunt. Similique rerum qui quo amet aut. Quibusdam dicta dignissimos tempora occaecati voluptatibus quam. Facere nam quis quod aut assumenda.'),
('37', 'Velit.', 'Nostrum est vero dolore maxime distinctio tempora hic. Sed nobis dolor sed qui enim vero.'),
('38', 'Quas.', 'Eveniet aut impedit exercitationem et totam cum. Et sequi maiores est enim nulla eligendi. Vel quod ut quia nam reprehenderit nemo quia.'),
('39', 'Eos.', 'Quidem optio vel maiores ducimus qui. Libero repellat atque quasi a. Blanditiis sit est consequuntur odit et. Voluptatibus adipisci omnis illum error.'),
('4', 'In et.', 'Quo eum voluptatibus pariatur voluptatem libero. Dolore rerum in sit nihil libero autem. Delectus corrupti ea natus ipsa quis nisi nesciunt. Rem consequatur et exercitationem.'),
('40', 'Qui.', 'Repellat alias non rerum id necessitatibus labore voluptatem. Sed sed nemo quas qui esse atque. Et saepe impedit dolorem eum.'),
('41', 'Unde.', 'Eaque doloremque adipisci qui tempora alias velit harum id. Nam assumenda illum laborum tenetur et vel mollitia. Quia doloremque iste accusantium et earum. Blanditiis quo itaque voluptatibus suscipit harum voluptas.'),
('42', 'Sit.', 'Impedit sit aut exercitationem quaerat. Nisi in id quis nihil ut dolor molestias. Labore possimus voluptatem excepturi aut dicta. Aut quasi voluptatem omnis dolorem adipisci.'),
('43', 'Porro.', 'Nisi minus ratione temporibus eveniet rerum animi at. Non perferendis est iure. Et est magnam voluptas distinctio eaque et ab. Molestiae ullam at quidem iure voluptas similique.'),
('44', 'Sequi.', 'Dolorem tenetur ut molestiae dolor. Eum ab non est sed ea voluptas atque enim. Ipsa aut aliquid nostrum eos.'),
('45', 'Enim.', 'Rerum iste aliquam hic eum quia voluptate id vero. Quo rem unde qui eveniet. In qui et sunt aut cumque aut. Voluptatibus voluptatem sapiente ipsa vel perspiciatis molestias veniam. Ut omnis quidem nemo omnis.'),
('46', 'Saepe.', 'Aut dolor vel ducimus sunt harum sed in. Autem consequuntur iure quaerat magni consequatur minima. Temporibus eos eum ex enim.'),
('47', 'Totam.', 'Est nesciunt repellat fugiat magnam molestiae dolore. Suscipit quia corrupti dolores excepturi dolor fugit rerum error. Amet earum quisquam omnis velit consequatur.'),
('48', 'In.', 'Corrupti eum at quas consequatur. Optio sunt facilis nobis magni. Velit cumque enim quibusdam veniam autem sint qui quia. Non pariatur at error ipsum.'),
('49', 'Et.', 'Quia reiciendis rem quo amet libero. Dolorum nisi dicta vitae. Hic fuga autem sit temporibus aperiam explicabo. Consequatur repellendus vel veritatis dolorem qui.'),
('5', 'Sed.', 'Et velit cum non natus. Eos explicabo et qui ipsum molestiae numquam. Vitae nihil dolore aliquam doloremque repellat.'),
('50', 'Eaque.', 'Molestiae nihil voluptatibus blanditiis culpa et. Exercitationem alias earum expedita nihil ut. Blanditiis tenetur fuga et itaque. Ipsa libero optio reiciendis consequatur.'),
('51', 'A.', 'Et ab veniam molestiae enim nihil cumque et animi. Consequatur nihil corrupti ab corrupti voluptatum. Labore accusantium ab eius sit nemo ab.'),
('52', 'Eum.', 'Error maxime dicta reprehenderit quo veniam. Tempore quia animi et ut voluptatem numquam. Aspernatur hic blanditiis tenetur sint esse sint.'),
('53', 'Iusto.', 'Saepe quis dolorum dicta adipisci vitae. Harum aut adipisci quia occaecati. Et rem aperiam omnis ex eum officia modi. Est fugit qui adipisci provident in debitis voluptates nam.'),
('54', 'Alias.', 'Nihil impedit quia voluptas non voluptatibus quaerat consequatur. Aut sequi illo dolore reprehenderit omnis consectetur. Consequatur aspernatur quod perspiciatis nihil vitae voluptas voluptate. Magnam repellendus quia accusamus temporibus earum quaerat.'),
('55', 'Iste.', 'Quia molestiae perferendis et et. Molestiae dolor suscipit et repellendus ipsa. Excepturi saepe est sit earum nobis nesciunt. Tempore ullam saepe pariatur repellat facilis nam nulla. Assumenda id illum autem aut vel.'),
('56', 'Sint.', 'Vero voluptatibus eveniet eaque voluptas omnis rerum voluptatibus velit. At nemo itaque facere necessitatibus maxime blanditiis mollitia sit. Sed fugit ea velit dolores. Veritatis accusamus dolorem molestias laudantium facere.'),
('57', 'Omnis.', 'Delectus cupiditate quasi omnis ratione natus quam laborum. Neque porro odit nihil delectus quos. Eum distinctio expedita vel ipsam.'),
('58', 'Eos.', 'Laboriosam qui fugit adipisci quo excepturi modi animi maiores. Est libero deleniti ex aperiam dolorum. Est possimus minima eos et minima ad asperiores.'),
('59', 'Et.', 'Veniam itaque facere alias consectetur deleniti sunt velit. Vel in in inventore doloremque a ut. Quasi fugit dolorem est iure dolores. Magni facilis ut molestiae dolore fugiat qui similique.'),
('6', 'Ut.', 'Culpa recusandae possimus nemo cumque. Sint doloribus laborum maxime consequatur et sint veniam. Omnis sunt assumenda dignissimos fuga repellendus placeat. Voluptatem error et iste qui temporibus.'),
('60', 'Hic.', 'Nulla et aut voluptatum amet et. Nemo minima autem sed commodi. Vitae temporibus et et quia quos illo.'),
('61', 'Vero.', 'Odit quia nemo quia quia facilis enim. Nemo dolor impedit sint ullam. Pariatur non et a laboriosam ex. Quae dicta maiores quia officia numquam quia enim.'),
('62', 'Eum.', 'Quaerat voluptas ipsum ut et cum voluptas. Iusto dolor rem nesciunt qui aliquam tempora. Mollitia sit reprehenderit ut voluptatem ea iure quisquam natus. Vel suscipit quos fugit dignissimos iure aut explicabo.'),
('63', 'Ipsum.', 'Nam quis est nulla. Ratione rem modi nihil. Fuga et fugiat enim dolore deserunt.'),
('64', 'Natus.', 'Nobis sit et officia. Et molestiae et non vero nostrum quasi dignissimos dignissimos. Temporibus accusamus rerum ullam labore iste.'),
('65', 'Sed.', 'Labore omnis in voluptatum in repellendus corporis ea. Laboriosam ea nisi labore similique. Error ad neque ab voluptatibus. Est et iste quia ut sit alias ducimus.'),
('66', 'Velit.', 'Nisi culpa aut a in. Nam magni sed saepe nihil quis. Numquam qui enim reiciendis voluptatum eos debitis.'),
('67', 'Et.', 'Impedit et commodi unde molestiae dolor eligendi saepe. Deleniti minus aut voluptatem officia. Non nesciunt dignissimos sequi nihil. Quis eos ut harum.'),
('68', 'Aut.', 'Velit labore laborum sit. Optio aut commodi et esse id aperiam. Suscipit et nesciunt soluta. Dolores provident aperiam et.'),
('69', 'Enim.', 'Qui sed reiciendis sunt. Repellat aut perspiciatis vitae sint sit qui. Iste molestiae qui laborum a iure dolores non.'),
('7', 'Ab.', 'Nostrum veniam a consequatur quo neque exercitationem. Illum dolor quae neque aut velit est voluptatem perspiciatis. Aspernatur et aut aliquid culpa cupiditate. Eos et nemo aperiam.'),
('70', 'Neque.', 'Ullam ut laboriosam rerum cumque nemo. Voluptas ipsam est necessitatibus fugit omnis. Et omnis veritatis non recusandae itaque eos. Vel laboriosam ea quibusdam quo mollitia perferendis id.'),
('71', 'Sequi.', 'Est nihil minus laudantium qui qui. Aliquid vitae excepturi nobis enim. Laboriosam nobis laborum veritatis consequatur eaque atque similique.'),
('72', 'Quos.', 'Assumenda qui dolorem distinctio quae architecto sunt voluptatem. Natus placeat non voluptatibus quia aliquam sit. Minus sint autem ut id est. Veritatis rerum reiciendis pariatur.'),
('73', 'In.', 'Eum facilis debitis ut qui enim. Voluptatem similique rerum ratione sint iste cumque earum. Iste delectus error minus delectus repellat sed aliquam.'),
('74', 'Autem.', 'Repudiandae rem vel exercitationem vitae ea et rem. Quia beatae ad cumque soluta. Tempora accusantium perferendis sit incidunt sint eos. Dolorem velit aut voluptatibus reprehenderit repudiandae ea.'),
('75', 'Alias.', 'In labore enim placeat ipsum in quidem. Ad et et unde. Itaque veniam tempora omnis ad aut at. Exercitationem et vel distinctio atque qui rem velit et.'),
('76', 'Vero.', 'Ut enim aperiam sed repellendus et. Soluta et quia aut assumenda reiciendis minus voluptatem. Dolorum iure suscipit quos eum.'),
('77', 'Id et.', 'Qui provident voluptatem dolorem sed optio fuga. Aut recusandae dolorem consectetur quidem dolores quo saepe temporibus. Nobis aut et quas molestiae laudantium est. Est id aperiam est officia sapiente.'),
('78', 'Quia.', 'Dolores neque molestiae fugit optio. Sit provident at officia molestiae ut natus. Nemo illo est ut.'),
('79', 'Quas.', 'Aut est et velit consequatur consequatur. Ab illo occaecati quaerat consequatur debitis sed molestiae. Aut et fugit odio deleniti. Dolorum eum nisi occaecati reprehenderit.'),
('8', 'Quis.', 'Id est eaque perferendis debitis molestias. Non aperiam est porro. Necessitatibus fugiat doloremque id voluptatem error.'),
('80', 'Dolor.', 'Iure eveniet iste animi. Velit molestiae perferendis quia voluptate. Sit dolorem sint molestiae fuga.'),
('81', 'A.', 'Officiis qui tenetur id ut. Ab autem odio hic minus. Et tempore velit cupiditate.'),
('82', 'Velit.', 'Rerum magnam est laboriosam accusamus dolores repellendus et. Et nam voluptas quia praesentium facilis voluptas. Cumque eum qui odit quaerat ea recusandae. Sed corrupti quia numquam id omnis architecto. Minima minus eligendi ipsa rerum magnam vel iusto.'),
('83', 'Et.', 'Eaque magnam porro maiores in dolores. Corporis dignissimos autem sapiente quod. Consequuntur doloremque laboriosam enim quis dolor aliquam dolor. Voluptatem eius cumque facilis consequuntur dolorem.'),
('84', 'Non.', 'Magnam facilis pariatur minus et voluptas consequatur odio. Nesciunt mollitia consequatur illum est omnis tempore. Perferendis hic ex cumque vel aperiam voluptatem. Quisquam mollitia ut in distinctio. Dicta quis sunt saepe quia et officia voluptas cum.'),
('85', 'Rerum.', 'Ut quos dolores quasi consectetur ea. Voluptate eveniet fuga ab odit qui. Perferendis explicabo aliquam qui voluptatum.'),
('86', 'Qui.', 'Et ullam ipsam consequatur voluptatem sit ea inventore dolorem. Blanditiis omnis molestiae ut. Nisi quasi commodi vitae totam unde illo delectus.'),
('87', 'Sed.', 'Illo culpa expedita velit officia rerum et et. Fugit cumque et eaque eius vero perferendis. Ut magnam et accusantium doloribus id. Ex recusandae explicabo et.'),
('88', 'Quo.', 'Et libero nihil debitis expedita voluptas. Similique et et voluptatem qui reprehenderit. Molestiae tempora fugit et exercitationem aut vero.'),
('89', 'Minus.', 'Totam aspernatur architecto suscipit est id. Minima animi in vel repellat corrupti eligendi consequuntur est. Quia beatae ducimus ut error possimus voluptatem qui id. Nostrum vel ad tempore possimus voluptatem magni sint. Rerum neque molestias accusantium'),
('9', 'Non.', 'Ratione cumque repellendus possimus nulla magni eos. Non voluptate assumenda esse aut ut et. Et sit fugiat fugiat ipsa.'),
('90', 'Illum.', 'Dolorem quibusdam sunt adipisci non. Aliquid sint corrupti delectus quisquam iure sunt. Qui nihil delectus alias eos.'),
('91', 'Sequi.', 'Autem et quod vel illum repudiandae amet repellat. Fugiat rerum similique dolor. Quia inventore qui alias dicta aut voluptatem dolor suscipit.'),
('92', 'Alias.', 'Rerum placeat temporibus suscipit reiciendis vel molestias explicabo. Modi molestias doloremque nostrum officia. Ab eos necessitatibus velit ab.'),
('93', 'Sunt.', 'Iste ut id asperiores asperiores esse. Similique in et rerum eos fugit et nam. Aspernatur voluptates reprehenderit atque vel error voluptas consequatur.'),
('94', 'Saepe.', 'Iste aperiam similique amet exercitationem similique ut impedit. Ea recusandae quasi quibusdam dignissimos mollitia suscipit aut in. Molestiae nisi et exercitationem eligendi eum quasi consequatur. Fuga laborum vel saepe dolorum.'),
('95', 'Magni.', 'Eius animi quo et labore officia dolorem rerum. Repudiandae accusantium et minus labore et hic assumenda. Cum minima et perferendis optio voluptate et voluptatibus. Est rerum qui voluptatem sequi illum.'),
('96', 'Ad et.', 'Est doloremque non vel dolorem et et culpa perferendis. Ducimus unde eligendi porro ea unde maxime ducimus. Dolorem occaecati magni velit optio.'),
('97', 'Quos.', 'Nulla nulla nam adipisci. Eos non repellat eius eos. Voluptatem vel quia molestiae.'),
('98', 'Dicta.', 'Non non autem reiciendis rerum fugit ipsa ut. Tempore qui rerum voluptatem. Id praesentium voluptatum facere fuga est omnis.'),
('99', 'Optio.', 'Eveniet tempora neque illum sit distinctio nihil quasi nemo. Voluptatem possimus ipsam eveniet quis earum ea error voluptate. Alias qui veritatis eum molestias dicta qui omnis. Repellat harum rerum et dolor nulla exercitationem nam. Recusandae corporis te');

-- --------------------------------------------------------

--
-- Table structure for table `Student`
--

CREATE TABLE `Student` (
  `StuID` varchar(255) NOT NULL,
  `Gender` varchar(255) NOT NULL,
  `PersonType` varchar(255) NOT NULL,
  `WorkExp` int(11) NOT NULL,
  `GPA` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `Student`
--

INSERT INTO `Student` (`StuID`, `Gender`, `PersonType`, `WorkExp`, `GPA`) VALUES
('1', 'M', 'A', 2017, 3),
('10', 'M', 'F', 2016, 2),
('100', 'F', 'A', 2014, 4),
('11', 'M', 'A', 2018, 2),
('12', 'F', 'D', 2019, 3),
('13', 'M', 'F', 2014, 2),
('14', 'M', 'E', 2018, 5),
('15', 'M', 'E', 2018, 4),
('16', 'M', 'B', 2016, 5),
('17', 'M', 'F', 2015, 2),
('18', 'M', 'A', 2015, 2),
('19', 'M', 'E', 2018, 5),
('2', 'M', 'D', 2015, 2),
('20', 'M', 'C', 2017, 4),
('21', 'M', 'F', 2016, 1),
('22', 'M', 'D', 2016, 2),
('23', 'M', 'C', 2014, 3),
('24', 'M', 'A', 2017, 3),
('25', 'M', 'E', 2016, 5),
('26', 'M', 'C', 2017, 5),
('27', 'M', 'F', 2017, 3),
('28', 'M', 'B', 2016, 2),
('29', 'M', 'C', 2017, 4),
('3', 'M', 'E', 2017, 2),
('30', 'M', 'C', 2014, 2),
('31', 'M', 'C', 2015, 4),
('32', 'M', 'A', 2015, 4),
('33', 'F', 'C', 2017, 1),
('34', 'F', 'D', 2016, 5),
('35', 'M', 'D', 2017, 3),
('36', 'M', 'E', 2015, 1),
('37', 'M', 'D', 2018, 5),
('38', 'M', 'A', 2014, 1),
('39', 'M', 'F', 2018, 2),
('4', 'M', 'D', 2015, 4),
('40', 'M', 'A', 2015, 4),
('41', 'M', 'D', 2014, 4),
('42', 'M', 'B', 2018, 3),
('43', 'M', 'F', 2019, 4),
('44', 'M', 'C', 2017, 3),
('45', 'M', 'A', 2014, 3),
('46', 'F', 'C', 2017, 4),
('47', 'M', 'C', 2017, 4),
('48', 'M', 'B', 2019, 4),
('49', 'M', 'E', 2014, 5),
('5', 'M', 'F', 2014, 2),
('50', 'M', 'E', 2017, 5),
('51', 'M', 'E', 2018, 3),
('52', 'M', 'E', 2019, 1),
('53', 'M', 'B', 2018, 2),
('54', 'M', 'C', 2015, 2),
('55', 'M', 'E', 2017, 2),
('56', 'M', 'D', 2017, 4),
('57', 'M', 'B', 2018, 4),
('58', 'M', 'D', 2018, 2),
('59', 'M', 'C', 2018, 3),
('6', 'M', 'E', 2018, 5),
('60', 'F', 'A', 2019, 5),
('61', 'F', 'A', 2016, 2),
('62', 'F', 'F', 2017, 2),
('63', 'F', 'D', 2016, 2),
('64', 'F', 'D', 2016, 1),
('65', 'F', 'F', 2019, 5),
('66', 'F', 'C', 2018, 3),
('67', 'F', 'A', 2015, 4),
('68', 'F', 'A', 2015, 1),
('69', 'F', 'E', 2017, 4),
('7', 'M', 'A', 2016, 3),
('70', 'F', 'C', 2016, 1),
('71', 'F', 'A', 2018, 1),
('72', 'M', 'A', 2016, 5),
('73', 'M', 'A', 2017, 5),
('74', 'F', 'C', 2016, 3),
('75', 'F', 'A', 2016, 4),
('76', 'F', 'B', 2015, 4),
('77', 'F', 'A', 2018, 2),
('78', 'F', 'A', 2014, 3),
('79', 'F', 'D', 2018, 3),
('8', 'M', 'A', 2019, 1),
('80', 'M', 'A', 2015, 2),
('81', 'F', 'B', 2014, 1),
('82', 'M', 'E', 2016, 3),
('83', 'F', 'E', 2015, 3),
('84', 'F', 'D', 2017, 4),
('85', 'M', 'E', 2017, 2),
('86', 'F', 'C', 2016, 1),
('87', 'F', 'F', 2015, 3),
('88', 'F', 'E', 2019, 4),
('89', 'M', 'E', 2017, 3),
('9', 'M', 'B', 2017, 4),
('90', 'M', 'F', 2018, 3),
('91', 'F', 'B', 2016, 5),
('92', 'F', 'A', 2017, 3),
('93', 'F', 'A', 2018, 1),
('94', 'M', 'F', 2019, 1),
('95', 'M', 'E', 2019, 4),
('96', 'F', 'C', 2015, 3),
('97', 'F', 'A', 2017, 4),
('98', 'F', 'D', 2017, 6),
('99', 'F', 'B', 2016, 5);

-- --------------------------------------------------------

--
-- Table structure for table `Student_Project`
--

CREATE TABLE `Student_Project` (
  `StuID` int(11) NOT NULL,
  `ProID1` int(11) NOT NULL,
  `ProID2` varchar(255) NOT NULL,
  `ProID3` int(11) NOT NULL,
  `ProID4` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `Student_Project`
--

INSERT INTO `Student_Project` (`StuID`, `ProID1`, `ProID2`, `ProID3`, `ProID4`) VALUES
(1, 21, '86', 3, 26),
(2, 15, '49', 27, 48),
(3, 57, '12', 26, 60),
(4, 46, '40', 22, 5),
(5, 1, '98', 19, 52),
(6, 52, '13', 52, 81),
(7, 14, '45', 68, 83),
(8, 55, '24', 57, 68),
(9, 5, '76', 49, 18),
(10, 62, '95', 89, 69),
(11, 24, '66', 85, 56),
(12, 7, '70', 48, 92),
(13, 86, '99', 5, 95),
(14, 96, '36', 8, 36),
(15, 98, '59', 100, 28),
(16, 22, '26', 88, 14),
(17, 66, '5', 10, 24),
(18, 19, '54', 76, 79),
(19, 35, '91', 90, 77),
(20, 2, '16', 32, 40),
(21, 73, '38', 38, 25),
(22, 18, '57', 34, 6),
(23, 8, '82', 50, 23),
(24, 54, '42', 45, 50),
(25, 45, '30', 7, 12),
(26, 12, '31', 9, 22),
(27, 17, '44', 16, 44),
(28, 72, '89', 37, 41),
(29, 95, '10', 98, 43),
(30, 78, '64', 36, 61),
(31, 30, '35', 46, 88),
(32, 50, '69', 62, 31),
(33, 99, '9', 79, 64),
(34, 68, '7', 56, 33),
(35, 13, '67', 14, 53),
(36, 44, '20', 92, 32),
(37, 65, '60', 17, 4),
(38, 36, '25', 12, 37),
(39, 84, '14', 66, 71),
(40, 34, '97', 54, 2),
(41, 71, '29', 86, 42),
(42, 43, '39', 33, 66),
(43, 53, '62', 28, 45),
(44, 11, '78', 93, 96),
(45, 70, '4', 97, 85),
(46, 74, '23', 18, 46),
(47, 64, '47', 83, 47),
(48, 4, '43', 43, 57),
(49, 56, '50', 47, 19),
(50, 75, '18', 74, 84),
(51, 33, '84', 61, 80),
(52, 82, '63', 4, 8),
(53, 87, '73', 75, 91),
(54, 20, '100', 51, 38),
(55, 10, '52', 55, 9),
(56, 63, '90', 41, 86),
(57, 91, '80', 24, 99),
(58, 29, '8', 82, 54),
(59, 47, '53', 25, 13),
(60, 100, '88', 39, 55),
(61, 32, '1', 94, 98),
(62, 28, '28', 44, 78),
(63, 40, '58', 40, 11),
(64, 41, '75', 69, 16),
(65, 27, '51', 80, 76),
(66, 23, '79', 87, 73),
(67, 49, '65', 1, 67),
(68, 81, '77', 20, 39),
(69, 92, '41', 65, 58),
(70, 69, '11', 60, 82),
(71, 60, '32', 15, 74),
(72, 61, '81', 29, 35),
(73, 93, '3', 13, 34),
(74, 25, '72', 30, 90),
(75, 48, '21', 63, 15),
(76, 80, '85', 21, 70),
(77, 94, '15', 6, 29),
(78, 83, '83', 72, 10),
(79, 77, '46', 73, 94),
(80, 89, '17', 84, 72),
(81, 79, '71', 81, 3),
(82, 67, '74', 2, 59),
(83, 37, '33', 67, 30),
(84, 51, '56', 23, 65),
(85, 31, '2', 70, 51),
(86, 39, '48', 91, 62),
(87, 26, '92', 31, 63),
(88, 90, '68', 71, 89),
(89, 42, '37', 99, 49),
(90, 97, '34', 78, 93),
(91, 76, '94', 64, 75),
(92, 88, '93', 96, 100),
(93, 38, '22', 53, 97),
(94, 9, '55', 58, 21),
(95, 6, '87', 59, 27),
(96, 16, '96', 77, 7),
(97, 59, '6', 42, 87),
(98, 3, '27', 35, 20),
(99, 58, '61', 95, 1),
(100, 85, '19', 11, 17);

-- --------------------------------------------------------

--
-- Table structure for table `Teams`
--

CREATE TABLE `Teams` (
  `TeamID` varchar(255) NOT NULL,
  `ProID` varchar(255) NOT NULL,
  `Student1` varchar(255) NOT NULL,
  `Student2` int(11) NOT NULL,
  `Student3` int(11) NOT NULL,
  `Student4` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `Teams`
--

INSERT INTO `Teams` (`TeamID`, `ProID`, `Student1`, `Student2`, `Student3`, `Student4`) VALUES
('1', '0', '1', 26, 51, 76),
('2', '0', '2', 27, 52, 77),
('3', '0', '3', 28, 53, 78),
('4', '0', '4', 29, 54, 79),
('5', '0', '5', 30, 55, 80),
('6', '0', '6', 31, 56, 81),
('7', '0', '7', 32, 57, 82),
('8', '0', '8', 33, 58, 83),
('9', '0', '9', 34, 59, 84),
('10', '0', '10', 35, 60, 85),
('11', '0', '11', 36, 61, 86),
('12', '0', '12', 37, 62, 87),
('13', '0', '13', 38, 63, 88),
('14', '0', '14', 39, 64, 89),
('15', '0', '15', 40, 65, 90),
('16', '0', '16', 41, 66, 91),
('17', '0', '17', 42, 67, 92),
('18', '0', '18', 43, 68, 93),
('19', '0', '19', 44, 69, 94),
('20', '0', '20', 45, 70, 95),
('21', '0', '21', 46, 71, 96),
('22', '0', '22', 47, 72, 97),
('23', '0', '23', 48, 73, 98),
('24', '0', '24', 49, 74, 99),
('25', '0', '25', 50, 75, 100);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `Project`
--
ALTER TABLE `Project`
  ADD UNIQUE KEY `ProID_2` (`ProID`),
  ADD KEY `ProID` (`ProID`);

--
-- Indexes for table `Student`
--
ALTER TABLE `Student`
  ADD UNIQUE KEY `StuID` (`StuID`);

--
-- Indexes for table `Student_Project`
--
ALTER TABLE `Student_Project`
  ADD UNIQUE KEY `StuID` (`StuID`),
  ADD UNIQUE KEY `StuID_2` (`StuID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
