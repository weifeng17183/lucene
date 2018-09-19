package net.justfind.test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import net.justfind.bean.Pager;
import net.justfind.entity.LuceneGoods;
import net.justfind.lucene.impl.LuceneServiceImpl;

import org.junit.Test;

/**
 * <br>
 * <b>功能：</b>详细的功能描述<br>
 * <b>作者：</b>xiaogl<br>
 * <b>日期：</b> 2015年5月21日 <br>
 * <b>版权所有：<b>版权所有(C) 2015，快找网络<br>
 */
public class LuceneTest {
    private LuceneServiceImpl luceneService = new LuceneServiceImpl();

    @Test
    public void testSave() {
        LuceneGoods goods = new LuceneGoods();
        BigDecimal bigLoanAmount = new BigDecimal("0");
        goods.setId("21");
        goods.setCost(bigLoanAmount);
        goods.setPrice(bigLoanAmount);
        goods.setWeight(0.0);
        goods.setScore(11);
        goods.setIsBest(false);
        goods.setIsHot(null);
        goods.setFreezeStore(null);
        goods.setGoodsAttributeValue0(null);
        goods.setStatus(1);
        goods.setName("01/8599鞋纽扣圆形花形");
        goods.setGoodsImageStore("[{\"id\":\"8599\",\"path\":\"/upload/image/201504\",\"description\":\"\",\"orderList\":null,\"sourceImageFormatName\":\"jpg\"}]");
        goods.setOutLength(6);
        goods.setHtmlPath("/html/201504/57a34f7966d4407b844447862020fad1.html");
        goods.setOutWidth(10);
        goods.setInLength(16);
        goods.setInWidth(null);
        goods.setIntroduction("装饰五金扣方形围边椭圆组合链条压滴油");
        goods.setCategoryName("鞋纽扣");
        goods.setProviderId("aaa");
        luceneService.save(goods);
    }

    @Test
    public void testUpdate() {
        LuceneGoods goods = new LuceneGoods();
        goods.setId("9");
        goods.setName("01/18467装饰五金扣方形围边椭圆组合链条压滴油");
        goods.setOutLength(5);
        goods.setOutWidth(2);
        goods.setInLength(4);
        goods.setInWidth(1);
        goods.setIntroduction("装饰五金扣方形围边椭圆组合链条压滴油");
        luceneService.update(goods);
    }

    @Test
    public void testDelete() {
        luceneService.deleteGoods("402880e94cd99444014cd9e94e72245e,21");
    }
    
    @Test
    public void testBatchDelete(){
        luceneService.batchDelete("'1','2',3,4,5,6,7,8,9");
    }

    @Test
    public void testQuery() {
        Pager pager = new Pager();
        pager.setKeyword("扣");
        //        pager.setOrderBy("goodsId");
        //        pager.setOrder(Order.desc);
        Pager queryByPager = luceneService.queryByPager(pager,0,2,null,null,null,null,null,null,null);
        if (queryByPager != null) {
            List<LuceneGoods> result = (List<LuceneGoods>) queryByPager.getResult();
            for (LuceneGoods goods : result) {
                String name = goods.getName();
                String nameRe = name.replace("<font color='red'>", "&");
                nameRe = nameRe.replace("</font>", "*");
                char[] charArray = nameRe.toCharArray();
                int i=0,j=0,k=0;
                for (char c : charArray) {
                    if (c=='&') {
                        j = j+1;
                    }else if(c=='*'){
                        k = k+1;
                    }else{
                        i = i+1;
                    }
                    if (i==13) {
                        if (c!='&') {
                            break;
                        }else{
                            i=11;
                        }
                    }
                }
                String substring = nameRe.substring(0, i+j+k);
                substring = substring.replace("&", "<font color='red'>");
                substring = substring.replace("*", "</font>");
                if (j>k) {
                    substring = substring+"</font>";
                }
                System.out.println(substring);
                System.out.println(goods.getInWidth());
            }
            System.out.println(queryByPager.getTotalCount());
        }
    }
    
    
    
    /**  
     * Java里数字转字符串前面自动补0的实现。  
     *    
     */  
      public static void main(String[] args) {   
        int youNumber = 11;   
        // 0 代表前面补充0   
        // 4 代表长度为4   
        // d 代表参数为正数型   
        String str = String.format("%04d", youNumber);   
        System.out.println(str); // 0001   
      }   
}
