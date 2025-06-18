import com.caesar.XiaoxiaApp;
import com.caesar.assistant.SeparateChatAssistant;
import com.caesar.assistant.TestAssistant;
import dev.langchain4j.community.model.dashscope.QwenChatModel;
import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.ollama.OllamaChatModel;
import dev.langchain4j.service.AiServices;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Author: Yuxian Zheng
 * Version: 1.0
 * Date: 2025/6/17
 */


@SpringBootTest(classes = XiaoxiaApp.class)
public class ChatMemoryTest {

    @Autowired
    TestAssistant testAssistant;

    @Autowired
    OllamaChatModel ollamaChatModel;
    @Autowired
    private QwenChatModel qwenChatModel;

    @Autowired
    SeparateChatAssistant separateChatAssistant;

    @Test
    public void memoryTest()
    {
        String chat = testAssistant.chat("我是郑可，你的儿子！");
        System.out.println(chat);

        String chat1 = testAssistant.chat("我到底是谁？");
        System.out.println(chat1);

//        String chat = ollamaChatModel.chat("我叫做郑可，记住我的名字");
//        System.out.println(chat);
//
//        String chat1 = ollamaChatModel.chat("请问你，我是谁？");
//        System.out.println(chat1);


    }


    @Test
    public void testChatMemory()
    {

        // 先创建一个原始的内存
        MessageWindowChatMemory messageWindowChatMemory = MessageWindowChatMemory.withMaxMessages(10);

        // 先使用原始的Assistant来创建一个助手。

        TestAssistant assistant = AiServices.builder(TestAssistant.class)
                .chatLanguageModel(qwenChatModel)
                .chatMemory(messageWindowChatMemory)
                .build();

        String chat = assistant.chat("我的名字叫做郑可");
        System.out.println(chat);

        String chat1 = assistant.chat("请问我是谁？");
        System.out.println(chat1);

    }

    @Test
    public void testChatMemory2()
    {



        String chat = separateChatAssistant.chat(1,"我的名字叫做郑可，我头痛");
        System.out.println(chat);

        String chat1 = separateChatAssistant.chat(1,"请问我是谁？");
        System.out.println(chat1);

        String chat2 = separateChatAssistant.chat(2,"请问我是谁？");
        System.out.println(chat2);

    }

    @Test
    public void testChatMemory3()
    {



        String chat = testAssistant.chat("我的名字叫做郑可");
        System.out.println(chat);

        String chat1 = testAssistant.chat("请问我是谁？");
        System.out.println(chat1);

    }





}
