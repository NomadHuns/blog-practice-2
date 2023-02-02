package shop.mtcoding.blog.model;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface BoardRepository {
    public List<Board> findAll();

    public Board findById(int id);

    public int insert(@Param("title") String title, @Param("content") String content, @Param("userId") int userId);

    public int updateById(@Param("title") String title, @Param("content") String content, @Param("userId") int userId,
            @Param("id") int id);

    public int deleteById(int id);
}
