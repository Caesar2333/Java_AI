import com.caesar.XiaoxiaApp;
import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.store.embedding.EmbeddingMatch;
import dev.langchain4j.store.embedding.EmbeddingSearchRequest;
import dev.langchain4j.store.embedding.EmbeddingSearchResult;
import dev.langchain4j.store.embedding.EmbeddingStore;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Author: Yuxian Zheng
 * Version: 1.0
 * Date: 2025/6/18
 */
@SpringBootTest(classes = XiaoxiaApp.class)
public class PineconeEmbeded {

    @Autowired
    private EmbeddingStore embeddingStore;

    @Autowired
    private EmbeddingModel embeddingModel;


    @Test
    public void PineconeStoreTest()
    {

        // embedding 对应textSegment

        TextSegment segment = TextSegment.from("我喜欢打羽毛球");
        Embedding content = embeddingModel.embed(segment).content();

        embeddingStore.add(content,segment);

        TextSegment segment2 = TextSegment.from("今天天气很好");
        Embedding content2 = embeddingModel.embed(segment).content();

        embeddingStore.add(content2,segment2);



    }

    @Test
    public void pineconeStoreSearch()
    {
        /**
         * 下面写的代码 其实是  retriever中的逻辑，其中的search方法就是这样封装的
         */


        // 提问，并将问题转成向量数据
        Embedding embedding = embeddingModel.embed("你最喜欢的运动是什么？").content();

        //创建搜索请求对象,每次请求的时候，需要封装一下请求的对象，提出你的一些条件之类的
        EmbeddingSearchRequest request = EmbeddingSearchRequest.builder()
                .queryEmbedding(embedding)
                .maxResults(1) // 匹配最相似的记录
                .minScore(0.8) // 匹配度是 0.8分以上的
                .build();

        // 再到库中去寻找
        EmbeddingSearchResult<TextSegment> searchResult = embeddingStore.search(request);


        //searchResult.matches()：获取搜索结果中的匹配项列表。
        //.get(0)：从匹配项列表中获取第一个匹配项
        EmbeddingMatch<TextSegment> embeddingMatch = searchResult.matches().get(0);

        // 获取匹配项的相似度得分
        System.out.println(embeddingMatch.score());

        // 返回文本结果
        System.out.println(embeddingMatch.embedded().text());





    }





}
