/**
 * Created by Y.S.K on 2017/8/5 in wx_bot.
 */
public class Test2 {
    private   String  head="1";


    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public static void main(String[] args) {
        Test2 test2=new Test2();
         String  a=test2.getHead()+"2";
        test2.setHead("3");
        System.out.println(a);
     
    }
}
