package net.justfind.lucene.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.justfind.bean.Pager;
import net.justfind.bean.Pager.Order;
import net.justfind.entity.LuceneGoods;
import net.justfind.lucene.LuceneService;
import net.justfind.util.BeanUtils;
import net.justfind.util.Configuration;
import net.justfind.util.IndexSearcherUtil;
import net.justfind.util.IndexWriterUtil;

import org.apache.commons.lang.StringUtils;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.NumericField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.NumericRangeQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.apache.lucene.search.TopDocs;

/**
 * <br>
 * <b>功能：</b>详细的功能描述<br>
 * <b>作者：</b>xiaogl<br>
 * <b>日期：</b> 2015年5月21日 <br>
 * <b>版权所有：<b>版权所有(C) 2015，快找网络<br>
 */
public class LuceneServiceImpl implements LuceneService {
    @Override
    public void save(LuceneGoods goods) {
        IndexWriter indexWriter = IndexWriterUtil.getIndexWriter();
        try {
            Document document = new Document();

            document.add(new Field("id", goods.getId() + "", Field.Store.YES, Field.Index.NOT_ANALYZED_NO_NORMS));

            document.add(new Field("createDate", goods.getCreateDate() + "", Field.Store.YES, Field.Index.NO));

            document.add(new Field("modifyDate", goods.getModifyDate() + "", Field.Store.YES, Field.Index.NO));

            document.add(new Field("goodsSn", goods.getGoodsSn() + "", Field.Store.YES,
                    Field.Index.NOT_ANALYZED_NO_NORMS));

            document.add(new Field("name", goods.getName() + "", Field.Store.YES, Field.Index.ANALYZED_NO_NORMS));

            document.add(new Field("price", goods.getPrice() + "", Field.Store.YES, Field.Index.NO));

            document.add(new Field("cost", goods.getCost() + "", Field.Store.YES, Field.Index.NO));

            document.add(new Field("marketPrice", goods.getMarketPrice() + "", Field.Store.YES, Field.Index.NO));

            document.add(new Field("weight", goods.getWeight() + "", Field.Store.YES, Field.Index.NO));

            document.add(new Field("score", goods.getScore() + "", Field.Store.YES, Field.Index.NO));

            document.add(new Field("isSpecificationEnabled", goods.getIsSpecificationEnabled() + "", Field.Store.YES,
                    Field.Index.NO));

            document.add(new Field("introduction", goods.getIntroduction() + "", Field.Store.YES,
                    Field.Index.ANALYZED_NO_NORMS));

            document.add(new Field("metaKeywords", goods.getMetaKeywords() + "", Field.Store.YES,
                    Field.Index.ANALYZED_NO_NORMS));

            document.add(new Field("excludeKeywords", goods.getExcludeKeywords() + "", Field.Store.YES, Field.Index.NO));

            document.add(new Field("metaDescription", goods.getMetaDescription() + "", Field.Store.YES,
                    Field.Index.ANALYZED_NO_NORMS));

            document.add(new Field("htmlPath", goods.getHtmlPath() + "", Field.Store.YES, Field.Index.NO));

            document.add(new Field("goodsImageStore", goods.getGoodsImageStore() + "", Field.Store.YES, Field.Index.NO));

            document.add(new Field("freezeStore", goods.getFreezeStore() + "", Field.Store.YES, Field.Index.NO));

            document.add(new Field("store", goods.getStore() + "", Field.Store.YES, Field.Index.NO));

            document.add(new Field("isHot", goods.getIsHot() + "", Field.Store.YES, Field.Index.NO));

            document.add(new Field("isNew", goods.getIsNew() + "", Field.Store.YES, Field.Index.NO));

            document.add(new Field("isBest", goods.getIsBest() + "", Field.Store.YES, Field.Index.NO));

            document.add(new Field("categoryName", goods.getCategoryName(), Field.Store.YES,
                    Field.Index.ANALYZED_NO_NORMS));

            document.add(new Field("providerId", goods.getProviderId(), Field.Store.YES,
                    Field.Index.NOT_ANALYZED_NO_NORMS));

            if (goods.getOutLength() != null) {
                document.add(new NumericField("outLength", Field.Store.YES, true).setIntValue(goods.getOutLength()));
            }
            if (goods.getOutWidth() != null) {
                document.add(new NumericField("outWidth", Field.Store.YES, true).setIntValue(goods.getOutWidth()));
            }
            if (goods.getInLength() != null) {
                document.add(new NumericField("inLength", Field.Store.YES, true).setIntValue(goods.getInLength()));
            }
            if (goods.getInWidth() != null) {
                document.add(new NumericField("inWidth", Field.Store.YES, true).setIntValue(goods.getInWidth()));
            }

            document.add(new Field("isShelve", goods.getIsShelve() + "", Field.Store.YES, Field.Index.NO));

            document.add(new Field("status", goods.getStatus() + "", Field.Store.YES, Field.Index.NOT_ANALYZED_NO_NORMS));

            indexWriter.addDocument(document);
            //将把产生.cfs的文档和原来的文档压缩并合并为一个
            indexWriter.optimize();
            indexWriter.commit();
        } catch (Exception e) {
            try {
                indexWriter.rollback();
            } catch (IOException e1) {
                throw new RuntimeException(e);
            }
        } finally {
            IndexWriterUtil.closeIndexWrite(indexWriter);
        }
    }

    @Override
    public void batchSave(List<LuceneGoods> goodsList) {
        IndexWriter indexWriter = IndexWriterUtil.getIndexWriter();
        try {
            if (goodsList != null && goodsList.size() > 0) {
                for (LuceneGoods goods : goodsList) {
                    Document document = new Document();

                    document.add(new Field("id", goods.getId() + "", Field.Store.YES, Field.Index.NOT_ANALYZED_NO_NORMS));

                    document.add(new Field("createDate", goods.getCreateDate() + "", Field.Store.YES, Field.Index.NO));

                    document.add(new Field("modifyDate", goods.getModifyDate() + "", Field.Store.YES, Field.Index.NO));

                    document.add(new Field("goodsSn", goods.getGoodsSn() + "", Field.Store.YES,
                            Field.Index.NOT_ANALYZED_NO_NORMS));

                    document.add(new Field("name", goods.getName() + "", Field.Store.YES, Field.Index.ANALYZED_NO_NORMS));

                    document.add(new Field("price", goods.getPrice() + "", Field.Store.YES, Field.Index.NO));

                    document.add(new Field("cost", goods.getCost() + "", Field.Store.YES, Field.Index.NO));

                    document.add(new Field("marketPrice", goods.getMarketPrice() + "", Field.Store.YES, Field.Index.NO));

                    document.add(new Field("weight", goods.getWeight() + "", Field.Store.YES, Field.Index.NO));

                    document.add(new Field("score", goods.getScore() + "", Field.Store.YES, Field.Index.NO));

                    document.add(new Field("isSpecificationEnabled", goods.getIsSpecificationEnabled() + "",
                            Field.Store.YES, Field.Index.NO));

                    document.add(new Field("introduction", goods.getIntroduction() + "", Field.Store.YES,
                            Field.Index.ANALYZED_NO_NORMS));

                    document.add(new Field("metaKeywords", goods.getMetaKeywords() + "", Field.Store.YES,
                            Field.Index.ANALYZED_NO_NORMS));

                    document.add(new Field("excludeKeywords", goods.getExcludeKeywords() + "", Field.Store.YES,
                            Field.Index.NO));

                    document.add(new Field("metaDescription", goods.getMetaDescription() + "", Field.Store.YES,
                            Field.Index.ANALYZED_NO_NORMS));

                    document.add(new Field("htmlPath", goods.getHtmlPath() + "", Field.Store.YES, Field.Index.NO));

                    document.add(new Field("goodsImageStore", goods.getGoodsImageStore() + "", Field.Store.YES,
                            Field.Index.NO));

                    document.add(new Field("freezeStore", goods.getFreezeStore() + "", Field.Store.YES, Field.Index.NO));

                    document.add(new Field("store", goods.getStore() + "", Field.Store.YES, Field.Index.NO));

                    document.add(new Field("isHot", goods.getIsHot() + "", Field.Store.YES, Field.Index.NO));

                    document.add(new Field("isNew", goods.getIsNew() + "", Field.Store.YES, Field.Index.NO));

                    document.add(new Field("isBest", goods.getIsBest() + "", Field.Store.YES, Field.Index.NO));

                    document.add(new Field("categoryName", goods.getCategoryName() + "", Field.Store.YES,
                            Field.Index.ANALYZED_NO_NORMS));

                    document.add(new Field("providerId", goods.getProviderId() + "", Field.Store.YES,
                            Field.Index.NOT_ANALYZED_NO_NORMS));

                    if (goods.getOutLength() != null) {
                        document.add(new NumericField("outLength", Field.Store.YES, true).setIntValue(goods
                                .getOutLength()));
                    }
                    if (goods.getOutWidth() != null) {
                        document.add(new NumericField("outWidth", Field.Store.YES, true).setIntValue(goods
                                .getOutWidth()));
                    }
                    if (goods.getInLength() != null) {
                        document.add(new NumericField("inLength", Field.Store.YES, true).setIntValue(goods
                                .getInLength()));
                    }
                    if (goods.getInWidth() != null) {
                        document.add(new NumericField("inWidth", Field.Store.YES, true).setIntValue(goods.getInWidth()));
                    }
                    document.add(new Field("isShelve", goods.getIsShelve() + "", Field.Store.YES, Field.Index.NO));

                    document.add(new Field("status", goods.getStatus() + "", Field.Store.YES,
                            Field.Index.NOT_ANALYZED_NO_NORMS));

                    indexWriter.addDocument(document);
                }
                indexWriter.optimize();
                indexWriter.commit();
            }
        } catch (Exception e) {
            try {
                indexWriter.rollback();
            } catch (IOException e1) {
                throw new RuntimeException(e);
            }
        } finally {
            IndexWriterUtil.closeIndexWrite(indexWriter);
        }
    }

    @Override
    public void update(LuceneGoods goods) {
        IndexWriter indexWriter = null;
        try {
            indexWriter = IndexWriterUtil.getIndexWriter();
            Document document = new Document();

            document.add(new Field("id", goods.getId() + "", Field.Store.YES, Field.Index.NOT_ANALYZED_NO_NORMS));

            document.add(new Field("createDate", goods.getCreateDate() + "", Field.Store.YES, Field.Index.NO));

            document.add(new Field("modifyDate", goods.getModifyDate() + "", Field.Store.YES, Field.Index.NO));

            document.add(new Field("goodsSn", goods.getGoodsSn() + "", Field.Store.YES,
                    Field.Index.NOT_ANALYZED_NO_NORMS));

            document.add(new Field("name", goods.getName() + "", Field.Store.YES, Field.Index.ANALYZED_NO_NORMS));

            document.add(new Field("price", goods.getPrice() + "", Field.Store.YES, Field.Index.NO));

            document.add(new Field("cost", goods.getCost() + "", Field.Store.YES, Field.Index.NO));

            document.add(new Field("marketPrice", goods.getMarketPrice() + "", Field.Store.YES, Field.Index.NO));

            document.add(new Field("weight", goods.getWeight() + "", Field.Store.YES, Field.Index.NO));

            document.add(new Field("score", goods.getScore() + "", Field.Store.YES, Field.Index.NO));

            document.add(new Field("isSpecificationEnabled", goods.getIsSpecificationEnabled() + "", Field.Store.YES,
                    Field.Index.NO));

            document.add(new Field("introduction", goods.getIntroduction() + "", Field.Store.YES,
                    Field.Index.ANALYZED_NO_NORMS));

            document.add(new Field("metaKeywords", goods.getMetaKeywords() + "", Field.Store.YES,
                    Field.Index.ANALYZED_NO_NORMS));

            document.add(new Field("excludeKeywords", goods.getExcludeKeywords() + "", Field.Store.YES, Field.Index.NO));

            document.add(new Field("metaDescription", goods.getMetaDescription() + "", Field.Store.YES,
                    Field.Index.ANALYZED_NO_NORMS));

            document.add(new Field("htmlPath", goods.getHtmlPath() + "", Field.Store.YES, Field.Index.NO));

            document.add(new Field("goodsImageStore", goods.getGoodsImageStore() + "", Field.Store.YES, Field.Index.NO));

            document.add(new Field("freezeStore", goods.getFreezeStore() + "", Field.Store.YES, Field.Index.NO));

            document.add(new Field("store", goods.getStore() + "", Field.Store.YES, Field.Index.NO));

            document.add(new Field("isHot", goods.getIsHot() + "", Field.Store.YES, Field.Index.NO));

            document.add(new Field("isNew", goods.getIsNew() + "", Field.Store.YES, Field.Index.NO));

            document.add(new Field("isBest", goods.getIsBest() + "", Field.Store.YES, Field.Index.NO));

            document.add(new Field("categoryName", goods.getCategoryName(), Field.Store.YES,
                    Field.Index.ANALYZED_NO_NORMS));

            document.add(new Field("providerId", goods.getProviderId(), Field.Store.YES,
                    Field.Index.NOT_ANALYZED_NO_NORMS));

            if (goods.getOutLength() != null) {
                document.add(new NumericField("outLength", Field.Store.YES, true).setIntValue(goods.getOutLength()));
            }
            if (goods.getOutWidth() != null) {
                document.add(new NumericField("outWidth", Field.Store.YES, true).setIntValue(goods.getOutWidth()));
            }
            if (goods.getInLength() != null) {
                document.add(new NumericField("inLength", Field.Store.YES, true).setIntValue(goods.getInLength()));
            }
            if (goods.getInWidth() != null) {
                document.add(new NumericField("inWidth", Field.Store.YES, true).setIntValue(goods.getInWidth()));
            }
            document.add(new Field("isShelve", goods.getIsShelve() + "", Field.Store.YES, Field.Index.NO));

            document.add(new Field("status", goods.getStatus() + "", Field.Store.YES, Field.Index.NOT_ANALYZED_NO_NORMS));

            /*
             *根据字段的值进行修改数据内容；原则，先删除再添加
             *如果字段值不存在，将新增一条记录（文档）
             *如果字段值唯一，那就相当 于根据主键修改数据；
             *如果字段值对应有多条数据记录（文档），则其它记录（文档）将被逻辑删除后，再添加一记录（文档）
             */
            indexWriter.updateDocument(new Term("id", goods.getId()), document);
            //如果要在把原来的记录物理删除可以加下边一行代码
            indexWriter.optimize();
            indexWriter.commit();
        } catch (Exception e) {
            try {
                indexWriter.rollback();
            } catch (IOException e1) {
                throw new RuntimeException(e);
            }
        } finally {
            IndexWriterUtil.closeIndexWrite(indexWriter);
        }
    }

    @Override
    public void batchUpdate(List<LuceneGoods> goodsList) {
        IndexWriter indexWriter = IndexWriterUtil.getIndexWriter();
        try {
            if (goodsList != null && goodsList.size() > 0) {
                for (LuceneGoods goods : goodsList) {
                    Document document = new Document();

                    document.add(new Field("id", goods.getId() + "", Field.Store.YES, Field.Index.NOT_ANALYZED_NO_NORMS));

                    document.add(new Field("createDate", goods.getCreateDate() + "", Field.Store.YES, Field.Index.NO));

                    document.add(new Field("modifyDate", goods.getModifyDate() + "", Field.Store.YES, Field.Index.NO));

                    document.add(new Field("goodsSn", goods.getGoodsSn() + "", Field.Store.YES,
                            Field.Index.NOT_ANALYZED_NO_NORMS));

                    document.add(new Field("name", goods.getName() + "", Field.Store.YES, Field.Index.ANALYZED_NO_NORMS));

                    document.add(new Field("price", goods.getPrice() + "", Field.Store.YES, Field.Index.NO));

                    document.add(new Field("cost", goods.getCost() + "", Field.Store.YES, Field.Index.NO));

                    document.add(new Field("marketPrice", goods.getMarketPrice() + "", Field.Store.YES, Field.Index.NO));

                    document.add(new Field("weight", goods.getWeight() + "", Field.Store.YES, Field.Index.NO));

                    document.add(new Field("score", goods.getScore() + "", Field.Store.YES, Field.Index.NO));

                    document.add(new Field("isSpecificationEnabled", goods.getIsSpecificationEnabled() + "",
                            Field.Store.YES, Field.Index.NO));

                    document.add(new Field("introduction", goods.getIntroduction() + "", Field.Store.YES,
                            Field.Index.ANALYZED_NO_NORMS));

                    document.add(new Field("metaKeywords", goods.getMetaKeywords() + "", Field.Store.YES,
                            Field.Index.ANALYZED_NO_NORMS));

                    document.add(new Field("excludeKeywords", goods.getExcludeKeywords() + "", Field.Store.YES,
                            Field.Index.NO));

                    document.add(new Field("metaDescription", goods.getMetaDescription() + "", Field.Store.YES,
                            Field.Index.ANALYZED_NO_NORMS));

                    document.add(new Field("htmlPath", goods.getHtmlPath() + "", Field.Store.YES, Field.Index.NO));

                    document.add(new Field("goodsImageStore", goods.getGoodsImageStore() + "", Field.Store.YES,
                            Field.Index.NO));

                    document.add(new Field("freezeStore", goods.getFreezeStore() + "", Field.Store.YES, Field.Index.NO));

                    document.add(new Field("store", goods.getStore() + "", Field.Store.YES, Field.Index.NO));

                    document.add(new Field("isHot", goods.getIsHot() + "", Field.Store.YES, Field.Index.NO));

                    document.add(new Field("isNew", goods.getIsNew() + "", Field.Store.YES, Field.Index.NO));

                    document.add(new Field("isBest", goods.getIsBest() + "", Field.Store.YES, Field.Index.NO));

                    document.add(new Field("categoryName", goods.getCategoryName(), Field.Store.YES,
                            Field.Index.ANALYZED_NO_NORMS));

                    document.add(new Field("providerId", goods.getProviderId(), Field.Store.YES,
                            Field.Index.NOT_ANALYZED_NO_NORMS));

                    if (goods.getOutLength() != null) {
                        document.add(new NumericField("outLength", Field.Store.YES, true).setIntValue(goods
                                .getOutLength()));
                    }
                    if (goods.getOutWidth() != null) {
                        document.add(new NumericField("outWidth", Field.Store.YES, true).setIntValue(goods
                                .getOutWidth()));
                    }
                    if (goods.getInLength() != null) {
                        document.add(new NumericField("inLength", Field.Store.YES, true).setIntValue(goods
                                .getInLength()));
                    }
                    if (goods.getInWidth() != null) {
                        document.add(new NumericField("inWidth", Field.Store.YES, true).setIntValue(goods.getInWidth()));
                    }

                    document.add(new Field("isShelve", goods.getIsShelve() + "", Field.Store.YES, Field.Index.NO));

                    document.add(new Field("status", goods.getStatus() + "", Field.Store.YES,
                            Field.Index.NOT_ANALYZED_NO_NORMS));

                    indexWriter.updateDocument(new Term("id", goods.getId()), document);
                }
                indexWriter.optimize();
                indexWriter.commit();
            }
        } catch (Exception e) {
            try {
                indexWriter.rollback();
            } catch (IOException e1) {
                throw new RuntimeException(e);
            }
        } finally {
            IndexWriterUtil.closeIndexWrite(indexWriter);
        }
    }

    @Override
    public void deleteGoods(String goodsId) {
        IndexWriter indexWriter = null;
        try {
            indexWriter = IndexWriterUtil.getIndexWriter();
            // 参数一：指定处理的字段，参数二：指定关键字(词），如果对应多条记录，多条记录也被删除
            indexWriter.deleteDocuments(new Term("id", goodsId));
            //优化索引，可支持物理删除，如果是逻辑删除，可省略下面一行代码
            indexWriter.optimize();
            // 提交事务
            indexWriter.commit();
        } catch (Exception e) {
            try {
                indexWriter.rollback();
            } catch (IOException e1) {
                throw new RuntimeException(e);
            }
        } finally {
            IndexWriterUtil.closeIndexWrite(indexWriter);
        }
    }

    @Override
    public void batchDelete(String goodsIds) {
        IndexWriter indexWriter = null;
        try {
            indexWriter = IndexWriterUtil.getIndexWriter();
            if (StringUtils.isNotBlank(goodsIds)) {
                String[] Ids = goodsIds.split(",");
                for (String goodsId : Ids) {
                    goodsId = goodsId.replaceAll("'", "");
                    // 参数一：指定处理的字段，参数二：指定关键字(词），如果对应多条记录，多条记录也被删除
                    indexWriter.deleteDocuments(new Term("id", goodsId));
                }
            }
            //优化索引，可支持物理删除，如果是逻辑删除，可省略下面一行代码
            indexWriter.optimize();
            // 提交事务
            indexWriter.commit();
        } catch (Exception e) {
            try {
                indexWriter.rollback();
            } catch (IOException e1) {
                throw new RuntimeException(e);
            }
        } finally {
            IndexWriterUtil.closeIndexWrite(indexWriter);
        }
    }

    @Override
    public Pager queryByPager(Pager pager, Integer outlengthMin, Integer outlengthMax, Integer outWidthMin,
            Integer outWidthMax, Integer inLengthMin, Integer inLengthMax, Integer inWidthMin, Integer inWidthMax,
            String providerId) {
        IndexSearcher indexSearcher = IndexSearcherUtil.getIndexSearcher();
        try {
            if (pager != null) {
                // 返回集合
                List<LuceneGoods> goodes = new ArrayList<LuceneGoods>();
                // 排序字段
                String orderBy = pager.getOrderBy();
                // 排序方式
                Order order = pager.getOrder();
                // 搜索关键词
                String keyword = pager.getKeyword();
                // 搜索字段
                //                String searchBy = pager.getSearchBy();
                // 当前页面
                int pageNumber = pager.getPageNumber();
                // 每页记录数
                int pageSize = pager.getPageSize();
                // 查询页的开始记录数的序号
                int startRecord = (pageNumber - 1) * pageSize;
                // 查询页的结束记录数序号
                int endRecord = pageNumber * pageSize;
                Boolean flag = false;
                // 添加排序
                Sort sort = new Sort();
                if (orderBy != null) {
                    if (order != null) {
                        if (("desc").equals(order.name())) {
                            flag = true;
                        }
                        SortField sortField = new SortField(orderBy, SortField.STRING, flag);
                        sort.setSort(sortField);
                    }
                }
                // 查询解析器
                BooleanQuery Bquery = new BooleanQuery();
                if (StringUtils.isNotBlank(keyword)) {
                    QueryParser queryParser = new MultiFieldQueryParser(Configuration.LOCAL_VERSION, new String[] {
                            "name", "categoryName" }, Configuration.ANALYZER);
                    Query nameQuery = queryParser.parse(keyword);
                    Bquery.add(nameQuery, BooleanClause.Occur.MUST);
                }
                Query query1 = NumericRangeQuery.newIntRange("outLength", outlengthMin, outlengthMax, true, true);
                Bquery.add(query1, BooleanClause.Occur.MUST);
                Query query2 = NumericRangeQuery.newIntRange("outWidth", outWidthMin, outWidthMax, true, true);
                Bquery.add(query2, BooleanClause.Occur.MUST);
                Query query3 = NumericRangeQuery.newIntRange("inLength", inLengthMin, inLengthMax, true, true);
                Bquery.add(query3, BooleanClause.Occur.MUST);
                Query query4 = NumericRangeQuery.newIntRange("inWidth", inWidthMin, inWidthMax, true, true);
                Bquery.add(query4, BooleanClause.Occur.MUST);
                if (StringUtils.isNotBlank(providerId)) {
                    QueryParser qParser = new QueryParser(Configuration.LOCAL_VERSION, "providerId",
                            Configuration.ANALYZER);
                    Query pQuery = qParser.parse(providerId);
                    Bquery.add(pQuery, BooleanClause.Occur.MUST);
                }
                // 查询
                // 查询索引库
                TopDocs topDocs = indexSearcher.search(Bquery, null, endRecord, sort);
                // 判断结果集长度
                if (topDocs.totalHits <= startRecord) {
                    return null;
                }
                if (endRecord > topDocs.totalHits) {
                    endRecord = topDocs.totalHits;
                }
                // 文档编号数组
                ScoreDoc[] scoreDocs = topDocs.scoreDocs;
                for (int i = startRecord; i < endRecord; i++) {
                    // 根据文档编号查询真正的文档对象
                    Document doc = indexSearcher.doc(scoreDocs[i].doc);
                    LuceneGoods goods = (LuceneGoods) BeanUtils.documentToBean(doc, LuceneGoods.class, Bquery);
                    goodes.add(goods);
                }
                pager.setResult(goodes);
                pager.setTotalCount(topDocs.totalHits);
                return pager;
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        } finally {
            IndexSearcherUtil.closeIndexSearcher(indexSearcher);
        }
    }
}
