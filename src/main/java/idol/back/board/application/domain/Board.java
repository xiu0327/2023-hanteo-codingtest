package idol.back.board.application.domain;

public class Board {
    private Integer id;
    private Integer categoryId;
    private BoardType type;

    public Board(Integer categoryId, String categoryName) {
        this.categoryId = categoryId;
        this.type = lookUpType(categoryName);
    }

    /* getter */

    public BoardType getType() {
        return type;
    }

    public Integer getId() {
        return id;
    }

    /* 비즈니스 로직 */

    public void setId(Integer id) {
        this.id = id;
    }

    private static BoardType lookUpType(String categoryName){
        if (categoryName.startsWith("익명")){
            return BoardType.ANONYMOUS;
        }
        if (categoryName.equals("공지사항")){
            return BoardType.NOTICE;
        }
        return BoardType.BASIC;
    }
}
