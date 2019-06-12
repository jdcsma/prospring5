package jun.prospring5.ch10.entity;

public enum Genre {

    POP("P"),
    JAZZ("J"),
    BULES("B"),
    COUNTRY("C");

    private String code;

    Genre(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "Genre{" +
                "code='" + code + '\'' +
                '}';
    }
}