package net.justfind.lucene;

import java.util.List;

import net.justfind.bean.Pager;
import net.justfind.entity.LuceneGoods;

/**
 * <br>
 * <b>功能：</b>详细的功能描述<br>
 * <b>作者：</b>xiaogl<br>
 * <b>日期：</b> 2015年5月26日 <br>
 * <b>版权所有：<b>版权所有(C) 2015，快找网络<br>
 */
public interface LuceneService {
    /**
     * <br>
     * <b>功能：</b>保存单个索引<br>
     * <b>作者：</b>xiaogl<br>
     * <b>日期：</b> 2015年5月29日 <br>
     * @param goods
     */
    public void save(LuceneGoods goods);

    /**
     * <br>
     * <b>功能：</b>更新单个索引<br>
     * <b>作者：</b>xiaogl<br>
     * <b>日期：</b> 2015年5月29日 <br>
     * @param goods
     */
    public void update(LuceneGoods goods);

    /**
     * <br>
     * <b>功能：</b>删除单个索引<br>
     * <b>作者：</b>xiaogl<br>
     * <b>日期：</b> 2015年5月29日 <br>
     * @param goods
     */
    public void deleteGoods(String goodsId);

    /**
     * <br>
     * <b>功能：</b>查询<br>
     * <b>作者：</b>xiaogl<br>
     * <b>日期：</b> 2015年5月29日 <br>
     * @param goods
     */
    public Pager queryByPager(Pager pager, Integer outlengthMin, Integer outlengthMax, Integer outWidthMin,
            Integer outWidthMax, Integer inLengthMin, Integer inLengthMax, Integer inWidthMin, Integer inWidthMax,
            String providerId);

    /**
     * <br>
     * <b>功能：</b>批量保存索引<br>
     * <b>作者：</b>xiaogl<br>
     * <b>日期：</b> 2015年5月29日 <br>
     * @param goods
     */
    public void batchSave(List<LuceneGoods> goodsList);
    
    /**
     * <br>
     * <b>功能：</b>批量更新索引<br>
     * <b>作者：</b>xiaogl<br>
     * <b>日期：</b> 2015年5月29日 <br>
     * @param goods
     */
    public void batchUpdate(List<LuceneGoods> goodsList);

    /**
     * <br>
     * <b>功能：</b>批量删除索引<br>
     * <b>作者：</b>xiaogl<br>
     * <b>日期：</b> 2015年5月29日 <br>
     * @param goods
     */
    public void batchDelete(String goodsIds);
}
