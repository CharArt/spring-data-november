import com.charart.spring.config.HibernateConfig;
import com.charart.spring.dao.AbstractDAO;
import com.charart.spring.entity.Post;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;
import java.util.stream.Collectors;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = HibernateConfig.class)
@Sql(scripts = "classpath:Microblog.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Transactional
public class PostHibernateTest {

    private final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("yyyy MM dd hh:mm:ss");

    private AbstractDAO<Post> postDAO;

    @Autowired
    public PostHibernateTest(AbstractDAO<Post> postDAO) { this.postDAO = postDAO; }

    @Test
    void create() {
        Post post = new Post();
        post.setTitle("Day 4");
        post.setContent("All is ok again");
        post.setCreate_at(LocalDateTime.now());
        postDAO.create(post);
        assertEquals("Day 4", postDAO.getById(4).getTitle());
        Set<String> titles = postDAO.getAll().stream().map(Post:: getTitle).collect(Collectors.toSet());
        assertEquals(Set.of("Day 1", "Day 2", "Day 3", "Day 4"), titles);
    }

    @Test
    void update (){
        Post post = postDAO.getById(1);
        post.setTitle("Day 5");
        post.setUpdate_at(LocalDateTime.now());
        postDAO.update(1, post);
        LocalDateTime now = LocalDateTime.now();
        Post update = postDAO.getById(1);
        assertEquals("Day 5", update.getTitle());
        assertEquals(now.format(TIME_FORMAT), update.getUpdate_at().format(TIME_FORMAT));
    }

    @Test
    void delete () {
        postDAO.delete(1);
        assertEquals(2, postDAO.getAll().size());
    }
}
