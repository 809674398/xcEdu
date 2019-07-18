package com.xuecheng.search;

import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.IndicesClient;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.Map;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestSearch {

    @Autowired
    RestHighLevelClient restHighLevelClient;

    @Autowired
    RestClient restClient;

    /**
     * 搜索所有
     */
    @Test
    public void searchAll() throws IOException {
        //创建搜索请求对象
        SearchRequest searchRequest = new SearchRequest("xc_course");
        //指定类型
        searchRequest.types("doc");
        //搜索源构建对象
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        //搜索方式
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        //设置分页参数
        int page = 1;
        int size = 1;
        int from = (page-1)* size;
        searchSourceBuilder.from(from);
        searchSourceBuilder.size(size);

        //设置原字段过滤
        searchSourceBuilder.fetchSource(new String[]{"name","studymodel","price","timestamp"},new String[]{});
        //向搜索对象中设置源
        searchRequest.source(searchSourceBuilder);
        //客户端执行搜索
        SearchResponse searchResponse = restHighLevelClient.search(searchRequest);
        //获取搜索结果
        SearchHits hits = searchResponse.getHits();
        //总记录数
        long totalHits = hits.getTotalHits();
        System.out.println("总记录数为:"+totalHits);
        SearchHit[] hits1 = hits.getHits();
        for (SearchHit hi: hits1){
            String id = hi.getId();
            Map<String, Object> map = hi.getSourceAsMap();
            String name = (String) map.get("name");
            System.out.println(id+"   "+name);

        }


    }

    /**
     * 精确查询
     * @throws IOException
     */
    @Test
    public void searchTerm() throws IOException {
        //创建搜索请求对象
        SearchRequest searchRequest = new SearchRequest("xc_course");
        //指定类型
        searchRequest.types("doc");
        //搜索源构建对象
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        //搜索方式
        //精确查询
        searchSourceBuilder.query(QueryBuilders.termQuery("name","spring"));
        //设置原字段过滤
        searchSourceBuilder.fetchSource(new String[]{"name","studymodel","price","timestamp"},new String[]{});
        //向搜索对象中设置源
        searchRequest.source(searchSourceBuilder);
        //客户端执行搜索
        SearchResponse searchResponse = restHighLevelClient.search(searchRequest);
        //获取搜索结果
        SearchHits hits = searchResponse.getHits();
        //总记录数
        long totalHits = hits.getTotalHits();
        System.out.println("总记录数为:"+totalHits);
        SearchHit[] hits1 = hits.getHits();
        for (SearchHit hi: hits1){
            String id = hi.getId();
            Map<String, Object> map = hi.getSourceAsMap();
            String name = (String) map.get("name");
            System.out.println(id+"   "+name);

        }


    }

    /**
     * 根据id查询
     * @throws IOException
     */
    @Test
    public void searchTerms() throws IOException {
        //创建搜索请求对象
        SearchRequest searchRequest = new SearchRequest("xc_course");
        //指定类型
        searchRequest.types("doc");
        //搜索源构建对象
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        //搜索方式
        String [] ids = new String[]{"1","2","100"};
        //精确查询
        searchSourceBuilder.query(QueryBuilders.termsQuery("_id",ids));
        //设置原字段过滤
        searchSourceBuilder.fetchSource(new String[]{"name","studymodel","price","timestamp"},new String[]{});
        //向搜索对象中设置源
        searchRequest.source(searchSourceBuilder);
        //客户端执行搜索
        SearchResponse searchResponse = restHighLevelClient.search(searchRequest);
        //获取搜索结果
        SearchHits hits = searchResponse.getHits();
        //总记录数
        long totalHits = hits.getTotalHits();
        System.out.println("总记录数为:"+totalHits);
        SearchHit[] hits1 = hits.getHits();
        for (SearchHit hi: hits1){
            String id = hi.getId();
            Map<String, Object> map = hi.getSourceAsMap();
            String name = (String) map.get("name");
            System.out.println(id+"   "+name);

        }


    }

 /**
     * 全文检索
     * @throws IOException
     */
    @Test
    public void searchMatch() throws IOException {
        //创建搜索请求对象
        SearchRequest searchRequest = new SearchRequest("xc_course");
        //指定类型
        searchRequest.types("doc");
        //搜索源构建对象
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        //搜索方式
        String [] ids = new String[]{"1","2","100"};
        //精确查询
        searchSourceBuilder.query(QueryBuilders.matchQuery("description","spring开发框架")
                        .minimumShouldMatch("70%"));
        //设置原字段过滤
        searchSourceBuilder.fetchSource(new String[]{"name","studymodel","price","timestamp"},new String[]{});
        //向搜索对象中设置源
        searchRequest.source(searchSourceBuilder);
        //客户端执行搜索
        SearchResponse searchResponse = restHighLevelClient.search(searchRequest);
        //获取搜索结果
        SearchHits hits = searchResponse.getHits();
        //总记录数
        long totalHits = hits.getTotalHits();
        System.out.println("总记录数为:"+totalHits);
        SearchHit[] hits1 = hits.getHits();
        for (SearchHit hi: hits1){
            String id = hi.getId();
            Map<String, Object> map = hi.getSourceAsMap();
            String name = (String) map.get("name");
            System.out.println(id+"   "+name);

        }


    }
    /**
     * 全文检索
     * @throws IOException
     */
    @Test
    public void searchMulty() throws IOException {
        //创建搜索请求对象
        SearchRequest searchRequest = new SearchRequest("xc_course");
        //指定类型
        searchRequest.types("doc");
        //搜索源构建对象
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        //搜索方式
        String [] ids = new String[]{"1","2","100"};
        //精确查询
        searchSourceBuilder.query(QueryBuilders.multiMatchQuery("spring css","name","description")
                        .minimumShouldMatch("50%")
        .field("name",10));
        //设置原字段过滤
        searchSourceBuilder.fetchSource(new String[]{"name","studymodel","price","timestamp"},new String[]{});
        //向搜索对象中设置源
        searchRequest.source(searchSourceBuilder);
        //客户端执行搜索
        SearchResponse searchResponse = restHighLevelClient.search(searchRequest);
        //获取搜索结果
        SearchHits hits = searchResponse.getHits();
        //总记录数
        long totalHits = hits.getTotalHits();
        System.out.println("总记录数为:"+totalHits);
        SearchHit[] hits1 = hits.getHits();
        for (SearchHit hi: hits1){
            String id = hi.getId();
            Map<String, Object> map = hi.getSourceAsMap();
            String name = (String) map.get("name");
            System.out.println(id+"   "+name);

        }


    }

    /**
     * boolean检索
     * @throws IOException
     */
    @Test
    public void searchBool() throws IOException {
        //创建搜索请求对象
        SearchRequest searchRequest = new SearchRequest("xc_course");
        //指定类型
        searchRequest.types("doc");
        //搜索源构建对象
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        //搜索方式
        String [] ids = new String[]{"1","2","100"};
        MultiMatchQueryBuilder multiMatchQueryBuilder = QueryBuilders.multiMatchQuery("spring css", "name", "description")
                .minimumShouldMatch("50%")
                .field("name", 10);
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("studymodel", "201001");
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.must(multiMatchQueryBuilder);
        boolQueryBuilder.must(termQueryBuilder);

        //精确查询
        searchSourceBuilder.query(boolQueryBuilder);
        //设置原字段过滤
        searchSourceBuilder.fetchSource(new String[]{"name","studymodel","price","timestamp"},new String[]{});
        //向搜索对象中设置源
        searchRequest.source(searchSourceBuilder);
        //客户端执行搜索
        SearchResponse searchResponse = restHighLevelClient.search(searchRequest);
        //获取搜索结果
        SearchHits hits = searchResponse.getHits();
        //总记录数
        long totalHits = hits.getTotalHits();
        System.out.println("总记录数为:"+totalHits);
        SearchHit[] hits1 = hits.getHits();
        for (SearchHit hi: hits1){
            String id = hi.getId();
            Map<String, Object> map = hi.getSourceAsMap();
            String name = (String) map.get("name");
            System.out.println(id+"   "+name);

        }


    }

 /**
     * 过滤器查询
     * @throws IOException
     */
    @Test
    public void searchFilter() throws IOException {
        //创建搜索请求对象
        SearchRequest searchRequest = new SearchRequest("xc_course");
        //指定类型
        searchRequest.types("doc");
        //搜索源构建对象
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        //搜索方式
        String [] ids = new String[]{"1","2","100"};
        MultiMatchQueryBuilder multiMatchQueryBuilder = QueryBuilders.multiMatchQuery("spring css", "name", "description")
                .minimumShouldMatch("50%")
                .field("name", 10);
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.must(multiMatchQueryBuilder);
        //定义过滤器
        boolQueryBuilder.filter(QueryBuilders.termQuery("studymodel","201001"));
        boolQueryBuilder.filter(QueryBuilders.rangeQuery("price").gt(50).lt(100));
        //精确查询
        searchSourceBuilder.query(boolQueryBuilder);
        //设置原字段过滤
        searchSourceBuilder.fetchSource(new String[]{"name","studymodel","price","timestamp"},new String[]{});
        //向搜索对象中设置源
        searchRequest.source(searchSourceBuilder);
        //客户端执行搜索
        SearchResponse searchResponse = restHighLevelClient.search(searchRequest);
        //获取搜索结果
        SearchHits hits = searchResponse.getHits();
        //总记录数
        long totalHits = hits.getTotalHits();
        System.out.println("总记录数为:"+totalHits);
        SearchHit[] hits1 = hits.getHits();
        for (SearchHit hi: hits1){
            String id = hi.getId();
            Map<String, Object> map = hi.getSourceAsMap();
            String name = (String) map.get("name");
            System.out.println(id+"   "+name);

        }


    }
    /**
     * 排序查询
     * @throws IOException
     */
    @Test
    public void searchSort() throws IOException {
        //创建搜索请求对象
        SearchRequest searchRequest = new SearchRequest("xc_course");
        //指定类型
        searchRequest.types("doc");
        //搜索源构建对象
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        //搜索方式
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        //定义过滤器
        boolQueryBuilder.filter(QueryBuilders.rangeQuery("price").gt(50).lt(100));
        //精确查询
        searchSourceBuilder.query(boolQueryBuilder);
        searchSourceBuilder.sort("studymodel", SortOrder.DESC);
        searchSourceBuilder.fetchSource(new String[]{"name","studymodel","price","timestamp"},new String[]{});
        //设置原字段过滤
        //向搜索对象中设置源
        searchRequest.source(searchSourceBuilder);
        //客户端执行搜索
        SearchResponse searchResponse = restHighLevelClient.search(searchRequest);
        //获取搜索结果
        SearchHits hits = searchResponse.getHits();
        //总记录数
        long totalHits = hits.getTotalHits();
        System.out.println("总记录数为:"+totalHits);
        SearchHit[] hits1 = hits.getHits();
        for (SearchHit hi: hits1){
            String id = hi.getId();
            Map<String, Object> map = hi.getSourceAsMap();
            String name = (String) map.get("name");
            System.out.println(id+"   "+name);

        }


    }
    /**
     * 排序查询
     * @throws IOException
     */
    @Test
    public void searchHighlight() throws IOException {
        //创建搜索请求对象
        SearchRequest searchRequest = new SearchRequest("xc_course");
        //指定类型
        searchRequest.types("doc");
        //搜索源构建对象
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        //搜索方式
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        MultiMatchQueryBuilder multiMatchQueryBuilder = QueryBuilders.multiMatchQuery("spring css", "name", "description")
                .minimumShouldMatch("50%")
                .field("name", 10);
        //定义过滤器
        boolQueryBuilder.filter(QueryBuilders.rangeQuery("price").gt(0).lt(100));
        boolQueryBuilder.must(multiMatchQueryBuilder);
        //精确查询
        searchSourceBuilder.query(boolQueryBuilder);
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.preTags("<tag>");
        highlightBuilder.postTags("</tag>");
        highlightBuilder.fields().add(new HighlightBuilder.Field("name"));
        searchSourceBuilder.highlighter(highlightBuilder);
        //设置原字段过滤
        //向搜索对象中设置源
        searchRequest.source(searchSourceBuilder);
        //客户端执行搜索
        SearchResponse searchResponse = restHighLevelClient.search(searchRequest);
        //获取搜索结果
        SearchHits hits = searchResponse.getHits();
        //总记录数
        long totalHits = hits.getTotalHits();
        System.out.println("总记录数为:"+totalHits);
        SearchHit[] hits1 = hits.getHits();
        for (SearchHit hi: hits1){
            String id = hi.getId();
            Map<String, Object> map = hi.getSourceAsMap();
            String name = (String) map.get("name");
            //取出高亮字段
            Map<String, HighlightField> highlightFields = hi.getHighlightFields();
            if (highlightFields!=null){
                HighlightField hightName = highlightFields.get("name");
                if (hightName!=null){
                    Text[] fragments = hightName.getFragments();
                    StringBuffer buffer = new StringBuffer();
                    for (Text text : fragments){
                        buffer.append(text);
                    }
                    name = buffer.toString();
                }

            }


            System.out.println(id+"   "+name);

        }


    }

}
