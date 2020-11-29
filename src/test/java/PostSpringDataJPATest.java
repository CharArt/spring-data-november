import com.charart.spring.config.JpaConfig;

import com.charart.spring.dao.repository.PostRepository;
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
@ContextConfiguration(classes = JpaConfig.class)
@Sql(scripts = "classpath:Microblog.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Transactional
public class PostSpringDataJPATest {

    private final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("yyyy MM dd hh:mm:ss");

    private PostRepository postRepo;
    @Autowired
    public PostSpringDataJPATest(PostRepository postRepo) {this.postRepo = postRepo;}

    @Test
    void create() {
        Post post = new Post();
        post.setTitle("Day 4");
        post.setContent("All is ok again");
        post.setCreate_at(LocalDateTime.now());
        postRepo.save(post);
        assertEquals("Day 4", postRepo.findById(4L).get().getTitle());
        Set<String> titles = postRepo.findAll().stream().map(Post:: getTitle).collect(Collectors.toSet());
        assertEquals(Set.of("Day 1", "Day 2", "Day 3", "Day 4"), titles);
    }

    @Test
    void update (){
        Post post = postRepo.findById(1L).get();
        post.setTitle("Day 5");
        post.setUpdate_at(LocalDateTime.now());
        postRepo.save(post);
        LocalDateTime now = LocalDateTime.now();
        Post update = postRepo.findById(1L).get();
        assertEquals("Day 5", update.getTitle());
        assertEquals(now.format(TIME_FORMAT), update.getUpdate_at().format(TIME_FORMAT));
    }

    @Test
    void delete () {
        postRepo.deleteById(1L);
        assertEquals(2, postRepo.findAll().size());
    }
}
