package com.caesar.config;

import com.caesar.MemoryStore.MongodbMemoryStore;
import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.loader.FileSystemDocumentLoader;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.memory.chat.ChatMemoryProvider;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.rag.content.retriever.ContentRetriever;
import dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.EmbeddingStoreIngestor;
import dev.langchain4j.store.embedding.inmemory.InMemoryEmbeddingStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Author: Yuxian Zheng
 * Version: 1.0
 * Date: 2025/6/17
 */
@Configuration
public class XiaoxiaConfig {

    @Autowired
    MongodbMemoryStore mongodbMemoryStore;

    @Autowired
    private EmbeddingStore<TextSegment> embeddingStore;

    @Autowired
    private EmbeddingModel embeddingModel;


    @Bean
    public ChatMemoryProvider chatMemoryProviderXiaoxia() {

        return memoryId -> MessageWindowChatMemory.builder()
                .id(memoryId)
                .maxMessages(20)
                .chatMemoryStore(mongodbMemoryStore)
                .build();

    }


    public ContentRetriever contentRetrieverXiaoxia() {

        Document document1 = FileSystemDocumentLoader.loadDocument("D:/!File1/医院信息.md");
        Document document2 = FileSystemDocumentLoader.loadDocument("D:/!File1/科室信息.md");
        Document document3 = FileSystemDocumentLoader.loadDocument("D:/!File1/神经内科.md");
        List<Document> documents = Arrays.asList(document1, document2, document3);

        //使用内存向量存储
        InMemoryEmbeddingStore<TextSegment> embeddingStore = new InMemoryEmbeddingStore<>();

        //使用默认的文档分割器
        // 默认的Spliter + 默认tokenizer 以及默认的easy rag 的embedding model
        // 这里的ingest 是用来消化进来的数据的
        // (1) 默认的Spliter + 默认tokenizer
        // (2) 默认的easy rag 的embedding model ——确定默认的 embedding model的方式和下面的Retriever是一样的，这个是给存入向量库中的信息做 embedding的
        EmbeddingStoreIngestor.ingest(documents, embeddingStore);


        // 从某一个向量库中 设置的embedding store
        // （1） 默认的默认的easy rag 的embedding model —— 这个是用于给进来的query做 embedding的，默认是没有对query进行split和tokenize的，因为默认进来的query不是很大，
        return EmbeddingStoreContentRetriever.from(embeddingStore); // EmbeddingStoreContentRetriever 实现类 是接口 ContentRetriever的实现
        // 其中的一个from他妈的说白了，就是将embeddingstore放进去。后续更好地方法是使用build直接搞

    }

    @Bean
    public ContentRetriever contentRetrieverXiaoxiaPinecone() {

        Document document1 = FileSystemDocumentLoader.loadDocument("D:/!File1/医院信息.md");
        Document document2 = FileSystemDocumentLoader.loadDocument("D:/!File1/科室信息.md");
        Document document3 = FileSystemDocumentLoader.loadDocument("D:/!File1/神经内科.md");
        List<Document> documents = Arrays.asList(document1, document2, document3);

        // 定制一下 ingestor
        EmbeddingStoreIngestor.builder()
                .embeddingModel(embeddingModel)
                .embeddingStore(embeddingStore)
                .build()
                .ingest(documents);


        // 定制一下retriever
        return EmbeddingStoreContentRetriever.builder()
                .embeddingStore(embeddingStore)
                .embeddingModel(embeddingModel)
                .maxResults(1)
                .minScore(0.8)
                .build();

    }


}
