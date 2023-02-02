package shop.mtcoding.blog.model;

import java.security.Timestamp;

import lombok.Getter;
import lombok.Setter;

/*  create table board_tb(
    id int auto_increment primary key,
    title varchar not null,
    content varchar not null,
    user_id int not null,
    created_at timestamp
    );
 */

@Getter
@Setter
public class Board {
    private Integer id;
    private String title;
    private String content;
    private Integer userId;
    private Timestamp createdAt;
}
