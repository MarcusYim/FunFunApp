package com.marcus.funfunapp;

import android.util.Log;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VarHolder
{
    //final String[] levelImages = new String[] {"level_1_image.png", "level_2_image.png", "level_3_image.png", "level_4_image.png", "level_5_image.png", "level_6_image.png"};
    final String[] levelImages = new String[] {"level_1_image.png", "level_2_image.png", "level_3_image.png", "level_4_image.png"};
    final String[] levelNames = new String[] {"LEVEL 1", "LEVEL 2", "LEVEL 3", "LEVEL 4"};
    final String[] DialogueNames = new String[] {"Dialogue 1", "Dialogue 2", "Dialogue 3", "Dialogue 4", "Dialogue 5", "Dialogue 6", "Dialogue 7", "Dialogue 8", "Dialogue 9", "Dialogue 10", "Dialogue 11", "Dialogue 12", "Dialogue 13", "Dialogue 14", "Dialogue 15", "Dialogue 16", "Dialogue 17", "Dialogue 18", "Dialogue 19", "Dialogue 20", "Dialogue 21", "Dialogue 22", "Dialogue 23", "Dialogue 24", "Dialogue 25", "Dialogue 26", "Dialogue 27", "Dialogue 28", "Dialogue 29", "Dialogue 30", "Dialogue 31", "Dialogue 32", "Dialogue 33"};
    int[] dialoguesPerLevel = new int[] {8, 8, 9, 8};
    int[] previousDialogueSums;
    static boolean[][] starredArr;

    final String[][] allPin = {{"nǐ","wǒ","tā","tā","hǎo","jiào","xìng","ne","shén me","míng zì","xiǎo ","xiǎo jiě","xiān sheng"},
            {"hěn","gāo xìng","rèn shi","yě","hāi","ō","ma","en","bù","shì","zhōng guó","yīng guó","měi guó","rén","běi jīng","xī yǎ tú","zài"},
            {"hēi","zhè lǐ","yào","qù","nǎ lǐ","shàng hǎi","chū chāi","tài ..le","qiǎo","de","gōng zuò","gōng chéng shī","zhōng wén","lǎo shī","ō","zhè","míng piàn","zuò","xiè xie","kàn","gē ge","men","fú wù yuán","lái","ba"},
            {"ó","ò","bà ba","mā ma","jiě jie","mèi mei","dì di","yǒu","hé","jiā","nà","shéi","yī","liǎng","gè","sān","sì","dà","dà gē","hái zi","duì","zhào piàn","shuài","měi"},
            {"líng","èr","wǔ ","liù ","qī ","bā ","jiǔ ","shí ","jǐ ","jǐ diǎn","diǎn","bàn","kè","fēn","xiàn zài","shàng wǔ","xià wǔ"," tiān  ","jīn tiān","fēi","fēi jī","dào","a","tóng","zuò wèi","hào","kǒu","dēng jī","dēng jī kǒu"},
            {"míng tiān","máng","zǎo","zǎo shàng","wǎn","wǎn shàng","zhōng wǔ","duō","shì","huì","hái","tóng shì","qǐng","chī","fàn","wǔ fàn","wǎn fàn","méi","méi yǒu","péng you","tài guó","cài"},
            {"nín","xǐ huan","dǎ","qiú","lán qiú","kàn","shū","diàn","shì","diàn shì","yǐng","diàn yǐng","shàng","wǎng","shàng wǎng","chàng","gē","chàng gē","tīng","yīn yuè","tiào","wǔ","tiào wǔ","zhōu mò","yǒu de","shí hòu","yǒu de shí hòu","cháng cháng","jué  de","yì si","yǒu yì si","méi yǒu yì si ","dēng jī pái","nà"},
            {"qǐng ","wèn ","qǐng wèn ","yào","hē","jiǔ","hóng jiǔ","pí jiǔ","lǎng mǔ jiǔ ","xiāng bīn","wēi shì jì","ài ěr lán ","kě lè","kā fēi","chá","shuǐ","bēi","duì bu qǐ","kě shì","gěi","chǎo miàn","hái shì","yì dà lì","pī sà","fèn"},
            {"le ","nǎr ","mén ","zěn me ","cái ","wǎn diǎn ","kuài ","chū","lái ","chū lai ","yí huìr ","jiàn ","wēi xìn ","kě yǐ ","jiā ","ya ","sǎo ","jiè shào","yí xià ","huí"},
            {"hǎo ","jiǔ ","hǎo jiǔ ","hǎo jiǔ bú jiàn ","zěn me yàng ","cuò","bú cuò ","mǎ mǎ hū hū","yǒu yì diǎn ","lèi ","yīn wèi ","zuó tiān ","shuì jiào ","wèi shén me ","nǚ hái ","nán hái ","zuò ","yì qǐ ","liáo","liáo tiān ","hā hā ","dǒng ","piào liang ","tǐng ","rú guǒ...de huà ","xiǎng ","zhǎo ","chū qù ","wán ","děi","qǐng kè ","dōu "},
            {"bāng ","yù dìng ","shāo ","děng ","shí jiān ","wèi ","guì ","hào mǎ ","chóng fù ","bié ","kè qi ","rì "},
            {"zuì","jìn","zuì jìn ","tiān tiān","kāi ","kāi huì ","xià ","xià ge","shàng ","shàng ge","xīng qī ","kòng ","yuè ","hào ","gēn ","gāo zhōng","tóng xué ","jiàn miàn ","yào shì ","fāng biàn ","diàn huà ","dǎ diàn huà ","shuō ","wéi ","zhī dào ","fǎ guó ","xíng ","zài "},
            {"huí jiā ","zài ","zài jiàn "},
            {"jìn","jìn lai ","cài dān ","（y ì）diǎn ","xiǎo lóng bāo ","hǎo chī ","nǎi ","bō bà nǎi chá ","hǎo hē ","diǎn ","diǎn cài ","lái ","píng ","suàn le ","chǎo fàn ","xǐ ","xǐ shǒu ","xǐ shǒu jiān ","dà ","duō dà ","dà xué ","dà xué shēng ","xué shēng","jiāo ","wài guó ","hán guó ","jiā ná dà ","yìn dù ","xué xiào ","jié ","kè ","bié de ","bàn gōng shì ","zhǔn bèi ","wèn tí "},
            {"yǐ hòu ","xīng","xīng bā kè ","mǎi ","mǎi dān ","xiě ","de","màn ","dǒng ","xué ","hàn zì ","yǔ fǎ ","zhǐ ","nán ","róng yì ","zěn me ","gào su","dì ","zhāng ","zhǐ ","zhī ","bǐ ","yì biān ...yì biān ","niàn ","shēng cí ","liàn xí ","fù xí ","píng cháng ","shuō huà ","fā yīn ","kù ","xué xí ","dàn shì ","kǎo ","shì ","kǎo shì ","zhè me ","ài "},
            {"chú le..yǐ wài, hái","huí ","yóu jiàn","diàn zi yóu jiàn","zhèng zài","shén me shí hòu","huí lai","yīn yuè huì","bié rén","xī wàng","néng","zǒu","yǐ jīng"},
            {"huì","xiào","lā","nǎ lǐ nǎ lǐ","gōng sī","jiā","diàn zǐ","yóu xì","diàn zǐ yóu xì","zhēn de","nǎ","hái","měi","měi tiān","chuáng","qǐ chuáng","xǐ zǎo","jiù","yǐ qián","kāi shǐ","cān tīng","zǎo fàn","wǔ fàn","nǎo","diàn nǎo","zhuān yè"},
            {"hái kě yǐ","hòu tiān","shēng","rì","shēng rì","pài duì","tīng shuō","suǒ yǐ","suì","dàn shì","cì","zhǔ tí","yán sè","hóng","hóng sè","lán","lán sè","hēi","hēi sè","chuān","yī fu","hé shì","shāng diàn","qún zi","tiáo","bú guò","mǎi","huò zhě","cháng duǎn","dà xiǎo","shì shi","tā","yàng zi","yòng"},
            {"diàn","kù zi","jiàn","T- xù shān","chèn shān","dǎ zhé","shòu huò yuán","hào","kā fēi sè","huáng","huáng sè","nián","jīn nián","chūn tiān","dà jiā","shǎo","duō shǎo","qián","kuài","máo","fēn","bǎi","qiān","pián yi","yí yàng","shuā","shuā kǎ","fù","fù qián","xiàn  jīn","yí gòng","sǎo mǎ","huàn","bú yòng","zhǎo qián","dōng","xī","dōng xi"},
            {"cóng..dào..","zǒu","shān","gōng yuán","dì tiě","zhàn","xiān","rán hòu","xiān.. rán hòu..","zuì hòu","xiàn","běi","lù","chē","shàng chē","xià chē","zū","chū zū","chū zū chē","tè bié","dǔ chē","dǎ chē","huā","fēn zhōng","jǐn zhāng","lǎo tóng xué","nán ","nán jīng","sì chuān"},
            {"kuài lè","sòng ","lǐ wù ","měi ","xué qī ","jiào shì ","shàng kè ","chūn jià ","dǎ suàn ","lǎo jiā ","bà mā ","kāi ","kāi chē ","sòng ","zì jǐ ","gōng gòng ","qì chē ","gōng gòng qì chē ","ràng ","má fan ","méi shì ","gāo sù ","gōng lù ","gāo sù gōng lù "},
            {"zāo gāo ","yǔ ","xià yǔ ","xiū xi","yòu ","xià tiān ","jì jié","yì bān ","dù ","zuǒ ","yòu ","zuǒ yòu ","nà me ","rè ","chà bu duō ","huì ","tiān ","tiān qì ","bú dàn ...ér qiě ...","gèng ","fēi cháng ","shū fu","lěng ","yuē ","pá shān ","shān ","dōng tiān ","xuě ","xià xuě ","de shí hòu ","huá xuě ","hǎo wán","jí "},
            {"huí qù ","chéng shì ","tuī jiàn ","chéng dū ","gè ","zhǒng ","hǎo chī ","xióng māo ","huǒ guō ","jiǔ zhài gōu ","wā","qiū tiān ","bǐ ","lǜ ","gāng cái ","yù bào ","nuǎn huó","wǎng shàng ","piào ","fēi jī piào "},
            {"suǒ yǒu de ","dài ","là ","shǒu jī ","qián bāo ","kǎ ","xìn yòng kǎ ","shén me de","xié zi","fā ","chū fā ","chǎng ","jī chǎng ","duǎn xìn ","zhù ","yí lù píng ān ","è ","huǒ guō ","là ","méi guān xi ","tāng ","fàn guǎn ","dì dào ","xīn xiān ","wèi dào ","guo","yǒu míng ","tā ","hǎi dǐ lāo ","liú yī shǒu "},
            {"wèi zi","ná ","qián biān ","zhuō","zuò ","xiǎo chī ","měi jiǎ ","shī fu ","miǎn fèi ","dì fang ","guàng guang ","gāng ","gēn ","ròu ","zhū","zhū ròu ","niú","niú ròu ","yáng","yáng ròu ","jī","jī ròu ","yú ","xiā ","sù cài ","qīng cài ","bái cài ","yù mǐ ","dòu fǔ ","tián ","wǎn ","mǐ fàn ","pán ","yìn dù ","fēi bǐng ","gòu "},
            {"shàng cài ","wán ","yǐn liào ","suān ","suān nǎi ","guàn ","bīng ","hóng chá ","liáng chá ","zuǒ liào ","xuǎn ","kǒu wèi ","fàng","là jiāo ","cōng ","jiāng ","suàn ","yán ","cù ","jiàng yóu ","zhī má jiàng ","wèi jīng ","liáng bàn ","shuǐ guǒ ","shuāng ","kuài zi","sháo zi","xiē ","cān jīn zhǐ "},
            {"shàng ","huó dòng ","bái tiān ","kuān ","zhǎi ","xiàng zi","tǐ yàn ","lǎo ","wén huà ","yè shì ","lǐ biān ","shì ","zhōng shì ","yuán ","kě ài ","dì fang ","dēng lóng ","jiǔ diàn ","lí ","yuǎn ","xiǎo shí "},
            {"guò ","lù kǒu ","dēng ","hóng lǜ dēng ","wǎng ","guǎi ","yē","dǎo yóu ","dǎ kāi ","dì tú ","chù","shòu piào chù","nán biān ","yóu kè ","zhōng xīn ","jì niàn pǐn ","páng biān ","bó wù guǎn ","yì zhí ","yī yuàn ","zhōng jiān ","xī nán biān ","tiān é ","hú ","yóu jú ","míng xìn piàn ","jì ","jiā rén "},
            {"yí ","xǐ ","píng guǒ ","lán méi ","cǎo méi ","pú tao ","lí ","xiāng jiāo ","xī gua ","zhòng ","zhù ","xī ěr dùn ","xīng ","jiē ","jiē jī ","fú wù ","āi yā ","wàng ","lóu "},
            {"léi ","léi yǔ ","háng bān ","lǚ kè ","qíng kuàng ","kàn qíng kuàng ","kě néng ","shū diàn ","běn ","zhōng tóu ","zhuō yóu ","yǐ wéi ","hòu lái ","wù jī ","xíng li ","tuō yùn ","bàn "},
            {"liǎ","fèi ","lì qi","chá xún ","xiàng ","hǎo xiàng ","diū ","jiù ","jì","jì de","zhào xiàng","zhào xiàng jī ","chōng diàn ","chōng diàn qì ","xiǎng ","xiǎng qi lái ","xiǎng bu qǐ lái ","zhāo jí ","tián xiě ","xìn xī ","qīng chu ","xiāng zi "},
            {"rè nào ","pái duì ","suàn ","guò ","jié ","guó qìng jié ","pù bù ","mài ","zàng zú ","shù ","zhǎng ","huā ","kāi ","fēng jǐng ","yóu yǒng ","zhǔn ","xiǎo xīn ","wēi xiǎn ","jiù ","jiù mìng ","yān sǐ ","hài ","chǎo ","cuò "},
            {"kě xī ","guǎng gào ","fù jìn ","kè zhàn ","zǒu lù ","jǐ","kǎo lǜ ","wǎn","biāo zhǔn ","biāo zhǔn jiān ","dān rén jiān ","mǎn ","tào fáng ","liǎng shì yì tīng ","wèi shēng jiān ","rén mín ","bì ","rén mín bì ","yā jīn ","tuì ","tuì fáng ","huán ","wò shì ","kè tīng ","lìng wài ","fáng jiān ","xī yān "}};

    final String[][] allChinese = {{"你","我","他","她","好","叫","姓","呢","什么","名字","小","小姐","先生"},
            {"很","高兴","认识","也","嗨","噢","吗","嗯","不","是","中国","英国","美国","人","北京","西雅图","在"},
            {"嘿","这里","要","去","哪里","上海","出差","太..了","巧","的","工作","工程师","中文","老师","噢","这","名片","做","谢谢","看","哥哥","们","服务员","来","吧"},
            {"哦","哦","爸爸","妈妈","姐姐","妹妹","弟弟","有","和","家","那","谁","一","两","个","三","四","大","大哥","孩子","对","照片","帅","美"},
            {"零","二","五","六","七","八","九","十","几","几点","点","半","刻","分","现在","上午","下午","天 ","今天","飞","飞机","到","啊","同","座位","号","口","登机","登机口"},
            {"明天","忙","早","早上","晚","晚上","中午","多","事","会","还","同事","请","吃","饭","午饭","晚饭","没","没有","朋友","泰国","菜"},
            {"您","喜欢","打","球","篮球","看","书","电","视","电视","影","电影","上","网","上网","唱","歌","唱歌","听","音乐","跳","舞","跳舞","周末","有的","时候","有的时候","常常","觉得","意思","有意思","没有意思","登机牌","那"},
            {"请","问","请问","要","喝","酒","红酒","啤酒","朗姆酒","香槟","威士忌","爱尔兰","可乐","咖啡","茶","水","杯","对不起","可是","给","炒面","还是","意大利","披萨","份"},
            {"了","哪儿","门","怎么","才","晚点","快","出","来","出来","一会儿","见","微信","可以","加","呀","扫","介绍","一下","回"},
            {"好","久","好久","好久不见","怎么样","错","不错","马马虎虎","有一点","累","因为","昨天","睡觉","为什么","女孩","男孩","坐","一起","聊","聊天","哈哈","懂","漂亮","挺","如果...的话","想","找","出去","玩","得","请客","都"},
            {"帮","预订","稍","等","时间","位","贵","号码","重复","别","客气","日"},
            {"最","近","最近","天天","开","开会","下","下个","上","上个","星期","空","月","号","跟","高中","同学","见面","要是","方便","电话","打电话","说","喂","知道","法国","行","在"},
            {"回家","再","再见"},
            {"进","进来","菜单","（一）点","小笼包","好吃","奶","波霸奶茶","好喝","点","点菜","来","瓶","算了","炒饭","洗","洗手","洗手间","大","多大","大学","大学生","学生","教","外国","韩国","加拿大","印度","学校","节","课","别的","办公室","准备","问题"},
            {"以后","星","星巴克","买","买单","写","得","慢","懂","学","汉字","语法","只","难","容易","怎么","告诉","第","张","纸","支","笔","一边..一边..","念","生词","练习","复习","平常","说话","发音","酷","学习","但是","考","试","考试","这么","爱"},
            {"除了..以外.还","回","邮件","电子邮件","正在","什么时候","回来","音乐会","别人","希望","能","走","已经"},
            {"会","笑","啦","哪里 哪里","公司","家","电子","游戏","电子游戏","真（的）","哪","还","每","每天","床","起床","洗澡","就","以前","开始","餐厅","早饭","午饭","脑","电脑","专业"},
            {"还可以","后天","生","日","生日","派对","听说","所以","岁","但是","次","主题","颜色","红","红色","蓝","蓝色","黑","黑色","穿","衣服","合适","商店","裙子","条","不过","买","或者","长短","大小","试试","它","样子","用"},
            {"店","裤子","件","T-恤衫","衬衫","打折","售货员","号","咖啡色","黄","黄色","年","今年","春天","大家","少","多少","钱","块","毛","分","百","千","便宜","一样","刷","刷卡","付","付钱","现金","一共","扫码","换","不用","找钱","东","西","东西"},
            {"从..到..","走","山","公园","地铁","站","先","然后","先..然后..","最后","线","北","路","车","上车","下车","租","出租","出租车","特别","堵车","打车","花","分钟","紧张","老同学","南","南京","四川"},
            {"快乐","送","礼物","每","学期","教室","上课","春假","打算","老家","爸妈","开","开车","送","自己","公共","汽车","公共汽车","让","麻烦","没事","高速","公路","高速公路"},
            {"糟糕","雨","下雨","休息","又","夏天","季节","一般","度","左","右","左右","那么","热","差不多","会","天","天气","不但..而且..","更","非常","舒服","冷","约","爬山","山","冬天","雪","下雪","的时候","滑雪","好玩","极"},
            {"回去","城市","推荐","成都","各","种","好吃","熊猫","火锅","九寨沟","哇","秋天","比","绿","刚才","预报","暖和","网上","票","飞机票"},
            {"所有的","带","拉","手机","钱包","卡","信用卡","什么的","鞋子","发","出发","场","机场","短信","祝","一路平安","饿","火锅","辣","没关系","汤","饭馆","地道","新鲜","味道","过","有名","它","海底捞","刘一手"},
            {"位子","拿","前边","桌","坐","小吃","美甲","师傅","免费","地方","逛逛","刚","跟","肉","猪","猪肉","牛","牛肉","羊","羊肉","鸡","鸡肉","鱼","虾","素菜","青菜","白菜","玉米","豆腐","甜","碗","米饭","盘","印度","飞饼","够"},
            {"上菜","完","饮料","酸","酸奶","罐","冰","红茶","凉茶","佐料","选","口味","放","辣椒","葱","姜","蒜","盐","醋","酱油","芝麻酱","味精","凉拌","水果","双","筷子","勺子","些","餐巾纸"},
            {"上","活动","白天","宽","窄","巷子","体验","老","文化","夜市","里边","式","中式","圆","可爱","地方","灯笼","酒店","离","远","小时"},
            {"过","路口","灯","红绿灯","往","拐","耶","导游","打开","地图","处","售票处","南边","游客","中心","纪念品","旁边","博物馆","一直","医院","中间","西南边","天鹅","湖","邮局","明信片","寄","家人"},
            {"咦","洗","苹果","蓝莓","草莓","葡萄","梨","香蕉","西瓜","重","住","希尔顿","星","接","接机","服务","哎呀","忘","楼"},
            {"雷","雷雨","航班","旅客","情况","看情况","可能","书店","本","钟头","桌游","以为","后来","误机","行李","托运","办"},
            {"俩","费","力气","查询","像","好像","丢","就","记","记得","照相","照相机","充电","充电器","想","想起来","想不起来","着急","填写","信息","清楚","箱子"},
            {"热闹","排队","算","过","节","国庆节","瀑布","卖","藏族","树","长","花","开","风景","游泳","准","小心","危险","救","救命","淹死","害","吵","错"},
            {"可惜","广告","附近","客栈","走路","几","考虑","晚","标准","标准间","单人间","满","套房","两室一厅","卫生间","人民","币","人民币","押金","退","退房","还","卧室","客厅","另外","房间","吸烟"}};

    final String[][] allEnglish = {{"you","I,me","he, him","she,her","good,nice, Okay","to be called, to call for","one's surname is, family name, surname","What about...? And..?","What?","name","small, tiny, young","young lady, miss","Mister (Mr.), husband"},
            {"very","happy, glad","to recognize","also, too","hi","oh","Yes/No (question fial interrogative particle)","Umm","no","is, are, am, yes, to be","China, Middle Kingdom","Britain, England","United States, USA","person, people","Beijing, capital of Peoples Republic of China","Seattle, Washington State","be at, be in, be on"},
            {"hey","here","will be, will do","to go","where","Shanghai","to go on an official or business trip","extremely","coincidental","possessive particle","work, job","engineer","Chinese, Chinese written language","teacher","Oh, interjection for pain or sad","this","business card","make; to do","to thank, thanks","to see, to watch, to look, read, to look after","older brother","(plural marker for pronouns and a few animate nouns)","waiter, waitress","to come","sentence final particle for suggestion"},
            {"oh? really? is that so?","oh!","dad, father","mother, mummy, mom","older sister","younger sister","younger brother","to have/has, exist, there is, there are","and, with","home, family","that","who, whom","one","two","measure word for general objects","three","four","big","eldest brother","child","correct","photo, photograph","handsome","beautiful, pretty"},
            {"Zero","two","five","six","seven","eight","nine","ten","how many, few","what time?","O‘clock","half","quarter of an hour","minute","now","morning","afternoon","day","today","to fly","aircraft, airplane","to arrive, until (a time)","interjection of surprise, ah ","same","seat","number","mouth, entrance","to board a plane","departure gate (aviation)"},
            {"tomorrow","busy","early","early morning","late","evening, night","noon, midday","much, many","thing, matter","meeting","also, still","colleague, co-worker","to invite, to treat, please","eat","meal, cooked rice","midday meal, lunch","dinner","not","have not, has not","friend","Thailand, Thai","food, cuisine, vegetables"},
            {"honorific for you","to like","hit, beat, fight","ball","basketball","to see, to watch, to read, to look, to look after","book","electric, electricity, electrical","vision","television, TV","shadow","film, movie","go, attend","net, network","to be on the internet","to sing","song","to sing (a song)","to hear; listen","music","to jump","dance","dance","weekend","some, some of (not for measure something)","time, moment, period","sometimes","often","to think, to feel","meaning"," interesting, fun, enjoyable","boring, not interesting","boarding pass","then, in that case"},
            {" please (do something), to treat (to a meal etc) to invite","to ask","Excuse me, may I ask...?","to demand, to want","to drink","spirits, alcoholic beverage","red wine","beer","rum ","champagne","whiskey","Ireland","cola","coffee","tea","water","measure word for glass, cup","sorry","but, however","to give","stir-fried noodles","or (for question)","Italy, Italian","pizza","measure word for dishes, jobs"},
            {"particle of completed action","where","door, gate","how? how come?","not until, only then","delay (only for flight, bus, boat, train, subway time )","fast, quick","out","to come","to come out","a little while"," to see, to meet","WeChat (a mobile text and voice messaging communication APP)","can, may (for permission)","to add, to plus","interjectory particle used to soften a question or express intimate terms","to scan","to introduce","a bit (used after a verb to indicate a short, quick, random, informal action)","to return"},
            {"very"," long (time)","a long time","Long time no see","how about that? Is it okay?","wrong, incorrect","pretty good","so-so","to be a little","  tired","because","yesterday","to go to bed, to sleep","why?","girl"," boy","travel by, sit"," together","to chat","to chat","ha-ha (the sound of laughing)","to understand"," pretty, beautiful"," very","if ..."," to think, to want"," to find, to look for","to go out","  to play "," have to","to invite somebody "," all, both"},
            {"to assist, to help","to reserve, to book, to subscribe for","slightly","to wait","time","measure word (polite way) for people","expensive, noble"," number","repeat","don't","polite"," day of the month (formal), sun"},
            {"most, -est, of superlative degree","close, near","recent, recently","everyday"," to hold (meeting, party)"," to hold or attend a meeting","down, below","next","upon, up","previous","week"," free time"," moon, month"," day of the month","with","high school","classmates, schoolmates","to meet up"," if","convenient","telephone, phone","to make a telephone call"," to speak, to say","hello?","to know","France","all right, ok.","at, in,on"},
            {" to return home","again (for future)","goodbye"},
            {"to enter","to come in","menu","a little bit","steamed dumpling","delicious","milk","Boba(Bubble)Milk Tea","tasty (of beverages)","to order","to order dishes (in a restaurant)","to order","measure word for bottled liquid, etc.","forget it, never mind","fried rice","to wash","to wash hands","restroom","big, huge, old (for asking age: how old)","how old, how big ","college, university","university student, college student","student","to teach","foreign country","South Korea","Canada","India","school","measure word for class period","class, lesson, course","else, other","office","preparation, to prepare","question, problem"},
            {"after, in the future","star","Starbucks(US coffee shop chain)","buy, purchase","pay the restaurant bill","to write","Descriptive particle (grammar)","slow(ly)","to understand","learn, study","Chinese character","grammar","only, just","difficult","easy","how","to tell","Ordinal numbers (sequence number)","measure word for flat objects (such as paper or newspaper)","paper","measure word for sticks (such as pens, pencils)","pen","at the same time (to do two actions)","to read aloud","new word, vocabulary","practice, drill","review","usually","talk","pronunciation","cool","to learn, to study","but, however","to take or give a test","test, to try ","to take or give a test/test","so, such, like this","to love"},
            {"besides...also","to reply","mail","email","in process of. In the middle of (doing something)","when, what time","to come back","concert","other people","hope, wish","can, capable, permitted to","to walk,  to leave","already"},
            {"can (by study)","to smile ,to laugh, to laugh at","final particle of assertion(soften tone)","humble attitude","company","measure word for families or businesses","electronic, electron","game","computer and video games"," real, really","which","also, still","each, every","every day, everyday","bed","get up","take a shower","right away","before, ago","to begin, to start, beginning","restaurant, cafeteria, dining room","breakfast","lunch","brain","computer","major(in college), specialized field, professional"},
            {"not bad","the day after tomorrow","to be born, to give birth","day (formal), sun"," birthday","party","heard","therefore, so","years old","however, but","times of an action is performed (frequency)","theme","color","red","red color","blue","blue color","black","black color","to wear","clothes, clothing","suitable, appropriate,","shop, shopping mall, store","skirt, dress","measure word for long thin things (dress, river, road, trousers etc)","but","buy, purchase","or","length","size","try it","It ","shape, appearance","to use"},
            {"store, shop","Trousers, pants"," measure word for clothing","T-shirts","shirt","discount","Sales assistant","size, NO.","brown, coffee color","yellow","yellow (color)","year","this year","spring","everybody","less","how much, how many","money, currency","dollar（measure word for Chinese monetary unit)","dime (one-tenth of yuan)measure word for Chinese money","cent measure word for Chinese money)","hundred","Thousands","Cheap","the same, as … as …","to brush, swipe","to pay with a credit card","pay","to pay money","cash","in all, altogether, in total","Scan code","to change, to exchange","not need","to give change","east","west","thing, stuff"},
            {"from..to..","to walk, to leave","Mountain, hill","Park","subway, metro","station, measure word for stops or stations","first, in advance","then (afterwards), after that","first..then..","final, last","line","north","road, path, street","vehicles, car","to get on or into (a bus, train, car etc)","to get off or out of (a bus, train, car etc)","to rent, to lease, rental","to rent out","taxi","special, particular, especially","traffic jam ","take taxi","to spend (money, time)","minute(s)","nervous","old classmates, old schoolmates","south","Nanjing","Sichuan"},
            {"happy, merry","to give (gift).","gift ","each, every","school term, semester","classroom","to go to class, to attend class, begin class, during class","spring break","plan, intend","hometown","parents","drive (vehicle, airplane, boat)","to drive a car","to see off, to send off","oneself","public","automobile, car","bus (for public transit)","to allow somebody to do something, to cause somebody to do something"," bother somebody, troublesome","don't worry，no problem, it's okay","high speed","highway, public road","highway, freeway"},
            {"too bad, terrible","rain","to rain, rainy","rest, to rest","again","summer","season","general, generally","degree","left","right","about, roughly, approximately","so, in that way","heat, hot","almost","will","sky","weather","not only.. but also ..","even more, even further","extreme, extremely","comfortable, feeling well","cold","make an appointment","hiking, to climb a mountain","hill, mountain","winter","snow","to snow"," when, while","to ski, skiing"," fun","extremely"},
            {"to return, to go back","town, city","recommend","Chengdu (capital city of Sichuan) ","each, every","measure word for kind of, type of, sort of","tasty, delicious","panda","hotpot","Jiu Zhai Valley, Sichuan","wow (interjection used to express surprise and intimate terms)","autumn, fall","to compare with","green","just now, a moment ago","to forecast, forecast","warm","on-line","ticket","plane ticket"},
            {"all of","to bring","to leave (something) behind","cell phone","purse, wallet","card","credit card","and so on","shoes","to send","to leave, departure (airport)","field","airport","text message","to wish (make wish)","to have a pleasant and safe journey, Bon voyage!","hungry","hotpot","hot, spicy","it doesn't matter","soup","restaurant","authentic","fresh (experience, food, air etc), freshness","flavor","particle used after a verb to express a past experience","famous, well-known","it, other","a Hotpot restaurant name","a Hotpot restaurant name"},
            {"seat, space","to hold, to take","the front side, in front of","measure word for table, table ","to sit","snacks, refreshments","nail art","master worker, master","free (of charge)","place","to roam around","just","to follow","meat","pig","pork","cow, ox, bull","beef","sheep, goat","lamb","chicken","chicken meat","fish","shrimp, prawn","vegetable dish","green vegetables","Chinese cabbage","corn","tofu","sweet","measure word for bowl, bowl ","(cooked) rice","measure word for plate, plate ","India","Naan Bread (India)","enough"},
            {"serve the dishes (of food)","finished","beverage","sour, sore, acid","yogurt","measure word for jar, can","ice","black tea","Chinese herb tea","spices, sauce","to choose, to select","(a persons preferences) flavor","to put, to place","hot pepper, chili","green onion","ginger","garlic","salt","vinegar","soy sauce","sesame paste","monosodium glutamate (MSG)","salad with dressing, cold vegetables dressed with sauce (e.g. cole slaw)","fruit","pair, measure word for things that are in pairs, such as chopsticks and shoes","chopsticks","scoop","measure word for some","paper napkin"},
            {"to go ","activity ","daytime","wide","narrow ","Alley ","experience","old","culture ","night market ","inside ","style","Chinese style ","round ","cute","place","lantern","hotel ","away from ","far ","hour "},
            {"cross, pass","intersection","light, lamp","traffic lights","towards ","to turn ","Yeah!","tour guide ","open","map ","place ","ticket office ","south side ","tourist ","center ","souvenir ","beside","museum","straight, continuously, always ","hospital ","middle ","southwest ","swan ","lake ","post office ","postcard ","mail","(family members) family"},
            {"Eh? ","to wash ","apple ","blueberry ","strawberry ","grape ","pear","banana","watermelon","heavy ","to live (in a certain place)  ","Hilton ","star ","to pick up (phone/somebody), to catch (something)  ","pick someone up at the airport ","service ","Ay ya","to forget  ","building, floor (of a multi-level building) "},
            {"thunder ","thunderstorm","flight","passenger","situation","It depends","probably, maybe ","bookstore","measure word for books","hour","board games","to assume erroneously","later (cannot use it for future tense)","missed flight ","luggage ","to check (luggage)","to handle. to do "},
            {"two (colloquial equivalent of 两个）"," to take (effort), to spend ","strength, effort ","inquiry","to look like ","to seem ","lose ","just ","to record ","to remember ","take photograph","camera","charge (a battery)","charger ","to think","to remember, to recall","can't remember","worry ","fill in","information ","clear","suitcase, box "},
            {"lively","line up","count as","to celebrate","festival ","National Day","waterfall","sell","Tibetan","tree","to grow","flower","to bloom (flower)","scenery","to swim","to allow; to be allowed (verb)","be careful!","dangerous","save","save life","to drown","harm","to quarrel, noisy","fault"},
            {"unfortunate (a feeling of sadness or sympathy for the suffering or unhappiness of others）","advertisement","nearby area","Inn","walk","few","consider, consideration","measure word for nights","standard, criterion","standard, criterion","single room","full","suite","two bedrooms and one living room","bathroom","the people","currency","RMB (Chinese currency)","deposit","to return (purchased item), refund ","check out","to return (borrowed item) ","bedroom","living room","in addition, furthermore","room ","to smoke "}};

    public VarHolder(boolean calculatePreviousSums)
    {
        if (calculatePreviousSums)
        {
            previousDialogueSums = new int[dialoguesPerLevel.length];

            int count = 0;
            for (int i = 0; i < dialoguesPerLevel.length; i++)
            {
                previousDialogueSums[i] = count;
                count += dialoguesPerLevel[i];
            }
        }
    }

    public VarHolder()
    {
        //too lazy to change all of the class calls
    }

    public String[] getLevelImages()
    {
        return levelImages;
    }

    public String[] getLevelNames()
    {
        return levelNames;
    }

    public int getNumImages()
    {
        return levelImages.length;
    }

    public int getDialoguesPerLevel(int index)
    {
        return dialoguesPerLevel[index];
    }

    public int getNumPreviousDialogues(int index)
    {
        return previousDialogueSums[index];
    }

    public String[] getSubDialogueArray(int start, int end)
    {
        String[] ret = new String[end - start + 1];

        ret[0] = "All";

        int count = 1;

        for (int i = start; i < end; i++)
        {
            ret[count] = DialogueNames[i];
            count++;
        }

        return ret;
    }

    public List<String>[] getSubChineseArray(int endDialogue, int startDialogue)
    {
        List<String>[] ret = new List[endDialogue - startDialogue];

        int count = 0;
        for (int i = startDialogue; i < endDialogue; i++)
        {
            ret[count] = Arrays.asList(allChinese[i]);
            count++;
        }

        return ret;
    }

    public List<String>[] getSubPinArray(int endDialogue, int startDialogue)
    {
        List<String>[] ret = new List[endDialogue - startDialogue];

        int count = 0;
        for (int i = startDialogue; i < endDialogue; i++)
        {
            ret[count] = Arrays.asList(allPin[i]);
            count++;
        }

        return ret;
    }

    public List<String>[] getSubEnglishArray(int endDialogue, int startDialogue)
    {
        List<String>[] ret = new List[endDialogue - startDialogue];

        int count = 0;
        for (int i = startDialogue; i < endDialogue; i++)
        {
            ret[count] = Arrays.asList(allEnglish[i]);
            count++;
        }

        return ret;
    }

    public boolean[][] getStarredArr()
    {
        return starredArr;
    }

    public boolean[][] getSubStarredArray(int endDialogue, int startDialogue)
    {
        if ((endDialogue - startDialogue) == allEnglish.length)
        {
            return starredArr;
        }

        boolean ret [][] = new boolean[endDialogue - startDialogue][];

        int count = 0;
        for (int i = startDialogue; i < endDialogue; i++)
        {
            ret[count] = starredArr[i];
            count++;
        }

        return ret;
    }

    public void initStarredArray()
    {
        boolean temp [][] = new boolean[allEnglish.length][];

        int count = 0;
        for (int i = 0; i < allEnglish.length; i++)
        {
            temp[count] = new boolean[allEnglish[i].length];
            count++;
        }

        starredArr = temp;
    }

    public ArrayList<Integer> getStarredChecked()
    {
        ArrayList<Integer> ret = new ArrayList<>();

        int count = 0;
        for (int i = 0; i < starredArr.length; i++)
        {
            for (int x = 0; x < starredArr[i].length; x++)
            {
                if (starredArr[i][x])
                {
                    ret.add(count);
                }

                count++;
            }
        }

        return ret;
    }
}
