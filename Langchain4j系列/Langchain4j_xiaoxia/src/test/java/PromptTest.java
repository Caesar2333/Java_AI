import com.caesar.XiaoxiaApp;
import com.caesar.assistant.SeparateChatAssistant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Author: Yuxian Zheng
 * Version: 1.0
 * Date: 2025/6/17
 */
@SpringBootTest(classes = XiaoxiaApp.class)
public class PromptTest {

    @Autowired
    SeparateChatAssistant separateChatAssistant;

    @Test
    public void systemPromptTest() {

        String chat = separateChatAssistant.chat(3, "你今天好漂亮啊！");
        System.out.println(chat);


    }



}
