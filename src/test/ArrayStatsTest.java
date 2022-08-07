package test;

import numberstatistics.ArrayStats;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import test.testresources.TestArrayReference;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;


class ArrayStatsTest {

    private final PrintStream ogOutStream = System.out;
    private ByteArrayOutputStream newOutStream;
    private ArrayStats arrayStats;
    private int[] arr;

    @BeforeEach
    void setUp() {
        newOutStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(newOutStream));
        arrayStats = new ArrayStats();
    }

    @AfterEach
    void tearDown() {
        arr = null;
        System.setOut(ogOutStream);
        newOutStream = null;
        arrayStats = null;
    }

    /*

    Basic tests ---------------------------------------------------------------------------------------------

    */
    @Test
    void getStatsBasicInputTest1() {
        arr = IntStream.range(0, 11).toArray();
        arrayStats.getStats(arr);
        assertEquals("Median: 5\nMean: 5\nMode: 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10\nRange: 10\n",
                newOutStream.toString());
    }

    @Test
    void getStatsBasicInputTest2() {
        arr = IntStream.range(1, 11).toArray();
        arrayStats.getStats(arr);
        assertEquals("Median: 5.5\nMean: 5.5\nMode: 1, 2, 3, 4, 5, 6, 7, 8, 9, 10\nRange: 9\n",
                newOutStream.toString());
    }

    @Test
    void getStatsBasicInputTest3() {
        arr = IntStream.range(0, 2).toArray();
        arrayStats.getStats(arr);
        assertEquals("Median: 0.5\nMean: 0.5\nMode: 0, 1\nRange: 1\n",
                newOutStream.toString());
    }

    @Test
    void getStatsBasicInputTest4() {
        arr = new int[]{1, 1, 1, 1, 1, 0, 0, 0, 0, 0};
        arrayStats.getStats(arr);
        assertEquals("Median: 0.5\nMean: 0.5\nMode: 0, 1\nRange: 1\n",
                newOutStream.toString());
    }

    @Test
    void getStatsBasicInputTest5() {
        arr = new int[]{5, 4, 4, 3, 3, 3, 2, 2, 2, 2, 1, 1, 1, 1, 1};
        arrayStats.getStats(arr);
        assertEquals("Median: 2\nMean: 2.3333333333333335\nMode: 1\nRange: 4\n",
                newOutStream.toString());
    }

    @Test
    void getStatsBasicInputTest6() {
        arr = new int[]{0};
        arrayStats.getStats(arr);
        assertEquals("Median: 0\nMean: 0\nMode: 0\nRange: 0\n",
                newOutStream.toString());
    }

    /*

    Edge case tests ---------------------------------------------------------------------------------------------

    */
    @Test
    void getStatsEdgeCaseTest1() {
        // empty array
        arr = new int[]{};
        assertThrows(IllegalArgumentException.class,
                () -> arrayStats.getStats(arr));
    }

    @Test
    void getStatsEdgeCaseTest2() {
        // large array, large ints
        arr = TestArrayReference.largeArray;
        arrayStats.getStats(arr);
        assertEquals("Median: -30850124\nMean: -2.209687385600033E7\nMode: 1626917113, 1085888699, 1955349640, -1446383155, -757769518, -1025146142, -496342431, 1835793766, -713816710, -1620338821, 2067247908, -75322473, 163428779, -157018446, 550342871, 1452574351, 642432593, -262307713, -1755001017, -1910981059, 591123228, -1047553624, -897725867, -1177417221, -389451549, 728314693, -1739671426, 1930199869, -373929598, -1456979684, 214047988, -508925461, -1976253828, 2126419700, 1162337548, -994552589, 901139960, 1918990899, 547066056, 340261908, -1774889370, -576346639, -805302188, 1305189778, -581034748, -1182287399, -1124096863, 2074942415, -986954418, -134434920, 27226612, -580991691, 727114554, 248368800, -71844902, -450505388, -1185291990, -2017443917, -1600407328, -1879447678, -1166128637, 386879375, 1798734769, 11538488, -1524914797, 326302712, 341587153, 359257592, -1193615287, -1142697104, 1544549531, -900881704, 1003340629, -1717436103, 1917111013, -907720377, -1656945255, 1903878608, -95319301, -1569353000, 778424010, 20029854, -126799677, -162005276, -304874137, -1771731248, -1200095026, 950601758, -1491738705, 1579742954, -1468507974, 225478070, -21696900, 1355089932, 981531209, -764634434, -1089431609, -275927212, 458541966, -2021222564, 466361107, 1713446654, -1704885570, -1395129296, 2003361665, -1086148695, 987017786, -2014898423, 610475148, -1817898166, 854630913, 1204010806, -798187367, -355280348, -1756256687, 699529395, 1559850491, 2085895511, -1720876946, 1947716893, 1006816527, -625396812, 654796297, -830752920, -1464804953, -1652351847, -245442491, 1504110778, -701206744, -36342584, 650201055, -481109428, 1833825388, -1982402315, -1163185267, -1257958365, 4127001, 191842886, 1240238278, 540858647, -505870094, -1814848769, 1303207040, -703299783, -272095514, 755250219, 1999646208, 1386365840, -1106477250, -1620263330, 654153675, 1568607303, -1119142799, 457699964, -884571528, -1742724766, 946815275, -2107624656, -115554225, -2122852308, -693305357, -134492519, -1562319998, 1217214953, 1401049834, 2018140452, -610561301, -477922572, -1574993052, -1266579969, -1851168721, 2034309318, 1572848694, -1670101512, 712180733, -1227372711, 623979710, -197536330, 1424960895, -1930803842, -961865922, -768603226, -687636843, 290756808, 580651777, 2049784717, 582957854, 439974810, 1994129272, -54704872, -712625120, 971288652, 59920954, 1942276717, -2094617969, 1060728466, -1482725836, 427299029, 1437944859, -49881930, -1826362734, -1642391633, 949788965, -513914651, 1772458008, 1970214098, -568342622, 1028648077, 2036297885, 1263440522, -1004730919, -1085917566, 1467303612, 2100008162, -376948671, 1655946083, 1574710285, -1827245369, 941582797, 1100183617, 1054893874, 94016590, 571839424, 732021367, -1868164751, 1800878734, 1842271255, 445246292, 1184552772, -1098715300, 505042939, 156194990, -618953991, -1783421871, -1816040925, -558586031, -591246044, -952021337, -924948168, -2007825989, -1032162415, 1643612166, 1309306875, 724310744, -1967973561, -1140742668, -801465908, 1411130848, 2015076839, -1868824222, -587297535, -76123786, 785286345, 842299453, 1029107548, 2023228051, 44265646, 3410491, -1993075931, 811010629, -259032419, 464796052, -638770232, -1056539872, -557386527, -1672331651, -1254127857, 1568731058, 1296904057, 791500055, 499492856, 135670358, -496453589, 1690392199, 1413138032, 2090417864, 972975020, -886384264, -14207628, -948132569, -1547834911, 1831319418, -1961160378, -1463917860, 751957691, -290865978, -1867140412, -179501250, -1670076921, 1541343659, -1694576504, -1628699498, -1374526360, -244595945, 1116193017, 490401721, 1311106209, 1332721130, -1788053527, -331972943, 214169163, -2116539568, 901740340, -1442197374, -265454944, 22791124, -805125491, -1954801171, 1641358150, -2141766976, 1799326119, 1517988067, 418990690, -1098572764, 1752451793, 791704983, 351931997, -787223628, -978352376, -1128997357, 1229083630, 2061574220, 1098996529, 327856442, -1527013814, 2118300923, 43501611, 1811521862, 238873852, -1497961354, -471776987, -29262713, 24861628, -1158944724, 1306171152, -493338540, -1003634945, 66328871, -505435380, -935296364, 1757086306, 1723681895, 1722309748, -320852418, -1861116978, 627967885, 1863591409, -1597955546, 360486806, 10076789, -944843454, 587411949, -1837373291, 270965463, 1125876201, -862347669, 274121377, -562346868, -53301718, 1992098884, 1768842132, -736309536, -1113544867, 2087815024, -1378382124, -1139007720, 1343851280, -1324656126, -736958695, -2131352581, 339158841, -805780241, 1554505663, 305916195, 52092928, -1245165869, -1241674014, -1979682524, 1256960461, 1899461148, 943610642, -774829315, -1998089269, 632080029, 1986493778, 1844135645, -1024009791, 822131256, -700670714, 733747451, 531428578, -659686405, -1805979900, -1177955671, -969497257, 1035808474, 279096261, -1487281090, -681745355, -1376776552, -1586716154, -604886884, 2097956477, -716181960, 544774915, -1757211585, -1098770947, 1776317028, -377824526, -2011315307, 1303666238, -1131233506, -703347298, -1115949322, 453701784, 1369467444, -1056206192, -539419579, -1048530402, -1840430637, 233864815, 1623804777, 841380228, 1306451581, -1419282236, -577737164, 1374827095, -1091441320, -1739453445, -1027460760, -1009999776, 1889055530, -434434648, -1277874080, -91623106, 852566376, 65724496, 1996571839, 1979399738, 608186243, 979202458, -1684961192, -1422571265, 1301800517, 1282596774, 1378148848, 887043840, 859250903, -1093382859, 170092992, -1730178244, -1863783667, 530504823, 1831698116, 145226569, 2063907057, 1013544860, 1981920733, -1012512678, 401904630, -255009592, 830465392, -1143369770, 2082048009, 249930485, 2026308821, -406117410, -1425172712, 685665480, 819969279, -350791882, -283382984, -1748231191, -2000786277, -2092243089, 1142124597, -1027506457, 1823426695, -1616817271, 1828852014, 684389606, -1410660409, -677930054, -2126201495, -2130993964, -349341923, 1099244980, -1216441521, 965000624, -330946446, 1964929323, 1120374521, 1331170073, -513176280, 51631952, -1732252424, 717813382, 14142599, 850341630, -1597334373, -1258059433, -1680791675, -2009607058, 119075652, 1759820987, -42364634, -1701650739, -1194275661, 457762594, -257366845, -202387581, -635449746, -625953084, -1508644246, -863506180, -554886512, -769718687, -664026091, -269862038, 1039006060, -1148819705, -736474984, 2029124726, 1427681684, -1848033964, 511861260, 987160135, -1505908052, -1941892909, -221966762, 1071197007, -64855884, -132842366, -1647240885, 676969669, 1785243380, -865128208, -1056219756, 1237417312, -768549230, 672707771, -1582908155, -680027181, -1886311627, -1277671555, 1071434612, 933776131, 848657977, -1561866677, -1650206450, 627993052, 2021219531, -1371682161, -2010942319, 548641798, 1278134416, 532992891, -174675671, -363032861, 494166453, -2060824088, -2043542795, -1787571788, -447084142, -898953565, -1205969706, -1665114102, 629590349, -1172265237, 432510219, -214427659, -836988207, 584967692, -1014290598, 369058092, -879313086, -661351359, -8406105, -774015739, -251696090, 2062968365, 1555708006, -680346706, 840011471, 1216924776, 254151622, 1652065948, -1788173938, 178867778, 292687262, 1441747231, -32437535, 1317012082, -1851212454, 27540823, -1254469170, 534889239, -1665261496, 330250069, 2112016665, -1698079180, -53766091, -1857462857, 1211673799, 875092183, -1178548929, -151321607, 1573447883, -258030181, 906556174, 499263684, -272168255, -620922885, 2029905396, -1082740101, 524096050, 115395567, -1250473881, -1919074161, -1776766198, 169000705, -722441754, -728129137, -1332961900, -1272872648, 1312011048, 1606081190, -1485401494, 1908413663, 1826863552, 647463869, 1864522249, -1892193764, 147371492, -644963138, -918104970, 354652187, 1376589620, 244906914, -1073117897, 1765766268, -1576427699, 566058228, -2076100337, 1925040109, -494250023, 1773679852, 2012236454, 1352353229, 1275193687, 446666694, -654414434, -862393862, 1126090365, 869396151, 275778838, -677946640, 670825104, 1872372465, -779246367, -990053997, 1058644588, -2047324017, 1817690414, 398936765, 526823962, 2028225944, 835296436, 308467485, -1683204397, 590036650, -966360089, -1545343391, -603459197, -1995744125, 1653227267, -440266679, -1785982976, 652704618, 1036365899, 666143277, -580341519, 1606433371, 1511319433, 454028944, 1597217452, 1154744695, 1811789400, -1975742572, 1981418416, 815471925, -503751593, 2077132414, 1352781072, -1039419458, -1745978789, 796328652, -1765014665, -864173636, 682505579, 647308115, 23536800, 1507947561, 128348782, -181376773, -1878901298, 1814419944, 961148039, -1581702025, -1811471914, 1665619605, -2128737079, 1527473874, -1823805801, -1064565421, 1013084544, -814540143, 1559419153, 240755642, -2140830335, 625260716, -848146277, -585864968, -176787304, -238220253, -1464625827, -734690875, -1519894376, 228493423, 1743645214, 1001487942, -213584205, 717883184, 954651933, 610238882, 1086893366, 1319332003, 1474030042, -81238742, -1687243411, 835094466, 538332691, -404602393, -949317265, 1367512965, 1463202107, 1216323186, 207051354, 1459247348, -887788261, 1897570069, 703580156, 1348693623, 1750337088, 1914285058, 585592056, -303709244, -910977134, 123554164, 1771855794, -486585138, -1478260266, 1990115496, 1066957230, -766591879, 2053219417, -2001379699, -679011909, 997070163, 127959453, 593427742, -1444223064, -1613926002, 2025907846, -1148310074, -1079424519, -544106045, 1185239281, 1194344804, -1668056377, 1168057284, -971239353, 116469933, -579607761, -1423467143, -1783915573, -1784157248, -966612983, 780066835, -1722765506, -1920029728, 1193656650, 859346253, 1907693516, -2025547459, 988759188, 1074251387, -64830813, -1378725039, -2144995674, -527313388, 2141391151, -1451178228, 1366896628, 21118927, 30410589, -1990076431, 1753406999, 1350295276, 937599354, 1206382975, -1256223865, -906064025, -1093533617, -1271168348, 1773868827, -164566896, -1327509889, 2111243126, 2097826729, -1387858971, 114618480, 586423378, -853527672, 1782965484, 242710750, -1972542269, 146144768, 739963565, 1293872094, -829339565, 973843650, -2069704088, -847725645, -1891325553, -529621341, 1072318757, -67343053, -912402612, -1777923884, 1668969903, -1772494719, -1977234179, 1243917567, 1339066636, 171144430, -216652341, -762723242, 636674833, 2064750069, -575694005, -179674193, 661215631, 110512240, -1370238796, -1333059988, 1523553316, -585401354, 2132361719, -2128478257, 1422725693, -1748397765, -1259902444, -928145831, -565581636, 1037097766, 1863584237, 511111309, 1420997198, -480113250, 779450506, 1372536624, 101589262, 1852795245, 1289491423, 1751469921, -1366099561, 770421473, 320818196, 2076155052, 1412432676, 2083955489, 1989443983, -1867839562, 1616705346, -775508249, 1576948444, -1181376841, 832970373, 1177348367, -505377085, -296664715, 1065211992, 2036864576, -1751402318, -1073281235, -1618474841, 922875955, 407461755, -212247443, 1241791795, -1444598062, 1546009375, -1649274226, -2077801702, 202793814, 1729349714, -572601700, -526938157, -1677305021, 156212745, -2090873831, -1866379385, 1958425586, -742759181, -1108485472, -1618569015, -1683997454, 502264504, 883612659, -1735905327, -1706726117, 69669752, -2054112561, -1882031950, -24647194, 1408382097, 55442473, -261800190, 1491040182, -1672207555, -197369002, -1837052435, 1267363066, -1076541275, 1916290380, -840240481, 1701415455, 743938863, 1060999236, -968615483, 1367440898, -1391334766, -1215096809, -1397472452, 939108464, 589417644, 2002364628, 1886046183, -397756473, -865487900, -6012885, 61981729, -229120570, 444063204, -1425126243, -939403369, -532113455, 176377113, 2045611638, -735077454, -1666534604, -1466521847, -1588766995, 1059276930, 438312321, 944601071, 1729306810, 1057343663, 1956700941, 1387953422, 1082101703, -1538841604, -1474314332, 46370173, -850386288, 675000312, -687851324, 1313261953, -693350037, -1895012155, 1696176850, 755385044, -1642165816, -1962507056, -601383952, 522211579, -767539807, 1925438752, 1889945413, 1306190399, -738259943, 1343920107, 1833827007, -1592983815, -994776253, 1077364684, 1844443665, -1896031998, 1092425442\nRange: 4286386825\n",
                newOutStream.toString());
    }

    @Test
    void getStatsEdgeCaseTest3() {
        // large array, limited ints
        arr = TestArrayReference.largeArrayLimitedValues;
        arrayStats.getStats(arr);
        assertEquals("Median: 0\nMean: -0.07199999999999979\nMode: -1\nRange: 2\n",
                newOutStream.toString());
    }

    @Test
    void getStatsEdgeCaseTest4() {
        // value extremes
        arr = new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE};
        arrayStats.getStats(arr);
        assertEquals("Median: -0.5\nMean: -0.5\nMode: 2147483647, -2147483648\nRange: 4294967295\n",
                newOutStream.toString());
    }

    @Test
    void getStatsEdgeCaseTest5() {
        // null input
        arr = null;
        assertThrows(IllegalArgumentException.class,
                () -> arrayStats.getStats(arr));
    }

    @Test
    void getStatsEdgeCaseTest6() {
        // very large array input, repeated values
        arr = TestArrayReference.largerArray;
        arrayStats.getStats(arr);
        assertEquals("Median: -7.67009845E7\nMean: -2.4683290433001626E7\nMode: -1171070400, -146303097, -365546677, -674183566, -768522073, -236680358, 937012492, -188090862, 581003774, -1371968055, -225433963, 1036413368, 1836863537, 1621159408, -2144926296, -480614711, 165381187, -1813887598, -1412887181, 912382143, 880479077, -1220888546, -1853694463\nRange: 4291249847\n",
                newOutStream.toString());
    }

}