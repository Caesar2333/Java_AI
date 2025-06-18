import com.caesar.XiaoxiaApp;
import com.caesar.assistant.TestAssistant;
import dev.langchain4j.community.model.dashscope.QwenChatModel;

import dev.langchain4j.service.AiServices;
import dev.langchain4j.service.spring.AiService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Author: Yuxian Zheng
 * Version: 1.0
 * Date: 2025/6/16
 */
@SpringBootTest(classes = XiaoxiaApp.class)
public class AiServiceTest {

    @Autowired
    private QwenChatModel qwenChatModel;

    @Autowired
    TestAssistant testAssistant;

    @Test
    public void testAiService()
    {

        TestAssistant qwenAssistant = AiServices.create(TestAssistant.class, qwenChatModel);

        String chat = qwenAssistant.chat("你好吗？？来说一句土味情况听听！");
        System.out.println(chat);


    }

    @Test
    public void testAiService2()
    {
        String chat = testAssistant.chat("我准备从图书馆回家了，你能说一句名言送一下我吗？");
        System.out.println(chat);


    }




}
