import com.charart.spring.dao.AbstractDAO;
import com.charart.spring.config.JDBCConfig;
import com.charart.spring.dto.PostDto;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;
import java.util.stream.Collectors;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = JDBCConfig.class)
@Sql(scripts = "classpath:Microblog.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class PostJDBCTest {

    private final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("yyyy MM dd hh:mm:ss");

    private AbstractDAO <PostDto> postDAO;

    @Autowired
    public PostJDBCTest(AbstractDAO<PostDto> postDAO) { this.postDAO = postDAO; }

    @Test
    void create() {
        PostDto postDto = new PostDto();
        postDto.setTitle("Day 4");
        postDto.setContent("All is ok again");
        postDto.setCreate_at(LocalDateTime.now());
        postDAO.create(postDto);
        assertEquals("Day 4", postDAO.getById(4).getTitle());
        Set<String> titles = postDAO.getAll().stream().map(PostDto:: getTitle).collect(Collectors.toSet());
        assertEquals(Set.of("Day 1", "Day 2", "Day 3", "Day 4"), titles);
    }

    @Test
    void update (){
        PostDto postDto = postDAO.getById(1);
        postDto.setTitle("Day 5");
        postDto.setUpdate_at(LocalDateTime.now());
        postDAO.update(1, postDto);
        LocalDateTime now = LocalDateTime.now();
        PostDto update = postDAO.getById(1);
        assertEquals("Day 5", update.getTitle());
        assertEquals(now.format(TIME_FORMAT), update.getUpdate_at().format(TIME_FORMAT));
    }

    @Test
    void delete () {
        postDAO.delete(1);
        assertEquals(2, postDAO.getAll().size());
    }
}
