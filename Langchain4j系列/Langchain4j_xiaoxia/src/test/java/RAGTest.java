import com.caesar.XiaoxiaApp;
import dev.langchain4j.agent.tool.P;
import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.DocumentParser;
import dev.langchain4j.data.document.DocumentSplitter;
import dev.langchain4j.data.document.loader.FileSystemDocumentLoader;
import dev.langchain4j.data.document.parser.TextDocumentParser;
import dev.langchain4j.data.document.parser.apache.pdfbox.ApachePdfBoxDocumentParser;
import dev.langchain4j.data.document.splitter.DocumentSplitters;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.rag.content.retriever.ContentRetriever;
import dev.langchain4j.store.embedding.EmbeddingStoreIngestor;
import dev.langchain4j.store.embedding.IngestionResult;
import dev.langchain4j.store.embedding.inmemory.InMemoryEmbeddingStore;
import okio.FileSystem;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.file.FileSystems;
import java.nio.file.PathMatcher;
import java.util.List;

/**
 * Author: Yuxian Zheng
 * Version: 1.0
 * Date: 2025/6/18
 */
@SpringBootTest(classes = XiaoxiaApp.class)
public class RAGTest {

    // 这边使用的文件加载器 都是 fileSystemDocumentLoader，而还有一个就是 类路径上文档的加载器 叫做是 classPathDocumentLoader



    @Test
    public void testReadDocument()
    {

        // 这里的Document是Langchain4j 自己搞的一个类，底层还是java自己的原生的读取文档的操作
        // 这里读取文档的操作隐藏了：选择文档解析器的步骤。这里选择的文档解析器是默认的  textParser的。
        Document document = FileSystemDocumentLoader.loadDocument("D:/!File1/hello.txt");
        System.out.println(document.text());

        // （1）在load文件的时候可以指定一下特定的文档解析器的
        //  (2) langchiain4j的核心包中，这个parser的话，只有这样的一个核心实现类，就是这个 TestDocumentParser。只能读取纯文本格式的，比如 txt，html，md等等的
        Document document1 = FileSystemDocumentLoader.loadDocument("D:/!File1/hello.txt", new TextDocumentParser());
        System.out.println(document1.text());

        // (1)load一下这个目录下面所有的文件

        List<Document> documents = FileSystemDocumentLoader.loadDocuments("D:/!File2", new TextDocumentParser());
        documents.stream()
                .forEach(doc -> System.out.println(doc.text()));

        // （2）在某一文件夹中 加载一下 指定文件类型的所有文件
        PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher("glob:*.txt");
        List<Document> documents1 = FileSystemDocumentLoader.loadDocuments("D:/!File1", pathMatcher, new TextDocumentParser());
        documents1.forEach(doc -> System.out.println(doc.text()));


        // (3) 递归地从一个目录中，不断地寻找加载所有的文档（可以配合上述的pathMatcher一起使用 getPathMatcher）
        List<Document> documents2 = FileSystemDocumentLoader.loadDocumentsRecursively("D:/!File1", pathMatcher, new TextDocumentParser());
        documents2.forEach(doc -> System.out.println(doc.text()));


    }

    @Test
    public void pdfTest()
    {
        DocumentParser parser = new ApachePdfBoxDocumentParser();
        Document document = FileSystemDocumentLoader.loadDocument("D:/!File1/hey.pdf", parser);
        System.out.println(document.text());


    }


    @Test
    public void testEasyRAG()
    {
        // 所谓的easy rag的话是langchain4j 自己搞的一个内置的简单的embedding model的。
        // 向量存储的话，也搞了一个内存的方案，也是一个内置的方案。后续的话 会进行替换，会对embedding model使用第三方的，向量存储的数据库也使用第三方的

        // (1) 先使用默认的text Parser 对指定区域的文件进行读取
        Document document = FileSystemDocumentLoader.loadDocument("D:/!File1/人工智能.md", new TextDocumentParser());

        // (2) 确定 embedding model 以及 向量库存储
        // (2.1)基于内存的向量库存储——只有一个默认的实现
        InMemoryEmbeddingStore<TextSegment> embeddingStore = new InMemoryEmbeddingStore<>();



        /**
         * （3）分割spliter的策略，以及其中的 tokenizer的策略，以及使用的embedding model都是内置的策略——浓缩在了ingest中
         * （3.1） 内置的easy rag 使用的配对的tokenizer 就是这个内置的HuggingFaceTokenizer() 是完全可以的
         *public DocumentSplitter create() {
         *           return DocumentSplitters.recursive(300, 30, new HuggingFaceTokenizer());
         *其中recursive的策略是：先尝试以段落为单位切分；
         * 某个段落过长？用句子再切一遍；
         * 句子还太长？用行或字符进一步切；
         * 直到每个 segment 满足 token 限制（maxSegmentSize）为止。
         * 这就是递归切分的核心。
         * easy rag 的默认实现
         * public class BgeSmallEnV15QuantizedEmbeddingModelFactory implements EmbeddingModelFactory {
         *     public BgeSmallEnV15QuantizedEmbeddingModelFactory() {
         *     }
         *
         *     public EmbeddingModel create() {
         *         return new BgeSmallEnV15QuantizedEmbeddingModel();
         *     }
         * }
         *
         *
         */


        /**
         * //ingest
         * //1、分割文档：默认使用递归分割器，将文档分割为多个文本片段，每个片段包含不超过 300个token，并且
         * 有 30个token的重叠部分保证连贯性
         * //DocumentByParagraphSplitter(DocumentByLineSplitter(DocumentBySentenceSplitter(Docume
         * ntByWordSplitter)))
         * //2、文本向量化：使用一个LangChain4j内置的轻量化向量模型对每个文本片段进行向量化
         * //3、将原始文本和向量存储到向量数据库中(InMemoryEmbeddingStore)
         */


        EmbeddingStoreIngestor.ingest(document, embeddingStore);

        System.out.println(embeddingStore);


    }









}
