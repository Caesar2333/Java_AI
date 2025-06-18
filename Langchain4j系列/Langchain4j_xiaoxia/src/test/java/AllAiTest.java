import com.caesar.XiaoxiaApp;
import dev.langchain4j.community.model.dashscope.QwenChatModel;
import dev.langchain4j.model.ollama.OllamaChatModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Author: Yuxian Zheng
 * Version: 1.0
 * Date: 2025/6/16
 */
@SpringBootTest(classes = XiaoxiaApp.class)
public class AllAiTest {

    @Autowired
    OpenAiChatModel openAiChatModel;

    @Autowired
    OllamaChatModel ollamaChatModel;

    @Autowired
    QwenChatModel qwenChatModel;

    @Test
    public void TestGptDemo()
    {
        // 这里的demo是langchain4j为了帮助你 给你提供的一个秘钥 只能访问gpt-4o-mini这个东西
        // 这里的配置可以通过 application.property进行省略
        OpenAiChatModel model = OpenAiChatModel.builder()
                .baseUrl("http://langchain4j.dev/demo/openai/v1")
                .apiKey("demo")
                .modelName("gpt-4o-mini")
                .build();


        String chat = model.chat("你好！你今天过的好吗？？"); // 这里返回的chat是大模型返回的东西

        System.out.println(chat);

    }

    @Test
    public void testSpringBootIntegration()
    {
        String chat = openAiChatModel.chat("今天的月色很美，你觉得呢？？");
        System.out.println(chat);


    }

    @Test
    public void testOllama()
    {
        String chat = ollamaChatModel.chat("简单的介绍一下你自己的吧！");
        System.out.println(chat);
    }


    @Test
    public void testQwenChatModel()
    {
        String chat = qwenChatModel.chat("你还好吗？？今天的月亮真美丽！");
        System.out.println(chat);


    }

}
