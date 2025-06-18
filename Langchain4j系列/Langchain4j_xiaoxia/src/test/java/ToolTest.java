import com.caesar.XiaoxiaApp;
import com.caesar.assistant.SeparateChatAssistant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Author: Yuxian Zheng
 * Version: 1.0
 * Date: 2025/6/18
 */
@SpringBootTest(classes = XiaoxiaApp.class)
public class ToolTest {

    @Autowired
    SeparateChatAssistant separateChatAssistant;

    @Test
    public void toolTest()
    {
        String chat = separateChatAssistant.chat(1, "5+2等于多少呢? 然后47569503756的平方根是多少？两个都需要告诉我答案哦！");

        //答案：3，689706.4865

        System.out.println(chat);


    }



}
