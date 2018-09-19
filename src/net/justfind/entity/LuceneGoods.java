package net.justfind.entity;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import net.justfind.bean.GoodsImage;
import net.justfind.bean.Setting;
import net.justfind.util.JsonUtil;
import net.justfind.util.SettingUtil;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSONArray;

/**
 * 实体类 - 商品
 * ============================================================================
 * 版权所有 2010-2012 英科风睿软件有限公司,并保留所有权利。
 * ============================================================================
 */

public class LuceneGoods extends BaseEntity {

    private static final long serialVersionUID = 8394952361534286179L;
    private String goodsSn;// 商品编号
    private String name;// 商品名称
    private BigDecimal price;// 销售价
    private BigDecimal cost;// 成本价
    private BigDecimal marketPrice;// 市场价
    private Double weight;// 商品重量(单位: 千克)
    private Integer score;// 积分
    // 已下架
    private Boolean isSpecificationEnabled;// 是否启用商品规格
    private String introduction;// 介绍
    private String metaKeywords;// 页面关键词
    private String excludeKeywords; // 屏蔽关键字
    private String metaDescription;// 页面描述
    private String htmlPath;// HTML静态文件路径
    private String goodsImageStore;// 商品图片存储
    private String goodsAttributeValue0;// 商品属性值0
    private String goodsAttributeValue1;// 商品属性值1
    private String goodsAttributeValue2;// 商品属性值2
    private String goodsAttributeValue3;// 商品属性值3
    private String goodsAttributeValue4;// 商品属性值4
    private String goodsAttributeValue5;// 商品属性值5
    private String goodsAttributeValue6;// 商品属性值6
    private String goodsAttributeValue7;// 商品属性值7
    private String goodsAttributeValue8;// 商品属性值8
    private String goodsAttributeValue9;// 商品属性值9
    private String goodsAttributeValue10;// 商品属性值10
    private String goodsAttributeValue11;// 商品属性值11
    private String goodsAttributeValue12;// 商品属性值12
    private String goodsAttributeValue13;// 商品属性值13
    private String goodsAttributeValue14;// 商品属性值14
    private String goodsAttributeValue15;// 商品属性值15
    private String goodsAttributeValue16;// 商品属性值16
    private String goodsAttributeValue17;// 商品属性值17
    private String goodsAttributeValue18;// 商品属性值18
    private String goodsAttributeValue19;// 商品属性值19
    private String goodsParameterValueStore;// 商品参数存储
    private Integer freezeStore;// 商品占用库存 2012 - 04- 12
    private Integer store;// 库存
    private Boolean isHot; // 是否是热销产品 2012-04-18
    private Boolean isNew; // 是否是新品推荐 2012 -04-18
    private Boolean isBest;// 是否为精品商品 2012 -04-18
    private String categoryName;// 商品分类
    private String providerId;// 供应商编号
    private String position;//商品位置
    private Integer terminalDisplay;//终端展示
    private Integer outLength; // 外长
    private Integer outWidth; // 外宽
    private Integer inLength; // 内长
    private Integer inWidth; // 内宽
    private Integer PRICESCALE = 2;

    private int status;// 商品状态(1:暂存,2:待审核,3:审核未通过,4:审核通过,5:已上架,6:平台下架,7:供应下架)
    private Boolean isShelve;// 是否上过架(默认 false , 2012-06-17 13:16:23 added)
    private String snapshotId;// 快照ID(2012-06-17 15:11:26 added);
    private BigDecimal goodsRate;//商品费率(2012-08-20 10:05:08 added);
    private Boolean isSpecialRate;//是否特殊返点(2012-08-27 11:09:59)

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getGoodsSn() {
        return goodsSn;
    }

    public void setGoodsSn(String goodsSn) {
        this.goodsSn = goodsSn;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price.setScale(PRICESCALE, BigDecimal.ROUND_HALF_UP);
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost.setScale(PRICESCALE, BigDecimal.ROUND_HALF_UP);
    }

    public BigDecimal getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(BigDecimal marketPrice) {
        this.marketPrice = marketPrice.setScale(PRICESCALE, BigDecimal.ROUND_HALF_UP);
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getIsSpecificationEnabled() {
        return isSpecificationEnabled;
    }

    public void setIsSpecificationEnabled(Boolean isSpecificationEnabled) {
        this.isSpecificationEnabled = isSpecificationEnabled;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getMetaKeywords() {
        return metaKeywords;
    }

    public void setMetaKeywords(String metaKeywords) {
        this.metaKeywords = metaKeywords;
    }

    public String getExcludeKeywords() {
        return excludeKeywords;
    }

    public void setExcludeKeywords(String excludeKeywords) {
        this.excludeKeywords = excludeKeywords;
    }

    public String getMetaDescription() {
        return metaDescription;
    }

    public void setMetaDescription(String metaDescription) {
        this.metaDescription = metaDescription;
    }

    public String getHtmlPath() {
        return htmlPath;
    }

    public void setHtmlPath(String htmlPath) {
        this.htmlPath = htmlPath;
    }

    public String getGoodsImageStore() {
        return goodsImageStore;
    }

    public void setGoodsImageStore(String goodsImageStore) {
        this.goodsImageStore = goodsImageStore;
    }

    public String getGoodsAttributeValue0() {
        return goodsAttributeValue0;
    }

    public void setGoodsAttributeValue0(String goodsAttributeValue0) {
        this.goodsAttributeValue0 = goodsAttributeValue0;
    }

    public String getGoodsAttributeValue1() {
        return goodsAttributeValue1;
    }

    public void setGoodsAttributeValue1(String goodsAttributeValue1) {
        this.goodsAttributeValue1 = goodsAttributeValue1;
    }

    public String getGoodsAttributeValue2() {
        return goodsAttributeValue2;
    }

    public void setGoodsAttributeValue2(String goodsAttributeValue2) {
        this.goodsAttributeValue2 = goodsAttributeValue2;
    }

    public String getGoodsAttributeValue3() {
        return goodsAttributeValue3;
    }

    public void setGoodsAttributeValue3(String goodsAttributeValue3) {
        this.goodsAttributeValue3 = goodsAttributeValue3;
    }

    public String getGoodsAttributeValue4() {
        return goodsAttributeValue4;
    }

    public void setGoodsAttributeValue4(String goodsAttributeValue4) {
        this.goodsAttributeValue4 = goodsAttributeValue4;
    }

    public String getGoodsAttributeValue5() {
        return goodsAttributeValue5;
    }

    public void setGoodsAttributeValue5(String goodsAttributeValue5) {
        this.goodsAttributeValue5 = goodsAttributeValue5;
    }

    public String getGoodsAttributeValue6() {
        return goodsAttributeValue6;
    }

    public void setGoodsAttributeValue6(String goodsAttributeValue6) {
        this.goodsAttributeValue6 = goodsAttributeValue6;
    }

    public String getGoodsAttributeValue7() {
        return goodsAttributeValue7;
    }

    public void setGoodsAttributeValue7(String goodsAttributeValue7) {
        this.goodsAttributeValue7 = goodsAttributeValue7;
    }

    public String getGoodsAttributeValue8() {
        return goodsAttributeValue8;
    }

    public void setGoodsAttributeValue8(String goodsAttributeValue8) {
        this.goodsAttributeValue8 = goodsAttributeValue8;
    }

    public String getGoodsAttributeValue9() {
        return goodsAttributeValue9;
    }

    public void setGoodsAttributeValue9(String goodsAttributeValue9) {
        this.goodsAttributeValue9 = goodsAttributeValue9;
    }

    public String getGoodsAttributeValue10() {
        return goodsAttributeValue10;
    }

    public void setGoodsAttributeValue10(String goodsAttributeValue10) {
        this.goodsAttributeValue10 = goodsAttributeValue10;
    }

    public String getGoodsAttributeValue11() {
        return goodsAttributeValue11;
    }

    public void setGoodsAttributeValue11(String goodsAttributeValue11) {
        this.goodsAttributeValue11 = goodsAttributeValue11;
    }

    public String getGoodsAttributeValue12() {
        return goodsAttributeValue12;
    }

    public void setGoodsAttributeValue12(String goodsAttributeValue12) {
        this.goodsAttributeValue12 = goodsAttributeValue12;
    }

    public String getGoodsAttributeValue13() {
        return goodsAttributeValue13;
    }

    public void setGoodsAttributeValue13(String goodsAttributeValue13) {
        this.goodsAttributeValue13 = goodsAttributeValue13;
    }

    public String getGoodsAttributeValue14() {
        return goodsAttributeValue14;
    }

    public void setGoodsAttributeValue14(String goodsAttributeValue14) {
        this.goodsAttributeValue14 = goodsAttributeValue14;
    }

    public String getGoodsAttributeValue15() {
        return goodsAttributeValue15;
    }

    public void setGoodsAttributeValue15(String goodsAttributeValue15) {
        this.goodsAttributeValue15 = goodsAttributeValue15;
    }

    public String getGoodsAttributeValue16() {
        return goodsAttributeValue16;
    }

    public void setGoodsAttributeValue16(String goodsAttributeValue16) {
        this.goodsAttributeValue16 = goodsAttributeValue16;
    }

    public String getGoodsAttributeValue17() {
        return goodsAttributeValue17;
    }

    public void setGoodsAttributeValue17(String goodsAttributeValue17) {
        this.goodsAttributeValue17 = goodsAttributeValue17;
    }

    public String getGoodsAttributeValue18() {
        return goodsAttributeValue18;
    }

    public void setGoodsAttributeValue18(String goodsAttributeValue18) {
        this.goodsAttributeValue18 = goodsAttributeValue18;
    }

    public String getGoodsAttributeValue19() {
        return goodsAttributeValue19;
    }

    public void setGoodsAttributeValue19(String goodsAttributeValue19) {
        this.goodsAttributeValue19 = goodsAttributeValue19;
    }

    public String getGoodsParameterValueStore() {
        return goodsParameterValueStore;
    }

    public void setGoodsParameterValueStore(String goodsParameterValueStore) {
        this.goodsParameterValueStore = goodsParameterValueStore;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getFreezeStore() {
        return freezeStore;
    }

    public void setFreezeStore(Integer freezeStore) {
        this.freezeStore = freezeStore;
    }

    public Boolean getIsHot() {
        return isHot;
    }

    public void setIsHot(Boolean isHot) {
        this.isHot = isHot;
    }

    public Boolean getIsNew() {
        return isNew;
    }

    public void setIsNew(Boolean isNew) {
        this.isNew = isNew;
    }

    public Integer getStore() {
        return store;
    }

    public void setStore(Integer store) {
        this.store = store;
    }

    public Boolean getIsBest() {
        return isBest;
    }

    public void setIsBest(Boolean isBest) {
        this.isBest = isBest;
    }

    public Boolean getIsShelve() {
        return isShelve;
    }

    public void setIsShelve(Boolean isShelve) {
        this.isShelve = isShelve;
    }

    public String getSnapshotId() {
        return snapshotId;
    }

    public void setSnapshotId(String snapshotId) {
        this.snapshotId = snapshotId;
    }

    public BigDecimal getGoodsRate() {
        return goodsRate;
    }

    public void setGoodsRate(BigDecimal goodsRate) {
        this.goodsRate = goodsRate;
    }

    public Boolean getIsSpecialRate() {
        return isSpecialRate;
    }

    public void setIsSpecialRate(Boolean isSpecialRate) {
        this.isSpecialRate = isSpecialRate;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Integer getTerminalDisplay() {
        return terminalDisplay;
    }

    public void setTerminalDisplay(Integer terminalDisplay) {
        this.terminalDisplay = terminalDisplay;
    }

    public Integer getOutLength() {
        return outLength;
    }

    public void setOutLength(Integer outLength) {
        this.outLength = outLength;
    }

    public Integer getOutWidth() {
        return outWidth;
    }

    public void setOutWidth(Integer outWidth) {
        this.outWidth = outWidth;
    }

    public Integer getInLength() {
        return inLength;
    }

    public void setInLength(Integer inLength) {
        this.inLength = inLength;
    }

    public Integer getInWidth() {
        return inWidth;
    }

    public void setInWidth(Integer inWidth) {
        this.inWidth = inWidth;
    }

    public Integer getPRICESCALE() {
        return PRICESCALE;
    }

    public void setPRICESCALE(Integer pRICESCALE) {
        PRICESCALE = pRICESCALE;
    }
    
    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    // 获取默认商品图片（大）
    public String getDefaultBigGoodsImagePath() {
        List<GoodsImage> goodsImageList = getGoodsImageList();
        if (goodsImageList != null && goodsImageList.size() > 0) {
            return goodsImageList.get(0).getBigImagePath();
        } else {
            Setting setting = SettingUtil.getSetting();
            return setting.getDefaultBigGoodsImagePath();
        }
    }

    // 获取默认商品图片（小）
    public String getDefaultSmallGoodsImagePath() {
        List<GoodsImage> goodsImageList = getGoodsImageList();
        if (goodsImageList != null && goodsImageList.size() > 0) {
            return goodsImageList.get(0).getSmallImagePath();
        } else {
            Setting setting = SettingUtil.getSetting();
            return setting.getDefaultSmallGoodsImagePath();
        }
    }

    // 获取默认商品图片（缩略图）
    public String getDefaultThumbnailGoodsImagePath() {
        List<GoodsImage> goodsImageList = getGoodsImageList();
        if (goodsImageList != null && goodsImageList.size() > 0) {
            return goodsImageList.get(0).getThumbnailImagePath();
        } else {
            Setting setting = SettingUtil.getSetting();
            return setting.getDefaultThumbnailGoodsImagePath();
        }
    }

    // 获取商品图片
    public List<GoodsImage> getGoodsImageList() {
        if (StringUtils.isEmpty(goodsImageStore)) {
            return null;
        }
        try {
            return JSONArray.parseArray(goodsImageStore, GoodsImage.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // 设置商品图片
    public void setGoodsImageList(List<GoodsImage> goodsImageList) {
        if (goodsImageList == null || goodsImageList.size() == 0) {
            goodsImageStore = null;
            return;
        }
        Collections.sort(goodsImageList);
        goodsImageStore = JsonUtil.toJson(goodsImageList);
    }

}
