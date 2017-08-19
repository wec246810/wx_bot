import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ysk.dao.SendRecordDao;
import com.ysk.dto.EUDataGridResult;
import com.ysk.entity.SendRecord;
import com.ysk.service.Impl.WxInitImpl;
import com.ysk.util.HttpUtils;
import com.ysk.util.ToolUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Created by Y.S.K on 2017/8/1 in wx_bot.
 */
public class App {
     static Logger logger= org.apache.log4j.Logger.getLogger(App.class);
//    private  final Logger logger= LoggerFactory.getLogger(App.class);
//    @Autowired
//    private UserDao userDao;
//    @Autowired
//    private CDK cdk;
//    @Autowired
//    private WxInitImpl WxInit;
    @Autowired
    private ToolUtils toolUtils;
//    @Resource
//   static private CDKDao cdkDao;
 public static  void main(String[] args) {
//        ToolUtils toolUtils=new ToolUtils();
        ApplicationContext applicationContext= new ClassPathXmlApplicationContext("classpath:/spring/spring-dao.xml");
        WxInitImpl wxInit=(WxInitImpl)applicationContext.getBean("wxInit");
//            SendRecordDao sendRecordDao=(SendRecordDao)applicationContext.getBean("sendRecordDao");
//        UserService userService=(UserService)applicationContext.getBean("userService");
//        CDK cdk=new CDK();
//        CDKDao cdkDao=(CDKDao) applicationContext.getBean("cdkDao");
//        for(int i=0;i<300;i++){
//        cdk.setValue(toolUtils.creatCDK(16));
//        cdk.setCreateTime(new Timestamp(new Date().getTime()));
//        cdk.setDelayDay(100);
//        cdkDao.addCDK(cdk);}
//        System.out.println(toolUtils.creatCDK(16));

//        System.out.println(userService.getMyTime("aa"));

//        wxInit.getQrCode();
//        wxInit.BeforeWXInit();
//        wxInit.wxInit();
//        wxInit.getMyFriendList();
//        wxInit.sendMessage("你好",wxInit.getMyMan());
     HttpUtils httpUtils=new HttpUtils();
     String s= httpUtils.doPost("http://118.190.78.120/xservice/login","type=uuid&plat=nvpnapp&userid=hatvpn_7e694007de07e38b");
     System.out.println(s);

        ToolUtils toolUtils=new ToolUtils();
     System.out.println(toolUtils.getOrderNo());

//     List<SendRecord> sendRecord=sendRecordDao.querySendRecordByUserName("aa");

     //设置分页的参数
//     PageHelper.startPage(1, 10);
//     //查询数据
//     List<SendRecord> list=sendRecordDao.querySendRecordByUserName("aa");
//     //创建一个返回值对象
//     EUDataGridResult result=new EUDataGridResult();
//     result.setRows(list);
//     Page<SendRecord> listCountry = (Page<SendRecord>)list;
//     System.out.println(listCountry.getTotal());
//     System.out.println(listCountry);
//     for (SendRecord sendRecord : list) {
//         System.out.println(sendRecord.getCreateTime());
//     }
//     取记录总条数
//     PageInfo pageInfo=new PageInfo(list);
//     result.setTotal(pageInfo.getTotal());
//     System.out.println(result);
//     return result;
 }
}
