import com.caesar.XiaoxiaApp;
import com.caesar.domain.Messages;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

/**
 * Author: Yuxian Zheng
 * Version: 1.0
 * Date: 2025/6/17
 */
@SpringBootTest(classes = XiaoxiaApp.class)
public class MongodbTest {


    @Autowired
    MongoTemplate mongoTemplate;

    @Test
    public void insert()
    {
        Messages messages = new Messages();
        messages.setMessage("你好啊，今晚有空吗？？");
        mongoTemplate.insert(messages);


    }

    @Test
    public void findById()
    {
        String id = "6851b1e7a1894d684c2078b9";
        Messages byId = mongoTemplate.findById(id, Messages.class);// 通过id找到整条记录出来，Messages.class有对应的collection的信息（也就是表的名字信息）
        // 而mybatisPlus中是自动映射，将你entity的类名字 驼峰映射成为表的名字，总之是映射过去的
        System.out.println(byId.getMessage());



    }


    @Test
    public void deleteById()
    {
        String id = "6851b1e7a1894d684c2078b9";
        Criteria id1 = Criteria.where("id_").is(id);
        Query query = new Query(id1);
        mongoTemplate.remove(query, Messages.class);


    }

    @Test
    public void updateById()
    {

        // 这个是PUT风格的，你自己给出一个id,
        String id = "6851b1e7a1894d684c2078b9";
        Criteria id1 = Criteria.where("id_").is(id);
        Query query = new Query(id1);

        // 给出更新的内容
        Update update = new Update();
        update.set("message", "今天的你，瘦了吗？");

        mongoTemplate.upsert(query,update,Messages.class);

        // api设计的难用的一比

    }


}
