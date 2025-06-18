import com.caesar.XiaoxiaApp;
import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.output.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Author: Yuxian Zheng
 * Version: 1.0
 * Date: 2025/6/18
 */
@SpringBootTest(classes = XiaoxiaApp.class)
public class EmbeddingTest {

    // 这里添加的是  community-dashscope的依赖，之前已经添加过了
    // 只要你在配置文件中有了对应的配置，其autoconfig文件就会给你注册出来一个bean。你到时候直接使用这个接口注入就好了，不过超过一个bean的话是会打架的
    @Autowired
    private EmbeddingModel embeddingModel;



    @Test
    public void embeddingTest()
    {
        Response<Embedding> embed = embeddingModel.embed("你好啊，二狗子");
        System.out.println("向量维度：" + embed.content().vector().length);
        System.out.println("向量输出" + embed.content());
        System.out.println(embed);




    }




}
